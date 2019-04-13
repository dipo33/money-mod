package sk.dipo.money.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import sk.dipo.money.container.ContainerATM;
import sk.dipo.money.gui.button.GuiButtonATM;
import sk.dipo.money.network.PacketDispatcher;
import sk.dipo.money.network.packet.server.CreatePinCodeMessage;
import sk.dipo.money.network.packet.server.SignCreditCardMessage;
import sk.dipo.money.tileentity.TileEntityATM;
import sk.dipo.money.utils.Reference;

public class GuiATM extends GuiContainer implements Runnable {

	private static final ResourceLocation atmGuiTexture = new ResourceLocation(Reference.MODID,
			"textures/gui/container/atm.png");
	private final InventoryPlayer inventoryPlayer;
	private final IInventory inventoryATM;
	private String message;
	private String PIN = "";
	private String pinCode = "  ";
	private short nextPinNum = 0;

	/**
	 * Phase -1 - No phase 
	 * Phase 0 - Signing credit card 
	 * Phase 1 - Creating PIN code
	 * Phase 2 - Logging to account using PIN code
	 * Phase 3 - Welcome
	 */
	private int phase;

	private String movingText;
	private Thread thread;
	// private final TileEntityATM te;

	@SuppressWarnings("deprecation")
	public void setMessage(String message) {
		this.message = message;
		if (thread != null && thread.isAlive()) {
			thread.stop();
		}

		thread = new Thread(this);
		thread.start();
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public GuiATM(InventoryPlayer inventoryPlayer, TileEntityATM inventoryATM) {
		super(new ContainerATM(inventoryPlayer, inventoryATM));
		this.inventoryPlayer = inventoryPlayer;
		this.inventoryATM = inventoryATM;
		this.xSize = 243;
		this.ySize = 222;
		message = "";
		movingText = "";
		phase = -1;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj
				.drawString(this.inventoryATM.hasCustomInventoryName() ? this.inventoryATM.getInventoryName()
						: I18n.format(this.inventoryATM.getInventoryName(), new Object[0]), 42, 5, 4210752);
		this.fontRendererObj.drawString(I18n.format(this.inventoryPlayer.getInventoryName(), new Object[0]), 42,
				this.ySize - 93, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.atm_in", new Object[0]), 42, 31, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.atm_out", new Object[0]), 42, 81, 4210752);
		this.fontRendererObj.drawString(movingText, 44, 17, 16777215);
		this.fontRendererObj.drawString(pinCode, 219, 27, 16777215);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(atmGuiTexture);
		int k = (this.width - this.xSize + 68) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.add(new GuiButtonATM(1, (this.width / 2) + 95, (this.height / 2) - 25, "1", (short) 0));
		this.buttonList.add(new GuiButtonATM(2, (this.width / 2) + 113, (this.height / 2) - 25, "2", (short) 0));
		this.buttonList.add(new GuiButtonATM(3, (this.width / 2) + 131, (this.height / 2) - 25, "3", (short) 0));
		this.buttonList.add(new GuiButtonATM(4, (this.width / 2) + 95, (this.height / 2) - 43, "4", (short) 0));
		this.buttonList.add(new GuiButtonATM(5, (this.width / 2) + 113, (this.height / 2) - 43, "5", (short) 0));
		this.buttonList.add(new GuiButtonATM(6, (this.width / 2) + 131, (this.height / 2) - 43, "6", (short) 0));
		this.buttonList.add(new GuiButtonATM(7, (this.width / 2) + 95, (this.height / 2) - 61, "7", (short) 0));
		this.buttonList.add(new GuiButtonATM(8, (this.width / 2) + 113, (this.height / 2) - 61, "8", (short) 0));
		this.buttonList.add(new GuiButtonATM(9, (this.width / 2) + 131, (this.height / 2) - 61, "9", (short) 0));
		this.buttonList.add(new GuiButtonATM(10, (this.width / 2) + 95, (this.height / 2) - 7, "0", (short) 0));
		this.buttonList.add(new GuiButtonATM(11, (this.width / 2) + 113, (this.height / 2) - 7, ".", (short) 0));
		this.buttonList.add(new GuiButtonATM(12, (this.width / 2) + 131, (this.height / 2) - 7, "C", (short) 1));
		this.buttonList.add(new GuiButtonATM(13, (this.width / 2) + 95, (this.height / 2) + 11, I18n.format("char.atm.arrow_1", new Object[0]), (short) 2));
		this.buttonList.add(new GuiButtonATM(14, (this.width / 2) + 124, (this.height / 2) + 11, I18n.format("char.atm.arrow_2", new Object[0]), (short) 3));
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.id == 13) {
			confirm();
		} else if (button.id == 14) {
			decline();
		} else if (button.id > 0 && button.id < 10) {
			number(button.id);
		} else if (button.id == 10) {
			number(0);
		} else if (button.id == 11) {
			dot();
		} else if (button.id == 12) {
			clear();
		}
	}

	private void confirm() {
		if (phase == 0) {
			System.out.println("Confirmed. Signing...");
			PacketDispatcher.sendToServer(new SignCreditCardMessage());
		} else if (phase == 1) {
			System.out.println("Creating PIN code");
			PacketDispatcher.sendToServer(new CreatePinCodeMessage(PIN));
			clear();
		}
	}

	private void decline() {
		if (phase == 0 || phase == 1 || phase == 2) {
			Minecraft.getMinecraft().thePlayer.closeScreen();
			System.out.println("Closing terminal...");
		}
	}

	private void clear() {
		PIN = "";
		nextPinNum = 0;
		pinCode = "  ";
	}

	private void dot() {
	}

	private void number(int number) {
		if (phase == 1 || phase == 2) {
			if (nextPinNum < 4) {
				PIN += number;
				nextPinNum++;
				pinCode += "* ";
			}
		}
	}

	@Override
	public void run() {
		while (true)
			try {
				String temp = I18n.format(message, new Object[0]);
				movingText = temp;
				if (this.fontRendererObj.getStringWidth(temp) > 156) {
					movingText = this.fontRendererObj.trimStringToWidth(temp, 156);
				}

				for (int i = 0; i < 30; i++) {
					Thread.sleep(50);
				}
				while (this.fontRendererObj.getStringWidth(temp) > 156) {
					Thread.sleep(100);
					temp = temp.substring(1);
					movingText = this.fontRendererObj.trimStringToWidth(temp, 156);
					if (this.fontRendererObj.getStringWidth(temp) < 200)
						temp += "        " + I18n.format(message, new Object[0]);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		thread.stop();
	}
}