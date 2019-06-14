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
import sk.dipo.money.network.packet.server.DepositMessage;
import sk.dipo.money.network.packet.server.LoginMessage;
import sk.dipo.money.network.packet.server.SignCreditCardMessage;
import sk.dipo.money.network.packet.server.WithdrawMessage;
import sk.dipo.money.tileentity.TileEntityATM;
import sk.dipo.money.utils.Reference;

public class GuiATM extends GuiContainer implements Runnable {

	private int posX, posY, posZ;

	private static final ResourceLocation atmGuiTexture = new ResourceLocation(Reference.MODID, "textures/gui/container/atm.png");
	private final InventoryPlayer inventoryPlayer;
	private final IInventory inventoryATM;
	private String message;
	private short messageType;
	private int money;
	private String name;
	private String PIN = "";
	private String pinCode = "  ";
	private short nextPinNum = 0;
	private int withdrawValue = 0;
	private boolean dot = false;
	private short dotPos = 0;

	/**
	 * Phase -1 - No phase
	 * Phase 0 - Signing credit card
	 * Phase 1 - Creating PIN code
	 * Phase 2 - Logging to account using PIN code
	 * Phase 3 - Welcome
	 * Phase 4 - Card eaten
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

	public void setParams(int phase) {
		this.phase = phase;
		this.messageType = 0;
	}

	public void setParams(int phase, int money) {
		this.phase = phase;
		this.messageType = 1;
		this.money = money;
	}

	public void setParams(int phase, String name) {
		this.phase = phase;
		this.messageType = 2;
		this.name = name;
	}

	public GuiATM(InventoryPlayer inventoryPlayer, TileEntityATM inventoryATM) {
		super(new ContainerATM(inventoryPlayer, inventoryATM));
		this.inventoryPlayer = inventoryPlayer;
		this.posX = inventoryATM.xCoord;
		this.posY = inventoryATM.yCoord;
		this.posZ = inventoryATM.zCoord;
		this.inventoryATM = inventoryATM;
		this.xSize = 243;
		this.ySize = 222;
		message = "";
		movingText = "";
		phase = -1;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(this.inventoryATM.hasCustomInventoryName() ? this.inventoryATM.getInventoryName()
				: I18n.format(this.inventoryATM.getInventoryName(), new Object[0]), 42, 5, 4210752);
		this.fontRendererObj.drawString(translate(this.inventoryPlayer.getInventoryName()), 42, this.ySize - 93, 4210752);
		this.fontRendererObj.drawString(translate("container.atm_in"), 42, 31, 4210752);
		this.fontRendererObj.drawString(translate("container.atm_out"), 42, 81, 4210752);
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
		this.buttonList.add(new GuiButtonATM(13, (this.width / 2) + 95, (this.height / 2) + 11, translate("char.atm.arrow_1"), (short) 2));
		this.buttonList.add(new GuiButtonATM(14, (this.width / 2) + 124, (this.height / 2) + 11, translate("char.atm.arrow_2"), (short) 3));
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
			if (PIN.length() != 4)
				return;
			System.out.println("Creating PIN code");
			PacketDispatcher.sendToServer(new CreatePinCodeMessage(PIN));
			clear();
		} else if (phase == 2) {
			if (PIN.length() != 4)
				return;
			System.out.println("Logging in...");
			PacketDispatcher.sendToServer(new LoginMessage(PIN, this.posX, this.posY, this.posZ));
			clear();
		} else if (phase == 3) {
			PacketDispatcher.sendToServer(new DepositMessage(this.posX, this.posY, this.posZ));
		}
	}

	private void decline() {
		if (phase == 0 || phase == 1 || phase == 2) {
			Minecraft.getMinecraft().thePlayer.closeScreen();
			System.out.println("Closing terminal...");
		} else if (phase == 3) {
			if (withdrawValue == 0)
				return;
			if (!dot || (dot && dotPos == 0))
				PacketDispatcher.sendToServer(new WithdrawMessage(withdrawValue * 100, this.posX, this.posY, this.posZ));
			else if (dot && dotPos == 1)
				PacketDispatcher.sendToServer(new WithdrawMessage(withdrawValue * 10, this.posX, this.posY, this.posZ));
			else if (dot && dotPos == 2)
				PacketDispatcher.sendToServer(new WithdrawMessage(withdrawValue, this.posX, this.posY, this.posZ));
			System.out.println("Withdrawing...");
		}
	}

	private void clear() {
		PIN = "";
		nextPinNum = 0;
		pinCode = "  ";
		withdrawValue = 0;
		dot = false;
		dotPos = 0;
	}

	private void dot() {
		if (phase == 3) {
			dot = true;
		}
	}

	private void number(int number) {
		if (phase == 1 || phase == 2) {
			if (nextPinNum < 4) {
				PIN += number;
				nextPinNum++;
				pinCode += "* ";
			}
		} else if (phase == 3) {
			if ((withdrawValue + "").length() == 7)
				return;

			if (withdrawValue != 0) {
				if (dot) {
					if (dotPos > 1)
						return;
					dotPos++;
					withdrawValue = Integer.parseInt((withdrawValue + "") + number);
					String temp = (withdrawValue + "").substring(0, (withdrawValue + "").length() - dotPos);
					pinCode = (temp.length() == 0 ? "0" : temp) + "." + (withdrawValue + "").substring((withdrawValue + "").length() - dotPos) + "€";
				} else {
					withdrawValue = Integer.parseInt((withdrawValue + "") + number);
					pinCode = withdrawValue + "€";
				}
			} else {
				if (dot) {
					dotPos++;
					withdrawValue = Integer.parseInt((withdrawValue + "") + number);
					if (withdrawValue < 10 && dotPos == 2) {
						pinCode = "0.0" + withdrawValue + "€";
					} else {
						String temp = (withdrawValue + "").substring(0, (withdrawValue + "").length() - dotPos); // TODO neda sa napisat 0.01
						pinCode = (temp.length() == 0 ? "0" : temp) + "." + (withdrawValue + "").substring((withdrawValue + "").length() - dotPos) + "€";
					}
				} else {
					withdrawValue = number;
					pinCode = number + "€";
				}
			}
		}
	}

	@Override
	public void run() {
		while (true)
			try {
				String temp = format(translate(message));
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
						temp += "        " + format(translate(message));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	private String format(String str) {
		if (messageType == 1) {
			str = str.replaceAll("@", "%");
			if (phase == 2)
				return String.format(str, money);
			else
				return String.format(str, toEur());
		} else if (messageType == 2) {
			str = str.replaceAll("@", "%");
			return String.format(str, name);
		} else {
			return str;
		}
	}

	private String toEur() {
		String monety = money + "";
		String part1, part2;

		if (money > 99) {
			part1 = monety.substring(0, monety.length() - 2);
			part2 = monety.substring(monety.length() - 2);
		} else {
			if (money > 9) {
				part1 = "0";
				part2 = monety;
			} else {
				part1 = "0";
				part2 = "0" + monety;
			}
		}
		return part1 + "." + part2;
	}

	private String translate(String str) {
		return I18n.format(str, new Object[0]);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		thread.stop();
	}
}