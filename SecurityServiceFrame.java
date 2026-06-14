package guide;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class SecurityServiceFrame extends JFrame {

    JTextField txtName;
    JTextField txtOffice;
    JTextField txtContact;
    JTextField txtType;
    JTextField txtSearch;

    JButton btnAdd;
    JButton btnView;
    JButton btnSearch;
    JButton btnDelete;

    JTable table;
    DefaultTableModel model;

    public SecurityServiceFrame() {

        setTitle("Security Services");

        setSize(700,700);

        setLayout(null);

        JLabel l1 = new JLabel("Service Name");
        l1.setBounds(20,20,120,25);

        JLabel l2 = new JLabel("Office Name");
        l2.setBounds(20,60,120,25);

        JLabel l3 = new JLabel("Contact");
        l3.setBounds(20,100,120,25);

        JLabel l4 = new JLabel("Service Type");
        l4.setBounds(20,140,120,25);

        txtName = new JTextField();
        txtName.setBounds(150,20,200,25);

        txtOffice = new JTextField();
        txtOffice.setBounds(150,60,200,25);

        txtContact = new JTextField();
        txtContact.setBounds(150,100,200,25);

        txtType = new JTextField();
        txtType.setBounds(150,140,200,25);

        btnAdd = new JButton("Add Service");
        btnAdd.setBounds(30,220,150,30);

        btnView = new JButton("View Services");
        btnView.setBounds(220,220,170,30);

        model = new DefaultTableModel();

        model.addColumn("Service Name");
        model.addColumn("Office Name");
        model.addColumn("Contact");
        model.addColumn("Service Type");

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
                new JButton("Delete Selected");

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
        add(txtOffice);
        add(txtContact);
        add(txtType);

        add(btnAdd);
        add(btnView);

        add(scrollPane);

        add(lblSearch);
        add(txtSearch);
        add(btnSearch);
        add(btnDelete);

        // Add Service

        btnAdd.addActionListener(e -> {

            String data =
                    txtName.getText() + "," +
                    txtOffice.getText() + "," +
                    txtContact.getText() + "," +
                    txtType.getText();

            FileManager.save(
                    "security.txt",
                    data);

            JOptionPane.showMessageDialog(
                    null,
                    "Security Service Saved");

            txtName.setText("");
            txtOffice.setText("");
            txtContact.setText("");
            txtType.setText("");
        });

        // View

        btnView.addActionListener(e -> {

            loadServices();

        });

        // Search

        btnSearch.addActionListener(e -> {

            searchService();

        });

        // Delete

        btnDelete.addActionListener(e -> {

            deleteService();

        });

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    private void loadServices() {

        model.setRowCount(0);

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "security.txt"));

            String line;

            while((line = br.readLine()) != null) {

                String[] data =
                        line.split(",");

                model.addRow(data);
            }

            br.close();

        } catch(Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "No security service data found.");
        }
    }

    private void searchService() {

        String searchName =
                txtSearch.getText().trim();

        model.setRowCount(0);

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "security.txt"));

            String line;

            while((line = br.readLine()) != null) {

                String[] data =
                        line.split(",");

                if(data[0].equalsIgnoreCase(
                        searchName)) {

                    model.addRow(data);
                }
            }

            br.close();

        } catch(Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage());
        }
    }

    private void deleteService() {

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
                            "security.txt");

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

            while((line = br.readLine()) != null) {

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

            loadServices();

            JOptionPane.showMessageDialog(
                    null,
                    "Security Service Deleted");

        } catch(Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage());
        }
    }
}