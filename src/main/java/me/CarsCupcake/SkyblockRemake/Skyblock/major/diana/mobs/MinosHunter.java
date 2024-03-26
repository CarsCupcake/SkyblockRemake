package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.MythologicalPerk;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

public class MinosHunter extends SkyblockEntity {
    private int maxHealth = 1;
    private final int damage;
    private LivingEntity entity;
    private final MythologicalPerk perk;
    public MinosHunter(ItemRarity rarity, MythologicalPerk perk) {
        switch (rarity) {
            case RARE, EPIC -> {
                maxHealth = 100_000;
                damage = 850;
            }
            case LEGENDARY -> {
                maxHealth = 500_000;
                damage = 2_700;
            }
            default -> {
                maxHealth = 2_000;
                damage = 300;
            }
        }
        health = maxHealth;
        this.perk = perk;
    }
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class, zombie -> {
            zombie.getEquipment().setHelmet(new ItemBuilder(Material.PLAYER_HEAD).headTextureAsValue().setHead("ewogICJ0aW1lc3RhbXAiIDogMTU5ODQzOTU1MzMzMCwKICAicHJvZmlsZUlkIiA6ICI0MWQzYWJjMmQ3NDk0MDBjOTA5MGQ1NDM0ZDAzODMxYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJNZWdha2xvb24iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGI3MDUwNjg3NDBjZDZiNGMyYjAyNjNkYWVhYjYxMDNkNjUyYjllZmJjYWFkOGFjYmFiZWU2OGU2ZmQxYzRiNCIKICAgIH0KICB9Cn0=").build());
            zombie.getEquipment().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(Color.RED).setGlint(true).build());
            zombie.getEquipment().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(Color.RED).setGlint(true).build());
            zombie.getEquipment().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(Color.BLACK).setGlint(true).build());
            zombie.getEquipment().setItemInMainHand(new ItemBuilder(Material.WOODEN_SWORD).build());
            zombie.setAdult();
            zombie.setCustomNameVisible(true);
        });
        SkyblockEntity.livingEntity.addEntity(entity, this);
    }

    @Override
    protected NametagType getNametagType() {
        return NametagType.SmallNumber;
    }

    @Override
    public String getName() {
        return "§aMinos Hunter";
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void kill() {
        super.kill();
        if (perk != null) perk.kill(this);
    }
}
