package not.etheryun.economy.api;

import not.etheryun.economy.EcoMain;
import not.etheryun.economy.database.Connection;
import not.etheryun.economy.hologram.KingdomHologram;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Balances {


    private static Balances instance = new Balances();
    private final KingdomHologram holo = new KingdomHologram();
    public double getBalance(UUID player) {
        double level = 0;
        try {
            PreparedStatement stm = Connection.con.prepareStatement("SELECT money FROM player WHERE uuid = ?");
            stm.setString(1, player.toString());
            ResultSet set = stm.executeQuery();
            if(!set.next()) return 0;
            level = set.getDouble("money");
            set.close();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return level;
    }

    public boolean playerHasBalance(String name) {
        Player player = Bukkit.getPlayer(name);
        try {
            PreparedStatement stm = Connection.con.prepareStatement("SELECT money FROM player WHERE uuid = ?");
            stm.setString(1, player.getUniqueId().toString());
            ResultSet set = stm.executeQuery();
            if(set.next()) return true;
            set.close();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setBalance(UUID uuid, double newbalance) {
        Bukkit.getScheduler().runTaskAsynchronously(EcoMain.getInstance(), () -> {
        try {
            PreparedStatement stm = Connection.con.prepareStatement("UPDATE player SET money = " + newbalance + " WHERE uuid = ?");
            stm.setString(1, uuid.toString());
            stm.executeUpdate();
            stm.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        holo.setHologramName();
        });
    }
    public void addBalance(Player player, double newbalance){
        Bukkit.getScheduler().runTaskAsynchronously(EcoMain.getInstance(), () -> {
            try {
                double soma = getBalance(player.getUniqueId()) + newbalance;
                PreparedStatement stm = Connection.con.prepareStatement("UPDATE player SET money = " + soma + " WHERE uuid = ?");
                stm.setString(1, player.getUniqueId().toString());
                stm.executeUpdate();
                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            holo.setHologramName();
        });
    }

    public static Balances getInstance() {
        return instance;
    }
}
