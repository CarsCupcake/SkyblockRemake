package me.CarsCupcake.SkyblockRemake.Crafting;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;

public class SkyblockShapelessRecipe implements SkyblockRecipe{
	private final ArrayList<CraftingObject> ingredients = new ArrayList<>();
	private final ItemManager result;
	private final String id;
public SkyblockShapelessRecipe(String id, ItemManager manager) {
	this.id = id;
	result = manager;
}
public void addIngredient(CraftingObject manager) {
	ingredients.add(manager);
}
public ArrayList<CraftingObject> getIngredients(){
	return ingredients;
}
public String getId() {
	return id;
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
