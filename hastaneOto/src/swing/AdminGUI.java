package swing;

import Hastane.Appointment;
import Hastane.Doctor;
import Hastane.Patient;
import Hastane.Staff;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminGUI {

	// Yönetici paneli penceresini açan metod
    public static void open(List<Doctor> doctors, List<Patient> patients, List<swing.StaffViewer.Staff> staffList, List<swing.AppointmentViewer.Appointment> appointments) {
    	 // Ana pencere oluşturuluyor
    	JFrame frame = new JFrame("Yönetici Kontrol Paneli");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Ana panel
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBackground(Color.WHITE);

        // Butonlar
        JButton doctorButton = new JButton("Doktorları Görüntüle");
        JButton patientButton = new JButton("Hastaları Görüntüle");
        JButton appointmentButton = new JButton("Randevuları Görüntüle");
        JButton staffButton = new JButton("Personelleri Görüntüle");
     // Butonlar panele ekleniyor
        panel.add(doctorButton);
        panel.add(patientButton);
        panel.add(appointmentButton);
        panel.add(staffButton);

        // Ana panel, pencereye ekleniyor
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Doktorları görüntüle
        doctorButton.addActionListener(e -> {
            DoctorViewer doctorViewer = new DoctorViewer(doctors);
            doctorViewer.open();
        });

        // Hastaları görüntüle
        patientButton.addActionListener(e -> {
            PatientViewer patientViewer = new PatientViewer(patients);
            patientViewer.open(patients);
        });

        // Randevuları görüntüle
        appointmentButton.addActionListener(e -> {
            AppointmentViewer appointmentViewer = new AppointmentViewer(appointments);
            appointmentViewer.open();
        });

        // Personelleri görüntüle
        staffButton.addActionListener(e -> {
            StaffViewer staffViewer = new StaffViewer(staffList);
            staffViewer.open();
        });
    }
}
