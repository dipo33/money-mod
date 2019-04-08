package sk.dipo.money.register;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import sk.dipo.money.item.MoneyItems;

public class ItemRegister {
	public static void registerItems() {
		registerItem(MoneyItems.cent1);
		registerItem(MoneyItems.cent2);
		registerItem(MoneyItems.cent5);
		registerItem(MoneyItems.cent10);
		registerItem(MoneyItems.cent20);
		registerItem(MoneyItems.cent50);
		registerItem(MoneyItems.euro1);
		registerItem(MoneyItems.euro2);
		registerItem(MoneyItems.euro5);
		registerItem(MoneyItems.euro10);
		registerItem(MoneyItems.euro20);
		registerItem(MoneyItems.euro50);
		registerItem(MoneyItems.euro100);
		registerItem(MoneyItems.euro200);
		registerItem(MoneyItems.euro500);
		registerItem(MoneyItems.wallet);
	}

	private static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
}
