package sk.dipo.money.handler;

import java.util.ArrayList;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.database.Database;
import sk.dipo.money.item.MoneyItems;
import sk.dipo.money.utils.Config;
import sk.dipo.money.utils.Utils;

public class MyEventHandler {

	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onEntityDropEvent(LivingDropsEvent event) {
		if (!Config.shouldMobsDropMoney)
			return;
		if (event.entity instanceof EntityZombie) {
			if (!((EntityZombie) event.entity).isVillager()) {
				ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.zombieDropMin, Config.zombieDropMax);
				event.drops.addAll(itemsToDrop);
			} else {
				ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.zombieVillagerDropMin, Config.zombieVillagerDropMax);
				event.drops.addAll(itemsToDrop);
			}
		} else if (event.entity instanceof EntitySkeleton) {
			if (((EntitySkeleton) event.entity).getSkeletonType() == 0) {
				ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.skeletonDropMin, Config.skeletonDropMax);
				event.drops.addAll(itemsToDrop);
			} else if (((EntitySkeleton) event.entity).getSkeletonType() == 1) {
				ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.witherSkeletonDropMin, Config.witherSkeletonDropMax);
				event.drops.addAll(itemsToDrop);
			}
		} else if (event.entity instanceof EntitySpider) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.spiderDropMin, Config.spiderDropMax);
			event.drops.addAll(itemsToDrop);
		} else if (event.entity instanceof EntityCreeper) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.creeperDropMin, Config.creeperDropMax);
			event.drops.addAll(itemsToDrop);
		} else if (event.entity instanceof EntitySilverfish) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.silverfishDropMin, Config.silverfishDropMax);
			event.drops.addAll(itemsToDrop);
		} else if (event.entity instanceof EntitySlime) {
			if (event.entity instanceof EntityMagmaCube) {
				if (((EntityMagmaCube) event.entity).getSlimeSize() == 1) {
					ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.magmaCubeSDropMin, Config.magmaCubeSDropMax);
					event.drops.addAll(itemsToDrop);
				} else if (((EntityMagmaCube) event.entity).getSlimeSize() == 4) {
					ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.magmaCubeLDropMin, Config.magmaCubeLDropMax);
					event.drops.addAll(itemsToDrop);
				} else {
					ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.magmaCubeMDropMin, Config.magmaCubeMDropMax);
					event.drops.addAll(itemsToDrop);
				}
			} else {
				if (((EntitySlime) event.entity).getSlimeSize() == 1) {
					ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.slimeSDropMin, Config.slimeSDropMax);
					event.drops.addAll(itemsToDrop);
				} else if (((EntitySlime) event.entity).getSlimeSize() == 4) {
					ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.slimeLDropMin, Config.slimeLDropMax);
					event.drops.addAll(itemsToDrop);
				} else {
					ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.slimeMDropMin, Config.slimeMDropMax);
					event.drops.addAll(itemsToDrop);
				}
			}
		} else if (event.entity instanceof EntityCaveSpider) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.caveSpiderDropMin, Config.caveSpiderDropMax);
			event.drops.addAll(itemsToDrop);
		} else if (event.entity instanceof EntityGhast) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.ghastDropMin, Config.ghastDropMax);
			event.drops.addAll(itemsToDrop);
		} else if (event.entity instanceof EntityBlaze) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.blazeDropMin, Config.blazeDropMax);
			event.drops.addAll(itemsToDrop);
		} else if (event.entity instanceof EntityPigZombie) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.pigZombieDropMin, Config.pigZombieDropMax);
			event.drops.addAll(itemsToDrop);
		} else if (event.entity instanceof EntityEnderman) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.endermanDropMin, Config.endermanDropMax);
			event.drops.addAll(itemsToDrop);
		} else if (event.entity instanceof EntityWitch) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.witchDropMin, Config.witchDropMax);
			event.drops.addAll(itemsToDrop);
		} else if (event.entity instanceof EntityWither) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.witherDropMin, Config.witherDropMax);
			event.drops.addAll(itemsToDrop);
		} else if (event.entity instanceof EntityDragon) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, Config.enderDragonDropMin, Config.enderDragonDropMax);
			event.drops.addAll(itemsToDrop);
		}
	}

	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onPlayerJoin(EntityJoinWorldEvent event) {
		if (event.world.isRemote)
			return;
		if (!(event.entity instanceof EntityPlayer))
			return;

		EntityPlayer player = (EntityPlayer) event.entity;

		if (!MoneyMod.db.exists("Players", player.getUniqueID().toString() + ".Balance")) {
			MoneyMod.db.set("Players", player.getUniqueID().toString() + ".Balance", 50000);
			player.inventory.addItemStackToInventory(new ItemStack(MoneyItems.creditCard));
		}
	}

	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onWorldLoad(WorldEvent.Load event) {
		if (event.world.isRemote)
			return;
		System.out.println("CreateIt");
		MoneyMod.db = new Database("dipomoney");
		MoneyMod.db.put("Players");
	}
}