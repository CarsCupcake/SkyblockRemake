package me.CarsCupcake.SkyblockRemake.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Configs.PetMenus;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Pets.Pet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChimeraListener implements Listener {
    @EventHandler
    public void getStat(GetStatFromItemEvent event){
        if(event.getPlayer() == null)
            return;
        if(event.getItem().getItemMeta() == null)
            return;
        if(!event.getItem().getItemMeta().getEnchants().containsKey(SkyblockEnchants.CHIMERA))
            return;
        if (PetMenus.get().getInt(event.getPlayer().getUniqueId() + ".equiped") != 0) {
            Pet pet = Pet.pets.get(PetMenus.get().getString(
                    event.getPlayer().getUniqueId() + "." + PetMenus.get().getInt(event.getPlayer().getUniqueId() + ".equiped") + ".id"));

            event.setValue( event.getValue() + getValue(pet.getStat(event.getStat(), PetMenus.get().getInt(
                    event.getPlayer().getUniqueId() + "." + PetMenus.get().getInt(event.getPlayer().getUniqueId() + ".equiped") + ".level")),
                    event.getItem().getItemMeta().getEnchants().get(SkyblockEnchants.CHIMERA)));

        }
    }
    private double getValue(double petStat, int chimera){
        double pers = 0.2*chimera;
        return petStat*pers;
    }
}
