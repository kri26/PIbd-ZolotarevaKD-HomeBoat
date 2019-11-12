import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class ParkingForm {

	private JFrame frame;
	private JTextField takeTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParkingForm window = new ParkingForm();
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
	public ParkingForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 601);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		PanelParking panel = new PanelParking();
		panel.setBounds(15, 16, 860, 508);
		frame.getContentPane().add(panel);
		
		JButton btnSetBoat = new JButton("Set Boat");
		btnSetBoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boat boat = new Boat((int)(Math.random() * 200) + 100, 
											  (int)(Math.random() * 1000) + 1000, 
											  new Color((int)(Math.random() * 256), 
													    (int)(Math.random() * 256), 
													    (int)(Math.random() * 256)));
				int position = panel.addBoat(boat);
				panel.repaint();
			}
		});
		btnSetBoat.setBounds(886, 29, 173, 29);
		frame.getContentPane().add(btnSetBoat);
		
		JButton btnSetSportBoat = new JButton("Set SportBoat");
		btnSetSportBoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SportBoat sportBoat = new SportBoat((int)(Math.random() * 200) + 100, 
						  												 (int)(Math.random() * 1000) + 1000, 
						  												 new Color((int)(Math.random() * 256), 
						  														   (int)(Math.random() * 256), 
						  														   (int)(Math.random() * 256)),
						  												 new Color((int)(Math.random() * 256), 
						  														   (int)(Math.random() * 256), 
						  														   (int)(Math.random() * 256)));
				int position = panel.addBoat(sportBoat);
				panel.repaint();
			}
		});
		btnSetSportBoat.setBounds(886, 74, 173, 29);
		frame.getContentPane().add(btnSetSportBoat);
		
		JLabel lblTakeBoat = new JLabel("Take Boat");
		lblTakeBoat.setBounds(929, 209, 105, 20);
		frame.getContentPane().add(lblTakeBoat);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(912, 245, 69, 20);
		frame.getContentPane().add(lblPosition);
		
		takeTextField = new JTextField();
		takeTextField.setBounds(996, 242, 45, 26);
		frame.getContentPane().add(takeTextField);
		takeTextField.setColumns(10);
		
		PanelTakeBoat takePanel = new PanelTakeBoat();
		takePanel.setBounds(912, 326, 127, 115);
		frame.getContentPane().add(takePanel);
		
		JButton btnTakeBoat = new JButton("Take Boat");
		btnTakeBoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = Integer.parseInt(takeTextField.getText());
				ITransport transport = 	panel.delBoat(index);
				takePanel.setTransport(transport);
				panel.repaint();
				takePanel.repaint();
			}
		});
		btnTakeBoat.setBounds(929, 281, 127, 29);
		frame.getContentPane().add(btnTakeBoat);
	}
}