package me.CarsCupcake.SkyblockRemake.Items.Crafting;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;

public class SkyblockShapedRecipe extends SkyblockRecipe {

    private final String id;
    private final ItemManager result;
    private final int amount;

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
}

