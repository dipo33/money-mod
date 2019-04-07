package sk.dipo.money.entity;

import java.util.Random;

import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import sk.dipo.money.items.MoneyItems;

public class MyVillagerTradeHandler implements VillagerRegistry.IVillageTradeHandler {

	public static final int VILLAGER_EXCHANGER_ID = 10;

	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
	}
}
