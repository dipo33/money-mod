package sk.dipo.money.handlers;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sk.dipo.money.guis.ContainerWallet;
import sk.dipo.money.guis.GuiWallet;
import sk.dipo.money.guis.InventoryWallet;
import sk.dipo.money.items.ItemWallet;

public class GuiHandler implements IGuiHandler {

	public static final int GUI_WALLET = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_WALLET:
			ItemStack stack = player.inventory.getStackInSlot(player.inventory.currentItem);
			InventoryWallet inv = ItemWallet.readFromNBT(stack);
			return new ContainerWallet(player.inventory, inv);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_WALLET:
			ItemStack stack = player.inventory.getStackInSlot(player.inventory.currentItem);
			InventoryWallet inv = ItemWallet.readFromNBT(stack);
			return new GuiWallet(player.inventory, inv);
		default:
			return null;
		}
	}

}
