package sk.dipo.money.handler;

import java.util.ArrayList;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.database.Database;
import sk.dipo.money.item.MoneyItems;
import sk.dipo.money.utils.Utils;

public class MyEventHandler {

	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onEntityDropEvent(LivingDropsEvent event) {
		if (Utils.LOW_TIER.contains(event.entity.getClass()) || (event.entity instanceof EntityZombie && !((EntityZombie) event.entity).isVillager())) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, 1, 2);
			event.drops.addAll(itemsToDrop);
		} else if (Utils.MID_TIER.contains(event.entity.getClass())) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, 2, 5);
			event.drops.addAll(itemsToDrop);
		} else if (Utils.HIGH_TIER.contains(event.entity.getClass())) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, 5, 10);
			event.drops.addAll(itemsToDrop);
		} else if (Utils.RARE_TIER.contains(event.entity.getClass()) || (event.entity instanceof EntityZombie && ((EntityZombie) event.entity).isVillager())) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, 10, 20);
			event.drops.addAll(itemsToDrop);
		} else if (Utils.LEGENDARY_TIER.contains(event.entity.getClass())) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, 20, 50);
			event.drops.addAll(itemsToDrop);
		} else if (event.entity instanceof EntityDragon) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, 50, 100);
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