package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Furnace;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Random;

public class ArrowPointingPattern {
    public static ArrowPointingPattern currPattern;
    private final HashMap<Block, Bundle<Rotation, Type>> frames = new HashMap<>();
    //keywording stuff: s for start u for up r for right d for down l for left f for finish
    public ArrowPointingPattern(String... lines){
        if(currPattern != null)
            currPattern.remove();
        if(lines.length != 5)
            throw new ArrayIndexOutOfBoundsException("Length of not right");

        int i = 0;
        int i2 = 0;
        for (int y = 124; y > 119; y--) {
            for (int z = 79; z > 74; z--){
                if(lines[i].length() <= i2)
                    continue;
                switch (lines[i].charAt(i2)){
                    case 'u' ->
                            frames.put(new Location(Bukkit.getWorld("world"), -2, y, z).getBlock() ,new Bundle<>(Bukkit.getWorld("world").spawn(new Location(Bukkit.getWorld("world"), -2, y, z), ItemFrame.class, frame ->{
                                frame.setItem(new ItemStack(Material.ARROW));
                                frame.setRotation(Rotation.values()[new Random().nextInt(8)]);
                                frame.setFacingDirection(BlockFace.EAST);
                                frame.setInvulnerable(true);
                                frame.addScoreboardTag("terminalframe");
                            }).getRotation(), Type.Up));
                    case 'r' ->
                            frames.put(new Location(Bukkit.getWorld("world"), -2, y, z).getBlock() ,new Bundle<>(Bukkit.getWorld("world").spawn(new Location(Bukkit.getWorld("world"), -2, y, z), ItemFrame.class, frame ->{
                                frame.setItem(new ItemStack(Material.ARROW));
                                frame.setRotation(Rotation.values()[new Random().nextInt(8)]);
                                frame.setFacingDirection(BlockFace.EAST);
                                frame.setInvulnerable(true);
                                frame.addScoreboardTag("terminalframe");
                            }).getRotation(), Type.Right));
                    case 'd' ->
                            frames.put(new Location(Bukkit.getWorld("world"), -2, y, z).getBlock() ,new Bundle<>(Bukkit.getWorld("world").spawn(new Location(Bukkit.getWorld("world"), -2, y, z), ItemFrame.class, frame ->{
                                frame.setItem(new ItemStack(Material.ARROW));
                                frame.setRotation(Rotation.values()[new Random().nextInt(8)]);
                                frame.setFacingDirection(BlockFace.EAST);
                                frame.setInvulnerable(true);
                                frame.addScoreboardTag("terminalframe");
                            }).getRotation(), Type.Down));
                    case 'l' ->
                            frames.put(new Location(Bukkit.getWorld("world"), -2, y, z).getBlock() ,new Bundle<>(Bukkit.getWorld("world").spawn(new Location(Bukkit.getWorld("world"), -2, y, z), ItemFrame.class, frame ->{
                                frame.setItem(new ItemStack(Material.ARROW));
                                frame.setRotation(Rotation.values()[new Random().nextInt(8)]);
                                frame.setFacingDirection(BlockFace.EAST);
                                frame.setInvulnerable(true);
                                frame.addScoreboardTag("terminalframe");
                            }).getRotation(), Type.Left));
                    case 's' ->
                            frames.put(new Location(Bukkit.getWorld("world"), -2, y, z).getBlock() ,new Bundle<>(Bukkit.getWorld("world").spawn(new Location(Bukkit.getWorld("world"), -2, y, z), ItemFrame.class, frame ->{
                                frame.setItem(new ItemStack(Material.GREEN_CONCRETE));
                                frame.setFacingDirection(BlockFace.EAST);
                                frame.setInvulnerable(true);
                                frame.addScoreboardTag("terminalframe");
                            }).getRotation(), Type.Start));
                    case 'f' ->
                            frames.put(new Location(Bukkit.getWorld("world"), -2, y, z).getBlock() ,new Bundle<>(Bukkit.getWorld("world").spawn(new Location(Bukkit.getWorld("world"), -2, y, z), ItemFrame.class, frame ->{
                                frame.setItem(new ItemStack(Material.RED_CONCRETE));
                                frame.setFacingDirection(BlockFace.EAST);
                                frame.setInvulnerable(true);
                                frame.addScoreboardTag("terminalframe");
                            }).getRotation(), Type.Stop));
                    default -> frames.put(new Location(Bukkit.getWorld("world"), -2, y, z).getBlock(), null);
                }
                i2++;
            }
            i2 = 0;
            i++;
        }
        paint();
        currPattern = this;
    }
    public boolean checkForFinish(Block b, Rotation rotation){
        Bundle<Rotation, Type> p = frames.get(b);
        if(p != null)
            p.setFirst(rotation);
        paint();
        for (Bundle<Rotation, Type> pack : frames.values()){
            if(pack == null)
                continue;

            boolean isCorrect = false;
            switch (pack.getLast()){
                case Up -> isCorrect = pack.getFirst() == Rotation.COUNTER_CLOCKWISE_45;
                case Right -> isCorrect = pack.getFirst() == Rotation.CLOCKWISE_45;
                case Down -> isCorrect = pack.getFirst() == Rotation.CLOCKWISE_135;
                case Left -> isCorrect = pack.getFirst() == Rotation.FLIPPED_45;
                case Start,Stop -> isCorrect = true;
            }
            //set sea latern
            if(!isCorrect) {
                return false;
            }
        }
        return true;
    }
    public void paint(){
        for (Block b : frames.keySet()){
            Bundle<Rotation, Type> pack = frames.get(b);
            if(pack == null)
                continue;

            boolean isCorrect = false;
            switch (pack.getLast()){
                case Up -> isCorrect = pack.getFirst() == Rotation.COUNTER_CLOCKWISE_45;
                case Right -> isCorrect = pack.getFirst() == Rotation.CLOCKWISE_45;
                case Down -> isCorrect = pack.getFirst() == Rotation.CLOCKWISE_135;
                case Left -> isCorrect = pack.getFirst() == Rotation.FLIPPED_45;
                case Start,Stop -> isCorrect = true;
            }

            //set sea latern
            if(isCorrect) {
                b.getLocation().subtract(1, 0, 0).getBlock().setType(Material.SEA_LANTERN);
            }else{
                b.getLocation().subtract(1, 0, 0).getBlock().setType(Material.BLUE_TERRACOTTA);
            }
        }
    }

    public void remove(){
        Bukkit.getWorld("world").getEntities().stream().filter(entity -> entity instanceof ItemFrame && entity.getScoreboardTags().contains("terminalframe")).forEach(entity -> {
            entity.remove();
            entity.getLocation().subtract(1,0,0).getBlock().setType(Material.BLUE_TERRACOTTA);
        });
    }
    enum Type{
        Up,
        Right,
        Down,
        Left,
        Start,
        Stop

    }
}
