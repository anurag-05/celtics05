import java.awt.EventQueue;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


public class Frame1 {

	private JFrame frame;
	private JTextField textField1;
	private JTextField textField2;
	JTextArea textArea;
	JScrollPane scroll;
	JPanel panel_1,panel_2;
	private JButton btnBack;
	private JTextArea Header;
	JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
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
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		
		frame = new JFrame();
		frame.setBounds(100, 100, 932, 636);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, "name_12195122635849");
		panel_1.setLayout(null);
		
		JLabel lblEnterGen = new JLabel("Enter Male / Female :");
		lblEnterGen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterGen.setBounds(112, 120, 170, 16);
		panel_1.add(lblEnterGen);
		
		textField1 = new JTextField();
		textField1.setBounds(294, 118, 116, 22);
		panel_1.add(textField1);
		textField1.setColumns(10);
		
	
		
		JLabel lblEnterYear = new JLabel("Enter Year :");
		lblEnterYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterYear.setBounds(173, 186, 127, 16);
		panel_1.add(lblEnterYear);
		
		textField2 = new JTextField();
		textField2.setBounds(294, 184, 116, 22);
		panel_1.add(textField2);
		textField2.setColumns(10);
		
		JButton btnFind = new JButton("Search");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String gender,year,f_name,row,name,birth;
				String []array;
				String disp;
				int flag=0,max,i=0;
				
				panel_1.setVisible(false);
				panel_2.setVisible(true);
				
				textArea.setText("");
				Header.setText("");
				
				gender = textField1.getText();
				year = textField2.getText();
				disp=comboBox.getSelectedItem().toString();
				System.out.println(disp);
				
				if(disp.equals("Top 5 Names"))
				max=5;
				else if(disp.equals("Top 10 Names"))
					max=10;
				else if(disp.equals("Top 20 Names"))
					max=20;
				else if(disp.equals("Top 50 Names"))
					max=50;
				else
					max=99999;
					
				
				if(gender.toLowerCase().equals("male") || gender.toLowerCase().equals("female"))
				{
					flag=0;
				}
				else
				{
					Header.append("Invalid Input");
					flag=1;
				}
				
				if(Integer.parseInt(year)>2013 || Integer.parseInt(year)<1944)
				{
					Header.append("Invalid Input");
					flag=1;
				}
				
				if(flag==0)
				{
					f_name = gender.toLowerCase()+"_cy"+year+"_top.csv";
					//textArea.append(f_name);
					
					File file=new File(f_name);
					Header.append("YEAR    " + year);
					
					try
					{
						Scanner read=new Scanner(file);
						
						read.next();
						read.next();
						
						while(read.hasNext() && i<=max)
						{
							row=read.next();
							array=row.split(",");
							
							name=array[0];
							birth=array[1];
							birth=birth.replaceAll("\"","");
					
							textArea.append(name);
							textArea.append("  No of Births ->" );
							textArea.append(birth);
							textArea.append("\n");
							i++;
							
						}
						
						
					}
					catch(FileNotFoundException e)
					{
						e.printStackTrace();
					}
				}
				
				
			}
		});
		btnFind.setBounds(302, 342, 90, 25);
		panel_1.add(btnFind);
		
		JLabel lblDisplay = new JLabel("Display :");
		lblDisplay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDisplay.setBounds(198, 243, 56, 16);
		panel_1.add(lblDisplay);
		
		String[] Display = {"Top 5 Names","Top 10 Names","Top 20 Names","Top 50 Names","All Names"};
		
		
		comboBox = new JComboBox(Display);
		comboBox.setBounds(294, 241, 116, 22);
		panel_1.add(comboBox);
		
		panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, "name_12248119307610");
		panel_2.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));

		scroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(50, 79, 818, 423);
		panel_2.add(scroll);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panel_1.setVisible(true);
				panel_2.setVisible(false);
				
			}
		});
		btnBack.setBounds(771, 533, 97, 25);
		panel_2.add(btnBack);
		
		Header = new JTextArea();
		Header.setFont(new Font("Monospaced", Font.PLAIN, 15));
		Header.setBounds(50, 44, 205, 22);
		panel_2.add(Header);
		
	}
}
