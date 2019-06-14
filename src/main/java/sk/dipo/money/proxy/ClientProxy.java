package sk.dipo.money.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.register.CommonRegisters;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		MoneyMod.LOGGER.info("FMLPreInitializationEvent on Client side");
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		CommonRegisters.registerRenderers();
		MoneyMod.LOGGER.info("FMLInitializationEvent on Client side");
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		MoneyMod.LOGGER.info("FMLPostInitializationEvent on Client side");
	}

	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
}