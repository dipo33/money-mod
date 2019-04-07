package sk.dipo.money.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.handlers.GuiHandler;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		MoneyMod.LOGGER.info("FMLPreInitializationEvent on Server side");
	}

	public void init(FMLInitializationEvent event) {
		MoneyMod.LOGGER.info("FMLInitializationEvent on Server side");
		NetworkRegistry.INSTANCE.registerGuiHandler(MoneyMod.instance, new GuiHandler());
	}

	public void postInit(FMLPostInitializationEvent event) {
		MoneyMod.LOGGER.info("FMLPostInitializationEvent on Server side");
	}

	public void serverStarting(FMLServerStartingEvent event) {
	}
}
