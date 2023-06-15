package me.CarsCupcake.SkyblockRemake.cmd;


import com.mojang.authlib.properties.Property;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Entities.BasicEntity;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.DialogBuilder;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F3.guardians.RougeGuardianP1;
import me.CarsCupcake.SkyblockRemake.utils.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;


public class testobjectCMD implements CommandExecutor {
    private PlayerDisguise disguise;
    private static final DialogBuilder testDialog = new DialogBuilder(new Sound(org.bukkit.Sound.ENTITY_VILLAGER_AMBIENT, 1, 1)).withTextPrefix("§e[Joe]§r: ").addText("Hi!").addDialogOption(new Bundle<>("Howdy", new DialogBuilder(new Sound(org.bukkit.Sound.ENTITY_VILLAGER_AMBIENT, 1, 1)).withTextPrefix("§e[Joe]§r: ").addText("Bruh")), new Bundle<>("Hi", new DialogBuilder(new Sound(org.bukkit.Sound.ENTITY_VILLAGER_AMBIENT, 1, 1)).withTextPrefix("§e[Joe]§r: ").addText("Hi")));

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("testobject")) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage("Du kannst das net");
                return true;
            }
            if(args.length == 1){
                disguise.status(Byte.parseByte(args[0]));
                return false;
            }
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
            LivingEntity entity = player.getWorld().spawn(player.getLocation(), Zombie.class);
            disguise = new PlayerDisguise(entity, new Property("textures","ewogICJ0aW1lc3RhbXAiIDogMTY1OTYyNTIwODg1MSwKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzQ3VwY2FrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hNzdmNzZmOWViNjAwYTgxNGFkYjE4YzA1NGRjN2MyNGUyNzg5MDJjZTRhYWU0ZTlmNzE0YWYxYTg5Y2VhZGZmIgogICAgfQogIH0KfQ==",
                    "dT4X23auQlmLPrNkMmLuY0OPCjvWSo6VOG9hgGAmXWMJUip4iRfGt7JrILxzCvCJKKMUx5VBLCc/HyTob/2BMVBfMng1nYuTsUK8pzlACQvlbLYP+RTL9gXhLNGKHSdnfbTBfo16Eg4pRtJXouwY6Ni9tk2tpPfihvIjdmMnp1lQSnjK4G8p0UwVt4mturSNGRg4INjyEquj4EO68z9BhEVFdl5hT8ZK6m1UwH0ZJltiaH27QDacmQqK6dgGb33fBdjFQjomQTjG2cL+Tbp19iUqzhLGJJQHUvVGmnbQ7vrR0ezJv+EptvPKSem0H3OaJSzALx+q0PxzLTfA1VfwaNB0gUutMQ0dT+jglbT998+MOmVKCwNICrPbnVjAE3g+CFisuePsOr1HrxNUgXrAH8UWtqAXAPdycj7kqPmo1I/QiV5ulgOl7+X/81OczYsCfdM+42wuUVXYNM7V7VSM4EvAtYyWj6iiH7KwN+8bMcPd/AIAcPzS9+yuxJwNacQJmOHwU7A6a98jMSax3eJOyOxeqeT3T4nAaevfAvsNDSG6yRBtQIqzij6m04lH4lkLGtLQmyVkQR4xEcScAe8McKUWsT0fTb4TR3WYnsaogj98vHl8ZDhGzvKJQmy2Titb45GhmmtS/BhUYBykxhRz8C1PcPAXIQOjQBwSgy+Zn1A="));

            return true;

        }

        return false;
    }

}
