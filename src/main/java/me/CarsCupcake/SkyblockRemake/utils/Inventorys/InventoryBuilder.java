package me.CarsCupcake.SkyblockRemake.utils.Inventorys;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class InventoryBuilder {
    private final int rows;
    private final LinkedList<ItemStack> itemStacks;
    private final String name;

    @Range(from=1, to=6)
    public InventoryBuilder(int rows, String name){
        this.rows = rows;
        itemStacks = new LinkedList<>();
        this.name = name;
        init();
        }

    public InventoryBuilder(InventoryTemplate template){
        this.rows = template.getRows();
        this.name = template.getName();
        itemStacks = new LinkedList<>();
        itemStacks.addAll(Arrays.asList(template.getCompiledItems().getContents()));
        init();
    }
    private void init(){
        if(itemStacks.isEmpty()){
            for (int i = 0; i < rows*9; i++)
                itemStacks.add(new ItemStack(Material.AIR));
        }
    }
    public Inventory build(){
        Inventory inventory = Bukkit.createInventory(null, rows*9, name);
        inventory.setContents(itemStacks.toArray(new ItemStack[0]));
        return inventory;
    }

    public InventoryBuilder fill(ItemStack item, int v1, int v2){
        for(int i = v1; i < v2 + 1; i++)
            itemStacks.set(i, item);
        return this;
    }
    public InventoryBuilder fill(ItemStack item){
        return fill(item,0,rows*9-1);

    }
    public InventoryBuilder fill(ItemManager manager, int v1, int v2){
        return fill(manager.getRawItemStack(), v1, v2);
    }
    public InventoryBuilder fill(ItemManager manager){
        return fill(manager.getRawItemStack());
    }
    public InventoryBuilder setItem(ItemStack item, int i){
        itemStacks.set(i, item);
        return this;
    }
    public InventoryBuilder setItem(ItemManager item, int i){
        return setItem(item.getRawItemStack(), i);
    }

    public InventoryBuilder setItems(ItemStack[] item, int i){
        for(ItemStack stack : item){
            itemStacks.set(i, stack);
            i++;
        }
        return this;
    }
    public InventoryBuilder setItems(ItemStack item, int... i){
        for (int t : i)
            setItem(item,t);
        return this;
    }
    public InventoryBuilder setItems(ItemManager[] item, int i){
        ArrayList<ItemStack> items = new ArrayList<>(item.length);
        for(ItemManager itemManager : item)
            items.add(itemManager.getRawItemStack());

        return setItems(items.toArray(new ItemStack[0]), i);
    }


}
