package sk.dipo.money.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import sk.dipo.money.MoneyMod;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MoneyMod.LOGGER.info("FMLPreInitializationEvent on Client side");
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		MoneyMod.LOGGER.info("FMLInitializationEvent on Client side");
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		MoneyMod.LOGGER.info("FMLPostInitializationEvent on Client side");
	}
}