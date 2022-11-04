package me.CarsCupcake.SkyblockRemake.CrimsonIsle;

import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockServer;

public enum CrimsonIsleAreas {
    Stronghold("§5Stronghold"),
    CrimsonFields("§cCrimson Filds"),
    BalzingVolcano("§6Blazing Volcano"),
    OdgersHut("§aOdger's Hut"),
    PlhlegblastPool("§cPlhlegblast Pool"),
    MagmaChamber("§4Magma Chamber"),
    AurasLab("§dAura's Lab"),
    MatriarchsLair("§6Matriarch's Lair"),
    BellyoftheBeast("§4Belly of the Beast"),
    Dojo("§eDojo"),
    BurningDesert("§eBurning Desert"),
    MysticMarsh("§dMystic Marsh"),
    BarbarianOutpost("§Barbarian Outpost"),
    MageOutpost("§bMage Outpost"),
    Dragontail("§2Dragontail"),
    ChiefsHut("§2Chief's Hut"),
    DragontailBlacksmith("§2Dragontail Blacksmith"),
    DragontailTownsquare("§2Dragontail Townsquare"),
    DragontailAuctionHouse("§2Dragontail Auction House"),
    DragontailBazaar("§2Dragontail Bazaar"),
    DragontailBank("§2Dragontail Bank"),
    MinionShop("§2Minion Shop"),
    TheDukedom("§5The Dukedom"),
    TheBastion("§2The Bastion"),
    Scarleton("§5Scarleton"),
    ScarletonCommunityCenter("§dScarleton Community Center"),
    ThroneRoom("§dThrone Room"),
    MageCouncil("§dMage Council"),
    ScarletonPlaza("§dScarleton Plaza"),
    ScarletonMinionShop("§dScarleton Minion Shop"),
    ScarletonAuctionHouse("§dScarleton Auction House"),
    ScarletonBazaar("§dScarleton Bazaar"),
    ScarletonBank("§5Scarleton Bank"),
    ScarletonBlacksmith("§5Scarleton Blacksmith"),
    IgrupansHouse("§dIgrupan's House"),
    IgrupansChickenCoop("§eIgrupan's Chicken Coop"),
    Cathedral("§5Cathedral"),
    Courtyard("§5Courtyard"),
    TheWasteland("§cThe Wasteland"),
    RuinsofAshfang("§5Ruins of Ashfang"),
    ForgottenSkull("§6Forgotten Skull"),
    SmolderingTomb("§cSmoldering Tomb");

    private final String displayName;

    CrimsonIsleAreas(String s) {
    displayName = s;
    }
    public String getDisplayName(){
        return displayName;
    }
    public static CrimsonIsleAreas getPlayerLocation(SkyblockPlayer player){
        if(SkyblockServer.getServer().getType() != ServerType.CrimsonIsle)
            return null;
        else
            return player.crimsonArea;
    }
}
