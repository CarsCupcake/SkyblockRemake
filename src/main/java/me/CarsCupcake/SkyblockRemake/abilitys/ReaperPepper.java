package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Configs.ExtraInformations;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.Sound;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ReaperPepper implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        int reaperPepperAmount = ExtraInformations.get().getInt(player.getUniqueId() + ".reaperpepper");
        if(!(reaperPepperAmount >= 5)){
            event.getItem().setAmount(event.getItem().getAmount() - 1);
            reaperPepperAmount++;
            ExtraInformations.get().set(player.getUniqueId() + ".reaperpepper", reaperPepperAmount);
            ExtraInformations.save();
            ExtraInformations.reload();
            player.setBaseStat(Stats.Health, 1 + player.basehealth);
            switch (reaperPepperAmount) {
                case 1 -> player.sendMessage("§aYou've eaten a reaper pepper, it's delicious!");
                case 2 -> player.sendMessage("§aYou've eaten a reaper pepper, it's uh, really hot.");
                case 3 -> player.sendMessage("§aYou've eaten a reaper pepper and begun regretting this decision.");
                case 4 -> player.sendMessage("§aYou've eaten a reaper pepper, WHERE IS THE MILK?");
                case 5 -> player.sendMessage("§aYou've eaten a reaper pepper, you feel death creeping up in your spine. Your whole life flashes by, is this what it means to be endgame?");
            }
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
        }else {
            player.sendMessage("§cYou already ate the maximum number of reaper peppers!");
            player.sendMessage("§cYou may want to visit a hospital §lASAP");
        }
        return false;
    }

}
