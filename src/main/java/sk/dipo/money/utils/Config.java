package sk.dipo.money.utils;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {

	public static boolean shouldMobsDropMoney;

	public static int zombieDropMin;
	public static int zombieDropMax;
	public static int skeletonDropMin;
	public static int skeletonDropMax;
	public static int spiderDropMin;
	public static int spiderDropMax;
	public static int creeperDropMin;
	public static int creeperDropMax;
	public static int silverfishDropMin;
	public static int silverfishDropMax;
	public static int slimeSDropMin;
	public static int slimeSDropMax;
	public static int slimeMDropMin;
	public static int slimeMDropMax;
	public static int slimeLDropMin;
	public static int slimeLDropMax;
	public static int caveSpiderDropMin;
	public static int caveSpiderDropMax;
	public static int ghastDropMin;
	public static int ghastDropMax;
	public static int blazeDropMin;
	public static int blazeDropMax;
	public static int pigZombieDropMin;
	public static int pigZombieDropMax;
	public static int endermanDropMin;
	public static int endermanDropMax;
	public static int witchDropMin;
	public static int witchDropMax;
	public static int magmaCubeSDropMin;
	public static int magmaCubeSDropMax;
	public static int magmaCubeMDropMin;
	public static int magmaCubeMDropMax;
	public static int magmaCubeLDropMin;
	public static int magmaCubeLDropMax;
	public static int zombieVillagerDropMin;
	public static int zombieVillagerDropMax;
	public static int witherSkeletonDropMin;
	public static int witherSkeletonDropMax;
	public static int witherDropMin;
	public static int witherDropMax;
	public static int enderDragonDropMin;
	public static int enderDragonDropMax;

	public static void loadConfiguration(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();


		shouldMobsDropMoney = config.getBoolean("shouldMobsDropMoney", "Mobs", true, "Allow mobs to drop money when killed");
		zombieDropMin = config.getInt("zombieDropMin", "Mobs.Zombie", 1, 0, 500000, "Minimum amount of money dropped by zombie (value is in cents)");
		zombieDropMax = config.getInt("zombieDropMax", "Mobs.Zombie", 2, 0, 500000, "Maximum amount of money dropped by zombie (value is in cents)");

		skeletonDropMin = config.getInt("skeletonDropMin", "Mobs.Skeleton", 1, 0, 500000, "Minimum amount of money dropped by skeleton (value is in cents)");
		skeletonDropMax = config.getInt("skeletonDropMax", "Mobs.Skeleton", 2, 0, 500000, "Maximum amount of money dropped by skeleton (value is in cents)");

		spiderDropMin = config.getInt("spiderDropMin", "Mobs.Spider", 1, 0, 500000, "Minimum amount of money dropped by spider (value is in cents)");
		spiderDropMax = config.getInt("spiderDropMax", "Mobs.Spider", 2, 0, 500000, "Maximum amount of money dropped by spider (value is in cents)");

		creeperDropMin = config.getInt("creeperDropMin", "Mobs.Creeper", 1, 0, 500000, "Minimum amount of money dropped by creeper (value is in cents)");
		creeperDropMax = config.getInt("creeperDropMax", "Mobs.Creeper", 2, 0, 500000, "Maximum amount of money dropped by creeper (value is in cents)");

		silverfishDropMin = config.getInt("silverfishDropMin", "Mobs.Silverfish", 1, 0, 500000,
				"Minimum amount of money dropped by silverfish (value is in cents)");
		silverfishDropMax = config.getInt("silverfishDropMax", "Mobs.Silverfish", 2, 0, 500000,
				"Maximum amount of money dropped by silverfish (value is in cents)");

		slimeSDropMin = config.getInt("slimeSDropMin", "Mobs.Slime", 1, 0, 500000, "Minimum amount of money dropped by small slime (value is in cents)");
		slimeSDropMax = config.getInt("slimeSDropMax", "Mobs.Slime", 2, 0, 500000, "Maximum amount of money dropped by small slime (value is in cents)");
		slimeMDropMin = config.getInt("slimeMDropMin", "Mobs.Slime", 2, 0, 500000, "Minimum amount of money dropped by middle slime (value is in cents)");
		slimeMDropMax = config.getInt("slimeMDropMax", "Mobs.Slime", 3, 0, 500000, "Maximum amount of money dropped by middle slime (value is in cents)");
		slimeLDropMin = config.getInt("slimeLDropMin", "Mobs.Slime", 3, 0, 500000, "Minimum amount of money dropped by large slime (value is in cents)");
		slimeLDropMax = config.getInt("slimeLDropMax", "Mobs.Slime", 5, 0, 500000, "Maximum amount of money dropped by large slime (value is in cents)");

		caveSpiderDropMin = config.getInt("caveSpiderDropMin", "Mobs.CaveSpider", 2, 0, 500000,
				"Minimum amount of money dropped by cave spider (value is in cents)");
		caveSpiderDropMax = config.getInt("caveSpiderDropMax", "Mobs.CaveSpider", 5, 0, 500000,
				"Maximum amount of money dropped by cave spider (value is in cents)");

		ghastDropMin = config.getInt("ghastDropMin", "Mobs.Ghast", 5, 0, 500000, "Minimum amount of money dropped by ghast (value is in cents)");
		ghastDropMax = config.getInt("ghastDropMax", "Mobs.Ghast", 10, 0, 500000, "Maximum amount of money dropped by ghast (value is in cents)");

		blazeDropMin = config.getInt("blazeDropMin", "Mobs.Blaze", 5, 0, 500000, "Minimum amount of money dropped by blaze (value is in cents)");
		blazeDropMax = config.getInt("blazeDropMax", "Mobs.Blaze", 10, 0, 500000, "Maximum amount of money dropped by blaze (value is in cents)");

		pigZombieDropMin = config.getInt("pigZombieDropMin", "Mobs.PigZombie", 5, 0, 500000,
				"Minimum amount of money dropped by pig zombie (value is in cents)");
		pigZombieDropMax = config.getInt("pigZombieDropMax", "Mobs.PigZombie", 10, 0, 500000,
				"Maximum amount of money dropped by pig zombie (value is in cents)");

		endermanDropMin = config.getInt("endermanDropMin", "Mobs.Enderman", 10, 0, 500000, "Minimum amount of money dropped by enderman (value is in cents)");
		endermanDropMax = config.getInt("endermanDropMax", "Mobs.Enderman", 20, 0, 500000, "Maximum amount of money dropped by enderman (value is in cents)");

		witchDropMin = config.getInt("witchDropMin", "Mobs.Witch", 10, 0, 500000, "Minimum amount of money dropped by witch (value is in cents)");
		witchDropMax = config.getInt("witchDropMax", "Mobs.Witch", 20, 0, 500000, "Maximum amount of money dropped by witch (value is in cents)");

		magmaCubeSDropMin = config.getInt("magmaCubeSDropMin", "Mobs.MagmaCube", 10, 0, 500000,
				"Minimum amount of money dropped by small magma cube (value is in cents)");
		magmaCubeSDropMax = config.getInt("magmaCubeSDropMax", "Mobs.MagmaCube", 12, 0, 500000,
				"Maximum amount of money dropped by small magma cube (value is in cents)");
		magmaCubeMDropMin = config.getInt("magmaCubeMDropMin", "Mobs.MagmaCube", 12, 0, 500000,
				"Minimum amount of money dropped by middle magma cube (value is in cents)");
		magmaCubeMDropMax = config.getInt("magmaCubeMDropMax", "Mobs.MagmaCube", 15, 0, 500000,
				"Maximum amount of money dropped by middle magma cube (value is in cents)");
		magmaCubeLDropMin = config.getInt("magmaCubeLDropMin", "Mobs.MagmaCube", 15, 0, 500000,
				"Minimum amount of money dropped by large magma cube (value is in cents)");
		magmaCubeLDropMax = config.getInt("magmaCubeLDropMax", "Mobs.MagmaCube", 20, 0, 500000,
				"Maximum amount of money dropped by large magma cube (value is in cents)");

		zombieVillagerDropMin = config.getInt("zombieVillagerDropMin", "Mobs.ZombieVillager", 10, 0, 500000,
				"Minimum amount of money dropped by zombie villager (value is in cents)");
		zombieVillagerDropMax = config.getInt("zombieVillagerDropMax", "Mobs.ZombieVillager", 20, 0, 500000,
				"Maximum amount of money dropped by zombie villager (value is in cents)");

		witherSkeletonDropMin = config.getInt("witherSkeletonDropMin", "Mobs.WitherSkeleton", 10, 0, 500000,
				"Minimum amount of money dropped by wither skeleton (value is in cents)");
		witherSkeletonDropMax = config.getInt("witherSkeletonDropMax", "Mobs.WitherSkeleton", 20, 0, 500000,
				"Maximum amount of money dropped by wither skeleton (value is in cents)");

		witherDropMin = config.getInt("witherDropMin", "Mobs.Wither", 20, 0, 500000, "Minimum amount of money dropped by wither (value is in cents)");
		witherDropMax = config.getInt("witherDropMax", "Mobs.Wither", 50, 0, 500000, "Maximum amount of money dropped by wither (value is in cents)");

		enderDragonDropMin = config.getInt("enderDragonDropMin", "Mobs.EnderDragon", 50, 0, 500000,
				"Minimum amount of money dropped by ender dragon (value is in cents)");
		enderDragonDropMax = config.getInt("enderDragonDropMax", "Mobs.EnderDragon", 100, 0, 500000,
				"Maximum amount of money dropped by ender dragon (value is in cents)");

		config.save();
	}

}
