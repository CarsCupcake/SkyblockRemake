package me.CarsCupcake.SkyblockRemake.utils.loot;

import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class LootTable {
    @Setter
    private boolean breakIfFound = false;
    @Setter
    private boolean fillupLootable = false;
    private boolean showRareDropMessage = false;
    private final Map<Loot, LootChance> lootPool = new HashMap<>();
    public LootTable(){}
    public LootTable(boolean breakIfFound, boolean fillupLootable) {
        this.breakIfFound = breakIfFound;
        this.fillupLootable = fillupLootable;
    }

    public LootTable showRareDropMessage() {
        showRareDropMessage = true;
        return this;
    }

    public LootTable addLoot(Loot loot, double chance, boolean magicFind) {
        lootPool.put(loot, new LootChance(chance, magicFind));
        return this;
    }

    public LootTable addLootFamily(List<Loot> loots, double chance, boolean magicFind) {
        lootPool.put(new LootFamily(loots), new LootChance(chance, magicFind));
        return this;
    }

    public LootTable addLoot(Loot loot) {
        addLoot(loot, 1, false);
        return this;
    }

    public LootTable addLoot(Loot loot, double chance) {
        addLoot(loot, chance, chance <= 0.3);
        return this;
    }

    public LootTable addSubLootTable(LootTable table, double chance, boolean magicFind) {
        addLoot(new LootableLoot(table), chance, magicFind);
        return this;
    }

    public LootTable addSubLootTable(LootTable table, double chance) {
        addSubLootTable(table, chance, false);
        return this;
    }

    public LootTable addSubLootTable(LootTable table) {
        addSubLootTable(table, 1, false);
        return this;
    }

    public List<Loot> use(boolean sendMessage, @Nullable SkyblockPlayer player) {
        double magicFind = (player == null) ? 0d : Main.getPlayerStat(player, Stats.MagicFind);
        List<Loot> loot = new ArrayList<>();
        if (fillupLootable) {
            HashMap<Loot, Double> ajusted = new HashMap<>();
            for (Loot l : lootPool.keySet()) ajusted.put(l, lootPool.get(l).newChance(magicFind));
            Loot l = Tools.getOneItemFromLootTable(ajusted);
            loot.add(l);
            if (sendMessage && player != null) {
                if (showRareDropMessage) {
                    player.sendMessage("§6§lRARE DROP! §r" + l.name() + ((lootPool.get(l).magicFind) ? " §b(+" + String.format("%.0f", magicFind) + "% Magic Find!)" : ""));
                } else {
                    LootChance chance = lootPool.get(l);
                    if (!(chance.chance > 0.3))
                        player.sendMessage(chanceString(chance.chance) + " DROP! §r" + l.name() + ((chance.magicFind) ? " §b(+" + String.format("%.0f", magicFind) + "% Magic Find!)" : ""));
                }
            }
            return loot;
        }

        for (Loot l : lootPool.keySet())
            if (lootPool.get(l).drop(magicFind)) {
                if (l instanceof LootableLoot ll) {
                    if (ll.loot == this) throw new IllegalArgumentException("lootable tryes to call itselve");
                    loot.addAll(ll.loot.use(sendMessage, player));
                } else {
                    loot.add((l instanceof LootFamily lf) ? Tools.getRandom(lf.loots) : l);
                    if (sendMessage && player != null) {
                        if (showRareDropMessage) {
                            player.sendMessage("§6RARE DROP! §r" + l.name() + ((lootPool.get(l).magicFind) ? " §b(+" + String.format("%.0f", magicFind) + "% Magic Find!)" : ""));
                        } else {
                            LootChance chance = lootPool.get(l);
                            if (chance.chance > 0.3) continue;
                            player.sendMessage(chanceString(chance.chance) + " DROP! §r" + l.name() + ((chance.magicFind) ? " §b(+" + String.format("%.0f", magicFind) + "% Magic Find!)" : ""));
                        }
                    }
                }
                if (breakIfFound) break;

            }
        return loot;
    }

    public String chanceString(double chance) {
        if (chance <= 0.0001) return "§d§lRNGSUS";
        if (chance <= 0.001) return "§6§lLEGENDARY";
        if (chance <= 0.01) return "§9§lRARE";
        if (chance <= 0.3) return "§a§lUNCOMMON";
        return "§r§lCOMMON";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("LootTable[");
        Iterator<Loot> lootIterator = lootPool.keySet().iterator();
        while (lootIterator.hasNext()) {
            Loot loot = lootIterator.next();
            builder.append("{").append(toString(loot))
                    .append(",").append(lootPool.get(loot)).append("}");
            if (lootIterator.hasNext()) builder.append(";");
        }
        return builder.toString();
    }

    public String toString(Loot loot) {
        if (loot instanceof LootFamily fam) {
            StringBuilder builder = new StringBuilder("{");
            Iterator<Loot> lootIterator = fam.loots.iterator();
            while (lootIterator.hasNext()) {
                Loot l = lootIterator.next();
                builder.append(toString(l));
                if (lootIterator.hasNext()) builder.append(",");
            }
            builder.append("}");
            return builder.toString();
        }
        if (loot instanceof LootableLoot ll) return ll.loot.toString();
        return loot.toString();
    }

    private record LootFamily(List<Loot> loots) implements Loot {

        @Override
        public void consume(SkyblockPlayer killer, Location dropLocation, boolean toPlayer) {
            throw new IllegalArgumentException("Not allowed");
        }

        @Override
        public String name() {
            throw new IllegalArgumentException("Not allowed");
        }
    }

    private record LootableLoot(LootTable loot) implements Loot {

        @Override
        public void consume(SkyblockPlayer killer, Location dropLocation, boolean toPlayer) {
            throw new IllegalArgumentException("Not allowed");
        }

        @Override
        public String name() {
            throw new IllegalArgumentException("Not allowed");
        }
    }

    private record LootChance(double chance, boolean magicFind) {
        double newChance(double magicFind) {
            return (this.magicFind) ? (chance * (1 + (magicFind / 100d))) : chance;
        }

        boolean drop(double magicFind) {
            return new Random().nextDouble() <= newChance(magicFind);
        }
    }
}
