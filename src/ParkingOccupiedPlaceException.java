public class ParkingOccupiedPlaceException extends Exception {
	public ParkingOccupiedPlaceException(int index) {
		super("����� ��� ���� �����" + index);
	}
}