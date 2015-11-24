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
import javax.swing.JCheckBox;
import javax.swing.JSlider;


public class Frame5 {

	JFrame frame;
	JTextField textField2;
	JTextArea textArea;
	JScrollPane scroll;
	JPanel panel_1,panel_2;
	JButton btnBack;
	JTextArea Header;
	JComboBox comboBox;
	JCheckBox chckbxMale,chckbxFemale;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame5 window = new Frame5();
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
	public Frame5() {
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
		
	
		
		JLabel lblEnterYear = new JLabel("Enter Year :");
		lblEnterYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterYear.setBounds(173, 186, 127, 16);
		panel_1.add(lblEnterYear);
		
		textField2 = new JTextField();
		textField2.setBounds(294, 184, 98, 22);
		panel_1.add(textField2);
		textField2.setColumns(10);
		
		JButton btnFind = new JButton("Search");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String gender="",year="",f_name,row,name,birth;
				String []array;
				String disp;
				int flag_y=0,max=0,i=0,flag;
				
				panel_1.setVisible(false);
				panel_2.setVisible(true);
				
				textArea.setText("");
				Header.setText("");
				
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
				
				if(chckbxMale.isSelected() && chckbxFemale.isSelected())
				{
					String f1,f2;
					f1="male_cy"+ year +"_top.csv";
					f2="female_cy"+ year +"_top.csv";
					System.out.println(f1);
					System.out.println(f2);
					both(f1,f2,year,max);
					flag_y=1;
					
				}else if(chckbxMale.isSelected())
				{
					gender="male";
				}
				else if(chckbxFemale.isSelected())
				{
					gender="female";
				}
				
				if(Integer.parseInt(year)>2013 || Integer.parseInt(year)<1944)
				{
					Header.append("Invalid Input");
					flag_y=1;
				}
				
				if(flag_y==0)
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
							textArea.append("\n  No of Births ->" );
							textArea.append(birth + "\t" +"(" + gender + ")");
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
		lblDisplay.setBounds(198, 258, 56, 16);
		panel_1.add(lblDisplay);
		
		String[] Display = {"Top 5 Names","Top 10 Names","Top 20 Names","Top 50 Names","All Names"};
		
		
		comboBox = new JComboBox(Display);
		comboBox.setBounds(294, 256, 116, 22);
		panel_1.add(comboBox);
		
		chckbxMale = new JCheckBox("Male");
		chckbxMale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxMale.setBounds(294, 111, 78, 50);
		panel_1.add(chckbxMale);
		
		chckbxFemale = new JCheckBox("Female");
		chckbxFemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxFemale.setBounds(388, 111, 98, 50);
		panel_1.add(chckbxFemale);
		
		JLabel lblSelect = new JLabel("Select :\r\n");
		lblSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelect.setBounds(198, 111, 56, 50);
		panel_1.add(lblSelect);
		
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
	public void both (String f_name1 , String f_name2 , String year , int display )
	{
		int male_births=0 ,female_births=0,i=1,flag=0 ;
		String []male_data={""},female_data={""};
		String male_row,female_row;
		
		File file1 = new File (f_name1);
		
		File file2 = new File (f_name2);
		
		try
		{
			Scanner read1 = new Scanner (file1);
			Scanner read2 = new Scanner (file2);
			
			for(int z=0;z<2;z++)
			read1.next(); 
			
			
			for(int z=0;z<2;z++)
			read2.next();
			
			Header.append("YEAR    " + year);
			
			do
			{
				if(flag!=2)
				{
					male_row = read1.next();
					male_data = male_row.split(",");
					male_births = convert (male_data[1]);
				}
				if(flag!=1)
				{
					female_row = read2.next();
					female_data = female_row.split(",");
					female_births = convert (female_data[1]);
				}
			
			
				if(male_births >= female_births)
				{
					textArea.append(male_data[0]);
					textArea.append("\n  No of Births -> " );
					textArea.append(male_births + "\t" + "(male)\n" );
					flag=1;
				}
				else
				{
					textArea.append(female_data[0]);
					textArea.append("\n  No. of Births -> " );
					textArea.append(female_births + "\t" + "(female)\n");
					flag=2;
				}
			
			i++;
			
			}while(i<=display && read1.hasNext() && read2.hasNext());
			
			if(i<=display)
			{
				textArea.append(female_data[0]);
				textArea.append("\n  No. of Births -> " );
				textArea.append(female_births + "\t" + "(female)\n");
				i++;
			}
			
			if(i<=display)
			{
				do
				{
					female_row = read2.next();
					female_data = female_row.split(",");
					female_births = convert (female_data[1]);
				
					textArea.append(female_data[0]);
					textArea.append("\n  No. of Births -> " );
					textArea.append(female_births + "\t" + "(female)\n");
				
					i++;
				
				}while(read2.hasNext() && i<=display);
			}
			
			
			read1.close();
			read2.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

	}
	public static int convert (String births)
	{
		births=births.replaceAll("[^0-9]", "");
		int new_v = Integer.parseInt(births);
		return (new_v);	
	}
}
