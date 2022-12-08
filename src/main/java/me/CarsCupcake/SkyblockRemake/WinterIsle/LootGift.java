package me.CarsCupcake.SkyblockRemake.WinterIsle;

import me.CarsCupcake.SkyblockRemake.Items.ItemGenerator;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LootGift implements Listener {
    private static final HashMap<ArrayList<ArmorStand>, WinterItems.GiftData> data = new HashMap<>();

    @EventHandler
    public void executeAbility(PlayerInteractEntityEvent event) {


        HashMap<ItemGenerator, Double> lootTable = null;
        int giftType = 0;

        try {
            if (event.getPlayer().getItemInUse().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals("WHITE_GIFT"))
                lootTable = WinterItems.whiteGiftLootTable;
        }catch (Exception ignored){}
        if(lootTable == null) {
            if(event.getRightClicked() instanceof ArmorStand stand){
                for(ArrayList<ArmorStand> stands : data.keySet()){
                    if(stands.contains(stand)){
                        WinterItems.GiftData d = data.get(stands);
                        if(event.getPlayer().equals(d.getGifter())) {
                            d.cancle();
                            data.remove(stands);
                        }
                        else
                        if(event.getPlayer().equals(d.getReciver())) {
                            d.open();
                            data.remove(stands);
                        }
                        return;
                    }
                }
            }
            return;
        }
        String armorStandHelmet;
        switch (giftType){
            case 0 -> armorStandHelmet = "http://textures.minecraft.net/texture/a5c6944593820d13d7d47db2abcfcbf683bb74a07e1a982db9f32e0a8b5dc466";
            default -> armorStandHelmet = "";
        }
        event.getPlayer().getItemInUse().setAmount(event.getPlayer().getItemInUse().getAmount() - 1);

        if(event.getRightClicked() instanceof Player p2){
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
            SkyblockPlayer player2 = SkyblockPlayer.getSkyblockPlayer(p2);
            if(!player2.isOnGround())
                return;
            Location lo = player2.getLocation().setDirection(player.getLocation().clone().subtract(player2.getLocation()).toVector());
            lo.setPitch(0);
            lo = lo.add(lo.getDirection().multiply(1));
            ArmorStand text1 = lo.getWorld().spawn(lo, ArmorStand.class, s ->{
                s.setCustomName("§eForm: §f" + player.getName());
                s.setCustomNameVisible(true);
                s.setInvulnerable(true);
                s.setInvisible(true);
            });
            lo.subtract(0, 0.4, 0);
            ArmorStand text2 = lo.getWorld().spawn(lo, ArmorStand.class, s ->{
                s.setCustomName("§To: §f" + player2.getName());
                s.setCustomNameVisible(true);
                s.setInvulnerable(true);
                s.setInvisible(true);
            });
            lo.subtract(0, 1.35, 0);
            ArmorStand giftStand = lo.getWorld().spawn(lo, ArmorStand.class, s ->{
                s.getEquipment().setHelmet(Tools.CustomHeadTexture(armorStandHelmet));
                s.setCustomNameVisible(false);
                s.setInvulnerable(true);
                s.setInvisible(true);
            });
            data.put(new ArrayList<>(List.of(giftStand, text1, text2)), new WinterItems.GiftData(player, player2, lootTable, new ArrayList<>(List.of(giftStand, text1, text2)), giftType));

        }


    }

    @EventHandler
    public void pluginDisableEvent(PluginDisableEvent event){
        if(event.getPlugin().equals(Main.getMain()))
            data.values().forEach(WinterItems.GiftData::cancle);

    }


}

