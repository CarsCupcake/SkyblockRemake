package me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Entities.BasicEntity;
import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SoulEater extends UltimateEnchant implements Listener {
    public SoulEater() {
        super(new NamespacedKey(Main.getMain(), "Soul_Eater"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Soul Eater";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    private static final HashMap<SkyblockPlayer, Double> lastEntityDamage = new HashMap<>();

    @EventHandler
    public void onDamage(SkyblockDamageEvent event) {
        if (event.getEntity() == null)
            return;
        if (event.getPlayer() == null)
            return;
        if (event.getType() != SkyblockDamageEvent.DamageType.PlayerToEntity)
            return;
        if (event.getPlayer().getItemInHand() == null || event.getPlayer().getItemInHand().getItemMeta() == null)
            return;
        if (lastEntityDamage.containsKey(event.getPlayer())) {
            event.getCalculator().damage += 2 * event.getPlayer().getItemInHand().getItemMeta().getEnchantLevel(SkyblockEnchants.SOUL_EATER);
        }
        double damage;
        if (!SkyblockEntity.livingEntity.exists(event.getEntity())) new BasicEntity(event.getEntity());
        damage = (SkyblockEntity.livingEntity.getSbEntity(event.getEntity()).getDamage());
        lastEntityDamage.put(event.getPlayer(), damage);

    }

    @Override
    public ItemType[] getAllowedTypes() {
        return Tools.combine(ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]), ItemType.Type.Bow.getTypeList().toArray(new ItemType[0]));
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("Your weapon gains §c%mult%x §7the", "§7Damage of the latest monster", "§7killed and applies it on", "§7your next hit.")
                .addPlaceholder("%mult%", (player, itemStack) -> String.valueOf(2 * ItemHandler.getEnchantmentLevel(this, itemStack)));
    }
}
