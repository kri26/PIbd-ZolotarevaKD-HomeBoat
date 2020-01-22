import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SportBoat extends Boat {
    private int _pictureWidth;
    private int _pictureHeight;
    private int planeWidth = 90;
    private int planeHeight = 50;
    public int MaxSpeed;
    public float Weight;
    public Color DopColor;
    IMotors drawMotor;

    public SportBoat(int maxSpeed, float weight, Color mainColor, Color dopColor) {
    	super(maxSpeed, weight, mainColor);
    	MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        DopColor = dopColor;
    }
    
    public SportBoat(String info)
    {
    	super(info);
        String[] strs = info.split(";");
        if (strs.length == 4)
        {
            MaxSpeed = Integer.parseInt(strs[0]);
            Weight = Float.parseFloat(strs[1]);
            MainColor = new Color(Integer.parseInt(strs[2]));
            DopColor = new Color(Integer.parseInt(strs[3]));
        }
    }
    
    public void setDopColor(Color dop) {
		DopColor = dop;
	}
    
    public void setMotors(IMotors iMotors) {
		drawMotor = iMotors;
	}
    
    public IMotors getMotors() {
    	return drawMotor;
    }

    @Override
    public String ToString()
    {
        return super.ToString() + ";" + DopColor.getRGB() + ";";
    }

    public void DrawBoat(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g;
	        Rectangle2D rectangle = new Rectangle2D.Double( _startPosX + 20, _startPosY + 10, 50, 30);
	        Rectangle2D rectangleSmall = new Rectangle2D.Double( _startPosX + 30, _startPosY, 10, 10);
	        g2.setPaint(Color.GRAY);
	        g2.fill(rectangle);
	        g2.setPaint(Color.BLACK);
	        g2.fill(rectangleSmall);
    		super.DrawBoat(g);
    		g2.setPaint(DopColor);
    		if(drawMotor != null) {
    			drawMotor.DrawM(g, DopColor, _startPosX, _startPosY);
    		} else {
    		 g.fillOval(_startPosX, _startPosY + 25, 15, 10);
             g.fillOval(_startPosX, _startPosY + 35, 15, 10);
             g.fillOval(_startPosX, _startPosY + 45, 15, 10);
    		}
    }
    
    @Override
	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(SportBoat boat) {
		if (ToString().equals(boat.ToString())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return equals((SportBoat)obj);
	}

	public int compareTo(SportBoat boat) {
		if (boat == null)
			return 1;
        if (!DopColor.equals(boat.DopColor))
        	return Integer.compare(DopColor.getRGB(), boat.DopColor.getRGB());
        if (MaxSpeed != boat.MaxSpeed)
        	return Integer.compare(MaxSpeed, boat.MaxSpeed);
        if (Weight != boat.Weight)
        	return Integer.compare((int)Weight, (int)boat.Weight);
        if (!MainColor.equals(boat.MainColor))
        	return Integer.compare(MainColor.getRGB(), boat.MainColor.getRGB());
        return 0;
	}
}
