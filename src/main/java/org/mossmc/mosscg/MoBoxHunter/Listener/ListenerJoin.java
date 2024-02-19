package org.mossmc.mosscg.MoBoxHunter.Listener;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.mossmc.mosscg.MoBoxCore.Game.GameBasicInfo;
import org.mossmc.mosscg.MoBoxCore.Main;
import org.mossmc.mosscg.MoBoxCore.Player.PlayerPick;
import org.mossmc.mosscg.MoBoxHunter.BasicInfo;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerCache;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerChat;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerDamage;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerReconnect;
import org.mossmc.mosscg.MoBoxHunter.Utils;

public class ListenerJoin implements Listener {
    @EventHandler
    public static void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);
        switch (GameBasicInfo.gameStatus) {
            case Waiting:
                PlayerCache.playerList.add(player.getUniqueId());
                player.getInventory().clear();
                PlayerChat.initPlayerNormalChat(player);
                player.setGameMode(GameMode.ADVENTURE);
                event.setJoinMessage(ChatColor.translateAlternateColorCodes('&',PlaceholderAPI.setPlaceholders(event.getPlayer(),"%luckperms_prefix%%player_name%%luckperms_suffix%&e加入了游戏(&b"+ Main.instance.getServer().getOnlinePlayers().size()+"&e/&b"+GameBasicInfo.getGame.maxPlayer()+"&e)")));
                PlayerDamage.disableDamage(player);
                PlayerPick.disablePickUp(player);
                Inventory inventory = Bukkit.createInventory(player, 27, "投票选择游戏模式");
                inventory.setItem(11, Utils.voteTime());
                inventory.setItem(15, Utils.voteNormal());
                player.openInventory(inventory);
                player.getInventory().setItemInMainHand(Utils.voteItem());
                break;
            case Starting:
            case Running:
                PlayerDamage.enableDamage(player);
                PlayerPick.enablePickUp(player);
                if (PlayerCache.hunterList.contains(player.getUniqueId())) {
                    player.setGameMode(GameMode.SURVIVAL);
                    event.getPlayer().sendMessage(ChatColor.GREEN + "游戏尚未结束！已回到游戏！");
                    PlayerReconnect.reconnectPlayer(player.getUniqueId());
                    break;
                }
                if (PlayerCache.runnerList.contains(player.getUniqueId())) {
                    if (PlayerCache.runnerStatusMap.get(player.getUniqueId()).equals(BasicInfo.runnerStatus.Alive)) {
                        player.setGameMode(GameMode.SURVIVAL);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "游戏尚未结束！已回到游戏！");
                        PlayerReconnect.reconnectPlayer(player.getUniqueId());
                    } else {
                        player.setGameMode(GameMode.SPECTATOR);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "您已被击杀！现在正在观战中~");
                    }
                    break;
                }
                player.setGameMode(GameMode.SPECTATOR);
                PlayerCache.observerList.add(player.getUniqueId());
                PlayerChat.setPlayerChatObserver(player.getUniqueId());
                event.getPlayer().sendMessage(ChatColor.GREEN + "游戏已经开始！正在观战中~");
                break;
            case Ending:
                PlayerDamage.enableDamage(player);
                PlayerPick.enablePickUp(player);
                player.setGameMode(GameMode.SPECTATOR);
                event.getPlayer().kickPlayer(ChatColor.RED + "本场游戏已结束！");
                break;
            default:
                PlayerDamage.enableDamage(player);
                PlayerPick.enablePickUp(player);
                player.setGameMode(GameMode.SPECTATOR);
                event.getPlayer().kickPlayer(ChatColor.RED + "未知的游戏状态！");
                break;
        }
    }
}
