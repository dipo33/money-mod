package sk.dipo.money.network.packet.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import sk.dipo.money.network.packet.ByteBufferUtils;
import sk.dipo.money.tileentity.TileEntityATM;
import sk.dipo.money.utils.Location;

public class OpenCloseAtmMessage implements IMessage {
	
	private boolean isAtmOpenable;
	private Location loc;

	public OpenCloseAtmMessage() {
	}

	public OpenCloseAtmMessage(boolean isAtmOpened, int x, int y, int z) {
		this.isAtmOpenable = isAtmOpened;
		this.loc = new Location(null, x, y, z);
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		isAtmOpenable = ByteBufferUtils.readBooleanFromBuffer(buffer);
		loc = ByteBufferUtils.readLocationFromBuffer(buffer);
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		ByteBufferUtils.writeBooleanToBuffer(buffer, isAtmOpenable);
		ByteBufferUtils.writeLocationToBuffer(buffer, loc);
	}

	public static class Handler extends AbstractClientMessageHandler<OpenCloseAtmMessage> {
		@Override
		public IMessage handleClientMessage(EntityPlayer player, OpenCloseAtmMessage message, MessageContext ctx) {
			System.out.println("Is ATM openable ? " + message.isAtmOpenable);
			System.out.println("X: " + message.loc.getX());
			System.out.println("Y: " + message.loc.getY());
			System.out.println("Z: " + message.loc.getZ());
			TileEntityATM atm = (TileEntityATM) Minecraft.getMinecraft().theWorld.getTileEntity(message.loc.getX(),
					message.loc.getY(), message.loc.getZ());
			atm.openable = message.isAtmOpenable;
			
			return null;
		}
	}
}