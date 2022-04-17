package healthify.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Background {

	private JFrame frame;
	private Set<String> selectedSymptoms;
	private String[] symptomsList;
	private JLabel lblNewLabel;
	private String imagePath = "src/main/resources/images/robot_eye.png";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Background window = new Background();
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
	public Background() {
		selectedSymptoms = new HashSet<String>();
		symptomsList = getSymptomsNames();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMaximizedBounds(new Rectangle(5000000, 5000000, 5000000, 5000000));
		frame.getContentPane().setForeground(Color.DARK_GRAY);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Healthify");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(598, 34, 187, 45);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("A Blockchain based Disease Prediction System");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(457, 91, 623, 35);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(imagePath));
		lblNewLabel_3.setBounds(-10, 130, 423, 525);
		frame.getContentPane().add(lblNewLabel_3);
		
		final JLabel lblNewLabel_4 = new JLabel("Added symptoms are : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(470, 522, 911, 77);
		frame.getContentPane().add(lblNewLabel_4);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(symptomsList));
		comboBox.setBounds(646, 214, 187, 45);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Add ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp= (String) comboBox.getItemAt(comboBox.getSelectedIndex()); 
				selectedSymptoms.add(temp);
				
				lblNewLabel_4.setText("Added Symptoms are : "+selectedSymptoms.toString());			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(646, 284, 187, 45);
		frame.getContentPane().add(btnNewButton); 
		
		
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(646, 601, 187, 45);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_6 = new JLabel("Enter your symptoms :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(470, 208, 187, 53);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel("Enter the prgonosis :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(470, 384, 160, 35);
		frame.getContentPane().add(lblNewLabel_5);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "3", "3", "3", "4", "5", "6", "7", "8", "9"}));
		comboBox_1.setBounds(646, 377, 187, 52);
		frame.getContentPane().add(comboBox_1);
		
		
		final JLabel lblNewLabel_7 = new JLabel("Added prognosis is : ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(934, 214, 447, 47);
		frame.getContentPane().add(lblNewLabel_7);
		frame.setBounds(100, 100, 1405, 747);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton_2 = new JButton("Add ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp= (String) comboBox_1.getItemAt(comboBox_1.getSelectedIndex()); 
				
				lblNewLabel_7.setText("Added Prognosis is : "+temp);	
			}
		}); 
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.setBounds(646, 467, 187, 45);
		frame.getContentPane().add(btnNewButton_2);
		 
		JButton btnNewButton_3 = new JButton("Add CSV File");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CsvPathReader file=new CsvPathReader();
				frame.dispose();
				file.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3.setBounds(952, 459, 262, 53);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Show Added Symptoms");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SymptomsList list=new SymptomsList(symptoms.toString());
				list.setVisible(true);
				
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_4.setBounds(934, 284, 240, 45);
		frame.getContentPane().add(btnNewButton_4); 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	private String[] getSymptomsNames() {
		List<String> tempSymptomsLst = new ArrayList<>();
		for(int symptomID = 1; ;symptomID++) {
			try {
				var symptomName = Symptom.getNameById(symptomID);				
				tempSymptomsLst.add(symptomName);
			} catch(ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
		String[] result = tempSymptomsLst.toArray(new String[tempSymptomsLst.size()]);
		Arrays.sort(result);
		return result;
	}
}
