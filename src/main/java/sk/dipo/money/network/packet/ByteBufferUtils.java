package sk.dipo.money.network.packet;

import io.netty.buffer.ByteBuf;
import sk.dipo.money.utils.Location;

public class ByteBufferUtils {

	public static void writeBooleanToBuffer(ByteBuf buffer, boolean value) {
		buffer.writeInt(value == false ? 0 : 1);
	}

	public static boolean readBooleanFromBuffer(ByteBuf buffer) {
		return buffer.readInt() == 0 ? false : true;
	}

	public static void writeLocationToBuffer(ByteBuf buffer, Location loc) {
		buffer.writeInt(loc.getX());
		buffer.writeInt(loc.getY());
		buffer.writeInt(loc.getZ());
	}

	public static Location readLocationFromBuffer(ByteBuf buffer) {
		return new Location(null, buffer.readInt(), buffer.readInt(), buffer.readInt());
	}
}