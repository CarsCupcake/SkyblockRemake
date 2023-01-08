package me.CarsCupcake.SkyblockRemake.Items;


import java.util.HashMap;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.SkyblockDamagePlayerToEntityExecuteEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.AdditionalManaCosts;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.scheduler.BukkitRunnable;

public interface AbilityManager<T extends Event>  {
	
	 boolean executeAbility(T event);
	HashMap<SkyblockPlayer, HashMap<String, AdditionalManaCosts>> additionalMana = new HashMap<>();

	
	@SuppressWarnings("deprecation")
	 static void abilityTrigger(PlayerInteractEvent event) {
		
		if(event.getItem() != null && event.getItem().getItemMeta() != null) {
			ItemManager manager = Items.SkyblockItems.get(event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
			if(manager!= null)
			if(manager.ability != null) {
				int totalManaCost;
				if(manager.abilityMana1AsPers)
					totalManaCost = ((int) ( Main.playermanacalc(SkyblockPlayer.getSkyblockPlayer(event.getPlayer())) * manager.abilityManaCost));
				else
					totalManaCost = (int) manager.abilityManaCost;
				
				if(additionalMana.get(SkyblockPlayer.getSkyblockPlayer(event.getPlayer())).containsKey(manager.itemID)) {
					totalManaCost += additionalMana.get(SkyblockPlayer.getSkyblockPlayer(event.getPlayer())).get(manager.itemID).amount;

				}

				AbilityPreExecuteEvent executeEvent = new AbilityPreExecuteEvent(manager.ability, totalManaCost, SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), event.getAction());
				Bukkit.getPluginManager().callEvent(executeEvent);
				if(executeEvent.isCancelled())
					return;
				totalManaCost = executeEvent.getPayedMana();

				boolean hasActivated = false;

				if(manager.abilityType == AbilityType.LeftOrRightClick && AbilityType.isNoSneak(manager.abilityType2)){
					if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana >= totalManaCost) {


						

							if(!allowToFire(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), AbilityType.LeftOrRightClick)){
								event.getPlayer().sendMessage("§cOn Coolown!");
								return;
							}
							if(manager.abilityCD > 0)
								startCooldown(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), manager.abilityCD * 20L, AbilityType.LeftOrRightClick);


							SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setMana(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana - totalManaCost);
							Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
							try {

								manager.ability.getClass().newInstance().executeAbility(event);
								if(totalManaCost > 0)
									SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName + "§b)");
								hasActivated = true;
							} catch (InstantiationException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								event.getPlayer().sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
								hasActivated = true;
							}
						
					}else{
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§c§lNOT ENOUGHT MANA");
					}
					}



				if(!hasActivated)
if(manager.abilityType == AbilityType.RightClick &&( event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))

				if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana >= totalManaCost) {

					if(!(event.getPlayer().isSneaking() && (manager.abilityType2 == AbilityType.SneakRightClick || manager.abilityType2 == AbilityType.SneakLeftOrRightClick))) {

						if(!allowToFire(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), AbilityType.RightClick)){
							event.getPlayer().sendMessage("§cOn Coolown!");
							return;
						}
						if(manager.abilityCD > 0)
						 startCooldown(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), manager.abilityCD * 20L, AbilityType.RightClick);


						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setMana(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana - totalManaCost);
						Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
						try {

							manager.ability.getClass().newInstance().executeAbility(event);
							if(totalManaCost > 0)
								SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName + "§b)");
						} catch (InstantiationException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							event.getPlayer().sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
						}
					}
					}else{
					SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§c§lNOT ENOUGHT MANA");
					}
				
				if(!hasActivated)
				if(manager.abilityType == AbilityType.LeftClick &&( event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)))
					if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana >= totalManaCost) {
						if(!(event.getPlayer().isSneaking() && (manager.abilityType2 == AbilityType.SneakLeftClick || manager.abilityType2 == AbilityType.SneakLeftOrRightClick))) {
							if(!allowToFire(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), AbilityType.LeftClick)){
								event.getPlayer().sendMessage("§cOn Coolown!");
								return;
							}
							if(manager.abilityCD > 0)
								startCooldown(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), manager.abilityCD * 20L, AbilityType.LeftClick);

							SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setMana(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana - totalManaCost);
							Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
							try {

								manager.ability.getClass().newInstance().executeAbility(event);
								if(totalManaCost > 0)

									SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName + "§b)");
							} catch (InstantiationException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								event.getPlayer().sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
							}
						}
						
					}else{
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§c§lNOT ENOUGHT MANA");
					}

				hasActivated = false;
				if(manager.abilityType == AbilityType.SneakLeftOrRightClick && event.getPlayer().isSneaking()){
					if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana >= totalManaCost) {




						if(!allowToFire(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), AbilityType.SneakLeftOrRightClick)){
							event.getPlayer().sendMessage("§cOn Coolown!");
							return;
						}
						if(manager.abilityCD > 0)
							startCooldown(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), manager.abilityCD * 20L, AbilityType.SneakLeftOrRightClick);


						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setMana(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana - totalManaCost);
						Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
						try {

							manager.ability.getClass().newInstance().executeAbility(event);
							if(totalManaCost > 0)
								SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName + "§b)");
							hasActivated = true;
						} catch (InstantiationException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							event.getPlayer().sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
							hasActivated = true;
						}

					}else{
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§c§lNOT ENOUGHT MANA");
					}
				}
				
				
				
				if(!hasActivated)
if(manager.abilityType == AbilityType.SneakRightClick &&( (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && event.getPlayer().isSneaking() ))
	if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana >= totalManaCost) {
		if(!allowToFire(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), AbilityType.SneakRightClick)){
			event.getPlayer().sendMessage("§cOn Coolown!");
			return;
		}
		if(manager.abilityCD > 0)
			startCooldown(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), manager.abilityCD * 20L, AbilityType.SneakRightClick);
		SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setMana(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana - totalManaCost);
						Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()) );
		try {

			manager.ability.getClass().newInstance().executeAbility(event);
			if(totalManaCost > 0)
				SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName + "§b)");
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			event.getPlayer().sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
		}
						
					}else{
		SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§c§lNOT ENOUGHT MANA");
					}
				
				if(!hasActivated)
				if(manager.abilityType == AbilityType.SneakLeftClick &&(event.getPlayer().isSneaking() &&  (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK))))
					if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana >= totalManaCost) {
						if(!allowToFire(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), AbilityType.SneakLeftClick)){
							event.getPlayer().sendMessage("§cOn Coolown!");
							return;
						}
						if(manager.abilityCD > 0)
							startCooldown(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), manager.abilityCD * 20L, AbilityType.SneakLeftClick);
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setMana(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana - totalManaCost);
						Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()) );
						try {

							manager.ability.getClass().newInstance().executeAbility(event);
							if(totalManaCost > 0)
							 SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName + "§b)");
						} catch (InstantiationException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							event.getPlayer().sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
						}
						
					}else{
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§c§lNOT ENOUGHT MANA");
					}
				
				
				
				
				
			}
		}
		
		//2. Ability

		if(event.getItem() != null && event.getItem().getItemMeta() != null) {
			ItemManager manager = Items.SkyblockItems.get(event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
			if(manager!= null)
			 if(manager.ability2 != null) {
				 int totalManaCost;
				 if(manager.abilityMana2AsPers)
					 totalManaCost = ((int) ( Main.playermanacalc(SkyblockPlayer.getSkyblockPlayer(event.getPlayer())) * manager.manacost2));
				 else
					 totalManaCost =  manager.manacost2;

				 if(additionalMana.get(SkyblockPlayer.getSkyblockPlayer(event.getPlayer())).containsKey(manager.itemID)) {
					 totalManaCost += additionalMana.get(SkyblockPlayer.getSkyblockPlayer(event.getPlayer())).get(manager.itemID).amount;

				 }

				 AbilityPreExecuteEvent executeEvent = new AbilityPreExecuteEvent(manager.ability2, totalManaCost, SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), event.getAction());
				 Bukkit.getPluginManager().callEvent(executeEvent);
				 totalManaCost = executeEvent.getPayedMana();
				 if(executeEvent.isCancelled())
					 return;


				 boolean hasActivated = false;
				 if(manager.abilityType2 == AbilityType.LeftOrRightClick && AbilityType.isNoSneak(manager.abilityType)){
					 if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana >= totalManaCost) {




						 if(!allowToFire(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), AbilityType.LeftOrRightClick)){
							 event.getPlayer().sendMessage("§cOn Coolown!");
							 return;
						 }
						 if(manager.abilityCD > 0)
							 startCooldown(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability2.getClass(), manager.cooldown2 * 20L, AbilityType.LeftOrRightClick);


						 SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setMana(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana - totalManaCost);
						 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
						 try {

							 manager.ability2.getClass().newInstance().executeAbility(event);
							 if(totalManaCost > 0)
								 SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName2 + "§b)");
							 hasActivated = true;
						 } catch (InstantiationException | IllegalAccessException e) {
							 // TODO Auto-generated catch block
							 e.printStackTrace();
							 event.getPlayer().sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
							hasActivated = true;
						 }

					 }else{
						 SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§c§lNOT ENOUGHT MANA");
					 }
				 }
				 if(!hasActivated)
if(manager.abilityType2 == AbilityType.RightClick &&( event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))

					if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana >= totalManaCost) {
						if(event.getPlayer().isSneaking() && (manager.abilityType == AbilityType.SneakRightClick || manager.abilityType == AbilityType.SneakLeftOrRightClick))
							return;
						if(!allowToFire(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability2.getClass(), AbilityType.RightClick)){
							event.getPlayer().sendMessage("§cOn Coolown!");
							return;
						}
						if(manager.abilityCD > 0)
							startCooldown(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability2.getClass(), manager.cooldown2 * 20L, AbilityType.RightClick);
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setMana(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana - totalManaCost);
						Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()) );
						try {

							manager.ability2.getClass().newInstance().executeAbility(event);
							if(totalManaCost > 0)
							 SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName2 + "§b)");
						} catch (InstantiationException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							event.getPlayer().sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
						}

					}else{
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§c§lNOT ENOUGHT MANA");
					}

if(!hasActivated)
				if(manager.abilityType2 == AbilityType.LeftClick &&( event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)))

					if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana >= totalManaCost) {
						if(event.getPlayer().isSneaking() && (manager.abilityType == AbilityType.SneakRightClick || manager.abilityType == AbilityType.SneakLeftOrRightClick))
							return;
						if(!allowToFire(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability2.getClass(), AbilityType.LeftClick)){
							event.getPlayer().sendMessage("§cOn Coolown!");
							return;
						}
						if(manager.abilityCD > 0)
							startCooldown(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability2.getClass(), manager.cooldown2 * 20L, AbilityType.LeftClick);
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setMana(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana - totalManaCost);
						Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()) );
						try {

							manager.ability2.getClass().newInstance().executeAbility(event);
							if(totalManaCost > 0)
								SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName2 + "§b)");
						} catch (InstantiationException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							event.getPlayer().sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
						}

					}else{
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§c§lNOT ENOUGHT MANA");
					}

				  hasActivated = false;
				 if(manager.abilityType2 == AbilityType.SneakLeftOrRightClick && event.getPlayer().isSneaking()){
					 if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana >= totalManaCost) {




						 if(!allowToFire(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability.getClass(), AbilityType.SneakLeftOrRightClick)){
							 event.getPlayer().sendMessage("§cOn Coolown!");
							 return;
						 }
						 if(manager.abilityCD > 0)
							 startCooldown(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability2.getClass(), manager.cooldown2 * 20L, AbilityType.SneakLeftOrRightClick);


						 SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setMana(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana - totalManaCost);
						 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
						 try {

							 manager.ability2.getClass().newInstance().executeAbility(event);
							 if(totalManaCost > 0)
								 SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName2 + "§b)");
							 hasActivated = true;
						 } catch (InstantiationException | IllegalAccessException e) {
							 // TODO Auto-generated catch block
							 e.printStackTrace();
							 event.getPlayer().sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
							 hasActivated = true;
						 }

					 }else{
						 SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§c§lNOT ENOUGHT MANA");
					 }
				 }

if(!hasActivated)
                  if(manager.abilityType2 == AbilityType.SneakRightClick &&( (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && event.getPlayer().isSneaking() )

					if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana >= totalManaCost) {
						if(!allowToFire(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability2.getClass(), AbilityType.SneakRightClick)){
							event.getPlayer().sendMessage("§cOn Coolown!");
							return;
						}
						if(manager.abilityCD > 0)
							startCooldown(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability2.getClass(), manager.cooldown2 * 20L, AbilityType.SneakRightClick);
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setMana(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana - totalManaCost);
						Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()) );
						try {

							manager.ability2.getClass().newInstance().executeAbility(event);
							if(totalManaCost > 0)
								SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName2 + "§b)");
						} catch (InstantiationException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							event.getPlayer().sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
						}

					}else{
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§c§lNOT ENOUGHT MANA");
					}



if(!hasActivated)
				if(manager.abilityType2 == AbilityType.SneakLeftClick &&(event.getPlayer().isSneaking() &&  (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK))))

					if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana >= totalManaCost) {
						if(!allowToFire(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability2.getClass(), AbilityType.SneakLeftClick)){
							event.getPlayer().sendMessage("§cOn Coolown!");
							return;
						}
						if(manager.abilityCD > 0)
							startCooldown(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), manager.ability2.getClass(), manager.cooldown2 * 20L,AbilityType.SneakLeftClick);
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setMana(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).currmana - totalManaCost);
						Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()) );
						try {

							manager.ability2.getClass().newInstance().executeAbility(event);
							if(totalManaCost > 0)
								SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName2 + "§b)");
						} catch (InstantiationException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							event.getPlayer().sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
						}

					}else{
						SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setTempDefenceString("§c§lNOT ENOUGHT MANA");
					}





			}
		}





	}

	@SuppressWarnings("deprecation")
	static void abilityTrigger(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) event.getDamager());

			if(player.getItemInHand().getItemMeta() == null || player.getItemInHand().getItemMeta().getPersistentDataContainer() == null)
				return;

			ItemManager manager = Items.SkyblockItems.get(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
			if(manager!= null)
				if(manager.ability != null) {
					if(manager.abilityType == AbilityType.EntityHit) {
						int totalManaCost;
						if(manager.abilityMana1AsPers)
							totalManaCost = ((int) ( Main.playermanacalc(player) * manager.abilityManaCost));
						else
							totalManaCost = (int) manager.abilityManaCost;
						AbilityPreExecuteEvent executeEvent = new AbilityPreExecuteEvent(manager.ability, totalManaCost, player, Action.PHYSICAL);
						Bukkit.getPluginManager().callEvent(executeEvent);
						if(executeEvent.isCancelled())
							return;
						totalManaCost = executeEvent.getPayedMana();
						if(player.currmana >= totalManaCost) {
							if(!allowToFire(player, manager.ability.getClass(), AbilityType.EntityHit)){
								player.sendMessage("§cOn Coolown!");
								return;
							}
							if(manager.abilityCD > 0)
								startCooldown(SkyblockPlayer.getSkyblockPlayer(player), manager.ability.getClass(), manager.abilityCD * 20L, AbilityType.EntityHit);

							player.setMana(player.currmana - totalManaCost);
							Main.updatebar(player);
							try {

								manager.ability.getClass().newInstance().executeAbility(event);
								if(totalManaCost > 0)
									player.setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName + "§b)");
							} catch (InstantiationException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								player.sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
							}

						}else{
							player.setTempDefenceString("§c§lNOT ENOUGHT MANA");
						}
					}
				}





			if(manager!= null)
				if(manager.ability2 != null) {
					int totalManaCost;
					if(manager.abilityMana2AsPers)
						totalManaCost = ((int) ( Main.playermanacalc(player) * manager.manacost2));
					else
						totalManaCost = (int) manager.manacost2;

					if(additionalMana.get(player).containsKey(manager.itemID)) {
						totalManaCost += additionalMana.get(player).get(manager.itemID).amount;

					}
					AbilityPreExecuteEvent executeEvent = new AbilityPreExecuteEvent(manager.ability, totalManaCost, player, Action.PHYSICAL);
					Bukkit.getPluginManager().callEvent(executeEvent);
					totalManaCost = executeEvent.getPayedMana();
					if(executeEvent.isCancelled())
						return;
					if(manager.abilityType2 == AbilityType.EntityHit) {
						if(player.currmana >= totalManaCost) {
							if(!allowToFire(player, manager.ability2.getClass(), AbilityType.EntityHit)){
								player.sendMessage("§cOn Coolown!");
								return;
							}
							if(manager.abilityCD > 0)
								startCooldown(player, manager.ability2.getClass(), manager.cooldown2 * 20L, AbilityType.EntityHit);
							player.setMana(player.currmana - totalManaCost);
							Main.updatebar(player);

							try {

								manager.ability2.getClass().newInstance().executeAbility(event);
								if(totalManaCost > 0)
									player.setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName2 + "§b)");
							} catch (InstantiationException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								player.sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
							}

						}else{
							player.setTempDefenceString("§c§lNOT ENOUGHT MANA");
						}
					}
				}



		}
	}
	@SuppressWarnings("deprecation")
	static void abilityTrigger(DamagePrepairEvent event) {
		if(event.getCalculator().getType() == SkyblockDamageEvent.DamageType.PlayerToEntity)
		{
			SkyblockPlayer player = event.getPlayer();

			if(player.getItemInHand().getItemMeta() == null || player.getItemInHand().getItemMeta().getPersistentDataContainer() == null)
				return;

			ItemManager manager = Items.SkyblockItems.get(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
			if(manager!= null)
				if(manager.ability != null) {
					if(manager.abilityType == AbilityType.SkyblockPreHit) {
						int totalManaCost;
						if(manager.abilityMana1AsPers)
							totalManaCost = ((int) ( Main.playermanacalc(player) * manager.abilityManaCost));
						else
							totalManaCost = (int) manager.abilityManaCost;
						AbilityPreExecuteEvent executeEvent = new AbilityPreExecuteEvent(manager.ability, totalManaCost, player, Action.PHYSICAL);
						Bukkit.getPluginManager().callEvent(executeEvent);
						if(executeEvent.isCancelled())
							return;
						totalManaCost = executeEvent.getPayedMana();
						if(player.currmana >= totalManaCost) {
							if(!allowToFire(player, manager.ability.getClass(), AbilityType.SkyblockPreHit)){
								player.sendMessage("§cOn Coolown!");
								return;
							}
							if(manager.abilityCD > 0)
								startCooldown(SkyblockPlayer.getSkyblockPlayer(player), manager.ability.getClass(), manager.abilityCD * 20L, AbilityType.SkyblockPreHit);

							player.setMana(player.currmana - totalManaCost);
							Main.updatebar(player);
							try {

								manager.ability.getClass().newInstance().executeAbility(event);
								if(totalManaCost > 0)
									player.setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName + "§b)");
							} catch (InstantiationException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								player.sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
							}

						}else{
							player.setTempDefenceString("§c§lNOT ENOUGHT MANA");
						}
					}
				}





			if(manager!= null)
				if(manager.ability2 != null) {
					int totalManaCost;
					if(manager.abilityMana2AsPers)
						totalManaCost = ((int) ( Main.playermanacalc(player) * manager.manacost2));
					else
						totalManaCost = (int) manager.manacost2;

					if(additionalMana.get(player).containsKey(manager.itemID)) {
						totalManaCost += additionalMana.get(player).get(manager.itemID).amount;

					}
					AbilityPreExecuteEvent executeEvent = new AbilityPreExecuteEvent(manager.ability, totalManaCost, player, Action.PHYSICAL);
					Bukkit.getPluginManager().callEvent(executeEvent);
					totalManaCost = executeEvent.getPayedMana();
					if(executeEvent.isCancelled())
						return;
					if(manager.abilityType2 == AbilityType.SkyblockPreHit) {
						if(player.currmana >= totalManaCost) {
							if(!allowToFire(player, manager.ability2.getClass(), AbilityType.SkyblockPreHit)){
								player.sendMessage("§cOn Coolown!");
								return;
							}
							if(manager.abilityCD > 0)
								startCooldown(player, manager.ability2.getClass(), manager.cooldown2 * 20L, AbilityType.SkyblockPreHit);
							player.setMana(player.currmana - totalManaCost);
							Main.updatebar(player);

							try {

								manager.ability2.getClass().newInstance().executeAbility(event);
								if(totalManaCost > 0)
									player.setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName2 + "§b)");
							} catch (InstantiationException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								player.sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
							}

						}else{
							player.setTempDefenceString("§c§lNOT ENOUGHT MANA");
						}
					}
				}



		}
	}

	@SuppressWarnings("deprecation")
	static void abilityTrigger(SkyblockDamagePlayerToEntityExecuteEvent event) {
		if(event.getCalculator().getType() == SkyblockDamageEvent.DamageType.PlayerToEntity && !event.getCalculator().isMagic())
		{
			SkyblockPlayer player = event.getPlayer();

			if(player.getItemInHand().getItemMeta() == null || player.getItemInHand().getItemMeta().getPersistentDataContainer() == null)
				return;

			ItemManager manager = Items.SkyblockItems.get(player.getItemInHand().getItemMeta().getPersistentDataContainer()
					.get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
			if(manager!= null)
				if(manager.ability != null) {
					if(manager.abilityType == AbilityType.AfterHit) {
						int totalManaCost;
						if(manager.abilityMana1AsPers)
							totalManaCost = ((int) ( Main.playermanacalc(player) * manager.abilityManaCost));
						else
							totalManaCost = (int) manager.abilityManaCost;
						AbilityPreExecuteEvent executeEvent = new AbilityPreExecuteEvent(manager.ability, totalManaCost, player, Action.PHYSICAL);
						Bukkit.getPluginManager().callEvent(executeEvent);
						if(executeEvent.isCancelled())
							return;
						totalManaCost = executeEvent.getPayedMana();
						if(player.currmana >= totalManaCost) {
							if(!allowToFire(player, manager.ability.getClass(), AbilityType.AfterHit)){
								player.sendMessage("§cOn Coolown!");
								return;
							}
							if(manager.abilityCD > 0)
								startCooldown(SkyblockPlayer.getSkyblockPlayer(player), manager.ability.getClass(), manager.abilityCD * 20L, AbilityType.AfterHit);

							player.setMana(player.currmana - totalManaCost);
							Main.updatebar(player);
							try {

								manager.ability.getClass().newInstance().executeAbility(event);
								if(totalManaCost > 0)
									player.setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName + "§b)");
							} catch (InstantiationException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								player.sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
							}

						}else{
							player.setTempDefenceString("§c§lNOT ENOUGHT MANA");
						}
					}
				}





			if(manager!= null)
				if(manager.ability2 != null) {
					int totalManaCost;
					if(manager.abilityMana2AsPers)
						totalManaCost = ((int) ( Main.playermanacalc(player) * manager.manacost2));
					else
						totalManaCost = (int) manager.manacost2;

					if(additionalMana.get(player).containsKey(manager.itemID)) {
						totalManaCost += additionalMana.get(player).get(manager.itemID).amount;

					}
					AbilityPreExecuteEvent executeEvent = new AbilityPreExecuteEvent(manager.ability, totalManaCost, player, Action.PHYSICAL);
					Bukkit.getPluginManager().callEvent(executeEvent);
					totalManaCost = executeEvent.getPayedMana();
					if(executeEvent.isCancelled())
						return;
					if(manager.abilityType2 == AbilityType.AfterHit) {
						if(player.currmana >= totalManaCost) {
							if(!allowToFire(player, manager.ability2.getClass(), AbilityType.AfterHit)){
								player.sendMessage("§cOn Coolown!");
								return;
							}
							if(manager.abilityCD > 0)
								startCooldown(player, manager.ability2.getClass(), manager.cooldown2 * 20L, AbilityType.AfterHit);
							player.setMana(player.currmana - totalManaCost);
							Main.updatebar(player);

							try {

								manager.ability2.getClass().newInstance().executeAbility(event);
								if(totalManaCost > 0)
									player.setTempDefenceString("§b-" + totalManaCost + " Mana (§6" + manager.abilityName2 + "§b)");
							} catch (InstantiationException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								player.sendMessage("§cAbility failed to execute §7(" + e.getClass().getSimpleName() + ")");
							}

						}else{
							player.setTempDefenceString("§c§lNOT ENOUGHT MANA");
						}
					}
				}



		}
	}



	static void  startCooldown(SkyblockPlayer player, Class<? extends AbilityManager> abilityManagerClass, long time, AbilityType type){
		Bundle<Class<? extends AbilityManager>, AbilityType> b = new Bundle<>(abilityManagerClass, type);
		player.addCooldown(b);
		new BukkitRunnable() {
			@Override
			public void run() {
				player.removeCooldown(b);
			}
		}.runTaskLater(Main.getMain(), time);

	}
	static boolean  allowToFire(SkyblockPlayer player, final Class<? extends AbilityManager> ability, AbilityType type){
		boolean isIn = false;
		for(Bundle<Class<? extends AbilityManager>, AbilityType> b : player.getCooldowns()){
			if(b.getFirst().equals(ability) && b.getLast().equals(type)){
				isIn = true;
			}
		}
		return !isIn;

	}
	
}
