package sk.dipo.money.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import sk.dipo.money.utils.Utils;

public class SlotWallet extends Slot {

	public SlotWallet(IInventory inventory, int i, int x, int y) {
		super(inventory, i, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return Utils.isItemMoney(stack);
	}
}