import java.sql.*;


public class DBConnection {

    private final String url;
    private final String user;
    private final String pass;
    private Connection con;

    public DBConnection(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public void connect() {
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int checkIfBetreiberExists(String betreiber_id) {
        int ret = -1;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select `betreiber_nr_pk` from `opendata`.`betreiber` where `betreiber_id` =  '" + betreiber_id + "'");

            if (rs.next()) {
                ret = rs.getInt("betreiber_nr_pk");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public int checkIfHaltestelleExists(int bpuic) {
        int ret = -1;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select `haltestelle_id` from `opendata`.`haltestelle` where `bpuic` =  " + bpuic + "");

            if (rs.next()) {
                ret = rs.getInt("haltestelle_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public int checkIfBetriebstagExists(String betriebstag) {
        int ret = -1;
        try {
            String sql = "Select `betriebstag_id` from `opendata`.`betriebstag` where `betriebstag` = ?";
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, betriebstag);
            ResultSet rs = prepareStatement.executeQuery();
            if (rs.next()) {
                ret = rs.getInt("betriebstag_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public int createBetreiberData(String betreiber_id, String betreiber_abk, String betreiber_name) {
        int ret = 0;
        try {
            String sql = "INSERT INTO `opendata`.`betreiber` (`betreiber_id`, `betreiber_abk`, `betreiber_name`) VALUES (?, ?, ?)";
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, betreiber_id);
            prepareStatement.setString(2, betreiber_abk);
            prepareStatement.setString(3, betreiber_name);
            prepareStatement.executeUpdate();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select `betreiber_nr_pk` from `opendata`.`betreiber` where `betreiber_id` =  '" + betreiber_id + "'");
            if (rs.next()) {
                ret = rs.getInt("betreiber_nr_pk");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public int createHaltestelleData(int bpuic, String geopos, String haltestelle_name) {
        int ret = 0;
        try {
            String sql = "INSERT INTO `opendata`.`haltestelle` (`bpuic`, `geopos`, `haltestelle_name`) VALUES (?, ?, ?)";
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setInt(1, bpuic);
            prepareStatement.setString(2, geopos);
            prepareStatement.setString(3, haltestelle_name);
            prepareStatement.executeUpdate();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select `haltestelle_id` from `opendata`.`haltestelle` where `bpuic` =  " + bpuic + "");
            if (rs.next()) {
                ret = rs.getInt("haltestelle_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }


    public int createBetriebstagData(String betriebstag) {
        int ret = 0;
        try {
            String sql = "INSERT INTO `opendata`.`betriebstag` (`betriebstag`) VALUES (?)";
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, betriebstag);
            prepareStatement.executeUpdate();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select `betriebstag_id` from `opendata`.`betriebstag` where `betriebstag` =  '" + betriebstag + "'");
            if (rs.next()) {
                ret = rs.getInt("betriebstag_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void createFahrtData(String linien_text, String ankunftszeit, boolean ankunftsverspatung, String abfahrtszeit, boolean faellt_aus_tf,
                                int betriebstagID, int betreiber_nr_pk, int haltestelleID) {
        try {
            String sql = "INSERT INTO `opendata`.`fahrt` (`linien_text`, `ankunftszeit`," +
                    "`ankunftsverspatung`, `abfahrtszeit`, `faellt_aus_tf`," +
                    "`betreibstag_nr_fk`, `betreiber_nr_pk_fk`, `haltestelle_id_fk`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, linien_text);
            prepareStatement.setString(2, ankunftszeit);
            prepareStatement.setInt(3, ankunftsverspatung ? 1 : 0);
            prepareStatement.setString(4, abfahrtszeit);
            prepareStatement.setInt(5, faellt_aus_tf ? 1 : 0);
            prepareStatement.setInt(6, betriebstagID);
            prepareStatement.setInt(7, betreiber_nr_pk);
            prepareStatement.setInt(8, haltestelleID);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
