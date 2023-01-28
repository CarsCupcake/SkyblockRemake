package me.CarsCupcake.SkyblockRemake.Skyblock;

public class EntityHandler {
    public static EntityAtributes.Attributes[] getAttributes(SkyblockEntity entity){
        if(entity.getClass().isAnnotationPresent(EntityAtributes.class)){
            return (entity.getClass().getAnnotationsByType(EntityAtributes.class).length != 0) ? entity.getClass().getAnnotationsByType(EntityAtributes.class)[0].value() : new EntityAtributes.Attributes[0];
        }else return new EntityAtributes.Attributes[0];
    }
}
