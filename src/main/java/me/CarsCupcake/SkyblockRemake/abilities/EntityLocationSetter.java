package me.CarsCupcake.SkyblockRemake.abilities;

import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;


public class EntityLocationSetter implements AbilityManager<PlayerInteractEvent> {
    @Setter
    private static Class<? extends SkyblockEntity> entity = null;
    public static final ArrayList<Location> locations = new ArrayList<>();
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        if(event.getClickedBlock() == null)
            return false;

       /* if(entity == null){
            event.getPlayer().sendMessage("§cNo Mob Selected");
        }else{
            if(event.getClickedBlock() == null){
                event.getPlayer().sendMessage("§cNo Block Selected");
                return false;
            }

            Location location = event.getClickedBlock().getLocation().add(0,1,0);

            TextComponent text = new TextComponent("§aPlaced mob at "+ location.getX() + " " + location.getY() + " " + location.getX()+"  §e§lUndo");
            text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, ""));
            text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("§aClick to undo! - §cWork in progress").create()));
            event.getPlayer().spigot().sendMessage(text);
        }*/
        Location l = new Location(event.getPlayer().getWorld(), event.getClickedBlock().getX() + 0.5, event.getClickedBlock().getY() + 1, event.getClickedBlock().getZ() + 0.5);
        if(locations.contains(l)){
            event.getPlayer().sendMessage("§cAlready set!");
            return false;
        }
        locations.add(new Location(event.getPlayer().getWorld(), event.getClickedBlock().getX() + 0.5, event.getClickedBlock().getY() + 1, event.getClickedBlock().getZ() + 0.5));
        StringBuilder str = new StringBuilder("{");
        int i = 0;
        for (Location location : locations){
            str.append("new Location(Main.getMain().getServer().getWorld(\"").append(location.getWorld().getName()).append("\"), ").append(location.getX()).append(", ").append(location.getY()).append(", ").append(location.getZ()).append(")");
            if(i == locations.size() - 1)
                str.append("}");
            else
                str.append(",");
            i++;
        }

        TextComponent textComponent = new TextComponent("§aLocation marked (with +1 on y) §e§lClick to copy");
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, str.toString()));
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aClick to copy!").create()));
        event.getPlayer().spigot().sendMessage(textComponent);


        return false;
    }

}
