package me.CarsCupcake.SkyblockRemake.End.Dragon;

import me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi.SkyblockDragon;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import net.minecraft.world.entity.EntityTypes;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.Map;

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
            /*case OLD -> {
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
            }*/
            case SUPERIOR -> {
                SuperiorDragon dragon = new SuperiorDragon();
                dragon.spawn(loc);
                return dragon;
            }
            /*case UNSTABLE -> {
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
            }*/
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


}
