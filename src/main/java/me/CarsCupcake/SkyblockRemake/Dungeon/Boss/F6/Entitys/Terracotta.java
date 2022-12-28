package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Phase1;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.HashMap;
import java.util.Random;

public class Terracotta extends SkyblockEntity {
    private int health = 32000000;
    private LivingEntity entity;
    private boolean imune = false;
    private JumpAI ai;
    @Override
    public int getMaxHealth() {
        return 32000000;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }



    @Override
    public int getDamage() {
        return 13200;
    }

    @Override
    public void spawn(Location loc) {
        TerracottaAI ai = new TerracottaAI(loc);
        ((CraftWorld) loc.getWorld()).addEntityToWorld(ai, CreatureSpawnEvent.SpawnReason.CUSTOM);
        ai.setGoalTarget(((CraftPlayer)Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())]).getHandle(), EntityTargetEvent.TargetReason.TARGET_ATTACKED_NEARBY_ENTITY, false);
        entity = (LivingEntity) ai.getBukkitEntity();
        DiguestMobsManager.createEntity(entity, loc, getName(),"ewogICJ0aW1lc3RhbXAiIDogMTY2NzY1MTg1MDQ2NywKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzQ3VwY2FrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82NTcwYTJkMmEzNjJhZGVlYjA1ZTZmMjVlZGNkNTRjYWMzMzVmMjJmMGU0NjY5ZTljMWViZDlhMDI1NzNiY2FiIgogICAgfQogIH0KfQ=="
        , "vn5xLDSLe3pzLi8HRIRBDECup+e4JVOBr84TVeO/Eiyq7EwZclnZusnxtqWJJKPpuZKtkyXudwZlw1AaZgeEU74zBK49Ab8ua8Mnl7NDi7LHEObXgAXLAcsVlV/A2leUhJuVARl0k4/CyO6cTy7DPWvH6W5Nx4b8TUKe1VuuvMN9j4ELGH0L4PU8hdbnLspFgfdFPds8lfAyjnld7ZwbRFNo6TKnm/oYAAt6XIPS1f/79OlQ9IZaQt7LJVTOoJwKTBtQ46zXjx8Hd0iTrHhn1oBrTZiNEUfitC0oCWGZrNibCTsUb8umtvje25DBSNdXMRNLfsRysJtQXLqXsa7Y10xDX05WdTjkzR2e7YxFxX8acvP7KcMS5z9wPIKutJqjv51yj71+yWsvhoy9Zqnd85mXTrKiE4eWEVgTRd/M3Oeqrtm3ae7875yeseW7vNmVfOqnq0BvDLJaTzpWNlKExRigIIl/THgabJij/Xcu/9EWwvI79wI7lBJ1L0rKZGgrvkkpDzVpw9JpHJAYg0TFhLyyVOMGWo3vc/DX5EwUYKfMCw/b9Z0ns6qD52hIUukG2IQghE2/pYJaq9OPJoOXJn7gTkQ03z5C7+1iIGBG7ZGghhwMh3JY6ASLzQHiLB7DeJAH75PHMdqtcVLx7RmstbDvY7mzEBF/ymUm0M13mTA=");
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.6);
        entity.addScoreboardTag("combatxp:300");
        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);
        this.ai = new JumpAI(entity);
    }


    @Override
    public String getName() {
        return "§6§lTerracotta";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName(getName() + " §a" + Tools.toShortNumber(getHealth()) + "§c❤");
    }

    @Override
    public void kill() {
        Phase1.getPhase().terracottaKill(this);
        try {
            ai.cancel();
        }catch (Exception ignored){}
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        if(imune)
            return;
        health -= damage;
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }
    public void reset(){

    }
    public void setImune(boolean b){
        imune = b;
    }
}
