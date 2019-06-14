package sk.dipo.money.rendering.tileentity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import sk.dipo.money.models.ModelATM;
import sk.dipo.money.utils.Reference;

public class RenderATM extends TileEntitySpecialRenderer {

	private ModelATM model;
	private ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/model/atm.png");

	public RenderATM() {
		this.model = new ModelATM();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
		GL11.glRotated(180, 0, 0, 1);
		GL11.glRotated(90, 0, 1, 0);
		this.bindTexture(texture);
		GL11.glPushMatrix();
		if (tile.hasWorldObj())
			this.adjustRotatePivotViaMeta(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord);
		this.model.renderModel(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		meta--;
		GL11.glRotated(meta * 90, 0, 1F, 0);
	}
}