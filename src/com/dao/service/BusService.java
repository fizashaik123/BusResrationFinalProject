package com.dao.service;

import com.dao.dao.BusBookingDao;
import com.dao.dao.CancelSeatDao;
import com.dao.dao.CheckingSeatAvailabilityDao;
import com.dao.daoimpl.BusBookingImpl;
import com.dao.daoimpl.CancelingSeatImpl;
import com.dao.daoimpl.CheckingSeatAvailabilityImpl;
import com.dao.model.Booking;
import com.dao.model.Bus;
import java.time.LocalDateTime;
import java.util.Scanner;

public class BusService {

    public static void bookingValidation() {
        Scanner scan = new Scanner(System.in);
        CancelSeatDao dao = new CancelingSeatImpl();
        System.out.println("Enter the BookingID:");
        int bookingId = scan.nextInt();

        Booking booking=dao.bookingDetails(bookingId);

        if(booking==null){
            System.out.println("Invalid BookingId");
        }else {
            dao.deleteSeat(bookingId);
            System.out.println("BookingId cancelled successfully");
        }

        Bus bus = dao.busDetails(booking.getBusId());
        if(bus !=null){
            int updatedSeats = bus.getAvailableSeats() + booking.getSeatsBooked();

            dao.updateSeats(booking.getBusId(), updatedSeats);

        }else {
            System.out.println("Invalid BusId");
        }
    }
    public static void seatAvailability() {
        Scanner scan = new Scanner(System.in);
        CheckingSeatAvailabilityDao dao = new CheckingSeatAvailabilityImpl();
        System.out.println("List of buses");
        dao.busNumber();
        System.out.println("Enter the Bus_Number:");
        String busNo = scan.next();
        if (busNo.equalsIgnoreCase(busNo)) {
            System.out.println(busNo);
        }
        dao.checkSeatPresence(busNo);
    }
    public static void bookingASeat(){
        Scanner scan = new Scanner(System.in);
        BusBookingDao dao = new BusBookingImpl();
        System.out.println("List of Buses");
        dao.busNumber();
        System.out.println("enter the bus number");
        String busNumber=scan.next();
        busNumber.trim();
        System.out.println("How many seats to Book:");
        int seats=scan.nextInt();
        System.out.println("Enter departure time in format: YYYY-MM-DDTHH:MM:SS");
        String departureTime= scan.next();
        LocalDateTime dateTime1=LocalDateTime.parse(departureTime);
        System.out.println("Enter the Passenger Name:");
        String passengerName= scan.next();
        passengerName.trim();
        System.out.println("Enter the ContactInfo");
        String contactInfo=scan.next();

        dao.bookSeat(busNumber,seats,passengerName,contactInfo,dateTime1);
    }
}


