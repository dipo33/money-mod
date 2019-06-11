package sk.dipo.money.network.packet.server;

import java.util.ArrayList;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.tileentity.TileEntityATM;
import sk.dipo.money.utils.Utils;

public class WithdrawMessage implements IMessage {

	private int value;
	private int x, y, z;

	public WithdrawMessage(int value, int x, int y, int z) {
		this.value = value;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public WithdrawMessage() {
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		this.value = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeInt(this.value);
		buffer.writeInt(this.x);
		buffer.writeInt(this.y);
		buffer.writeInt(this.z);
	}

	public static class Handler extends AbstractServerMessageHandler<WithdrawMessage> {
		@Override
		public IMessage handleServerMessage(EntityPlayer player, WithdrawMessage message, MessageContext ctx) {
			NBTTagCompound nbt = player.getHeldItem().getTagCompound();

			int balance = MoneyMod.db.getInteger("Players", nbt.getString("OwnerUUID") + ".Balance");

			System.out.println("BALANCE IS: " + balance + ", VALUE IS: " + message.value);
			if (balance >= message.value) {
				System.out.println("WITHDRAWING");
				TileEntityATM atm = (TileEntityATM) player.worldObj.getTileEntity(message.x, message.y, message.z);
				ArrayList<ItemStack> items = Utils.getItemStacksByValue(message.value);

				for (ItemStack stack : items) {
					atm.withdrawMoney(stack, player, message.x, message.y, message.z);
				}
			} else {
				System.out.println("NOT ENOUGH MONEY");
			}
			return null;
		}
	}
}