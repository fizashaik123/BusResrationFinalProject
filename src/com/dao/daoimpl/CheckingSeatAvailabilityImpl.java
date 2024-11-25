package com.dao.daoimpl;

import com.dao.dao.CheckingSeatAvailabilityDao;
import com.dao.util.DBConnection;

import java.sql.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckingSeatAvailabilityImpl implements CheckingSeatAvailabilityDao {
    private Connection con=DBConnection.Connection();

    final static String BUS_EXECUTE_QUERY = "select bus_number from bus";
    final static String ROUTE_EXECUTE_QUERY = "select origin, destination from route";
    final static String CHECKING_QUERY = "select bus_number ,available_seats from bus where bus_number=?";

    // Create a logger for the class
    private static final Logger logger = Logger.getLogger(CheckingSeatAvailabilityImpl.class.getName());

    // Setup logger (console handler, level)
    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL); // Capture all log levels
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL); // Set root logger level to ALL
    }

    @Override
    public void checkSeatPresence(String busNumber) {
        try  {
            PreparedStatement pstmt = con.prepareStatement(CHECKING_QUERY);
            pstmt.setString(1, busNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String busno = rs.getString("bus_number");
                int availableSeats = rs.getInt("available_seats");
                logger.info("Bus number: " + busno + "\tAvailable Seats: " + availableSeats);

                if (availableSeats == 0) {
                    logger.info("No Seats available");
                }
            } else {
                logger.warning("Invalid Bus Number: " + busNumber);
            }
        } catch (SQLException throwables) {
            logger.severe("SQL Syntax Error: " + throwables.getMessage());
        }
    }

    @Override
    public void busNumber() {
        checkingSeat(BUS_EXECUTE_QUERY, ROUTE_EXECUTE_QUERY);
    }

    static void checkingSeat(String busExecuteQuery, String routeExecuteQuery) {
        try (Connection con = DBConnection.Connection()) {
            Statement pstmt5 = con.createStatement();
            ResultSet rs = pstmt5.executeQuery(busExecuteQuery);
            int i = 1;

            Statement pstmt6 = con.createStatement();
            ResultSet rs1 = pstmt6.executeQuery(routeExecuteQuery);
            while (rs1.next() && rs.next()) {
                logger.info(i + "\t" + rs.getString("bus_number") + "\tgoes from\t" +
                        rs1.getString("origin") + "\t to \t" + rs1.getString("destination"));
                i++;
            }
        } catch (SQLException e) {
            logger.severe("SQL Syntax Error: " + e.getMessage());
        }
    }
}
