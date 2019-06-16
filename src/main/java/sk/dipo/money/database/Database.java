package sk.dipo.money.database;

import java.io.File;
import java.util.Map;

import io.bluecube.thunderbolt.Thunderbolt;
import io.bluecube.thunderbolt.io.ThunderFile;
import net.minecraftforge.common.DimensionManager;

public class Database {
	private File rootFolder = DimensionManager.getCurrentSaveRootDirectory().getAbsoluteFile();
	private String folder;

	public Database() {
		this.folder = null;
	}

	public Database(String folder) {
		this.folder = folder;
	}

	public ThunderFile yml(String name) {
		try {
			ThunderFile tfile = Thunderbolt.load(name, this.rootFolder + File.separator + folder);
			Thunderbolt.unload(name);
			return tfile;
		} catch (Exception e) {
			Thunderbolt.unload(name);
			return null;
		}
	}

	public void save(String name) {
		try {
			ThunderFile tfile = Thunderbolt.load(name, this.rootFolder + File.separator + folder);
			tfile.save();
			Thunderbolt.unload(name);
		} catch (Exception e) {
			Thunderbolt.unload(name);
		}
	}

	// Puts the file "name" in the database.
	public void put(String name) {
		try {
			ThunderFile tfile = Thunderbolt.load(name, this.rootFolder + File.separator + folder);
			tfile.save();
			Thunderbolt.unload(name);
			System.out.println("YML SAVED");
		} catch (Exception e) {
			Thunderbolt.unload(name);
			e.printStackTrace();
		}

	}

	// Map<String path, Object obj> Put obj at path.
	/*public void put(String name, Map<String, Object> objects) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			for (Entry<String, Object> e : objects.entrySet()) {
				yml.set(e.getKey(), e.getValue());
			}
			yml.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	// Store's a object in path.
	public void set(String name, String path, Object object) {
		try {
			ThunderFile tfile = Thunderbolt.load(name, this.rootFolder + File.separator + folder);
			tfile.set(path, object);
			tfile.save();
			Thunderbolt.unload(name);
		} catch (Exception e) {
			Thunderbolt.unload(name);
			e.printStackTrace();
		}
	}

	public String getString(String name, String path) {
		try {
			ThunderFile tfile = Thunderbolt.load(name, this.rootFolder + File.separator + folder);
			String item = tfile.getString(path);
			Thunderbolt.unload(name);
			return item;
		} catch (Exception e) {
			Thunderbolt.unload(name);
		}
		return null;
	}

	public int getInteger(String name, String path) {
		try {
			ThunderFile tfile = Thunderbolt.load(name, this.rootFolder + File.separator + folder);
			int item = tfile.getInt(path);
			Thunderbolt.unload(name);
			return item;
		} catch (Exception e) {
			Thunderbolt.unload(name);
		}
		return 0;
	}

	/*public double getDouble(String name, String path) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			return yml.getDouble(path);
		} catch (Exception e) {

		}
		return 0;
	}*/

	/*public boolean getBoolean(String name, String path) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			return yml.getBoolean(path);
		} catch (Exception e) {

		}
		return false;
	}*/

	/*public List<Integer> getIntegerList(String name, String path) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			return yml.getIntegerList(path);
		} catch (Exception e) {

		}
		return null;
	}*/

	/*public List<Double> getDoubleList(String name, String path) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			return yml.getDoubleList(path);
		} catch (Exception e) {

		}
		return null;
	}*/

	/*public List<String> getStringList(String name, String path) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			return yml.getStringList(path);
		} catch (Exception e) {

		}
		return null;
	}*/

	/*public Map<?, ?> getEnchantmentMap(String name, String path) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			return yml.getMapList(path).get(0);
		} catch (Exception e) {

		}
		return null;
	}*/

	public boolean exists(String name, String path) {
		try {
			ThunderFile tfile = Thunderbolt.load(name, this.rootFolder + File.separator + folder);
			boolean item = tfile.get(path) != null;
			Thunderbolt.unload(name);
			return item;
		} catch (Exception e) {
			Thunderbolt.unload(name);
		}
		return false;
	}
}