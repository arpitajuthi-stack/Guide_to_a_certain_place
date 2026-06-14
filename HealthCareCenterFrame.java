package guide;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class HealthCareCenterFrame extends JFrame {

    JTextField txtName;
    JTextField txtAddress;
    JTextField txtContact;
    JTextField txtSpecialization;
    JTextField txtSearch;

    JButton btnAdd;
    JButton btnView;
    JButton btnSearch;
    JButton btnDelete;

    JTable table;
    DefaultTableModel model;

    public HealthCareCenterFrame() {

        setTitle("Healthcare Centers");

        setSize(700,700);

        setLayout(null);

        JLabel l1 = new JLabel("Center Name");
        l1.setBounds(20,20,120,25);

        JLabel l2 = new JLabel("Address");
        l2.setBounds(20,60,120,25);

        JLabel l3 = new JLabel("Contact");
        l3.setBounds(20,100,120,25);

        JLabel l4 = new JLabel("Specialization");
        l4.setBounds(20,140,120,25);

        txtName = new JTextField();
        txtName.setBounds(150,20,200,25);

        txtAddress = new JTextField();
        txtAddress.setBounds(150,60,200,25);

        txtContact = new JTextField();
        txtContact.setBounds(150,100,200,25);

        txtSpecialization = new JTextField();
        txtSpecialization.setBounds(150,140,200,25);

        btnAdd = new JButton("Add Center");
        btnAdd.setBounds(30,220,150,30);

        btnView = new JButton("View Centers");
        btnView.setBounds(220,220,170,30);

        model = new DefaultTableModel();

        model.addColumn("Name");
        model.addColumn("Address");
        model.addColumn("Contact");
        model.addColumn("Specialization");

        table = new JTable(model);

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBounds(
                20,
                280,
                640,
                220);

        JLabel lblSearch =
                new JLabel("Search Name");

        lblSearch.setBounds(
                20,
                530,
                100,
                25);

        txtSearch =
                new JTextField();

        txtSearch.setBounds(
                120,
                530,
                120,
                25);

        btnSearch =
                new JButton("Search");

        btnSearch.setBounds(
                260,
                530,
                100,
                25);

        btnDelete =
                new JButton(
                        "Delete Selected");

        btnDelete.setBounds(
                390,
                530,
                170,
                25);

        add(l1);
        add(l2);
        add(l3);
        add(l4);

        add(txtName);
        add(txtAddress);
        add(txtContact);
        add(txtSpecialization);

        add(btnAdd);
        add(btnView);

        add(scrollPane);

        add(lblSearch);
        add(txtSearch);
        add(btnSearch);
        add(btnDelete);

        // Add Center

        btnAdd.addActionListener(e -> {

            String data =
                    txtName.getText() + "," +
                    txtAddress.getText() + "," +
                    txtContact.getText() + "," +
                    txtSpecialization.getText();

            FileManager.save(
                    "healthcare.txt",
                    data);

            JOptionPane.showMessageDialog(
                    null,
                    "Healthcare Center Saved");

            txtName.setText("");
            txtAddress.setText("");
            txtContact.setText("");
            txtSpecialization.setText("");
        });

        // View

        btnView.addActionListener(e -> {

            loadCenters();

        });

        // Search

        btnSearch.addActionListener(e -> {

            searchCenter();

        });

        // Delete

        btnDelete.addActionListener(e -> {

            deleteCenter();

        });

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    private void loadCenters() {

        model.setRowCount(0);

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "healthcare.txt"));

            String line;

            while((line = br.readLine())
                    != null) {

                String[] data =
                        line.split(",");

                model.addRow(data);
            }

            br.close();
        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "No healthcare data found.");
        }
    }

    private void searchCenter() {

        String searchName =
                txtSearch.getText().trim();

        model.setRowCount(0);

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "healthcare.txt"));

            String line;

            while((line = br.readLine())
                    != null) {

                String[] data =
                        line.split(",");

                if(data[0].equalsIgnoreCase(
                        searchName)) {

                    model.addRow(data);
                }
            }

            br.close();
        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage());
        }
    }

    private void deleteCenter() {

        int selectedRow =
                table.getSelectedRow();

        if(selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "Select a row first");

            return;
        }

        String nameToDelete =
                model.getValueAt(
                        selectedRow,
                        0).toString();

        try {

            File inputFile =
                    new File(
                            "healthcare.txt");

            File tempFile =
                    new File(
                            "temp.txt");

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    inputFile));

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(
                                    tempFile));

            String line;

            while((line = br.readLine())
                    != null) {

                String[] data =
                        line.split(",");

                if(!data[0].equalsIgnoreCase(
                        nameToDelete)) {

                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            loadCenters();

            JOptionPane.showMessageDialog(
                    null,
                    "Healthcare Center Deleted");
        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage());
        }
    }
}