package org.mossmc.mosscg.MoBoxHunter.Step;

import com.alonsoaliaga.alonsolevels.api.AlonsoLevelsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.mossmc.mosscg.MoBoxCore.Chat.ChatChannel;
import org.mossmc.mosscg.MoBoxCore.Game.GameBasicInfo;
import org.mossmc.mosscg.MoBoxCore.Game.GameEnd;
import org.mossmc.mosscg.MoBoxCore.Game.GameStatus;
import org.mossmc.mosscg.MoBoxHunter.BasicInfo;
import org.mossmc.mosscg.MoBoxHunter.Main;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerCache;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerPoint;

public class StepEnding {
    @SuppressWarnings("deprecation")
    public static void runStep() {
        Main.logger.info(ChatColor.GREEN + "游戏正在进入结束阶段！");
        GameBasicInfo.gameStatus = GameStatus.gameStatus.Ending;
        ChatChannel.useChannelChat = false;
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "====================================");
        Bukkit.broadcastMessage(ChatColor.GREEN + "游戏结束！");
        Bukkit.broadcastMessage(ChatColor.AQUA + "正在结算玩家信息！");
        try {
            PlayerPoint.countPlayerPoint();
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.setGameMode(GameMode.SPECTATOR);
                if (BasicInfo.endLocation != null) {
                    player.teleport(BasicInfo.endLocation);
                }
                if (BasicInfo.winner.equals(BasicInfo.playerRole.Runner)) {
                    if (PlayerCache.runnerList.contains(player.getUniqueId())) {
                        if (BasicInfo.isFastMode) {
                            player.sendTitle("§6§lVICTORY", "§a成功存活20分钟");
                        } else {
                            player.sendTitle("§6§lVICTORY", "§a成功击杀了末影龙");
                        }
                    }
                } else {
                    if (PlayerCache.hunterList.contains(player.getUniqueId())) {
                        player.sendTitle("§6§lVICTORY", "§a成功消灭了逃亡者");
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "====================================");
        Bukkit.broadcastMessage(ChatColor.GREEN + "本局排名：");
        Bukkit.broadcastMessage(ChatColor.RED + "第一名：" + PlayerPoint.getRankPlayerName(1));
        Bukkit.broadcastMessage(ChatColor.GOLD + "第二名：" + PlayerPoint.getRankPlayerName(2));
        Bukkit.broadcastMessage(ChatColor.YELLOW + "第三名：" + PlayerPoint.getRankPlayerName(3));
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "====================================");
        PlayerPoint.playerPointMap.forEach((uuid,integer) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                player.sendMessage(ChatColor.GREEN + "基础经验：" + PlayerPoint.basicPoint);
                if (PlayerCache.getPlayerRole(uuid).equals(BasicInfo.winner)) {
                    player.sendMessage(ChatColor.GREEN + "胜利经验：" + PlayerPoint.getPlayerWinnerPoint(uuid));
                }
                player.sendMessage(ChatColor.GREEN + "击杀经验：" + PlayerPoint.playerPointKillMap.getOrDefault(uuid, 0));
                player.sendMessage(ChatColor.GREEN + "本局总经验：" + PlayerPoint.playerPointMap.get(uuid));
                AlonsoLevelsAPI.addExperience(uuid,integer);
                // NOTICE - THIS IS ONLY FOR MY NETWORK.
            }
        });
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "====================================");
        Main.logger.info(ChatColor.GREEN + "游戏进入到结束阶段！");

        GameEnd.startEnd();

    }
}
