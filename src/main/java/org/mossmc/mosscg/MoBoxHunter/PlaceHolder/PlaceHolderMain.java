package org.mossmc.mosscg.MoBoxHunter.PlaceHolder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mossmc.mosscg.MoBoxCore.Game.GameBasicInfo;
import org.mossmc.mosscg.MoBoxCore.Game.GameStatus;
import org.mossmc.mosscg.MoBoxHunter.BasicInfo;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerCache;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class PlaceHolderMain extends PlaceholderExpansion {
    @Override
    public String getAuthor() {
        return "MossCG";
    }
    @Override
    public String getIdentifier() {
        return "moboxhunter";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        GameStatus.gameStatus status = GameBasicInfo.gameStatus;
        switch (identifier) {
            case "htremain":
                return String.valueOf(PlayerCache.hunterList.size());
            case "rnremain":
                //TODO Optimize
                 int l = 0;
                 for(UUID uuid : PlayerCache.runnerStatusMap.keySet()){
                     if(PlayerCache.runnerStatusMap.get(uuid) == BasicInfo.runnerStatus.Alive)l++;
                 }
                 return String.valueOf(l);
            case "role":
                BasicInfo.playerRole role = PlayerCache.getPlayerRole(player.getUniqueId());
                if (status == GameStatus.gameStatus.Waiting) {
                    return ChatColor.GRAY + "[等待中]";
                }
                switch (role) {
                    case Runner:
                        return ChatColor.GREEN + "[逃亡者]";
                    case Hunter:
                        return ChatColor.RED + "[猎人]";
                    case Observer:
                        return ChatColor.GRAY + "[旁观者]";
                    default:
                        return "未知身份";
                }
            case "role_not":
                BasicInfo.playerRole role2 = PlayerCache.getPlayerRole(player.getUniqueId());
                if (status == GameStatus.gameStatus.Waiting) {
                    return ChatColor.GRAY + "等待中";
                }
                switch (role2) {
                    case Runner:
                        return ChatColor.GREEN + "逃亡者";
                    case Hunter:
                        return ChatColor.RED + "猎人";
                    case Observer:
                        return ChatColor.GRAY + "旁观者";
                    default:
                        return "未知身份";
                }
            case "eta":
                if (status == GameStatus.gameStatus.Waiting) {
                    return ChatColor.GRAY + "Not Available";
                }
                SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                if (BasicInfo.isFastMode) {

                    return (ChatColor.RED + "剩余时间: " + ChatColor.WHITE + format.format(1200000L - System.currentTimeMillis() + BasicInfo.StartTime));
                } else {

                    return (ChatColor.GREEN + "持续时间: " + ChatColor.WHITE + format.format(System.currentTimeMillis() - BasicInfo.StartTime));
                }
            case "goal":
                BasicInfo.playerRole rolex = PlayerCache.getPlayerRole(player.getUniqueId());
                if (status == GameStatus.gameStatus.Waiting) {
                    return ChatColor.GRAY + "等待游戏开始....";
                }
                switch (rolex) {
                    case Runner:
                        if (BasicInfo.isFastMode) {
                            return ChatColor.GREEN + "躲避猎人追杀并存活20分钟";
                        } else {
                            return ChatColor.GREEN + "躲避猎人追杀并击杀末影龙";
                        }
                    case Hunter:
                        return ChatColor.RED + "击杀逃亡者";
                    case Observer:
                        return ChatColor.GRAY + "你没有目标";
                    default:
                        return "未知身份";
                }
            default:
                return "null";
        }
    }
}
