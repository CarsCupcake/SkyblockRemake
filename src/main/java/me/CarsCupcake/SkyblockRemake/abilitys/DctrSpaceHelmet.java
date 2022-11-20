package me.CarsCupcake.SkyblockRemake.abilitys;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class DctrSpaceHelmet implements FullSetBonus {

	
	private SkyblockPlayer player;
	private BukkitRunnable run;
	private int counter = 0;
	
	@Override
	public void start() {
		animation();

	}

	@Override
	public void stop() {
		try {
			run.cancel();
		} catch (Exception e) {
		
		}
		

	}

	@Override
	public int getMaxPieces() {
		return 1;
	}

	@Override
	public int getPieces() {
		return 1;
	}

	@Override
	public void setPlayer(SkyblockPlayer player) {
		this.player = player;

	}

	@Override
	public Bonuses getBonus() {
		return Bonuses.DctrSpaceHelmet;
	}
	
	private void animation() {
		run = new BukkitRunnable() {
			
			@Override
			public void run() {
				counter += 1;
				ItemStack helmet = player.getEquipment().getHelmet();
				switch (counter){
				case 0:
					helmet.setType(Material.RED_STAINED_GLASS);
					break;
				case 1:
					helmet.setType(Material.ORANGE_STAINED_GLASS);
					break;
				case 2:
					helmet.setType(Material.YELLOW_STAINED_GLASS);
					break;


				case 3:
					helmet.setType(Material.LIME_STAINED_GLASS);
					break;
				case 4:
					helmet.setType(Material.GREEN_STAINED_GLASS);
					break;
				case 5:
					helmet.setType(Material.LIGHT_GRAY_STAINED_GLASS);
					break;
				case 6:
					helmet.setType(Material.LIGHT_BLUE_STAINED_GLASS);
					break;
				case 7:
					helmet.setType(Material.BLUE_STAINED_GLASS);
					break;
				case 8:
					helmet.setType(Material.PURPLE_STAINED_GLASS);
					break;
				case 9:
					helmet.setType(Material.PINK_STAINED_GLASS);
					counter = -1;
				    break;

					
				}
				player.getEquipment().setHelmet(helmet);
				
			}
		};
		run.runTaskTimer(Main.getMain(), 10, 10);
	}

}
