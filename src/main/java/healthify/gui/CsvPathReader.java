package healthify.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CsvPathReader extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CsvPathReader(Image icon) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 875, 595);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setTitle("Bulk Uploader");
		this.setIconImage(icon);
		
		
		final JLabel addedFilePathLabel = new JLabel("Added File path is :");
		addedFilePathLabel.setForeground(Color.WHITE);
		addedFilePathLabel.setBackground(Color.DARK_GRAY);
		addedFilePathLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addedFilePathLabel.setBounds(63, 372, 755, 116);
		contentPane.add(addedFilePathLabel);
		
		JButton addFilePathButton = new JButton("Add File Path");
		addFilePathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser= new JFileChooser();
				fileChooser.showOpenDialog(null);//select file to open
				File path=new File(fileChooser.getSelectedFile().getAbsolutePath());
				addedFilePathLabel.setText("File path is : "+path.toString());
			}
		});
		addFilePathButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addFilePathButton.setBounds(520, 194, 222, 51);
		contentPane.add(addFilePathButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("src/main/resources/images/robot_hand.png"));
		lblNewLabel_1.setBounds(64, 58, 271, 326);
		contentPane.add(lblNewLabel_1);
		
		JButton bulkUploadButton = new JButton("Bulk Upload Data");
		bulkUploadButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bulkUploadButton.setBounds(520, 287, 222, 51);
		contentPane.add(bulkUploadButton);
		
		
	}
}
