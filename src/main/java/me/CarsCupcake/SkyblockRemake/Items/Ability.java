package me.CarsCupcake.SkyblockRemake.Items;

import lombok.Getter;
import org.bukkit.event.Event;


public class Ability {
    @Getter
    private final AbilityManager<? extends Event> abilityManager;
    @Getter
    private final String name;
    @Getter
    private final AbilityType type;
    @Getter
    private final AbilityLore lore;
    @Getter
    private final int manacost;
    @Getter
    private final int cooldown;
    public Ability(AbilityManager<? extends Event> abilityManager, String name, AbilityType type, AbilityLore lore, int manacost, int cooldown){
        this.abilityManager = abilityManager;
        this.name = name;
        this.type = type;
        this.lore = lore;
        this.manacost = manacost;
        this.cooldown = cooldown;
    }
    @Getter
    private boolean isPersentage = false;
    @Getter
    private double persentage = 1;
    public void setPersentage(double d){
        isPersentage = true;
        persentage = d;
    }
}
