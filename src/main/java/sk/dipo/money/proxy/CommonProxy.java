package sk.dipo.money.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.util.ResourceLocation;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.entity.MyVillagerTradeHandler;
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
		VillagerRegistry.instance().registerVillageTradeHandler(MyVillagerTradeHandler.VILLAGER_EXCHANGER_ID, new MyVillagerTradeHandler());
		VillagerRegistry.instance().registerVillagerId(MyVillagerTradeHandler.VILLAGER_EXCHANGER_ID);
		VillagerRegistry.instance().registerVillagerSkin(MyVillagerTradeHandler.VILLAGER_EXCHANGER_ID, new ResourceLocation("chow", "textures/entity.png"));
		VillagerRegistry.getRegisteredVillagers();
		MoneyMod.LOGGER.info("FMLInitializationEvent on Server side");
	}

	public void postInit(FMLPostInitializationEvent event) {
		MoneyMod.LOGGER.info("FMLPostInitializationEvent on Server side");
	}

	public void serverStarting(FMLServerStartingEvent event) {
	}
}
