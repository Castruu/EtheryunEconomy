package not.etheryun.economy.hologram;

import not.etheryun.economy.EcoMain;
import not.etheryun.economy.api.GetKingdomEco;
import not.etheryun.economy.utils.Kingdoms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class KingdomHologram {
    GetKingdomEco eco = new GetKingdomEco();
    public void spawnHologram(Player player, String grupo) {
        String[] names = new String[] {ChatColor.BLUE + "" + eco.getKingdomEconomy(grupo), ChatColor.AQUA + "A economia do Reino é: "};
        double height = 0;
        for (int i = 0; i < names.length; i++) {
            ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, height, 0), EntityType.ARMOR_STAND);
            stand.setVisible(false);
            stand.setGravity(false);
            stand.setInvulnerable(true);
            stand.setCustomNameVisible(true);
            stand.setCustomName(names[i]);
            int var = 1;
            if(EcoMain.getData().contains("data"))
                var = EcoMain.getData().getConfigurationSection("data").getKeys(false).size() + 1;
            EcoMain.getData().set("data." + var + ".x", player.getLocation().add(0, height, 0).getX());
            EcoMain.getData().set("data." + var + ".y", player.getLocation().add(0, height, 0).getY());
            EcoMain.getData().set("data." + var + ".z", player.getLocation().add(0, height, 0).getZ());
            EcoMain.getData().set("data." + var + ".world", player.getLocation().getWorld().getName());
            EcoMain.getData().set("data." + var + ".name", stand.getCustomName());
            EcoMain.getData().set("data." + var + ".grupo", grupo);
            EcoMain.saveData();
            EcoMain.getArmorStandLists().add(stand);
            EcoMain.getArmorStandMap().put(stand, grupo);
            height += 0.3;
        }

    }
    public void spawnKingdomHologram(Player player, String grupo) {
            ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, 0.6, 0), EntityType.ARMOR_STAND);
            stand.setVisible(false);
            stand.setGravity(false);
            stand.setInvulnerable(true);
            stand.setCustomNameVisible(true);
            for(Kingdoms kingdoms : Kingdoms.values()) {
                if(kingdoms.getGrupo().equals(grupo))
                stand.setCustomName(kingdoms.getNome());
            }

            int var = 1;
            if(EcoMain.getData().contains("names"))
                var = EcoMain.getData().getConfigurationSection("names").getKeys(false).size() + 1;
            EcoMain.getData().set("names." + var + ".x", player.getLocation().add(0, 0.6, 0).getX());
            EcoMain.getData().set("names." + var + ".y", player.getLocation().add(0, 0.6, 0).getY());
            EcoMain.getData().set("names." + var + ".z", player.getLocation().add(0, 0.6, 0).getZ());
            EcoMain.getData().set("names." + var + ".world", player.getLocation().getWorld().getName());
            EcoMain.getData().set("names." + var + ".name", stand.getCustomName());
            EcoMain.getData().set("names." + var + ".grupo", grupo);
            EcoMain.saveData();
            EcoMain.getArmorStandNameList().add(stand);
    }

    public void setHologramName() {
        Bukkit.getScheduler().runTaskAsynchronously(EcoMain.getInstance(), () -> {
        EcoMain.getArmorStandLists().forEach(armorStand -> {
            if(EcoMain.getArmorStandMap().containsKey(armorStand)) {
                if((EcoMain.getArmorStandLists().indexOf(armorStand) + 1) % 2 == 0) return;
                    String name = "";
                    for(Kingdoms kingdoms  : Kingdoms.values()) {
                        if(EcoMain.getArmorStandMap().get(armorStand).equals(kingdoms.getGrupo())) {
                            name = ChatColor.BLUE + "" + eco.getKingdomEconomy(kingdoms.getGrupo());
                            armorStand.setCustomName(name);
                            EcoMain.getData().set("data." + (EcoMain.getArmorStandLists().indexOf(armorStand) + 1) + ".name", name);
                        }
                    }
                    EcoMain.saveData();
                }
            });
        });
    }
    public static void loadHolo(Location location, String name, String grupo) {
            ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            stand.setVisible(false);
            stand.setGravity(false);
            stand.setInvulnerable(true);
            stand.setCustomNameVisible(true);
            stand.setCustomName(name);
            EcoMain.getArmorStandLists().add(stand);
            EcoMain.getArmorStandMap().put(stand, grupo);
        }
        public static void loadNameHolo(Location location, String name, String grupo) {
            ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            stand.setVisible(false);
            stand.setGravity(false);
            stand.setInvulnerable(true);
            stand.setCustomNameVisible(true);
            stand.setCustomName(name);
            EcoMain.getArmorStandNameList().add(stand);
        }


}