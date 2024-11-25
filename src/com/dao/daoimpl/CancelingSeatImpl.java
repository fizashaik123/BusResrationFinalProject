package com.dao.daoimpl;

import com.dao.dao.CancelSeatDao;
import com.dao.model.Booking;
import com.dao.model.Bus;
import com.dao.util.DBConnection;

import java.sql.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CancelingSeatImpl implements CancelSeatDao {

    private Connection con = DBConnection.Connection();

    final static String BOOKING_DETAILS = "select * from booking where booking_id=?";
    final static String CANCEL_QUERY = "delete from booking where booking_id=?";
    final static String BUS_DETAILS = "select * from bus where bus_id=?";
    final static String UPDATE_CANCEL_SEAT = "update bus set available_seats=? where bus_id=?";

    // Logger setup
    private static final Logger logger = Logger.getLogger(CancelingSeatImpl.class.getName());

    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL); // Capture all log levels
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL); // Set root logger level to ALL
    }

    @Override
    public Booking bookingDetails(int bookingId) {
        Booking booking = null;
        try  {
            PreparedStatement pstmt1 = con.prepareStatement(BOOKING_DETAILS);
            pstmt1.setInt(1, bookingId);
            ResultSet rs = pstmt1.executeQuery();

            if (rs.next()) {
                //inserting the details
                int seatBooked = rs.getInt("seat_booked");
                int busId = rs.getInt("bus_id");
                String passengerName = rs.getString("passenger_name");
                String contactInfo = rs.getString("contact_info");
                int bookedSeats = rs.getInt("seat_booked");
                double ticketPrice = rs.getDouble("ticket_price");

                booking = new Booking(seatBooked, busId, passengerName, contactInfo, bookedSeats, ticketPrice);
                logger.info("Fetched booking details: " + booking); // Log successful booking details fetch
            }

        } catch (SQLException e) {
            logger.severe("SQL Syntax Error while fetching booking details: " + e.getMessage()); // Log error
        }
        return booking;
    }

    @Override
    public void deleteSeat(int bookingId) {
        try  {
            PreparedStatement pstmt = con.prepareStatement(CANCEL_QUERY);
            pstmt.setInt(1, bookingId);
            pstmt.executeUpdate();
            logger.info("Successfully cancelled booking with ID: " + bookingId); // Log successful cancellation

        } catch (SQLException throwables) {
            logger.severe("SQL Error while cancelling booking ID " + bookingId + ": " + throwables.getMessage()); // Log error
        }
    }

    @Override
    public void updateSeats(int busId, int availableSeats) {
        try  {
            PreparedStatement pstmt = con.prepareStatement(UPDATE_CANCEL_SEAT);
            pstmt.setInt(1, availableSeats);
            pstmt.setInt(2, busId);
            pstmt.executeUpdate();
            logger.info("Updated available seats for bus ID " + busId + " to " + availableSeats); // Log seat update

        } catch (Exception e) {
            logger.severe("Error updating available seats for bus ID " + busId + ": " + e.getMessage()); // Log error
        }
    }

    @Override
    public Bus busDetails(int busId) {
        Bus bus = null;
        try  {
            PreparedStatement pstmt = con.prepareStatement(BUS_DETAILS);
            pstmt.setInt(1, busId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                //Fetch the bus details
                String busNumber = rs.getString("bus_number");
                int availableSeats = rs.getInt("available_seats");
                Timestamp departureTime = rs.getTimestamp("departure_time");
                Timestamp arrivalSeats = rs.getTimestamp("arrival_time");
                int routeId = rs.getInt("route_id");

                bus = new Bus(busId, busNumber, availableSeats, departureTime, arrivalSeats, routeId);
                logger.info("Fetched bus details: " + bus); // Log successful bus details fetch
            }
        } catch (Exception e) {
            logger.severe("Error fetching bus details for bus ID " + busId + ": " + e.getMessage()); // Log error
        }
        return bus;
    }
}

