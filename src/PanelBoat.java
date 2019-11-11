import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelBoat extends JPanel {

	ITransport tranport;

    public void renovate(int width, int height, boolean modelBoat) {
    	if(modelBoat) {
	        tranport = (ITransport)new SportBoat((int) (Math.random() * 200) + 100, (float) (Math.random() * 1000) + 1000, Color.orange,
	                Color.darkGray);
    	} else {
    		tranport = (ITransport)new Boat((int) (Math.random() * 200) + 100, (float) (Math.random() * 1000) + 1000, Color.orange);
    	}
        tranport.SetPosition((int) (Math.random() * 200) + 100, (int) (Math.random() * 100) + 50, width, height);
    }

    public void MoveTransport(Direction direction) {
        switch (direction) {
            case Right:
            	tranport.MoveTransport(Direction.Right);
                break;
            case Left:
            	tranport.MoveTransport(Direction.Left);
                break;
            case Up:
            	tranport.MoveTransport(Direction.Up);
                break;
            case Down:
            	tranport.MoveTransport(Direction.Down);
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (tranport != null) {
        	tranport.DrawBoat(g);
        }
    }
}