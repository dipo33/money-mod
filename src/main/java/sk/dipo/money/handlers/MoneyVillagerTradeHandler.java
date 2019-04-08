package sk.dipo.money.handlers;

import java.util.Random;

import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import sk.dipo.money.items.MoneyItems;

public class MoneyVillagerTradeHandler implements VillagerRegistry.IVillageTradeHandler {

	public static final int VILLAGER_EXCHANGER_ID = 10;

	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
		if (villager.getProfession() == VILLAGER_EXCHANGER_ID) {
			recipeList.addToListWithCheck(
					new MerchantRecipe(new ItemStack(Items.emerald), new ItemStack(MoneyItems.euro100)));
		}
	}
}
