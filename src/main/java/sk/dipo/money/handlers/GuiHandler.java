package sk.dipo.money.handlers;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import sk.dipo.money.guis.ContainerWallet;
import sk.dipo.money.guis.GuiWallet;
import sk.dipo.money.guis.InventoryWallet;

public class GuiHandler implements IGuiHandler {

	public static final int GUI_WALLET = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_WALLET:
			return new ContainerWallet(player.inventory, new InventoryWallet(player.getHeldItem()));
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_WALLET:
			return new GuiWallet(player.inventory, (InventoryWallet) new InventoryWallet(player.getHeldItem()));
		default:
			return null;
		}
	}

}
