package sk.dipo.money.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import sk.dipo.money.container.ContainerATM;
import sk.dipo.money.container.ContainerWallet;
import sk.dipo.money.gui.GuiATM;
import sk.dipo.money.gui.GuiWallet;
import sk.dipo.money.inventory.InventoryWallet;
import sk.dipo.money.tileentity.TileEntityATM;

public class GuiHandler implements IGuiHandler {

	public static final int GUI_WALLET = 0;
	public static final int GUI_ATM = 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_WALLET:
			return new ContainerWallet(player.inventory, new InventoryWallet(player.getHeldItem()));
		case GUI_ATM:
			TileEntityATM entity = (TileEntityATM) world.getTileEntity(x, y, z);
			return new ContainerATM(player.inventory, entity);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_WALLET:
			return new GuiWallet(player.inventory, (InventoryWallet) new InventoryWallet(player.getHeldItem()));
		case GUI_ATM:
			TileEntityATM entity = (TileEntityATM) world.getTileEntity(x, y, z);
			return new GuiATM(player.inventory, entity);
		default:
			return null;
		}
	}
}