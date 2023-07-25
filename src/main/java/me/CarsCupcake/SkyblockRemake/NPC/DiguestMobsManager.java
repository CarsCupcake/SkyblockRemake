package me.CarsCupcake.SkyblockRemake.NPC;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import com.mojang.authlib.properties.Property;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;


public class DiguestMobsManager implements Listener {
	public static void createEntity(LivingEntity entity, String texture, String signature) {
		new PlayerDisguise(entity, new Property("textures",texture, signature));
	}
}
