package me.CarsCupcake.SkyblockRemake.Items.Pets;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.configs.ConfigFile;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Bukkit;

public class PetEquip {
    @Getter
    private final Pet pet;
    private final SkyblockPlayer player;
    private final int id;
    @Getter
    private int level;
    @Getter
    private double xp;

    public PetEquip(SkyblockPlayer player) {
        this.player = player;
        player.setPetEquip(this);
        this.pet = player.getPetFollowRunner().getPet();
        this.id = player.getPetFollowRunner().getSlot();
        for (PetAbility ability : pet.getPetAbilities()) {
            ability.start(player);
        }
        ConfigFile file = new ConfigFile(player, "pet", true);
        this.level = file.get().getInt(id + ".level", 1);
        this.xp = file.get().getDouble(id + ".currxp", 0d);
    }

    public void despawn() {
        player.setPetEquip(null);
        for (PetAbility petAbility : pet.getPetAbilities())
            petAbility.stop(player);
        ConfigFile file = new ConfigFile(player, "pet", true);
        file.get().set(id + "." + "level", level);
        file.get().set(id + "." + "currxp", xp);
        file.get().set(id + ".id", pet.itemID);
        file.get().set(id + ".equiped", false);
        file.save(false);
    }

    public double getStat(Stats stats) {
        return pet.getStat(stats, level);
    }

    public void addPetXP(double xp, Skills skill) {
        double result = xp;
        if (skill == Skills.Fishing || skill == Skills.Mining) result *= 1.50;

        if (!pet.petType.equals(skill)) {
            if (skill == Skills.Alchemy || skill == Skills.Enchanting) result /= 12;
            else result /= 3;


        }
        result = Tools.round(result, 1);

        switch (skill) {
            case Farming, Mining, Combat, Foraging, Fishing, Enchanting -> player.addSkillXp(xp * 0.25, Skills.Taming);
            case Alchemy -> player.addSkillXp(xp * 0.025, Skills.Taming);
        }

        addPetXP(result);
    }

    public void addPetXP(double xp) {
        if (level + 1 > pet.maxLevel) {
            this.xp += xp;
            return;
        }
        if (pet.getRequieredXp(level) <= this.xp + xp) {
            double upxp = this.xp + xp;
            while (upxp > 0) {

                if (upxp - pet.getRequieredXp(level) < 0) {
                    this.xp = upxp;
                    Main.updatebar(player);
                    player.getPetFollowRunner().updatePet();
                    player.sendMessage("§aYour " + pet.getRarity().getPrefix() + pet.name + " §alevelled up to level §9" + level);
                    return;
                }
                if (level + 1 >= pet.maxLevel) {
                    this.xp = upxp - pet.getRequieredXp(level);
                    this.level = pet.maxLevel;
                    Main.updatebar(player);
                    player.getPetFollowRunner().updatePet();
                    player.sendMessage("§aYour " + pet.getRarity().getPrefix() + pet.name + " §alevelled up to level §9" + pet.maxLevel);
                    Bukkit.getPluginManager().callEvent(new PetLevelUpEvent(player, pet, level));
                    return;

                }
                upxp -= pet.getRequieredXp(level);
                level++;
                Bukkit.getPluginManager().callEvent(new PetLevelUpEvent(player, pet, level));
                if (level == 100) level += 1;
            }
        } else {
            this.xp += xp;
        }
    }
}
