import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.IOException;
import java.util.Collections;

public class MultiLevelParking {

	ArrayList <Parking<ITransport, IMotors>> parkingStages;
	int pictureHeight;
	int pictureWidth;

    int counter = -1;

	public int getCounter() {
		return counter;
	}

	private static final int countPlaces = 20;

	public MultiLevelParking(int countStages, int _pictureWidth, int _pictureHeight) {
		pictureHeight = _pictureHeight;
		pictureWidth = _pictureWidth;
		parkingStages = new ArrayList<Parking<ITransport, IMotors>>();
		for (int i = 0; i < countStages; ++i) {
			parkingStages.add(new Parking<ITransport, IMotors>(countPlaces, pictureWidth, pictureHeight));
		}
	}

	public Parking<ITransport, IMotors> getParking(int index) {

		if (index > -1 && index < parkingStages.size()) {
			return parkingStages.get(index);
		}
		return null;
	}

	public ITransport getTransport(int index, int lvl) throws ParkingNotFoundException {
		if (lvl > -1 && lvl < parkingStages.size()) {
			try {
				ITransport transport = parkingStages.get(lvl)._places.get(index);
				parkingStages.get(lvl).deletBoat(index);
				return transport;
			}
			catch(ParkingNotFoundException ex) {
				throw new ParkingNotFoundException(index);
			}
		}
		return null;
	}
	
	 public boolean SaveData(String filename) throws IOException
	    { 
		 FileWriter fw = new FileWriter(filename);
	        WriteToFile("CountLeveles:"+ parkingStages.size() + "\n", fw);
	        for (Parking<ITransport, IMotors> level : parkingStages)
	        {
	            WriteToFile("Level" + "\n", fw);
	            for(ITransport transport : level) {
	            	if (transport.getClass().getName() == "Boat")
                    {
                        WriteToFile(level.GetKey() + ":Boat:", fw);
                    }
                    if (transport.getClass().getName() == "SportBoat")
                    {
                        WriteToFile(level.GetKey() + ":SportBoat:", fw);
                    }
                  WriteToFile(transport.ToString() + "\n", fw);
	            }
	        }
	        fw.flush();
	        return true;
	    }

	    public boolean SaveLevel(String filename, int lvl) throws IOException
	    {
          if ((lvl > parkingStages.size()) || (lvl < 0)) {
              return false;
            }
	    	FileWriter fw = new FileWriter(filename);
	        WriteToFile("Level:"+ lvl + "\n", fw);
	        Parking<ITransport, IMotors> level = parkingStages.get(lvl);
	        for (int i = 0; i < countPlaces; i++)
	        {
	            ITransport transport = level.get(i);
	            if (transport != null)
	            {
	                if (transport.getClass().getName() == "Boat")
	                {
	                    WriteToFile(i + ":Tractor:", fw);
	                }
	                if (transport.getClass().getName() == "SportBoat")
	                {
	                    WriteToFile(i + ":SportBoat:", fw);
	                }
	                WriteToFile(transport.ToString() + "\n", fw);
	            }
	        }
	        fw.flush();	   
	        return true;
	    }

	    private void WriteToFile(String text, FileWriter fw)
	    {
	        try {
				fw.write(text);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    public boolean LoadData(String filename) throws IOException, ParkingOccupiedPlaceException
	    {
	    	FileReader fr = new FileReader(filename);
	        String bufferTextFromFile = "";
	        int counter = -1;

	        int c;
	        while ((char)(c = fr.read()) != '\n') {
	        	bufferTextFromFile += (char)c;
	        }

	        if(bufferTextFromFile.contains("CountLeveles")) {
	        	int count = Integer.parseInt(bufferTextFromFile.split(":")[1]);
	        	if(parkingStages != null) {
	        		parkingStages.clear();
	        	}
	        	parkingStages = new ArrayList<Parking<ITransport, IMotors>>(count);
	            bufferTextFromFile = "";
	        } else {
	        	return false;
	        }

	        while ((c = fr.read()) != -1) {
	        	if ((char)c == '\n') {        		
	        		ITransport transport = null;

	                if(bufferTextFromFile.equals("Level")) {
	                	counter++;
	                	parkingStages.add(new Parking<ITransport, IMotors>(countPlaces, pictureWidth, pictureHeight));
	                    bufferTextFromFile = "";
	                	continue;
	                }

	                if (bufferTextFromFile.split(":").length > 1) {
	        	       	if(bufferTextFromFile.split(":")[1].equals("Boat")) {
	        	       		transport = new Boat(bufferTextFromFile.split(":")[2]);
	        	       	} 
	        	       	else if(bufferTextFromFile.split(":")[1].equals("SportBoat")) {
	        	       		transport = new SportBoat(bufferTextFromFile.split(":")[2]);
	        	       	}

	        	       	parkingStages.get(counter).setBoat(Integer.parseInt(bufferTextFromFile.split(":")[0]), transport);
	                }

	                bufferTextFromFile = "";
	            } else {
	            	bufferTextFromFile += (char)c;
	            }
	        }

	        return true;
	    }

	    public boolean LoadLevel(String filename) throws IOException, ParkingOccupiedPlaceException
	    {
	        FileReader fr = new FileReader(filename);
	        String bufferTextFromFile = "";
	        int lvl = 0;

	        int c;
	        while ((char)(c = fr.read()) != '\n') {
	        	bufferTextFromFile += (char)c;
	        }

	        if (bufferTextFromFile.contains("Level")) {
	           	lvl = Integer.parseInt(bufferTextFromFile.split(":")[1]);
	           	bufferTextFromFile = "";
	        } else {
	        	return false;
	        }

	        if (parkingStages.size() < lvl && lvl > -1) {
	        	return false;
	        }

	    	parkingStages.set(lvl, new Parking<ITransport, IMotors>(countPlaces, pictureWidth, pictureHeight));

	        while ((c = fr.read()) != -1) {
	        	if ((char)c == '\n') {                
	                ITransport transport = null;

	                if(bufferTextFromFile == null) {
	                	continue;
	                }

	                if (bufferTextFromFile.split(":").length > 2) {
	        	       	if(bufferTextFromFile.split(":")[1].equals("Boat")) {
	        	       		transport = new Boat(bufferTextFromFile.split(":")[2]);
	        	       	} 
	        	       	else if(bufferTextFromFile.split(":")[1].equals("SportBoat")) {
	        	       		transport = new SportBoat(bufferTextFromFile.split(":")[2]);
	        	       	}
	        	       	parkingStages.get(lvl).setBoat(Integer.parseInt(bufferTextFromFile.split(":")[0]), transport);
	                }

	                bufferTextFromFile = "";
	            } else {
	            	bufferTextFromFile += (char)c;
	            }
	        }

	        return true;
	    }
	    
	    public String printShipsConfig() {
	    	for (Parking<ITransport, IMotors> level : parkingStages)
		        {
		            for(ITransport transport : level) {
		            	if (transport.getClass().getName() == "Boat")
	                 {
		            	System.out.println(level.GetKey() + ":Boat:" + transport.ToString());
		            	return level.GetKey() + ":Boat:" + transport.ToString();
	                 }
	                 if (transport.getClass().getName() == "SportBoat")
	                 {
	                 	System.out.println(level.GetKey() + ":SportBoat:" + transport.ToString());
	                 	return level.GetKey() + ":SportBoat:" + transport.ToString();
	                 }
		            }
		        } 
	    	return "";
		}

		public void _sort() {
			Collections.sort(parkingStages);
		}
} 
