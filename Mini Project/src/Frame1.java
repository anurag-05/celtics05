import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import javax.swing.BoxLayout;


public class Frame1 {

	JFrame frame;
	JTextField textField2;
	JTextArea textArea;
	JScrollPane scroll;
	JPanel panel_1,panel_2,panel_3,Chart;
	JButton btnBack,btnSearch;
	JTextArea Header;
	JComboBox comboBox;
	JCheckBox chckbxMale,chckbxFemale,chckbxFemale_1,chckbxMale_1;
	JLabel lblEnterName,select_p3;
	JTextField textField;
	JTextField textField_1;
	JLabel label_bottle;
	JPanel panel_4;
	JButton btnBack_1;
	JTextField Header_2;
	JLabel lblOr;
	JButton btnCheckOverallPopularity;

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
		
	
		
		JLabel lblEnterYear = new JLabel("Enter Year :");
		lblEnterYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterYear.setBounds(173, 186, 127, 16);
		panel_1.add(lblEnterYear);
		
		textField2 = new JTextField();
		textField2.setBounds(294, 184, 98, 22);
		panel_1.add(textField2);
		textField2.setColumns(10);
		
		JButton btnFind = new JButton("Search");
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
		lblSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelect.setBounds(198, 111, 56, 50);
		panel_1.add(lblSelect);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(570, 0, 344, 436);
		Image img = new ImageIcon(this.getClass().getResource("/pn.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		panel_1.add(lblNewLabel);
		
		lblOr = new JLabel("Or");
		lblOr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOr.setBounds(336, 401, 56, 16);
		panel_1.add(lblOr);
		
		btnCheckOverallPopularity = new JButton("Check Overall Popularity Of a Name");
		btnCheckOverallPopularity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCheckOverallPopularity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panel_1.setVisible(false);
				panel_3.setVisible(true);
			}
		});
		btnCheckOverallPopularity.setBounds(230, 451, 256, 25);
		panel_1.add(btnCheckOverallPopularity);
		
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
		btnBack.setBounds(50, 532, 97, 25);
		panel_2.add(btnBack);
		
		Header = new JTextArea();
		Header.setFont(new Font("Monospaced", Font.PLAIN, 15));
		Header.setBounds(50, 44, 205, 22);
		panel_2.add(Header);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panel_2.setVisible(false);
				panel_3.setVisible(true);
				
			}
		});
		btnNext.setBounds(771, 532, 97, 25);
		panel_2.add(btnNext);
		
		panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, "name_168620335624468");
		panel_3.setLayout(null);
		
		lblEnterName = new JLabel("Enter  Name :");
		lblEnterName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterName.setBounds(77, 173, 112, 19);
		panel_3.add(lblEnterName);
		
		textField = new JTextField();
		textField.setBounds(182, 172, 116, 22);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JLabel lblYear = new JLabel("Year :");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYear.setBounds(127, 232, 41, 33);
		panel_3.add(lblYear);
		
		textField_1 = new JTextField();
		textField_1.setBounds(182, 238, 116, 22);
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
	    select_p3 = new JLabel("Select :");
		select_p3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		select_p3.setBounds(127, 110, 52, 34);
		panel_3.add(select_p3);
		
		chckbxMale_1 = new JCheckBox("Male");
		chckbxMale_1.setBounds(185, 116, 58, 25);
		panel_3.add(chckbxMale_1);
		
		chckbxFemale_1 = new JCheckBox("Female");
		chckbxFemale_1.setBounds(247, 116, 113, 25);
		panel_3.add(chckbxFemale_1);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String year,name,gender="";
				int flag=0;
			
				year= textField_1.getText();
				name=textField.getText();
				Header_2.setText(name);
				Chart.removeAll();
				
				panel_4.setVisible(true);
				panel_3.setVisible(false);
				
				if(chckbxMale_1.isSelected() && chckbxFemale_1.isSelected())
				{
					Header_2.setText("Select : Either Male or Female");
					flag=1;
					
				}else if(chckbxMale_1.isSelected())
				{
					gender="male";
				}
				else if(chckbxFemale_1.isSelected())
				{
					gender="female";
				}
				
				if(Integer.parseInt(year)<1944 || Integer.parseInt(year) >2013)
				{
					Header_2.setText("Invalid Input");
					flag=1;
				
				}
			
				if(flag==0)
				{
					chart(gender,name,year);
				}
			}
		});
		btnSearch.setBounds(201, 311, 97, 25);
		panel_3.add(btnSearch);
		
		label_bottle = new JLabel("");
		label_bottle.setBounds(392, 0, 510, 589);
		Image bottle = new ImageIcon(this.getClass().getResource("/bottle.png")).getImage();
		label_bottle.setIcon(new ImageIcon(bottle));
		panel_3.add(label_bottle);
		
		panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, "name_170804203605447");
		panel_4.setLayout(null);
		
		btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_3.setVisible(true);
				panel_4.setVisible(false);
			}
		});
		btnBack_1.setBounds(31, 531, 97, 25);
		panel_4.add(btnBack_1);
		
		Header_2 = new JTextField();
		Header_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Header_2.setBackground(Color.WHITE);
		Header_2.setEditable(false);
		Header_2.setBounds(31, 47, 373, 22);
		panel_4.add(Header_2);
		Header_2.setColumns(10);
		
		Chart = new JPanel();
		Chart.setBackground(Color.WHITE);
		Chart.setBounds(31, 86, 831, 430);
		panel_4.add(Chart);
		Chart.setLayout(new BoxLayout(Chart, BoxLayout.X_AXIS));
		
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
	
	public void chart(String choose_gen,String name,String yr1)
	{   int number_births,yr;
		String cls,f_name,gender,nof;
		yr=convert(yr1);
	    gender=choose_gen.toLowerCase();
		name=name.toUpperCase();
        DefaultCategoryDataset data_set = new DefaultCategoryDataset();
	    JFreeChart jchart = ChartFactory.createLineChart("Overall Popularity","Year","Births",data_set);
		CategoryPlot plot = jchart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.black);
		
		for(int i=yr;i<=2013;i++){
			int flag=0;
			f_name=gender + "_cy" + i + "_top.csv";
		
			File file3=new File (f_name);
			try{
				Scanner read3 = new Scanner (file3);
				for(int pp=0;pp<2;pp++)
				
				read3.next();
				
				while(read3.hasNext()){
					String datas = read3.next();
					String []values = datas.split(",");
					if(values[1].equals("\""))
						number_births = convert (values[2]);
					else
						number_births = convert (values[1]);
					
					cls =values[0].replaceAll("\"", "");
			
					if(cls.equals(name)){
						nof=Integer.toString(number_births);
						flag=1;
						data_set.setValue(number_births,"Births",Integer.toString(i));	
						break;
					}
				}			
				read3.close();
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
	
			if(flag==0)
			{
				String xy =Integer.toString(i);
				data_set.setValue(0,"Births",xy);	
			}
		}
		
		ChartFrame chart_frame = new ChartFrame("Student",jchart,true);
	    ChartPanel chart_panel = new ChartPanel(jchart);
		Chart.removeAll();
		Chart.add(chart_panel);
		Chart.updateUI();
	}
}
