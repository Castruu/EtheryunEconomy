package not.etheryun.economy.events;

import not.etheryun.economy.EcoMain;
import not.etheryun.economy.api.Balances;
import not.etheryun.economy.utils.BankerMenu;
import not.etheryun.economy.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class InteractEntity implements Listener {
    Balances balances = new Balances();
    ItemStack gold;
    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {
        if(e.getRightClicked().getCustomName() != null)
        if (e.getRightClicked().getCustomName().equals(ChatColor.GREEN + "Banqueiro"))
        e.getPlayer().openInventory(BankerMenu.gui);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        gold = new ItemBuilder(Material.GOLD_INGOT).name(ChatColor.DARK_PURPLE + "Dracmas").build();
        if(e.getView().getTitle().equals(ChatColor.GREEN + "Banco de Etheryun")) {
            switch (e.getSlot()) {
                case 11:
                    Bukkit.getScheduler().runTaskAsynchronously(EcoMain.getInstance(), () -> {
                        gold.setAmount((int) Balances.getInstance().getBalance(player.getUniqueId()));
                        balances.addBalance(player, -gold.getAmount());
                        player.getInventory().addItem(gold);
                    });
                    break;
                case 15:
                    Bukkit.getScheduler().runTaskAsynchronously(EcoMain.getInstance(), () -> {
                        for(ItemStack itemStack : player.getInventory().getContents()) {
                            if(itemStack != null)
                                if(itemStack.isSimilar(gold)){
                                    player.getInventory().remove(itemStack);
                                    balances.addBalance(player, itemStack.getAmount());
                                }
                        }
                    });

                    break;
                default: break;
            }
            e.setCancelled(true);
        }
    }
}
