package sk.dipo.money;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import sk.dipo.money.items.MoneyItems;
import sk.dipo.money.proxy.IProxy;
import sk.dipo.money.recipes.CraftingRecipes;
import sk.dipo.money.utils.Reference;

@Mod(modid = Reference.MODID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class MoneyMod {

	@Instance(Reference.MODID)
	public static MoneyMod instance;

	public MoneyMod() {
		instance = this;
	}

	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
	public static IProxy proxy;

	public static CreativeTabs moneyTab = new MoneyTab();

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
		registerItem(MoneyItems.wallet);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		CraftingRecipes.registerRecipes();
	}

	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
}
