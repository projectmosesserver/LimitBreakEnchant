package info.ahaha.limitbreakenchant.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static org.bukkit.Bukkit.getLogger;

public class AnvilGUIListener implements Listener {

    Map<UUID, Integer> cost = new HashMap<>();
    Map<UUID, Integer> result = new HashMap<>();
    Map<UUID, Map<Enchantment, Integer>> resultench;
    Map<UUID, Map<Integer, ItemStack>> items;

    @EventHandler
    public void onAnvil(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals(ChatColor.DARK_PURPLE + "LimitBreakAnvil")) {
            Inventory anvil = e.getView().getTopInventory();
            if (resultench == null && items == null) {
                resultench = new HashMap<>();
                items = new HashMap<>();
            }
            if (!cost.containsKey(player.getUniqueId()) && !result.containsKey(player.getUniqueId())) {
                cost.put(player.getUniqueId(), 27);
                result.put(player.getUniqueId(), 0);
            }
            e.setCancelled(true);
            if (e.getClickedInventory() != null)
                if (e.getClickedInventory().equals(anvil)) {
                    if (anvil.getItem(11) != null && anvil.getItem(13) == null) {
                        if (e.getSlot() == 11) {
                            e.getWhoClicked().getInventory().addItem(anvil.getItem(11));
                            anvil.clear(11);
                            anvil.clear(15);
                            resultench.get(player.getUniqueId()).clear();
                            cost.put(player.getUniqueId(), 27);
                            result.put(player.getUniqueId(), 0);
                            return;
                        }

                    }
                    if (anvil.getItem(11) == null && anvil.getItem(13) != null) {
                        if (e.getSlot() == 13) {
                            e.getWhoClicked().getInventory().addItem(anvil.getItem(13));
                            anvil.clear(13);
                            anvil.clear(15);
                            resultench.get(player.getUniqueId()).clear();
                            cost.put(player.getUniqueId(), 27);
                            result.put(player.getUniqueId(), 0);
                            return;
                        }
                    }
                    if (anvil.getItem(11) != null && anvil.getItem(13) != null) {
                        if (e.getSlot() == 11) {
                            e.getWhoClicked().getInventory().addItem(anvil.getItem(11));
                            anvil.clear(11);
                            anvil.clear(15);
                            resultench.get(player.getUniqueId()).clear();
                            cost.put(player.getUniqueId(), 27);
                            result.put(player.getUniqueId(), 0);
                            return;

                        }
                        if (e.getSlot() == 13) {
                            e.getWhoClicked().getInventory().addItem(anvil.getItem(13));
                            anvil.clear(13);
                            anvil.clear(15);
                            resultench.get(player.getUniqueId()).clear();
                            cost.put(player.getUniqueId(), 27);
                            result.put(player.getUniqueId(), 0);
                            return;
                        }
                        if (e.getSlot() == 15) {
                            if (player.getLevel() >= cost.get(player.getUniqueId())) {
                                ItemStack first = items.get(player.getUniqueId()).get(1).clone();
                                ItemMeta meta1 = first.getItemMeta();
                                for (Enchantment ench : meta1.getEnchants().keySet()) {

                                    meta1.removeEnchant(ench);
                                }
                                for (Enchantment ench : resultench.get(player.getUniqueId()).keySet()) {
                                    meta1.addEnchant(ench, resultench.get(player.getUniqueId()).get(ench), true);
                                }
                                first.setItemMeta(meta1);
                                anvil.setItem(15, first);
                                e.getWhoClicked().getWorld().playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_USE, 1L, 1L);
                                items.remove(player.getUniqueId());
                                player.setLevel(player.getLevel() - cost.get(player.getUniqueId()));
                                resultench.remove(player.getUniqueId());
                                cost.remove(player.getUniqueId());
                                result.remove(player.getUniqueId());
                                anvil.clear(11);
                                anvil.clear(13);
                                player.getInventory().addItem(anvil.getItem(15));
                                anvil.clear(15);
                            } else {
                                player.sendMessage(ChatColor.GOLD + "[ LimitBreakEnchant ] " + ChatColor.RED + "レベルが足りません！");
                            }

                        }


                    }
                }


            if (e.getCurrentItem() != null)
                if (e.getClickedInventory() != null)
                    if (e.getClickedInventory().equals(e.getWhoClicked().getInventory())) {
                        if (e.getCurrentItem().getItemMeta() == null) return;
                        if (anvil.getItem(11) == null && anvil.getItem(13) == null) {
                            anvil.setItem(11, e.getCurrentItem());
                            Map<Integer, ItemStack> itemData = new HashMap<>(items.get(player.getUniqueId()));
                            itemData.put(1, e.getCurrentItem());
                            items.put(player.getUniqueId(), itemData);
                            e.setCurrentItem(new ItemStack(Material.AIR));

                            return;
                        }
                        if (e.getCurrentItem().getItemMeta().hasEnchants()) {
                            if (anvil.getItem(11) != null && anvil.getItem(13) != null) {
                                return;
                            }

                            if (anvil.getItem(11) == null && anvil.getItem(13) != null) {
                                if (anvil.getItem(13).getType() == e.getCurrentItem().getType()) {
                                    anvil.setItem(11, e.getCurrentItem());
                                    Map<Integer, ItemStack> itemData = new HashMap<>(items.get(player.getUniqueId()));
                                    itemData.put(1, e.getCurrentItem());
                                    items.put(player.getUniqueId(), itemData);
                                    e.setCurrentItem(new ItemStack(Material.AIR));
                                    createResult(player, items.get(player.getUniqueId()).get(1), items.get(player.getUniqueId()).get(2), anvil);
                                    return;
                                }
                            }
                            if (anvil.getItem(11) != null && anvil.getItem(13) == null) {
                                if (anvil.getItem(11).getType() == e.getCurrentItem().getType()) {
                                    anvil.setItem(13, e.getCurrentItem());
                                    Map<Integer, ItemStack> itemData = new HashMap<>(items.get(player.getUniqueId()));
                                    itemData.put(2, e.getCurrentItem());
                                    items.put(player.getUniqueId(), itemData);
                                    e.setCurrentItem(new ItemStack(Material.AIR));
                                    createResult(player, items.get(player.getUniqueId()).get(1), items.get(player.getUniqueId()).get(2), anvil);
                                    return;
                                }
                            }
                        }
                    }
        }
    }

    public void createResult(Player player, ItemStack first, ItemStack second, Inventory anvil) {

        Map<Enchantment, Integer> enchMap = new HashMap<>();
        for (Enchantment ench : second.getItemMeta().getEnchants().keySet()) {
            enchMap.put(ench, second.getItemMeta().getEnchantLevel(ench));
        }
        for (Enchantment ench : first.getItemMeta().getEnchants().keySet()) {
            if (first.getEnchantmentLevel(ench) > second.getEnchantmentLevel(ench))
                enchMap.put(ench, first.getItemMeta().getEnchantLevel(ench));
            else if (first.getEnchantmentLevel(ench) < second.getEnchantmentLevel(ench))
                enchMap.put(ench, second.getItemMeta().getEnchantLevel(ench));
            else enchMap.put(ench, first.getItemMeta().getEnchantLevel(ench));
        }
        ItemStack item = second.clone();
        ItemMeta meta = item.getItemMeta();
        resultench.put(player.getUniqueId(), enchMap);

        for (Enchantment ench : resultench.get(player.getUniqueId()).keySet()) {
            meta.addEnchant(ench, resultench.get(player.getUniqueId()).get(ench), true);
            result.put(player.getUniqueId(), result.get(player.getUniqueId()) + resultench.get(player.getUniqueId()).get(ench));
        }

        cost.put(player.getUniqueId(), cost.get(player.getUniqueId()) + result.get(player.getUniqueId()));
        if (meta.getLore() == null) {
            meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GOLD + "Cost : " + ChatColor.GREEN + cost)));
        } else {
            List<String> lore = new ArrayList<>(meta.getLore());
            lore.add(ChatColor.GOLD + "Cost : " + ChatColor.GREEN + cost);
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        anvil.setItem(15, item);


    }


}
