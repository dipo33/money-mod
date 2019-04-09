package sk.dipo.money.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import sk.dipo.money.tileentity.TileEntityATM;

public class BlockATM extends MoneyBlock implements ITileEntityProvider {

	protected BlockATM(String name) {
		super(Material.iron, name);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityATM();
	}
}