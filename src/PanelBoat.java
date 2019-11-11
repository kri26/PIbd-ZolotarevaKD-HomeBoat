import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelBoat extends JPanel {


    public PanelBoat() {

    }

    Boat boat;

    public void renovate(int width, int height) {
        boat = new Boat((int) (Math.random() * 200) + 100, (int) (Math.random() * 1000) + 1000, Color.orange,
                Color.darkGray);
        boat.SetPosition((int) (Math.random() * 200) + 100, (int) (Math.random() * 100) + 50, width, height);
    }

    public void MoveTransport(Direction direction) {
        switch (direction) {
            case Right:
                boat.MoveTransport(Direction.Right);
                break;
            case Left:
                boat.MoveTransport(Direction.Left);
                break;
            case Up:
                boat.MoveTransport(Direction.Up);
                break;
            case Down:
                boat.MoveTransport(Direction.Down);
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (boat != null) {
            boat.DrawBoat(g);
        }
    }
}