package me.CarsCupcake.SkyblockRemake.API.PlayerEvent;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BowShootEvent extends PlayerEvent{
    private static final HandlerList handlers = new HandlerList();
    @Getter
    private final ItemStack bow;
    @Getter
    private final List<Arrow> arrows;
    public BowShootEvent(SkyblockPlayer player, ItemStack bow, List<Arrow> arrows) {
        super(player);
        this.bow = bow;
        this.arrows = arrows;
    }
    public ItemManager getBowManager() {
        return ItemHandler.getItemManager(bow);
    }
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
}
