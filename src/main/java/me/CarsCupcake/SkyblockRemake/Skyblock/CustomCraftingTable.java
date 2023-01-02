package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockRecipe;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockShapedRecipe;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Set;


public class CustomCraftingTable implements Listener {
    @EventHandler
    public void onSwap(InventoryClickEvent event){
        if(!event.getView().getTitle().equals("§8Crafting Table"))
            return;
        if(event.getClickedInventory().getType() != InventoryType.PLAYER){
            int slot = event.getSlot();
            if(!(Tools.isInRange(9,13,slot) || Tools.isInRange(18,22,slot) || Tools.isInRange(27,31,slot)))
                event.setCancelled(true);
        }

        if(event.getClickedInventory().getType() != InventoryType.PLAYER && event.getSlot() == 23){
            ArrayList<ItemStack> craftingGrid = new ArrayList<>();
            for (int i = 10; i < 13; i++)
                craftingGrid.add(event.getView().getTopInventory().getItem(i));
            for (int i = 19; i < 22; i++)
                craftingGrid.add(event.getView().getTopInventory().getItem(i));
            for (int i = 28; i < 31; i++)
                craftingGrid.add(event.getView().getTopInventory().getItem(i));
            Set<SkyblockRecipe> managers = SkyblockRecipe.checkForRecipe(craftingGrid);
            if(managers.isEmpty()) {
                event.getView().getTopInventory().setItem(23, CustomCraftingTableInvenotry.CraftDenie());
                return;
            }

                SkyblockRecipe entry = managers.iterator().next();
                ItemStack item = Main.item_updater(Main.item_updater(Items.SkyblockItems.get(entry.getResult().itemID).createNewItemStack(),
                        SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())), SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked()));
                item.setAmount(entry.getAmount());
                event.getView().getTopInventory().setItem(23, Main.item_updater(Main.item_updater(item, SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())), SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())));

            if(!managers.isEmpty()){
                if(event.getCursor() != null && event.getCursor().getType() != Material.AIR &&
                        event.getCursor().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(),"id") , PersistentDataType.STRING).equals(entry.getResult().itemID) &&
                        (event.getCursor().getAmount() + entry.getAmount() > entry.getResult().getRawItemStack().getMaxStackSize()))
                    return;

                int runner = 0;
                for (int i = 10; i < 13; i++) {

                    if(entry instanceof SkyblockShapedRecipe){
                        SkyblockShapedRecipe shapedRecipe = (SkyblockShapedRecipe) entry;
                        if(shapedRecipe.getRecipe().get(runner) != null){
                            ItemStack is = event.getView().getTopInventory().getItem(i);
                            if(is != null){

                            is.setAmount(is.getAmount() - shapedRecipe.getRecipe().get(runner).amount());
                            event.getView().getTopInventory().setItem(i,is);
                            }
                        }runner++;
                    }

                }
                for (int i = 19; i < 22; i++) {
                    if(entry instanceof SkyblockShapedRecipe){
                        SkyblockShapedRecipe shapedRecipe = (SkyblockShapedRecipe) entry;
                        if(shapedRecipe.getRecipe().get(runner) != null){
                            ItemStack ite = event.getView().getTopInventory().getItem(i);
                            if(ite != null){

                                ite.setAmount(ite.getAmount() - shapedRecipe.getRecipe().get(runner).amount());
                                event.getView().getTopInventory().setItem(i,ite);
                            }
                        }runner++;
                    }

                }
                for (int i = 28; i < 31; i++) {
                    if(entry instanceof SkyblockShapedRecipe shapedRecipe){
                        if(shapedRecipe.getRecipe().get(runner) != null){
                            ItemStack ite = event.getView().getTopInventory().getItem(i);
                            if(ite != null){

                                ite.setAmount(ite.getAmount() - shapedRecipe.getRecipe().get(runner).amount());
                                event.getView().getTopInventory().setItem(i,ite);
                            }
                        } runner++;
                    }

                }
                if(event.getCursor() != null && event.getCursor().getType() != Material.AIR){
                    ItemStack it = event.getCursor();
                    it.setAmount(it.getAmount() + entry.getAmount());
                    event.setCursor(it);
                }else {
                    ItemStack it = Main.item_updater(entry.getResult().createNewItemStack(),  SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked()));
                    it.setAmount(entry.getAmount());
                    event.setCursor(Main.item_updater(it, SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())));
                }
                refreshCraft(event);
            }

        }
        else
        new BukkitRunnable() {
            @Override
            public void run() {
                ArrayList<ItemStack> craftingGrid = new ArrayList<>();
                for (int i = 10; i < 13; i++)
                    craftingGrid.add(event.getView().getTopInventory().getItem(i));
                for (int i = 19; i < 22; i++)
                    craftingGrid.add(event.getView().getTopInventory().getItem(i));
                for (int i = 28; i < 31; i++)
                    craftingGrid.add(event.getView().getTopInventory().getItem(i));
                Set<SkyblockRecipe> managers = SkyblockRecipe.checkForRecipe(craftingGrid);
                if(managers.isEmpty())
                    event.getView().getTopInventory().setItem(23,CustomCraftingTableInvenotry.CraftDenie());
                else{
                    SkyblockRecipe entry = managers.iterator().next();
                    ItemStack item = Main.item_updater(Main.item_updater(Items.SkyblockItems.get(entry.getResult().itemID).createNewItemStack(), SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())), SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked()));
                    item.setAmount(entry.getAmount());
                    event.getView().getTopInventory().setItem(23, Main.item_updater(Main.item_updater(item, SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())), SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())));
                }
            }
        }.runTaskLater(Main.getMain(),5);


    }
    private void refreshCraft(InventoryClickEvent event) {
        ArrayList<ItemStack> craftingGrid = new ArrayList<>();
        for (int i = 10; i < 13; i++){
            craftingGrid.add(event.getView().getTopInventory().getItem(i));
        }
        for (int i = 19; i < 22; i++)
            craftingGrid.add(event.getView().getTopInventory().getItem(i));
        for (int i = 28; i < 31; i++)
            craftingGrid.add(event.getView().getTopInventory().getItem(i));
        Set<SkyblockRecipe> managers = SkyblockRecipe.checkForRecipe(craftingGrid);
        if(managers.isEmpty())
            event.getView().getTopInventory().setItem(23,CustomCraftingTableInvenotry.CraftDenie());
        else{
            SkyblockRecipe entry = managers.iterator().next();
            ItemStack item = Main.item_updater(Main.item_updater(Items.SkyblockItems.get(entry.getResult().itemID).createNewItemStack(), SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())), SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked()));
            item.setAmount(entry.getAmount());
            event.getView().getTopInventory().setItem(23, Main.item_updater(Main.item_updater(item, SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())), SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())));
        }
    
    }     
    @EventHandler
    public void interact(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.CRAFTING_TABLE){
                event.setCancelled(true);
                event.getPlayer().openInventory(CustomCraftingTableInvenotry.createInventory());
            }

        }
    }
    @EventHandler
    public void returnItems(InventoryCloseEvent event){
        if(event.getView().getTitle().equals("§8Crafting Table")){
            for (int i = 10; i < 13; i++) {
                if((event.getView().getItem(i) == null))
                    continue;
                final int slot = i;
                boolean hasSpace = false;
                int sl = 0;
                for (ItemStack item : event.getView().getBottomInventory().getContents()) {

                    if ((item == null || item.getType() == Material.AIR)&& sl < 36) {


                        hasSpace = true;
                        break;
                    }sl++;
                }
                System.out.println(hasSpace);
                    if (hasSpace)
                        event.getPlayer().getInventory().addItem(event.getView().getItem(i));
                    else
                        event.getPlayer().getWorld().spawn(event.getPlayer().getLocation(), Item.class, it -> {
                            it.setItemStack(event.getView().getTopInventory().getItem(slot));
                        });

            }

            for (int i = 19; i < 22; i++){
                if((event.getView().getItem(i) == null))
                    continue;
                final int slot = i;
                boolean hasSpace = false;
                int sl = 0;
                for (ItemStack item : event.getView().getBottomInventory().getContents()) {

                    if ((item == null || item.getType() == Material.AIR)&& sl < 36) {


                        hasSpace = true;
                        break;
                    }sl++;
                }
                System.out.println(hasSpace);
                    if (hasSpace)
                        event.getPlayer().getInventory().addItem(event.getView().getItem(i));
                    else
                        event.getPlayer().getWorld().spawn(event.getPlayer().getLocation(), Item.class, it -> {
                            it.setItemStack(event.getView().getTopInventory().getItem(slot));
                        });

            }
            for (int i = 28; i < 31; i++){
                if((event.getView().getItem(i) == null))
                    continue;
                final int slot = i;
                boolean hasSpace = false;
                int sl = 0;
                for (ItemStack item : event.getView().getBottomInventory().getContents()) {

                    if ((item == null || item.getType() == Material.AIR)&& sl < 36) {


                        hasSpace = true;
                        break;
                    }sl++;
                }
                System.out.println(hasSpace);
                if (hasSpace)
                    event.getPlayer().getInventory().addItem(event.getView().getItem(i));
                    else
                        event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), event.getView().getItem(i));
            }

            }
        }
    }


