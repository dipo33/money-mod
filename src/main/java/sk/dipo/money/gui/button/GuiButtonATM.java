package sk.dipo.money.gui.button;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import sk.dipo.money.utils.Reference;

public class GuiButtonATM extends GuiButton {

	private static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/gui/container/atm.png");
	private final short type;

	public GuiButtonATM(int id, int xPos, int yPos, String displayString, short type) {
		super(id, xPos, yPos, (type == 0 || type == 1) ? 16 : 23, 16, displayString);
		this.type = type;
	}

	@Override
	public void drawButton(Minecraft minecraft, int mouseX, int mouseY) {
		if (this.visible) {
			FontRenderer fontrenderer = minecraft.fontRenderer;
			minecraft.getTextureManager().bindTexture(BUTTON_TEXTURES);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition
					&& mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			int k = this.getHoverState(this.field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			if (type == 0)
				this.drawTexturedModalRect(this.xPosition, this.yPosition, k * 16, 225, this.width, this.height);
			else if (type == 1)
				this.drawTexturedModalRect(this.xPosition, this.yPosition, k * 16 + 48, 225, this.width, this.height);
			else if (type == 2)
				this.drawTexturedModalRect(this.xPosition, this.yPosition, k * 23 + 96, 225, this.width, this.height);
			else
				this.drawTexturedModalRect(this.xPosition, this.yPosition, k * 23 + 165, 225, this.width, this.height);
			this.mouseDragged(minecraft, mouseX, mouseY);
			int l = 16777215;

			if (packedFGColour != 0) {
				l = packedFGColour;
			} else if (!this.enabled) {
				l = 10526880;
			} else if (this.field_146123_n) {
				l = 16777120;
			}

			this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2,
					this.yPosition + (this.height - 8) / 2, l);
		}
	}
}