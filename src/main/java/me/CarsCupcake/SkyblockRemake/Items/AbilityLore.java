package me.CarsCupcake.SkyblockRemake.Items;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AbilityLore {
    public enum LoreFlags{
        AsShortInteger
    }
    private final List<String> baseList;
    private final String placeholder;
    private final Bundle<Double,Double> infos;
    private final Set<LoreFlags> flags = new HashSet<>();
    private final HashMap<String, UpdateFlag> placeholders = new HashMap<>();

    //Bundle: BaseAbilityDamage - AbilityScaling
    public AbilityLore(List<String> base, String placeholder, Bundle<Double, Double> infos){
        baseList = base;
        this.placeholder = placeholder;
        this.infos = infos;
    }
    public AbilityLore(List<String> base){
        this(base, null, null);
    }
    public ArrayList<String> makeLore(@Nullable SkyblockPlayer player, ItemStack item){
        ArrayList<String> lore = new ArrayList<>();
        double damage = 0;
        if(infos != null)
        {
            if (player == null)
                damage = infos.getFirst();
            else {
                Calculator calc = new Calculator();
                calc.setMagic("temp", infos.getLast());
                calc.playerToEntityMagicDamage(player, null, infos.getFirst());
                damage = calc.damage;
            }
        }

        for(String str : baseList){
            String newString;

            if(placeholder != null && str.contains(placeholder))
                newString = str.replace(placeholder,(flags.contains(LoreFlags.AsShortInteger)) ? Tools.toShortNumber(damage) : String.format("%.0f", damage));
            else
                newString = str;
            for(String placeholder : placeholders.keySet()){
                if(str.contains(placeholder))
                    newString = newString.replace(placeholder, placeholders.get(placeholder).getReplaceValue(player, item ));
            }

            lore.add(newString);
        }

        return lore;
    }
    public void addFlag(LoreFlags flag){
        flags.add(flag);
    }
    public void addPlaceholder(String plshldr, UpdateFlag flag){
        placeholders.put(plshldr, flag);
    }
}
