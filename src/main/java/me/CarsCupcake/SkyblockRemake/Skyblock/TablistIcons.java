package me.CarsCupcake.SkyblockRemake.Skyblock;

import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.properties.Property;

import net.minecraft.server.level.EntityPlayer;
import org.jetbrains.annotations.Nullable;

public enum TablistIcons {
Green,
Grey,
DarkAqua,
Gold,
Players;
public String getSkinSignature(@Nullable Player player) {
	switch (this) {
		case DarkAqua -> {
			return "hR0TA6Hhs9JtsUAaMEVqCjPgAn4xBqpHmRoMOJ9JyQWTPqhHA/COqQ3sLnPjum0ufEc23oQyvkcMRn4p+7cAAZRMwhdV4dy1KHistnPL8EKDLglQc/0sxnHdEj8Aw3M5PovNPrOTiumSMuEOZlRvBIsLn1nT/Nb9aUNUcJ6AZubVTcOltlpLfXqDulDNOH1ORs6z18eA9NACN4/cMzouUcgcpgejuJ25cVf2EjyqE4QyQKmsz1mczwrkNxvm6OS0xRE6M4tREO4+sXuI1+0l8oFnorVZICZjICU8FtJofC/GaVHzE4Cst1TMp2NRtZq4RacwwUIxnudkh/5SqBkFR1z+cDFln1CwBVGyFq8CFoGAjb47TlG9UKwQcKJ6GVHb6WyT9fLql6imzLRNnLZsyxAg+m2KxK1YdmSZC5cP5Kmn7/MCCo1YmTPknwGeWCnWCAFGu9C/05chEVxEg0H8OpHrWtkKudL2T+6hAWEg5+tlJ1dwOnv94rKfSoV7gtA6YNTBkfJkGdUNBpB5MmUzfVCBF4tc7sgZkiWU26AUM+evHG125TK2fMNVmVysTgPWavw21pTOuMqPQHOhibKKS9YvSEQmjBW4LlqDr6lLchfuBHAIWU20+mKwSIKPw9Y/nuM9XbYtTguBiclmZ17j5akDMPpvVYbzGUbBR177Hus=";
		}
		case Gold -> {
			return "xqZRADMdNemt/MrUG5clks9M4FbYbTtnnXxSF9klaoa2DDcp8XNntSQwytN/By//UzshoMVeM4m+AWix3qZM00kK1ZbrvmVt7uTPyuwEYgoCirdAoOS3TcAOpoInfiPVTW/cBKCAr6c9rFILQaGON0AWbMo16uGzY2OBQaCEWYuyx/dkw7jFaMNnrYewPc/vUcIToN92/8w3aXtOtjFl7wHuZ/ChpGd1I2tp6ixSTcjkjwNvFTJe2Pu2DCwtHTIrhTSmrpkMRguwqvjbQ0yXvxcP2gJMG00O8IkQ6cVkYu7eVfT2MgTVbUdNhy0rvB8JLkQRN3vqnS4GAIj9nDLSNbxo245IrGoLxFivu11LlL2xA4toxRiOTQLZanfdvrbTdHQcYDk+yFVcW4k5EeJ2oWfuoZM2vLgrZVQsWLpKKNhz0dp6ph4/KItkJ4qKrOwnx5KZHPuPQf1wq2vGPSztllWaY62wHaKV/FhGvU6iFQwvL0mP7+/VRkzs1P2T2qYOu1gkSIGPR62XWdPu4eclbuCrRi2VLyAy+QKc1cgw0ju5E6YSj8Mj5RG//cfdT7v4HRdbnMp6bmxSDn9mLsdP3fb1aFkheWn0N91umEr66vomMBu8isJ57ndNZcOI+ytZi/TJzYSfMgRTIP7spamZHk79t9L4F73OiMnTEmZ8Gvw=";
		}
		case Green -> {
			return "fHdacYc1oH2fn3VTEtCszBFH8EwkaFTdnE2gGae503rHxrWhXr45sVBEIxcFtuwkjd+ebDawpuuPXCcqMEiNsAs1PrjcqOwWpPgSRT2Gn7MR0CFTT1u4laumHet5kv4cVVFWF7DkUA9stgvkSewkbsdOIdR3f7rFOwEzUm+Iz03VVszG3LsWVZi8dBxywC0yhZcxMj9a6XBKlyhyyxDC2Ro2a0/PDBhsG/b75DvgU3XXMev/Q3cbeyermfx7/8zUMiFDYsZNWyobdhf3wr4Ac1icfxyewao+5ZbPj4gptyypAmkEAgiE+koH8X4FFlfF81MjcEVstFOGP61BizIrOwKwABDhdiCxhQPptbThC6eTzTdpAWiKNg7Edm4n9p47/dp5qr+hY6Ol5IuIcNDbh46Iri524npGvhJlegWwKCXC/f7eTolrSDqfOu08r+YqyCLQJZgNUsJgzAhc167gx5BHC5ecoPZN9EJmHRg19GXdo7JdzQBpbLv3izHUjQcwBGmJhT8+uoff6UGwTvff2dHvMO5OTY+VcNrqNMrlQN7PHAk2FxeJFp0nCmyNmdAM+DohnskBjbP04XMCBAvEQ9bt2dmKnxWf/AFKtq+JsXuv3XtqAd/x5ppUZ3YBO8ZHU8WBkzjOolzfwGWI+4N5DhWMzW6s2Dd1EKW+r/vu8hk=";
		}
		case Grey -> {
			return "RD6CmO2Ix3F3Is8wxi9/5lOfkYlLgELl2QEz0BZBTaT7W/G/6xS9GRGa2x4LtM/jCi3U3jwUgjhc4sL/qeyuDawYR+ZibR2GwNXhyunoFIvja8ELkdBO9xu+Ma/7nayTI3PQeMMQ3eiATxZN8WbJS43CSVBmUZFqhNem55w/1J8p84v/i/bff4CEDWn4j8ZrG/Pe+0JCHCj/N1mBScb7KJ14Axyk8K4jDuIUOEeLsQmKnOCT+BhUoS/QIoA4vWmg8RvKiuCkPBnnlun9ue06MBstbadICUgL3g47tIGyRxyEdlXoTDwp6mFKUlAMqbEpeEc+5ZUrlNqP4+B5FlsTI+ZjYa/BsDM5PynZY5ISVuKxny6nfKlO3KV8oBGEMoDlMF0dYPLvL5zzzXyvpV74fIbhWE6H9ZdNsKXrmEgKe/NZjXqitIvoVysIN1UIL2tYS6JasqUo7CmvqVC0sj0cCSg7tZd4D4xMWIY6uoaXQC7WqKxhRMptoCecbzqZu+UplmET+iGPc+XmOhVX7Jo0AAAd8d1ZUqLDtDk8G6cw7oBVChrg8BrfqGM703wIOVlvZo1FO+pUMafEU+QGl5jPU+9uV+blXCWz5JQHLKNmoN0o0/JzkuBqM2rqruCTmZn5DynzacQFS6bQz8y+5Ytc2N2wlg0YuOFRkC16q8RUVRs=";
		}
		case Players -> {
			EntityPlayer craftPlayer = ((CraftPlayer) player).getHandle();
			Object[] propertys = craftPlayer.getProfile().getProperties().get("textures").toArray();
			if(propertys.length == 0) return "";
			return ((Property) propertys[0]).getSignature();
		}
		default -> {
			return "";
		}
	}
		}	
 public String getSkinTexture(@Nullable Player player) {
	 switch (this) {
		 case DarkAqua -> {
			 return "ewogICJ0aW1lc3RhbXAiIDogMTY1OTI2NjUwNzg2OSwKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzQ3VwY2FrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kYjc4NzczMThjNDg0ZTAzMWI2ZDU4N2RlMjk5YjRhYWI4ZGYwMjc4MWJlZmIyODMxNDFiN2NkMWJjODkxMDU0IgogICAgfQogIH0KfQ==";
		 }
		 case Gold -> {
			 return "ewogICJ0aW1lc3RhbXAiIDogMTY1OTI2Njc5NjQzNCwKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzQ3VwY2FrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85YmJkNzJlMzMwNjE4NGJjNmE5M2FhY2I4YTdjZWQ5ZTg3MTNjMjRlN2U0NmRhOGRmNzc2YTA5YzdhNWQ4YTA0IgogICAgfQogIH0KfQ==";
		 }
		 case Green -> {
			 return "ewogICJ0aW1lc3RhbXAiIDogMTY1OTI2NzI3ODc0MCwKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzQ3VwY2FrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81NTI1NWE2YTJlNDkwMGVjMTQ5OWQxMGIwN2EyZjg5MjUyYjg5ZjM4NWJlNmU2MjRkMTMwODZjMGY1ZjYzZWQ4IgogICAgfQogIH0KfQ==";
		 }
		 case Grey -> {
			 return "ewogICJ0aW1lc3RhbXAiIDogMTY1OTI2NzUxMTE0MCwKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzQ3VwY2FrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mOTU2MzZhNmEwZjRiOTc5ZTFiN2JhNjRhYzBlOTEzNGQxYzcyODc2MmZmNTljNDBmNTc5N2MxZGZhOWNjMjc1IgogICAgfQogIH0KfQ==";
		 }
		 case Players -> {
			 EntityPlayer craftPlayer = ((CraftPlayer) player).getHandle();
			 Object[] propertys = craftPlayer.getProfile().getProperties().get("textures").toArray();
			 if(propertys.length == 0) return "";
			 return ((Property) propertys[0]).getValue();
		 }
		 default -> {
			 return "";
		 }
	 }
	
	
}
}
