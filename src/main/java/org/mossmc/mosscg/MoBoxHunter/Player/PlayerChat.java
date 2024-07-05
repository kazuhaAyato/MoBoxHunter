package org.mossmc.mosscg.MoBoxHunter.Player;

import com.alonsoaliaga.alonsolevels.api.AlonsoLevelsAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mossmc.mosscg.MoBoxCore.Chat.ChatChannel;

import java.util.UUID;

public class PlayerChat {
    public static void initNormalChat() {
        ChatChannel.addChannel("default");
        ChatChannel.useChannelChat = true;
    }
    public static void initPlayerNormalChat(Player p){
        ChatChannel.addPlayerChatChannel(p.getUniqueId(),"default");
        ChatChannel.setPlayerChatPrefix(p.getUniqueId(), ChatColor.translateAlternateColorCodes('&',"&8[&b&b%clv_player_level_colored%✰&8]%luckperms_prefix%"));
    }
    public static void initPlayerChat() {

        ChatChannel.addChannel("runner");
        ChatChannel.addChannel("hunter");
        ChatChannel.addChannel("observer");
        ChatChannel.setChatCopyChannel("runner", "observer");
        ChatChannel.setChatCopyChannel("hunter", "observer");
        PlayerCache.runnerList.forEach(PlayerChat::setPlayerChatRunner);
        PlayerCache.hunterList.forEach(PlayerChat::setPlayerChatHunter);
    }

    public static void setPlayerChatRunner(UUID player) {
        ChatChannel.resetPlayerChat(player);
        ChatChannel.addPlayerChatChannel(player, "runner");
        ChatChannel.setPlayerChatColor(player, ChatColor.WHITE);
        ChatChannel.setPlayerChatPrefix(player,ChatColor.translateAlternateColorCodes('&',"&8[&b%clv_player_level_colored%✰&8]"+ ChatColor.GREEN + "[逃亡者]"));
    }

    public static void setPlayerChatHunter(UUID player) {
        ChatChannel.resetPlayerChat(player);
        ChatChannel.addPlayerChatChannel(player, "hunter");
        ChatChannel.setPlayerChatColor(player, ChatColor.WHITE);
        ChatChannel.setPlayerChatPrefix(player, ChatColor.translateAlternateColorCodes('&',"&8[&b%clv_player_level_colored%✰&8]"+ ChatColor.RED + "[猎人]"));
    }

    public static void setPlayerChatObserver(UUID player) {
        ChatChannel.resetPlayerChat(player);
        ChatChannel.addPlayerChatChannel(player, "observer");
        ChatChannel.setPlayerChatColor(player, ChatColor.GRAY);
        ChatChannel.setPlayerChatPrefix(player, ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&',"&8[&b%clv_player_level_colored%✰&8]"+ ChatColor.GRAY + "[观察者]"));
    }

}
