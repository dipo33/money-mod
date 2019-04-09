package sk.dipo.money.register;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.handler.EventHandler;
import sk.dipo.money.handler.GuiHandler;
import sk.dipo.money.handler.MoneyVillagerTradeHandler;
import sk.dipo.money.tileentity.TileEntityATM;
import sk.dipo.money.utils.Reference;

public class CommonRegisters {

	public static void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}

	public static void registerHandlers() {
		NetworkRegistry.INSTANCE.registerGuiHandler(MoneyMod.instance, new GuiHandler());
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityATM.class, Reference.MODID + ":te_atm");
	}

	public static void registerVillagers() {
		VillagerRegistry.instance().registerVillageTradeHandler(MoneyVillagerTradeHandler.VILLAGER_EXCHANGER_ID,
				new MoneyVillagerTradeHandler());
		VillagerRegistry.instance().registerVillagerId(MoneyVillagerTradeHandler.VILLAGER_EXCHANGER_ID);
		VillagerRegistry.instance().registerVillagerSkin(MoneyVillagerTradeHandler.VILLAGER_EXCHANGER_ID,
				new ResourceLocation(Reference.MODID, "textures/entity/exchanger.png"));
	}
}