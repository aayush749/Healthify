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
	private Set<String> symptoms;
	private JLabel lblNewLabel;

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
		symptoms=new HashSet<String>();
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
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Jarvis\\Downloads\\OIP-(2)-transformed.png"));
		lblNewLabel_3.setBounds(0, 101, 412, 509);
		frame.getContentPane().add(lblNewLabel_3);
		
		final JLabel lblNewLabel_4 = new JLabel("Added symptoms are : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(470, 522, 911, 77);
		frame.getContentPane().add(lblNewLabel_4);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "abdominal_pain", "abnormal_menstruation", "acidity", "acute_liver_failure", "altered_sensorium ", "anxiety ", "back_pain\t", "belly_pain\t", "blackheads ", "bladder_discomfort ", "blister\t", "blood_in_sputum\t", "bloody_stool\t", "blurred_and_distorted_vision", "breathlessness\t", "brittle_nails\t", "bruising", "burning_micturition", "chest_pain\tchills", "cold_hands_and_feets\tcoma", "congestion", "constipation", "continuous_feel_of_urine\t", "continuous_sneezing\t", "cough\tcramps", "dark_urine\tdehydration", "depression", "diarrhoea", "dischromic _patches", "distention_of_abdomen", "dizziness", "drying_and_tingling_lips", "enlarged_thyroid", "excessive_hunger", "extra_marital_contacts", "family_history\tfast_heart_rate", "fatigue", "fluid_overload\t", "foul_smell_of urine", "headache", "high_fever", "hip_joint_pain\t", "history_of_alcohol_consumption\t", "increased_appetite", "indigestion", "inflammatory_nails", "internal_itching", "irregular_sugar_level", "irritability", "irritation_in_anus", "itching\tjoint_pain", "knee_pain\tlack_of_concentration", "lethargy", "loss_of_appetite", "loss_of_balance", "loss_of_smell", "malaise", "mild_fever", "mood_swings", "movement_stiffness", "mucoid_sputum", "muscle_pain", "muscle_wasting", "muscle_weakness", "nausea\tneck_pain", "nodal_skin_eruptions", "obesity", "pain_behind_the_eyes", "pain_during_bowel_movements", "pain_in_anal_region", "painful_walking", "palpitations", "passage_of_gases", "patches_in_throat\tphlegm", "polyuria", "prominent_veins_on_calf", "puffy_face_and_eyes", "pus_filled_pimples", "receiving_blood_transfusion", "receiving_unsterile_injections", "red_sore_around_nose", "red_spots_over_body", "redness_of_eyes", "restlessness", "runny_nose", "rusty_sputum", "scurring", "shivering", "silver_like_dusting", "sinus_pressure", "skin_peeling", "skin_rash", "slurred_speech", "small_dents_in_nails", "spinning_movements", "spotting_ urination", "stiff_neck", "stomach_bleeding", "stomach_pain", "sunken_eyes", "sweating", "swelled_lymph_nodes", "swelling_joints", "swelling_of_stomach", "swollen_blood_vessels", "swollen_extremeties", "swollen_legs", "throat_irritation", "toxic_look_(typhos)", "ulcers_on_tongue", "unsteadiness", "visual_disturbances", "vomiting", "watering_from_eyes", "weakness_in_limbs", "weakness_of_one_body_side", "weight_gain", "weight_loss", "yellow_crust_ooze\t", "yellow_urine", "yellowing_of_eyes", "yellowish_skin"}));
		comboBox.setBounds(646, 214, 187, 45);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Add ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp= (String) comboBox.getItemAt(comboBox.getSelectedIndex()); 
				symptoms.add(temp); 
				
				lblNewLabel_4.setText("Added Symptoms are : "+symptoms.toString());			}
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
}
