package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ChadIncoming implements AbilityManager<PlayerInteractEvent> {
    private static final ArrayList<SkyblockPlayer> players = new ArrayList<>();

    @Override
    public boolean executeAbility(PlayerInteractEvent event) {

        if(!players.contains(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()))){
            players.add(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
            startTimer(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        }

        return false;
    }

    public static ArrayList<SkyblockPlayer> getPlayers() {
        return players;
    }
    public void startTimer(final SkyblockPlayer player) {

        player.sendMessage("§aChad Incoming is now active!");
        player.setHealth((player.currhealth + (Main.playerhealthcalc(player) * 30))*player.healingMulti, HealthChangeReason.Ability);
        Main.updatebar(player);
        new BukkitRunnable() {
            private int timer = 10;
            @Override
            public void run() {
                timer--;
                if(timer == 0) {
                    players.remove(player);
                    cancel();
                    player.sendMessage("§cChad Incoming expired");
                    return;
                }
                if(timer == 5){
                    player.sendMessage("§cChad Incoming expires in 5 seconds!");
                }
                if(timer <4)
                    player.sendMessage("§cChad Incoming expires in "+timer+" seconds!");

            }
        }.runTaskTimer(Main.getMain(), 20,20);
    }
}
