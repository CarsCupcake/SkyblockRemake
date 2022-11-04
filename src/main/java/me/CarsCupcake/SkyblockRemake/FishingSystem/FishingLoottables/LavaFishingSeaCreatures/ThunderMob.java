package me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures;

import net.minecraft.sounds.SoundEffect;
import net.minecraft.sounds.SoundEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.AttributeProvider;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.monster.EntityGuardian;
import net.minecraft.world.entity.monster.EntityGuardianElder;
import net.minecraft.world.level.World;
import org.bukkit.Location;

public class ThunderMob extends EntityGuardian {
    public static final float b;
    static {
        b = EntityTypes.t.k() / EntityTypes.K.k();
    }
    public ThunderMob(World world, Location loc) {
        super(EntityTypes.t, world);
        this.setPersistent();
        if (this.d != null) {
            this.d.setTimeBetweenMovement(400);
        }
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setHealth(80);
        this.setCustomNameVisible(true);


    }

    public static AttributeProvider.Builder n() {
        return EntityGuardian.fw().a(GenericAttributes.d, 0.30000001192092896).a(GenericAttributes.f, 8.0).a(GenericAttributes.a, 80.0);
    }

    public int p() {
        return 60;
    }

    protected SoundEffect getSoundAmbient() {
        return this.aO() ? SoundEffects.eW : SoundEffects.eX;
    }

    protected SoundEffect getSoundHurt(DamageSource damagesource) {
        return this.aO() ? SoundEffects.fc : SoundEffects.fd;
    }

    protected SoundEffect getSoundDeath() {
        return this.aO() ? SoundEffects.eZ : SoundEffects.fa;
    }

    protected SoundEffect getSoundFlop() {
        return SoundEffects.fb;
    }
    @Override
    protected void mobTick(){
        super.mobTick();
    }

}
