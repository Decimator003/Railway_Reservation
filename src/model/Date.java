package model;

public class Date {
    private String journeyDate;
    private String departureTime;
    private String arrivalTime;

    public Date(String journeyDate, String departureTime, String arrivalTime) {
        this.journeyDate = journeyDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getJourneyDate() {
        return journeyDate;
    }
}