package sk.dipo.money.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import sk.dipo.money.container.ContainerATM;
import sk.dipo.money.tileentity.TileEntityATM;
import sk.dipo.money.utils.Reference;

public class GuiATM extends GuiContainer {

	private static final ResourceLocation atmGuiTexture = new ResourceLocation(Reference.MODID, "textures/gui/container/atm.png");
	private final InventoryPlayer inventoryPlayer;
	private final IInventory inventoryATM;

	public GuiATM(InventoryPlayer inventoryPlayer, TileEntityATM inventoryATM) {
		super(new ContainerATM(inventoryPlayer, inventoryATM));
		this.inventoryPlayer = inventoryPlayer;
		this.inventoryATM = inventoryATM;
		this.xSize = 243;
		this.ySize = 222;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		this.fontRendererObj.drawString(this.inventoryATM.hasCustomInventoryName() ? this.inventoryATM.getInventoryName() : I18n.format(this.inventoryATM.getInventoryName(), new Object[0]), 42, 5, 4210752);
		this.fontRendererObj.drawString(I18n.format(this.inventoryPlayer.getInventoryName(), new Object[0]), 42, this.ySize - 93, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.atm_in", new Object[0]), 42, 31, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.atm_out", new Object[0]), 42, 81, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(atmGuiTexture);
        int k = (this.width - this.xSize + 67) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}