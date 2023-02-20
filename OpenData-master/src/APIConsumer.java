public class APIConsumer {
    public static Traffic[] getTraffic() {
        return (Traffic[]) RestClientGET.request("https://data.sbb.ch", "/api/v2/catalog/datasets/ist-daten-sbb/exports/json", Traffic[].class);
    }
}
