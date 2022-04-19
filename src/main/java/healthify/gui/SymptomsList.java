package healthify.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SymptomsList extends JFrame {

	private JPanel contentPane;
    private final int LETTER_WIDTH=200;
    private final int LETTER_HEIGHT=200;
    private final int LINE_LETTERS_NUM=10;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public SymptomsList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 837, 527);
		contentPane.add(lblNewLabel);
	}
     
	public SymptomsList(String symptoms)
	{
		int window_width = LETTER_WIDTH * LINE_LETTERS_NUM;
		int window_height = LETTER_HEIGHT * (symptoms.length() / LINE_LETTERS_NUM);
		symptoms = stuffNewLine(symptoms);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, window_width, window_height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0,window_width, window_height);
		contentPane.add(lblNewLabel);
		lblNewLabel.setText(symptoms);
	}
	
	private String stuffNewLine(String symptoms) {
		String stuffedSymptoms = "";
		
		for (int i = 0; i < symptoms.length(); i++) {
			if ((i + 1) % LINE_LETTERS_NUM == 0) {
				stuffedSymptoms += "\n";
			}
			stuffedSymptoms += symptoms.charAt(i);
		}
		
		return stuffedSymptoms;
	}
}
