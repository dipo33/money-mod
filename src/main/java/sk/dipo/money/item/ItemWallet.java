package sk.dipo.money.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.handler.GuiHandler;

public class ItemWallet extends MoneyItem {

	public ItemWallet(String name) {
		super(name);
		setMaxStackSize(1);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			player.openGui(MoneyMod.instance, GuiHandler.GUI_WALLET, world, 0, 0, 0);
		}
		
		return stack;
	}
}