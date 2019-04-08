package sk.dipo.money.register;

import net.minecraftforge.oredict.OreDictionary;
import sk.dipo.money.item.MoneyItems;

public class OreDicts {
	
	public static void registerOreDicts() {
		OreDictionary.registerOre("moneyDipo", MoneyItems.cent1);
		OreDictionary.registerOre("moneyDipo", MoneyItems.cent2);
		OreDictionary.registerOre("moneyDipo", MoneyItems.cent5);
		OreDictionary.registerOre("moneyDipo", MoneyItems.cent10);
		OreDictionary.registerOre("moneyDipo", MoneyItems.cent20);
		OreDictionary.registerOre("moneyDipo", MoneyItems.cent50);
		OreDictionary.registerOre("moneyDipo", MoneyItems.euro1);
		OreDictionary.registerOre("moneyDipo", MoneyItems.euro2);
		OreDictionary.registerOre("moneyDipo", MoneyItems.euro5);
		OreDictionary.registerOre("moneyDipo", MoneyItems.euro10);
		OreDictionary.registerOre("moneyDipo", MoneyItems.euro20);
		OreDictionary.registerOre("moneyDipo", MoneyItems.euro50);
		OreDictionary.registerOre("moneyDipo", MoneyItems.euro100);
		OreDictionary.registerOre("moneyDipo", MoneyItems.euro200);
		OreDictionary.registerOre("moneyDipo", MoneyItems.euro500);
	}
}