package sk.dipo.money.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.tileentity.TileEntity;
import sk.dipo.money.network.PacketDispatcher;
import sk.dipo.money.network.packet.client.AtmMovingTextMessage;
import sk.dipo.money.utils.Utils;

public class TileEntityATM extends TileEntity implements ISidedInventory {

	private static final int[] SLOTS_TOP = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 };
	private static final int[] SLOTS_SIDE = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 };
	private static final int[] SLOTS_BOTTOM = { 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35 };

	public static final int INV_SIZE = 36;
	private ItemStack[] inventory = new ItemStack[INV_SIZE];
	private String customName;
	public boolean openable = true;
	public int attempts = 3;
	private EntityPlayer user;

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
			} else {
				setInventorySlotContents(slot, null);
			}
		}

		return stack;
	}

	public void withdrawMoney(ItemStack stack, EntityPlayer player, int x, int y, int z) {
		for (int i = 18; i < 36; i++) {
			if (stack == null || stack.stackSize == 0)
				return;
			if (getStackInSlot(i) == null || getStackInSlot(i).stackSize == 0) {
				setInventorySlotContents(i, stack);
				((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S2FPacketSetSlot(player.openContainer.windowId, i, stack));
				return;
			} else if (getStackInSlot(i).getItem() == stack.getItem() && getStackInSlot(i).stackSize != 64) {
				int newAmount = getStackInSlot(i).stackSize + stack.stackSize;
				if (newAmount < 65) {
					setInventorySlotContents(i, new ItemStack(stack.getItem(), newAmount));
					((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S2FPacketSetSlot(player.openContainer.windowId, i, getStackInSlot(i)));
					return;
				} else {
					int amountToRemove = 64 - getStackInSlot(i).stackSize;
					setInventorySlotContents(i, new ItemStack(stack.getItem(), 64));
					((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S2FPacketSetSlot(player.openContainer.windowId, i, getStackInSlot(i)));
					stack = new ItemStack(stack.getItem(), stack.stackSize - amountToRemove);
				}
			}
		}

		if (stack != null && stack.stackSize != 0) {
			EntityItem item = new EntityItem(player.worldObj, x, y, z, stack);
			player.worldObj.spawnEntityInWorld(item);
		}
	}

	public int depositMoney(EntityPlayer player) {
		int balance = 0;

		for (int i = 0; i < 18; i++) {
			balance += Utils.getValueByMoney(getStackInSlot(i));
			setInventorySlotContents(i, null);
			((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S2FPacketSetSlot(player.openContainer.windowId, i, null));
		}

		return balance;
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
	}

	@Override
	public String getInventoryName() {
		return hasCustomInventoryName() ? customName : "container.atm";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return customName != null && customName.length() > 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("ATMItems", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];

		if (nbt.hasKey("CustomName", 8)) {
			this.customName = nbt.getString("CustomName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.inventory.length) {
				this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inventory.length; ++i) {
			if (this.inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("ATMItems", nbttaglist);

		if (this.hasCustomInventoryName()) {
			nbt.setString("CustomName", this.customName);
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return Utils.isItemMoney(stack);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? SLOTS_BOTTOM : (side == 1 ? SLOTS_TOP : SLOTS_SIDE);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return (slot > -1 && slot < 18 && side != 0 && isItemValidForSlot(slot, stack));
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return (slot > 17 && slot < 36 && side == 0);
	}

	public EntityPlayer getUser() {
		return user;
	}

	public void setUser(EntityPlayer user) {
		this.user = user;
		ItemStack creditCard = user.getHeldItem();
		NBTTagCompound nbt = creditCard.getTagCompound();

		if (nbt != null && nbt.hasKey("OwnerUUID")) {
			if (nbt.hasKey("PIN")) {
				String ownerName = nbt.getString("OwnerName");
				PacketDispatcher.sendTo(new AtmMovingTextMessage("msg.atm.login", (short) 2, ownerName), (EntityPlayerMP) this.user);
			} else {
				PacketDispatcher.sendTo(new AtmMovingTextMessage("msg.atm.create_pin", (short) 1), (EntityPlayerMP) this.user);
			}
		} else {
			PacketDispatcher.sendTo(new AtmMovingTextMessage("msg.atm.not_signed", (short) 0), (EntityPlayerMP) this.user);
		}
	}
}