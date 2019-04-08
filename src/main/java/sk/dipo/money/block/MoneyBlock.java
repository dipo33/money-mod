package sk.dipo.money.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.utils.Reference;

public class MoneyBlock extends Block {

	protected MoneyBlock(Material material, String name) {
		super(material);
		setBlockName(name);
		setBlockTextureName(Reference.MODID + ":" + name);
		setCreativeTab(MoneyMod.moneyTab);
	}

}
