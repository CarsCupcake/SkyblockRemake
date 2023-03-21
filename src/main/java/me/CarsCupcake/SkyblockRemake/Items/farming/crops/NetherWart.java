package me.CarsCupcake.SkyblockRemake.Items.farming.crops;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.PlayerFarmEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import org.bukkit.Material;

public class NetherWart extends Crop{
    @Override
    public Material getBlockType() {
        return Material.NETHER_WART;
    }

    @Override
    public String getItemId() {
        return Material.NETHER_WART.toString();
    }

    @Override
    protected Bundle<Integer, Integer> getDropAmounts() {
        return new Bundle<>(2,4);
    }
    public void addSkillXp(PlayerFarmEvent event){
        event.getPlayer().addSkillXp(0.2, Skills.Alchemy);
    }
}
