package me.CarsCupcake.SkyblockRemake.utils.Inventories.Templates;

import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import org.bukkit.Material;

public class DemoGui {
    public static void openDemoGui(){
        GUI gui = new GUI(new InventoryBuilder(6,"Test").setItem(new ItemBuilder(Material.OAK_BUTTON).setName("§aClick Me!").build(), 0).build());
        //Selve explanetory: closes the inv (will cause an exception cuz player curr null :/)
        gui.closeInventory();
        //Shows the inv to a player (will also cause an exception cuz player null)
        gui.showGUI(null);
        //Stops item from beeing taken out of the menu (default: false)
        gui.setCanceled(true);
        //Runns the interface if the player clicks on that slot (in this case 0)
        gui.inventoryClickAction(0, type -> {
            //type is the click type (right,left...)
        });
        //Swaps the current opened inv with another inv
        gui.swapInventory(null);
        //Interface is beeing run if the player closes the inv or the inv gets closed by stuff like GUI#closeInventory()
        gui.closeAction(type -> {
            //idk why type is here tbh
        });
        //same as inventoryClickAction but in the players inventory
        gui.playerInventoryClickAction(0, type -> {

        });
        //Triggers if player clicks in any inv or the inv is getting closed
        gui.setGeneralAction((slot, actionType, type) -> {
            //slot is the slot
            //action type is the type of action (Click a the "custom inv" click, Player is a player inv click, close is on close)
            //type is again the click type
            //Returns if it should be canceled or not (ignores the return if setCanceled is true)
            return true;
        });
    }
}
