package me.CarsCupcake.SkyblockRemake.cmd;




import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;


public class testobjectCMD implements CommandExecutor{
	
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("testobject")) {
			if (!(sender instanceof Player player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			player.getWorld().spawn(player.getLocation(), ArmorStand.class, s -> {
				s.setRightArmPose(new EulerAngle(Math.toRadians(Double.parseDouble(args[0])),0,0));
				s.getEquipment().setItemInMainHand(new ItemStack(Material.WOODEN_PICKAXE));
			});


			return true;
			
		}
		
		return false;
	}

}
