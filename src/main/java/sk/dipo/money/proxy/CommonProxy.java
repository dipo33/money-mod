package sk.dipo.money.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.recipe.CraftingRecipes;
import sk.dipo.money.register.BlockRegister;
import sk.dipo.money.register.CommonRegisters;
import sk.dipo.money.register.ItemRegister;
import sk.dipo.money.register.OreDicts;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ItemRegister.registerItems();
		BlockRegister.registerBlocks();
		MoneyMod.LOGGER.info("FMLPreInitializationEvent on Server side");
	}

	public void init(FMLInitializationEvent event) {
		OreDicts.registerOreDicts();
		CraftingRecipes.registerRecipes();
		CommonRegisters.registerVillagers();
		CommonRegisters.registerHandlers();
		CommonRegisters.registerEventHandlers();
		MoneyMod.LOGGER.info("FMLInitializationEvent on Server side");
	}

	public void postInit(FMLPostInitializationEvent event) {
		MoneyMod.LOGGER.info("FMLPostInitializationEvent on Server side");
	}

	public void serverStarting(FMLServerStartingEvent event) {
	}
}
