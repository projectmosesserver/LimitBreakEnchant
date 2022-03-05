package info.ahaha.limitbreakenchant.listener;

import info.ahaha.limitbreakenchant.*;
import org.bukkit.*;
import org.bukkit.block.TileState;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GUIOpenListener implements Listener {

    public static Map<Player, Location>limit = new HashMap<>();

    @EventHandler
    public void onOpen(PlayerInteractEvent e) {

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock() == null) return;
            if (e.getClickedBlock().getType() == Material.ENCHANTING_TABLE || e.getClickedBlock().getType() == Material.ANVIL) {
                for (Entity entity : e.getClickedBlock().getWorld().getNearbyEntities(e.getClickedBlock().getLocation(), 0.5, 0.5, 0.5)) {
                    if (entity instanceof ArmorStand) {
                        ArmorStand stand = (ArmorStand) entity;
                        if (stand.getPersistentDataContainer().has(new NamespacedKey(LimitBreakEnchant.plugin, "limit_break"), LimitBreakEnchant.plugin.getGuiDataType())) {
                            GUI gui = stand.getPersistentDataContainer().get(new NamespacedKey(LimitBreakEnchant.plugin, "limit_break"), LimitBreakEnchant.plugin.getGuiDataType());
                            String data = stand.getPersistentDataContainer().get(new NamespacedKey(LimitBreakEnchant.plugin, "limit_break_data"), PersistentDataType.STRING);
                            if (gui == null) return;
                            e.setCancelled(true);
                            if (data.equalsIgnoreCase("")){
                                e.getPlayer().openInventory(gui.getGui(data));

                            }else {
                                Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.DARK_PURPLE+gui.getType().name());
                                try {
                                    inv.setContents(ItemSerializable.itemStackArrayFromBase64(data));
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                e.getPlayer().openInventory(inv);
                            }
                            limit.put(e.getPlayer(),e.getClickedBlock().getLocation());
                            break;
                        }
                    }
                }
            }
        }
    }
}
