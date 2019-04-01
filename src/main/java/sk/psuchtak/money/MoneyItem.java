package sk.psuchtak.money;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MoneyItem extends Item {

	public MoneyItem(String name) {
		setTextureName(Reference.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName(name);
	}
}
