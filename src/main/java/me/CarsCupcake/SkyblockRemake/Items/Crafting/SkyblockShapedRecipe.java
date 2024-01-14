package me.CarsCupcake.SkyblockRemake.Items.Crafting;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.utils.Assert;

public final class SkyblockShapedRecipe extends SkyblockRecipe {
    private final ItemManager result;
    private final int amount;
    @Getter
    private int prior = -1;

    @Setter
    @Getter
    private ArrayList<CraftingObject> recipe = new ArrayList<>();


    public SkyblockShapedRecipe(ItemManager manager, int amount) {
        result = manager;
        this.amount = amount;
    }


    @Override
    public ItemManager getResult() {
        return result;
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

