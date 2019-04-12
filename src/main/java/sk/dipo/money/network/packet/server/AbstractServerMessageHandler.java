package sk.dipo.money.network.packet.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import sk.dipo.money.network.packet.AbstractMessageHandler;

public abstract class AbstractServerMessageHandler<T extends IMessage> extends AbstractMessageHandler<T> {
	public final IMessage handleClientMessage(EntityPlayer player, T message, MessageContext ctx) {
		return null;
	}
}
