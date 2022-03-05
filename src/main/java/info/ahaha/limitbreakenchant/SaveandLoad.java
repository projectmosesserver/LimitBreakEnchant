package info.ahaha.limitbreakenchant;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SaveandLoad {

    public static void Save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(LimitBreakEnchant.plugin.getDataFolder() + "/anvil.data"));
            out.writeObject(AnvilLoc.locs);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(LimitBreakEnchant.plugin.getDataFolder() + "/enchant.data"));
            out.writeObject(EnchantLoc.locs);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(LimitBreakEnchant.plugin.getDataFolder() + "/anvildata.data"));
            out.writeObject(GUIs.anvilData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(LimitBreakEnchant.plugin.getDataFolder() + "/enchantdata.data"));
            out.writeObject(GUIs.enchData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() {
        File build = new File(LimitBreakEnchant.plugin.getDataFolder(), "anvil.data");
        if (!build.exists()) {
            try {
                build.createNewFile();
            } catch (IOException ev) {
                ev.printStackTrace();
            }
        }
        try {
            ObjectInputStream inputStream;
            inputStream = new ObjectInputStream(new FileInputStream(build));
            AnvilLoc.locs = (List<AnvilLoc>) inputStream.readObject();

        } catch (IOException ev) {
            ev.printStackTrace();
        } catch (ClassNotFoundException ev) {
            ev.printStackTrace();
        }

        File furnace = new File(LimitBreakEnchant.plugin.getDataFolder(), "enchant.data");
        if (!furnace.exists()) {
            try {
                furnace.createNewFile();
            } catch (IOException ev) {
                ev.printStackTrace();
            }
        }
        try {
            ObjectInputStream inputStream;
            inputStream = new ObjectInputStream(new FileInputStream(furnace));
            EnchantLoc.locs = (List<EnchantLoc>) inputStream.readObject();

        } catch (IOException ev) {
            ev.printStackTrace();
        } catch (ClassNotFoundException ev) {
            ev.printStackTrace();
        }

        File anvil = new File(LimitBreakEnchant.plugin.getDataFolder(), "anvildata.data");
        if (!anvil.exists()) {
            try {
                anvil.createNewFile();
            } catch (IOException ev) {
                ev.printStackTrace();
            }
        }
        try {
            ObjectInputStream inputStream;
            inputStream = new ObjectInputStream(new FileInputStream(anvil));
            GUIs.anvilData = (Map<String, String>) inputStream.readObject();
            for (AnvilLoc anvilloc : AnvilLoc.locs){
                Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.DARK_PURPLE + "LimitBreakAnvil");
                inv.setContents(Objects.requireNonNull(ItemSerializable.itemStackArrayFromBase64(GUIs.anvilData.get(anvilloc.getLoc()))));
                GUIs.anvil.put(anvilloc.getLoc(),inv);
            }

        } catch (IOException ev) {
            ev.printStackTrace();
        } catch (ClassNotFoundException ev) {
            ev.printStackTrace();
        }

        File ench = new File(LimitBreakEnchant.plugin.getDataFolder(), "enchantdata.data");
        if (!ench.exists()) {
            try {
                ench.createNewFile();
            } catch (IOException ev) {
                ev.printStackTrace();
            }
        }
        try {
            ObjectInputStream inputStream;
            inputStream = new ObjectInputStream(new FileInputStream(ench));
            GUIs.enchData = (Map<String, String>) inputStream.readObject();
            for (EnchantLoc enchLoc : EnchantLoc.locs){
                Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.DARK_PURPLE + "LimitBreakEnchantTable");
                inv.setContents(Objects.requireNonNull(ItemSerializable.itemStackArrayFromBase64(GUIs.enchData.get(enchLoc.getLoc()))));
                GUIs.ench.put(enchLoc.getLoc(),inv);
            }
        } catch (IOException ev) {
            ev.printStackTrace();
        } catch (ClassNotFoundException ev) {
            ev.printStackTrace();
        }
    }
}
