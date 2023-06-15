package me.CarsCupcake.SkyblockRemake.Items.Crafting;

import java.util.ArrayList;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.utils.Assert;

public class SkyblockShapedRecipe extends SkyblockRecipe {

    private final String id;
    private final ItemManager result;
    private final int amount;
    @Getter
    private int prior = -1;

    private ArrayList<CraftingObject> recipe = new ArrayList<>();


    public SkyblockShapedRecipe(String id, ItemManager manager, int amount) {
        this.id = id;
        result = manager;
        this.amount = amount;
    }

    public void setRecipe(ArrayList<CraftingObject> recipe) {
        this.recipe = recipe;


    }

    @Override
    public ItemManager getResult() {
        //yes


        return result;
    }

    public ArrayList<CraftingObject> getRecipe() {
        return recipe;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    /**
     * Is the item that gets the attributes transfered to the result
     * @param i slot (0 index) (left to right top to bottom)
     */
    public void setPrio(int i){
        Assert.isSmaller(9, i);
        prior = i;
    }
    public boolean hasPrio(){
        return prior > 1;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + result.itemID + ", " + amount + "]";
    }
}

