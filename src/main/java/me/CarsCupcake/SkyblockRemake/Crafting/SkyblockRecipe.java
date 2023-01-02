package me.CarsCupcake.SkyblockRemake.Crafting;



import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public interface SkyblockRecipe  {

      Set<SkyblockRecipe> recipes = new HashSet<>();
     ItemManager getResult();
    String getId();
     int getAmount();


     static  Set<SkyblockRecipe> checkForRecipe(ArrayList<ItemStack> stacks){
        ArrayList<String> managerIds = new ArrayList<>();
        for(ItemStack stack : stacks) {
            if(stack == null || stack.getType() == Material.AIR) {
                managerIds.add("");
                continue;
            }
            String id = stack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING);
            if(id == null)
            {
                managerIds.add("");
                continue;
            }
            managerIds.add(id);

        }
        Set<SkyblockRecipe> results = new HashSet<>();
        for(SkyblockRecipe recipe : recipes){

            if(recipe instanceof SkyblockShapedRecipe shapedRecipe){

                int i = 0;
                boolean isCorrect = true;
                for(CraftingObject craftingObject : shapedRecipe.getRecipe()){
                    String id = managerIds.get(i);
                    String id2 = "";
                    if(craftingObject != null && craftingObject.manager() != null){
                        id2 = craftingObject.manager().itemID;
                    }

                    if(!id.equals(id2)){

                        isCorrect = false;
                        break;
                    }else {
                        if(stacks.get(i) != null && stacks.get(i).getItemMeta() != null){
                            if(stacks.get(i).getAmount() < craftingObject.amount()) {
                                isCorrect = false;

                                break;
                            }
                        }
                    }

                    i++;
                }
                if(isCorrect){
                    if(recipe.getResult() == null)
                        continue;

                    results.add(recipe);

                }


            }
            if(recipe instanceof SkyblockShapelessRecipe shapless){
                boolean isCorrect = true;
                Set<String> ids = new HashSet<>(managerIds);
                Set<CraftingObject> ingredients = new HashSet<>(shapless.getIngredients());
                int index = -1;
                HashMap<String, Integer> ingredientsAmounts = new HashMap<>();
                for(CraftingObject ingredient : ingredients)
                    if(ingredientsAmounts.containsKey(ingredient.manager().itemID))
                        ingredientsAmounts.replace(ingredient.manager().itemID,ingredientsAmounts.get(ingredient.manager().itemID) + ingredient.amount() );
                    else
                        ingredientsAmounts.put(ingredient.manager().itemID,ingredient.amount());
                HashMap<String, Integer> ingredientsAmount = new HashMap<>(ingredientsAmounts);
                for(String str:  ids){
                    index++;
                    if(str.equals(""))
                        continue;
                    boolean contains = false;
                    CraftingObject object = null;
                    for (CraftingObject obj : ingredients ){
                        if(obj.manager().itemID.equals(str)){
                            contains = true;
                            object = obj;
                        break;
                        }
                    }
                    if(!contains || object == null){
                        isCorrect = false;
                        break;
                    }else {
                        ingredientsAmount.replace(str,ingredientsAmount.get(str) - stacks.get(index).getAmount() );
                    }






                }
                if(isCorrect && shaplessIsDone(ingredientsAmount, ingredientsAmounts)){
                    if(recipe.getResult() == null)
                        continue;
                    results.add(recipe);
                }
            }


        }




        return results;
    }
     private static boolean shaplessIsDone(HashMap<String, Integer> ingredientsAmount, HashMap<String, Integer> wanted) {
         ArrayList<Boolean> result = new ArrayList<>();
         result.add(true);
         ingredientsAmount.forEach((id,amount)->
                 {
                     if (amount > wanted.get(id))
                         result.set(0, false);
                 }
             );
         return result.get(0);
    }
	//ItemManager item = Items.SkyblockItems.get("FLAWLESS_RUBY_GEM"), 8);
	//objects.add(new CraftingObject(item, 8));
	//RecipeItem instead of objects
     static void init(){
        ArrayList<CraftingObject> objects = new ArrayList<>();
        objects.add(new CraftingObject(Items.SkyblockItems.get("FLAWLESS_RUBY_GEM"), 8));objects.add(new CraftingObject(Items.SkyblockItems.get("FLAWLESS_RUBY_GEM"), 8));objects.add(new CraftingObject(Items.SkyblockItems.get("FLAWLESS_RUBY_GEM"), 8));
        objects.add(new CraftingObject(Items.SkyblockItems.get("FLAWLESS_RUBY_GEM"), 8));objects.add(new CraftingObject(Items.SkyblockItems.get(Material.EGG.toString()), 1));objects.add(new CraftingObject(Items.SkyblockItems.get("FLAWLESS_RUBY_GEM"), 8));
        objects.add(new CraftingObject(Items.SkyblockItems.get("FLAWLESS_RUBY_GEM"), 8));objects.add(new CraftingObject(Items.SkyblockItems.get("FLAWLESS_RUBY_GEM"), 8));objects.add(new CraftingObject(Items.SkyblockItems.get("FLAWLESS_RUBY_GEM"), 8));
        SkyblockShapedRecipe shapedRecipe = new SkyblockShapedRecipe("GemstoneGolemPet;COMMON",Items.SkyblockItems.get("GEMSTONE_GOLEM;COMMON"), 1);
        shapedRecipe.setRecipe(objects);
        recipes.add(shapedRecipe);

        objects = new ArrayList<>();
        objects.add(new CraftingObject(null, 0));objects.add(new CraftingObject(Items.SkyblockItems.get("ENCHANTED_SULPHUR"), 16));objects.add(new CraftingObject(null, 0));
        objects.add(new CraftingObject(null, 0));objects.add(new CraftingObject(Items.SkyblockItems.get("MOLTEN_POWDER"), 8));objects.add(new CraftingObject(null, 0));
        objects.add(new CraftingObject(null, 0));objects.add(new CraftingObject(Items.SkyblockItems.get("MOLTEN_POWDER"), 8));objects.add(new CraftingObject(null, 0));
        shapedRecipe = new SkyblockShapedRecipe("WarningFlare",Items.SkyblockItems.get("WARNING_FLARE"), 1);
        shapedRecipe.setRecipe(objects);
        recipes.add(shapedRecipe);

        objects = new ArrayList<>();
        objects.add(new CraftingObject(null, 0));objects.add(new CraftingObject(Items.SkyblockItems.get("ENCHANTED_SULPHUR"), 32));objects.add(new CraftingObject(Items.SkyblockItems.get("INFERNO_VERTEX"), 3));
        objects.add(new CraftingObject(null, 0));objects.add(new CraftingObject(Items.SkyblockItems.get("MOLTEN_POWDER"), 64));objects.add(new CraftingObject(null, 0));
        objects.add(new CraftingObject(Items.SkyblockItems.get("INFERNO_VERTEX"), 8));objects.add(new CraftingObject(Items.SkyblockItems.get("WARNING_FLARE"), 1));objects.add(new CraftingObject(null, 0));
        shapedRecipe = new SkyblockShapedRecipe("AlertFlare",Items.SkyblockItems.get("ALERT_FLARE"), 1);
        shapedRecipe.setRecipe(objects);
        recipes.add(shapedRecipe);

        objects = new ArrayList<>();
        objects.add(new CraftingObject(Items.SkyblockItems.get("INFERNO_VERTEX"), 8));objects.add(new CraftingObject(Items.SkyblockItems.get("ENCHANTED_SULPHUR"), 32));objects.add(new CraftingObject(Items.SkyblockItems.get("INFERNO_APEX"), 1));
        objects.add(new CraftingObject(Items.SkyblockItems.get("MOLTEN_POWDER"), 64));objects.add(new CraftingObject(null, 0));objects.add(new CraftingObject(Items.SkyblockItems.get("MOLTEN_POWDER"), 64));
        objects.add(new CraftingObject(Items.SkyblockItems.get("WILSON_ENGINEERING_PLANS"), 1));objects.add(new CraftingObject(Items.SkyblockItems.get("ALERT_FLARE"), 1));objects.add(new CraftingObject(Items.SkyblockItems.get("INFERNO_VERTEX"), 8));
        shapedRecipe = new SkyblockShapedRecipe("SosFlare",Items.SkyblockItems.get("SOS_FLARE"), 1);
        shapedRecipe.setRecipe(objects);
        recipes.add(shapedRecipe);

        ShapeEncoder encoder = new ShapeEncoder(" k ", " k ", " h ");
        encoder.setKey('k', new CraftingObject(Items.SkyblockItems.get("WITHER_CATALYST"), 12));
        encoder.setKey('h', new CraftingObject(Items.SkyblockItems.get("NECRON_HANDLE"), 1));
        shapedRecipe = new SkyblockShapedRecipe("necronsblade", Items.SkyblockItems.get("NECRON_BLADE"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);


        //Hyperion Recipes
        encoder = new ShapeEncoder("eee"
                                 , "ese",
                                   "eee");
        encoder.setKey('e', new CraftingObject(Items.laser_eye(), 1));
        encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("NECRON_BLADE"), 1));
        shapedRecipe = new SkyblockShapedRecipe("hyperion1", Items.SkyblockItems.get("hyperion"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        encoder.setKey('s' ,new CraftingObject(Items.SkyblockItems.get("VALKYRIE"), 1));
        shapedRecipe = new SkyblockShapedRecipe("hyperion2", Items.SkyblockItems.get("hyperion"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        encoder.setKey('s' ,new CraftingObject(Items.SkyblockItems.get("ASTRAEA"), 1));
        shapedRecipe = new SkyblockShapedRecipe("hyperion3", Items.SkyblockItems.get("hyperion"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        encoder.setKey('s' ,new CraftingObject(Items.SkyblockItems.get("SCYLLA"), 1));
        shapedRecipe = new SkyblockShapedRecipe("hyperion4", Items.SkyblockItems.get("hyperion"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);


        //Valkyrie Recipes
        encoder = new ShapeEncoder("eee"
                , "ese",
                "eee");
        encoder.setKey('e', new CraftingObject(Items.diamantesHandle(), 1));
        encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("NECRON_BLADE"), 1));
        shapedRecipe = new SkyblockShapedRecipe("valkyrie1", Items.SkyblockItems.get("VALKYRIE"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        encoder.setKey('s' ,new CraftingObject(Items.SkyblockItems.get("hyperion"), 1));
        shapedRecipe = new SkyblockShapedRecipe("valkyrie2", Items.SkyblockItems.get("VALKYRIE"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        encoder.setKey('s' ,new CraftingObject(Items.SkyblockItems.get("ASTRAEA"), 1));
        shapedRecipe = new SkyblockShapedRecipe("valkyrie3", Items.SkyblockItems.get("VALKYRIE"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        encoder.setKey('s' ,new CraftingObject(Items.SkyblockItems.get("SCYLLA"), 1));
        shapedRecipe = new SkyblockShapedRecipe("valkyrie4", Items.SkyblockItems.get("VALKYRIE"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        //Astraea
        encoder = new ShapeEncoder("eee"
                , "ese",
                "eee");
        encoder.setKey('e', new CraftingObject(Items.jollyPinkRock(), 1));
        encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("NECRON_BLADE"), 1));
        shapedRecipe = new SkyblockShapedRecipe("astraea1", Items.SkyblockItems.get("ASTRAEA"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        encoder.setKey('s' ,new CraftingObject(Items.SkyblockItems.get("hyperion"), 1));
        shapedRecipe = new SkyblockShapedRecipe("astraea2", Items.SkyblockItems.get("ASTRAEA"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        encoder.setKey('s' ,new CraftingObject(Items.SkyblockItems.get("VALKYRIE"), 1));
        shapedRecipe = new SkyblockShapedRecipe("astraea3", Items.SkyblockItems.get("ASTRAEA"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        encoder.setKey('s' ,new CraftingObject(Items.SkyblockItems.get("SCYLLA"), 1));
        shapedRecipe = new SkyblockShapedRecipe("astraea4", Items.SkyblockItems.get("ASTRAEA"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        //Scylla
        encoder = new ShapeEncoder("eee"
                , "ese",
                "eee");
        encoder.setKey('e', new CraftingObject(Items.bigfootsLasso(), 1));
        encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("NECRON_BLADE"), 1));
        shapedRecipe = new SkyblockShapedRecipe("scylla1", Items.SkyblockItems.get("SCYLLA"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        encoder.setKey('s' ,new CraftingObject(Items.SkyblockItems.get("hyperion"), 1));
        shapedRecipe = new SkyblockShapedRecipe("scylla2", Items.SkyblockItems.get("SCYLLA"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        encoder.setKey('s' ,new CraftingObject(Items.SkyblockItems.get("VALKYRIE"), 1));
        shapedRecipe = new SkyblockShapedRecipe("scylla3", Items.SkyblockItems.get("SCYLLA"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        encoder.setKey('s' ,new CraftingObject(Items.SkyblockItems.get("ASTRAEA"), 1));
        shapedRecipe = new SkyblockShapedRecipe("scylla4", Items.SkyblockItems.get("SCYLLA"), 1);
        shapedRecipe.setRecipe(encoder.encode());
        recipes.add(shapedRecipe);

        SkyblockShapelessRecipe shapelessRecipe = new SkyblockShapelessRecipe("STUFFED_CHILI_PEPPER", Items.stuffedChiliPepper());
        shapelessRecipe.addIngredient(new CraftingObject(Items.chiliPepper(), 160));
        recipes.add( shapelessRecipe);

         shapedRecipe = new SkyblockShapedRecipe("Pain_Releaser", Items.SkyblockItems.get("PAIN_RELEASER"), 1);
         encoder = new ShapeEncoder("#&#", "&*&", "#&#");
         encoder.setKey('*', new CraftingObject(Items.reaperPepper(), 1));
         encoder.setKey('&', new CraftingObject(Items.stuffedChiliPepper(), 5));
         encoder.setKey('#', new CraftingObject(Items.SkyblockItems.get("PERFECT_AMETHYST_GEM"), 1));
         shapedRecipe.setRecipe(encoder.encode());
         recipes.add(shapedRecipe);

         shapedRecipe = new SkyblockShapedRecipe("Chad_Stick", Items.SkyblockItems.get("CHAD_STICK"), 1);
         encoder = new ShapeEncoder(
                 "#*#",
                 "*&*",
                 "#+#");
         encoder.setKey('*', new CraftingObject(Items.reaperPepper(), 1));
         encoder.setKey('&', new CraftingObject(Items.SkyblockItems.get("PAIN_RELEASER"), 1));
         encoder.setKey('#', new CraftingObject(Items.SkyblockItems.get("PERFECT_AMETHYST_GEM"), 1));
         encoder.setKey('+', new CraftingObject(Items.enchantedBlazeRod(), 64));
         shapedRecipe.setRecipe(encoder.encode());
         recipes.add(shapedRecipe);




        Iterator<Recipe> iterator = Bukkit.recipeIterator();
        while (iterator.hasNext()){
            Recipe recipe = iterator.next();
            if(recipe instanceof ShapedRecipe){
                ShapedRecipe sh = (ShapedRecipe) recipe;
				ArrayList<CraftingObject> objects1 = new ArrayList<>();
                int layer = -1;
                for(String str : sh.getShape()){
                    if(sh.getIngredientMap() == null)
                        continue;
                    layer++;
                    int i = -1;
                    for (char c : str.toCharArray()){
                        Character character = c;
                        if(c == ' ')
                            objects1.add(new CraftingObject(null, 1));
                        else{
                            if(sh.getIngredientMap().get(character) == null)
                                objects1.add(new CraftingObject(null, 1));
                            else
                                objects1.add(new CraftingObject(Items.SkyblockItems.get(sh.getIngredientMap().get(character).getType().toString()), 1));
                        }


                        i++;
                    }
                    if(i != 2){
                        while (i != 2){
                            i++;
                            objects1.add(new CraftingObject(null, 1));
                        }
                    }


                }
                if(layer != 2){
                    while (layer != 2){
                        layer++;
                        objects1.add(new CraftingObject(null, 1));objects1.add(new CraftingObject(null, 1));objects1.add(new CraftingObject(null, 1));
                    }
                }

                shapedRecipe = new SkyblockShapedRecipe(sh.getKey().getKey(),Items.SkyblockItems.get(sh.getResult().getType().toString()), sh.getResult().getAmount());
                shapedRecipe.setRecipe(objects1);
                recipes.add(shapedRecipe);


            }
            if(recipe instanceof ShapelessRecipe){
                ShapelessRecipe shapelessRecipee = (ShapelessRecipe) recipe;
                SkyblockShapelessRecipe newShapeles = new SkyblockShapelessRecipe(shapelessRecipee.getKey().getKey(),Items.SkyblockItems.get(shapelessRecipee.getResult().getType().toString()));
                for (ItemStack item : shapelessRecipee.getIngredientList())
                    newShapeles.addIngredient(new CraftingObject(Items.SkyblockItems.get(item.getType().toString()), 1));
                recipes.add(newShapeles);
            }
        }



    }






}
