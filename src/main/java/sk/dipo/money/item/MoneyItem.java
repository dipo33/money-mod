package sk.dipo.money.item;

import net.minecraft.item.Item;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.utils.Reference;

public class MoneyItem extends Item {

	public MoneyItem(String name) {
		setTextureName(Reference.MODID + ":" + name);
		setCreativeTab(MoneyMod.moneyTab);
		setUnlocalizedName(name);
	}
}
