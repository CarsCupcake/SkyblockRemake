package me.CarsCupcake.SkyblockRemake.Skyblock.Jerry;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.EntityNPCInteractionEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryGUIAction;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class JerryListener implements Listener {
    @EventHandler
    public void checkEntity(EntityNPCInteractionEvent event){


        if(event.getNpc().getName().equals("Jerry")){
            event.setCancelled(true);
            Inventory inv = new InventoryBuilder(6, "Deliverys").build();

            int i = 10;
            final ArrayList<IDelivery> deliverys = IDelivery.getAll(event.getPlayer());
            for (IDelivery delivery :deliverys){
                inv.setItem(i, delivery.getShowItem());
                i++;
                if(i == 17 || i == 26 || i == 35)
                    i+= 2;
                if(i == 44)
                    break;

            }
            SkyblockPlayer player = event.getPlayer();
            GUI gui = new GUI(inv);
            gui.setGeneralAction((slot, actionType, type) -> {
                int id = getIntFormSlot(slot);
                if(id == -1)
                    return;
                Bukkit.getScheduler().runTask(Main.getMain(), ()-> {
                    deliverys.get(id).claim();
                    gui.closeInventory();
                });
            });
            gui.setCanceled(true);
            gui.showGUI(player);


        }


    }
    private int getIntFormSlot(int slot){
        int i = slot - 10;
        if(slot > 16)
            i -= 2;
        if(slot > 25)
            i -= 2;
        if(slot > 34)
            i -= 2;
        if(slot > 43)
            i = -1;
        return i;
    }
}
