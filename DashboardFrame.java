package guide;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("Guide To Place");
        setSize(500, 400);
        setLayout(new GridLayout(5, 1));
        
        JButton btnSecurity =
        new JButton("Security Service");

add(btnSecurity);

btnSecurity.addActionListener(e -> {

    new SecurityServiceFrame();

});

        JButton btnBus =
        new JButton("Bus Transport");

add(btnBus);

btnBus.addActionListener(e -> {

    new BusTransportFacilityFrame();

});

        JButton btnHealth =
        new JButton("Healthcare Centers");

add(btnHealth);

btnHealth.addActionListener(e -> {

    new HealthCareCenterFrame();

});

        JButton btnEducation =
        new JButton("Educational Places");

add(btnEducation);

btnEducation.addActionListener(e -> {

    new EducationalPlaceFrame();

});

        JButton btnResturant =
        new JButton("Resturant");

add(btnResturant);

btnResturant.addActionListener(e -> {

    new ResturantFrame();

});

        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}