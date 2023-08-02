import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ViewFrame extends JFrame{

Container c;
TextArea taData;
JButton btnBack;

ViewFrame(){

c= getContentPane();
c.setLayout(new FlowLayout());

taData = new TextArea(20,80);
btnBack = new JButton("Back");

c.add(taData);
c.add(btnBack);

StringBuffer data = new StringBuffer();
try{
	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
	String url = "jdbc:mysql://localhost:3306/sms_project";
	String un = "root";
	String pw = "abc456";
	Connection con = DriverManager.getConnection(url,un,pw);

	String sql = "select * from student";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	while(rs.next()){
		int rno = rs.getInt(1);
		String name = rs.getString(2);
		int m1 = rs.getInt(3);
		int m2 = rs.getInt(4);
		int m3 = rs.getInt(5);
		data.append("Rno -> " + rno + " Name -> " + name + " Marks 1 -> " + m1 + " Marks 2 -> " + m2 + " Marks 3 -> " + m3 + "\n\n");

	}
	taData.setText(data.toString());
	con.close();
}
catch(SQLException e){
JOptionPane.showMessageDialog(c,"Issue :" + e);
}


ActionListener a1=(ae) -> {

MainFrame m = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

setTitle("View Students");
setSize(600,600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}