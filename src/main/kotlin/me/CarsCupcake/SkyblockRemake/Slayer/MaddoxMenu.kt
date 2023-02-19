package me.CarsCupcake.SkyblockRemake.Slayer

import me.CarsCupcake.SkyblockRemake.utils.Inventory.GuiTemplate
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer
import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats
import me.CarsCupcake.SkyblockRemake.Slayer.Zombie.ZombieT1
import me.CarsCupcake.SkyblockRemake.Slayer.Zombie.ZombieT2
import me.CarsCupcake.SkyblockRemake.Slayer.Zombie.ZombieT3
import me.CarsCupcake.SkyblockRemake.Slayer.Zombie.ZombieT4
import me.CarsCupcake.SkyblockRemake.Slayer.Zombie.ZombieT5
import me.CarsCupcake.SkyblockRemake.utils.Assert
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.*
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder
import me.CarsCupcake.SkyblockRemake.utils.Tools
import org.bukkit.Material
import org.bukkit.inventory.Inventory

object MaddoxMenu {
    fun openMenu(player:SkyblockPlayer){
        val builder = InventoryBuilder(4, "Slayer").fill(TemplateItems.EmptySlot.item)
            .setItem(ItemBuilder(Material.ROTTEN_FLESH)
                .setName("§c☠ §eRevenant Horror")
                .addAllLore(listOf("§7Abhorrent Zombie stuck", "§7between life and death for", "§7an eternity."))
                .build(), 10)
        val gui = GUI(builder.build())
        gui.inventoryClickAction(10
        ) {
            val g = GUI(
                SlayerTemplate("Revenant Horror", Material.ROTTEN_FLESH, 5, listOf(
                ZombieT1(player), ZombieT2(player), ZombieT3(player), ZombieT4(player), ZombieT5(player)
            ), gui, player)
            )
            g.showGUI(player)
        }
        gui.showGUI(player)
    }
    class SlayerTemplate(private val name: String,private val material: Material, private val levels: Int, private val slayers: List<Slayer>, private val gui: GUI, private val player: SkyblockPlayer):
        GuiTemplate {
        init {
            Assert.isTrue(this.levels == this.slayers.size, "Slayers do not have the same size as level")
            Assert.isTrue(Tools.isInRange(4.0, 5.0, this.levels.toDouble()), "Level not in range")
        }
        override fun getInventory(): Inventory {
            val builder = InventoryBuilder(6, name)
                .fill(TemplateItems.EmptySlot.item)
                for (s in listOf(1, 2, 3, 4)){
                    builder.setItem(
                        ItemBuilder(material)
                            .setName("$name ${Tools.intToRoman(s)}")
                            .addAllLore(
                                listOf(
                                    " ",
                                    "§7Health §c${slayers[s - 1].health}${Stats.Health.getSymbol()}",
                                    "§7Damage: §c${slayers[s - 1].damage} §7per second",
                                    " ",
                                    "§7Cost to start: §6${when(s){
                                        1 -> "2,000"
                                        2 -> "7,500"
                                        3 -> "20,000"
                                        4 -> "50,000"
                                        5 -> "100,000"

                                        else -> {"§cN/A§6"}
                                    }
                                    } coins",
                                    " ",
                                    "§eClick to slay!"
                                )
                            )
                            .build(), 9 + s
                    )
                }
            if(levels == 5){
                builder.setItem(
                    ItemBuilder(material)
                        .setName("$name ${Tools.intToRoman(5)}")
                        .addAllLore(
                            listOf(
                                " ",
                                "§7Health §c${slayers[4].health}${Stats.Health.getSymbol()}",
                                "§7Damage: §c${slayers[4].damage} §7per second",
                                " ",
                                "§7Cost to start: §6100,000 coins",
                                " ",
                                "§eClick to slay!"
                            )
                        )
                        .build(), 14
                )
            }else{
                builder.setItem(ItemBuilder(Material.COAL_BLOCK)
                    .setName("$name V")
                    .addLoreRow("§cNot Comming Soon!")
                    .build(), 14)
            }
            builder.setItem(TemplateItems.BackArrow.item, 48)
            return builder.build()
        }
        override fun getSlotActions(): Map<Int, GUIAction> {
            return hashMapOf(
                48 to GUIAction{ gui.showGUI(player) },
                49 to GUIAction{ gui.closeInventory() },
                10 to GUIAction { slayers[0].spawn(player.location)},
                11 to GUIAction { slayers[1].spawn(player.location)},
                12 to GUIAction { slayers[2].spawn(player.location)},
                13 to GUIAction { slayers[3].spawn(player.location)}
                ).also {
                    if(levels == 5)
                        it[14] = GUIAction {slayers[4].spawn(player.location)}
            }
        }
    }
}