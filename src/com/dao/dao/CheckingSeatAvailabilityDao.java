package com.dao.dao;

public interface CheckingSeatAvailabilityDao {

    //Checking whether the seats are present in seat
    void checkSeatPresence(String busNumber);

    //Retrieve the bus details.
    void busNumber();
}
