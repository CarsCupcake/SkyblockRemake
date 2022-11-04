package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityPreExecuteEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public abstract class PreAbilityExecution implements Listener {
    public abstract void preEvent(AbilityPreExecuteEvent event);

    @EventHandler
    public void toExecute(AbilityPreExecuteEvent event){
        if(event.getAbility() instanceof PreAbilityExecution)
            ((PreAbilityExecution) event.getAbility()).preEvent(event);
    }
}
