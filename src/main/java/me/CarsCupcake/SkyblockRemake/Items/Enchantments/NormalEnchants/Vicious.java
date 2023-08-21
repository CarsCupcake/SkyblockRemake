package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Vicious extends CustomEnchantment implements Listener {
    public Vicious() {
        super(new NamespacedKey(Main.getMain(), "vicious"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Vicious";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 3;
    }


    @EventHandler
    public void onStatsGain(GetStatFromItemEvent event){
        if(event.getStat() == Stats.Ferocity){
            if(ItemHandler.hasEnchantment(SkyblockEnchants.VICIOUS, event.getItem()))
                event.setValue(event.getValue() + event.getItem().getItemMeta().getEnchants().get(SkyblockEnchants.VICIOUS));
        }
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore(List.of("§7Grants §c+%am% ⫽Ferocity")).addPlaceholder("%am%", (player, itemStack) -> String.valueOf(ItemHandler.getEnchantmentLevel(this, itemStack)));
    }
    @Override
    public ItemType[] getAllowedTypes() {
        return Tools.combine(ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]), ItemType.Type.Bow.getTypeList().toArray(new ItemType[0]));
    }
}
