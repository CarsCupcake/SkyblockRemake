package me.CarsCupcake.SkyblockRemake.isles.rift;

import kotlin.Triple;
import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.configs.ConfigFile;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.Region;
import me.CarsCupcake.SkyblockRemake.utils.ReflectionUtils;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.log.DebugLogger;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.network.protocol.game.PacketPlayOutUpdateAttributes;
import net.minecraft.network.protocol.game.PacketPlayOutUpdateHealth;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeBase;
import net.minecraft.world.entity.ai.attributes.AttributeModifiable;
import net.minecraft.world.entity.ai.attributes.AttributeRanged;
import net.royawesome.jlibnoise.module.combiner.Max;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@SuppressWarnings({"unused", "deprecation"})
public class RiftPlayer extends SkyblockPlayer {
    @Getter
    @Setter
    private long motes;
    private final long maxRiftTime;
    @Getter
    private long riftTime;
    @Getter
    private final RiftTicker timer;
    @Getter
    private final Set<Region> paiedRegions = new HashSet<>();

    public RiftPlayer(CraftServer server, EntityPlayer entity) {
        super(server, entity, true);
        maxRiftTime = (long) Main.getPlayerStat(this, Stats.RiftTime);
        riftTime = maxRiftTime;
        timer = new RiftTicker();
        Main.getDebug().debug(riftTime + " rift time left",false);
        motes = new ConfigFile(this, "riftStats", true).get().getLong("motes", 0);
        setHealth(getMaxHealth(), HealthChangeReason.Force);
    }

    @Override
    public void saveInventory() {
        super.saveInventory();
        ConfigFile c = new ConfigFile(this, "riftStats", true);
        c.get().set("motes", motes);
        c.save();
    }

    public void setRiftTime(long i) {
        riftTime = i;
    }

    public void addRiftTime(long i) {
        setRiftTime(i + riftTime);
    }

    public void subtractRiftTime(long i) {
        if(timer.isAdminPause()) return;
        if (riftTime < 10) return;
        if (riftTime - i < 10) setRiftTime(10);
        else setRiftTime(riftTime - i);
    }
    public void addMotes(long l){
        motes += l;
    }
    public void removeMotes(long l){
        motes -= l;
    }

    public void riftTimeRunout() {

    }

    public static RiftPlayer getRiftPlayer(Player player) {
        if (ServerType.getActiveType() != ServerType.Rift)
            throw new RuntimeException("Not allowed to get the riftplayer without beeing in the rift!");
        return (RiftPlayer) SkyblockPlayer.getSkyblockPlayer(player);
    }
    public void updateBar(){
        this.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(((timer.isRunning()) ? "§a" : "§7") + toTimeString(Tools.breakDownTime(riftTime)) + Stats.RiftTime.getSymbol() + " Left   " + ((showDefenceString) ? defenseString : "") + "   §b"
        + currmana + "/" + Main.getPlayerStat(this, Stats.RiftInteligence)));
        double maxHealth = Main.getPlayerStat(this, Stats.Hearts) * 2;
        if (getMaxHealth() != maxHealth)
            setMaxHealth(maxHealth);
    }
    private String toTimeString(Triple<Long, Long, Long> time){
        StringBuilder builder = new StringBuilder();
        if(time.getThird() != 0)
            builder.append(time.getThird()).append("h").append(" ");
        if(time.getSecond() != 0)
            builder.append(time.getSecond()).append("m").append(" ");
        builder.append((time.getFirst() < 10) ? "0" : "").append(time.getFirst()).append("s");
        return builder.toString();
    }
    private double health;

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void setHealth(int value, HealthChangeReason reason) {
        setHealth((double) value, reason);
    }

    @Override
    public void setHealth(float value, HealthChangeReason reason) {
        setHealth((double) value, reason);
    }

    @Override
    public void setHealth(double value, HealthChangeReason reason) {
        if (getGameMode() != GameMode.SURVIVAL) return;
        health = Math.max(0, Math.min(getMaxHealth(), value));
        getHandle().b.sendPacket(new PacketPlayOutUpdateHealth((float) health, 20, 5));
        if (health == 0) getPlayer().setHealth(0);
    }

    @Override
    public void setHealth(int value) {
        setHealth(value, HealthChangeReason.Force);
    }

    @Override
    public void setHealth(float value) {
        setHealth(value, HealthChangeReason.Force);
    }

    @Override
    public void setHealth(double value) {
        setHealth(value, HealthChangeReason.Force);
    }

    @Getter
    public class RiftTicker extends BukkitRunnable {
        @Setter
        private boolean adminPause = false;

        @Override
        public void run() {
            riftTime--;
            getPlayer().setLevel((int) riftTime);
            getPlayer().setExp(((float) riftTime) / ((float) maxRiftTime));
            if (riftTime == 0) {
                riftTimeRunout();
                cancel();
            }
        }

        public void pause() {
            try {
                cancel();
                ReflectionUtils.setField("task", this, null);
            } catch (Exception ignored) {
            }
        }

        public void start() {
            if (!adminPause) this.runTaskTimer(Main.getMain(), 20, 20);
        }
        public boolean isRunning(){
            return ReflectionUtils.getField(ReflectionUtils.findField(this.getClass(), "task"), this) != null;
        }
        public boolean canRun(){
            //TODO: add zones stuff
            return true;
        }
    }
}
