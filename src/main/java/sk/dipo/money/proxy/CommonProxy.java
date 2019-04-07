package sk.dipo.money.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.handlers.GuiHandler;
import sk.dipo.money.recipes.CraftingRecipes;
import sk.dipo.money.register.ItemRegister;
import sk.dipo.money.register.OreDicts;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ItemRegister.registerItems();
		MoneyMod.LOGGER.info("FMLPreInitializationEvent on Server side");
	}

	public void init(FMLInitializationEvent event) {
		CraftingRecipes.registerRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(MoneyMod.instance, new GuiHandler());
		OreDicts.registerOreDicts();
		MoneyMod.LOGGER.info("FMLInitializationEvent on Server side");
	}

	public void postInit(FMLPostInitializationEvent event) {
		MoneyMod.LOGGER.info("FMLPostInitializationEvent on Server side");
	}

	public void serverStarting(FMLServerStartingEvent event) {
	}
}
