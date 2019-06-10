package sk.dipo.money.network.packet.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.network.packet.client.AtmMovingTextMessage;

public class LoginMessage implements IMessage {

	private String PIN;

	public LoginMessage(String PIN) {
		this.PIN = PIN;
	}

	public LoginMessage() {
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		this.PIN = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, this.PIN);
	}

	public static class Handler extends AbstractServerMessageHandler<LoginMessage> {
		@Override
		public IMessage handleServerMessage(EntityPlayer player, LoginMessage message, MessageContext ctx) {

			NBTTagCompound nbt = player.getHeldItem().getTagCompound();
			if (nbt.getString("PIN").equalsIgnoreCase(message.PIN))
				return new AtmMovingTextMessage("msg.atm.welcome", (short) 3, MoneyMod.db.getInteger("Players", player.getUniqueID().toString() + ".Balance"));
			else
				return new AtmMovingTextMessage("msg.atm.bad_login", (short) 2);
		}
	}
}