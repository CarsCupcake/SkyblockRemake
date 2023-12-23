package me.CarsCupcake.SkyblockRemake.Skyblock.major;


import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;
import org.bukkit.ChatColor;

@Getter
public enum Majors implements RunnableWithParam<Major> {
    Diana("Diana", ChatColor.LIGHT_PURPLE,
            new Perk("Pet XP Buff", "§7Gain §d35% §7more pet XP."),
            new Perk("Lucky!", "§7Gain §d25 " + Stats.PetLuck),
            new Perk("Mythological Ritual", "§7Maypr Diana will sell the", "Griffin pet, which lets you", "§7find §2Mythological Creatures", "§7and tons of §cunique", "§citems§7.")){
        @Override
        public void run(Major major) {
        }
    };
    private final String name;
    private final ChatColor color;
    private final Perk[] perks;
    Majors(String name, ChatColor color, Perk... perks) {
        this.name = name;
        this.color = color;
        this.perks = perks;
    }
}
