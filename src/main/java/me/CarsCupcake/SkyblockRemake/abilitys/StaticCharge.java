package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Items.UpdateFlag;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class StaticCharge implements FullSetBonus, Listener {
    private static final HashMap<SkyblockPlayer, StaticCharge> players = new HashMap<>();
    private SkyblockPlayer player;
    private int stacks = 0;
    private BukkitRunnable runnable;
    @Override
    public void start() {
        players.put(player,this);
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(getMaxStacks(player) > stacks)
                    stacks++;
            }
        };
        runnable.runTaskTimer(Main.getMain(), getTime(player.bonusAmounts.get(getBonus())), getTime(player.bonusAmounts.get(getBonus())));
    }

    @Override
    public void stop() {
        players.remove(player);
        try {
            runnable.cancel();
        }catch (Exception ignored){

        }
    }

    @Override
    public int getPieces() {
        return 2;
    }

    @Override
    public int getMaxPieces() {
        return 5;
    }

    @Override
    public void setPlayer(SkyblockPlayer player) {
        this.player = player;
    }
    private int getTime(int amount){
        switch (amount){
            case 3 ->{
                return 25*20;
            }
            case 4 ->{
                return 20*20;
            }
            case 5 ->{
                return 10*20;
            }
            default -> {
                return 30 * 20;
            }
        }
    }

    @Override
    public Bonuses getBonus() {
        return Bonuses.StaticCharge;
    }
    //⚡
    public static String getPlayerDisplay(SkyblockPlayer player){
        int maxStacks = getMaxStacks(player);
        if(maxStacks == 0)
            return "";
        StringBuilder str = new StringBuilder();
        int stacks = players.get(player).getStacks();
        for (int i = 0; i < maxStacks; i++){
            str.append((i < stacks) ? "§b" : "§7").append("⚡");
        }

        return str.toString();
    }
    public int getStacks(){
        return stacks;
    }

    public static int getMaxStacks(SkyblockPlayer player){
        if(!player.bonusAmounts.containsKey(Bonuses.StaticCharge))
            return 0;
        if(player.bonusAmounts.get(Bonuses.StaticCharge) < 2)
            return 0;
        int amount = player.bonusAmounts.get(Bonuses.StaticCharge);
        if(amount < 5)
            return amount;
        else
            return 5;
    }
    private double getMult(SkyblockPlayer player){
        switch (player.bonusAmounts.get(Bonuses.StaticCharge)){
            case 2 ->{
                return 1+0.15*players.get(player).getStacks();
            }
            case 3 ->{
                return 1+0.2*players.get(player).getStacks();
            }
            case 4 ->{
                return 1+0.25*players.get(player).getStacks();
            }
            case 5 ->{
                return 1+ 0.4*players.get(player).getStacks();
            }
            default -> {
                return 1;
            }
        }
    }
    @EventHandler
    public void onCalculate(SkyblockDamageEvent event){
        if(!players.containsKey(event.getPlayer()))
            return;
        if(event.getCalculator().isFerocity() || event.getCalculator().isMagic() || event.getCalculator().getProjectile() != null)
            return;

        SkyblockPlayer player = event.getPlayer();
        event.getCalculator().damage *= getMult(player);
        int stacks = players.get(event.getPlayer()).getStacks();
        if(stacks != 0){
            for (int i = 0; i < stacks; i++)
                event.getEntity().getWorld().spawn(event.getEntity().getLocation(), LightningStrike.class);
            players.get(event.getPlayer()).stacks = 0;
        }
    }
    public enum Placeholders{

        time((player, item) -> {
            int bonus = 0;
            if(player != null && player.bonusAmounts.get(Bonuses.StaticCharge)!= null)
                bonus = player.bonusAmounts.get(Bonuses.StaticCharge);

            if(bonus <=2)
                return "30";
            if (bonus == 3)
                return "25";
            if(bonus == 4)
                return "20";
            if(bonus >= 5)
                return "10";
            return "N/A";

        }),
        damage((player, item) -> {
            int bonus = 0;
            if(player != null && player.bonusAmounts.get(Bonuses.StaticCharge)!= null)
                bonus = player.bonusAmounts.get(Bonuses.StaticCharge);

            if(bonus <=2)
                return "15%";
            if (bonus == 3)
                return "20%";
            if(bonus == 4)
                return "25%";
            if(bonus >= 5)
                return "40%";
            return "N/A";

        }),
        charge((player, item) -> {
            int bonus = 0;
            if(player != null && player.bonusAmounts.get(Bonuses.StaticCharge)!= null)
                bonus = player.bonusAmounts.get(Bonuses.StaticCharge);

            if(bonus <=2)
                return "2";
            if (bonus == 3)
                return "3";
            if(bonus == 4)
                return "4";
            if(bonus >= 5)
                return "5";
            return "N/A";

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
