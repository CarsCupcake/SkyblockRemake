package me.CarsCupcake.SkyblockRemake.Slayer.Zombie;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;

public abstract class ZombieSlayer extends Slayer {
    public ZombieSlayer(SkyblockPlayer player) {
        super(player);
    }
    protected void equip(Zombie entity){
        entity.getEquipment().setItemInMainHand(new ItemBuilder(Material.DIAMOND_HOE).setGlint(true).build());
        entity.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/d8bee23b5c726ae8e3d021e8b4f7525619ab102a4e04be983b61414349aaac67"));
        entity.getEquipment().setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE).setGlint(true).build());
        entity.getEquipment().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).setGlint(true).build());
        entity.getEquipment().setLeggings(new ItemBuilder(Material.DIAMOND_BOOTS).build());
    }
}
