package me.CarsCupcake.SkyblockRemake.utils.Inventorys;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventory.GuiTemplate;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class GUI {
    protected Inventory inv;
    protected SkyblockPlayer player;
    protected final HashMap<Integer, GUIAction> inventoryClickAction = new HashMap<>();
    protected final HashMap<Integer, GUIAction> playerInventoryClickAction = new HashMap<>();
    protected GUIAction closeAction;
    protected InventoryGUIAction generalAction;
    protected static final HashMap<SkyblockPlayer, GUI> opened = new HashMap<>();
    protected boolean isCanceled = false;

    public GUI(Inventory inventory){
        inv = inventory;
    }
    public GUI(GuiTemplate template){
        this(template.getInventory());
        for (int slot : template.getSlotActions().keySet())
            inventoryClickAction.put(slot, template.getSlotActions().get(slot));
        isCanceled = template.isCanceled();
    }
    public void showGUI(SkyblockPlayer player){
        this.player = player;
        player.openInventory(inv);
        opened.put(player, this);
    }
    public SkyblockPlayer getPlayer(){
        return player;
    }
    public void inventoryClickAction(int slot, GUIAction action){
        inventoryClickAction.put(slot, action);
    }
    public void playerInventoryClickAction(int slot, GUIAction action){
        playerInventoryClickAction.put(slot, action);
    }
    public void closeAction(GUIAction action){
        closeAction = action;
    }
    public void closeInventory(){
        if(player != null&& player.getOpenInventory() != null)
             player.closeInventory();
        opened.remove(player);
        player = null;

    }
    public void setCanceled(boolean bool){
        isCanceled = bool;
    }
    public boolean isCanceled(){
        return isCanceled;
    }
    public void triggerAction(GUIActions actions, int slot, ClickType type){
        if(slot < 0 && (actions == GUIActions.Click || actions == GUIActions.PlayerClick))
            throw new IndexOutOfBoundsException("Your slot is negative!");
        switch (actions){
            case Click -> {
                if (inventoryClickAction.containsKey(slot))
                    inventoryClickAction.get(slot).run(type);
                if(generalAction != null)
                    generalAction.run(slot, actions, type);
            }
            case PlayerClick -> {
                if (playerInventoryClickAction.containsKey(slot))
                    playerInventoryClickAction.get(slot).run(type);
                if(generalAction != null)
                    generalAction.run(slot, actions, type);
            }
            case Close -> {
                if (closeAction != null)
                    closeAction.run(type);
            }


        }
    }
    public void triggerAction(GUIActions actions){
        triggerAction(actions, -1,null);
    }
    public static HashMap<SkyblockPlayer, GUI> getGUIs(){
       return opened;
    }
    public void setGeneralAction(InventoryGUIAction action){
        generalAction = action;
    }
    public void swapInventory(Inventory i){
        inv = i;
        player.openInventory(inv);
    }
    public Inventory getInventory(){
        return inv;
    }
    public enum GUIActions{
        Close,
        Click,
        PlayerClick

    }
}
