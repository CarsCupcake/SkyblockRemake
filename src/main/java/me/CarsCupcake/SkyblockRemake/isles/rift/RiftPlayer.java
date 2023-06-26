package me.CarsCupcake.SkyblockRemake.isles.rift;

import kotlin.Triple;
import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.ReflectionUtils;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RiftPlayer extends SkyblockPlayer {
    private final long maxRiftTime;
    @Getter
    private long riftTime;
    @Getter
    private final RiftTicker timer;

    public RiftPlayer(CraftServer server, EntityPlayer entity) {
        super(server, entity, true);
        maxRiftTime = (long) Main.getPlayerStat(this, Stats.RiftTime);
        riftTime = maxRiftTime;
        timer = new RiftTicker();
        Main.getDebug().debug(riftTime + " rift time left",false);
    }

    public void setRiftTime(long i) {
        riftTime = i;
    }

    public void addRiftTime(long i) {
        setRiftTime(i + riftTime);
    }

    public void subtractRiftTime(long i) {
        if (riftTime < 10) return;
        if (riftTime - i < 10) setRiftTime(10);
        else setRiftTime(riftTime - i);
    }

    public void riftTimeRunout() {

    }

    public static RiftPlayer getRiftPlayer(Player player) {
        if (ServerType.getActiveType() != ServerType.Rift)
            throw new RuntimeException("Not allowed to get the riftplayer without beeing in the rift!");
        return (RiftPlayer) SkyblockPlayer.getSkyblockPlayer(player);
    }
    public void updateBar(){
        this.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(((timer.isRunning()) ? "§a" : "§7") + toTimeString(Tools.breakDownTime(riftTime)) + Stats.RiftTime.getSymbol() + " Left   " + defenseString + "   §b"
        + currmana + "/" + Main.getPlayerStat(this, Stats.RiftInteligence)));
    }
    private String toTimeString(Triple<Long, Long, Long> time){
        StringBuilder builder = new StringBuilder();
        if(time.getThird() != 0)
            builder.append(time.getThird()).append("h").append(" ");
        if(time.getSecond() != 0)
            builder.append(time.getSecond()).append("m").append(" ");
        builder.append(time.getFirst()).append("s");
        return builder.toString();
    }

    @Override
    public void setHealth(int value, HealthChangeReason reason) {
        getPlayer().setHealth(value);
    }

    @Override
    public void setHealth(float value, HealthChangeReason reason) {
        getPlayer().setHealth(value);
    }

    @Override
    public void setHealth(double value, HealthChangeReason reason) {
        getPlayer().setHealth(value);
    }

    @Override
    public void setHealth(int value) {
        getPlayer().setHealth(value);
    }

    @Override
    public void setHealth(float value) {
        getPlayer().setHealth(value);
    }

    @Override
    public void setHealth(double value) {
        getPlayer().setHealth(value);
    }

    public class RiftTicker extends BukkitRunnable {
        @Getter
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
