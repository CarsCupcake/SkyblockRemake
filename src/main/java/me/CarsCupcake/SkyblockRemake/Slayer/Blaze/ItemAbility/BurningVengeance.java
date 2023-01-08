package me.CarsCupcake.SkyblockRemake.Slayer.Blaze.ItemAbility;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BurningVengeance {
    private static final HashMap<LivingEntity, Set<BurningVengeance>> abilitys = new HashMap<>();
    @Getter
    private final SkyblockPlayer player;
    public BurningVengeance(SkyblockPlayer player, LivingEntity entity){
        Set<BurningVengeance> bV = (abilitys.containsKey(entity)) ? abilitys.get(entity) : new HashSet<>();
        bV.add(this);
        abilitys.put(entity, bV);
        this.player = player;
    }
    public static boolean isBurning(LivingEntity entity, SkyblockPlayer player){
        if(!abilitys.containsKey(entity))
            return false;
        Set<BurningVengeance> bV =  abilitys.get(entity);
        for (BurningVengeance bv : bV)
            if(bv.getPlayer() == player)
                return true;
        return false;
    }
}
