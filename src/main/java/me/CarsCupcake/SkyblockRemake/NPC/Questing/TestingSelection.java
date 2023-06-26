package me.CarsCupcake.SkyblockRemake.NPC.Questing;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TestingSelection implements ISelection {
    static final char[] numbers = {
            '①',
            '②',
            '③',
            '④',
            '⑤',
            '⑥',
            '⑦',
            '⑧',
            '⑨',
    };
    private final HashMap<UUID, QuestOption> options = new HashMap<>();
    private final SkyblockPlayer player;
    public TestingSelection(List<QuestOption> options, String description, SkyblockPlayer player){
        assert options != null && player != null;
        selections.put(player, this);
        this.player = player;
        TextComponent text = new TextComponent("§e" + description);
        text.setColor(ChatColor.YELLOW);
        player.spigot().sendMessage(text);
        int i = 0;
        for (QuestOption option : options){
            UUID id = UUID.randomUUID();
            this.options.put(id, option);
            BaseComponent component = new TextComponent("§b" + numbers[i] + "§e" + option.toString());
            component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quest " + id));
            component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(option.hoverText())));
            player.spigot().sendMessage(component);
            i++;
        }
    }
    @Override
    public void select(UUID id){
        Main.getDebug().debug("Quest Option selected: " + id, false);
        if(id == null) return;
        if(!options.containsKey(id)) return;
        selections.remove(player);
        options.get(id).select();
    }
}
