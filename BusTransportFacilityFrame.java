package guide;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class BusTransportFacilityFrame extends JFrame {

    JTextField txtName, txtAddress,
            txtContact, txtType,
            txtCapacity, txtSearch;

    JButton btnAdd, btnView,
            btnSearch, btnDelete;

    JTable table;
    DefaultTableModel model;

    public BusTransportFacilityFrame() {

        setTitle("Bus Transport Facility");

        setSize(700,700);

        setLayout(null);

        JLabel l1 =
                new JLabel("Bus Stop");

        l1.setBounds(20,20,120,25);

        JLabel l2 =
                new JLabel("Location");

        l2.setBounds(20,60,120,25);

        JLabel l3 =
                new JLabel("Contact");

        l3.setBounds(20,100,120,25);

        JLabel l4 =
                new JLabel("Bus Type");

        l4.setBounds(20,140,120,25);

        JLabel l5 =
                new JLabel("Capacity");

        l5.setBounds(20,180,120,25);

        txtName = new JTextField();
        txtAddress = new JTextField();
        txtContact = new JTextField();
        txtType = new JTextField();
        txtCapacity = new JTextField();

        txtName.setBounds(150,20,200,25);
        txtAddress.setBounds(150,60,200,25);
        txtContact.setBounds(150,100,200,25);
        txtType.setBounds(150,140,200,25);
        txtCapacity.setBounds(150,180,200,25);

        btnAdd =
                new JButton("Add");

        btnView =
                new JButton("View");

        btnAdd.setBounds(
                50,230,120,30);

        btnView.setBounds(
                220,230,120,30);

        model =
                new DefaultTableModel();

        model.addColumn("Stop");
        model.addColumn("Location");
        model.addColumn("Contact");
        model.addColumn("Type");
        model.addColumn("Capacity");

        table =
                new JTable(model);

        JScrollPane sp =
                new JScrollPane(table);

        sp.setBounds(
                20,280,640,220);

        JLabel lblSearch =
                new JLabel("Search");

        lblSearch.setBounds(
                20,530,100,25);

        txtSearch =
                new JTextField();

        txtSearch.setBounds(
                120,530,120,25);

        btnSearch =
                new JButton("Search");

        btnDelete =
                new JButton("Delete");

        btnSearch.setBounds(
                260,530,100,25);

        btnDelete.setBounds(
                390,530,120,25);

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);

        add(txtName);
        add(txtAddress);
        add(txtContact);
        add(txtType);
        add(txtCapacity);

        add(btnAdd);
        add(btnView);

        add(sp);

        add(lblSearch);
        add(txtSearch);
        add(btnSearch);
        add(btnDelete);

        btnAdd.addActionListener(e -> {

            String data =
                    txtName.getText()+","+
                    txtAddress.getText()+","+
                    txtContact.getText()+","+
                    txtType.getText()+","+
                    txtCapacity.getText();

            FileManager.save(
                    "bus.txt",
                    data);

            JOptionPane.showMessageDialog(
                    null,
                    "Saved");
        });

        btnView.addActionListener(
                e -> loadData());

        btnSearch.addActionListener(
                e -> searchData());

        btnDelete.addActionListener(
                e -> deleteData());

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    private void loadData() {

        model.setRowCount(0);

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "bus.txt"));

            String line;

            while((line=br.readLine())
                    != null) {

                model.addRow(
                        line.split(","));
            }

            br.close();
        }
        catch(Exception e){}
    }

    private void searchData() {

        String name =
                txtSearch.getText();

        model.setRowCount(0);

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "bus.txt"));

            String line;

            while((line=br.readLine())
                    != null) {

                String[] data =
                        line.split(",");

                if(data[0]
                        .equalsIgnoreCase(
                                name)) {

                    model.addRow(data);
                }
            }

            br.close();
        }
        catch(Exception e){}
    }

    private void deleteData() {

        int row =
                table.getSelectedRow();

        if(row==-1)
            return;

        String name =
                model.getValueAt(
                        row,0)
                        .toString();

        try {

            File input =
                    new File(
                            "bus.txt");

            File temp =
                    new File(
                            "temp.txt");

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    input));

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(
                                    temp));

            String line;

            while((line=br.readLine())
                    != null) {

                String[] data =
                        line.split(",");

                if(!data[0]
                        .equalsIgnoreCase(
                                name)) {

                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            input.delete();

            temp.renameTo(input);

            loadData();
        }
        catch(Exception e){}
    }
}