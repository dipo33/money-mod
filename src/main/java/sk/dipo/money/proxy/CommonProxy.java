package sk.dipo.money.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.network.PacketDispatcher;
import sk.dipo.money.recipe.CraftingRecipes;
import sk.dipo.money.register.BlockRegister;
import sk.dipo.money.register.CommonRegisters;
import sk.dipo.money.register.ItemRegister;
import sk.dipo.money.register.OreDicts;
import sk.dipo.money.utils.Config;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ItemRegister.registerItems();
		BlockRegister.registerBlocks();
		PacketDispatcher.registerPackets();
		Config.loadConfiguration(event);
		MoneyMod.LOGGER.info("FMLPreInitializationEvent on Server side");
	}

	public void init(FMLInitializationEvent event) {
		OreDicts.registerOreDicts();
		CraftingRecipes.registerRecipes();
		if (Config.allowVillager)
			CommonRegisters.registerVillagers();
		CommonRegisters.registerHandlers();
		CommonRegisters.registerEventHandlers();
		CommonRegisters.registerTileEntities();
		MoneyMod.LOGGER.info("FMLInitializationEvent on Server side");
	}

	public void postInit(FMLPostInitializationEvent event) {
		MoneyMod.LOGGER.info("FMLPostInitializationEvent on Server side");
	}

	public void serverStarting(FMLServerStartingEvent event) {
	}

	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}
}