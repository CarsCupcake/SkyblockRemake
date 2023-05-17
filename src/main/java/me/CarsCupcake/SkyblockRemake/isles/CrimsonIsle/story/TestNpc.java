package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.story;

import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.DialogBuilder;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.QuestNpc;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.QuestStage;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.Quests;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Pair;
import me.CarsCupcake.SkyblockRemake.utils.Sound;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TestNpc extends QuestNpc {
    private static final DialogBuilder dialog = new DialogBuilder(new Sound(org.bukkit.Sound.ENTITY_VILLAGER_AMBIENT, 1, 1))
            .withTextPrefix("§e[CarsCupcake]§r: ").addText("Hey, whats up :)")
            .addResponse("Hi")
            .addText("Btw can i have a stick?")
            .giveItems(Items.SkyblockItems.get("STICK"), 1)
            .addText("Imma head out now, bye o/")
            .addText("Thanks btw");

    public TestNpc() {
        super(new Location(Bukkit.getWorld("world_nether"), -360.5, 80, -475.5), "CarsCupcake", new Pair<>("ewogICJ0aW1lc3RhbXAiIDogMTY4MzQ3OTgwMDQzNSwKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzQ3VwY2FrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yOTJhZDZmYTQzOGVhYzFhYjlkNDQ3MDE4YjFkZDYyZGM2NGFiMjJlYzE2NTQ1Y2Y2ZjBmNzdhNWU5OTc4Y2Q4IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0sCiAgICAiQ0FQRSIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjM0MGMwZTAzZGQyNGExMWIxNWE4YjMzYzJhN2U5ZTMyYWJiMjA1MWIyNDgxZDBiYTdkZWZkNjM1Y2E3YTkzMyIKICAgIH0KICB9Cn0=", "dc26WUkudKpaSKNWN/+rbj6VitHpPuCjT7LF8dzXhMXCAKfKyIeT+ZtWZPKQN2AQG2MjtEh3Lu2c7EZAf9jRG6VH0OCDxR8znH2gmpxMEg0ggoKfAVXZCeu1i6E9VbxyMP5KBpcqa15d2DgKVlb+RODO9sxBau5kKUJB70f2d9SAIwHEDW82WAaxhP8ZgJQN+n6r7UMwoBIf210RjKOFRRAFjPbTcb5Pp8/y00nxIRca1GqQBQnFrnKkaDdtQuSIQCl+SUvfkJD1wfpVoYcKtobF1edjM9ClBW4hnacbaUgBoYS/F+uC/zpD1qEA0Z06K2QGjstEotrBpd7+LltUEZwwaXPK0lWje1DlR2/1f2SO7HFv2pCq366/S3YDRo09jeXagLC5kvHX2PADX84ZVrTUu6421sW576ao+zYW28j/PbADMjVoI8sUoxtUdFImPvc6tpaO/ku2ThdoAhSF+LIRPHWq3U+W6uz4/TbrXS32yLtwJnsaosbWb49H2/B+KqfRCH9/gN2jJGSDHrlaV7xZ4Ir9wk8mlNFpx/nby2PxpprvJ3Ln6glAyDaPIK0B6iGaDNujeARj80k8Kc7vX9iQy07UqGPUv6rPDDmNkLqrxgZnrFBx7nRmkK1VwDHXPDqPlHHA7U19ZHha2CD07/Sp2GlzdUStfjriMkj7dVY="));
    }

    @Override
    public Location getLocation(SkyblockPlayer player) {
        if (QuestStage.getQuestStage(Quests.TestQuest, player) == 0)
            return new Location(Bukkit.getWorld("world_nether"), -360.5, 80, -475.5);
        else return new Location(Bukkit.getWorld("world_nether"), -360.5, 86, -495.5);
    }

    private static final Set<SkyblockPlayer> inAnimation = new HashSet<>();

    @Override
    public void onClick(SkyblockPlayer player) {
        if (inAnimation.contains(player)) return;
        if (QuestStage.getQuestStage(Quests.TestQuest, player) == 0)
            dialog.clone().onDialogEnd(() -> {
                inAnimation.add(player);
                walk(player, () -> {
                            inAnimation.remove(player);
                            player.sendMessage("§e[CarsCupcake]§r: Better here :D");
                            QuestStage.setQuestStage(Quests.TestQuest, player, 1);
                        },
                        new Location(Bukkit.getWorld("world_nether"), -353.5, 80, -475.5),
                        new Location(Bukkit.getWorld("world_nether"), -360.5, 80, -475.5),
                        new Location(Bukkit.getWorld("world_nether"), -360.5, 86, -495.5)
                );
            }).build(player);
        else {
            new DialogBuilder(new Sound(org.bukkit.Sound.ENTITY_VILLAGER_AMBIENT, 1, 1)).withTextPrefix("§e[CarsCupcake]§r: ").addText("Hi!").addResponse("Hey").addText("Ho").build(player);
        }
    }

    @Override
    public boolean showToPlayer(SkyblockPlayer player) {
        return true;
    }
}
