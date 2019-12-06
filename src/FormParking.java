import java.awt.Color;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class FormParking {

	private JFrame frame;
	private JTextField takeTextField;
	PanelParking panel;

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
	public FormParking() {
		initialize();
	}

	class Delegate extends BoatDelegate {
		@Override
		public void induce(ITransport transport) {
			panel.addBoat(transport);
			panel.repaint();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 601);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new PanelParking();
		panel.setBounds(15, 16, 860, 508);
		frame.getContentPane().add(panel);

		JButton btnSetBoat = new JButton("Set Boat");
		btnSetBoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormBoatConfig config = new FormBoatConfig(new Delegate());
			}
		});
		btnSetBoat.setBounds(886, 29, 173, 29);
		frame.getContentPane().add(btnSetBoat);

		JButton btnSetSportBoat = new JButton("Set SportBoat");
		btnSetSportBoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SportBoat sportBoat = new SportBoat((int) (Math.random() * 200) + 100,
						(int) (Math.random() * 1000) + 1000,
						new Color((int) (Math.random() * 256), (int) (Math.random() * 256),
								(int) (Math.random() * 256)),
						new Color((int) (Math.random() * 256), (int) (Math.random() * 256),
								(int) (Math.random() * 256)));
				panel.addBoat(sportBoat);
				panel.repaint();
			}
		});
		btnSetSportBoat.setBounds(886, 74, 173, 29);
		frame.getContentPane().add(btnSetSportBoat);

		JLabel lblTakeBoat = new JLabel("Take Boat");
		lblTakeBoat.setBounds(939, 279, 105, 20);
		frame.getContentPane().add(lblTakeBoat);

		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(903, 309, 69, 20);
		frame.getContentPane().add(lblPosition);

		takeTextField = new JTextField();
		takeTextField.setBounds(1014, 307, 45, 26);
		frame.getContentPane().add(takeTextField);
		takeTextField.setColumns(10);

		PanelTakeBoat takePanel = new PanelTakeBoat();
		takePanel.setBounds(929, 409, 127, 115);
		frame.getContentPane().add(takePanel);

		JButton btnTakeBoat = new JButton("Take Boat");
		btnTakeBoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = takeTextField.getText();
				if (s != "") {
					int index = Integer.parseInt(s);
					transport = panel.getTransport(index);
					takePanel.setTransport(transport);
					takePanel.repaint();
					panel.repaint();
					hashtable.put(k, transport);
					k++;
				}
			}
		});
		btnTakeBoat.setBounds(929, 353, 127, 29);
		frame.getContentPane().add(btnTakeBoat);

		List list = new List();
		for (int i = 0; i < 5; i++) {
			list.add("Level " + i);
		}
		list.setBounds(929, 173, 104, 56);
		frame.getContentPane().add(list);
		list.setBackground(Color.CYAN);
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setLevel(list.getSelectedIndex());
				panel.repaint();
			}
		});
	}
}
