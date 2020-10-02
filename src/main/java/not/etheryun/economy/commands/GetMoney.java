package not.etheryun.economy.commands;

import not.etheryun.economy.api.Balances;
import not.etheryun.economy.utils.BankerCreator;
import not.etheryun.economy.hologram.KingdomHologram;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetMoney implements CommandExecutor {
    Balances balance = new Balances();
    KingdomHologram holo = new KingdomHologram();
    BankerCreator banker = new BankerCreator();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
                player.sendMessage(ChatColor.DARK_PURPLE + "Você tem " + ChatColor.RED + Balances.getInstance().getBalance(player.getUniqueId())
                        + ChatColor.DARK_PURPLE + " Dracmas!");
            } else commandSender.sendMessage(ChatColor.RED + "Especifique um jogador!");
        }

            else {
                if(strings[0].equals("set")) {
                    Player target = Bukkit.getServer().getPlayer(strings[1]);
                    double amount;
                    try {
                        amount = Double.parseDouble(strings[2]);
                    }catch (Throwable e){
                        commandSender.sendMessage("Digite um númeral válido");
                        return false;
                    }
                    if(amount >= 0) {
                        if (target.hasPlayedBefore()) {
                            balance.setBalance(target.getUniqueId(), amount);
                            target.sendMessage(ChatColor.DARK_PURPLE +"Sua economia agora é de "  + ChatColor.RED + amount + ChatColor.DARK_PURPLE + " Dracmas!");
                        }
                    } else commandSender.sendMessage(ChatColor.RED + "Insira uma quantia positiva!");
                }
                if(strings[0].equals("kingdom")) {
                    if(commandSender instanceof Player) {
                        Player player = (Player) commandSender;
                        holo.spawnHologram(player, strings[1]);
                        holo.spawnKingdomHologram(player, strings[1]);
                    }
                }
                if(strings[0].equals("banker")) {
                    if(commandSender instanceof Player) {
                        Player player = (Player) commandSender;
                        banker.spawnBanker(player);
                    }
                }

                }

            return false;
        }

    }

