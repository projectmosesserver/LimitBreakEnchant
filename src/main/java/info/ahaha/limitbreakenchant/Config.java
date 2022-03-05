package info.ahaha.limitbreakenchant;

public class Config {

    public static int getMinLevel(){
        return LimitBreakEnchant.plugin.getManager().getConfig().getInt("Enchant.LevelMin");
    }
    public static int getMaxLevel(){
        return LimitBreakEnchant.plugin.getManager().getConfig().getInt("Enchant.LevelMax");
    }
    public static int getLapisCost(){
        return LimitBreakEnchant.plugin.getManager().getConfig().getInt("Enchant.LapisCost");
    }
    public static int getCostLevel(){
        return LimitBreakEnchant.plugin.getManager().getConfig().getInt("Enchant.LevelCost");
    }

}
