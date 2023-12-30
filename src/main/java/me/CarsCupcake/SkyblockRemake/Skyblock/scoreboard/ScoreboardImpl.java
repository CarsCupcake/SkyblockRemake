package me.CarsCupcake.SkyblockRemake.Skyblock.scoreboard;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Factory;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.ChatColor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public enum ScoreboardImpl implements Factory<SkyblockPlayer, String[]> {
    Header {
        @Override
        public String[] factor(SkyblockPlayer player) {
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yy");
            String formattedDate = date.format(format);
            return new String[]{"§7" + formattedDate + "§8 mega69", "§7 "};
        }
    },
    Time{
        @Override
        public String[] factor(SkyblockPlayer player) {
            me.CarsCupcake.SkyblockRemake.utils.Time t = me.CarsCupcake.SkyblockRemake.utils.Time.getInstance();
            return new String[] {t.getSeason().getName() + " " + t.getDay() +t.getDaySuffix(), t.getTime()};
        }
    },
    Location{
        @Override
        public String[] factor(SkyblockPlayer player) {
            return new String[]{"§7⏣ " + ((player.getRegion() == null) ? "None" : player.getRegion().name()), "§7 "};
        }
    },
    Purse{
        @Override
        public String[] factor(SkyblockPlayer player) {
            String nm = (ServerType.getActiveType() == ServerType.Rift) ? "Motes" : "Purse";
            String cl = (ServerType.getActiveType() == ServerType.Rift) ? "d" : "6";
            String bef = player.coinsChange;
            player.coinsChange = "";
            return new String[]{((player.showMithrilPowder) ? ("§2᠅ §fMithril: §2" + Tools.addDigits(player.mithrilpowder)) : (nm + ": " + "§" + cl + (Tools.addDigits((ServerType.getActiveType() == ServerType.Rift) ? RiftPlayer.getRiftPlayer(player).getMotes() : player.coins) + bef))),
                    "Bits: " + ChatColor.AQUA + Tools.addDigits(player.bits)};
        }
    },
    Footer{
        @Override
        public String[] factor(SkyblockPlayer player) {
            return new String[]{"§8by CarsCupcake", ChatColor.YELLOW + "localhost:" + Main.getMain().getServer().getPort()};
        }
    };
    public ScoreboardSectionFactory getSection() {
        ScoreboardSectionFactory factory = ScoreboardSectionFactory.factorys.get(this);
        return (factory == null) ? new ScoreboardSectionFactory(this) : factory;
    }
    public static class ScoreboardSectionFactory extends ScoreboardSection {
        private static final HashMap<ScoreboardImpl, ScoreboardSectionFactory> factorys = new HashMap<>();
        private final ScoreboardImpl impl;
        public ScoreboardSectionFactory(ScoreboardImpl impl) {
            this.impl = impl;
            factorys.put(impl, this);
        }

        @Override
        public String[] score(SkyblockPlayer player) {
            return impl.factor(player);
        }
    }
}
