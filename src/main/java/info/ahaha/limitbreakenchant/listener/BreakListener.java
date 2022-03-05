package info.ahaha.limitbreakenchant.listener;

import info.ahaha.limitbreakenchant.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.TileState;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.persistence.PersistentDataType;

public class BreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.ENCHANTING_TABLE || e.getBlock().getType() == Material.ANVIL){
            for (Entity entity : e.getBlock().getWorld().getNearbyEntities(e.getBlock().getLocation(),0.5,0.5,0.5)){
                if (entity instanceof ArmorStand){
                    ArmorStand stand = (ArmorStand) entity;
                    if (stand.getPersistentDataContainer().has(new NamespacedKey(LimitBreakEnchant.plugin,"limit_break"),LimitBreakEnchant.plugin.getGuiDataType())){
                        GUI gui = stand.getPersistentDataContainer().get(new NamespacedKey(LimitBreakEnchant.plugin,"limit_break"),LimitBreakEnchant.plugin.getGuiDataType());
                        if (gui == null)continue;
                        e.setDropItems(false);
                        gui.remove(e.getBlock());
                    }
                }
            }
        }
        /*if (e.getBlock().getState() instanceof TileState) {
            TileState state = (TileState) e.getBlock().getState();
            if (state.getPersistentDataContainer().has(new NamespacedKey(LimitBreakEnchant.plugin, "enchant_table_loc"), PersistentDataType.STRING)) {
                String l = state.getPersistentDataContainer().get(new NamespacedKey(LimitBreakEnchant.plugin, "enchant_table_loc"), PersistentDataType.STRING);
                for (EnchantLoc loc : EnchantLoc.locs) {
                    if (loc.getLoc().equalsIgnoreCase(l)) {
                        loc.remove();
                        GUIs.ench.remove(l);
                        e.setDropItems(false);
                        e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), Items.getEnchantTable());
                        return;
                    }
                }
            }
        }

        if (AnvilLoc.locs.size() != 0) {
            for (AnvilLoc loc : AnvilLoc.locs) {
                if (loc.equalBlock(e.getBlock())) {
                    loc.remove();
                    GUIs.anvil.remove(loc.getLoc());
                    e.setDropItems(false);
                    e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), Items.getAnvil());
                    return;
                }
            }
        }*/
    }
}
