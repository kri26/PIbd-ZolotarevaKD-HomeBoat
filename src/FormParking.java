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
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

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
		frame.setBounds(100, 100, 1154, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new PanelParking();
		panel.setBounds(15, 66, 878, 506);
		frame.getContentPane().add(panel);

		JButton btnSetBoat = new JButton("Set Boat");
		btnSetBoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormBoatConfig config = new FormBoatConfig(new Delegate());
			}
		});
		btnSetBoat.setBounds(970, 26, 126, 29);
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
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
				filechooser.setFileFilter(filter);
				int ret = filechooser.showDialog(null, "Save");                
				if (ret == JFileChooser.APPROVE_OPTION) {
				    File file = filechooser.getSelectedFile();
				    panel.SaveData(file.getAbsolutePath() + ".txt");
				}
			}
		});
		mnFile.add(mntmFileSave);

		JMenuItem mntmFileLoad = new JMenuItem("Load");
		mntmFileLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
				filechooser.setFileFilter(filter);
				int ret = filechooser.showDialog(null, "Load");                
				if (ret == JFileChooser.APPROVE_OPTION) {
				    File file = filechooser.getSelectedFile();
				    panel.LoadData(file.getAbsolutePath());
				    panel.repaint();
				}
			}
		});
		mnFile.add(mntmFileLoad);

		JMenu mnLevel = new JMenu("Level");
		menuBar.add(mnLevel);

		JMenuItem mntmLevelSave = new JMenuItem("Save");
		mntmLevelSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("dop", "dop");
				filechooser.setFileFilter(filter);
				int ret = filechooser.showDialog(null, "Save");                
				if (ret == JFileChooser.APPROVE_OPTION) {
				    File file = filechooser.getSelectedFile();
				    panel.SaveCurrentLevel(file.getAbsolutePath() + ".dop");
				    panel.repaint();
				}
			}
		});
		mnLevel.add(mntmLevelSave);

		JMenuItem mntmLevelLoad = new JMenuItem("Load");
		mntmLevelLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("dop", "dop");
				filechooser.setFileFilter(filter);
				int ret = filechooser.showDialog(null, "Load");                
				if (ret == JFileChooser.APPROVE_OPTION) {
				    File file = filechooser.getSelectedFile();
				    panel.LoadCurrentLevel(file.getAbsolutePath());
				    panel.repaint();
				}
			}
		});
		mnLevel.add(mntmLevelLoad);
	}
}
