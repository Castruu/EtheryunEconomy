package not.etheryun.economy.utils;


import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class BankerCreator {

    public void spawnBanker(Player player) {
        Villager villager = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
        villager.setInvulnerable(true);
        villager.setProfession(Villager.Profession.NITWIT);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.GREEN + "Banqueiro");
    }



}
