package sk.dipo.money.network.packet.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.network.packet.client.AtmMovingTextMessage;
import sk.dipo.money.tileentity.TileEntityATM;

public class LoginMessage implements IMessage {

	private String PIN;
	private int x, y, z;

	public LoginMessage(String PIN, int x, int y, int z) {
		this.PIN = PIN;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public LoginMessage() {
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		this.PIN = ByteBufUtils.readUTF8String(buffer);
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, this.PIN);
		buffer.writeInt(this.x);
		buffer.writeInt(this.y);
		buffer.writeInt(this.z);
	}

	public static class Handler extends AbstractServerMessageHandler<LoginMessage> {
		@Override
		public IMessage handleServerMessage(EntityPlayer player, LoginMessage message, MessageContext ctx) {

			NBTTagCompound nbt = player.getHeldItem().getTagCompound();
			if (nbt.getString("PIN").equalsIgnoreCase(message.PIN))
				return new AtmMovingTextMessage("msg.atm.welcome", (short) 3, MoneyMod.db.getInteger("Players", player.getUniqueID().toString() + ".Balance"));
			else {
				TileEntityATM atm = (TileEntityATM) player.worldObj.getTileEntity(message.x, message.y, message.z);
				atm.attempts--;

				if (atm.attempts < 1) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
					return new AtmMovingTextMessage("msg.atm.card_eaten", (short) 4);
				} else
					return new AtmMovingTextMessage("msg.atm.bad_login", (short) 2, atm.attempts);
			}
		}
	}
}