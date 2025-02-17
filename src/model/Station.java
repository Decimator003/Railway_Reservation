package model;

public class Station {
    private String stationId;
    private String stationName;
    private String location;

    public Station(String stationId, String stationName, String location) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.location = location;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationId() {
        return stationId;
    }

    public String getLocation() {
        return location;
    }
}