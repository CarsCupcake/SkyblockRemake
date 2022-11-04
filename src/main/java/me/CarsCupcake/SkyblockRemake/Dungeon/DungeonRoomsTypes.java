package me.CarsCupcake.SkyblockRemake.Dungeon;

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

         public static boolean isPlaceble(DungeonRoomsTypes type, int x, int y) {
             if(type == DungeonRoomsTypes.r1x1 || type == DungeonRoomsTypes.red || type == DungeonRoomsTypes.green || type == DungeonRoomsTypes.miniboss || type == DungeonRoomsTypes.puzzle || type == DungeonRoomsTypes.Trap){
                 if(DungeonGeneration.layers.get(y).get(x) == null)
                 return true;
                 else
                 return false;
             }
             if(type == DungeonRoomsTypes.r2x2){
                if(DungeonGeneration.layers.get(y).get(x) == null && DungeonGeneration.layers.get(y).get(x + 1) == null && DungeonGeneration.layers.get(y +1).get(x ) == null && DungeonGeneration.layers.get(y+ 1).get(x + 1) == null)
                return true;
                else
                return false;
             }
             if(type == DungeonRoomsTypes.r1x2){
                if(DungeonGeneration.layers.get(y).get(x) == null&& DungeonGeneration.layers.get(y +1).get(x ) == null)  {
                    return true;
                }else{
                    return false;
                } 
               }
               if(type == DungeonRoomsTypes.r1x3){
                if(DungeonGeneration.layers.get(y).get(x) == null&& DungeonGeneration.layers.get(y +1).get(x ) == null&& DungeonGeneration.layers.get(y +2).get(x ) == null)  {
                    return true;
                }else{
                    return false;
                } 
               }
               if(type == DungeonRoomsTypes.r1x4){
                if(DungeonGeneration.layers.get(y).get(x) == null&& DungeonGeneration.layers.get(y +1).get(x ) == null&& DungeonGeneration.layers.get(y +2).get(x ) == null&& DungeonGeneration.layers.get(y +3).get(x ) == null)  {
                    return true;
                }else{
                    return false;
                } 
               }
               if(type == DungeonRoomsTypes.rLShaped){
                if(DungeonGeneration.layers.get(y).get(x) == null&& DungeonGeneration.layers.get(y +1).get(x ) == null && DungeonGeneration.layers.get(y+ 1).get(x + 1) == null)  {
                    return true;
                }else{
                    return false;
                }
               }        
             return false;
             
         }
         
    }
