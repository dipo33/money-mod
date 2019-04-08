package sk.dipo.money.register;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.handlers.EventHandlerDipo;
import sk.dipo.money.handlers.GuiHandler;
import sk.dipo.money.handlers.MoneyVillagerTradeHandler;

public class CommonRegisters {

	public static void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(new EventHandlerDipo());
	}

	public static void registerHandlers() {
		NetworkRegistry.INSTANCE.registerGuiHandler(MoneyMod.instance, new GuiHandler());
	}

	public static void registerVillagers() {
		VillagerRegistry.instance().registerVillageTradeHandler(MoneyVillagerTradeHandler.VILLAGER_EXCHANGER_ID,
				new MoneyVillagerTradeHandler());
		VillagerRegistry.instance().registerVillagerId(MoneyVillagerTradeHandler.VILLAGER_EXCHANGER_ID);
		VillagerRegistry.instance().registerVillagerSkin(MoneyVillagerTradeHandler.VILLAGER_EXCHANGER_ID,
				new ResourceLocation("chow", "textures/entity.png"));
		VillagerRegistry.getRegisteredVillagers();
	}
}
