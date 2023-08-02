import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DeleteFrame extends JFrame{

Container c;
JLabel lblrno;
JTextField txtrno;
JButton btndelete ,btnback;

DeleteFrame(){
c=getContentPane();
c.setLayout(null);

lblrno = new JLabel("Enter rno: ");
txtrno = new JTextField(20);
btndelete = new JButton("Delete");
btnback=new JButton("Back");

Font f = new Font("Courier", Font.BOLD,20);
lblrno.setFont(f);
txtrno.setFont(f);
btndelete.setFont(f);
btnback.setFont(f);

lblrno.setBounds(249,50,300,30);
txtrno.setBounds(170,90,300,30);
btndelete.setBounds(240,170,165,35);
btnback.setBounds(240,230,165,35);

c.add(lblrno);
c.add(txtrno);
c.add(btndelete);
c.add(btnback);

ActionListener a1=(ae) ->{
MainFrame a = new MainFrame();
dispose();
};
btnback.addActionListener(a1);

ActionListener a2=(ae) ->{
try{

	if(txtrno.getText().equals(""))
		throw new Exception("Please enter Rno");
	int r = Integer.parseInt(txtrno.getText());
	if(r==0)
		throw new Exception("Rollno cant be zero");
	if(r<0)
		throw new Exception("Roll no should be greater than 0");
	
	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
	String url = "jdbc:mysql://localhost:3306/sms_project";
	String un = "root";
	String pw = "abc456";
	
	Connection con = DriverManager.getConnection(url,un,pw);
	String sql = "select * from student where rno = ?";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setInt(1,r);
	ResultSet rs = ps.executeQuery();
	if(rs.next())
	{
		String sql2 = "delete from student where rno = ?";
		PreparedStatement pst = con.prepareStatement(sql2);
		pst.setInt(1,r);
		pst.executeUpdate();
		JOptionPane.showMessageDialog(c,"Record deleted successfully!!");
	}
	else
		JOptionPane.showMessageDialog(c,r + ": this rno does not exist");
	con.close();
	
}
catch(SQLException e){
		JOptionPane.showMessageDialog(c,"Issue : " + e);	
}
catch(NumberFormatException e){
		JOptionPane.showMessageDialog(c,"Please enter integer");
		txtrno.setText("");
		txtrno.requestFocus();
}
catch(Exception e){
		JOptionPane.showMessageDialog(c,e.getMessage());
		txtrno.setText("");
		txtrno.requestFocus();
}

};
btndelete.addActionListener(a2);
setTitle("Delete student");
setSize(650,800);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}