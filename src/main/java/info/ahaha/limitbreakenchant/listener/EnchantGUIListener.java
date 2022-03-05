package info.ahaha.limitbreakenchant.listener;

import info.ahaha.limitbreakenchant.*;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class EnchantGUIListener implements Listener {

    Map<Enchantment, Integer> kakutei = new HashMap<>();

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (!GUIOpenListener.limit.containsKey((Player) e.getPlayer())) return;
        Location loc = GUIOpenListener.limit.get((Player) e.getPlayer());
        for (Entity entity : e.getPlayer().getWorld().getNearbyEntities(loc, 0.5, 0.5, 0.5)) {
            if (entity instanceof ArmorStand) {
                ArmorStand stand = (ArmorStand) entity;
                if (stand.getPersistentDataContainer().has(new NamespacedKey(LimitBreakEnchant.plugin, "limit_break_data"), PersistentDataType.STRING)) {
                    String data = ItemSerializable.itemStackArrayToBase64(e.getView().getTopInventory().getContents());
                    stand.getPersistentDataContainer().set(new NamespacedKey(LimitBreakEnchant.plugin, "limit_break_data"), PersistentDataType.STRING, data);
                    GUIOpenListener.limit.remove((Player) e.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public void onEnchant(InventoryClickEvent e) {

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_PURPLE + "LimitBreakEnchantTable")) {
            if (e.getInventory().getType() != InventoryType.CHEST) return;

            Inventory encha = e.getView().getTopInventory();
            if (e.getClickedInventory() == null) return;
            e.setCancelled(true);

            if (e.getClickedInventory().equals(encha)) {
                if (e.getSlot() == 11) {
                    if (encha.getItem(11) != null) {
                        e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
                        e.setCurrentItem(new ItemStack(Material.AIR));
                        encha.clear(15);
                        kakutei.clear();
                        return;
                    }
                }
                if (e.getSlot() == 13) {
                    if (encha.getItem(13) != null) {

                        e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
                        e.setCurrentItem(new ItemStack(Material.AIR));
                        encha.clear(15);
                        kakutei.clear();
                        return;
                    }
                }
                if (e.getSlot() == 15) {
                    Player player = (Player) e.getWhoClicked();
                    EnchantData data = EnchantData.data;
                    if (encha.getItem(11) == null && encha.getItem(15) != null) {
                        player.getInventory().addItem(encha.getItem(15));
                        encha.clear(15);
                    }
                    if (encha.getItem(11) != null && encha.getItem(13) != null) {


                        ItemStack item = encha.getItem(11);
                        if (player.getLevel() >= Config.getCostLevel()) {
                            if (item.getType().name().contains("SWORD")) {

                                enchant(encha, e, data.getSword(), "SWORD");

                            } else if (item.getType().name().contains("SHOVEL") ||
                                    item.getType().name().contains("PICKAXE") ||
                                    item.getType().name().contains("HOE")) {
                                toolenchant(encha, e, data.getTool(), "PICKAXE", "SHOVEL", "HOE");

                            } else if (item.getType().name().contains("AXE")) {
                                enchant(encha, e, data.getAxe(), "AXE");

                            } else if (item.getType().name().contains("HELMET")) {
                                enchant(encha, e, data.getHead(), "HELMET");

                            } else if (item.getType().name().contains("LEGGINGS")) {
                                enchant(encha, e, data.getLeggings(), "LEGGINGS");

                            } else if (item.getType().name().contains("CHESTPLATE")) {
                                enchant(encha, e, data.getArmor(), "CHESTPLATE");

                            } else if (item.getType().name().contains("BOOTS")) {
                                enchant(encha, e, data.getBoots(), "BOOTS");

                            } else if (item.getType() == Material.BOW) {
                                enchant(encha, e, data.getBow(), "BOW");

                            } else if (item.getType() == Material.TRIDENT) {
                                enchant(encha, e, data.getTrident(), "TRIDENT");

                            } else if (item.getType() == Material.CROSSBOW) {
                                enchant(encha, e, data.getCrossbow(), "CROSSBOW");
                            } else if (item.getType() == Material.FISHING_ROD) {
                                enchant(encha, e, data.getFish(), "FISHING_ROD");
                            }
                            player.setLevel(player.getLevel() - Config.getCostLevel());
                        } else {
                            player.sendMessage(ChatColor.GOLD + "[ LimitBreakEnchant ] " + ChatColor.RED + "レベルが足りません！エンチャントをするためにはレベル" + Config.getCostLevel() + "必要です！");
                        }
                    }
                }
            }


            if (e.getClickedInventory().equals(e.getView().getBottomInventory())) {
                if (e.getCurrentItem() == null) return;
                if (e.getCurrentItem().getItemMeta() == null) return;
                if (!e.getCurrentItem().isSimilar(Items.getLapis())) {
                    if (e.getCurrentItem().getItemMeta().hasEnchants()) return;
                    ItemStack items = e.getCurrentItem();
                    if (items.getType().name().contains("SWORD") ||
                            items.getType().name().contains("SHOVEL") ||
                            items.getType().name().contains("PICKAXE") ||
                            items.getType().name().contains("HOE") ||
                            items.getType().name().contains("AXE") ||
                            items.getType().name().contains("HELMET") ||
                            items.getType().name().contains("LEGGINGS") ||
                            items.getType().name().contains("CHESTPLATE") ||
                            items.getType().name().contains("BOOTS") ||
                            items.getType().name().contains("BOW") ||
                            items.getType().name().contains("TRIDENT") ||
                            items.getType().name().contains("FISHING_ROD")
                    ) {
                        if (encha.getItem(11) != null) {
                            e.getWhoClicked().getInventory().addItem(encha.getItem(11));
                        }
                        encha.setItem(11, e.getCurrentItem());
                        e.setCurrentItem(new ItemStack(Material.AIR));
                        if (encha.getItem(13) != null) {
                            ItemStack item = encha.getItem(11).clone();
                            EnchantData data = EnchantData.data;
                            if (item.getType().name().contains("SWORD")) {
                                createResult(item, data.getSword(), encha);
                            } else if (item.getType().name().contains("SHOVEL") ||
                                    item.getType().name().contains("HOE") ||
                                    item.getType().name().contains("PICKAXE")) {
                                createResult(item, data.getTool(), encha);

                            } else if (item.getType().name().contains("AXE") && !item.getType().name().contains("PICKAXE")) {
                                createResult(item, data.getAxe(), encha);
                            } else if (item.getType().name().contains("HELMET")) {
                                createResult(item, data.getHead(), encha);

                            } else if (item.getType().name().contains("LEGGINGS")) {
                                createResult(item, data.getLeggings(), encha);

                            } else if (item.getType().name().contains("CHESTPLATE")) {
                                createResult(item, data.getArmor(), encha);

                            } else if (item.getType().name().contains("BOOTS")) {
                                createResult(item, data.getBoots(), encha);

                            } else if (item.getType() == Material.BOW) {
                                createResult(item, data.getBow(), encha);

                            } else if (item.getType() == Material.TRIDENT) {
                                createResult(item, data.getTrident(), encha);

                            } else if (item.getType() == Material.CROSSBOW) {
                                createResult(item, data.getCrossbow(), encha);
                            } else if (item.getType() == Material.FISHING_ROD) {
                                createResult(item, data.getFish(), encha);
                            }
                        }

                    }
                } else {
                    if (encha.getItem(13) == null) {
                        encha.setItem(13, e.getCurrentItem());
                        e.setCurrentItem(new ItemStack(Material.AIR));
                    } else {
                        if (encha.getItem(13).getAmount() + e.getCurrentItem().getAmount() < 64) {
                            encha.getItem(13).setAmount(encha.getItem(13).getAmount() + e.getCurrentItem().getAmount());
                            e.setCurrentItem(new ItemStack(Material.AIR));
                        } else {
                            int amount = encha.getItem(13).getAmount() + e.getCurrentItem().getAmount();
                            int result = amount - 64;
                            encha.getItem(13).setAmount(64);
                            e.getCurrentItem().setAmount(result);
                        }
                    }
                    if (encha.getItem(11) != null) {
                        ItemStack item = encha.getItem(11).clone();
                        EnchantData data = EnchantData.data;
                        if (item.getType().name().contains("SWORD")) {
                            createResult(item, data.getSword(), encha);
                        } else if (item.getType().name().contains("SHOVEL") ||
                                item.getType().name().contains("PICKAXE") ||
                                item.getType().name().contains("HOE")) {
                            createResult(item, data.getTool(), encha);

                        } else if (item.getType().name().contains("AXE") && !item.getType().name().contains("PICKAXE")) {
                            createResult(item, data.getAxe(), encha);
                        } else if (item.getType().name().contains("HELMET")) {
                            createResult(item, data.getHead(), encha);

                        } else if (item.getType().name().contains("LEGGINGS")) {
                            createResult(item, data.getLeggings(), encha);

                        } else if (item.getType().name().contains("CHESTPLATE")) {
                            createResult(item, data.getArmor(), encha);

                        } else if (item.getType().name().contains("BOOTS")) {
                            createResult(item, data.getBoots(), encha);

                        } else if (item.getType() == Material.BOW) {
                            createResult(item, data.getBow(), encha);

                        } else if (item.getType() == Material.TRIDENT) {
                            createResult(item, data.getTrident(), encha);

                        } else if (item.getType() == Material.CROSSBOW) {
                            createResult(item, data.getCrossbow(), encha);
                        } else if (item.getType() == Material.FISHING_ROD) {
                            createResult(item, data.getFish(), encha);
                        }
                    }
                }
            }
        }
    }

    public void createResult(ItemStack item, List<Enchantment> enchantments, Inventory encha) {
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantments.get(EnchantData.getRandomNumberInRange(0, enchantments.size() - 1)), EnchantData.getRandomNumberInRange(Config.getMinLevel(), Config.getMaxLevel()), true);
        if (meta.getLore() == null)
            meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GRAY + "......")));
        else {
            List<String> lore = new ArrayList<>(meta.getLore());
            lore.add(ChatColor.GRAY + ".....");

        }
        item.setItemMeta(meta);
        encha.setItem(15, item);
        for (Enchantment enchantment : item.getItemMeta().getEnchants().keySet())
            kakutei.put(enchantment, item.getItemMeta().getEnchantLevel(enchantment));
    }

    public void enchant(Inventory encha, InventoryClickEvent e, List<Enchantment> enchantData, String tool) {
        ItemStack item = encha.getItem(11).clone();
        ItemMeta meta = item.getItemMeta();
        if (item.getType().name().contains(tool)) {
            for (Enchantment enchantment : kakutei.keySet()) {

                meta.addEnchant(enchantment, kakutei.get(enchantment), true);
            }
            for (int i = 0; i < EnchantData.getRandomNumberInRange(1, 3); i++) {
                Enchantment enchantment = enchantData.get(EnchantData.getRandomNumberInRange(0, enchantData.size() - 1));
                if (!meta.getEnchants().containsKey(enchantment)) {
                    meta.addEnchant(enchantment, EnchantData.getRandomNumberInRange(Config.getMinLevel(), Config.getMaxLevel()), true);
                }

            }

            if (encha.getItem(13).getAmount() == Config.getLapisCost()) {
                encha.clear(13);
                encha.clear(11);
                item.setItemMeta(meta);
                encha.setItem(15, item);
            } else if (encha.getItem(13).getAmount() >= Config.getLapisCost()) {
                encha.getItem(13).setAmount(encha.getItem(13).getAmount() - Config.getLapisCost());
                encha.clear(11);
                item.setItemMeta(meta);
                encha.setItem(15, item);
            } else if (encha.getItem(13).getAmount() < Config.getLapisCost()) {
                Player player = (Player) e.getWhoClicked();
                player.sendMessage(ChatColor.GOLD + "[ LimitBreakEnchant ] " + ChatColor.RED + "The erudite lapislazuliが足りません！");
                e.setCancelled(true);
                return;
            }
            e.getWhoClicked().getWorld().playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1F, 1F);
            kakutei.clear();
        }
    }

    public void toolenchant(Inventory encha, InventoryClickEvent e, List<Enchantment> enchantData, String... tool) {
        ItemStack item = encha.getItem(11).clone();
        ItemMeta meta = item.getItemMeta();
        if (item.getType().name().contains(tool[0]) ||
                item.getType().name().contains(tool[1]) ||
                item.getType().name().contains(tool[2])) {
            for (Enchantment enchantment : kakutei.keySet()) {

                meta.addEnchant(enchantment, kakutei.get(enchantment), true);
            }
            for (int i = 0; i < EnchantData.getRandomNumberInRange(1, 3); i++) {
                Enchantment enchantment = enchantData.get(EnchantData.getRandomNumberInRange(0, enchantData.size() - 1));
                if (!meta.getEnchants().containsKey(enchantment)) {
                    meta.addEnchant(enchantment, EnchantData.getRandomNumberInRange(Config.getMinLevel(), Config.getMaxLevel()), true);
                }
            }

            if (encha.getItem(13).getAmount() == Config.getLapisCost()) {
                encha.clear(13);
                encha.clear(11);
                item.setItemMeta(meta);
                encha.setItem(15, item);
            } else if (encha.getItem(13).getAmount() > Config.getLapisCost()) {
                encha.getItem(13).setAmount(encha.getItem(13).getAmount() - Config.getLapisCost());
                encha.clear(11);
                item.setItemMeta(meta);
                encha.setItem(15, item);
            } else if (encha.getItem(13).getAmount() < Config.getLapisCost()) {
                Player player = (Player) e.getWhoClicked();
                player.sendMessage(ChatColor.GOLD + "[ LimitBreakEnchant ] " + ChatColor.RED + "The erudite lapislazuliが足りません！");
                e.setCancelled(true);
                return;
            }
            e.getWhoClicked().getWorld().playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1F, 1F);
            kakutei.clear();
        }
    }
}
