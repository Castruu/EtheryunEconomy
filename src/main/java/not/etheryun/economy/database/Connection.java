package not.etheryun.economy.database;

import not.etheryun.economy.EcoMain;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connection {

        public static java.sql.Connection con = null;
        private static final File file = new File(EcoMain.getInstance().getDataFolder(), "economy.db");
        private static final String URL = "jdbc:sqlite:" + file;
        public static void openConnectionSQLite(){
            try {
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection(URL);
                createTable();
                System.out.print("A conexao SQLite foi iniciada!");
            } catch (Exception e) {
                System.out.println("Na tentativa de conectar com SQLite, um erro aconteceu!");
                EcoMain.getInstance().getPluginLoader().disablePlugin(EcoMain.getInstance());
                e.printStackTrace();
            }
        }

        public static void createTable(){
            try {
                PreparedStatement stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS player (uuid CHAR(36), money REAL)");
                System.out.println("Tabela player carregadas com sucesso!");
                stm.execute();
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Falha ao criar a tabela de players!");
                EcoMain.getInstance().getPluginLoader().disablePlugin(EcoMain.getInstance());
            }
        }

        public static void close(){
            if(con != null) {
                try {
                    con.close();
                    System.out.println("Connection closed with DATABASE");
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erro ao fechar!");
                }
            }
        }
    }

