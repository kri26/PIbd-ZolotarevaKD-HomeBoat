import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Parking<T extends ITransport, M extends IMotors> {
	private T[] _places;

    private int PictureWidth;
    private int PictureHeight;

    private final int _placeSizeWidth = 210;
    private final int _placeSizeHeight = 80;
    
	ArrayList list = new ArrayList();

    public Parking(int sizes, int pictureWidth, int pictureHeight)
    {
        _places = (T[]) new ITransport[sizes];
        PictureWidth = pictureWidth;
        PictureHeight = pictureHeight;
        

        for (int i = 0; i < _places.length; i++) {
            _places[i] = null;
        }
    }
    
    public int getPictureWidth() {
    	return PictureWidth;
    }
    
    public int getPictureHeight() {
    	return PictureHeight;
    }
    
    public void setPictureWidth(int value) {
    	PictureWidth = value;
    }
    
    public void setPictureHeight(int value) {
    	PictureHeight = value;
    }

    public int addBoat(T boat)
    {
        for (int i = 0; i < _places.length; i++) {
            if (CheckFreePlace(i)) {
                _places[i] = boat;
                int [] x = {5 + i / 5 * _placeSizeWidth + 5};
                int [] y = {i % 5 * _placeSizeHeight + 15};
                _places[i].SetPosition(x, y, 1,PictureWidth, PictureHeight);
                return i;
            }
        }
        return -1;
    }
    
    public int addManyBoat(T boat)
    {
    	list.add(boat);
    	list.add(boat);
    	list.add(boat);
    	list.add(boat);
    	list.add(boat);
    	int [] x = new int[list.size()];
    	int [] y = new int[list.size()];
        for (int i = 0; i < _places.length; i++) {
            if (CheckFreePlace(i)) {
            	for(int j = 0; j < list.size() && i < _places.length; j++) {
	            	_places[i] = (T) list.get(j);
	            	x[j] = (int)(5 + i / 5 * _placeSizeWidth + 5);
	            	y[j] = (int)(i % 5 * _placeSizeHeight + 15);
	            	_places[i].SetPosition(x, y, list.size(),PictureWidth, PictureHeight);
	                i++;
            	}
	            list.remove(boat);
	            list.remove(boat); 
	            list.remove(boat); 
	            list.remove(boat);  
	            list.remove(boat); 
            	return i;
            }
        }
        list.remove(boat);
        list.remove(boat); 
        list.remove(boat); 
        list.remove(boat); 
        list.remove(boat); 
        return -1;
    }

    public T deletManyBoat(int index)
    {
        if (index < 1 || index > 4) {
            return null;
        }
        switch(index)
        {
        	case 1:
       		 	if (!CheckFreePlace(0)) {
     	         T boat = _places[0];
        		 for(int j = 0; j < 5; j++) {
	        	            	_places[j] = null;
	        	        }
	            return boat;
	            }
        		break;
        	case 2:
        		if (!CheckFreePlace(5)) {
        	    T boat = _places[5];
           		 for(int j = 5; j < 10; j++) {
   	        	            	_places[j] = null;
   	        	        }
   	            return boat;
   	            }
        		break;
        	case 3:
        		if (!CheckFreePlace(10)) {
        	    T boat = _places[10];
           		 for(int j = 10; j < 15; j++) {
   	        	            	_places[j] = null;
   	        	        }
   	            return boat;
   	            }
       		 	break;
        	case 4:
        		if (!CheckFreePlace(15)) {
        	         T boat = _places[15];
           		 for(int j = 15; j < 20; j++) {
   	        	            	_places[j] = null;
   	        	        }
   	            return boat;
   	            }
       		 break;
        }
        return null;
    }
    
    public T deletBoat(int index)
    {
        if (index < 0 || index > _places.length) {
            return null;
        }
        if (!CheckFreePlace(index)) {
            T boat = _places[index];
            _places[index] = null;
            return boat;
        }

        return null;
    }
    

    private boolean CheckFreePlace(int index)
    {
        return _places[index] == null;
    }

    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < _places.length; i++) {
            if (!CheckFreePlace(i)) {
                _places[i].DrawBoat(g);
            }
        }
    }

    private void DrawMarking(Graphics g)
    {
    	g.setColor(Color.black);
        g.drawRect(0, 0, (_places.length / 5) * _placeSizeWidth, 480);
        for (int i = 0; i < _places.length / 5; i++) {
            for (int j = 0; j < 6; j++){
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight,i * _placeSizeWidth + 110, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
        }
    }
}