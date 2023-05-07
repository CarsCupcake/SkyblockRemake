package me.CarsCupcake.SkyblockRemake.NPC.Questing;

public class BasicSelectOption implements QuestOption{
    private final String name;
    private final Runnable select;
    public BasicSelectOption(String name, Runnable select){
        this.name = name;
        this.select = select;
    }
    @Override
    public String name() {
        return name;
    }

    @Override
    public void select() {
        select.run();
    }

    @Override
    public String toString() {
        return "[" + name() + "]";
    }
}
