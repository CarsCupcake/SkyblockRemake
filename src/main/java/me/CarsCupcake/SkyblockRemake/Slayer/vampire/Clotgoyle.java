package me.CarsCupcake.SkyblockRemake.Slayer.vampire;

import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

public class Clotgoyle extends SkyblockEntity {
    private static final String texture = "ewogICJ0aW1lc3RhbXAiIDogMTU4OTk2MzczNTIzMiwKICAicHJvZmlsZUlkIiA6ICJiMGQ3MzJmZTAwZjc0MDdlOWU3Zjc0NjMwMWNkOThjYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJPUHBscyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mZDRjMTQyYzM4MmE2YmUwMGNhZWVlOWEwMjMwNmYwNjU0NGU2ZTQ2Yjk1M2JjYzhjYWM2NWU1Yzg5ZDQ3OTBhIgogICAgfQogIH0KfQ==";
    private static final String signature = "URexDOx2zHD90oNqpt3HgyJqO9SodIMArb25x6VwVzytAicYnAMc4jzjSI8KJ76vTRpPc7ks6NxgAsAi//0qyAhb7czPaqUkRGMDuNaj40fWsMmr5ukWqBTE2wLISAa4f2XF2w2lSxwAzI3qJ4M20cuQKJPqoUJYr+m/p+Zo6W4Vf0jrGe++/qfIVWH9HL8WUNFqMslx6cbnoeEQKLjgdVGbqEI8cXwCuH8nczmCFPjimJnUA36SJpJr5ysl/QIqk4vo63/VeVQfbGJOinSUkGC2JPf4SxJWCAtOIzzdUprlENwDa10Qig3mY3X5dLPOrXEdf4VhHyWU8roy/S7ZHl2PmYrkTlYiFP0Ldsvh1kHgt74IUfe2oiy3vxWjP+WvKRD9DZcX4KUNXTGcze5/AYqW1UsxmpQ2HAQouu5P5D1eAWghn9tOxcvyPGTo1Pp1Ki3iRPJ3fVLjlC6QuuJlmtX2MShN59m23eOzhwtSTolczh6z/KAx9lZnB0RHiCKKbQ6+5/llGm1PG1i38mPAPhFGTWIpaSddlN0SGbOYJo9jOvX6Uydn+L2dGn/SrQfkA6ESAe+UWDZzMiPLTySCV35NaVSe1/8+TValS58hDeGvTEOJWBZg7liqhLriksCPonosIU485Qez5bDnFe+yWwc3oUrlWxUEIIKa3/k1rnE=";
    private LivingEntity entity;
    private final VampireSlayerT1 vampireSlayerT1;
    public Clotgoyle(VampireSlayerT1 vampireSlayerT1) {
        this.vampireSlayerT1 =  vampireSlayerT1;
    }
    @Override
    public int getMaxHealth() {
        return 60;
    }

    @Override
    public int getRiftTimeDamage() {
        return 5;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class);
        new PlayerDisguise(entity, texture, signature);
        SkyblockEntity.livingEntity.addEntity(entity, this);
    }

    @Override
    public String getName() {
        return "Clotgoyle";
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public void kill() {
        super.kill();
        vampireSlayerT1.killClotgoyle(this);
    }
}
