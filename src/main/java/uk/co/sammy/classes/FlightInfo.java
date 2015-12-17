package uk.co.sammy.classes;

public class FlightInfo {
    private String from;
    private String to;
    private int distance;
    private boolean skip;

    public FlightInfo(String from, String to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        skip = false;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
}