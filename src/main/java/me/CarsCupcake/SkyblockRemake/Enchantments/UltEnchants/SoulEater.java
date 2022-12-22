package me.CarsCupcake.SkyblockRemake.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
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

    @NotNull
    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack itemStack) {
        return false;
    }

    private static final HashMap<SkyblockPlayer, Double> lastEntityDamage = new HashMap<>();

    @EventHandler
    public void onDamage(SkyblockDamageEvent event){
        if(event.getEntity() == null)
            return;
        if(event.getPlayer() == null)
            return;
        if(event.getType() != SkyblockDamageEvent.DamageType.PlayerToEntity)
            return;
        if(event.getPlayer().getItemInHand() == null || event.getPlayer().getItemInHand().getItemMeta() == null)
            return;

        if(lastEntityDamage.containsKey(event.getPlayer())){
            event.getCalculator().damage += 2* event.getPlayer().getItemInHand().getItemMeta().getEnchantLevel(SkyblockEnchants.SOUL_EATER);
        }
        double damage;
        if(SkyblockEntity.livingEntity.containsKey(event.getEntity())){
            damage = (SkyblockEntity.livingEntity.get(event.getEntity()).getDamage());
        }else if(Main.entitydamage.containsKey(event.getEntity())){
            damage = Main.entitydamage.get(event.getEntity());
        }else
            damage = event.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();


        lastEntityDamage.put(event.getPlayer(), damage);

    }
}
