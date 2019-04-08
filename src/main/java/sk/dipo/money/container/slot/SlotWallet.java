package sk.dipo.money.container.slot;

import java.util.ArrayList;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class SlotWallet extends Slot {

	public SlotWallet(IInventory inventory, int i, int x, int y) {
		super(inventory, i, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		ArrayList<ItemStack> moneys = OreDictionary.getOres("moneyDipo");
		for (ItemStack money : moneys)
			if (OreDictionary.itemMatches(money, stack, false)) {
				return true;
			}
		return false;
	}
}