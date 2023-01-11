package me.CarsCupcake.SkyblockRemake.NPC;

import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Main;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class EntityNPC  extends CraftLivingEntity {
    private static final CustomConfig file = new CustomConfig("EntityNpc", true);
    private static final HashMap<LivingEntity,EntityNPC> npcs = new HashMap<>();
    private final LivingEntity entity;
    public static boolean isKillable = false;

    public EntityNPC(String name, Location location, EntityLiving entity,Class<? extends LivingEntity> e,LivingEntity base, boolean isAlrSaved) {

        super((CraftServer) Main.getMain().getServer(), entity);
        this.entity = base;

        npcs.put(this.entity, this);
        if(!isAlrSaved)
        {
            file.reload();
            int var = 1;
            if (file.get().getConfigurationSection("data") != null && file.get().contains("data"))
                var = file.get().getConfigurationSection("data").getKeys(false).size() + 1;
            file.get().set("data." + var + ".x", location.getX());
            file.get().set("data." + var + ".y", location.getY());
            file.get().set("data." + var + ".z", location.getZ());
            file.get().set("data." + var + ".p",(int) location.getPitch());
            file.get().set("data." + var + ".ya", (int)location.getYaw());
            file.get().set("data." + var + ".world", location.getWorld().getName());
            file.get().set("data." + var + ".name", name);
            file.get().set("data." + var + ".entity", e.getName());
            file.save();
            file.reload();
        }



    }
    public EntityNPC(String name, Location location, EntityLiving entity,Class<? extends LivingEntity> e, LivingEntity base){
        this(name, location, entity, e,base,false);
    }

    @NotNull
    @Override
    public EntityType getType() {
        return entity.getType();
    }
    public LivingEntity getEntity(){
        return entity;
    }
    public static EntityNPC makeNPC(String name, Class<? extends LivingEntity> coreEntity, Location location){
        LivingEntity entity = location.getWorld().spawn(location, coreEntity, r->{
            r.setAI(false);
            r.setGravity(false);
            r.setRemoveWhenFarAway(false);
            r.setCustomName(name);
            r.setCustomNameVisible(true);
            r.addScoreboardTag("npc");
            r.setSilent(true);
        });
        return new EntityNPC(name, location, ((CraftLivingEntity) entity).getHandle(), coreEntity, entity);

    }
    public static void loadNPC(){
        file.reload();
        if(file.get().getConfigurationSection("data") != null)
        file.get().getConfigurationSection("data").getKeys(false).forEach(var -> {
        Location loc = new Location(Bukkit.getWorld(file.get().getString("data." + var + ".world")), file.get().getDouble("data." + var + ".x"),file.get().getDouble("data." + var + ".y")
                ,file.get().getDouble("data." + var + ".z") ,(float)file.get().getInt("data." + var + ".ya") ,(float)file.get().getInt("data." + var + ".p"));
            try {
                LivingEntity entity = loc.getWorld().spawn(loc, (Class<? extends LivingEntity>)
                        Class.forName(file.get().getString("data." + var + ".entity")), r->{
                    r.setAI(false);
                    r.setGravity(false);
                    r.setRemoveWhenFarAway(false);
                    r.setCustomName(file.get().getString("data." + var + ".name"));
                    r.setCustomNameVisible(true);
                    r.addScoreboardTag("npc");
                    r.setSilent(true);
                });
                new EntityNPC(var, loc, ((CraftLivingEntity) entity).getHandle(), (Class<? extends LivingEntity>) Class.forName(file.get().getString("data." + var + ".entity")), entity,true);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        });








    }
    public static void shutdown(){
        for (EntityNPC npc : npcs.values())
            npc.remove();
    }
    public static HashMap<LivingEntity, EntityNPC> getNPCs(){
        return npcs;
    }
}
