import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class AddFrame extends JFrame{

Container c;

JLabel lblRno, lblName, lblM1, lblM2, lblM3;
JButton btnSave,btnBack;
JTextField txtRno, txtName, txtM1, txtM2, txtM3;

AddFrame(){

c= getContentPane();
c.setLayout(null);

lblRno = new JLabel("Enter rno: " , JLabel.CENTER);
txtRno = new JTextField(20);
lblName = new JLabel("Enter name: " , JLabel.CENTER);
txtName = new JTextField(20);
lblM1 = new JLabel("Enter sub marks 1: ", JLabel.CENTER);
txtM1 = new JTextField(20);
lblM2 = new JLabel("Enter sub marks 2: ", JLabel.CENTER);
txtM2 = new JTextField(20);
lblM3 = new JLabel("Enter sub marks 3: ", JLabel.CENTER);
txtM3 = new JTextField(20);
btnSave = new JButton("Save");
btnBack = new JButton("Back");

Font f = new Font("Courier" , Font.BOLD , 20);

lblRno.setFont(f);
txtRno.setFont(f);
lblName.setFont(f);
txtName.setFont(f);
lblM1.setFont(f);
txtM1.setFont(f);
lblM2.setFont(f);
txtM2.setFont(f);
lblM3.setFont(f);
txtM3.setFont(f);

btnSave.setFont(f);
btnBack.setFont(f);

lblRno.setBounds(200,25,150,25);
txtRno.setBounds(220,60,120,30);
lblName.setBounds(200,110,150,25);
txtName.setBounds(200,145,150,30);
lblM1.setBounds(170,195,250,20);
txtM1.setBounds(190,230,200,30);
lblM2.setBounds(170,280,250,20);
txtM2.setBounds(190,320,200,30);
lblM3.setBounds(170,370,250,20);
txtM3.setBounds(190,400,200,30);
btnSave.setBounds(200,460,165,35);
btnBack.setBounds(200,530,165,35);

c.add(lblName);
c.add(txtName);
c.add(lblRno);
c.add(txtRno);
c.add(lblM1);
c.add(txtM1);
c.add(lblM2);
c.add(txtM2);
c.add(lblM3);
c.add(txtM3);
c.add(btnSave);
c.add(btnBack);

ActionListener a1=(ae) -> {

MainFrame a = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

ActionListener a2=(ae) -> {

try{
	
	if(txtRno.getText().equals("")){
		
		throw new Exception("Rno should not be empty");
	}
	int rno = Integer.parseInt(txtRno.getText());
	if(rno <= 0)
	{
		txtRno.setText("");
		txtRno.requestFocus();
		throw new Exception("Rno should be greater than zero");
	}
	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
	String url = "jdbc:mysql://localhost:3306/sms_project";
	String un = "root";
	String pw  = "abc456";
	Connection con = DriverManager.getConnection(url,un,pw);

	String sq="select * from student where rno=?";
	PreparedStatement ps=con.prepareStatement(sq);
	ps.setInt(1,rno);
	ResultSet rs=ps.executeQuery();
	if(rs.next())
		throw new Exception(rno+" Rno already exists ");
	
	String name = txtName.getText();
	if(txtName.getText().equals(""))
		throw new Exception("Please Enter Your Name");

	if(!(name.matches("[a-zA-z]+")) )
	{
		txtName.setText("");
		txtName.requestFocus();
		throw new Exception("name should contain letters only ");
	}
	if(name.length() < 2 )
	{
		txtName.setText("");
		txtName.requestFocus();
		throw new Exception("name should contain maximun two characters");
	}

	if(txtM1.getText().equals("") || txtM2.getText().equals("") || txtM3.getText().equals("") )
		throw new Exception("marks field should not be empty");

	int m1 = Integer.parseInt(txtM1.getText());
	int m2 = Integer.parseInt(txtM2.getText());
	int m3 = Integer.parseInt(txtM3.getText());
	if(m1<0 | m2<0 | m3<0 )
	{
		
		if(m1<0)
		{
		txtM1.setText("");
		txtM1.requestFocus();
		}
		if(m2<0 )
		{
		txtM2.setText("");
		txtM2.requestFocus();
		}
		if(m3<0)
		{
		txtM3.setText("");
		txtM3.requestFocus();
		}
		throw new Exception("Marks should not less than zero");
		
	}
	if(m1>100 | m2>100 | m3>100)
	{
		if(m1>100)
		{
		txtM1.setText("");
		txtM1.requestFocus();
		}
		if(m2>100)
		{
		txtM2.setText("");
		txtM2.requestFocus();
		}
		if(m3>100)
		{
		txtM3.setText("");
		txtM3.requestFocus();
		}

		throw new Exception("marks should not be greater than 100");
	}

	
	


	String sql="insert into student values(?,?,?,?,?)";
	PreparedStatement pst = con.prepareStatement(sql);
	pst.setInt(1,rno);
	pst.setString(2,name);
	pst.setInt(3,m1);
	pst.setInt(4,m2);
	pst.setInt(5,m3);
	pst.executeUpdate();
	JOptionPane.showMessageDialog(c,"Record Added!");
	con.close();

}
catch(SQLException e){
	JOptionPane.showMessageDialog(c,"Issue: " + e);	
}
catch(NumberFormatException e){
	JOptionPane.showMessageDialog(c,"Enter integers only");
}
catch(Exception e){
	JOptionPane.showMessageDialog(c,e.getMessage());
}
};
btnSave.addActionListener(a2);

setTitle("ADD RECORD");
setSize(550,640);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);


}
}