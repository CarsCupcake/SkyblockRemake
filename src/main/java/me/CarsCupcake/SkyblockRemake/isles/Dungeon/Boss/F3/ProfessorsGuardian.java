package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F3;

import me.CarsCupcake.SkyblockRemake.Skyblock.EntitySkillXp;
import me.CarsCupcake.SkyblockRemake.Skyblock.FinalDamageDesider;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Location;
@EntitySkillXp(150)
public abstract class ProfessorsGuardian extends SkyblockEntity implements FinalDamageDesider {
    protected final Floor3 floor;
    private EntityRunnable down;
    protected boolean isDown = false;
    public ProfessorsGuardian(Location location, Floor3 floor){
        this.floor = floor;
        spawn(location);

    }
    private void up(){
        floor.guardianUp();
    }
    private void setDeath(){
        isDown = true;
        floor.guardianDown();
        down = new EntityRunnable() {
            @Override
            public void run() {
                up();
            }
        };
        down.runTaskLater(this, 200);
    }
    public abstract void rejoin();
    public void toPhase2(){
        down.cancel();
    }

    @Override
    public double getFinalDamage(SkyblockPlayer player, double damage) {
        if(floor.getPhase() == 3) return damage;
        if(isDown) return 0;
        if(damage < health) return damage;
        setDeath();
        return health - 1;
    }
}
