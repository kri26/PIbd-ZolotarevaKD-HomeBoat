import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class FormBoatConfig {

	private JFrame frame;


	private ITransport transport = null;
	private IMotors iMotors = null;
	private Color color = null;
	BoatDelegate AddBoat;
	
	private Logger logger_error;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormBoatConfig window = new FormBoatConfig(null);
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
	public FormBoatConfig(BoatDelegate delegate) {
		AddBoat = delegate;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		logger_error = Logger.getLogger("MyLog2");
		try {
			FileHandler fh_e = null;
			fh_e = new FileHandler("C:\\Users\\krist\\Desktop\\file_error.txt");
			logger_error.addHandler(fh_e);
			logger_error.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fh_e.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 645, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setVisible(true);

		PanelBoat panel = new PanelBoat();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (transport != null) {
					panel.setBoat(transport);
					panel.repaint();
				}
				if (iMotors != null) {
					if (panel.getTransportBoat() != null) {
						((SportBoat) panel.getTransportBoat()).setMotors(iMotors);
						panel.repaint();
					}
				}
			}
		});
		panel.setBackground(Color.WHITE);
		panel.setBounds(195, 42, 115, 94);
		frame.getContentPane().add(panel);

		JLabel lblBoat = new JLabel("Boat");
		lblBoat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				transport = new Boat((int)(Math.random() * 200) + 100, 
						  (int)(Math.random() * 1000) + 1000, 
						  new Color((int)(Math.random() * 256), 
								    (int)(Math.random() * 256), 
								    (int)(Math.random() * 256)));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				transport = null;
			}
		});
		lblBoat.setBounds(15, 16, 69, 20);
		frame.getContentPane().add(lblBoat);

		JLabel lblSportBoat = new JLabel("SportBoat");
		lblSportBoat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				transport = new SportBoat((int)(Math.random() * 200) + 100, 
							 (int)(Math.random() * 1000) + 1000, 
							 new Color((int)(Math.random() * 256), 
									   (int)(Math.random() * 256), 
									   (int)(Math.random() * 256)),
							 new Color((int)(Math.random() * 256), 
									   (int)(Math.random() * 256), 
									   (int)(Math.random() * 256)));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				transport = null;
			}
		});
		lblSportBoat.setBounds(15, 45, 132, 20);
		frame.getContentPane().add(lblSportBoat);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(15, 251, 115, 29);
		frame.getContentPane().add(btnCancel);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				AddBoat.induce(panel.getTransportBoat());
				}
				catch (ParkingOverflowException e1)
				{
					logger_error.warning("Переполнение");
					JOptionPane.showMessageDialog(frame, e1.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
					return;
				}
				frame.dispose();
			}
		});
		btnOk.setBounds(15, 206, 115, 29);
		frame.getContentPane().add(btnOk);

		JLabel lblMotors = new JLabel("Motors");
		lblMotors.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				iMotors = new DrawMotor();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				iMotors = null;
			}
		});
		lblMotors.setBounds(15, 90, 132, 20);
		frame.getContentPane().add(lblMotors);

		JLabel lblMotors2Poit0 = new JLabel("Motors2Poit0");
		lblMotors2Poit0.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				iMotors = new DrawMotors2Poit0();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				iMotors = null;
			}
		});
		lblMotors2Poit0.setBounds(15, 116, 165, 20);
		frame.getContentPane().add(lblMotors2Poit0);

		JLabel lblMotorsVersionThree = new JLabel("MotorsVersionThree");
		lblMotorsVersionThree.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				iMotors = new DrawMotorsVersionThree();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				iMotors = null;
			}
		});
		lblMotorsVersionThree.setBounds(15, 141, 165, 20);
		frame.getContentPane().add(lblMotorsVersionThree);

		JLabel lblMainColor = new JLabel("Main Color");
		lblMainColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (color != null && panel.getTransportBoat() != null) {
					((Boat) panel.getTransportBoat()).setColor(color);
					color = null;
					panel.repaint();
				}
			}
		});
		lblMainColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainColor.setBounds(195, 170, 115, 20);
		frame.getContentPane().add(lblMainColor);

		JLabel lblDopColor = new JLabel("Dop Color");
		lblDopColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (color != null && panel.getTransportBoat() != null
						&& panel.getTransportBoat() instanceof SportBoat) {
					((SportBoat) panel.getTransportBoat()).setDopColor(color);
					color = null;
					panel.repaint();
				}
			}
		});
		lblDopColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDopColor.setBounds(195, 210, 115, 20);
		frame.getContentPane().add(lblDopColor);

		JPanel panelWhite = new JPanel();
		panelWhite.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.white;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelWhite.setBackground(Color.WHITE);
		panelWhite.setBounds(429, 42, 40, 40);
		frame.getContentPane().add(panelWhite);

		JPanel panelBlack = new JPanel();
		panelBlack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.black;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelBlack.setBackground(Color.BLACK);
		panelBlack.setBounds(379, 42, 40, 40);
		frame.getContentPane().add(panelBlack);

		JPanel panelRed = new JPanel();
		panelRed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.red;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelRed.setBackground(Color.RED);
		panelRed.setBounds(379, 141, 40, 40);
		frame.getContentPane().add(panelRed);

		JPanel panelGray = new JPanel();
		panelGray.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.gray;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelGray.setBackground(Color.GRAY);
		panelGray.setBounds(379, 190, 40, 40);
		frame.getContentPane().add(panelGray);

		JPanel panelBlue = new JPanel();
		panelBlue.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.blue;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelBlue.setBackground(Color.BLUE);
		panelBlue.setBounds(429, 90, 40, 40);
		frame.getContentPane().add(panelBlue);

		JPanel panelYellow = new JPanel();
		panelYellow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.yellow;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelYellow.setBackground(Color.YELLOW);
		panelYellow.setBounds(429, 141, 40, 40);
		frame.getContentPane().add(panelYellow);

		JPanel panelOrange = new JPanel();
		panelOrange.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.orange;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelOrange.setBackground(Color.ORANGE);
		panelOrange.setBounds(429, 190, 40, 40);
		frame.getContentPane().add(panelOrange);

		JPanel panelGreen = new JPanel();
		panelGreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.green;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelGreen.setBackground(Color.GREEN);
		panelGreen.setBounds(379, 90, 40, 40);
		frame.getContentPane().add(panelGreen);
	}

}
