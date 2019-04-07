package sk.dipo.money.guis;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiWallet extends GuiContainer {

	private static final ResourceLocation grinderGuiTextures = new ResourceLocation("textures/gui/container/generic_54.png");
	private final InventoryPlayer inventoryPlayer;
	private final InventoryWallet inventoryWallet;

	public GuiWallet(InventoryPlayer inventoryPlayer, InventoryWallet inventoryWallet) {
		super(new ContainerWallet(inventoryPlayer, inventoryWallet));
		this.inventoryPlayer = inventoryPlayer;
		this.inventoryWallet = inventoryWallet;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		this.fontRendererObj.drawString(this.inventoryWallet.hasCustomInventoryName() ? this.inventoryWallet.getInventoryName() : I18n.format(this.inventoryWallet.getInventoryName(), new Object[0]), 8, 5, 4210752);
		this.fontRendererObj.drawString(I18n.format(this.inventoryPlayer.getInventoryName(), new Object[0]), 8, this.ySize - 93, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(grinderGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l - 1, 0, 0, this.xSize, 71);
        this.drawTexturedModalRect(k, l + 70, 0, 126, this.xSize, 96);
	}

}
