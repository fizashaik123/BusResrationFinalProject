package com.dao.service;

import com.dao.model.Bus;
import com.dao.model.Route;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BookingService {
    public static Route route;
    public static Bus bus;

    public static double calculateTicketPrice(LocalDateTime localDateTime, int distance, double peakRate, double offPeakRate, int seatBooked, Timestamp departureTimeSql ) {
        double ticketPrice = 0.0;
        if (departureTimeSql.after(Timestamp.valueOf(localDateTime))) {
            ticketPrice = distance * peakRate * seatBooked;
        } else {
            ticketPrice = distance * offPeakRate * seatBooked;
        }
        return ticketPrice;

    }


}