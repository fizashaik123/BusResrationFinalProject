package com.dao.model;

public class Booking {
    private int bookingId;
    private int busId;
    private String passengerName;
    private String contactInfo;
    private int seatsBooked;
    private double ticketPrice;

    public Booking(int bookingId, int busId, String passengerName, String contactInfo, int seatsBooked, double ticketPrice) {
        this.bookingId = bookingId;
        this.busId = busId;
        this.passengerName = passengerName;
        this.contactInfo = contactInfo;
        this.seatsBooked = seatsBooked;
        this.ticketPrice = ticketPrice;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}

