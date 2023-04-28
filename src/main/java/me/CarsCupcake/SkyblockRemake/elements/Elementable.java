package me.CarsCupcake.SkyblockRemake.elements;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;

import java.util.Set;

public interface Elementable {
    void addElement(Element element);
    Set<Element> getElements();
    void removeElement(Element element);
    static void addElement(SkyblockEntity entity, Element element){
        entity.addElement(element);
    }
}
