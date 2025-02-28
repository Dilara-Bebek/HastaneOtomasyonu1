package swing;

import Hastane.Doctor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DoctorViewer {

    private List<Doctor> doctors;

    public DoctorViewer(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public void open() {
        JFrame frame = new JFrame("Doktorlar");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Doktorlar için tablo modeli
        DoctorTableModel tableModel = new DoctorTableModel(doctors);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
