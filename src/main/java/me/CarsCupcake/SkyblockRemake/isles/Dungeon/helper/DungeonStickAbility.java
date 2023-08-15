package me.CarsCupcake.SkyblockRemake.isles.Dungeon.helper;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

public class DungeonStickAbility implements AbilityManager<PlayerInteractEvent> {
    public static final HashMap<UUID, EntityHolder> entrys = new HashMap<>();
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        if(!event.hasBlock()) return true;
        Location l = Tools.getAsLocation(event.getClickedBlock()).add(0, 1, 0);
        UUID id = UUID.randomUUID();
        entrys.put(id, new EntityHolder(l, DungeonStickSelect.entityType, DungeonStickSelect.isStared));
        event.getPlayer().spigot().sendMessage(new TextComponent("§aMarked Block at " + l.getX() + " " + l.getY() + " " + l.getY() + " "), Tools.makeClickableText("§eUNDO ", "§eUndo the last step", ClickEvent.Action.RUN_COMMAND, "/drhelp undo " + id),
                Tools.makeClickableText("§cCLEAR ", "§cClears the entrys \n§c§l!  WARNING  !\n§cThis cannot be undone!", ClickEvent.Action.RUN_COMMAND, "/drhelp clear"));
        return true;
    }
    public enum EntityType {
        Melee,
        Arch,
        MiniBoss
    }
    public record EntityHolder(Location location, EntityType type, boolean isStared) {

    }
}
