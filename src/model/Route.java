package model;

import java.util.List;

public class Route {
    private String routeId;
    private Station startStation;
    private Station endStation;
    private List<Station> intermediateStations;

    public Route(String routeId, Station startStation, Station endStation, List<Station> intermediateStations) {
        this.routeId = routeId;
        this.startStation = startStation;
        this.endStation = endStation;
        this.intermediateStations = intermediateStations;
    }

    public Station getStartStation() {
        return startStation;
    }

    public Station getEndStation() {
        return endStation;
    }
}