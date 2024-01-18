package org.mossmc.mosscg.MoBoxHunter;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Utils {
    public static ItemStack voteTime() {
        ItemStack kit = new ItemStack(Material.CLOCK); //创建新的物品 是钻石胸甲
        ItemMeta meta = kit.getItemMeta(); //创建ItemMeta
        assert meta != null;
        meta.setDisplayName(ChatColor.AQUA + "极速模式");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7");
        lore.add("§f逃亡者获胜条件: ");
        lore.add("§a20分钟内不被猎人击杀");
        lore.add(" ");
        lore.add("§6投票选择该玩法的玩家:");
        lore.addAll(BasicInfo.choseTime);
        lore.add(" ");
        lore.add("§a点击投票！");
        if(BasicInfo.choseTime.isEmpty()){
            kit.setAmount(1);
        }else{
            kit.setAmount(BasicInfo.choseTime.size());
        }

        meta.setLore(lore);
        kit.setItemMeta(meta);
        return kit;
    }
    public static ItemStack voteItem(){
        ItemStack kit = new ItemStack(Material.TOTEM_OF_UNDYING); //创建新的物品 是钻石胸甲
        ItemMeta meta = kit.getItemMeta(); //创建ItemMeta
        assert meta != null;
        meta.setDisplayName(ChatColor.AQUA + "选择游戏模式");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§a点击投票！");
        kit.setAmount(1);
        meta.setLore(lore);
        kit.setItemMeta(meta);
        return kit;
    }
    public static ItemStack voteNormal() {
        ItemStack kit = new ItemStack(Material.DRAGON_HEAD); //创建新的物品 是钻石胸甲
        ItemMeta meta = kit.getItemMeta(); //创建ItemMeta
        assert meta != null;
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "正常模式");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7");
        lore.add("§f逃亡者获胜条件: ");
        lore.add("§c击杀末影龙");
        lore.add("");
        lore.add("§4§l注意: §c本模式耗时较长 (可能>1h)");
        lore.add(" ");
        lore.add("§6投票选择该玩法的玩家:");
        lore.addAll(BasicInfo.choseFull);
        lore.add(" ");
        lore.add("§a点击投票！");
        if(BasicInfo.choseFull.isEmpty()){
            kit.setAmount(1);
        }else{
            kit.setAmount(BasicInfo.choseFull.size());
        }
        meta.setLore(lore);
        kit.setItemMeta(meta);
        return kit;
    }
}
