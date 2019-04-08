package sk.dipo.money;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import sk.dipo.money.item.MoneyItems;

public class MoneyTab extends CreativeTabs {

	public MoneyTab() {
		super("money_tab");
	}

	@Override
	public Item getTabIconItem() {
		return MoneyItems.euro2;
	}
}
