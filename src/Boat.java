import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

public class Boat extends Vehicle implements Comparable<Boat>, Iterator<String>, Iterable<String> {
    private int _pictureWidth;
    private int _pictureHeight;
    private int planeWidth = 90;
    private int planeHeight = 50;
    public int MaxSpeed;
    public float Weight;
    DrawMotor drawMotor;
    private int _currentIndex = -1;

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
    
    @Override
   	public int hashCode() {
   		return super.hashCode();
   	}

   	@Override
   	public boolean equals(Object obj) {
   		if (obj == null)
   			return false;
   		if (getClass() != obj.getClass())
   			return false;
   		Boat other = (Boat) obj;
   		if (!MainColor.equals(other.MainColor))
   			return false;
   		if (MaxSpeed != other.MaxSpeed)
   			return false; 
   		return true;
   	}
   	
   	public int compareTo(Boat other) {
		if (other == null)
            return 1;
        if (MaxSpeed != other.MaxSpeed)
            return Integer.compare(MaxSpeed, other.MaxSpeed);
        if (MainColor != other.MainColor)
            return Integer.compare(MainColor.getRGB(), other.MainColor.getRGB());
        return 0;
	}

	@Override
	public boolean hasNext() {
		if (_currentIndex + 1 >= ToString().split(";").length)
		{
			_currentIndex = -1;
			return false;
		}else
			return true;
	}

	@Override
	public String next() {
		return ToString().split(";")[++_currentIndex];
	}

	@Override
	public Iterator<String> iterator() {
		return this;
	}
}