package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.jetbrains.annotations.NotNull;

public abstract class Terminal {
    protected boolean isOpen = false;
    protected final F7Phase3 phase;
    private final int id;

    public Terminal(F7Phase3 phase, int terminalId){
        this.phase = phase;
        id = terminalId;
    }

    public abstract void open(@NotNull SkyblockPlayer player);

    public boolean isOpen(){
        return isOpen;
    }
    public int getId(){
        return id;
    }
    public void finish(SkyblockPlayer player){
        if(phase != null)
            phase.solveTerminal(this, player);
    }

}
