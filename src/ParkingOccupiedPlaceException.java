public class ParkingOccupiedPlaceException extends Exception {
	public ParkingOccupiedPlaceException(int index) {
		super("Здесь уже есть катер" + index);
	}
}