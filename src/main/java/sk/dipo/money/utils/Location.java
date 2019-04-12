package sk.dipo.money.utils;

import net.minecraft.world.World;

public class Location {
	private int x, y, z;
	private World world;

	public Location(World world, int x, int y, int z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public World getWorld() {
		return world;
	}
}