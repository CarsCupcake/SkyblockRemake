package me.CarsCupcake.SkyblockRemake.End.Dragon;

import me.CarsCupcake.SkyblockRemake.End.EndItems;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import org.bukkit.Location;

public enum DragonTypes {
    PROTECTOR("protector", "Protector Dragon"),
    OLD("old", "Old Dragon"),
    WISE("wise", "Wise Dragon"),
    UNSTABLE("unstable", "Unstable Dragon"),
    YOUNG("young", "Young Dragon"),
    STRONG("strong", "Strong Dragon"),
    SUPERIOR("superior", "Superior Dragon");

    public String mobName;
    public String prefix;

    private DragonTypes(String prefix, String name) {
        mobName = name;
        this.prefix = prefix;
    }
    public CustomDragon spawnEntity(Location loc) {
        switch (this){
            case OLD -> {
                OldDragon dragon = new OldDragon();
                dragon.spawn(loc);
                return dragon;
            }
            case PROTECTOR -> {
                ProtectorDragon dragon = new ProtectorDragon();
                dragon.spawn(loc);
                return dragon;
            }
            case STRONG -> {
                StrongDragon dragon = new StrongDragon();
                dragon.spawn(loc);
                return dragon;
            }
            case SUPERIOR -> {
                SuperiorDragon dragon = new SuperiorDragon();
                dragon.spawn(loc);
                return dragon;
            }
            case UNSTABLE -> {
                UnstableDragon dragon = new UnstableDragon();
                dragon.spawn(loc);
                return dragon;
            }
            case WISE -> {
                WiseDragon dragon = new WiseDragon();
                dragon.spawn(loc);
                return dragon;
            }
            case YOUNG -> {
                YoungDragon dragon = new YoungDragon();
                dragon.spawn(loc);
                return dragon;
            }
        }
        return null;

    }
    public static DragonTypes dragonByEntity(SkyblockEntity entity){
        for(DragonTypes types : DragonTypes.values()){
            if(entity.getClass().getSimpleName().toLowerCase().startsWith(types.prefix)){
                return types;
            }
        }
        return null;
    }
    public ItemManager getFragment(){
        switch (this){
            case OLD -> {
                return EndItems.Items.OldDragonFragment.getItem();
            }
            case STRONG -> {
                return EndItems.Items.StrongDragonFragment.getItem();
            }
            case PROTECTOR -> {
                return EndItems.Items.ProtectorDragonFragment.getItem();
            }
            case SUPERIOR -> {
                return EndItems.Items.SuperiorDragonFragment.getItem();
            }
            case WISE -> {
                return EndItems.Items.WiseDragonFragment.getItem();
            }
            case UNSTABLE -> {
                return EndItems.Items.UnstableDragonFragment.getItem();
            }
            case YOUNG -> {
                return EndItems.Items.YoungDragonFragment.getItem();
            }
        }

        return null;
    }


}
