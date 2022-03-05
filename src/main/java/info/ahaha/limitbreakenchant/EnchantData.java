package info.ahaha.limitbreakenchant;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnchantData {

    private final List<Enchantment> sword = new ArrayList<>();
    private final List<Enchantment> tool = new ArrayList<>();
    private final List<Enchantment> axe = new ArrayList<>();
    private final List<Enchantment> head = new ArrayList<>();
    private final List<Enchantment> leggings = new ArrayList<>();
    private final List<Enchantment> armor = new ArrayList<>();
    private final List<Enchantment> boots = new ArrayList<>();
    private final List<Enchantment> bow = new ArrayList<>();
    private final List<Enchantment> crossbow = new ArrayList<>();
    private final List<Enchantment> fish = new ArrayList<>();
    private final List<Enchantment> trident = new ArrayList<>();

    public static EnchantData data;

    public EnchantData() {
        for (Enchantment ench : Enchantment.values()) {
            if (ench.getName().equals("MENDING") || ench.getName().equals("SILK_TOUCH"))
                continue;
            if (ench.getItemTarget().includes(Material.DIAMOND_SWORD)) {
                sword.add(ench);
            }
            if (ench.getItemTarget().includes(Material.DIAMOND_SHOVEL)) {
                tool.add(ench);
            }
            if (ench.getItemTarget().includes(Material.DIAMOND_AXE)){
                axe.add(ench);
            }
            if (ench.getItemTarget().includes(Material.DIAMOND_HELMET)) {
                head.add(ench);
            }
            if (ench.getItemTarget().includes(Material.DIAMOND_LEGGINGS)) {
                leggings.add(ench);
            }
            if (ench.getItemTarget().includes(Material.DIAMOND_CHESTPLATE)) {
                armor.add(ench);
            }
            if (ench.getItemTarget().includes(Material.DIAMOND_BOOTS)) {
                boots.add(ench);
            }
            if (ench.getItemTarget().includes(Material.BOW)) {
                bow.add(ench);
            }
            if (ench.getItemTarget().includes(Material.CROSSBOW)){
                crossbow.add(ench);
            }
            if (ench.getItemTarget().includes(Material.FISHING_ROD)){
                fish.add(ench);
            }
            if (ench.getItemTarget().includes(Material.TRIDENT)) {
                trident.add(ench);
            }
        }
        data = this;

    }

    public List<Enchantment> getSword() {
        return sword;
    }

    public List<Enchantment> getBow() {
        return bow;
    }

    public List<Enchantment> getCrossbow() {
        return crossbow;
    }

    public List<Enchantment> getFish() {
        return fish;
    }

    public List<Enchantment> getTrident() {
        return trident;
    }

    public List<Enchantment> getTool() {
        return tool;
    }

    public List<Enchantment> getHead() {
        return head;
    }

    public List<Enchantment> getArmor() {
        return armor;
    }

    public List<Enchantment> getLeggings() {
        return leggings;
    }

    public List<Enchantment> getBoots() {
        return boots;
    }

    public List<Enchantment> getAxe() {
        return axe;
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
