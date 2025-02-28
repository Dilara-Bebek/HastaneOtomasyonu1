package swing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PatientGUI {
    public static void openPatientWindow(String tcNumber, int age) {
        JFrame frame = new JFrame("Hasta Paneli");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        //  Kişisel Bilgiler Sekmesi
        JPanel personalInfoPanel = createPersonalInfoPanel(tcNumber, age);
        tabbedPane.addTab("Kişisel Bilgiler", personalInfoPanel);

        // Randevu Al Sekmesi
        JPanel appointmentPanel = createAppointmentPanel(tcNumber);
        tabbedPane.addTab("Randevu Al", appointmentPanel);

        //Randevularım Sekmesi
        JPanel appointmentsPanel = createAppointmentsPanel(tcNumber);
        tabbedPane.addTab("Randevularım", appointmentsPanel);

        // Reçetelerim Sekmesi
        JPanel prescriptionsPanel = createPrescriptionsPanel(tcNumber);
        tabbedPane.addTab("Reçetelerim", prescriptionsPanel);

        // Sağlık Geçmişi Sekmesi
        JPanel healthHistoryPanel = createHealthHistoryPanel(tcNumber);
        tabbedPane.addTab("Sağlık Geçmişi", healthHistoryPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JPanel createPersonalInfoPanel(String tcNumber, int age) {
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        UserDetails patientDetails = UserDatabase.getUserDatabase().get(tcNumber);
        String patientName = patientDetails != null ? patientDetails.getFullName() : "Bilinmiyor";

        JLabel nameLabel = new JLabel("Hasta Adı:");
        JTextField nameText = new JTextField(patientName, 20);
        nameText.setEditable(false);

        JLabel tcLabel = new JLabel("T.C. Kimlik No:");
        JTextField tcText = new JTextField(tcNumber, 11);
        tcText.setEditable(false);

        JLabel ageLabel = new JLabel("Yaş:");
        JTextField ageText = new JTextField(String.valueOf(age), 5);
        ageText.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        infoPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        infoPanel.add(nameText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(tcLabel, gbc);

        gbc.gridx = 1;
        infoPanel.add(tcText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        infoPanel.add(ageLabel, gbc);

        gbc.gridx = 1;
        infoPanel.add(ageText, gbc);

        return infoPanel;
    }

    private static JPanel createAppointmentPanel(String tcNumber) {
        JPanel appointmentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel branchLabel = new JLabel("Branş Seçin:");
        JComboBox<String> branchComboBox = new JComboBox<>(new String[]{"Dahiliye", "Kardiyoloji", "Ortopedi", "Nöroloji"});

        JLabel doctorLabel = new JLabel("Doktor Seçin:");
        JComboBox<String> doctorComboBox = new JComboBox<>(new String[]{"Dr. A", "Dr. B", "Dr. C"});

        JButton bookButton = new JButton("Randevu Al");

     // UI bileşenlerini panelde uygun şekilde yerleştiriyoruz
        gbc.gridx = 0;
        gbc.gridy = 0;
        appointmentPanel.add(branchLabel, gbc);

        gbc.gridx = 1;
        appointmentPanel.add(branchComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        appointmentPanel.add(doctorLabel, gbc);

        gbc.gridx = 1;
        appointmentPanel.add(doctorComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        appointmentPanel.add(bookButton, gbc);

     // Randevu alma işlemini gerçekleştir
        bookButton.addActionListener(e -> {
            String branch = (String) branchComboBox.getSelectedItem();
            String doctor = (String) doctorComboBox.getSelectedItem();
            // Veritabanına randevu kaydı yapıyoruz
            if (UserDatabase.bookAppointment(tcNumber, branch, doctor)) {
                JOptionPane.showMessageDialog(null, "Randevu Başarıyla Alındı!");
            } else {
                JOptionPane.showMessageDialog(null, "Randevu Alınırken Hata Oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        return appointmentPanel;
    }

    // Randevularım sekmesinin içeriğini oluşturan metod
    private static JPanel createAppointmentsPanel(String tcNumber) {
        JPanel appointmentsPanel = new JPanel(new BorderLayout());
        JLabel appointmentsLabel = new JLabel("Randevularınız:");

        // Kullanıcıya ait mevcut randevuları alıyoruz
        ArrayList<String> appointments = UserDatabase.getAppointments(tcNumber);
        JList<String> appointmentsList = new JList<>(appointments.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(appointmentsList);

        // UI bileşenlerini panelde uygun şekilde yerleştiriyoruz
        appointmentsPanel.add(appointmentsLabel, BorderLayout.NORTH);
        appointmentsPanel.add(scrollPane, BorderLayout.CENTER);

        return appointmentsPanel;
    }
    // Reçetelerim sekmesinin içeriğini oluşturan metod
    private static JPanel createPrescriptionsPanel(String tcNumber) {
        JPanel prescriptionsPanel = new JPanel(new BorderLayout());
        JLabel prescriptionsLabel = new JLabel("Reçeteleriniz:");

     // Kullanıcıya ait reçeteleri alıyoruz
        ArrayList<String> prescriptions = UserDatabase.getPrescriptions(tcNumber);
        JList<String> prescriptionsList = new JList<>(prescriptions.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(prescriptionsList);

        // UI bileşenlerini panelde uygun şekilde yerleştiriyoruz
        prescriptionsPanel.add(prescriptionsLabel, BorderLayout.NORTH);
        prescriptionsPanel.add(scrollPane, BorderLayout.CENTER);

        return prescriptionsPanel;
    }

    // Sağlık Geçmişi sekmesinin içeriğini oluşturan metod
    private static JPanel createHealthHistoryPanel(String tcNumber) {
        JPanel historyPanel = new JPanel(new BorderLayout());
        JLabel historyLabel = new JLabel("Sağlık Geçmişiniz:");

        // Kullanıcıya ait sağlık geçmişini alıyoruz
        ArrayList<String> healthHistory = UserDatabase.getPatientHistory(tcNumber);
        JList<String> historyList = new JList<>(healthHistory.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(historyList);

        // UI bileşenlerini panelde uygun şekilde yerleştiriyoruz
        historyPanel.add(historyLabel, BorderLayout.NORTH);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        return historyPanel;
    }
}
