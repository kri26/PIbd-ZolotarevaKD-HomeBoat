import java.util.Hashtable;

public class MultiLevelParking {

	Hashtable <Integer,Parking<ITransport, IMotors>> parkingStages;

	private static final int countPlaces = 20;

	public MultiLevelParking(int countStages, int pictureWidth, int pictureHeight) {
		parkingStages = new Hashtable<Integer,Parking<ITransport, IMotors>>();
		for (int i = 0; i < countStages; ++i) {
			parkingStages.put(i,new Parking<ITransport, IMotors>(countPlaces, pictureWidth, pictureHeight));
		}
	}

	public Parking<ITransport, IMotors> getParking(int index) {

		if (index > -1 && index < parkingStages.size()) {
			return parkingStages.get(index);
		}
		return null;
	}

	public ITransport getTransport(int index, int lvl) {
		if (lvl > -1 && lvl < parkingStages.size()) {
			ITransport plane = parkingStages.get(lvl).deletBoat(index);
			return plane;
		}
		return null;
	}
} 