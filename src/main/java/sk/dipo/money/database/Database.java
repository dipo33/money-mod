package sk.dipo.money.database;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.file.YamlConfiguration;

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

	public YamlConfiguration yml(String name) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			return yml;
		} catch (Exception e) {
			return null;
		}
	}

	public void save(String name) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			yml.save(file);
		} catch (Exception e) {

		}
	}

	// Puts the file "name" in the database.
	public void put(String name) {
		try {
			// Creates the file.
			File file = null;
			// If there's a folder, (the database at the main config folder)
			if (folder != null) {
				// File.separator is like those "folder / inFolder" things.
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				// getDataFolder() refers to the plugin configuration folder.
				file = new File(this.rootFolder, name + ".yml");
			}
			// Now that we have file lets load it as a YamlConfiguration.
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			// And never forget to save!
			yml.save(file);
			System.out.println("YML SAVED");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Map<String path, Object obj> Put obj at path.
	public void put(String name, Map<String, Object> objects) {
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
	}

	// Store's a object in path.
	public void set(String name, String path, Object object) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			// This is the only Line diffrent from the basic "put" function.
			yml.set(path, object);

			yml.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getString(String name, String path) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			return yml.getString(path);
		} catch (Exception e) {

		}
		return null;
	}

	public int getInteger(String name, String path) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			return yml.getInt(path);
		} catch (Exception e) {

		}
		return 0;
	}

	public double getDouble(String name, String path) {
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
	}

	public boolean getBoolean(String name, String path) {
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
	}

	public List<Integer> getIntegerList(String name, String path) {
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
	}

	public List<Double> getDoubleList(String name, String path) {
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
	}

	public List<String> getStringList(String name, String path) {
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
	}

	public Map<?, ?> getEnchantmentMap(String name, String path) {
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
	}

	public boolean exists(String name, String path) {
		try {
			File file = null;
			if (folder != null) {
				file = new File(this.rootFolder + File.separator + folder, name + ".yml");
			} else {
				file = new File(this.rootFolder, name + ".yml");
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			return yml.get(path) != null;
		} catch (Exception e) {

		}
		return false;
	}
}