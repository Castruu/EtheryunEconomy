package not.etheryun.economy.api;

import not.etheryun.economy.EcoMain;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class GetKingdomEco {

    public double getKingdomEconomy(String grupo) {
        double soma = 0;
        for(OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            if(player.hasPlayedBefore()) {
                if(EcoMain.getInstance().perms.playerInGroup("world", player, grupo)) {
                        soma += Balances.getInstance().getBalance(player.getUniqueId());
            }
        }
        }
        return soma;
    }
}
