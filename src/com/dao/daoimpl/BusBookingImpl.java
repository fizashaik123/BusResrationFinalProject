package com.dao.daoimpl;

import com.dao.dao.BusBookingDao;
import com.dao.service.BookingService;
import com.dao.util.DBConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BusBookingImpl implements BusBookingDao {

   private Connection con = DBConnection.Connection();

    final static String EXECUTE_QUERY1 = "select bus_number from bus";
    final static String EXECUTE_QUERY2 = "select origin,destination from route";
    final static String BUS_NUMBER = "select bus_id,available_seats,departure_time from bus where bus_number=?";
    final static String BOOKING_QUERY = "insert into booking(bus_id,passenger_name,contact_info,seat_booked,ticket_price) values(?,?,?,?,?)";
    final static String UPDATE_QUERY = "update bus set available_seats=available_seats-? where bus_id=?";
    final static String TICKET_PRICE = "select distance_KM,peak_rate,off_peak_rate from route where route_id=(select route_id from bus where bus_id=?)";

    // Logger setup
    private static final Logger logger = Logger.getLogger(BusBookingImpl.class.getName());

    static {
        // Set up console logging
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL); // Capture all log levels
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL); // Set the logging level to ALL for all messages
    }

    @Override
    public void busNumber() {
        CheckingSeatAvailabilityImpl.checkingSeat(EXECUTE_QUERY1, EXECUTE_QUERY2);
        logger.info("Displayed available buses and routes."); // Log user action
    }

    @Override
    public void bookSeat(String busNumber, int seatBooked, String passengerName, String contactInfo, LocalDateTime localDateTime) {
        try  {
            // Fetch bus details
            PreparedStatement pstmt1 = con.prepareStatement(BUS_NUMBER);
            pstmt1.setString(1, busNumber);
            ResultSet rs = pstmt1.executeQuery();

            if (rs.next()) {
                int busIdNo = rs.getInt("bus_id");
                int seats = rs.getInt("available_seats");
                Timestamp departureTimeSql = rs.getTimestamp("departure_time");

                // Fetch route details
                PreparedStatement pstmt3 = con.prepareStatement(TICKET_PRICE);
                pstmt3.setInt(1, busIdNo);
                ResultSet rs1 = pstmt3.executeQuery();

                if (rs1.next()) {
                    int distance = rs1.getInt("distance_KM");
                    double peakRate = rs1.getDouble("peak_rate");
                    double offPeakRate = rs1.getDouble("off_peak_rate");

                    // Check availability of seats
                    if (seats > 0 && seats >= seatBooked) {
                        // Insert booking details
                        PreparedStatement pstmt2 = con.prepareStatement(BOOKING_QUERY);
                        pstmt2.setInt(1, busIdNo);
                        pstmt2.setString(2, passengerName);
                        pstmt2.setString(3, contactInfo);
                        pstmt2.setInt(4, seatBooked);
                        pstmt2.setDouble(5, BookingService.calculateTicketPrice(localDateTime, distance, peakRate, offPeakRate, seatBooked, departureTimeSql));
                        pstmt2.executeUpdate();

                        // Update available seats
                        PreparedStatement pstmt4 = con.prepareStatement(UPDATE_QUERY);
                        pstmt4.setInt(1, seatBooked);
                        pstmt4.setInt(2, busIdNo);
                        pstmt4.executeUpdate();

                        // Log successful booking
                        logger.info("Successfully booked " + seatBooked + " seat(s) for passenger " + passengerName + " on bus number " + busNumber);
                        System.out.println("Seat Booked Successfully");
                    } else {
                        logger.warning("Seats not available or insufficient seats for booking on bus number " + busNumber);
                        System.out.println("Seat Not Available. Please try again");
                    }
                }
            } else {
                logger.warning("Invalid bus number entered: " + busNumber);
                System.out.println("Invalid Bus number. Please try again.");
            }
        } catch (SQLException throwables) {
            // Log SQL syntax error
            logger.severe("SQL syntax error while booking seat for bus number " + busNumber + ": " + throwables.getMessage());
            System.out.println("SQL syntax error: " + throwables.getMessage());
        }
    }

    @Override
    public void exitApplication() {
        logger.info("Application exited by the user."); // Log user action on exiting
        System.out.println("EXITED");
        System.exit(0);
    }
}

