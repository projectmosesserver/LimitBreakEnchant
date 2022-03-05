package info.ahaha.limitbreakenchant;

import info.ahaha.limitbreakenchant.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class LimitBreakEnchant extends JavaPlugin {

    public static LimitBreakEnchant plugin;
    private GUIDataType guiDataType;
    private DataManager manager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        guiDataType = new GUIDataType();
        manager = new DataManager(this);
        Path path = Paths.get(String.valueOf(plugin.getDataFolder()));
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                getLogger().info(e.toString());
            }
        }
        new EnchantData();

        SaveandLoad.Load();

        getServer().getPluginManager().registerEvents(new BreakListener(),this);
        getServer().getPluginManager().registerEvents(new PlaceListener(),this);
        getServer().getPluginManager().registerEvents(new GUIOpenListener(),this);
        getServer().getPluginManager().registerEvents(new AnvilGUIListener(),this);
        getServer().getPluginManager().registerEvents(new EnchantGUIListener(),this);

        Recipe.enchantedtableRecipe();
        Recipe.compBookshelfRecipe();
        Recipe.lapisRecipe();
        Recipe.compDiamondRecipe();
        Recipe.compObsidianRecipe();
        Recipe.compCompObsidianRecipe();
        Recipe.anvilRecipe();
        Recipe.comp64IronRecipe();
        Recipe.comp256IronRecipe();
        Recipe.compIronRecipe();
    }

    public GUIDataType getGuiDataType() {
        return guiDataType;
    }

    public DataManager getManager() {
        return manager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        /*for (World world : Bukkit.getWorlds()){
            for (Entity entity : world.getEntities()){
                if (entity instanceof ArmorStand){
                    ArmorStand stand = (ArmorStand) entity;
                    if (stand.getPersistentDataContainer().has(new NamespacedKey(this,"limit_break"), PersistentDataType.STRING)){
                        String data = stand.getPersistentDataContainer().get(new NamespacedKey(this,"limit_break"),PersistentDataType.STRING);
                        if (data == null)continue;
                        gui.setGuiData(gui.getGui());
                    }
                }
            }
        }*/
        SaveandLoad.Save();
    }
}
