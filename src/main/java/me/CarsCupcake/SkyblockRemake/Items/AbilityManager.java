package me.CarsCupcake.SkyblockRemake.Items;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.SkyblockDamagePlayerToEntityExecuteEvent;
import me.CarsCupcake.SkyblockRemake.utils.ReflectionUtils;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.AdditionalManaCosts;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.scheduler.BukkitRunnable;

public interface AbilityManager<T extends Event> {

    boolean triggerAbility(T event);

    HashMap<SkyblockPlayer, HashMap<String, AdditionalManaCosts>> additionalMana = new HashMap<>();

    static void abilityTrigger(PlayerInteractEvent event) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());

        boolean isSneaking = player.isSneaking();

        if(!player.getItemInHand().hasItemMeta())
            return;

        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", player.getItemInHand(), PersistentDataType.STRING));

        if (manager == null) return;

        if (manager.getAbilities().isEmpty()) return;

        List<Ability> abilities = new ArrayList<>();

        boolean hasSneak = false;

        for (Ability ability : manager.getAbilities())
            if (!ability.getType().isOther()) {
                abilities.add(ability);
                if (ability.getType().isSneak()) hasSneak = true;
            }

        if (abilities.isEmpty()) return;

        for (Ability ability : abilities) {
            if (ability.getType().toAction().contains(event.getAction())) {
                if (hasSneak && isSneaking && ability.getType().isSneak()) {
                    executeAbility(ability, player, manager, event);
                } else {
                    if (ability.getType().isSneak() && !isSneaking) continue;
                    if (hasSneak && isSneaking && !ability.getType().isSneak()) continue;
                    executeAbility(ability, player, manager, event);
                }
            }
        }
    }

    static void executeAbility(Ability ability, SkyblockPlayer player, ItemManager manager, PlayerInteractEvent event) {
        Action action = event.getAction();
        if (!precheck(ability, player, manager, action)) return;
        try {
            ability.getAbilityManager().getClass().newInstance().triggerAbility(event);
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
        }
    }

    static void executeAbility(Ability ability, SkyblockPlayer player, ItemManager manager, EntityDamageByEntityEvent event) {
        Action action = Action.PHYSICAL;
        if (!precheck(ability, player, manager, action)) return;
        try {
            ability.getAbilityManager().getClass().newInstance().triggerAbility(event);
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
        }
    }

    static <T extends Event> void executeAbility(Ability ability, SkyblockPlayer player, ItemManager manager, T event) {
        Action action = Action.PHYSICAL;
        if (!precheck(ability, player, manager, action)) return;
        try {
            ability.getAbilityManager().getClass().newInstance().triggerAbility(event);
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
        }
    }

    static boolean precheck(Ability ability, SkyblockPlayer player, ItemManager manager, Action action) {
        double manacost = (ability.isPersentage()) ? Main.playermanacalc(player) * ability.getPersentage() : ability.getManacost();
        if (additionalMana.get(player).containsKey(manager.itemID)) {
            manacost += additionalMana.get(player).get(manager.itemID).amount;
        }
        AbilityPreExecuteEvent executeEvent = new AbilityPreExecuteEvent(ability.getAbilityManager(), (int) manacost, player, action);
        Bukkit.getPluginManager().callEvent(executeEvent);
        if (executeEvent.isCancelled()) return false;
        manacost = executeEvent.getPayedMana();
        if (player.currmana < manacost) {
            player.setTempDefenceString("§c§lNOT ENOUGHT MANA");
            return false;
        }
        if (!allowToFire(player, ability.getAbilityManager().getClass(), AbilityType.LeftOrRightClick)) {
            player.sendMessage("§cOn Coolown!");
            return false;
        }
        if (ability.getCooldown() > 0)
            startCooldown(player, ability.getAbilityManager().getClass(), ability.getCooldown() * 20L, ability.getType());
        player.setMana((int) (player.currmana - manacost));
        Main.updatebar(player);
        if (manacost > 0) player.setTempDefenceString("§b-" + manacost + " Mana (§6" + ability.getName() + "§b)");
        return true;
    }

    static void abilityTrigger(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player p)) return;
        if (event.getEntity() instanceof Player) return;

        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);

        if(!player.getItemInHand().hasItemMeta())
            return;

        boolean isSneaking = player.isSneaking();

        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", player.getItemInHand(), PersistentDataType.STRING));

        if (manager == null) return;

        if (manager.getAbilities().isEmpty()) return;

        List<Ability> abilities = new ArrayList<>();

        boolean hasSneak = false;

        for (Ability ability : manager.getAbilities())
            if (ability.getType() == AbilityType.EntityHit) {
                abilities.add(ability);
            }

        if (abilities.isEmpty()) return;

        for (Ability ability : abilities) {
            executeAbility(ability, player, manager, event);
        }
    }

    @SuppressWarnings("deprecation")
    static void abilityTrigger(DamagePrepairEvent event) {
        SkyblockPlayer player = event.getPlayer();

        if(!player.getItemInHand().hasItemMeta())
            return;

        boolean isSneaking = player.isSneaking();

        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", player.getItemInHand(), PersistentDataType.STRING));

        if (manager == null) return;

        if (manager.getAbilities().isEmpty()) return;

        List<Ability> abilities = new ArrayList<>();

        boolean hasSneak = false;

        for (Ability ability : manager.getAbilities())
            if (ability.getType() == AbilityType.SkyblockPreHit) {
                abilities.add(ability);
            }

        if (abilities.isEmpty()) return;

        for (Ability ability : abilities) {
            executeAbility(ability, player, manager, event);
        }

    }

    @SuppressWarnings("deprecation")
    static void abilityTrigger(SkyblockDamagePlayerToEntityExecuteEvent event) {
        SkyblockPlayer player = event.getPlayer();

        if(!player.getItemInHand().hasItemMeta())
            return;

        boolean isSneaking = player.isSneaking();

        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", player.getItemInHand(), PersistentDataType.STRING));

        if (manager == null) return;

        if (manager.getAbilities().isEmpty()) return;

        List<Ability> abilities = new ArrayList<>();

        boolean hasSneak = false;

        for (Ability ability : manager.getAbilities())
            if (ability.getType() == AbilityType.AfterHit) {
                abilities.add(ability);
            }

        if (abilities.isEmpty()) return;

        for (Ability ability : abilities) {
            executeAbility(ability, player, manager, event);
        }

    }


    static void startCooldown(SkyblockPlayer player, Class<? extends AbilityManager> abilityManagerClass, long time, AbilityType type) {
        Bundle<Class<? extends AbilityManager>, AbilityType> b = new Bundle<>(abilityManagerClass, type);
        player.addCooldown(b);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.removeCooldown(b);
            }
        }.runTaskLater(Main.getMain(), time);

    }

    static boolean allowToFire(SkyblockPlayer player, final Class<? extends AbilityManager> ability, AbilityType type) {
        boolean isIn = false;
        for (Bundle<Class<? extends AbilityManager>, AbilityType> b : player.getCooldowns()) {
            if (b.getFirst().equals(ability) && b.getLast().equals(type)) {
                isIn = true;
            }
        }
        return !isIn;

    }

}
