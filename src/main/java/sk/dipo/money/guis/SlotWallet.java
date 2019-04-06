package sk.dipo.money.guis;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import sk.dipo.money.items.MoneyItems;

public class SlotWallet extends Slot {

	public SlotWallet(IInventory inventory, int i, int x, int y) {
		super(inventory, i, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack.getItem() == MoneyItems.cent1)
			return true;
		return false;
	}

}
