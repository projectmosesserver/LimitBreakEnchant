package info.ahaha.limitbreakenchant;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIs {

    public static Map<String,Inventory> ench = new HashMap<>();
    public static Map<String,Inventory> anvil = new HashMap<>();
    public static Map<String,String>enchData = new HashMap<>();
    public static Map<String,String>anvilData = new HashMap<>();

    public GUIs(Material type, String loc){
        if (type == Material.ENCHANTING_TABLE){
            if (!ench.containsKey(loc)) {
                Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.DARK_PURPLE + "LimitBreakEnchantTable");
                ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(" ");
                item.setItemMeta(meta);
                for (int i = 0; i < inv.getSize(); i++) {
                    if (i == 11 || i == 13 || i == 15)
                        continue;
                    inv.setItem(i, item);
                }
                ench.put(loc, inv);
                enchData.put(loc,ItemSerializable.itemStackArrayToBase64(inv.getContents()));
            }
        }
        if (type == Material.ANVIL){
            if (!anvil.containsKey(loc)) {
                Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.DARK_PURPLE + "LimitBreakAnvil");
                ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(" ");
                item.setItemMeta(meta);
                for (int i = 0; i < inv.getSize(); i++) {
                    if (i == 11 || i == 13 || i == 15)
                        continue;
                    inv.setItem(i, item);
                }
                anvil.put(loc, inv);
                anvilData.put(loc,ItemSerializable.itemStackArrayToBase64(inv.getContents()));
            }
        }

    }

}
