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
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select `betriebstag_id` from `opendata`.`betriebstag` where `betriebstag` =  " + betriebstag + "");

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

    public void createFahrtData(String produkt_id, String linien_id, String verkehrsmittel_text, String linien_text, String ankunftszeit, String an_prognose_status,
                                String an_prognose, boolean ankunftsverspatung, String abfahrtszeit, String ab_prognose, String ab_prognose_status, boolean faellt_aus_tf,
                                boolean zusatzfahrt_tf, boolean durchfahrt_tf, String fahrt_bezeichner, String lod, int betriebstagID, int betreiber_nr_pk, int haltestelleID) {
        try {
            String sql = "INSERT INTO `opendata`.`fahrt` (`produkt_id`, `linien_id`, `verkehrsmittel_text`, `linien_text`, `ankunftszeit`," +
                    "`an_prognose_status`, `an_prognose`, `ankunftsverspatung`, `abfahrtszeit`, `ab_prognose`, `ab_prognose_status`, `faellt_aus_tf`," +
                    "`zusatzfahrt_tf`, `durchfahrt_tf`, `fahrt_bezeichner`, `lod`, `betreibstag_nr_fk`, `betreiber_nr_pk_fk`, `haltestelle_id_fk`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, produkt_id);
            prepareStatement.setString(2, linien_id);
            prepareStatement.setString(3, verkehrsmittel_text);
            prepareStatement.setString(4, linien_text);
            prepareStatement.setString(5, ankunftszeit);
            prepareStatement.setString(6, an_prognose_status);
            prepareStatement.setString(7, an_prognose);
            prepareStatement.setInt(8, ankunftsverspatung ? 1 : 0);
            prepareStatement.setString(9, abfahrtszeit);
            prepareStatement.setString(10, ab_prognose);
            prepareStatement.setString(11, ab_prognose_status);
            prepareStatement.setInt(12, faellt_aus_tf ? 1 : 0);
            prepareStatement.setInt(13, zusatzfahrt_tf ? 1 : 0);
            prepareStatement.setInt(14, durchfahrt_tf ? 1 : 0);
            prepareStatement.setString(15, fahrt_bezeichner);
            prepareStatement.setString(16, lod);
            prepareStatement.setInt(17, betriebstagID);
            prepareStatement.setInt(18, betreiber_nr_pk);
            prepareStatement.setInt(19, haltestelleID);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("betreriber_nr_pk = " + betreiber_nr_pk + " betriebstagID = " + betriebstagID + " haltestelleID = " + haltestelleID);
            e.printStackTrace();
        }

    }
}
