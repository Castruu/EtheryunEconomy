package not.etheryun.economy.api;

import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;

import java.util.List;

public class EconomyService extends AbstractEconomy {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "Dracmas";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }

    @Override
    public String format(double amount) {
        return "Dracmas -> " + amount;
    }

    @Override
    public String currencyNamePlural() {
        return "Dracmas";
    }

    @Override
    public String currencyNameSingular() {
        return "Dracma";
    }

    @Override
    public boolean hasAccount(String playerName) {
        return Balances.getInstance().playerHasBalance(playerName);
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return Balances.getInstance().playerHasBalance(playerName);
    }

    @Override
    public double getBalance(String playerName) {
        try {
            return Balances.getInstance().getBalance(Bukkit.getPlayer(playerName).getUniqueId());
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    public double getBalance(String playerName, String world) {
        try {
            return Balances.getInstance().getBalance(Bukkit.getPlayer(playerName).getUniqueId());
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    public boolean has(String playerName, double amount) {
        return Balances.getInstance().getBalance(Bukkit.getPlayer(playerName).getUniqueId()) > amount;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return Balances.getInstance().getBalance(Bukkit.getPlayer(playerName).getUniqueId()) > amount;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        Balances.getInstance().addBalance(Bukkit.getPlayer(playerName), -amount);
        return new EconomyResponse(amount, Balances.getInstance().getBalance(Bukkit.getPlayer(playerName).getUniqueId()), EconomyResponse.ResponseType.SUCCESS, "No error");
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        Balances.getInstance().addBalance(Bukkit.getPlayer(playerName), -amount);
        return new EconomyResponse(amount, Balances.getInstance().getBalance(Bukkit.getPlayer(playerName).getUniqueId()), EconomyResponse.ResponseType.SUCCESS, "No error");
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        Balances.getInstance().addBalance(Bukkit.getPlayer(playerName), amount);
        return new EconomyResponse(amount, Balances.getInstance().getBalance(Bukkit.getPlayer(playerName).getUniqueId()), EconomyResponse.ResponseType.SUCCESS, "No error");
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        Balances.getInstance().addBalance(Bukkit.getPlayer(playerName), amount);
        return new EconomyResponse(amount, Balances.getInstance().getBalance(Bukkit.getPlayer(playerName).getUniqueId()), EconomyResponse.ResponseType.SUCCESS, "No error");
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return false;
    }
}
