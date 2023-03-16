package me.CarsCupcake.SkyblockRemake.abilitys;

import de.carscupcake.gameoflegends.utils.Inventorys.MultipleGui;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Items.AbilityPreExecuteEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Wand extends PreAbilityExecution implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        Spell spell = Spell.spells.get(ItemHandler.getOrDefaultPDC("activespell", player.getItemInHand(), PersistentDataType.STRING, ""));
        spell.shoot(player);
        return false;
    }

    @Override
    public void preEvent(AbilityPreExecuteEvent event) {
        Spell spell = Spell.spells.get(ItemHandler.getOrDefaultPDC("activespell", event.getPlayer().getItemInHand(), PersistentDataType.STRING, ""));
        if (spell == null) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§cYou have no spell selected!");
        }
    }

    public static class SpellSelect implements AbilityManager<PlayerInteractEvent> {
        static List<Inventory> inventories = new ArrayList<>();

        @Override
        public boolean triggerAbility(PlayerInteractEvent event) {
            MultipleGui gui = new MultipleGui(inventories, 53, 45);

            Inventory last = inventories.get(inventories.size() - 1);
            int rows = last.getSize() / 9;
            if (rows != 6)
                gui.addSpecialSlotSwap(inventories.size() - 1, (Math.min(rows, 6) - 1) * 9, (Math.min(rows, 6) * 9) - 1);

            gui.setGeneralAction((slot, actionType, type) -> {
                if (actionType != GUI.GUIActions.Click) return;
                ItemStack item = gui.getInventory().getItem(slot);
                String id = ItemHandler.getOrDefaultPDC("activespell", item, PersistentDataType.STRING, "");
                if (Spell.spells.containsKey(id)) {
                    Spell spell = Spell.spells.get(id);
                    spell.select(event.getItem());
                    gui.closeInventory();
                    event.getPlayer().sendMessage("§aYou selected the " + spell.getId());
                }
            });
            gui.showGUI(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
            return false;
        }

        public static void buildInventorys() {
            int spells = Spell.spells.size();
            ArrayList<Spell> spellList = new ArrayList<>(Spell.spells.values());
            int slots;
            if (spells % 9 == 0) {
                slots = (spells / 9) + 1;
            } else slots = (spells / 9) + 2;
            int invs = 1 + (spells / 45);
            int total = 0;
            for (int is = 1; is <= invs; is++) {
                Inventory inv = new InventoryBuilder(Math.min(slots, 6), "Spells Page " + is).fill(TemplateItems.EmptySlot.getItem(), (Math.min(slots, 6) - 1) * 9, (Math.min(slots, 6) * 9) - 1).build();

                for (int i = 0; i < 45; i++) {
                    if (spells == total) break;
                    Spell spell = spellList.get(total);
                    ItemStack item = new ItemBuilder(Material.STICK).setName("§a" + spell.getId()).build();
                    ItemHandler.setPDC("activespell", item, PersistentDataType.STRING, spell.getId());
                    inv.setItem(i, item);

                    total++;
                }
                inventories.add(inv);

                slots -= 6;
            }
        }
    }

    public static abstract class Spell {
        protected static HashMap<String, Spell> spells = new HashMap<>();
        protected final int MAX_RANGE = 50;
        protected double SPEED = 1;
        private BukkitRunnable runnable;

        public void select(ItemStack item) {
            ItemHandler.setPDC("activespell", item, PersistentDataType.STRING, getId());
        }

        protected abstract String getId();

        protected abstract void hit(LivingEntity entity, SkyblockPlayer player, Location location);

        public void shoot(SkyblockPlayer shooter) {
            long steps = (long) (MAX_RANGE / SPEED);
            runnable = new BukkitRunnable() {
                long i = 0;
                Location location = shooter.getEyeLocation();
                final Vector dir = shooter.getLocation().getDirection().normalize().multiply(SPEED);

                @Override
                public void run() {
                    if (i >= steps) {
                        cancel();
                        remove();
                        return;
                    }
                    location = location.add(dir);
                    if (!location.getBlock().isPassable()) {
                        playWallHitEffect(location);
                        cancel();
                        remove();
                        return;
                    }
                    List<Entity> entities = location.getWorld().getNearbyEntities(location, SPEED, 0.5, SPEED).stream().filter(entity -> entity instanceof LivingEntity && !(entity instanceof Player) && !(entity instanceof ArmorStand)).toList();
                    if (!entities.isEmpty()) {
                        hit((LivingEntity) entities.get(0), shooter, location);
                        remove();
                        return;
                    }

                    playEffect(location);

                    i++;
                }
            };
            runnable.runTaskTimer(Main.getMain(), 0, 1);
        }

        public abstract void playEffect(Location location);

        public void playWallHitEffect(Location location) {
            location.getWorld().spawnParticle(Particle.FLAME, location, 1);
            location.getWorld().spawnParticle(Particle.FLASH, location, 1);
            location.getWorld().playSound(location, Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
        }

        public void remove() {
            try {
                runnable.cancel();
            } catch (Exception ignored) {
            }
        }

        public static void registerSpells(Spell spell) {
            spells.put(spell.getId(), spell);
        }
    }
}
