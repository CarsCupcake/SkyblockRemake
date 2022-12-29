package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class EntityLocationSelecter implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
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
