package com.dao.model;

public class Route {
    private int routeId;
    private String origin;
    private String destination;
    private int distanceKM;
    private double peakRate;
    private double offPeakRate;

    public Route(int routeId, String origin, String destination, int distanceKM, double peakRate, double offPeakRate) {
        this.routeId = routeId;
        this.origin = origin;
        this.destination = destination;
        this.distanceKM = distanceKM;
        this.peakRate = peakRate;
        this.offPeakRate = offPeakRate;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDistanceKM() {
        return distanceKM;
    }

    public void setDistanceKM(int distanceKM) {
        this.distanceKM = distanceKM;
    }

    public double getPeakRate() {
        return peakRate;
    }

    public void setPeakRate(double peakRate) {
        this.peakRate = peakRate;
    }

    public double getOffPeakRate() {
        return offPeakRate;
    }

    public void setOffPeakRate(double offPeakRate) {
        this.offPeakRate = offPeakRate;
    }
}
