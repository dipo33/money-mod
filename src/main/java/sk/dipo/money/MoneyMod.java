package sk.dipo.money;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

@Mod(modid = Reference.MODID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class MoneyMod {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		registerItem(MoneyItems.cent1);
		registerItem(MoneyItems.cent2);
		registerItem(MoneyItems.cent5);
		registerItem(MoneyItems.cent10);
		registerItem(MoneyItems.cent20);
		registerItem(MoneyItems.cent50);
		registerItem(MoneyItems.euro1);
		registerItem(MoneyItems.euro2);
		registerItem(MoneyItems.euro5);
		registerItem(MoneyItems.euro10);
		registerItem(MoneyItems.euro20);
		registerItem(MoneyItems.euro50);
		registerItem(MoneyItems.euro100);
		registerItem(MoneyItems.euro200);
		registerItem(MoneyItems.euro500);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		CraftingRecipes.registerRecipes();
	}

	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
}
