package me.CarsCupcake.SkyblockRemake.NPC;

import com.mojang.datafixers.util.Pair;
import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F1.NPCUtil;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EnumItemSlot;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.maven.model.License;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class DisguisedEntity {
    private final LivingEntity entity;
    private BukkitRunnable runnable;
    @Getter
    private final EntityPlayer player;
    public DisguisedEntity(LivingEntity entity, String texture, String signature){
        this.entity = entity;
        entity.setInvisible(true);
        entity.setSilent(true);
        player = NPCUtil.spawnNPC(entity.getLocation(), texture, signature);
        sendPacket(new PacketPlayOutEntityEquipment(player.getId(), List.of(Pair.of(EnumItemSlot.a,
                CraftItemStack.asNMSCopy(entity.getEquipment().getItemInMainHand())))), true);
        sendPacket(new PacketPlayOutEntityEquipment(player.getId(), List.of(Pair.of(EnumItemSlot.b,
                CraftItemStack.asNMSCopy(entity.getEquipment().getItemInOffHand())))), true);
        sendPacket(new PacketPlayOutEntityEquipment(player.getId(), List.of(Pair.of(EnumItemSlot.c,
                CraftItemStack.asNMSCopy(entity.getEquipment().getBoots())))), true);
        sendPacket(new PacketPlayOutEntityEquipment(player.getId(), List.of(Pair.of(EnumItemSlot.d,
                CraftItemStack.asNMSCopy(entity.getEquipment().getLeggings())))), true);
        sendPacket(new PacketPlayOutEntityEquipment(player.getId(), List.of(Pair.of(EnumItemSlot.e,
                CraftItemStack.asNMSCopy(entity.getEquipment().getChestplate())))), true);
        sendPacket(new PacketPlayOutEntityEquipment(player.getId(), List.of(Pair.of(EnumItemSlot.f,
                CraftItemStack.asNMSCopy(entity.getEquipment().getHelmet())))), true);
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                player.setCustomName(new ChatComponentText(entity.getCustomName()));

            }
        };
        runnable.runTaskTimer(Main.getMain(), 1, 1);
    }
    public void performAction(Action a, Object... payload){
        switch (a){
            case Die -> {
                runnable.cancel();
                sendPacket(new PacketPlayOutEntityStatus((Entity) player, (byte) 3));
                sendPacket(new PacketPlayOutEntityDestroy(player.getId()), true);
                entity.remove();
            }
            case Hurt -> {
                sendPacket(new PacketPlayOutAnimation(player, 1));
            }
            case Swing -> {
                sendPacket(new PacketPlayOutAnimation(player, 0));
            }
        }
    }

    public void sendPacket(Packet<?> packet){
        sendPacket(packet, false);

    }
    public void sendPacket(Packet<?> packet, boolean force){
        if(entity.getLocation().getChunk().isEntitiesLoaded() || force)
            for (Player p : Bukkit.getOnlinePlayers()){
                if(p.hasLineOfSight(entity) || force)
                    ((CraftPlayer) p).getHandle().b.sendPacket(packet);
            }
    }

    enum Action{
        Hurt,
        Swing,
        Die

    }
}
