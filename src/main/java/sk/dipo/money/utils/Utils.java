package sk.dipo.money.utils;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.oredict.OreDictionary;
import sk.dipo.money.item.MoneyItems;

public class Utils {
	
	public static Item getCoinByValue(int value) {
		switch (value) {
		case 1:
			return MoneyItems.cent1;
		case 2:
			return MoneyItems.cent2;
		case 5:
			return MoneyItems.cent5;
		case 10:
			return MoneyItems.cent10;
		case 20:
			return MoneyItems.cent20;
		case 50:
			return MoneyItems.cent50;
		case 100:
			return MoneyItems.euro1;
		case 200:
			return MoneyItems.euro2;
		case 500:
			return MoneyItems.euro5;
		case 1000:
			return MoneyItems.euro10;
		case 2000:
			return MoneyItems.euro20;
		case 5000:
			return MoneyItems.euro50;
		case 10000:
			return MoneyItems.euro100;
		case 20000:
			return MoneyItems.euro200;
		case 50000:
			return MoneyItems.euro500;
		}

		return null;
	}

	public static int getValueByMoney(ItemStack item) {
		if (item == null)
			return 0;
		else if (item.getItem() == MoneyItems.cent1)
			return item.stackSize * 1;
		else if (item.getItem() == MoneyItems.cent2)
			return item.stackSize * 2;
		else if (item.getItem() == MoneyItems.cent5)
			return item.stackSize * 5;
		else if (item.getItem() == MoneyItems.cent10)
			return item.stackSize * 10;
		else if (item.getItem() == MoneyItems.cent20)
			return item.stackSize * 20;
		else if (item.getItem() == MoneyItems.cent50)
			return item.stackSize * 50;
		else if (item.getItem() == MoneyItems.euro1)
			return item.stackSize * 100;
		else if (item.getItem() == MoneyItems.euro2)
			return item.stackSize * 200;
		else if (item.getItem() == MoneyItems.euro5)
			return item.stackSize * 500;
		else if (item.getItem() == MoneyItems.euro10)
			return item.stackSize * 1000;
		else if (item.getItem() == MoneyItems.euro20)
			return item.stackSize * 2000;
		else if (item.getItem() == MoneyItems.euro50)
			return item.stackSize * 5000;
		else if (item.getItem() == MoneyItems.euro100)
			return item.stackSize * 10000;
		else if (item.getItem() == MoneyItems.euro200)
			return item.stackSize * 20000;
		else if (item.getItem() == MoneyItems.euro500)
			return item.stackSize * 50000;

		return 0;
	}

	public static boolean isItemMoney(ItemStack stack) {
		ArrayList<ItemStack> moneys = OreDictionary.getOres("moneyDipo");
		for (ItemStack money : moneys)
			if (OreDictionary.itemMatches(money, stack, false)) {
				return true;
			}

		return false;
	}

	public static ArrayList<ItemStack> getItemStacksByValue(int value) {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		int coins[] = { 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 };

		for (int coin : coins) {
			int temp = 0;
			while (true) {
				if (coin <= value) {
					value -= coin;
					temp++;
				} else {
					if (temp != 0) {
						ItemStack stack = new ItemStack(getCoinByValue(coin), temp);
						items.add(stack);
					}
					break;
				}
			}
		}

		return items;
	}

	public static ArrayList<EntityItem> randomCoinValue(LivingDropsEvent event, int low, int high) {
		ArrayList<EntityItem> itemsToDrop = new ArrayList<EntityItem>();
		int value = new Random().nextInt(high - low + 1);
		value += low;
		System.out.println(value);
		int coins[] = { 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 };
		while (value > 0) {
			for (int coin : coins) {
				if (coin <= value) {
					value -= coin;
					ItemStack drop = new ItemStack(getCoinByValue(coin), 1);
					itemsToDrop.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, drop));
					break;
				}
			}
		}

		return itemsToDrop;
	}
}