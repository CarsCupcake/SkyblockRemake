package me.CarsCupcake.SkyblockRemake.Crafting;


import java.util.ArrayList;
import java.util.HashMap;

public class ShapeEncoder {
    private final String line1;
    private final String line2;
    private final String line3;
    private  final HashMap<Character , CraftingObject> keys = new HashMap<>();

    public ShapeEncoder(String line1,String line2,String line3){


        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        if(line1.length() != 3 || line2.length() != 3 || line3.length() != 3)
            throw new StringIndexOutOfBoundsException("Each crafting line has to be 3 characters long");
    }
    public void setKey(char c, CraftingObject craftingObject){
        keys.put(c,craftingObject);
    }
    public ArrayList<CraftingObject> encode(){
        ArrayList<CraftingObject> objs = new ArrayList<>();
        for(char c : line1.toCharArray()){
            Character character = c;
            if(c == ' ' || !keys.containsKey(character)){

                objs.add(new CraftingObject(null,0));
            }else {
                objs.add(keys.get(character));
            }
        }
        for(char c : line2.toCharArray()){
            Character character = c;
            if(c == ' ' || !keys.containsKey(character)){

                objs.add(new CraftingObject(null,0));
            }else {
                objs.add(keys.get(character));
            }
        }
        for(char c : line3.toCharArray()){
            Character character = c;
            if(c == ' ' || !keys.containsKey(character)){

                objs.add(new CraftingObject(null,0));
            }else {
                objs.add(keys.get(character));
            }
        }

        if (objs.size() != 9)
            throw new RuntimeException("Failed to encode crafting recipe");

        return objs;
    }
}
