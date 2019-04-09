package sk.dipo.money.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import sk.dipo.money.utils.Utils;

public class InventoryWallet implements IInventory {

	private final ItemStack base;
	public static final int INV_SIZE = 27;

	private String walletCustomName;
	private ItemStack[] inventory = new ItemStack[INV_SIZE];

	public InventoryWallet(ItemStack stack) {
		this.base = stack;

		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}

		readFromNBT(stack.getTagCompound());
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize > count) {
				stack = stack.splitStack(count);
				onInventoryChanged();
			} else {
				setInventorySlotContents(slot, null);
			}
		}
		
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
		
		onInventoryChanged();
	}

	@Override
	public String getInventoryName() {
		return hasCustomInventoryName() ? walletCustomName : "container.wallet";
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
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return Utils.isItemMoney(stack);
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	private void onInventoryChanged() {
		for (int i = 0; i < getSizeInventory(); ++i) {
			if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0) {
				inventory[i] = null;
			}
		}

		writeToNBT(base.getTagCompound());
	}

	public void readFromNBT(NBTTagCompound nbt) {
		NBTTagList nbttaglist = nbt.getTagList("WalletItems", 10);

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int slot = nbttagcompound1.getByte("Slot") & 255;

			if (slot >= 0 && slot < getSizeInventory()) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		NBTTagCompound nbtt = nbt.getCompoundTag("display");
		if (nbtt != null && nbtt.hasKey("Name", 8)) {
			walletCustomName = nbtt.getString("Name");
		}
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inventory.length; ++i) {
			if (this.inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("WalletItems", nbttaglist);

		return nbt;
	}
}