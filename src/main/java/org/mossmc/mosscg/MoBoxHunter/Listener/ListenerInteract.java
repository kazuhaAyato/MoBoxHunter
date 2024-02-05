package org.mossmc.mosscg.MoBoxHunter.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.mossmc.mosscg.MoBoxHunter.BasicInfo;
import org.mossmc.mosscg.MoBoxHunter.Utils;

public class ListenerInteract implements Listener {
    @EventHandler
    public static void onInteract(PlayerInteractEvent event) {
        if ((event.getPlayer().getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING) && (!BasicInfo.canInteract)) {
            Inventory inventory = Bukkit.createInventory(event.getPlayer(), 27, "投票选择游戏模式");
            inventory.setItem(11, Utils.voteTime());
            inventory.setItem(15, Utils.voteNormal());
            event.getPlayer().openInventory(inventory);
        }
        if (!BasicInfo.canInteract) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public static void onInteract(PlayerInteractAtEntityEvent event) {
        if (!BasicInfo.canInteract) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public static void onInteract(PlayerInteractEntityEvent event) {
        if (!BasicInfo.canInteract) {
            event.setCancelled(true);
        }
    }
}
