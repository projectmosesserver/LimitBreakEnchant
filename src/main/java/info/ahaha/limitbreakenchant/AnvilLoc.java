package info.ahaha.limitbreakenchant;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AnvilLoc implements Serializable {

    String loc;
    public static List<AnvilLoc>locs = new ArrayList<>();

    public AnvilLoc(Block block){
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        String worldname = block.getWorld().getName();

        this.loc = worldname + ";" + x + ";" + y + ";" + z;
    }

    public String getLoc() {
        return loc;
    }

    public Location getLocation() {
        String[] locs = this.loc.split(";");
        String worldname = locs[0];
        int x = Integer.parseInt(locs[1]);
        int y = Integer.parseInt(locs[2]);
        int z = Integer.parseInt(locs[3]);

        return new Location(Bukkit.getWorld(worldname), x, y, z);
    }

    public Boolean equalBlock(Block block){
        return block.getLocation().getBlockX() == getLocation().getBlockX() && block.getLocation().getBlockY() == getLocation().getBlockY() && block.getLocation().getBlockZ() == getLocation().getBlockZ();
    }

    public void remove(){
        int i = 0;
        for (AnvilLoc loc : AnvilLoc.locs){
            if (AnvilLoc.locs.get(i).equals(loc)){
                AnvilLoc.locs.remove(i);
                GUIs.anvilData.remove(loc.getLoc());
                return;
            }
            i++;
        }
    }
}
