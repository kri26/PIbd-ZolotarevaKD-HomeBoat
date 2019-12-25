import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JPanel;

public class PanelParking extends JPanel {
	MultiLevelParking parking = new MultiLevelParking(5, 1000, 700);
	private final int countLevel = 5;
	private int presentLevel = 0;
	
	public void paint(Graphics g) {
		super.paint(g);
		parking.getParking(presentLevel).Draw(g);
	}
	
	public void setLevel(int index) {
		if (index >= 0 && index < countLevel) {
			presentLevel = index;
		}
	}
	
	public int addBoat(ITransport transport) {
		return parking.getParking(presentLevel).addBoat(transport);
	}
	
	public ITransport delBoat(int index) {
		ITransport transport = parking.getParking(presentLevel).deletBoat(index);
		return transport;
	}
	
	public ITransport getTransport(int i) {
		return parking.getTransport(i, presentLevel);
	}
	
	public void SaveData(String filename) throws IOException {
		try {
			parking.SaveData(filename);
		} catch (IOException ex) {
			throw ex; 
		}
	}

	public void LoadData(String filename) throws ParkingOccupiedPlaceException, IOException  {
		try {
			parking.LoadData(filename);
		}
		catch (ParkingOccupiedPlaceException | IOException ex) {
			if (ex instanceof ParkingOccupiedPlaceException) {
				throw new ParkingOccupiedPlaceException(parking.getCounter());
			} else throw new IOException();
		}
	}

	public void SaveCurrentLevel(String filename) {
		try {
			parking.SaveLevel(filename, presentLevel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void LoadCurrentLevel(String filename) throws ParkingOccupiedPlaceException, IOException {
		try {
			parking.LoadLevel(filename);
		} catch (ParkingOccupiedPlaceException ex) {
			throw ex;			
		} catch (IOException ex) {
			throw ex;
		}
	}
}