package not.etheryun.economy.events;

import not.etheryun.economy.database.Connection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FirstJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        try {
            PreparedStatement stm = Connection.con.prepareStatement("INSERT INTO player (uuid, money) VALUES ('" + e.getPlayer().getUniqueId().toString() + "', 100)");
            stm.execute();
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
