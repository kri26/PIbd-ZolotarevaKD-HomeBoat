public class ParkingOverflowException extends IndexOutOfBoundsException {
	public ParkingOverflowException() {
		super("Вы вышли за границы парковки");
	}
} 