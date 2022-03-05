package info.ahaha.limitbreakenchant.listener;

import info.ahaha.limitbreakenchant.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;

public class PlaceListener implements Listener {


    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (e.canBuild()) {
            if (e.getItemInHand().isSimilar(Items.getEnchantTable())) {
                ArmorStand stand = (ArmorStand) e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.ARMOR_STAND);
                stand.getPersistentDataContainer().set(new NamespacedKey(LimitBreakEnchant.plugin,"limit_break"),LimitBreakEnchant.plugin.getGuiDataType(),new GUI(GUIType.LimitBreakEnchantTable));
                stand.getPersistentDataContainer().set(new NamespacedKey(LimitBreakEnchant.plugin,"limit_break_data"),PersistentDataType.STRING,"");
                stand.setMarker(true);
                stand.setVisible(false);
                stand.setGravity(false);
            } else if (e.getItemInHand().isSimilar(Items.getAnvil())) {
                ArmorStand stand = (ArmorStand) e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.ARMOR_STAND);
                stand.getPersistentDataContainer().set(new NamespacedKey(LimitBreakEnchant.plugin,"limit_break"),LimitBreakEnchant.plugin.getGuiDataType(),new GUI(GUIType.LimitBreakAnvil));
                stand.getPersistentDataContainer().set(new NamespacedKey(LimitBreakEnchant.plugin,"limit_break_data"),PersistentDataType.STRING,"");
                stand.setMarker(true);
                stand.setVisible(false);
                stand.setGravity(false);
            }
        }
    }
}

