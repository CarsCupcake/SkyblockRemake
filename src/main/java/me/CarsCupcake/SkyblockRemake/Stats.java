package me.CarsCupcake.SkyblockRemake;

import org.bukkit.NamespacedKey;

public enum Stats {
    Health,
    Defense,
    Inteligence,
    Speed,
    Strength,
    CritDamage,
    CritChance,
    AbilityDamage,
    Ferocity,
    MagicFind,
    MiningSpeed,
    MiningFortune,
    Pristine,
    AttackSpeed,
    TrueDefense,
    SeaCreatureChance,
    FishingSpeed;
    public String getDataName(){
        switch (this){

            case Health -> {
                return "health";
            }
            case Defense -> {
                return "def";
            }
            case Inteligence -> {
                return "mana";
            }
            case Speed -> {
                return "speed";
            }
            case Strength -> {
                return "strength";
            }
            case CritDamage -> {
                return "cd";
            }
            case CritChance -> {
                return "cc";
            }
            case AbilityDamage -> {
                return "abilitydamage";
            }
            case Ferocity -> {
                return "ferocity";
            }
            case MagicFind -> {
                return "magicfind";
            }
            case MiningSpeed -> {
                return "miningspeed";
            }
            case MiningFortune -> {
                return "miningfortune";
            }
            case Pristine -> {
                return "pristine";
            }
            case AttackSpeed -> {
                return "as";
            }
            case TrueDefense -> {
                return "truedefense";
            }
            case SeaCreatureChance -> {
                return "seacreaturechance";
            }
            case FishingSpeed -> {
                return "fishingspeed";
            }
        }
        return null;

    }
    public NamespacedKey getKey(){
        return new NamespacedKey(Main.getMain(), getDataName());
    }
    public static Stats getFromDataName(String data){
        switch (data){
            case "health" ->{
                return Health;
            }
            case "def"->{
                return Defense;
            }
            case "mana" ->{
                return Inteligence;
            }
            case "speed" ->{
                return Speed;
            }
            case "strength" ->{
                return Strength;
            }
            case "cd" ->{
                return CritDamage;
            }
            case "cc" ->{
                return CritChance;
            }
            case "abilitydamage" ->{
                return AbilityDamage;
            }
            case "ferocity" ->{
                return Ferocity;
            }
            case "magicfind" ->{
                return MagicFind;
            }
            case "miningspeed" ->{
                return MiningSpeed;
            }
            case "miningfortune" ->{
                return MiningFortune;
            }
            case "pristine" ->{
                return Pristine;
            }
            case "as" ->{
                return AttackSpeed;
            }
            case "truedefense" ->{
                return TrueDefense;
            }
            case "seacreaturechance" ->{
                return SeaCreatureChance;
            }
            case "fishingspeed" ->{
                return FishingSpeed;
            }
            default -> throw new IndexOutOfBoundsException("There is no stat with the id: " + data);
        }
    }
}
