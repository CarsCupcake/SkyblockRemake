package me.CarsCupcake.SkyblockRemake.Entities;

import lombok.SneakyThrows;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.Loot;
import me.CarsCupcake.SkyblockRemake.utils.loot.LootTable;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.libs.org.apache.http.MethodNotSupportedException;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasicEntity extends SkyblockEntity implements MinionEntity {
    private static final HashMap<EntityType, LootTable> lootTables = new HashMap<>();
    private LivingEntity entity;
    private final int maxHealth;
    private final int damage;
    private Class<? extends LivingEntity> aClass;
    private LootTable lootTable;

    public BasicEntity(LivingEntity entity, int maxHealth) {
        super();
        lootTable = lootTables.getOrDefault(entity.getType(), new LootTable());
        health = maxHealth;
        this.entity = entity;
        this.maxHealth = maxHealth;
        AttributeInstance atr = entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        damage = (atr == null) ? 0 : (int) (atr.getBaseValue() * 5);
        SkyblockEntity.livingEntity.addEntity(entity, this);
        Main.updateentitystats(entity);

    }

    public BasicEntity(Class<? extends LivingEntity> entity, int maxHealth, int damage) {
        aClass = entity;
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.damage = damage;

    }

    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    @SneakyThrows
    public void spawn(Location loc) {
        if (aClass == null)
            throw new MethodNotSupportedException("Method is not avaidable!");
        entity = loc.getWorld().spawn(loc, aClass, entity1 -> entity1.setCustomNameVisible(true));
    }

    @Override
    @SuppressWarnings("deprecation")
    public String getName() {
        return entity.getType().getName();
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName(SkyblockEntity.getBaseName(this));
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }

    @Override
    public SkyblockEntity makeNew() {
        if (aClass != null)
            return new BasicEntity(aClass, maxHealth, damage);
        else
            return new BasicEntity(entity, maxHealth);
    }

    @Override
    public String getId() {
        if (aClass == null)
            return "N/A";
        return aClass + "";
    }

    public static void initAllLootTables() {
        for (EntityType type : EntityType.values()) {
            if (type.getEntityClass() == null) continue;
            if (!LivingEntity.class.isAssignableFrom(type.getEntityClass())) continue;
            LootTable lootTable = new LootTable();
            try {
                JSONObject file = (JSONObject) new JSONParser().parse(new InputStreamReader(Main.getMain().getResource("assets/loottable/" + type.getKey().getKey() + ".json")));
                Object o = file.get("pools");
                if (o instanceof JSONArray array) {
                    for (Object obj : array) {
                        if (!(obj instanceof JSONObject jO)) continue;
                        Object condition = jO.get("conditions");
                        if (condition == null) {
                            for (Object en : (JSONArray) jO.get("entries")) {
                                if (!(en instanceof JSONObject jOO)) continue;
                                if (!jOO.get("type").equals("minecraft:item")) continue;
                                if (jOO.get("functions") == null)
                                    lootTable.addLoot(new ItemLoot(Items.SkyblockItems.get(((String) jOO.get("name")).split(":")[1].toUpperCase()), 1, 1));
                                else
                                    for (Object fun : (JSONArray) jOO.get("functions")) {
                                        JSONObject functions = (JSONObject) fun;
                                        if (!functions.get("function").equals("minecraft:set_count")) continue;
                                        double min = 0, max = 0;
                                        Object c = functions.get("count");
                                        if (c instanceof JSONObject count) {
                                            min = (double) count.get("min");
                                            max = (double) count.get("max");
                                        } else if (c instanceof Double d) {
                                            min = d;
                                            max = d;
                                        }
                                        if (min == 0 && max == 0) continue;
                                        lootTable.addLoot(new ItemLoot(Items.SkyblockItems.get(((String) jOO.get("name")).split(":")[1].toUpperCase()), (int) min, (int) max));
                                    }
                            }

                        } else {
                            JSONArray con = (JSONArray) condition;
                            boolean b = false;
                            double c = 0;
                            for (Object cons : con)
                                if (cons instanceof JSONObject jOO) {
                                    Object ren = jOO.get("condition");
                                    if (ren instanceof String s && s.contains("random_chance")) {
                                        b = true;
                                        c = (double) jOO.get("chance");
                                        break;
                                    }
                                }
                            if (!b) continue;
                            List<Loot> loots = new ArrayList<>();
                            for (Object en : (JSONArray) jO.get("entries")) {
                                JSONObject entry = (JSONObject) en;
                                Object t = entry.get("type");
                                if (!(t instanceof String s && s.equals("minecraft:item"))) continue;
                                loots.add(new ItemLoot(Items.SkyblockItems.get(((String) entry.get("name")).split(":")[1].toUpperCase()), 1, 1));
                            }
                            for (Loot loot : loots)
                                lootTable.addLoot(loot, c, c <= 0.03);
                        }
                    }
                }
                lootTables.put(type, lootTable);
            } catch (Exception e) {
                System.err.println("Error while initing loottable from " + type);
                e.printStackTrace(System.err);
            }
        }
    }
}
