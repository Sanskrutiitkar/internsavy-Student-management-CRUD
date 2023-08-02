import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame{

Container c;
JButton btnAdd, btnView, btnUpdate, btnDelete;

MainFrame(){

c = getContentPane();
c.setLayout(null);

btnAdd = new JButton("Add");
btnView = new JButton("View");
btnUpdate = new JButton("Update");
btnDelete = new JButton("Delete");

Font f = new Font("Cambria", Font.BOLD,20);
btnAdd.setFont(f);
btnView.setFont(f);
btnUpdate.setFont(f);
btnDelete.setFont(f);

btnAdd.setBounds(80,50,100,50);
btnView.setBounds(200,50,100,50);
btnUpdate.setBounds(80,140,110,50);
btnDelete.setBounds(200,140,100,50);

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);

ActionListener a1=(ae) -> {
AddFrame a = new AddFrame();
dispose();
};
btnAdd.addActionListener(a1);

ActionListener a2 =(ae) -> {
ViewFrame a = new ViewFrame();
dispose();
};
btnView.addActionListener(a2);

ActionListener a3=(ae) -> {
UpdateFrame a = new UpdateFrame();
dispose();
};
btnUpdate.addActionListener(a3);

ActionListener a4=(ae) -> {
DeleteFrame a = new DeleteFrame();
dispose();
};
btnDelete.addActionListener(a4);

setTitle("Student Management System");
setSize(400,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}