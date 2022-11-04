package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Items.UpdateFlag;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class MagmaLordArmor implements FullSetBonus {
    private SkyblockPlayer player;
    private BukkitRunnable runnable;
    private int pieces;
    @Override
    public void start() {
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 25,1,false, false,false));
            }
        };
        runnable.runTaskTimer(Main.getMain(), 0, 20);
        pieces = player.bonusAmounts.get(getBonus());
        if(pieces > 4){
            player.setBaseStat(Stats.SeaCreatureChance, player.baseseacreaturechance+1.5);
        }

    }

    @Override
    public void stop() {
        runnable.cancel();
        if(pieces > 4){
            player.setBaseStat(Stats.SeaCreatureChance, player.baseseacreaturechance-1.5);
        }

    }

    @Override
    public int getPieces() {
        return 2;
    }

    @Override
    public int getMaxPieces() {
        return 2;
    }

    @Override
    public void setPlayer(SkyblockPlayer player) {
        this.player = player;

    }

    @Override
    public Bonuses getBonus() {
        return Bonuses.MagmaLordArmor;
    }

    public enum Placeholders{

        prefix((player, item) -> {
            int bonus = 0;
            if(player != null && player.bonusAmounts.get(Bonuses.MagmaLordArmor)!= null)
                bonus = player.bonusAmounts.get(Bonuses.MagmaLordArmor);

            if(bonus <4)
                return "§8";
            else
                return "§6";

        }),
        amount((player, item) -> {
            int bonus = 0;
            if(player != null && player.bonusAmounts.get(Bonuses.MagmaLordArmor)!= null)
                bonus = player.bonusAmounts.get(Bonuses.MagmaLordArmor);

            return bonus + "";

        }),
        scc((player, item) -> {
            int bonus = 0;
            if(player != null && player.bonusAmounts.get(Bonuses.MagmaLordArmor)!= null)
                bonus = player.bonusAmounts.get(Bonuses.MagmaLordArmor);

            if(bonus <=2)
                return "1";
            else
                return "1.5";

        }),
        mf((player, item) -> {
            int bonus = 0;
            if(player != null && player.bonusAmounts.get(Bonuses.MagmaLordArmor)!= null)
                bonus = player.bonusAmounts.get(Bonuses.MagmaLordArmor);

            if(bonus <=2)
                return "5";
            else
                return "10";

        });
        private final UpdateFlag flag;
        Placeholders(UpdateFlag flag) {
            this.flag = flag;
        }
        public UpdateFlag getFlag(){
            return flag;
        }
    }
}
