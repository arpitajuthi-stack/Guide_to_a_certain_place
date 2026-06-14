package guide;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class ResturantFrame extends JFrame {

    JTextField txtName;
    JTextField txtAddress;
    JTextField txtContact;
    JTextField txtCuisine;
    JTextField txtCapacity;
    JTextField txtSearch;

    JButton btnAdd;
    JButton btnView;
    JButton btnSearch;
    JButton btnDelete;

    JTable table;
    DefaultTableModel model;

    public ResturantFrame() {

        setTitle("Resturant Management");

        setSize(700,700);

        setLayout(null);

        // Labels

        JLabel l1 = new JLabel("Name");
        l1.setBounds(20,20,100,25);

        JLabel l2 = new JLabel("Address");
        l2.setBounds(20,60,100,25);

        JLabel l3 = new JLabel("Contact");
        l3.setBounds(20,100,100,25);

        JLabel l4 = new JLabel("Cuisine");
        l4.setBounds(20,140,100,25);

        JLabel l5 = new JLabel("Capacity");
        l5.setBounds(20,180,100,25);

        // Text Fields

        txtName = new JTextField();
        txtName.setBounds(120,20,200,25);

        txtAddress = new JTextField();
        txtAddress.setBounds(120,60,200,25);

        txtContact = new JTextField();
        txtContact.setBounds(120,100,200,25);

        txtCuisine = new JTextField();
        txtCuisine.setBounds(120,140,200,25);

        txtCapacity = new JTextField();
        txtCapacity.setBounds(120,180,200,25);

        // Buttons

        btnAdd = new JButton("Add Restaurant");
        btnAdd.setBounds(30,230,150,30);

        btnView = new JButton("View Restaurants");
        btnView.setBounds(220,230,170,30);

        // Table

        model = new DefaultTableModel();

        model.addColumn("Name");
        model.addColumn("Address");
        model.addColumn("Contact");
        model.addColumn("Cuisine");
        model.addColumn("Capacity");

        table = new JTable(model);

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBounds(
                20,
                290,
                640,
                220);

        // Search Components

        JLabel lblSearch =
                new JLabel("Search Name");

        lblSearch.setBounds(
                20,
                540,
                100,
                25);

        txtSearch =
                new JTextField();

        txtSearch.setBounds(
                120,
                540,
                120,
                25);

        btnSearch =
                new JButton("Search");

        btnSearch.setBounds(
                260,
                540,
                100,
                25);

        btnDelete =
                new JButton(
                        "Delete Selected");

        btnDelete.setBounds(
                390,
                540,
                170,
                25);

        // Add Components

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);

        add(txtName);
        add(txtAddress);
        add(txtContact);
        add(txtCuisine);
        add(txtCapacity);

        add(btnAdd);
        add(btnView);

        add(scrollPane);

        add(lblSearch);
        add(txtSearch);
        add(btnSearch);
        add(btnDelete);

        // Add Restaurant

        btnAdd.addActionListener(e -> {

            String data =
                    txtName.getText() + "," +
                    txtAddress.getText() + "," +
                    txtContact.getText() + "," +
                    txtCuisine.getText() + "," +
                    txtCapacity.getText();

            FileManager.save(
                    "resturant.txt",
                    data);

            JOptionPane.showMessageDialog(
                    null,
                    "Resturant Saved Successfully");

            txtName.setText("");
            txtAddress.setText("");
            txtContact.setText("");
            txtCuisine.setText("");
            txtCapacity.setText("");
        });

        // View Restaurants

        btnView.addActionListener(e -> {

            loadRestaurants();

        });

        // Search Restaurant

        btnSearch.addActionListener(e -> {

            searchRestaurant();

        });

        // Delete Restaurant

        btnDelete.addActionListener(e -> {

            deleteResturant();

        });

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    // Load Restaurants

    private void loadRestaurants() {

        model.setRowCount(0);

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "restaurant.txt"));

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
                    "No restaurant data found.");
        }
    }

    // Search Restaurant

    private void searchRestaurant() {

        String searchName =
                txtSearch.getText().trim();

        model.setRowCount(0);

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "restaurant.txt"));

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

    // Delete Restaurant

    private void deleteResturant() {

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
                            "restaurant.txt");

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

            loadRestaurants();

            JOptionPane.showMessageDialog(
                    null,
                    "Resturant Deleted");
        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage());
        }
    }
}