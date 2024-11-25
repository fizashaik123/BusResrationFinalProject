import com.dao.dao.BusBookingDao;
import com.dao.daoimpl.BusBookingImpl;
import com.dao.service.BusService;
import exception.CustomExceptionHandling;

import java.util.Scanner;

public class BusReservationSystemConsoleApp {
    public static void main(String[] args) {
        BusBookingDao dao=new BusBookingImpl();
        Scanner scan=new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Enter the Required Number");
                System.out.println("1.Book A Seat:");
                System.out.println("2.Cancel A Seat:");
                System.out.println("3.Check Seat Availability:");
                System.out.println("4.Exit the System");
                System.out.println("---------------------------");
                System.out.println("Enter your choice:");

                int n = scan.nextInt();

                switch (n) {
                    case 1:
                        BusService.bookingASeat();
                        break;
                    case 2:
                        BusService.bookingValidation();
                        break;
                    case 3:
                        BusService.seatAvailability();
                        break;
                    case 4:
                        dao.exitApplication();
                        break;
                    default:
                        System.out.println("Invalid Number.Enter Valid Number from 1 to 4 \n\n\n");
                }

            }
        }catch (Exception e){
            throw new CustomExceptionHandling(e.getMessage());
        }
    }
}

