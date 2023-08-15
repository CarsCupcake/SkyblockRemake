package me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Secrets;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Dungeon;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Room;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class BatSecret extends Secret {
    private static final HashMap<Location, BatSecret> unspawned = new HashMap<>();
    private final Location spawn;
    private boolean isDone = false;
    public BatSecret(@NotNull Room room, Location spawnLocation) {
        super(room);
        spawn = spawnLocation;
        unspawned.put(spawnLocation, this);
    }
    public void spawn(){
        unspawned.remove(spawn);
        new Bat(this).spawn(spawn);
    }
    private void finish(){
        isDone = true;
        Dungeon.INSTANCE.doSecret(this);
    }

    @Override
    public boolean isCompleted() {
        return isDone;
    }
    public static class Bat extends SkyblockEntity{
        private final BatSecret secret;
        public Bat(BatSecret secret){
            this.secret = secret;
        }
        private LivingEntity entity;

        @Override
        public int getMaxHealth() {
            return 120;
        }

        @Override
        public LivingEntity getEntity() {
            return entity;
        }

        @Override
        public int getDamage() {
            return 0;
        }

        @Override
        public void spawn(Location loc) {
            entity = loc.getWorld().spawn(loc, org.bukkit.entity.Bat.class, bat -> entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999, 250)));
        }

        @Override
        public String getName() {
            return "Bat";
        }

        @Override
        public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
            return null;
        }

        @Override
        public void updateNameTag() {

        }

        @Override
        public void kill() {
            secret.finish();
        }

        @Override
        public void damage(double damage, SkyblockPlayer player) {

        }

        @Override
        public boolean hasNoKB() {
            return false;
        }

        @Override
        public int getTrueDamage() {
            return 0;
        }
    }

    public static class EventListener implements Listener {
        @EventHandler
        public void onWalk(PlayerMoveEvent event){
            if(unspawned.isEmpty()) return;
            for (Location l : unspawned.keySet())
                if(l.distance(event.getTo()) < 10)
                    unspawned.get(l).spawn();
        }
    }
}
