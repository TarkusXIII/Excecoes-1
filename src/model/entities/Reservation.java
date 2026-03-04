package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import model.exception.DomainException;


public class Reservation {

	private Integer roomNumber;
	private LocalDate checkin;
	private LocalDate checkout;

	private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Reservation() {

	}

	public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) throws DomainException {
		if (!checkout.isAfter(checkin)) {
			throw new DomainException ("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public long duration() {

		return ChronoUnit.DAYS.between(checkin, checkout);

	}

	public void updateDates(LocalDate checkin, LocalDate checkout) throws DomainException{
		LocalDate now = LocalDate.now();
		if (!checkout.isAfter(checkin)) {
			throw new DomainException ("Check-out date must be after check-in date");
		}
		if (checkin.isBefore(now) || checkout.isBefore(now)) {
			throw new DomainException("Revervation Dates for updates must be future dates");
		}

		this.checkin = checkin;
		this.checkout = checkout;
		
	}

	@Override
	public String toString() {
		return "Reservation [roomNumber=" + roomNumber + ", checkin=" + fmt.format(checkin) + ", checkout="
				+ fmt.format(checkout) + "]" + ", " + duration() + " nights";
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

}
