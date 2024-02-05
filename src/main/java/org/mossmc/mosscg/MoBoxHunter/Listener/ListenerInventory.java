package org.mossmc.mosscg.MoBoxHunter.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.mossmc.mosscg.MoBoxHunter.BasicInfo;

import java.util.Objects;

public class ListenerInventory implements Listener {


    @EventHandler
    public static void onInvClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("投票选择游戏模式")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (Objects.requireNonNull(e.getCurrentItem()).getType() == Material.CLOCK) {
                BasicInfo.choseFull.remove(ChatColor.WHITE + e.getWhoClicked().getName());
                BasicInfo.choseTime.remove(ChatColor.WHITE + e.getWhoClicked().getName());
                BasicInfo.choseTime.add(ChatColor.WHITE + e.getWhoClicked().getName());
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatColor.GREEN + "投票成功，你投给了: 极速模式");
            } else if (Objects.requireNonNull(e.getCurrentItem()).getType() == Material.DRAGON_HEAD) {
                BasicInfo.choseTime.remove(ChatColor.WHITE + e.getWhoClicked().getName());
                BasicInfo.choseFull.remove(ChatColor.WHITE + e.getWhoClicked().getName());
                BasicInfo.choseFull.add(ChatColor.WHITE + e.getWhoClicked().getName());
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatColor.RED + "投票成功，你投给了: 正常模式");
            }
        }
    }


}
