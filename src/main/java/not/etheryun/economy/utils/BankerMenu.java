package not.etheryun.economy.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BankerMenu {
    public static final Inventory gui = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Banco de Etheryun");
    private static ItemStack gold;
    private static ItemStack paper;
    public static void createMenu() {
        gold = new ItemBuilder(Material.GOLD_INGOT).name(ChatColor.DARK_PURPLE + "Retire seus Dracmas").build();
        gui.setItem( 11, gold);
        paper = new ItemBuilder(Material.PAPER).name(ChatColor.GRAY + "Deposite seus Dracmas").build();
        gui.setItem(15, paper);
    }
}
