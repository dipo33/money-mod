package sk.dipo.money.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import sk.dipo.money.network.packet.client.AbstractClientMessageHandler;
import sk.dipo.money.network.packet.client.AtmMovingTextMessage;
import sk.dipo.money.network.packet.client.OpenCloseAtmMessage;
import sk.dipo.money.network.packet.server.CreatePinCodeMessage;
import sk.dipo.money.network.packet.server.LoginMessage;
import sk.dipo.money.network.packet.server.SignCreditCardMessage;
import sk.dipo.money.network.packet.server.WithdrawMessage;
import sk.dipo.money.utils.Reference;

public class PacketDispatcher {
	private static byte packetId = 0;

	private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

	public static final void registerPackets() {
		PacketDispatcher.registerMessage(OpenCloseAtmMessage.Handler.class, OpenCloseAtmMessage.class);
		PacketDispatcher.registerMessage(AtmMovingTextMessage.Handler.class, AtmMovingTextMessage.class);
		PacketDispatcher.registerMessage(SignCreditCardMessage.Handler.class, SignCreditCardMessage.class);
		PacketDispatcher.registerMessage(CreatePinCodeMessage.Handler.class, CreatePinCodeMessage.class);
		PacketDispatcher.registerMessage(LoginMessage.Handler.class, LoginMessage.class);
		PacketDispatcher.registerMessage(WithdrawMessage.Handler.class, WithdrawMessage.class);
	}

	private static final <REQ extends IMessage, REPLY extends IMessage> void registerMessage(
			Class<? extends IMessageHandler<REQ, REPLY>> handlerClass, Class<REQ> messageClass) {
		Side side = AbstractClientMessageHandler.class.isAssignableFrom(handlerClass) ? Side.CLIENT : Side.SERVER;
		PacketDispatcher.dispatcher.registerMessage(handlerClass, messageClass, packetId++, side);
	}

	public static final void sendTo(IMessage message, EntityPlayerMP player) {
		PacketDispatcher.dispatcher.sendTo(message, player);
	}

	public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
		PacketDispatcher.dispatcher.sendToAllAround(message, point);
	}

	public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z,
			double range) {
		PacketDispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
	}

	public static final void sendToAllAround(IMessage message, EntityPlayer player, double range) {
		PacketDispatcher.sendToAllAround(message, player.worldObj.provider.dimensionId, player.posX, player.posY,
				player.posZ, range);
	}

	public static final void sendToDimension(IMessage message, int dimensionId) {
		PacketDispatcher.dispatcher.sendToDimension(message, dimensionId);
	}

	public static final void sendToServer(IMessage message) {
		PacketDispatcher.dispatcher.sendToServer(message);
	}
}