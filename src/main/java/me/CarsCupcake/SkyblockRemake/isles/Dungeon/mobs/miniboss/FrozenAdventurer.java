package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.LostAdventurers.LostAdventurer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.HashMap;
@MiniBoss
public class FrozenAdventurer extends LostAdventurer {
    private static final String texture = "ewogICJ0aW1lc3RhbXAiIDogMTYwMTUyOTc4ODY1OSwKICAicHJvZmlsZUlkIiA6ICIyMWUzNjdkNzI1Y2Y0ZTNiYjI2OTJjNGEzMDBhNGRlYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJHZXlzZXJNQyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zZGVlNTAxODFkYmMzMzZkMGQ5MDM1ZjYyMjQ0OGM0M2RhYTdmZGI1ZDdjYWFhOTFmNjdiM2JjNjQ4NmMzMjEwIgogICAgfQogIH0KfQ==";
    private static final String signature = "i0cF74RyF/YAl8m2VathjBpRKlY93rrqnBx/fZPwzaXhL+KLGGhGEJc0SPSzDqpQDXXQKeMO2qKQwDsIbXrNQT0TUMzjFObzPznx5LVNrjZIs9xYpOyh6olmPOxKb8S/5DKKIbtm1ZfejK4KFLuz1OP4idcgPf+xzhoXsPfX8KdbWXoTu192zQ/L6lo0N2dAMzjz6ymELXkpl05gruONtSF01OjcyvVL80lWR5YyecoycxqFPpVXOhxAYYa2PoircLwMg2Vtmkck0/u0gniDt3EEkZkQ44CjT/9bxjf4LEkeHMdnXkt/KYaTk934/eSgr8dL6zlU7v/IyX6Jn3vceQQz9XF04Q+COBsxfjvExc7/Awti+8OJASCvlWoBHL2jqQbXDKXk/OJgjh6F8rPECljiqrdfmEC+W3lM/mc8WBX1KheHtiZiMlyYPOZQ4hCdCJoiHi+jxIhV56UVvu911lhsyRB4ovyb6JWqty/9ztN8spEA4Mxk0xIcK7aVJ3nb8XrfCsMRC17oAwd6W79qSGKxmLJxTg25w+HJ1Sj4JRrLcD4Ix505ptLAdyGdd17xr5oXZ7G4cT3vm19sR1SqPYuyjHV9S1eJBtJAo7kFhFcoAKGBp8MdHXZ4MTZPZZSXdOwPGcYavANN7NA3EPecvfqBUCh9e3IhXJOP70Huv5A=";
    private final boolean yellow;
    public FrozenAdventurer(int floor, boolean master) {
        super(floor, master);
        r.name = "§7Frozen Adventurer's Ice Spray damaged you for §c%d% §7damage!";
        r.particle = Particle.SNOWFLAKE;
        this.yellow = false;
    }
    public FrozenAdventurer(int floor, boolean master, boolean yellow) {
        super(floor, master);
        r.name = "§7Frozen Adventurer's Ice Spray damaged you for §c%d% §7damage!";
        r.particle = Particle.SNOWFLAKE;
        this.yellow = yellow;
    }

    @Override
    public void spawn(Location loc) {
        craftEntity = new SBEntity(loc);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(craftEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        entity = (LivingEntity) craftEntity.getBukkitEntity();
        entity.setAI(true);
        entity.getEquipment().setHelmet(Items.SkyblockItems.get("FROZEN_BLAZE_HELMET").getRawItemStack());
        entity.getEquipment().setChestplate(Items.SkyblockItems.get("FROZEN_BLAZE_CHESTPLATE").getRawItemStack());
        entity.getEquipment().setLeggings(Items.SkyblockItems.get("FROZEN_BLAZE_LEGGINGS").getRawItemStack());
        entity.getEquipment().setBoots(Items.SkyblockItems.get("FROZEN_BLAZE_BOOTS").getRawItemStack());
        new PlayerDisguise(entity, texture, signature);
        SkyblockEntity.livingEntity.addEntity(entity, this);
        updateNameTag();
        r.runTaskTimer(Main.getMain(), 5, 5);
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    protected int healthFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 4 -> (master) ? ((yellow) ? 60_000_000 : 42_000_000) : ((yellow) ? 1_100_000 : 475_000);
            case 5 -> (master) ? ((yellow) ? 66_000_000 : 60_000_000) : ((yellow) ? 1_600_000 : 1_100_000);
            case 6 -> (master) ? ((yellow) ? 134_000_000 : 66_000_000) : ((yellow) ? 2_600_000 : 1_600_000);
            case 7 -> (master) ? ((yellow) ? 160_000_000 : 134_000_000) : ((yellow) ? 6_600_000 : 2_600_000);
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected int damageFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 4 -> (master) ? ((yellow) ? 165_000 : 120_000) : ((yellow) ? 4_440 : 2_840);
            case 5 -> (master) ? ((yellow) ? 180_000 : 165_000) : ((yellow) ? 6_240 : 4_440);
            case 6 -> (master) ? ((yellow) ? 280_000 : 180_000) : ((yellow) ? 8_400 : 6_240);
            case 7 -> (master) ? ((yellow) ? 340_000 : 280_000) : ((yellow) ? 17_600 : 8_400);
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected double defenseFromFloor(int floor, boolean master) {
        return 100;
    }
}
