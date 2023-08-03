package me.CarsCupcake.SkyblockRemake.NPC;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import com.mojang.authlib.properties.Property;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;


public class DiguestMobsManager implements Listener {
	/**
	 * Used to make a fakeplayer.
	 * Deprecated for removal: Use PlayerDisguise
	 * @param entity the entity you want to to disguise
	 * @param texture texture of the skin
	 * @param signature signature of the skin
	 */
	@Deprecated(since = "0.2.4", forRemoval = true)
	public static void createEntity(LivingEntity entity, String texture, String signature) {
		new PlayerDisguise(entity, new Property("textures",texture, signature));
	}
}
