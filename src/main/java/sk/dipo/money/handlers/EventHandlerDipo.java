package sk.dipo.money.handlers;

import java.util.ArrayList;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import sk.dipo.money.utils.Utils;

public class EventHandlerDipo {

	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onEvent(LivingDropsEvent event) {
		if (Utils.LOW_TIER.contains(event.entity.getClass()) || (event.entity instanceof EntityZombie && !((EntityZombie) event.entity).isVillager())) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, 1, 2);
			event.drops.addAll(itemsToDrop);
		} else if (Utils.MID_TIER.contains(event.entity.getClass())) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, 2, 5);
			event.drops.addAll(itemsToDrop);
		} else if (Utils.HIGH_TIER.contains(event.entity.getClass())) {
			ArrayList<EntityItem> itemsToDrop = Utils.randomCoinValue(event, 5, 10);
			event.drops.addAll(itemsToDrop);
		} else if (Utils.RARE_TIER.contains(event.entity.getClass())
				|| (event.entity instanceof EntityZombie && ((EntityZombie) event.entity).isVillager())) {
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
}
