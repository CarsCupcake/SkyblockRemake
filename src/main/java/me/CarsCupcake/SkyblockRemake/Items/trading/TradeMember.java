package me.CarsCupcake.SkyblockRemake.Items.trading;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TradeMember {
    private static final List<Integer> ownSlots = new ArrayList<>();

    private static final List<Integer> otherSlot = new ArrayList<>();
    private final GUI gui;
    private final List<ITradeItem> own = new ArrayList<>();
    private final List<ITradeItem> other = new ArrayList<>();

    private final SkyblockPlayer player;
    static {
        for (int i = 0; i < 9 * 5 + 4; i++){
            if((i - 4) % 9 == 0) i+=5;
            ownSlots.add(i);
        }
        for (int i = 0; i < 45; i++){
            if((i) % 9 == 0) i+=5;
            otherSlot.add(i);
        }
    }
    public TradeMember(SkyblockPlayer player, Object otherMember, boolean initiator){
        TradeMember oM;
        if(initiator) oM = new TradeMember((SkyblockPlayer) otherMember, this, false);
        else oM = (TradeMember) otherMember;
        this.player = player;
        gui = new GUI(new InventoryBuilder(6, "You               Other")
                .verticalFill(4, 6, TemplateItems.EmptySlot.getItem())
                .build());
        gui.setCanceled(true);
        gui.setGeneralAction((slot, actionType, type) -> {
            if(actionType == GUI.GUIActions.PlayerClick){
                ItemStack item = player.getInventory().getItem(slot);
                if(item == null || item.getType() == Material.AIR) return true;
                addOwn(new ItemTrade(slot));
                oM.addOponent(new ItemTrade(slot));
            }else if(actionType == GUI.GUIActions.Click){
                //TODO: add removal
            }else {
                close();
                oM.close();
                oM.player.sendMessage("§cTrade got cancelled!");
            }
            return true;
        });
        gui.showGUI(player);
    }
    public void close(){
        for (ITradeItem item : own)
            item.takeBack(player);
        gui.closeInventory();
    }
    public void addOponent(ITradeItem item){
        if(!item.check(player)) return;
        other.add(item);
        update();
    }
    public void addOwn(ITradeItem item){
        if(!item.check(player)) return;
        own.add(item);
        item.takeIn(player);
        update();
    }

    public void update(){
        int i = 0;
        for (int slot : ownSlots){
            if(i < own.size()) gui.getInventory().setItem(slot, own.get(i).show());
            else gui.getInventory().setItem(slot, new ItemStack(Material.AIR));
            i++;
        }
        i = 0;
        for (int slot : otherSlot){
            if(i < other.size()) gui.getInventory().setItem(slot, other.get(i).show());
            else gui.getInventory().setItem(slot, new ItemStack(Material.AIR));
            i++;
        }
    }

    public void complete(){
        for (ITradeItem item : other){
            item.onClaim(player);
        }
        gui.closeInventory();
    }
}
