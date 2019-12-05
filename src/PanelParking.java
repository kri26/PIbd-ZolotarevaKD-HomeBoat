import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelParking extends JPanel {
	Parking parking = new Parking<ITransport, IMotors>(20, 1000, 700);
	
	public void paint(Graphics g) {
		super.paint(g);
		parking.Draw(g);
	}
	
	public int addBoat(ITransport transport) {
		return parking.addBoat(transport);
	}
	
	public ITransport delBoat(int index) {
		return parking.deletBoat(index);
	}
}