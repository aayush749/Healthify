package healthify.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import healthifylib.BlockChain;
import healthifylib.Prognosis;
import healthifylib.Symptom;

public class HealthifyMainGUI {

	private JFrame mainWindowFrame;
	private Set<String> selectedSymptoms;
	private String[] symptomsList;
	private String[] prognosisList;
	private String imagePath = "src/main/resources/images/android_robot.png";
	private String iconPath = "src/main/resources/images/HealthifyIcon.png";
	private Image icon;
	private String prognosisSelected = "";
	
	private HashMap<String, Integer> symptomNameToIDMapping;
	private HashMap<String, Integer> prognosisNameToIDMapping;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HealthifyMainGUI window = new HealthifyMainGUI();
					window.mainWindowFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HealthifyMainGUI() {
		selectedSymptoms = new HashSet<String>();
		symptomNameToIDMapping = new HashMap<String, Integer>();
		prognosisNameToIDMapping = new HashMap<String, Integer>();
		symptomsList = getSymptomsNames();
		prognosisList = getPrognosisNames();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainWindowFrame = new JFrame();
		mainWindowFrame.setMaximizedBounds(new Rectangle(5000000, 5000000, 5000000, 5000000));
		mainWindowFrame.getContentPane().setForeground(Color.DARK_GRAY);
		mainWindowFrame.getContentPane().setBackground(Color.DARK_GRAY);
		mainWindowFrame.getContentPane().setLayout(null);
		mainWindowFrame.setTitle("Healthify");
		try {
			icon = ImageIO.read(new File(iconPath));
			mainWindowFrame.setIconImage(icon);
		} catch (IOException e1) {
			mainWindowFrame.setIconImage(null);
			System.out.println("Unable to load icon image.");
		}
		
		JLabel mainTitleLabel = new JLabel("Healthify");
		mainTitleLabel.setForeground(Color.WHITE);
		mainTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mainTitleLabel.setBounds(598, 34, 187, 45);
		mainWindowFrame.getContentPane().add(mainTitleLabel);
		
		JLabel descriptionTitleLabel = new JLabel("A Blockchain based Medical Record Storage System");
		descriptionTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descriptionTitleLabel.setForeground(Color.WHITE);
		descriptionTitleLabel.setBounds(457, 91, 623, 35);
		mainWindowFrame.getContentPane().add(descriptionTitleLabel);
		
		JLabel leftSideImageHolderLabel = new JLabel("Left_Image");
		leftSideImageHolderLabel.setIcon(new ImageIcon(imagePath));
		leftSideImageHolderLabel.setBounds(-10, 130, 423, 525);
		mainWindowFrame.getContentPane().add(leftSideImageHolderLabel);
		
		final JLabel peekSymptomsLabel = new JLabel("Added symptoms are : ");
		peekSymptomsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		peekSymptomsLabel.setForeground(Color.WHITE);
		peekSymptomsLabel.setBounds(470, 522, 911, 77);
		mainWindowFrame.getContentPane().add(peekSymptomsLabel);
		
		final JComboBox symptomsListComboBox = new JComboBox();
		symptomsListComboBox.setModel(new DefaultComboBoxModel(symptomsList));
		symptomsListComboBox.setBounds(646, 214, 187, 45);
		mainWindowFrame.getContentPane().add(symptomsListComboBox);
		
		JButton addSymptomButton = new JButton("Add ");
		addSymptomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp= (String) symptomsListComboBox.getItemAt(symptomsListComboBox.getSelectedIndex()); 
				selectedSymptoms.add(temp);
				
				peekSymptomsLabel.setText("Added Symptoms are : " + selectedSymptoms.toString());
			}
		});
		addSymptomButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addSymptomButton.setBounds(646, 284, 187, 45);
		mainWindowFrame.getContentPane().add(addSymptomButton); 
		
		
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var healthifyContract = BlockChain.getDeployedContract();
				if(healthifyContract != null) {
					List<BigInteger> selectedSymptomsList = new ArrayList<>();
					for(String symptom : selectedSymptoms) {
						BigInteger id = BigInteger.valueOf(getSymptomID(symptom));
						selectedSymptomsList.add(id);
					}
					BigInteger prognosisID = BigInteger.valueOf(getPrognosisID(prognosisSelected));
					
					try {
						TransactionReceipt receipt = healthifyContract.addNewRecord(selectedSymptomsList, prognosisID).send();
			
						// show info
						JOptionPane.showMessageDialog(mainWindowFrame, String.format("Your info on adding a new record is: %s", receipt.getStatus()));
					} catch (Exception recordAdditionException) {
						JOptionPane.showMessageDialog(
														mainWindowFrame, 
														String.format("Error in adding new record, probably blockchain connectivity is lost: %s", recordAdditionException)
													  );
					}
				} else {
					JOptionPane.showMessageDialog(mainWindowFrame, "Error either in loading smart contract or connecting to blockchain server!");
				}
			}

			
		});
		submitButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		submitButton.setBounds(646, 601, 187, 45);
		mainWindowFrame.getContentPane().add(submitButton);
		
		JLabel enterYourSymptomLabel = new JLabel("Enter your symptoms :");
		enterYourSymptomLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		enterYourSymptomLabel.setForeground(Color.WHITE);
		enterYourSymptomLabel.setBounds(470, 208, 187, 53);
		mainWindowFrame.getContentPane().add(enterYourSymptomLabel);
		
		JLabel enterPrognosisLabel = new JLabel("Select the prgonosis :");
		enterPrognosisLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		enterPrognosisLabel.setForeground(Color.WHITE);
		enterPrognosisLabel.setBounds(470, 384, 160, 35);
		mainWindowFrame.getContentPane().add(enterPrognosisLabel);
		
		final JComboBox  prognosisListComboBox = new JComboBox();
		prognosisListComboBox.setModel(new DefaultComboBoxModel(prognosisList));
		prognosisListComboBox.setBounds(646, 377, 187, 52);
		mainWindowFrame.getContentPane().add(prognosisListComboBox);
		
		
		final JLabel prognosisPeekLabel = new JLabel("Selected prognosis is : ");
		prognosisPeekLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		prognosisPeekLabel.setForeground(Color.WHITE);
		prognosisPeekLabel.setBounds(934, 214, 447, 47);
		mainWindowFrame.getContentPane().add(prognosisPeekLabel);
		mainWindowFrame.setBounds(100, 100, 1405, 747);
		mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton selectPrognosisButton = new JButton("Select");
		selectPrognosisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prognosisSelected = (String) prognosisListComboBox.getItemAt(prognosisListComboBox.getSelectedIndex()); 
				
				prognosisPeekLabel.setText("Added Prognosis is : " + prognosisSelected);	
			}
		}); 
		selectPrognosisButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		selectPrognosisButton.setBounds(646, 467, 187, 45);
		mainWindowFrame.getContentPane().add(selectPrognosisButton);
		 
		JButton bulkUploaderModeSelectButton = new JButton("Insert Records from CSV file");
		bulkUploaderModeSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BulkUploaderGUI file = new BulkUploaderGUI(icon);
				file.setVisible(true);
			}
		});
		bulkUploaderModeSelectButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bulkUploaderModeSelectButton.setBounds(952, 459, 262, 53);
		mainWindowFrame.getContentPane().add(bulkUploaderModeSelectButton);
		
		JButton showAddedSymptomsButton = new JButton("Show Added Symptoms");
		showAddedSymptomsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Selected symptoms are\n" + selectedSymptoms.toString());
			}
		});
		showAddedSymptomsButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		showAddedSymptomsButton.setBounds(934, 284, 240, 45);
		mainWindowFrame.getContentPane().add(showAddedSymptomsButton); 
	}
	
	private String[] getSymptomsNames() {
		List<String> tempSymptomsLst = new ArrayList<String>();
		for(int symptomID = 1; ;symptomID++) {
			try {
				var symptomName = Symptom.getNameById(symptomID);				
				tempSymptomsLst.add(symptomName);
				
				symptomNameToIDMapping.put(symptomName, symptomID);
			} catch(ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
		String[] result = tempSymptomsLst.toArray(new String[tempSymptomsLst.size()]);
		Arrays.sort(result);
		return result;
	}
	
	private String[] getPrognosisNames() {
		List<String> tempPrognosisLst = new ArrayList<String>();
		for(int prognosisID = 1; ;prognosisID++) {
			try {
				var prognosisName = Prognosis.getNameById(prognosisID);				
				tempPrognosisLst.add(prognosisName);
				
				prognosisNameToIDMapping.put(prognosisName, prognosisID);
			} catch(ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
		String[] result = tempPrognosisLst.toArray(new String[tempPrognosisLst.size()]);
		Arrays.sort(result);
		return result;
	}
	
	private int getSymptomID(String symptom) {
		return symptomNameToIDMapping.get(symptom);
	}
	
	private int getPrognosisID(String prognosis) {
		return prognosisNameToIDMapping.get(prognosis);
	}
}