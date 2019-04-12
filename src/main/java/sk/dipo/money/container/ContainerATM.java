package sk.dipo.money.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import sk.dipo.money.container.slot.SlotATMInput;
import sk.dipo.money.container.slot.SlotATMOutput;
import sk.dipo.money.network.PacketDispatcher;
import sk.dipo.money.network.packet.client.OpenCloseAtmMessage;
import sk.dipo.money.tileentity.TileEntityATM;

public class ContainerATM extends Container {

	private final IInventory inventoryATM;
	private final TileEntityATM te;

	private static final int INV_START = TileEntityATM.INV_SIZE, INV_END = INV_START + 26, HOTBAR_START = INV_END + 1,
			HOTBAR_END = HOTBAR_START + 8;

	public ContainerATM(InventoryPlayer playerInventory, TileEntityATM inventoryATM) {
		this.inventoryATM = inventoryATM;
		this.te = inventoryATM;

		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				for (int k = 0; k < 9; ++k) {
					if (i == 0)
						this.addSlotToContainer(
								new SlotATMInput(inventoryATM, k + j * 9 + i * 18, 42 + k * 18, j * 18 + i * 50 + 40));
					else
						this.addSlotToContainer(
								new SlotATMOutput(inventoryATM, k + j * 9 + i * 18, 42 + k * 18, j * 18 + i * 50 + 40));
				}
			}
		}

		for (int j = 0; j < 3; ++j) {
			for (int k = 0; k < 9; ++k) {
				this.addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 42 + k * 18, 140 + j * 18));
			}
		}

		for (int j = 0; j < 9; ++j) {
			this.addSlotToContainer(new Slot(playerInventory, j, 42 + j * 18, 198));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return inventoryATM.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < INV_START) {
				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else {
				SlotATMInput slotOut = (SlotATMInput) this.inventorySlots.get(0);
				if (slotOut.isItemValid(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 18, false)) {
						return null;
					}
				}
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
		if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) {
			return null;
		}

		return super.slotClick(slot, button, flag, player);
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		this.te.openable = true;
		if (!player.worldObj.isRemote)
			PacketDispatcher.sendToDimension(
					new OpenCloseAtmMessage(true, this.te.xCoord, this.te.yCoord, this.te.zCoord),
					player.worldObj.provider.dimensionId);
	}
}