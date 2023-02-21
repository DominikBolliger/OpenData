import java.util.List;

public class OpenData {
    public static void main(String[] args) {
        List trafficList = List.of(APIConsumer.getTraffic());
        DBConnection con = new DBConnection("jdbc:mysql://localhost:3306/opendata", "root", "");
        for (Object actualLine: trafficList){
            con.connect();
            Traffic trafficLine = (Traffic) actualLine;
            int betreiber_nr_pk = con.checkIfBetreiberExists(trafficLine.betreiber_id());
            if (betreiber_nr_pk == -1){
                betreiber_nr_pk = con.createBetreiberData(trafficLine.betreiber_id(), trafficLine.betreiber_abk(), trafficLine.betreiber_name());
            }
            int haltestelleID = con.checkIfHaltestelleExists(trafficLine.bpuic());
            if (haltestelleID == -1){
                haltestelleID = con.createHaltestelleData(trafficLine.bpuic(), trafficLine.geopos(), trafficLine.haltestellen_name());
            }
            int betriebstagID = con.checkIfBetriebstagExists(trafficLine.betriebstag());
            if (betriebstagID == -1){
                betriebstagID = con.createBetriebstagData(trafficLine.betriebstag());
            }
            con.createFahrtData(trafficLine.produkt_id(), trafficLine.linien_id(), trafficLine.verkehrsmittel_text(),
                    trafficLine.linien_text(), trafficLine.ankunftszeit(), trafficLine.an_prognose_status(), trafficLine.an_prognose(),
                    trafficLine.ankunftsverspatung(), trafficLine.abfahrtszeit(), trafficLine.ab_prognose(), trafficLine.ab_prognose_status(),
                    trafficLine.faellt_aus_tf(), trafficLine.zusatzfahrt_tf(), trafficLine.durchfahrt_tf(), trafficLine.fahrt_bezeichner(),
                    trafficLine.lod(), betriebstagID, betreiber_nr_pk, haltestelleID);
            con.close();
        }
    }
}
