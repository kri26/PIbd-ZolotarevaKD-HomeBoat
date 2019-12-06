import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Boat extends Vehicle {
    private int _pictureWidth;
    private int _pictureHeight;
    private int planeWidth = 90;
    private int planeHeight = 50;
    public int MaxSpeed;
    public float Weight;
    DrawMotor drawMotor;

    public Boat(int maxSpeed, float weight, Color mainColor) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }

    public void DrawBoat(Graphics g) {
			        Graphics2D g2 = (Graphics2D) g;
			        Ellipse2D oval = new Ellipse2D.Double(_startPosX, _startPosY + 30, 90, 20);
			        g2.setPaint(MainColor);
			        g2.fill(oval);
        }
}