package org.mossmc.mosscg.MoBoxHunter.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.mossmc.mosscg.MoBoxCore.Game.GameBasicInfo;
import org.mossmc.mosscg.MoBoxCore.Game.GameStatus;
import org.mossmc.mosscg.MoBoxHunter.BasicInfo;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerCache;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerChat;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerCheck;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerStatic;

public class ListenerKill implements Listener {
    @EventHandler
    public static void onKill(PlayerDeathEvent event) {
        if (GameBasicInfo.gameStatus.equals(GameStatus.gameStatus.Running)) {
            Player target = event.getEntity();
            Player killer = event.getEntity().getKiller();
            int newCount;
            boolean sameTeam = false;
            if (killer != null) {
                if (PlayerCache.getPlayerRole(killer.getUniqueId()).equals(PlayerCache.getPlayerRole(target.getUniqueId()))) {
                    sameTeam = true;
                }
                if (!sameTeam) {
                    if (PlayerStatic.killCount.containsKey(killer.getUniqueId())) {
                        newCount = PlayerStatic.killCount.get(killer.getUniqueId()) + 1;
                    } else {
                        newCount = 1;
                    }
                    PlayerStatic.killCount.put(killer.getUniqueId(), newCount);
                }
            }
            if (!sameTeam) {
                if (PlayerStatic.deathCount.containsKey(target.getUniqueId())) {
                    newCount = PlayerStatic.deathCount.get(target.getUniqueId()) + 1;
                } else {
                    newCount = 1;
                }
                PlayerStatic.deathCount.put(target.getUniqueId(), newCount);
            }

            event.getDrops().removeIf(itemStack -> itemStack.getType().equals(Material.COMPASS));
            switch (PlayerCache.getPlayerRole(target.getUniqueId())) {
                case Observer:
                    event.setDeathMessage(null);
                    target.setGameMode(GameMode.SPECTATOR);
                    break;
                case Hunter:
                    Bukkit.broadcastMessage(ChatColor.RED + target.getName() + ChatColor.WHITE + "被击杀。");
                    target.sendTitle(ChatColor.DARK_RED + "" + ChatColor.BOLD + "WASTED", ChatColor.RED + "你已阵亡", 10, 100, 10);
                    target.sendMessage(ChatColor.GREEN + "你被逃亡者击杀了.但是你的猎人身份给予了你永生的权力。");
                    target.setGameMode(GameMode.SURVIVAL);
                    break;
                case Runner:
                    if (PlayerCache.runnerStatusMap.get(target.getUniqueId()).equals(BasicInfo.runnerStatus.Dead)) {
                        target.setGameMode(GameMode.SPECTATOR);
                        event.setDeathMessage(null);
                        break;
                    }
                    target.sendTitle(ChatColor.DARK_RED + "" + ChatColor.BOLD + "WASTED", ChatColor.RED + "你已阵亡", 10, 100, 10);
                    target.sendMessage(ChatColor.RED + "你已经阵亡。很抱歉你不能活到最后。");
                    PlayerCache.runnerStatusMap.replace(target.getUniqueId(), BasicInfo.runnerStatus.Dead);
                    BasicInfo.endLocation = target.getLocation();
                    target.setGameMode(GameMode.SPECTATOR);
                    PlayerChat.setPlayerChatObserver(target.getUniqueId());
                    Bukkit.broadcastMessage(ChatColor.WHITE + "=======================================================");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(ChatColor.AQUA + "逃亡者" + target.getName() + "已被击杀。 剩余玩家名单:");
                    Bukkit.broadcastMessage(ChatColor.GREEN + "逃亡者: " + PlayerCache.getRunnerNameList());
                    Bukkit.broadcastMessage(ChatColor.RED + "猎人: " + PlayerCache.getHunterNameList());
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(ChatColor.WHITE + "=======================================================");
                    break;
                default:
                    break;
            }
            PlayerCheck.check();
        }
    }
}
