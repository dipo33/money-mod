package sk.dipo.money.guis;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import sk.dipo.money.items.MoneyItems;

public class InventoryWallet implements IInventory {

	ItemStack base;

	public InventoryWallet(ItemStack base) {
		this.base = base;
	}

	private String walletCustomName;
	private ItemStack[] itemStacks = new ItemStack[27];

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.itemStacks.length; ++i) {
			if (this.itemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.itemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName()) {
			nbt.setString("CustomName", this.walletCustomName);
		}
		return nbt;
	}

	@Override
	public int getSizeInventory() {
		return itemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return itemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		System.out.println("FUCKIN decr");
		if (this.itemStacks[i] != null) {
			ItemStack itemstack;

			if (this.itemStacks[i].stackSize <= count) {
				itemstack = this.itemStacks[i];
				this.itemStacks[i] = null;

				return itemstack;
			} else {
				itemstack = this.itemStacks[i].splitStack(count);

				if (this.itemStacks[i].stackSize == 0) {
					this.itemStacks[i] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		base.setTagCompound(writeToNBT(base.hasTagCompound() ? base.getTagCompound() : new NBTTagCompound()));
		System.out.println("FUCKIN CLOSE");
		if (itemStacks[i] != null) {
			ItemStack itemstack = itemStacks[i];
			itemStacks[i] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		base.setTagCompound(writeToNBT(base.hasTagCompound() ? base.getTagCompound() : new NBTTagCompound()));
		System.out.println("SETTIN " + i);
		this.itemStacks[i] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return hasCustomInventoryName() ? walletCustomName : "container.wallet";
	}

	public void setInventoryName(String name) {
		this.walletCustomName = name;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return walletCustomName != null && walletCustomName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void closeInventory() {
		System.out.println("WRITING TO NBT");
		base.setTagCompound(writeToNBT(base.getTagCompound()));
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if (stack.getItem() == MoneyItems.cent1)
			return true;
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}
}