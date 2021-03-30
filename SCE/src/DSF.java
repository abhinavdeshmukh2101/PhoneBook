import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

import  AVL.*;


public class DSF extends JFrame {
	private JPanel contentPane;
	 static avl tree = new avl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DSF frame = new DSF();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DSF() {
		
		initComponent();
	}

	private void initComponent() {
		
		JFrame jf = new JFrame(); 
		Object[][] data= new Object[50][3] ;
		String [] column = {"No.","Name","Phone Number"};
		data[0][0]=1;
		data[0][1]="NO Record";
		data[0][2]="00000";
		DefaultTableModel Model = new DefaultTableModel(tree.getdata_call(data),column);
		JTable t1 = new JTable(Model);
		//JTable t1 = new Jtable(data,column);
		
		setForeground(new Color(0, 0, 153));
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("DSE SCE_PhoneBook");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\rohit\\eclipse-workspace\\SCE\\src\\Image_source\\baseline_add_ic_call_black_18dp.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 0, 102)));
		contentPane.setBackground(new Color(139, 0, 139));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phonebook ");
		lblNewLabel.setBackground(new Color(0, 204, 51));
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel.setBounds(140, 10, 168, 58);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton =  new JButton("");
		btnNewButton.setSelectedIcon(new ImageIcon("C:\\Users\\rohit\\Desktop\\icon collection\\add_to_photos-black-18dp\\2x\\baseline_add_to_photos_black_18dp.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String str=JOptionPane.showInputDialog("Enter Your Name");
				 if(str!=null) {
					 long num = Integer.parseInt(JOptionPane.showInputDialog("Enter the phone number"));
			         tree.insert_call(num, str);
				 }
				 Model.setDataVector(tree.getdata_call(data), column);
				 Model.fireTableDataChanged();
				// tree.inOrder(tree.getRoot());
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\rohit\\eclipse-workspace\\SCE\\src\\Image_source\\baseline_add_to_photos_black_18dp.png"));
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setForeground(SystemColor.desktop);
		btnNewButton.setBounds(25, 96, 85, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0 ;
				Object[][] data2 =new Object[50][3];
				String str=JOptionPane.showInputDialog("Enter Name To delete");
				if(str!=null)
				flag= tree.delete_call(str);
				if(flag==0) {
					JOptionPane.showMessageDialog(jf,"No Record Found!! With name "+str);
				}
				else
					 JOptionPane.showMessageDialog(jf,"Record Deleted!!!");
					 Model.setDataVector(tree.getdata_call(data2), column);
					 Model.fireTableDataChanged();
			}
			
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\rohit\\eclipse-workspace\\SCE\\src\\Image_source\\baseline_person_remove_black_18dp.png"));
		btnNewButton_1.setSelectedIcon(new ImageIcon("C:\\Users\\rohit\\Desktop\\icon collection\\person_remove-black-18dp\\2x\\baseline_person_remove_black_18dp.png"));
		btnNewButton_1.setBackground(new Color(255, 153, 0));
		btnNewButton_1.setForeground(SystemColor.desktop);
		btnNewButton_1.setBounds(150, 96, 85, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str=JOptionPane.showInputDialog("Enter Name To Search");
				if(str!=null)
				{long n= tree.search_call(str);
				JFrame jf = new JFrame();
				if(n!=-1) { JOptionPane.showMessageDialog(jf,"Name="+str+"\nPhone="+n);}
				else JOptionPane.showMessageDialog(jf,"No Record Found!! With name "+str);
				
				}}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\rohit\\eclipse-workspace\\SCE\\src\\Image_source\\baseline_search_black_18dp.png"));
		btnNewButton_2.setForeground(SystemColor.desktop);
		btnNewButton_2.setBounds(288, 96, 85, 39);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Display ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				JScrollPane p = new JScrollPane(t1);
				jf.getContentPane().add(p);
				jf.setForeground(new Color(0, 0, 153));
				jf.setFont(new Font("Dialog", Font.BOLD, 12));
				jf.setTitle("DSE SCE_PhoneBook");
				jf.setBounds(900, 300, 450, 300);
				jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				jf.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnNewButton_3.setBackground(new Color(102, 153, 0));
		btnNewButton_3.setBounds(106, 172, 180, 39);
		contentPane.add(btnNewButton_3);
	}
}
