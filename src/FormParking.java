import java.awt.Color;
import java.awt.EventQueue;
import java.awt.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Hashtable;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class FormParking {

	private JFrame frame;
	private JTextField takeTextField;
	PanelParking panel;
	private Logger logger;
	private Logger logger_error;

	private ITransport transport;
	Hashtable<Integer, ITransport> hashtable = new Hashtable<Integer, ITransport>();
	int k = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormParking window = new FormParking();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormParking() throws ParkingNotFoundException, ParkingOverflowException, 
							SecurityException, IOException, ParkingOccupiedPlaceException {
		initialize();
	}

	class Delegate extends BoatDelegate {
		@Override
		public void induce(ITransport transport) throws ParkingOverflowException, ParkingAlreadyHaveException {
			panel.addBoat(transport);
			panel.repaint();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SecurityException, ParkingNotFoundException, ParkingOverflowException, 
	SecurityException, IOException, ParkingOccupiedPlaceException {
		logger = Logger.getLogger("MyLog");
		logger_error = Logger.getLogger("MyDopLog");
		try {
			FileHandler fh = null;
			FileHandler fh_e = null;
			fh = new FileHandler("C:\\Users\\krist\\Desktop\\ÒÏ\\file_info.txt");
			fh_e = new FileHandler("C:\\Users\\krist\\Desktop\\ÒÏ\\file_error.txt");
			logger.addHandler(fh);
			logger_error.addHandler(fh_e);
			logger.setUseParentHandlers(false);
			logger_error.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			fh_e.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 1154, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new PanelParking();
		panel.setBounds(15, 66, 878, 506);
		frame.getContentPane().add(panel);

		JButton btnSetBoat = new JButton("Set Boat");
		btnSetBoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				FormBoatConfig config = new FormBoatConfig(new Delegate());
				logger.info("Äîáàâèëè êàòåð");
				}
				catch (ParkingOverflowException ex) {
					logger_error.warning("Ïåðåïîëíåíèå");
					JOptionPane.showMessageDialog(null, "Ïåðåïîëíåíèå");
				}
			}
		});
		btnSetBoat.setBounds(970, 26, 126, 29);
		frame.getContentPane().add(btnSetBoat);

		JButton btnSetSportBoat = new JButton("Set SportBoat");
		btnSetSportBoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					SportBoat sportBoat = new SportBoat(100, 1000,
							new Color((int) (Math.random() * 256), (int) (Math.random() * 256),
									(int) (Math.random() * 256)),
							new Color((int) (Math.random() * 256), (int) (Math.random() * 256),
									(int) (Math.random() * 256)));
					panel.addBoat(sportBoat);
					panel.repaint();
					logger.info("Äîáàâèëè êàòåð");
				}
				catch (ParkingOverflowException ex) {
					logger_error.warning("Ïåðåïîëíåíèå");
					JOptionPane.showMessageDialog(null, "Ïåðåïîëíåíèå");
				}
				catch(ParkingAlreadyHaveException ex) {
					logger_error.warning("Íà ïàðêîâêå óæå åñòü òàêîé êàòåð");
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnSetSportBoat.setBounds(970, 66, 126, 29);
		frame.getContentPane().add(btnSetSportBoat);

		JLabel lblTakeBoat = new JLabel("Take Boat");
		lblTakeBoat.setBounds(970, 298, 126, 20);
		frame.getContentPane().add(lblTakeBoat);

		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(953, 334, 81, 20);
		frame.getContentPane().add(lblPosition);

		takeTextField = new JTextField();
		takeTextField.setBounds(1037, 331, 59, 26);
		frame.getContentPane().add(takeTextField);
		takeTextField.setColumns(10);

		PanelTakeBoat takePanel = new PanelTakeBoat();
		takePanel.setBounds(953, 415, 143, 115);
		frame.getContentPane().add(takePanel);

		JButton btnTakeBoat = new JButton("Take Boat");
		btnTakeBoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = takeTextField.getText();
				try {
					int index = Integer.parseInt(s);
					transport = panel.getTransport(index);
					takePanel.setTransport(transport);
					takePanel.repaint();
					panel.repaint();
					hashtable.put(k, transport);
					k++;
					logger.info("Âçÿëè êàòåð ñ ìåñòà " + s);
				}
				catch (ParkingNotFoundException ex) {
					logger_error.warning("Êàòåð íå íàéäåí");
					JOptionPane.showMessageDialog(null, "Êàòåð íå íàéäåí",
							"Exception", 0, null);
				} catch (Exception ex) {
					logger_error.warning("Îøèáêà");
					JOptionPane.showMessageDialog(frame, "Îøèáêà",
							"Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnTakeBoat.setBounds(970, 370, 126, 29);
		frame.getContentPane().add(btnTakeBoat);

		List list = new List();
		for (int i = 0; i < 5; i++) {
			list.add("Level " + i);
		}
		list.setBounds(959, 138, 154, 115);
		frame.getContentPane().add(list);
		list.setBackground(Color.CYAN);
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setLevel(list.getSelectedIndex());
				panel.repaint();
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(15, 16, 81, 31);
		frame.getContentPane().add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmFileSave = new JMenuItem("Save");
		mntmFileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser filechooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
					filechooser.setFileFilter(filter);
					int ret = filechooser.showDialog(null, "Save");                
					if (ret == JFileChooser.APPROVE_OPTION) {
						File file = filechooser.getSelectedFile();
					    panel.SaveData(file.getAbsolutePath() + ".txt");
						JOptionPane.showMessageDialog(null, "Ñîõðàíåíî");
					    logger.info("Ñîõðàíèëè âñå");
					}
				}
				 catch (Exception e1) {						
					    logger_error.warning("Îøèáêà ïðè ñîõðàíåíèè");
						JOptionPane.showMessageDialog(null, "Îøèáêà ïðè ñîõðàíåíèè");
				 }
			}
		});
		mnFile.add(mntmFileSave);

		JMenuItem mntmFileLoad = new JMenuItem("Load");
		mntmFileLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser filechooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
					filechooser.setFileFilter(filter);
					int ret = filechooser.showDialog(null, "Load");                
					if (ret == JFileChooser.APPROVE_OPTION) {
					    File file = filechooser.getSelectedFile();
					    panel.LoadData(file.getAbsolutePath());
					    panel.repaint();
					    JOptionPane.showMessageDialog(null, "Çàãðóæåíî");
					    logger.info("Çàãðóçèëè âñå");
					}
				}
				catch (ParkingOccupiedPlaceException ex) {
					logger_error.warning("Ìåñòî çàíÿòî " + ex.getMessage());
					JOptionPane.showMessageDialog(frame, ex.getMessage(),
							"Exception", JOptionPane.ERROR_MESSAGE);
				} 
				catch (Exception ex) {
					logger_error.warning("Îøèáêà");
					JOptionPane.showMessageDialog(frame, "Îøèáêà", "Exception", JOptionPane.ERROR_MESSAGE);
				 }				
			}
		});
		mnFile.add(mntmFileLoad);

		JMenu mnLevel = new JMenu("Level");
		menuBar.add(mnLevel);

		JMenuItem mntmLevelSave = new JMenuItem("Save");
		mntmLevelSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser filechooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("dop", "dop");
					filechooser.setFileFilter(filter);
					int ret = filechooser.showDialog(null, "Save");                
					if (ret == JFileChooser.APPROVE_OPTION) {
					    File file = filechooser.getSelectedFile();
					    panel.SaveCurrentLevel(file.getAbsolutePath() + ".dop");
					    panel.repaint();
					    JOptionPane.showMessageDialog(null, "Ñîõðàíåíî");
					    logger.info("Ñîõðàíèëè");
					}
				}
				catch (Exception ex) {
					logger_error.warning("Îøèáêà ïðè ñîõðàíåíèè óðîâíÿ");
					JOptionPane.showMessageDialog(frame, "Îøèáêà ïðè ñîõðàíåíèè óðîâíÿ",
							"Exception", JOptionPane.ERROR_MESSAGE);
				 }
			}
		});
		mnLevel.add(mntmLevelSave);

		JMenuItem mntmLevelLoad = new JMenuItem("Load");
		mntmLevelLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {							
				try {
					JFileChooser filechooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("dop", "dop");
					filechooser.setFileFilter(filter);
					int ret = filechooser.showDialog(null, "Load");                
					if (ret == JFileChooser.APPROVE_OPTION) {
					    File file = filechooser.getSelectedFile();
					    panel.LoadCurrentLevel(file.getAbsolutePath());
					    panel.repaint();
					    JOptionPane.showMessageDialog(null, "Çàãðóæåíî");
					    logger.info("Çàãðóçèëè");
					}
				}
				catch (IOException ex) {
					logger_error.warning("Îøèáêà çàãðóçêè óðîâíÿ" + ex.getMessage());
					JOptionPane.showMessageDialog(frame, "Ìåñòî çàíÿòî" + ex.getMessage(),
								"Exception", JOptionPane.ERROR_MESSAGE);
				} catch (ParkingOccupiedPlaceException ex) {
					logger_error.warning("Ìåñòî çàíÿòî" + ex.getMessage());
					JOptionPane.showMessageDialog(frame, ex.getMessage(),"Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnLevel.add(mntmLevelLoad);
				
		JButton btnSort = new JButton("Ñîðòèðîâêà");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel._sort();
				panel.repaint();
				logger.info("Ñîðòèðîâêà óðîâíåé");
			}
		});
		btnSort.setBounds(970, 106, 126, 29);
		frame.getContentPane().add(btnSort);
		
		JButton buttonGetConfigs = new JButton("Ñâîéñòâà");
		buttonGetConfigs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.printShipsConfig();
			}
		});
		buttonGetConfigs.setBounds(970, 265, 126, 29);
		frame.getContentPane().add(buttonGetConfigs);
		

	}
}
