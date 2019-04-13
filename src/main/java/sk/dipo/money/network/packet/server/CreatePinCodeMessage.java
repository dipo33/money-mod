package sk.dipo.money.network.packet.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import sk.dipo.money.network.packet.client.AtmMovingTextMessage;

public class CreatePinCodeMessage implements IMessage {

	private String PIN;
	
	public CreatePinCodeMessage(String PIN) {
		this.PIN = PIN;
	}
	
	public CreatePinCodeMessage() {
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		this.PIN = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, this.PIN);
	}

	public static class Handler extends AbstractServerMessageHandler<CreatePinCodeMessage> {
		@Override
		public IMessage handleServerMessage(EntityPlayer player, CreatePinCodeMessage message, MessageContext ctx) {

			NBTTagCompound nbt = player.getHeldItem().getTagCompound();
			if (nbt == null)
				nbt = new NBTTagCompound();
			nbt.setString("PIN", message.PIN);
			player.getHeldItem().setTagCompound(nbt);

			return new AtmMovingTextMessage("msg.atm.signed_login", (short) 2);
		}
	}
}