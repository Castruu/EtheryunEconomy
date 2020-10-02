package not.etheryun.economy;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import not.etheryun.economy.api.EconomyService;
import not.etheryun.economy.commands.GetMoney;
import not.etheryun.economy.database.Connection;
import not.etheryun.economy.database.DataManager;
import not.etheryun.economy.events.FirstJoin;
import not.etheryun.economy.events.InteractEntity;
import not.etheryun.economy.hologram.KingdomHologram;
import not.etheryun.economy.utils.BankerMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class EcoMain extends JavaPlugin {
    public Permission perms;
    public static Economy econ = null;
    static DataManager data;
    private final Plugin vault = Bukkit.getPluginManager().getPlugin("Vault");
    @Override
    public void onEnable() {
        saveDefaultConfig();
        data = new DataManager(this);
        data.saveDefaultConfig();
        if (!setupPermissions()) this.getServer().getPluginManager().disablePlugin(this);
        Bukkit.getPluginManager().registerEvents(new FirstJoin(), this);
        Bukkit.getPluginManager().registerEvents(new InteractEntity(), this);
        getCommand("etheryuneco").setExecutor(new GetMoney());
        try {
            loadStand();
            loadNameStand();
        } catch (NullPointerException e) {
            System.out.println("Sem dados armazenados!");
        }
        Bukkit.getServicesManager().register(Economy.class, new EconomyService(), vault, ServicePriority.Normal);
        setupEconomy();
        BankerMenu.createMenu();
        Connection.openConnectionSQLite();
    }

    @Override
    public void onDisable(){
        getArmorStandLists().forEach(Entity::remove);
        getArmorStandNameList().forEach(Entity::remove);
        Connection.close();
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static FileConfiguration getData() {
        return data.getConfig();
    }
    public static void saveData(){
        data.saveConfig();
    }
    public static EcoMain getInstance() {return getPlugin(EcoMain.class);}
    public static HashMap<ArmorStand, String> getArmorStandMap() {
        return armorStandList;
    }
    public static List<ArmorStand> getArmorStandLists() {
        return armorStandLists;
    }
    private static final List<ArmorStand> armorStandLists = new ArrayList<>();
    private static final HashMap<ArmorStand, String> armorStandList = new HashMap<>();
    public static List<ArmorStand> getArmorStandNameList() { return armorStandNameList; }
    private static final List<ArmorStand> armorStandNameList = new ArrayList<>();

    public void loadStand(){
        FileConfiguration file = data.getConfig();
        if(!data.getConfig().getConfigurationSection("data").getKeys(true).isEmpty())
            data.getConfig().getConfigurationSection("data").getKeys(false).forEach(npc -> {
                Location location = new Location(Bukkit.getWorld(file.getString("data." + npc + ".world")),
                        file.getDouble("data." + npc + ".x"), file.getDouble("data." + npc + ".y"), file.getDouble("data." + npc + ".z"));
                String name = file.getString("data." + npc + ".name");
                String grupo = file.getString("data." + npc + ".grupo");
                KingdomHologram.loadHolo(location, name, grupo);
            });
    }
    public void loadNameStand(){
        FileConfiguration file = data.getConfig();
        if(!data.getConfig().getConfigurationSection("names").getKeys(true).isEmpty())
            data.getConfig().getConfigurationSection("names").getKeys(false).forEach(npc -> {
                Location location = new Location(Bukkit.getWorld(file.getString("names." + npc + ".world")),
                        file.getDouble("names." + npc + ".x"), file.getDouble("names." + npc + ".y"), file.getDouble("names." + npc + ".z"));
                String name = file.getString("names." + npc + ".name");
                String grupo = file.getString("names." + npc + ".grupo");
                KingdomHologram.loadNameHolo(location, name, grupo);
            });
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
