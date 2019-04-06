package sk.dipo.money.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.guis.InventoryWallet;
import sk.dipo.money.handlers.GuiHandler;

public class ItemWallet extends MoneyItem {

	public ItemWallet(String name) {
		super(name);
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.openGui(MoneyMod.instance, GuiHandler.GUI_WALLET, world, 0, 0, 0);
		return stack;
	}

	public static InventoryWallet readFromNBT(ItemStack stack) {
		NBTTagCompound nbt = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		InventoryWallet inventory = new InventoryWallet(stack);

		if (nbt.hasKey("CustomName", 8)) {
			inventory.setInventoryName(nbt.getString("CustomName"));
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < inventory.getSizeInventory()) {
				inventory.setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(nbttagcompound1));
			}
		}
		return inventory;
	}
}
