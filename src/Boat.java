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
    
    public Boat(String info)
    {
        String[] strs = info.split(";");
        if (strs.length == 3)
        {
            MaxSpeed = Integer.parseInt(strs[0]);
            Weight = Float.parseFloat(strs[1]);
            MainColor = new Color(Integer.parseInt(strs[2]));
        }
    }

    public void DrawBoat(Graphics g) {
			        Graphics2D g2 = (Graphics2D) g;
			        Ellipse2D oval = new Ellipse2D.Double(_startPosX, _startPosY + 30, 90, 20);
			        g2.setPaint(MainColor);
			        g2.fill(oval);
        }
    
    @Override
	public String ToString()
    {
        return MaxSpeed + ";" + Weight + ";" + MainColor.getRGB();
    }
}