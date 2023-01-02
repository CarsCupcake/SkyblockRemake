package me.CarsCupcake.SkyblockRemake;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;

public enum Stats {
    Health("health", '❤', ChatColor.RED, "Health"),
    Defense("def", '❈', ChatColor.GREEN, "Defense"),
    Inteligence("mana", '✎', ChatColor.AQUA, "Inteligence"),
    Speed("speed", '✦', ChatColor.WHITE, "Speed"),
    Strength("strength", '❁', ChatColor.RED, "Strength"),
    CritDamage("cd", '☠', ChatColor.BLUE, "Crit Damage"),
    CritChance("cc", '☣', ChatColor.BLUE, "Crit Chance"),
    AbilityDamage("abilitydamage", '๑', ChatColor.RED, "Ability Damage"),
    Ferocity("ferocity", '⫽', ChatColor.RED, "Ferocity"),
    MagicFind("magicfind", '✯', ChatColor.AQUA, "Magic Find"),
    MiningSpeed("miningspeed", '⸕', ChatColor.GOLD, "Mining Speed"),
    MiningFortune("miningfortune", '☘', ChatColor.GOLD, "Mining Fortune"),
    Pristine("pristine", '✧', ChatColor.DARK_PURPLE, "Pristine"),
    AttackSpeed("as", '⚔', ChatColor.YELLOW, "Attack Speed"),
    TrueDefense("truedefense", '❂', ChatColor.WHITE, "True Defense"),
    SeaCreatureChance("seacreaturechance", 'α', ChatColor.DARK_AQUA, "Sea Creature Chance"),
    FishingSpeed("fishingspeed", '☂', ChatColor.AQUA, "Fishing Speed"),
    SwingRange("swingrange", ' ', ChatColor.GOLD, "Swing Range");
    @Getter
    private final String dataName;
    @Getter
    private final char symbol;
    @Getter
    private final ChatColor color;
    @Getter
    private final String name;
    Stats(String dataName, char symbol, ChatColor color, String name){
        this.dataName = dataName;
        this.symbol = symbol;
        this.color = color;
        this.name = name;
    }
    public NamespacedKey getKey(){
        return new NamespacedKey(Main.getMain(), getDataName());
    }
    public static Stats getFromDataName(String data){
        for(Stats s : Stats.values())
            if(s.getDataName().equals(data))
                return s;
        throw new IndexOutOfBoundsException("There is no stat with the id: " + data);
    }
}
