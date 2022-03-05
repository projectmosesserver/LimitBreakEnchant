package info.ahaha.limitbreakenchant;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.io.Serializable;

public class GUI implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient Inventory gui;
    private final GUIType type;

    public GUI(GUIType type){
        if (type == GUIType.LimitBreakAnvil){
            this.gui = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.DARK_PURPLE + "LimitBreakAnvil");
            ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
            for (int i = 0; i < this.gui.getSize(); i++) {
                if (i == 11 || i == 13 || i == 15)
                    continue;
                this.gui.setItem(i, item);
            }

        }else if (type == GUIType.LimitBreakEnchantTable){
            this.gui = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.DARK_PURPLE + "LimitBreakEnchantTable");
            ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
            for (int i = 0; i < this.gui.getSize(); i++) {
                if (i == 11 || i == 13 || i == 15)
                    continue;
                this.gui.setItem(i, item);
            }
        }
        this.type = type;
    }

    public GUIType getType() {
        return type;
    }

    public Inventory getGui(String guiData) {
        if (gui == null){

            if (type == GUIType.LimitBreakEnchantTable){
                this.gui = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.DARK_PURPLE + "LimitBreakEnchantTable");

            }else if (type == GUIType.LimitBreakAnvil){
                this.gui = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.DARK_PURPLE + "LimitBreakAnvil");

            }
            try {
                if (guiData.equalsIgnoreCase("")){
                    this.gui = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.DARK_PURPLE + type.name());
                    ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(" ");
                    item.setItemMeta(meta);
                    for (int i = 0; i < this.gui.getSize(); i++) {
                        if (i == 11 || i == 13 || i == 15)
                            continue;
                        this.gui.setItem(i, item);
                    }
                }else {
                    this.gui.setContents(ItemSerializable.itemStackArrayFromBase64(guiData));
                    return this.gui;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.gui;
    }

    public void remove(Block block){
        for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(),0.5,0.5,0.5)){
            if (entity instanceof ArmorStand){
                ArmorStand stand = (ArmorStand) entity;
                if (stand.getPersistentDataContainer().has(new NamespacedKey(LimitBreakEnchant.plugin,"limit_break"), LimitBreakEnchant.plugin.getGuiDataType())) {
                    GUI gui = stand.getPersistentDataContainer().get(new NamespacedKey(LimitBreakEnchant.plugin,"limit_break"),LimitBreakEnchant.plugin.getGuiDataType());
                    if (gui == null)return;
                    if (gui.getType() == GUIType.LimitBreakEnchantTable){
                        block.getWorld().dropItemNaturally(block.getLocation(),Items.getEnchantTable());
                    }else if (gui.getType() == GUIType.LimitBreakAnvil){
                        block.getWorld().dropItemNaturally(block.getLocation(),Items.getAnvil());
                    }
                    stand.remove();
                    return;
                }
            }
        }
    }

}
