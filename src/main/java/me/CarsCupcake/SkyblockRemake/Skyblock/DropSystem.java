package me.CarsCupcake.SkyblockRemake.Skyblock;

import java.util.HashMap;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;



public class DropSystem {
public DropSystem(Entity entity, Player player, EntityDeathEvent e) {
	LivingEntity le = (LivingEntity) entity;
	if(SkyblockEntity.livingEntity.exists(le)){
		e.getDrops().clear();
		HashMap<ItemManager,Integer> drops = SkyblockEntity.livingEntity.getSbEntity(le).getDrops(SkyblockPlayer.getSkyblockPlayer(player));
		if(drops != null && !drops.isEmpty()){
			for(ItemManager item : drops.keySet()) {
				ItemStack i = item.createNewItemStack();
				i.setAmount(drops.get(item));
				e.getDrops().add(Main.item_updater(i, SkyblockPlayer.getSkyblockPlayer(player)));
			}
		}
	}
}
}
