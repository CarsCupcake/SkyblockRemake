package me.CarsCupcake.SkyblockRemake.abilities;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import org.bukkit.event.player.PlayerInteractEvent;

public class EntityLocationSelector implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        /*TextComponent text = new TextComponent("Wither Guard   §e§lSelect");
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/selectmob witherguard"));
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("Click to select!").create()));
        event.getPlayer().spigot().sendMessage(text);



        text = new TextComponent("Wither Miner   §e§lSelect");
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/selectmob witherminer"));
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("Click to select!").create()));
        event.getPlayer().spigot().sendMessage(text);*/

        event.getPlayer().sendMessage("§aList got reseted");
        EntityLocationSetter.locations.clear();

        return false;
    }


}
