package sk.dipo.money.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.handlers.GuiHandler;

public class ClientProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		// DEBUG
		System.out.println("on Client side");
	}

	@Override
	public void init(FMLInitializationEvent event) {
		// DEBUG
		NetworkRegistry.INSTANCE.registerGuiHandler(MoneyMod.instance, new GuiHandler());
		System.out.println("on Client side");
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// DEBUG
		System.out.println("on Client side");
	}

	@Override
	public void serverStarting(FMLServerStartingEvent event) {
		// This will never get called on client side
	}
}