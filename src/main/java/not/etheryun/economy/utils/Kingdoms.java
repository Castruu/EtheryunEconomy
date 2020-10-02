package not.etheryun.economy.utils;

import org.bukkit.ChatColor;

public enum Kingdoms {

    GRIMMONDOR("humano", ChatColor.GRAY + "Grimmondor"),
    LICH("lich", ChatColor.DARK_PURPLE + "Liches"),
    ELFO("elfo", ChatColor.GREEN + "Ayranmel"),
    DARKELF("darkelf", ChatColor.DARK_RED + "Elfos Negros"),
    DWARF("dwarf", ChatColor.DARK_GRAY + "Anoes"),
    MAGOS("mage", ChatColor.BLUE + "Mages"),
    ORCS("orc", ChatColor.DARK_GREEN + "Orcs");

    String grupo, nome;
    Kingdoms(String grupo, String name) {
        this.grupo = grupo;
        this.nome = name;

    }

    public String getNome() {return nome;}
    public String getGrupo() {return grupo;}
}
