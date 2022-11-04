package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Configs.AccessoryBag;

public class AddAccessoryBagSlots implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player && arg3.length == 1 && arg2.equalsIgnoreCase("accessoryslots")) {
			
			try {
				Integer.parseInt(arg3[0]);
			}catch(Exception e){
				System.out.println("Wrong Arg Use");
				return false;
			}
			Player player = (Player)arg0;
			int newSlots = Integer.parseInt(arg3[0]);
			
			AccessoryBag.reload();
		    int slots = AccessoryBag.get().getInt(player.getUniqueId() + ".slots");
			if(newSlots > slots) {
				for(int i = slots; i < newSlots; i++) {
					AccessoryBag.get().set(player.getUniqueId() + ".SLOT_" + i, false);
					
				}
				AccessoryBag.get().set(player.getUniqueId() + ".slots", newSlots);
				AccessoryBag.save();
				AccessoryBag.reload();
			}else {
				for(int i = slots-1; i > newSlots - 1; i--) {
					AccessoryBag.get().set(player.getUniqueId() + ".SLOT_" + i, null);
				}
				AccessoryBag.get().set(player.getUniqueId() + ".slots", newSlots);
				AccessoryBag.save();
				AccessoryBag.reload();
			}
				
			
			return true;
		}
		return false;
	}

}
