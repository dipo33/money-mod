package sk.dipo.money;

import net.minecraft.item.Item;

public class MoneyItem extends Item {

	public MoneyItem(String name) {
		setTextureName(Reference.MODID + ":" + name);
		setCreativeTab(MoneyMod.moneyTab);
		setUnlocalizedName(name);
	}
}
