package me.CarsCupcake.SkyblockRemake.Skyblock

import org.bukkit.entity.Entity

object EntityMap {
    private var allEntitysAreInHere: HashMap<Entity, SkyblockEntity> = HashMap()
    fun addEntity(key: Entity, value: SkyblockEntity) {
        allEntitysAreInHere[key] = value
    }

    fun exists(key: Entity): Boolean {
        return allEntitysAreInHere.containsKey(key)
    }
    fun remove(key: Entity){
        allEntitysAreInHere.remove(key)
    }
    fun getSbEntity(entity: Entity): SkyblockEntity? {
        return allEntitysAreInHere[entity]
    }
    fun getSbEntitys(): Collection<SkyblockEntity>{
        return allEntitysAreInHere.values
    }
}