package me.CarsCupcake.SkyblockRemake.Items.Enchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants.*;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants.*;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants.*;
import me.CarsCupcake.SkyblockRemake.Items.farming.emchantment.Harvesting;
import me.CarsCupcake.SkyblockRemake.Items.farming.emchantment.Replenish;
import me.CarsCupcake.SkyblockRemake.Items.farming.emchantment.SugarRush;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;

public class SkyblockEnchants {
    public static Map<String, CustomEnchantment> registeredEnchants = new HashMap<>();
    public static ArrayList<String> ultEnchantIDs = new ArrayList<>();
    public static ArrayList<String> skyblockEnchantIds = new ArrayList<>();
    public static ArrayList<Enchantment> enchantments = new ArrayList<>();
    public static final enchant_glient ENCHANT_GLINT = new enchant_glient(NamespacedKey.minecraft("glint"));
    public static final Telikinesis TELIKINESIS = new Telikinesis(NamespacedKey.minecraft("telikinesis"));
    public static final UltimateWise ULTIMATE_WISE = new UltimateWise();
    public static final Wisdom WISDOM = new Wisdom();
    public static final LastStand LAST_STAND = new LastStand();
    public static final Inferno INFERNO = new Inferno();
    public static final FatalTempo FATAL_TEMPO = new FatalTempo();
    public static final Chimera CHIMERA = new Chimera();
    public static final SoulEater SOUL_EATER = new SoulEater();
    public static final Overload OVERLOAD = new Overload();
    public static final Vicious VICIOUS = new Vicious();
    public static final Duplex DUPLEX = new Duplex();
    public static final Replenish REPLENISH = new Replenish();
    public static final Harvesting HARVESTING = new Harvesting();
    public static final Syphon SYPHON = new Syphon();
    public static final LifeSteal LIFE_STEAL = new LifeSteal();
    public static final ManaSteal MANA_STEAL = new ManaSteal();
    public static final DragonTracer DRAGON_TRACER = new DragonTracer();
    public static final SugarRush SUGAR_RUSH = new SugarRush();
    public static final Sharpness SHARPNESS = new Sharpness();
    public static final BaneOfArthropods BANE_OF_ARTHROPODS = new BaneOfArthropods();
    public static final Cleave CLEAVE = new Cleave();
    public static final Critical CRITICAL = new Critical();
    public static final Cubism CUBISM = new Cubism();
    public static final DivineGift DIVINE_GIFT = new DivineGift();
    public static final DragonHunter DRAGON_HUNTER = new DragonHunter();
    public static final EnderSlayer ENDER_SLAYER = new EnderSlayer();
    public static final Execute EXECUTE = new Execute();
    public static final Prosecute PROSECUTE = new Prosecute();
    public static final Experience EXPERIENCE = new Experience();
    public static final FireAspect FIRE_ASPECT = new FireAspect();
    public static final FirstStrike FIRST_STRIKE = new FirstStrike();
    public static final TripleStrike TRIPLE_STRIKE = new TripleStrike();
    public static final GiantKiller GIANT_KILLER = new GiantKiller();
    public static final TitanKiller TITAN_KILLER = new TitanKiller();
    public static final Smite SMITE = new Smite();
    public static final Smoldering SMOLDERING = new Smoldering();
    public static final Thunderbolt THUNDERBOLT = new Thunderbolt();
    public static final Thunderlord THUNDERLORD = new Thunderlord();
    public static final Vampirism VAMPIRISM = new Vampirism();
    public static final Flame FLAME = new Flame();
    public static final Piercing PIERCING = new Piercing();
    public static final InfiniteQuiver INFINITE_QUIVER = new InfiniteQuiver();
    public static final Power POWER = new Power();
    public static final Snipe SNIPE = new Snipe();
    public static final BigBrain BIG_BRAIN = new BigBrain();
    public static final Efficiency EFFICIENCY = new Efficiency();
    public static final Growth GROWTH = new Growth();
    public static final Protection PROTECTION = new Protection();
    public static final Rejuvenate REJUVENATE = new Rejuvenate();
    public static final Thorns THORNS = new Thorns();
    public static final Reflection REFLECTION = new Reflection();
    public static final TrueProtection TRUE_PROTECTION = new TrueProtection();
    public static final SmartyPants SMARTY_PANTS = new SmartyPants();
    public static final Pristine PRISTINE = new Pristine();


    private static void initEvents() {
        registerEvent(new UltWiseListener());
        registerEvent(new WisdomListener());
        registerEvent(new LastStandListener());
        registerEvent(new InfernoListener());
        registerEvent(new FatalTempoListner());
        registerEvent(new ChimeraListener());
        registerEvent(new SoulEater());
        registerEvent(new NormalEnchantsListener());
        registerEvent(DRAGON_TRACER);
        registerEvent(OVERLOAD);
        registerEvent(DUPLEX);
        registerEvent(VICIOUS);
        registerEvent(REPLENISH);
        registerEvent(HARVESTING);
        registerEvent(SUGAR_RUSH);
    }

    private static void registerEvent(Listener listener) {
        Main.getMain().getServer().getPluginManager().registerEvents(listener, Main.getMain());
    }

    public static void register() {
        initEvents();
        for (Field field : SkyblockEnchants.class.getDeclaredFields()) {
            try {
                if (field.getType().getSuperclass() == CustomEnchantment.class || field.getType().getSuperclass() == UltimateEnchant.class) {
                    registerEnchantment((CustomEnchantment) field.get(null));
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public static void unregister() {
        for (Enchantment enchantment : enchantments)
            unregisterEnchant(enchantment);
        enchantments.clear();
    }

    public static void registerEnchantment(CustomEnchantment enchantment) {
        if (enchantment instanceof UltimateEnchant)
            ultEnchantIDs.add(enchantment.getKey().getKey().toLowerCase(Locale.ROOT));
        skyblockEnchantIds.add(enchantment.getKey().getKey().toLowerCase(Locale.ROOT));
        enchantments.add(enchantment);
        boolean registered = true;
        try {
            registeredEnchants.put(enchantment.getKey().getKey(), enchantment);
            Field f = Enchantment.class.getDeclaredField("byKey");
            f.setAccessible(true);
            Map<NamespacedKey, Enchantment> map = (Map<NamespacedKey, Enchantment>) f.get(null);
            map.put(enchantment.getKey(), enchantment);

            Field f2 = Enchantment.class.getDeclaredField("byName");
            f2.setAccessible(true);
            Map<String, Enchantment> map2 = (Map<String, Enchantment>) f2.get(null);
            map2.put(enchantment.getName(), enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace(System.err);
        }
        if (!registered) {
            System.out.println("an error occured from the enchantment");
        }
    }

	@SuppressWarnings("deprecation")
    public static void unregisterEnchant(Enchantment enchantment) {
        skyblockEnchantIds.remove(enchantment.getKey().getKey());

        try {
            Field f = Enchantment.class.getDeclaredField("byKey");
            f.setAccessible(true);
            Map<NamespacedKey, Enchantment> map = (Map<NamespacedKey, Enchantment>) f.get(null);
            map.remove(enchantment.getKey());

            Field f2 = Enchantment.class.getDeclaredField("byName");
            f2.setAccessible(true);
            Map<String, Enchantment> map2 = (Map<String, Enchantment>) f2.get(null);
            map2.remove(enchantment.getName());
        } catch (Exception e) {
            System.out.println("Something went wrong while unregistering the enchant: " + enchantment.getKey().getKey());
            e.printStackTrace(System.err);
        }
    }
}
