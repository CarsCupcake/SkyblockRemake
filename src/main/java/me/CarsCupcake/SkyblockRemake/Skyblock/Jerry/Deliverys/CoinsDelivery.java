package me.CarsCupcake.SkyblockRemake.Skyblock.Jerry.Deliverys;

import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.Jerry.IDelivery;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CoinsDelivery implements IDelivery {
    private final SkyblockPlayer player;
    private final double coins;
    private int filevar = -1;

    public CoinsDelivery(SkyblockPlayer player, double coins){
        this.coins = coins;
        this.player = player;
    }
    public CoinsDelivery(){
        player = null;
        coins = 0;
    }

    @Override
    public ItemStack getShowItem() {
        return new ItemBuilder(Material.PLAYER_HEAD)
                .setHead("https://textures.minecraft.net/texture/c9b77999fed3a2758bfeaf0793e52283817bea64044bf43ef29433f954bb52f6")
                .setName("§6" + Tools.addDigits(coins) + " coins")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                .addLoreRow("§7Here, some coins for you :)")
                .addLoreRow(" ")
                .addLoreRow("§eClick to collect")
                .build();
    }

    @Override
    public void claim() {
        player.setCoins(player.coins + coins);
        IDelivery.config.reload();
        IDelivery.config.get().set(player.getUniqueId() + ".coins." + filevar, null);
        IDelivery.config.save();
        IDelivery.config.reload();
    }

    public void setFileVar(int i){
        filevar = i;
    }

    @Override
    public void save() {
        IDelivery.config.reload();
        CustomConfig file = IDelivery.config;
        int var = 1;
        if (file.get().getConfigurationSection(player.getUniqueId() + ".coins") != null && file.get().contains(player.getUniqueId() + ".coins"))
            var = file.get().getConfigurationSection(player.getUniqueId() + ".coins").getKeys(false).size() + 1;
        file.get().set(player.getUniqueId() + ".coins." + var +  ".amount", coins);
        filevar = var;
        IDelivery.config.save();
        IDelivery.config.reload();
    }

    @Override
    public @NotNull ArrayList<IDelivery> getDeliverys(SkyblockPlayer player) {
        ArrayList<IDelivery> deliveries = new ArrayList<>();
        IDelivery.config.reload();
        CustomConfig file = IDelivery.config;
        if(file.get().getConfigurationSection(player.getUniqueId() + ".coins") != null)
            file.get().getConfigurationSection(player.getUniqueId() + ".coins").getKeys(false).forEach(var -> {
                CoinsDelivery delivery = new CoinsDelivery(player, file.get().getInt(player.getUniqueId() + ".coins." + var + ".amount"));
                delivery.setFileVar(Integer.parseInt(var));
                deliveries.add(delivery);
            });
        return deliveries;
    }
}
