package sk.dipo.money.handler;

import java.util.Random;

import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import sk.dipo.money.utils.Config;
import sk.dipo.money.utils.Utils;

public class MoneyVillagerTradeHandler implements VillagerRegistry.IVillageTradeHandler {

	public static final int VILLAGER_EXCHANGER_ID = 10;

	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
		if (Config.allowVillager)
			if (villager.getProfession() == VILLAGER_EXCHANGER_ID) {
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Items.emerald), new ItemStack(Utils.getCoinByValue(Config.emeraldValue))));
			}
	}
}