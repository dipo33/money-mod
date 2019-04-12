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

	public AtmMovingTextMessage() {
	}

	public AtmMovingTextMessage(String text, short phase) {
		this.text = text;
		this.phase = phase;
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		text = ByteBufUtils.readUTF8String(buffer);
		phase = buffer.readShort();
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, text);
		buffer.writeShort(phase);
	}

	public static class Handler extends AbstractClientMessageHandler<AtmMovingTextMessage> {
		@Override
		public IMessage handleClientMessage(EntityPlayer player, AtmMovingTextMessage message, MessageContext ctx) {
			System.out.println("PRISLA SPRAVA YEEE: " + message.text);
			if (Minecraft.getMinecraft().currentScreen instanceof GuiATM) {
				((GuiATM) Minecraft.getMinecraft().currentScreen).setMessage(message.text);
				((GuiATM) Minecraft.getMinecraft().currentScreen).setPhase(message.phase);
			}

			return null;
		}
	}
}