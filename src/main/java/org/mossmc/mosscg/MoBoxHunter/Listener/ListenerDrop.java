package org.mossmc.mosscg.MoBoxHunter.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.mossmc.mosscg.MoBoxCore.Game.GameBasicInfo;
import org.mossmc.mosscg.MoBoxCore.Game.GameStatus;
import org.mossmc.mosscg.MoBoxHunter.BasicInfo;

public class ListenerDrop implements Listener {
    @EventHandler
    public static void onDrop(PlayerDropItemEvent event) {
        Item item = event.getItemDrop();
        if(GameBasicInfo.gameStatus == GameStatus.gameStatus.Waiting || GameBasicInfo.gameStatus == GameStatus.gameStatus.Starting ){
            event.setCancelled(true); // 防止丢弃不死图腾
        }
        if (item.getItemStack().getType().equals(Material.COMPASS)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "你不可以丢弃指南针。");
        }
    }
}
