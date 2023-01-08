package me.CarsCupcake.SkyblockRemake.Slayer.Blaze.ItemAbility;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.HellionShieldSwapEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Set;

public class HelionSwapAbility implements Listener {
    private static final Set<String> zeroAbility = Set.of("MAWDUST_DAGGER", "BURSTMAW_DAGGER", "HEARTMAW_DAGGER");
    private static final Set<String> oneAbility = Set.of("FIREDUST_DAGGER", "BURSTFIRE_DAGGER", "HEARTFIRE_DAGGER");
    private static final HashMap<SkyblockPlayer, Bundle<BukkitTask, String>> abilitys = new HashMap<>();
    @EventHandler
    public void onSwap(HellionShieldSwapEvent event){

        if(abilitys.containsKey(event.getPlayer())){
            try {
                abilitys.get(event.getPlayer()).getFirst().cancel();
            }catch (Exception ignored){}
            String[] s = abilitys.get(event.getPlayer()).getLast().split("-");
            abilitys.remove(event.getPlayer());
            if(String.valueOf(event.getEntity().getEntityId()).equals(s[0])){
                if(s[1].equals("0")){
                    //Twilight (heal) ability
                    int healamount = 200 + (int)(Main.getPlayerStat(event.getPlayer(), Stats.Health) * 0.04d);
                    event.getPlayer().setHealth(healamount + event.getPlayer().currhealth, HealthChangeReason.Ability);
                }else if(s[1].equals("1")){
                    //Fire ticks ability
                }
            }
        }
        if(event.getEntity() == null)
            return;
        if(!ItemHandler.hasPDC("id", event.getPlayer().getItemInHand(), PersistentDataType.STRING))
            return;

        String id = ItemHandler.getPDC("id", event.getPlayer().getItemInHand(), PersistentDataType.STRING);

        if(zeroAbility.contains(id))
            addAbility(event.getPlayer(), event.getEntity() + "-0");
        else if(oneAbility.contains(id))
            addAbility(event.getPlayer(), event.getEntity() + "-1");
    }

    //Usage: "entityId-abilityId"
    public static void addAbility(SkyblockPlayer player, String id){
        if(abilitys.containsKey(player))
            try {
                abilitys.get(player).getFirst().cancel();
            }catch (Exception ignored){}
        abilitys.put(player, new Bundle<>(new BukkitRunnable() {
            @Override
            public void run() {
                abilitys.remove(player);
            }
        }.runTaskLater(Main.getMain(), 20*6), id));
    }
}
