package me.CarsCupcake.SkyblockRemake.Items.Crafting;

import java.util.ArrayList;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;

public class SkyblockShapelessRecipe extends SkyblockRecipe {
    @Getter
    private final ArrayList<CraftingObject> ingredients = new ArrayList<>();
    private final ItemManager result;

    public SkyblockShapelessRecipe(ItemManager manager) {
        result = manager;
    }

    public void addIngredient(CraftingObject manager) {
        ingredients.add(manager);
    }

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public ItemManager getResult() {
        return result;
    }
}
