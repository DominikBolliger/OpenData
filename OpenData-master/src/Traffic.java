import java.util.ArrayList;
import java.util.List;

public class Traffic {
        private String betriebstag;
        private String fahrt_bezeichner;
        private String betreiber_id;
        private String betreiber_abk;
        private String betreiber_name;
        private String produkt_id;
        private String linien_id;
        private String linien_text;
        private String umlauf_id;
        private String verkehrsmittel_text;
        private String zusatzfahrt_tf;
        private boolean faellt_aus_tf;
        private int bpuic;
        private String haltestellen_name;
        private String ankunftszeit;
        private String an_prognose;
        private String an_prognose_status;
        private String abfahrtszeit;
        private String ab_prognose;
        private String ab_prognose_status;
        private String durchfahrt_tf;
        private boolean ankunftsverspatung;
        private Geopos geopos;
        private String lod;
        public static List<Traffic> trafficList = new ArrayList<>();

    public Traffic(String betriebstag, String fahrt_bezeichner, String betreiber_id, String betreiber_abk, String betreiber_name, String produkt_id, String linien_id, String linien_text, String umlauf_id, String verkehrsmittel_text, String zusatzfahrt_tf, boolean faellt_aus_tf, int bpuic, String haltestellen_name, String ankunftszeit, String an_prognose, String an_prognose_status, String abfahrtszeit, String ab_prognose, String ab_prognose_status, String durchfahrt_tf, boolean ankunftsverspatung, Geopos geopos, String lod) {
        this.betriebstag = betriebstag;
        this.fahrt_bezeichner = fahrt_bezeichner;
        this.betreiber_id = betreiber_id;
        this.betreiber_abk = betreiber_abk;
        this.betreiber_name = betreiber_name;
        this.produkt_id = produkt_id;
        this.linien_id = linien_id;
        this.linien_text = linien_text;
        this.umlauf_id = umlauf_id;
        this.verkehrsmittel_text = verkehrsmittel_text;
        this.zusatzfahrt_tf = zusatzfahrt_tf;
        this.faellt_aus_tf = faellt_aus_tf;
        this.bpuic = bpuic;
        this.haltestellen_name = haltestellen_name;
        this.ankunftszeit = ankunftszeit;
        this.an_prognose = an_prognose;
        this.an_prognose_status = an_prognose_status;
        this.abfahrtszeit = abfahrtszeit;
        this.ab_prognose = ab_prognose;
        this.ab_prognose_status = ab_prognose_status;
        this.durchfahrt_tf = durchfahrt_tf;
        this.ankunftsverspatung = ankunftsverspatung;
        this.geopos = geopos;
        this.lod = lod;
    }

    @Override
    public String toString() {
        return "Verkehrsdaten{" +
                "betriebstag='" + betriebstag + "\n" +
                ", fahrt_bezeichner='" + fahrt_bezeichner + "\n" +
                ", betreiber_id='" + betreiber_id + "\n" +
                ", betreiber_abk='" + betreiber_abk + "\n" +
                ", betreiber_name='" + betreiber_name + "\n" +
                ", produkt_id='" + produkt_id + "\n" +
                ", linien_id='" + linien_id + "\n" +
                ", linien_text='" + linien_text + "\n" +
                ", umlauf_id='" + umlauf_id + "\n" +
                ", verkehrsmittel_text='" + verkehrsmittel_text + "\n" +
                ", zusatzfahrt_tf='" + zusatzfahrt_tf + "\n" +
                ", faellt_aus_tf=" + faellt_aus_tf + "\n" +
                ", bpuic=" + bpuic + "\n" +
                ", haltestellen_name='" + haltestellen_name + "\n" +
                ", ankunftszeit='" + ankunftszeit + "\n" +
                ", an_prognose='" + an_prognose + "\n" +
                ", an_prognose_status='" + an_prognose_status + "\n" +
                ", abfahrtszeit='" + abfahrtszeit + "\n" +
                ", ab_prognose='" + ab_prognose + "\n" +
                ", ab_prognose_status='" + ab_prognose_status + "\n" +
                ", durchfahrt_tf='" + durchfahrt_tf + "\n" +
                ", ankunftsverspatung=" + ankunftsverspatung + "\n" +
                ", geopos='" + geopos + "\n" +
                ", lod='" + lod + "\n" +
                '}';
    }

    public String betriebstag() {
        return betriebstag;
    }

    public String fahrt_bezeichner() {
        return fahrt_bezeichner;
    }

    public String betreiber_id() {
        return betreiber_id;
    }

    public String betreiber_abk() {
        return betreiber_abk;
    }

    public String betreiber_name() {
        return betreiber_name;
    }

    public String produkt_id() {
        return produkt_id;
    }

    public String linien_id() {
        return linien_id;
    }

    public String linien_text() {
        return linien_text;
    }

    public String umlauf_id() {
        return umlauf_id;
    }

    public String verkehrsmittel_text() {
        return verkehrsmittel_text;
    }

    public boolean zusatzfahrt_tf() {
        return Boolean.parseBoolean(zusatzfahrt_tf);
    }

    public boolean faellt_aus_tf() {
        return faellt_aus_tf;
    }

    public int bpuic() {
        return bpuic;
    }

    public String haltestellen_name() {
        return haltestellen_name;
    }

    public String ankunftszeit() {
        return ankunftszeit;
    }

    public String an_prognose() {
        return an_prognose;
    }

    public String an_prognose_status() {
        return an_prognose_status;
    }

    public String abfahrtszeit() {
        return abfahrtszeit;
    }

    public String ab_prognose() {
        return ab_prognose;
    }

    public String ab_prognose_status() {
        return ab_prognose_status;
    }

    public boolean durchfahrt_tf() {
        return Boolean.parseBoolean(durchfahrt_tf);
    }

    public boolean ankunftsverspatung() {
        return ankunftsverspatung;
    }

    public String geopos() {
        if (geopos == null){
            this.geopos = new Geopos(0.0, 0.0);
        }
        return geopos.toString();
    }

    public String lod() {
        return lod;
    }
}
