package sk.dipo.money.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.block.MoneyBlocks;
import sk.dipo.money.handler.GuiHandler;

public class ItemCreditCard extends MoneyItem {

	public ItemCreditCard(String name) {
		super(name);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return false;
		if (world.getBlock(x, y, z) != MoneyBlocks.atm)
			return false;
		player.openGui(MoneyMod.instance, GuiHandler.GUI_ATM, world, x, y, z);
		return true;
	}
}