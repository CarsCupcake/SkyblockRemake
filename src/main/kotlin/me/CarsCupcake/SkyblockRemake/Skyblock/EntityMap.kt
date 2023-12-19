package me.CarsCupcake.SkyblockRemake.Skyblock

import me.CarsCupcake.SkyblockRemake.Entities.BasicEntity
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity

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
    fun getSbEntity(entity: Entity): SkyblockEntity {
        val e = allEntitysAreInHere[entity];
        if (e == null) {
            val nE: BasicEntity = BasicEntity(entity as LivingEntity)
            return nE
        }
        return e
    }
    fun getSbEntitys(): Collection<SkyblockEntity>{
        return allEntitysAreInHere.values
    }
}