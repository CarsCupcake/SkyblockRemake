package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F3;

import lombok.Getter;

public class Floor3 {
    @Getter
    private int phase = 1;
    private int gDowm = 0;
    public Floor3(boolean mastermode){

    }
    public void guardianDown(){
        gDowm++;
        if(gDowm == 4){

        }
    }
    public void guardianUp(){

    }
}
