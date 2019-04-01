package sk.psuchtak.money;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class CraftingRecipes {
	public static void registerRecipes() {

		// Un-Crafting
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent1, 2), MoneyItems.cent2);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent1, 5), MoneyItems.cent5);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent5, 2), MoneyItems.cent10);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent10, 2), MoneyItems.cent20);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent10, 5), MoneyItems.cent50);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent50, 2), MoneyItems.euro1);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro1, 2), MoneyItems.euro2);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro1, 5), MoneyItems.euro5);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro5, 2), MoneyItems.euro10);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro10, 2), MoneyItems.euro20);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro10, 5), MoneyItems.euro50);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro50, 2), MoneyItems.euro100);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro100, 2), MoneyItems.euro200);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro100, 5), MoneyItems.euro500);

		// Crafting
		
		//Cent 2
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent2, 1), MoneyItems.cent1, MoneyItems.cent1);
		
		// Cent 5
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent5, 1), MoneyItems.cent1, MoneyItems.cent1, MoneyItems.cent1, MoneyItems.cent1, MoneyItems.cent1);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent5, 1), MoneyItems.cent1, MoneyItems.cent2, MoneyItems.cent2);
		
		// Cent 10
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent10, 1), MoneyItems.cent5, MoneyItems.cent5);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent10, 1), MoneyItems.cent5, MoneyItems.cent2, MoneyItems.cent2, MoneyItems.cent1);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent10, 1), MoneyItems.cent2, MoneyItems.cent2, MoneyItems.cent2, MoneyItems.cent2, MoneyItems.cent2);
		
		// Cent 20
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent20, 1), MoneyItems.cent10, MoneyItems.cent10);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent20, 1), MoneyItems.cent10, MoneyItems.cent5, MoneyItems.cent5);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent20, 1), MoneyItems.cent5, MoneyItems.cent5, MoneyItems.cent5, MoneyItems.cent5);
		
		// Cent 50
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent50, 1), MoneyItems.cent10, MoneyItems.cent10, MoneyItems.cent10, MoneyItems.cent10, MoneyItems.cent10);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.cent50, 1), MoneyItems.cent20, MoneyItems.cent20, MoneyItems.cent10);
		
		// Euro 1
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro1, 1), MoneyItems.cent50, MoneyItems.cent50);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro1, 1), MoneyItems.cent50, MoneyItems.cent20, MoneyItems.cent20, MoneyItems.cent10);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro1, 1), MoneyItems.cent20, MoneyItems.cent20, MoneyItems.cent20, MoneyItems.cent20, MoneyItems.cent20);
		
		// Euro 2
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro2, 1), MoneyItems.euro1, MoneyItems.euro1);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro2, 1), MoneyItems.euro1, MoneyItems.cent50, MoneyItems.cent50);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro2, 1), MoneyItems.cent50, MoneyItems.cent50, MoneyItems.cent50, MoneyItems.cent50);
		
		// Euro 5
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro5, 1), MoneyItems.euro1, MoneyItems.euro1, MoneyItems.euro1, MoneyItems.euro1, MoneyItems.euro1);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro5, 1), MoneyItems.euro2, MoneyItems.euro2, MoneyItems.euro1);
		
		// Euro 10
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro10, 1), MoneyItems.euro5, MoneyItems.euro5);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro10, 1), MoneyItems.euro5, MoneyItems.euro2, MoneyItems.euro2, MoneyItems.euro1);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro10, 1), MoneyItems.euro2, MoneyItems.euro2, MoneyItems.euro2, MoneyItems.euro2, MoneyItems.euro2);
		
		// Euro 20
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro20, 1), MoneyItems.euro10, MoneyItems.euro10);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro20, 1), MoneyItems.euro10, MoneyItems.euro5, MoneyItems.euro5);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro20, 1), MoneyItems.euro5, MoneyItems.euro5, MoneyItems.euro5, MoneyItems.euro5);

		// Euro 50
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro50, 1), MoneyItems.euro10, MoneyItems.euro10, MoneyItems.euro10, MoneyItems.euro10, MoneyItems.euro10);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro50, 1), MoneyItems.euro20, MoneyItems.euro20, MoneyItems.euro10);
		
		// Euro 100
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro100, 1), MoneyItems.euro50, MoneyItems.euro50);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro100, 1), MoneyItems.euro50, MoneyItems.euro20, MoneyItems.euro20, MoneyItems.euro10);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro100, 1), MoneyItems.euro20, MoneyItems.euro20, MoneyItems.euro20, MoneyItems.euro20, MoneyItems.euro20);
		
		// Euro 200
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro200, 1), MoneyItems.euro100, MoneyItems.euro100);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro200, 1), MoneyItems.euro100, MoneyItems.euro50, MoneyItems.euro50);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro200, 1), MoneyItems.euro50, MoneyItems.euro50, MoneyItems.euro50, MoneyItems.euro50);
		
		// Euro 500
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro500, 1), MoneyItems.euro100, MoneyItems.euro100, MoneyItems.euro100, MoneyItems.euro100, MoneyItems.euro100);
		GameRegistry.addShapelessRecipe(new ItemStack(MoneyItems.euro500, 1), MoneyItems.euro200, MoneyItems.euro200, MoneyItems.euro100);
		
		//	GameRegistry.addShapelessRecipe(new ItemStack(Vending.blockVendingMachine, 1), MoneyItems.euro100, MoneyItems.euro100, MoneyItems.euro100);
		
	}
}
