public class ParkingNotFoundException extends NullPointerException {
	public ParkingNotFoundException(int index) {
		super("Катер не найден " + index);
	}
}