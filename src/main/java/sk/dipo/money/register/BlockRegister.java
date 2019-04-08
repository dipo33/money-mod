package sk.dipo.money.register;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import sk.dipo.money.block.MoneyBlocks;

public class BlockRegister {
	public static void registerBlocks() {
		registerBlock(MoneyBlocks.atm);
	}

	private static void registerBlock(Block block) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}
}
