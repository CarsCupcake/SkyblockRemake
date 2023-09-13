package me.CarsCupcake.SkyblockRemake.Items;

import lombok.Getter;
import org.bukkit.event.block.Action;

import java.util.HashSet;
import java.util.Set;

public enum AbilityType {
    RightClick(true, false, false, false),
    LeftClick(false, true, false, false),
    SneakRightClick(true, false, true, false),
    SneakLeftClick(false, true, true, false),
    Sneak(false, false, true, false),
    EntityHit(),
    FullSetBonus(),
    LeftOrRightClick(true, true, false, false),
    SneakLeftOrRightClick(true, true, true, false),
    SkyblockPreHit(),
    AfterHit();
    @Getter
    private final boolean rightClick;
    @Getter
    private final boolean leftClick;
    @Getter
    private final boolean sneak;
    @Getter
    private final boolean other;

    AbilityType(boolean isRightClick, boolean isLeftClick, boolean isSneak, boolean isOther) {
        rightClick = isRightClick;
        leftClick = isLeftClick;
        sneak = isSneak;
        other = isOther;
    }
    AbilityType() {
        this(false, false, false, true);
    }

    public String toString() {
        return switch (this) {
            case EntityHit, SkyblockPreHit, AfterHit -> "Hit";
            case LeftClick -> "Left Click";
            case RightClick -> "Right Click";
            case Sneak -> "Sneak";
            case SneakLeftClick -> "Sneak Left Click";
            case SneakRightClick -> "Sneak Right Click";
            case FullSetBonus -> "Full Set Bonus";
            case LeftOrRightClick -> "Left/Right Click";
            case SneakLeftOrRightClick -> "Sneak Left/Right Click";
        };
    }

    public Set<Action> toAction(){
        Set<Action> actions = new HashSet<>();
        if(rightClick) {
            actions.add(Action.RIGHT_CLICK_AIR);
            actions.add(Action.RIGHT_CLICK_BLOCK);
        }
        if(leftClick) {
            actions.add(Action.LEFT_CLICK_AIR);
            actions.add(Action.LEFT_CLICK_BLOCK);
        }
        return actions;
    }
}
