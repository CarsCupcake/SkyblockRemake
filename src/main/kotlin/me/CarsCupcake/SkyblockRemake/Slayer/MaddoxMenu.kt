package me.CarsCupcake.SkyblockRemake.Slayer

import me.CarsCupcake.SkyblockRemake.utils.Inventories.GuiTemplate
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer
import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats
import me.CarsCupcake.SkyblockRemake.Slayer.blaze.Entities.T1.BlazeSlayerT1
import me.CarsCupcake.SkyblockRemake.Slayer.blaze.Entities.T2.BlazeSlayerT2
import me.CarsCupcake.SkyblockRemake.Slayer.blaze.Entities.T3.BlazeSlayerT3
import me.CarsCupcake.SkyblockRemake.Slayer.blaze.Entities.T4.BlazeSlayerT4
import me.CarsCupcake.SkyblockRemake.Slayer.enderman.EndermanT1
import me.CarsCupcake.SkyblockRemake.Slayer.enderman.EndermanT4
import me.CarsCupcake.SkyblockRemake.Slayer.spider.entity.*
import me.CarsCupcake.SkyblockRemake.Slayer.sven.entity.SvenSlayerT1
import me.CarsCupcake.SkyblockRemake.Slayer.sven.entity.SvenSlayerT2
import me.CarsCupcake.SkyblockRemake.Slayer.sven.entity.SvenSlayerT3
import me.CarsCupcake.SkyblockRemake.Slayer.sven.entity.SvenSlayerT4
import me.CarsCupcake.SkyblockRemake.Slayer.zombie.ZombieT1
import me.CarsCupcake.SkyblockRemake.Slayer.zombie.ZombieT2
import me.CarsCupcake.SkyblockRemake.Slayer.zombie.ZombieT3
import me.CarsCupcake.SkyblockRemake.Slayer.zombie.ZombieT4
import me.CarsCupcake.SkyblockRemake.Slayer.zombie.ZombieT5
import me.CarsCupcake.SkyblockRemake.utils.Assert
import me.CarsCupcake.SkyblockRemake.utils.CoinUtils
import me.CarsCupcake.SkyblockRemake.utils.Inventories.*
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder
import me.CarsCupcake.SkyblockRemake.utils.Tools
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.Inventory

object MaddoxMenu {
    fun openMenu(player:SkyblockPlayer){
        val builder = InventoryBuilder(4, "Slayer").fill(TemplateItems.EmptySlot.item)
            .setItem(ItemBuilder(Material.ROTTEN_FLESH)
                .setName("§c☠ §eRevenant Horror")
                .addAllLore(listOf("§7Abhorrent Zombie stuck", "§7between life and death for", "§7an eternity."))
                .build(), 10)
            .setItem(ItemBuilder(Material.COBWEB)
                .setName("§c☠ §eTarantula Broodfather")
                .build(), 11)
            .setItem(ItemBuilder(Material.MUTTON)
                .setName("§c☠ §eSven Packmaster")
                .build(), 12)
            .setItem(ItemBuilder(Material.ENDER_PEARL)
                .setName("§eVoidgloom Seraph")
                .addAllLore(ChatColor.GRAY, "If Necron is the right-hand", "of the Wither King, this", "dark demigod is the", "left-hand.")
                .build(), 13)
            .setItem(ItemBuilder(Material.BLAZE_POWDER)
                .setName("§eInferno Demonlord")
                .addAllLore(ChatColor.GRAY, "Even demons fear this", "incarnation of pure evil,", "constatntly feeding on its", "burning desire for", "destruction.")
                .build(), 14)
        val gui = GUI(builder.build())
        gui.inventoryClickAction(10
        ) {
            val g = GUI(
                SlayerTemplate("§eRevenant Horror", Material.ROTTEN_FLESH, 5, listOf(
                    ZombieT1(player), ZombieT2(player), ZombieT3(player), ZombieT4(player), ZombieT5(player)
                ), gui, player)
            )
            g.showGUI(player)
        }
        gui.inventoryClickAction(11) {
            val g = GUI(
                SlayerTemplate("§eTarantula Broodfather", Material.COBWEB, 4, listOf(
                    SpiderSlayerT1(player), SpiderSlayerT2(player), SpiderSlayerT3(player), SpiderSlayerT4(player)
                ), gui, player)
            )
            g.showGUI(player)
        }
        gui.inventoryClickAction(12
        ) {
            val g = GUI(
                SlayerTemplate("§eSven Packmaster", Material.MUTTON, 4, listOf(
                    SvenSlayerT1(player), SvenSlayerT2(player), SvenSlayerT3(player), SvenSlayerT4(player)
                ), gui, player)
            )
            g.showGUI(player)
        }
        gui.inventoryClickAction(13
        ) {
            val g = GUI(
                SlayerTemplate("§eVoidgloom Seraphe", Material.ENDER_PEARL, 4, listOf(
                    EndermanT1(player), null, null, EndermanT4(player)
                ), gui, player)
            )
            g.showGUI(player)
        }
        gui.inventoryClickAction(14
        ) {
            val g = GUI(
                SlayerTemplate("§eInferno Demonlord", Material.BLAZE_POWDER, 4, listOf(
                    BlazeSlayerT1(player), BlazeSlayerT2(player), BlazeSlayerT3(player), BlazeSlayerT4(player)
                ), gui, player)
            )
            g.showGUI(player)
        }

        gui.isCanceled = true
        gui.showGUI(player)
    }
    class SlayerTemplate(private val name: String,private val material: Material, private val levels: Int, private val slayers: List<Slayer?>, private val gui: GUI, private val player: SkyblockPlayer):
        GuiTemplate() {
        init {
            Assert.isTrue(this.levels == this.slayers.size, "Slayers do not have the same size as level")
            Assert.isTrue(Tools.isInRange(3.0, 6.0, this.levels.toDouble()), "Level not in range")
        }
        override fun getInventory(): Inventory {
            val builder = InventoryBuilder(6, name)
                .fill(TemplateItems.EmptySlot.item)
                for (s in listOf(1, 2, 3, 4)){
                    val slayer = slayers[s-1]
                    if(slayer != null)
                    builder.setItem(
                        ItemBuilder(material)
                            .setName("$name ${Tools.intToRoman(s)}")
                            .addAllLore(
                                listOf(
                                    " ",
                                    "§7Health §c${slayer.health}${Stats.Health.symbol}",
                                    "§7Damage: §c${slayer.damage} §7per second",
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
                    else
                        builder.setItem(ItemBuilder(Material.COAL_BLOCK)
                            .setName("$name V")
                            .addLoreRow("§cNot Comming Soon!")
                            .build(), 9 + s)
                }
            if(levels == 5){
                builder.setItem(
                    ItemBuilder(material)
                        .setName("$name ${Tools.intToRoman(5)}")
                        .addAllLore(
                            listOf(
                                " ",
                                "§7Health §c${slayers[4]?.health}${Stats.Health.symbol}",
                                "§7Damage: §c${slayers[4]?.damage} §7per second",
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

        override fun getSlotActions(gui: GUI): Map<Int, GUIAction> {
            return hashMapOf(
                48 to GUIAction{ gui.showGUI(player) },
                49 to GUIAction{ gui.closeInventory() },
                10 to GUIAction {
                    if(CoinUtils.spendIfEnouth(player, 2000.0)) Slayer.summonSlayer(player.location, slayers[0])},
                11 to GUIAction { if(Slayer.getSlayer(player) != null) player.sendMessage("§cA Slayer already spawned!") else if(CoinUtils.spendIfEnouth(player, 7500.0)) Slayer.summonSlayer(player.location, slayers[1]) },
                12 to GUIAction { if(Slayer.getSlayer(player) != null) player.sendMessage("§cA Slayer already spawned!") else if(CoinUtils.spendIfEnouth(player, 50000.0)) Slayer.summonSlayer(player.location, slayers[2]) },
                13 to GUIAction { if(Slayer.getSlayer(player) != null) player.sendMessage("§cA Slayer already spawned!") else if(CoinUtils.spendIfEnouth(player, 100000.0)) Slayer.summonSlayer(player.location, slayers[3]) }
                ).also {
                    if(levels == 5)
                        it[14] = GUIAction { if(Slayer.getSlayer(player) != null) player.sendMessage("§cA Slayer already spawned!") else Slayer.summonSlayer(player.location, slayers[4]) }
            }
        }
        override fun isCanceled():Boolean{
            return true
        }
    }
}