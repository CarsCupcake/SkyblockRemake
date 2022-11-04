package me.CarsCupcake.SkyblockRemake.Items;

import com.google.common.annotations.Beta;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.jetbrains.annotations.NotNull;

public class AbilityPreExecuteEvent extends Event implements Cancellable {
    private boolean isCancelled = false;
    private final AbilityManager ability;
    private final SkyblockPlayer player;
    private int mana;
    private final Action action;
    public AbilityPreExecuteEvent(@NotNull AbilityManager manager, int payedMana, SkyblockPlayer player, Action action){
        ability = manager;
        mana = payedMana;
        this.player = player;
        this.action = action;
    }
    @Beta
    public void setPayedMana(int i){
        mana = i;
    }
    public int getPayedMana(){
        return mana;
    }
    public AbilityManager getAbility(){
        return ability;
    }
    public SkyblockPlayer getPlayer(){
        return player;
    }
    public Action getAction(){
        return action;
    }

    private static HandlerList HANDLERS= new HandlerList();
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList(){
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }
}
