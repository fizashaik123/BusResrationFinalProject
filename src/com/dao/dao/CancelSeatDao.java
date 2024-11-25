package com.dao.dao;

import com.dao.model.Booking;
import com.dao.model.Bus;

public interface CancelSeatDao {

    //Retrieve the booking details
    Booking bookingDetails(int bookingId);

    //Delete the booking by bookingId
    void deleteSeat(int bookingId);

    //Update the seats that are cancelled in the bus data
    void updateSeats(int busId,int availableSeats);

    //Retrieve the bus details
    Bus busDetails(int busId);
}
