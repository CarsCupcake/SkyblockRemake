package me.CarsCupcake.SkyblockRemake.NPC.Questing;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Text;

import java.util.HashMap;
import java.util.UUID;

public class Selection {
    public static final HashMap<SkyblockPlayer, Selection> selections = new HashMap<>();
    private final HashMap<UUID, QuestOption> options = new HashMap<>();
    private final SkyblockPlayer player;
    public Selection(QuestOption[] options, SkyblockPlayer player){
        assert options != null && player != null;
        selections.put(player, this);
        this.player = player;
        TextComponent text = new TextComponent("§eSelect: ");
        text.setColor(ChatColor.YELLOW);

        for (QuestOption option : options){
            UUID id = UUID.randomUUID();
            this.options.put(id, option);
            BaseComponent component = new TextComponent(option.toString() + " ");
            component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quest " + id));
            component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(option.hoverText())));
            text.addExtra(component);
            text.setColor(ChatColor.GREEN);
        }
        player.spigot().sendMessage(text);
    }
    public void select(UUID id){
        Main.getDebug().debug("Quest Option selected: " + id, false);
        if(id == null) return;
        if(!options.containsKey(id)) return;
        selections.remove(player);
        options.get(id).select();
    }
}
