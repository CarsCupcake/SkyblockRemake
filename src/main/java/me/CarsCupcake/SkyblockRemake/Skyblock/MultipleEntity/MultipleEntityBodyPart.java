package me.CarsCupcake.SkyblockRemake.Skyblock.MultipleEntity;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class MultipleEntityBodyPart extends SkyblockEntity {
    protected final MultipleEntityHead head;

    public MultipleEntityBodyPart(MultipleEntityHead main){
        head = main;
    }

    public abstract void move();
    public MultipleEntityHead getMain(){
        return head;
    }


    @Override
    public void damage(double damage, SkyblockPlayer player) {
        getMain().damage(damage, player);
    }

    @Override
    public boolean hasNoKB() {
        return head.hasNoKB();
    }

    @Override
    public int getDamage() {
        return head.getDamage();
    }

    @Override
    public int getHealth() {
        return head.getHealth();
    }

    @Override
    public int getMaxHealth() {
        return head.getMaxHealth();
    }

    @Override
    public int getTrueDamage() {
        return head.getTrueDamage();
    }

    @Override
    public String getName() {
        return head.getName();
    }
    @Override
    public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
        return head.getDrops(player);
    }


}
