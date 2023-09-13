package me.CarsCupcake.SkyblockRemake.utils.Inventories.Items;

import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemBuilder {
    private final Material material;
    private String name = "N/A";
    private final ArrayList<String> lore = new ArrayList<>();
    private final List<ItemFlag> flags = new ArrayList<>();
    private int count = 1;
    private boolean isHead;
    private String headURL;
    private Color color;
    private ArrayList<Pattern> bannerPatterns;
    private final HashMap<Enchantment, Integer> enchants = new HashMap<>();
    private boolean glint = false;
    private boolean headvalue = false;
    public ItemBuilder(Material material){
        this.material = material;
    }
    public ItemBuilder setName(String str){
        this.name = str;
        return this;
    }
    public ItemBuilder addLoreRow(String str){
        return setLoreRow(lore.size(), str);
    }
    public ItemBuilder setLoreRow(int i, String str){
        if(i == lore.size())
            lore.add(str);
        else
            lore.set(i,str);

        return this;
    }
    public ItemBuilder addAllLore(List<String> lore){
        for(String l : lore)
            addLoreRow(l);
        return this;
    }

    public ItemBuilder addAllLore(String... lore){
        for(String l : lore)
            addLoreRow(l);
        return this;
    }

    public ItemBuilder addAllLore(ChatColor base, String... lore){
        for(String l : lore)
            addLoreRow(base + l);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag flag){
        flags.add(flag);
        return this;
    }
    public ItemBuilder setAmount(int i){
        count = i;
        return this;
    }
    public ItemStack build(){
        ItemStack item = (isHead) ? ((headvalue) ? Tools.getCustomTexturedHeadFromSkullValue(headURL) : Tools.CustomHeadTexture(headURL) ): new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (!name.equals("N/A"))
            meta.setDisplayName(name);
        meta.setLore(lore);
        for(ItemFlag itemFlag : flags)
            meta.addItemFlags(itemFlag);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        if(glint)
            meta.addEnchant(SkyblockEnchants.ENCHANT_GLINT, 1, false);
        for (Enchantment enchantment : enchants.keySet()){
            meta.addEnchant(enchantment, enchants.get(enchantment), true);
        }
        item.setItemMeta(meta);
        if(color != null){
            LeatherArmorMeta lmeta = item.hasItemMeta() ? (LeatherArmorMeta) item.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
            lmeta.setColor(color);
            lmeta.addItemFlags(ItemFlag.HIDE_DYE);
            item.setItemMeta(lmeta);
        }
        if(bannerPatterns != null){

            BannerMeta lmeta = (BannerMeta) item.getItemMeta();
            lmeta.setPatterns(bannerPatterns);
            lmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            item.setItemMeta(lmeta);
        }
        item.setAmount(count);
        return item;
    }

    public ItemBuilder setHead(String url){
        isHead = true;
        headURL = url;
        return this;
    }
    public ItemBuilder headTextureAsValue() {
        headvalue = true;
        return this;
    }
    public ItemBuilder setLeatherColor(Color color){
        this.color = color;
        return this;
    }
    public ItemBuilder setBannerPatterns(ArrayList<Pattern> patterns){
        bannerPatterns = patterns;
        return this;
    }
    public ItemBuilder setGlint(boolean b){
        glint = b;
        return this;
    }
    public ItemBuilder addEnchant(Enchantment enchantment, int level){
        enchants.put(enchantment, level);
        return this;
    }
}
