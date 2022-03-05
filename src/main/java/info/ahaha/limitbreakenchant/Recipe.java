package info.ahaha.limitbreakenchant;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class Recipe {

    public static void lapisRecipe(){
        NamespacedKey key = new NamespacedKey(LimitBreakEnchant.plugin,"lapis");
        ShapedRecipe recipe = new ShapedRecipe(key,Items.getLapis());

        recipe.shape("LLL","LLL","LLL");
        recipe.setIngredient('L', Material.LAPIS_BLOCK);

        Bukkit.addRecipe(recipe);
    }

    public static void compObsidianRecipe(){
        NamespacedKey key = new NamespacedKey(LimitBreakEnchant.plugin,"compressed_obsidian");
        ShapedRecipe recipe = new ShapedRecipe(key,Items.getCompObsidian());

        recipe.shape("LLL","LLL","LLL");
        recipe.setIngredient('L', Material.OBSIDIAN);

        Bukkit.addRecipe(recipe);
    }

    public static void compCompObsidianRecipe(){
        NamespacedKey key = new NamespacedKey(LimitBreakEnchant.plugin,"comp_comp_obsidian");
        ShapedRecipe recipe = new ShapedRecipe(key,Items.getCompCompObsidian());

        recipe.shape("LLL","LLL","LLL");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(Items.getCompObsidian()));

        Bukkit.addRecipe(recipe);
    }

    public static void compDiamondRecipe(){
        NamespacedKey key = new NamespacedKey(LimitBreakEnchant.plugin,"comp_diamond");
        ShapedRecipe recipe = new ShapedRecipe(key,Items.getCompDiamond());

        recipe.shape("LLL","LLL","LLL");
        recipe.setIngredient('L', Material.DIAMOND_BLOCK);

        Bukkit.addRecipe(recipe);
    }

    public static void compBookshelfRecipe(){
        NamespacedKey key = new NamespacedKey(LimitBreakEnchant.plugin,"comp_bookshelf");
        ShapedRecipe recipe = new ShapedRecipe(key,Items.getCompBookshelf());

        recipe.shape("LLL","LLL","LLL");
        recipe.setIngredient('L', Material.BOOKSHELF);

        Bukkit.addRecipe(recipe);
    }

    public static void enchantedtableRecipe(){
        NamespacedKey key = new NamespacedKey(LimitBreakEnchant.plugin,"custom_enchanted_table");
        ShapedRecipe recipe = new ShapedRecipe(key,Items.getEnchantTable());

        recipe.shape(" B ","DOD","OOO");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(Items.getCompBookshelf()));
        recipe.setIngredient('O', new RecipeChoice.ExactChoice(Items.getCompCompObsidian()));
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(Items.getCompDiamond()));


        Bukkit.addRecipe(recipe);
    }

    public static void compIronRecipe(){
        NamespacedKey key = new NamespacedKey(LimitBreakEnchant.plugin,"comp_iron");
        ShapedRecipe recipe = new ShapedRecipe(key,Items.getCompIron());

        recipe.shape("LLL","L L","LLL");
        recipe.setIngredient('L', Material.IRON_INGOT);

        Bukkit.addRecipe(recipe);
    }

    public static void comp64IronRecipe(){
        NamespacedKey key = new NamespacedKey(LimitBreakEnchant.plugin,"comp_64_iron");
        ShapedRecipe recipe = new ShapedRecipe(key,Items.getComp64Iron());

        recipe.shape("LLL","LL ","LLL");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(Items.getCompIron()));

        Bukkit.addRecipe(recipe);
    }

    public static void comp256IronRecipe(){
        NamespacedKey key = new NamespacedKey(LimitBreakEnchant.plugin,"comp_256_iron");
        ShapedRecipe recipe = new ShapedRecipe(key,Items.getComp256Iron());

        recipe.shape("LL ","L L","   ");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(Items.getComp64Iron()));

        Bukkit.addRecipe(recipe);
    }

    public static void anvilRecipe(){
        NamespacedKey key = new NamespacedKey(LimitBreakEnchant.plugin,"custom_anvil");
        ShapedRecipe recipe = new ShapedRecipe(key,Items.getAnvil());

        recipe.shape("LLL"," G ","GGG");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(Items.getComp256Iron()));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(Items.getComp64Iron()));

        Bukkit.addRecipe(recipe);
    }
}
