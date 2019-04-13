package sk.dipo.money.network.packet.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import sk.dipo.money.gui.GuiATM;

public class AtmMovingTextMessage implements IMessage {

	private String text;
	private short phase;
	private short type;
	private int parInt;
	private String parString;

	public AtmMovingTextMessage() {
	}

	public AtmMovingTextMessage(String text, short phase) {
		this.text = text;
		this.phase = phase;
		this.type = 0;
	}

	public AtmMovingTextMessage(String text, short phase, int parInt) {
		this.text = text;
		this.phase = phase;
		this.type = 1;
		this.parInt = parInt;
	}

	public AtmMovingTextMessage(String text, short phase, String parString) {
		this.text = text;
		this.phase = phase;
		this.type = 2;
		this.parString = parString;
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		type = buffer.readShort();
		text = ByteBufUtils.readUTF8String(buffer);
		phase = buffer.readShort();
		if (type == 1)
			parInt = buffer.readInt();
		else if (type == 2)
			parString = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeShort(type);
		ByteBufUtils.writeUTF8String(buffer, text);
		buffer.writeShort(phase);
		if (type == 1)
			buffer.writeInt(parInt);
		else if (type == 2)
			ByteBufUtils.writeUTF8String(buffer, parString);
	}

	public static class Handler extends AbstractClientMessageHandler<AtmMovingTextMessage> {
		@Override
		public IMessage handleClientMessage(EntityPlayer player, AtmMovingTextMessage message, MessageContext ctx) {
			System.out.println("PRISLA SPRAVA YEEE: " + message.text);
			if (Minecraft.getMinecraft().currentScreen instanceof GuiATM) {
				((GuiATM) Minecraft.getMinecraft().currentScreen).setMessage(message.text);
				if (message.type == 0)
					((GuiATM) Minecraft.getMinecraft().currentScreen).setParams(message.phase);
				else if (message.type == 1)
					((GuiATM) Minecraft.getMinecraft().currentScreen).setParams(message.phase, message.parInt);
				else if (message.type == 2)
					((GuiATM) Minecraft.getMinecraft().currentScreen).setParams(message.phase, message.parString);
			}

			return null;
		}
	}
}