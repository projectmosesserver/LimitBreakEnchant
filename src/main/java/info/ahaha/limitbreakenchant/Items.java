package info.ahaha.limitbreakenchant;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class Items {

    public static ItemStack getEnchantTable(){
        ItemStack item = new ItemStack(Material.ENCHANTING_TABLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"LimitBreakEnchantTable");
        meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GRAY+"通常のエンチャントレベルの上限を超えたエンチャントをすることができます!")));
        meta.addEnchant(Enchantment.DAMAGE_ALL,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getLapis(){
        ItemStack item = new ItemStack(Material.LAPIS_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE+"The erudite lapislazuli");
        meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GREEN+"オーバーエンチャントするための素材です！")));
        meta.addEnchant(Enchantment.DAMAGE_ALL,6,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack getCompObsidian(){
        ItemStack item = new ItemStack(Material.OBSIDIAN);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Compressed obsidian");
        meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GREEN+"LimitBreakEnchantTableの素材です！")));
        meta.addEnchant(Enchantment.DAMAGE_ALL,6,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getCompCompObsidian(){
        ItemStack item = new ItemStack(Material.OBSIDIAN);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Obsidian compressed to 1/81");
        meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GREEN+"LimitBreakEnchantTableの素材です！")));
        meta.addEnchant(Enchantment.DAMAGE_ALL,6,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack getCompDiamond(){
        ItemStack item = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Compressed diamond");
        meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GREEN+"LimitBreakEnchantTableの素材です！")));
        meta.addEnchant(Enchantment.DAMAGE_ALL,6,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack getCompBookshelf(){
        ItemStack item = new ItemStack(Material.BOOKSHELF);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Compressed Bookshelf");
        meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GREEN+"LimitBreakEnchantTableの素材です！")));
        meta.addEnchant(Enchantment.DAMAGE_ALL,6,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getAnvil(){
        ItemStack item = new ItemStack(Material.ANVIL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"LimitBreakAnvil");
        meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GRAY+"通常のエンチャントレベルの上限を超えたエンチャントを合成することができます！")));
        meta.addEnchant(Enchantment.DAMAGE_ALL,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getCompIron(){
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Compressed Iron");
        meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GREEN+"LimitBreakAnvilの素材です！")));
        meta.addEnchant(Enchantment.DAMAGE_ALL,6,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }



    public static ItemStack getComp64Iron(){
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Compressed to 1/64 Iron");
        meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GREEN+"LimitBreakAnvilの素材です！")));
        meta.addEnchant(Enchantment.DAMAGE_ALL,6,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getComp256Iron(){
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Compressed to 1/256 Iron");
        meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GREEN+"LimitBreakAnvilの素材です！")));
        meta.addEnchant(Enchantment.DAMAGE_ALL,6,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
}
