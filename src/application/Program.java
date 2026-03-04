package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;
import model.exception.DomainException;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			System.out.print("Room Number: ");
			int roomNumber = sc.nextInt();
			sc.nextLine();

			System.out.print("Check-in Date: ");
			LocalDate checkIn = LocalDate.parse(sc.nextLine(), fmt);

			System.out.print("Check-out Date: ");
			LocalDate checkOut = LocalDate.parse(sc.nextLine(), fmt);

			Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);

			System.out.println("Reservation: " + reservation);

			System.out.println("");
			System.out.println("Enter Data to update Reservation: ");
			System.out.print("Check-in date: ");
			checkIn = LocalDate.parse(sc.next(), fmt);

			System.out.print("Check-out date: ");
			checkOut = LocalDate.parse(sc.next(), fmt);

			reservation.updateDates(checkIn, checkOut);

			System.out.println("");

			System.out.println("Reservation: " + reservation);

		} 
		catch (DateTimeParseException e) {
			System.out.println("Invalid date format");
		} 
		catch (DomainException e) {
			System.out.println("Error in reservation " + e.getMessage());
		}
		catch (RuntimeException e) {
			System.out.println("Unexpected error");//Tratar qualquer outra excecao
		}
		sc.close();
	}

}
