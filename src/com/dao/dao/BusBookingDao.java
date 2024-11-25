package com.dao.dao;

import java.time.LocalDateTime;

public interface BusBookingDao {

    //Retrieve data how many bus numbers and from origin to the destination of the bus number.
    void busNumber();

    //Book a seat with passenger details
    void bookSeat(String busNumber, int seatBooked, String passengerName, String contactInfo, LocalDateTime localDateTime);

    //Exit the application
    void exitApplication();
}
