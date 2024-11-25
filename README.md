This is project about the BUS RESERVATION SYSTEM

Objective:In this project we develop a console based system to perform the operations to book a seat,cancel a seat,checking seat is available or not.

Languages used:Java,Mysql
Connector used:Mysql connector.

In this project we perform on bus reservation operations.
We have the data of Routes,Buses and Bookings.
In routes, we have data like route_id,origin,destination,distance_KM ,double peak_rate,off_peak_rate.
In Buses, we have data like busId,busNumber,available_seats,departureTime,arrivalTime,routeId.
In Bookings, we have data like bookingId,bus_id,passengerName,contactInfo,seatsBooked,ticketPrice.
By using the above data we perform these operations.
The operations are:

1.Book A Seat :
 In this operation we take the Booking person details and store in the booking data in the booking table.
The bus number will calculate the ticket price by the route distance ,here the departure time will take the rates cost by the peak rate or off-peak rate.
Calculate the ticket price,give the ticket price to the booking details.

2.Cancel A Seat:
The booking data is cancelled.The seats booked in the booking details get cancelled and added to the bus available seats.So that other person will book the seats.

3.Check Seat Availability:
Checking the seats are available or not.
After checking the seats are present in the bus it will give the count of available seats to the user.
If seats are not present get a text like no seats present.

4.Exist:
Existing the system once all operations or required operations are performed.