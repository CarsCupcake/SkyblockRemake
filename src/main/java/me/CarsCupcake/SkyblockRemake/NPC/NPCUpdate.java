package me.CarsCupcake.SkyblockRemake.NPC;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.PlayerInteractManager;
import net.minecraft.server.level.WorldServer;

public class NPCUpdate extends EntityPlayer {

    public NPCUpdate(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile,
            PlayerInteractManager playerinteractmanager) {
        super(minecraftserver, worldserver, gameprofile);
        // TODO Auto-generated constructor stub
        }

        public NPCUpdate(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile) {
		// TODO Auto-generated constructor stub
        	super(minecraftserver, worldserver, gameprofile);
	}

		@Override
        public void tick() {
        Bukkit.getConsoleSender().sendMessage("Tick");
        for (Entity ent : getBukkitEntity().getNearbyEntities(10, 10, 10)) {
            if (!(ent instanceof Player))
            continue;
            ent.sendMessage("near");
            getBukkitEntity().teleport(ent.getLocation());
            setHeadRotation(ent.getLocation().getDirection().getBlockY());
            break;
        }
        }
}
