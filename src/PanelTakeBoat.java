import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelTakeBoat extends JPanel {
	
	ITransport transport;
	
	public void setTransport(ITransport t) {
		transport = t;
		int [] x = {15};
		int [] y = {15};
		transport.SetPosition(x, y, 1, 90, 50);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (transport != null) {
			transport.DrawBoat(g);
		}
	}
}