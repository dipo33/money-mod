package sk.dipo.money.guis;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerWallet extends Container {

	private final IInventory walletInventory;
	@SuppressWarnings("unused")
	private final int sizeInventory;

	public ContainerWallet(InventoryPlayer playerInventory, IInventory inventory) {
		walletInventory = inventory;
		sizeInventory = walletInventory.getSizeInventory();

		for (int j = 0; j < 3; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new SlotWallet(walletInventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (int j = 0; j < 3; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 85 + j * 18));
            }
        }

        for (int j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new Slot(playerInventory, j, 8 + j * 18, 143));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return walletInventory.isUseableByPlayer(player);
	}
	
	//craftlisteners

}
