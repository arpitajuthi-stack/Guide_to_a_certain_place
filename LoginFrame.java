package guide;

import javax.swing.*;
import java.awt.event.*;


public class LoginFrame
        extends JFrame {

    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin;

    public LoginFrame() {

        setTitle("Login");

        setSize(300,200);

        setLayout(null);

        JLabel l1 =
                new JLabel("Username");

        l1.setBounds(
                20,20,100,30);

        txtUser =
                new JTextField();

        txtUser.setBounds(
                120,20,120,30);

        JLabel l2 =
                new JLabel("Password");

        l2.setBounds(
                20,70,100,30);

        txtPass =
                new JPasswordField();

        txtPass.setBounds(
                120,70,120,30);

        btnLogin =
                new JButton("Login");

        btnLogin.setBounds(
                100,120,100,30);

        add(l1);
        add(l2);

        add(txtUser);
        add(txtPass);

        add(btnLogin);
        
        btnLogin.addActionListener((ActionEvent e) -> {

    UserLogin login = new UserLogin();

    String user = txtUser.getText();
    String pass = new String(txtPass.getPassword());

    if(login.login(user, pass)) {

        JOptionPane.showMessageDialog(
                null,
                "Login Successful");

        new DashboardFrame();

        dispose();

    } else {

        JOptionPane.showMessageDialog(
                null,
                "Invalid Username or Password");
    }

});

        setVisible(true);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
    }
}