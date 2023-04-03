package me.CarsCupcake.SkyblockRemake.isles.Dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum DungeonRoomsTypes {
    r1x1,
    r1x2,
    r1x3,
    r1x4,
    r2x2,
    rLShaped,
    puzzle,
    miniboss,
   Trap,
   green,
   red,
   fairy;

   public String toString(){
       switch(this){
        case Trap:
            return "trp";
        case green:
            return "gre";
        case miniboss:
        return "min";
        case puzzle:
        return "puz";
        case r1x1:
        return "1x1";
        case r1x2:
        return "1x2";
        case r1x3:
        return "1x3";
        case r1x4:
        return "1x4";
        case r2x2:
        return "2x2";
        case rLShaped:
        return "Lro";
        case red:
        return "red";
        case fairy:
        return "fai";
        default:
        return null;

       }


         }

         public static DungeonRoomsTypes getRandom(){
             List<DungeonRoomsTypes> l = new ArrayList<>(List.of(r1x2,r1x3, r1x4, r2x2, rLShaped));
             Collections.shuffle(l);
             return l.get(0);
         }
         
    }
