package swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DoctorGUI {
	// Doktor panelini açan metod
    public static void openDoctorWindow(String doctorName, String specialty, String workingHours) {
    	   // Yeni bir pencere oluşturuluyor
    	JFrame frame = new JFrame("Doktor Paneli");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);// Pencereyi ekranın ortasında aç

     // TabbedPane: Birden fazla sekme içeren panel
        JTabbedPane tabbedPane = new JTabbedPane();

        // Kişisel Bilgiler Sekmesi
        JPanel infoPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Doktorun bilgilerini alacağımız JTextField'lar ekleniyor
        infoPanel.add(new JLabel("Ad Soyad:"));
        JTextField nameField = new JTextField(doctorName);
        infoPanel.add(nameField);

        infoPanel.add(new JLabel("Uzmanlık:"));
        JTextField specialtyField = new JTextField(specialty);
        infoPanel.add(specialtyField);

        infoPanel.add(new JLabel("Çalışma Saatleri:"));
        JTextField hoursField = new JTextField(workingHours);
        infoPanel.add(hoursField);

        infoPanel.add(new JLabel("Telefon:"));
        JTextField phoneField = new JTextField();
        infoPanel.add(phoneField);

        infoPanel.add(new JLabel("E-posta:"));
        JTextField emailField = new JTextField();
        infoPanel.add(emailField);

        infoPanel.add(new JLabel("Adres:"));
        JTextArea addressArea = new JTextArea(3, 20);
        JScrollPane addressScrollPane = new JScrollPane(addressArea);
        infoPanel.add(addressScrollPane);

        // "Bilgileri Kaydet" butonu ekleniyor
        JButton saveInfoButton = new JButton("Bilgileri Kaydet");
        infoPanel.add(new JLabel()); // Boşluk için
        infoPanel.add(saveInfoButton);

        // Kaydet butonuna tıklanınca mesaj gösteriliyor
        saveInfoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Bilgiler başarıyla kaydedildi!");
        });

        tabbedPane.addTab("Kişisel Bilgiler", infoPanel);

        // Hastalar Sekmesi
        JPanel patientsPanel = new JPanel(new BorderLayout());
        ArrayList<String> allPatients = UserDatabase.getAllPatients(); // Veritabanından hastalar çekiliyor
        JList<String> patientList = new JList<>(allPatients.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(patientList);

     // Hasta detaylarını görüntüleme butonu
        JButton viewDetailsButton = new JButton("Hasta Detaylarını Görüntüle");
        patientsPanel.add(scrollPane, BorderLayout.CENTER);
        patientsPanel.add(viewDetailsButton, BorderLayout.SOUTH);

     // Butona tıklanınca, seçilen hastanın detaylarını gösteren pencere açılıyor
        viewDetailsButton.addActionListener(e -> {
            String selectedPatient = patientList.getSelectedValue();
            if (selectedPatient != null) {
                openPatientDetailsWindow(selectedPatient);
            } else {
                JOptionPane.showMessageDialog(frame, "Lütfen bir hasta seçin!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        tabbedPane.addTab("Hastalar", patientsPanel);

        // Takvim ve Randevular Sekmesi
        JPanel calendarPanel = new JPanel(new BorderLayout());
        JLabel calendarLabel = new JLabel("Günlük Randevular", JLabel.CENTER);
        calendarLabel.setFont(new Font("Arial", Font.BOLD, 16));
        calendarPanel.add(calendarLabel, BorderLayout.NORTH);

        DefaultTableModel calendarTableModel = new DefaultTableModel(
                new Object[]{"Saat", "Randevu Detayları"}, 0
        );

        // Örnek veriler
        calendarTableModel.addRow(new Object[]{"09:00", "Hasta 1: Genel kontrol"});
        calendarTableModel.addRow(new Object[]{"10:00", "Hasta 2: Kan testi"});
        calendarTableModel.addRow(new Object[]{"11:00", "Hasta 3: Danışma"});

        JTable calendarTable = new JTable(calendarTableModel);
        JScrollPane calendarScrollPane = new JScrollPane(calendarTable);
        calendarPanel.add(calendarScrollPane, BorderLayout.CENTER);

        // Randevu İptal Etme Butonu
        JButton cancelAppointmentButton = new JButton("Randevuyu İptal Et");
        calendarPanel.add(cancelAppointmentButton, BorderLayout.SOUTH);

        cancelAppointmentButton.addActionListener(e -> {
            int selectedRow = calendarTable.getSelectedRow();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(calendarPanel,
                        "Randevuyu silmek istediğinizden emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Randevuyu silme işlemi
                    calendarTableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(calendarPanel, "Randevu başarıyla silindi.");
                }
            } else {
                JOptionPane.showMessageDialog(calendarPanel, "Lütfen bir randevu seçin.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        tabbedPane.addTab("Takvim", calendarPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

 // Hasta detaylarını gösteren pencereyi açan metod
    private static void openPatientDetailsWindow(String patientName) {
        JFrame frame = new JFrame("Hasta Detayları - " + patientName);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.add(new JLabel("Hasta Adı: " + patientName));
        panel.add(new JLabel("Sağlık Geçmişi: " + UserDatabase.getPatientHistory(patientName)));
        panel.add(new JLabel("Teşhisler: " + UserDatabase.getPatientDiagnoses(patientName)));
        panel.add(new JLabel("Reçeteler: " + UserDatabase.getPatientPrescriptions(patientName)));


        // "Reçete Yaz" butonu
        JButton writePrescriptionButton = new JButton("Reçete Yaz");
        panel.add(writePrescriptionButton);
        // Reçete yazma butonuna tıklanınca, reçete yazma penceresi açılıyor
        writePrescriptionButton.addActionListener(e -> openPrescriptionWindow(patientName));

        frame.add(panel);// Paneli pencereye ekliyoruz
        frame.setVisible(true);// Pencereyi görünür yapıyoruz
    }

 // Reçete yazma penceresini açan metod
    private static void openPrescriptionWindow(String patientName) {
        JFrame frame = new JFrame("Reçete Yaz - " + patientName);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1));
        JTextField prescriptionField = new JTextField();
        JButton saveButton = new JButton("Kaydet");

        panel.add(new JLabel("Reçete:"));
        panel.add(prescriptionField);
        panel.add(saveButton);

        // Kaydet butonuna tıklanınca, reçete kaydediliyor
        saveButton.addActionListener(e -> {
            String prescription = prescriptionField.getText();
            if (!prescription.isEmpty()) {
                UserDatabase.addPrescriptionToPatient(patientName, prescription);
                JOptionPane.showMessageDialog(frame, "Reçete eklendi: " + prescription);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Lütfen bir reçete girin!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);// Paneli pencereye ekliyoruz
        frame.setVisible(true);// Pencereyi görünür yapıyoruz
    }
}
