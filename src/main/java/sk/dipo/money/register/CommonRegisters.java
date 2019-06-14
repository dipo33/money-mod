package sk.dipo.money.register;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import sk.dipo.money.MoneyMod;
import sk.dipo.money.block.MoneyBlocks;
import sk.dipo.money.handler.GuiHandler;
import sk.dipo.money.handler.MoneyVillagerTradeHandler;
import sk.dipo.money.handler.MyEventHandler;
import sk.dipo.money.rendering.tileentity.ItemRenderATM;
import sk.dipo.money.rendering.tileentity.RenderATM;
import sk.dipo.money.tileentity.TileEntityATM;
import sk.dipo.money.utils.Reference;

public class CommonRegisters {

	public static void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(new MyEventHandler());
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

	public static void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityATM.class, new RenderATM());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MoneyBlocks.atm), new ItemRenderATM());
	}
}