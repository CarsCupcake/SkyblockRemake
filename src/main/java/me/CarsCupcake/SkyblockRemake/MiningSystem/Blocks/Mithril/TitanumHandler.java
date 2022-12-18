package me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Mithril;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;

public class TitanumHandler {
    @Getter
    private static final HashMap<Block, TitanumHandler> handlers = new HashMap<>();
    private final Block block;
    private int time = 0;
    private final Material base;
    public TitanumHandler(Block b, Material base){
        block = b;
        handlers.put(b, this);
        this.base = base;
    }
    public void reset(){
        block.setType(base);
    }
    public void blockBreak(){
        if(time > 8) {
            block.setType(base);
            handlers.remove(block);
        }else {
            block.setType(Material.BEDROCK);
            time += 112;
            handlers.remove(block);
        }
    }
    public void tick(){
        time++;
        if(time == 120) {
            block.setType(base);
            handlers.remove(block);
        }
    }
}
