package sk.dipo.money.network.packet.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import sk.dipo.money.network.packet.client.AtmMovingTextMessage;

public class SignCreditCardMessage implements IMessage {

	public SignCreditCardMessage() {
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
	}

	@Override
	public void toBytes(ByteBuf buffer) {
	}

	public static class Handler extends AbstractServerMessageHandler<SignCreditCardMessage> {
		@Override
		public IMessage handleServerMessage(EntityPlayer player, SignCreditCardMessage message, MessageContext ctx) {

			NBTTagCompound nbt = player.getHeldItem().getTagCompound();
			if (nbt == null)
				nbt = new NBTTagCompound();
			nbt.setString("OwnerUUID", player.getUniqueID().toString());
			player.getHeldItem().setTagCompound(nbt);

			return new AtmMovingTextMessage("msg.atm.create_pin", (short) 1);
		}
	}
}