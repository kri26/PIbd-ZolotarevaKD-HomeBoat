import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelTakeBoat extends JPanel {
	
	ITransport transport;
	
	public void setTransport(ITransport t) {
		transport = t;
		transport.SetPosition(10, 10, 90, 50);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (transport != null) {
			transport.DrawBoat(g);
		}
	}
}