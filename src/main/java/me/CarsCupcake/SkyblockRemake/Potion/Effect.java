package me.CarsCupcake.SkyblockRemake.Potion;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public abstract class Effect {
    @Getter
    private long duration;
    @Getter
    private final SkyblockPlayer player;
    @Getter
    private final int level;
    public Effect(SkyblockPlayer player, Integer level, Long duration){
        this.player = player;
        this.level = level;
        this.duration = duration;
        if(player == null)
            return;
        player.getActiveEffects().add(this);
        start();
    }
    public static void load(SkyblockPlayer player){
        CustomConfig c = new CustomConfig(player, "potion");
        if(c.get().isConfigurationSection("")){
            Set<String> prepairedStatement = new HashSet<>();
            for (String s : c.get().getConfigurationSection("").getKeys(false)){
                int id;
                String str = s;
                if(s.contains("-")) {
                    id = 0;
                    s = s.split("-")[0];
                }
                else
                    id = 1;
                if(!PotionEffect.getPotionEffects().containsKey(s)){
                    player.sendMessage("Failed to initiase potion effect \"" + s + "\"");
                    continue;
                }
                if(prepairedStatement.contains(s)){
                    if(id == 0)
                        PotionEffect.getPotionEffects().get(s).applyEffect(player, c.get().getInt(str), c.get().getLong(s));
                    else
                        PotionEffect.getPotionEffects().get(s).applyEffect(player, c.get().getInt(s + "-level"), c.get().getLong(s));
                }else
                    prepairedStatement.add(s);

            }
        }
    }
    public static void init(){

        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    for(Effect e : SkyblockPlayer.getSkyblockPlayer(p).getActiveEffects())
                        e.tick();
                }
            }
        }.runTaskTimer(Main.getMain(), 1, 1);

    }
    /**
    A method which runs every game tick
     **/
    public void tick(){
        duration--;
        if(duration <= 0){
            removeEffect();
        }

    }
    public abstract void start();
    public abstract void stop();

    /**
     *
     * @return the display name (which is for example shown in the tablist) of the effect
     */
    public abstract String name();
    public abstract String id();
    public void clear(){
        duration = 0;
        removeEffect();
    }
    public void removeEffect(){
        stop();
        CustomConfig c = new CustomConfig(player, "potion");
        player.getActiveEffects().remove(this);
        if(duration > 0){
            c.get().set(id(), duration);
            c.get().set(id() + "-level",  level);
        }else {
            c.get().set(id(), null);
            c.get().set(id() + "-level",  null);
        }
    }

}
