public class Geopos {
    private Double lon;
    private Double lat;

    public Geopos(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return lon + "," + lat;
    }

    public Double lon() {
        return lon;
    }

    public Double lat() {
        return lat;
    }
}
