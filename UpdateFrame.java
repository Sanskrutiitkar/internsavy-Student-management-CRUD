import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UpdateFrame extends JFrame{

Container c;
JLabel lblrno, lblname, lblm1, lblm2, lblm3;
JTextField txtrno, txtname, txtm1, txtm2, txtm3;
JButton btnsave, btnBack;

UpdateFrame(){

c = getContentPane();
c.setLayout(null);

lblrno = new JLabel("Enter roll no: ");
txtrno = new JTextField(20);
lblname =  new JLabel("Enter name: ");
txtname = new JTextField(20);
lblm1 = new JLabel("Enter subject 1 marks: ");
txtm1 = new JTextField(20);
lblm2 = new JLabel("Enter subject 2 marks: ");
txtm2 = new JTextField(20);
lblm3 = new JLabel("Enter subject 3 marks: ");
txtm3 = new JTextField(20);
btnsave = new JButton("Save");
btnBack = new JButton("Back");

Font f = new Font("Courier", Font.BOLD , 20);

lblrno.setFont(f);
txtrno.setFont(f);
lblname.setFont(f);
txtname.setFont(f);
lblm1.setFont(f);
txtm1.setFont(f);
lblm2.setFont(f);
txtm2.setFont(f);
lblm3.setFont(f);
txtm3.setFont(f);
btnsave.setFont(f);
btnBack.setFont(f);

lblrno.setBounds(249,50,300,30);
txtrno.setBounds(170,90,300,30);
lblname.setBounds(242,140,300,30);
txtname.setBounds(170,180,300,30);
lblm1.setBounds(210,230,300,30);
txtm1.setBounds(170,270,300,30);
lblm2.setBounds(210,320,300,30);
txtm2.setBounds(170,360,300,30);
lblm3.setBounds(210,410,300,30);
txtm3.setBounds(170,450,300,30);
btnsave.setBounds(240,520,165,35);
btnBack.setBounds(240,600,165,35);

c.add(lblrno);
c.add(txtrno);
c.add(lblname);
c.add(txtname);
c.add(lblm1);
c.add(txtm1);
c.add(lblm2);
c.add(txtm2);
c.add(lblm3);
c.add(txtm3);
c.add(btnsave);
c.add(btnBack);

ActionListener a1=(ae) -> {

MainFrame a = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

ActionListener a2=(ae) -> {

try{
	if(txtrno.getText().equals(""))
		throw new Exception("Please Enter Rno to update the record");
	int r = Integer.parseInt(txtrno.getText());
	if(r<=0)
		throw new Exception("Rno should be greater than Zero ");
	
	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
	String url = "jdbc:mysql://localhost:3306/sms_project";
	String un = "root";
	String pw = "abc456";
	
	Connection con = DriverManager.getConnection(url,un,pw);
	String sql1 = "select * from student where rno = ?";
	PreparedStatement ps = con.prepareStatement(sql1);
	ps.setInt(1,r);
	ResultSet rs = ps.executeQuery();
	if(rs.next())
	{
		String name = txtname.getText();
		if(txtname.getText().equals(""))
			throw new Exception("Please enter name");
		if(!name.matches("[a-zA-Z]+") | name.length() < 2)
		{
			txtname.setText("");
			txtname.requestFocus();
			throw new Exception("Name should contain only letters with min length two");
		}
		
		if(txtm1.getText().equals("") || txtm2.getText().equals("") || txtm3.getText().equals(""))
			throw new Exception("Please fill the marks field");
		
		int m1 = Integer.parseInt(txtm1.getText());
		int m2 = Integer.parseInt(txtm2.getText());
		int m3 = Integer.parseInt(txtm3.getText());
	
		if(m1 < 0 || m1 > 100)
		{
			txtm1.setText("");
			txtm1.requestFocus();
			throw new Exception("Marks for subject 1 should be in range of 0 to 100");
		}
		if(m2 < 0 || m2 > 100)
		{
			txtm2.setText("");
			txtm2.requestFocus();
			throw new Exception("Marks for subject 2 should be in range of 0 to 100");
		}
		if(m3 < 0 || m3 > 100)
		{
			txtm3.setText("");
			txtm3.requestFocus();
			throw new Exception("Marks for subject 3 should be in range of 0 to 100");
		}
		String sql2 = "update student set name = ? , m1 = ? , m2 = ? , m3= ? where rno = ?";
		PreparedStatement pst = con.prepareStatement(sql2);
		pst.setString(1,name);
		pst.setInt(2,m1);
		pst.setInt(3,m2);
		pst.setInt(4,m3);
		pst.setInt(5,r);
		pst.executeUpdate();
		con.close();
		JOptionPane.showMessageDialog(c,"Record updated successfully!!");
	}
	else
	{
		JOptionPane.showMessageDialog(c,r + " : this rollno does not exist");
		con.close();	
	}
}
catch(SQLException e){
	JOptionPane.showMessageDialog(c, "Issue : " + e);	
}
catch(NumberFormatException e){
	JOptionPane.showMessageDialog(c, "enter integers only ");
}
catch(Exception e){
	JOptionPane.showMessageDialog(c , e.getMessage());
}
};
btnsave.addActionListener(a2);
setTitle("Update Student");
setSize(650,800);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}