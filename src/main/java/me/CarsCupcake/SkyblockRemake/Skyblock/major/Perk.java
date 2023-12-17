package me.CarsCupcake.SkyblockRemake.Skyblock.major;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import org.bukkit.ChatColor;

import java.util.List;

public record Perk(String name, AbilityLore lore) {
    public Perk(String name, String... lore) {
        this(name, new AbilityLore(lore));
    }
    public void append(List<String> lore, ChatColor color) {
        lore.add("§7  ");
        lore.add(color + name);
        lore.addAll(this.lore.makeLore(null, null));
    }
}
