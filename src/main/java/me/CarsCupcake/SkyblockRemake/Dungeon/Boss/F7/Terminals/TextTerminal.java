package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminal;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TextTerminal extends Terminal {
    // Item - color
    public static HashMap<Material, String> colorItems = new HashMap<>();

    private boolean isStartsWith;

    private int series;
    //If is starts with: 1 = e  2 = a 3 = b 4 = c
    //If not is starts with: 1 = Blue 2 = Black 3 = White 4 = Lime
    private final ArrayList<Integer> correct = new ArrayList<>();

    public TextTerminal(F7Phase3 phase, int terminalId) {
        super(phase, terminalId);
    }

    @Override
    public void open(@NotNull SkyblockPlayer player) {
        isOpen = true;
        isStartsWith = false; //new Random().nextBoolean();
        Random r = new Random();
        int low = 1;
        int high = 4;
        series = r.nextInt(high-low) + low;

        String name;
        if(isStartsWith)
            name = "What starts with " + String.valueOf(getCurrentChar()).toUpperCase() + "?";
        else
            name = "Select all the " + getColorText().toUpperCase() + " items!";

        InventoryBuilder builder = new InventoryBuilder(6, name)
                .fill(TemplateItems.EmptySlot.getItem());

        if(!isStartsWith){
            ArrayList<Material> corrects = new ArrayList<>();
            for (Material m : colorItems.keySet())
                if (colorItems.get(m).equals(getColorText().toUpperCase()))
                    corrects.add(m);
            for (Material material : corrects) {
                low = 10;
                high = 43;
                int slot = r.nextInt(high - low) + low;
                if((slot + 1) % 9 == 0)
                    slot -= 1;
                if((slot) % 9 == 0)
                    slot += 1;
                if(correct.contains(slot))
                    continue;

                correct.add(slot);
                builder.setItem(new ItemBuilder(material).addItemFlag(ItemFlag.HIDE_ENCHANTS).build(), slot);
            }
            low = 0;
            high = corrects.size() - 1;
            for(int i = 10; i < 44; i++){

                if((i + 1) % 9 == 0)
                    i += 2;
                if(correct.contains(i))
                    continue;
                Material m = colorItems.keySet().toArray(Material[]::new)[r.nextInt(high-low) + low];
                if(m.toString().toLowerCase().contains(getColorText()))
                    correct.add(i);
                builder.setItems(new ItemStack(m), i);
            }
        }

        GUI gui = new GUI(builder.build());
        gui.setCanceled(true);
        gui.setGeneralAction((slot, actionType, type) -> {
            if(!correct.contains(slot))
                return;
            if(actionType != GUI.GUIActions.Click)
                return;

            ItemMeta meta =  gui.getInventory().getItem(slot).getItemMeta();
            meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
            gui.getInventory().getItem(slot).setItemMeta(meta);
            correct.remove((Integer) slot);
            System.out.println(correct);
            if (correct.isEmpty()) {
                gui.closeInventory();
                finish(player);
            }
        });
        gui.closeAction(type -> isOpen = false);
        gui.showGUI(player);

    }

    public char getCurrentChar(){
        if(!isStartsWith)
            throw new IllegalArgumentException("Not Allowed to get a char here!");

        switch (series){
            case 1 -> {
                return 'e';
            }
            case 2 -> {
                return 'a';
            }
            case 3 -> {
                return 'b';
            }
            case 4 -> {
                return 'c';
            }
            default -> throw new IllegalArgumentException("Wrong series number: " + series);
        }
    }

    public String getColorText(){
        if(isStartsWith)
            throw new IllegalArgumentException("Not Allowed to get a a color string here!");

        switch (series){
            case 1 -> {
                return "blue";
            }
            case 2 -> {
                return "black";
            }
            case 3 -> {
                return "white";
            }
            case 4 -> {
                return "lime";
            }
            default -> throw new IllegalArgumentException("Wrong series number: " + series);
        }
    }
}
