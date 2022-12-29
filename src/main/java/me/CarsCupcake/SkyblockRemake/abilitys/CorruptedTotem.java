package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Items.AbilityPreExecuteEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

public class CorruptedTotem extends PreAbilityExecution implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        event.setCancelled(true);
        if(event.getClickedBlock().getLocation().add(0,1,0).getBlock().getType() != Material.AIR)
            return false;

        ItemStack item = new ItemStack(Material.WHITE_BANNER);
        BannerMeta bannerMeta = (BannerMeta) item.getItemMeta();
        bannerMeta.addPattern(new Pattern(DyeColor.BLACK, PatternType.BASE));
        bannerMeta.addPattern(new Pattern(DyeColor.MAGENTA, PatternType.CROSS));
        bannerMeta.addPattern(new Pattern(DyeColor.PURPLE, PatternType.CURLY_BORDER));
        bannerMeta.addPattern(new Pattern(DyeColor.PURPLE, PatternType.CIRCLE_MIDDLE));
        bannerMeta.addPattern(new Pattern(DyeColor.BLACK, PatternType.FLOWER));
        bannerMeta.addPattern(new Pattern(DyeColor.BLACK, PatternType.TRIANGLES_BOTTOM));
        bannerMeta.addPattern(new Pattern(DyeColor.BLACK, PatternType.TRIANGLES_TOP));

        item.setItemMeta(bannerMeta);
        Block block = event.getClickedBlock().getWorld().getBlockAt(event.getClickedBlock().getLocation().add(0,1,0));
        block.setType(Material.WHITE_BANNER, false);

        final BlockState state = block.getState();
        if(state instanceof Banner) {
            Banner banner = (Banner) block.getState();
            applyToBlock(banner, bannerMeta);

            org.bukkit.material.Banner material = (org.bukkit.material.Banner) banner.getData();
            material.setFacingDirection(BlockFace.SOUTH);
            banner.setData(material);
            banner.update(true);
            new Totem(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), block);
        return false;
    }
        return false;
    }
       private void applyToBlock(Banner block, BannerMeta meta) {
            block.setPatterns(meta.getPatterns());
        }


    @Override
    public void preEvent(AbilityPreExecuteEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK)
            event.setCancelled(true);
    }
}
