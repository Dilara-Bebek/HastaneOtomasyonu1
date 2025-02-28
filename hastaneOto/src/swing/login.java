package swing;

import javax.swing.*;

import Hastane.Administrator;

import java.awt.*;
import java.awt.event.*;

public class login {
    public static void main(String[] args) {
        // Veritabanı verilerini yükleyelim
        UserDatabase.loadUserData();

        // Ana pencere
        JFrame frame = new JFrame("Hastane Yönetim Sistemi - Giriş");
        frame.setSize(600, 400);// Pencere boyutunu ayarlıyoruz
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Pencere kapatıldığında uygulama kapanır
        frame.setLocationRelativeTo(null);// Pencereyi ekranın ortasına yerleştiriyoruz

        // Ana panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(0, 51, 102)); // Koyu mavi arka plan
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);// Alanlar arasındaki boşluğu ayarlıyoruz

        // Başlık
        JLabel welcomeLabel = new JLabel("Hastane Yönetim Sistemi");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;// Başlık etiketini iki sütuna yayıyoruz
        mainPanel.add(welcomeLabel, gbc);

        // Hasta Girişi Butonu
        JButton patientLoginButton = new JButton("Hasta Girişi");
        styleButton(patientLoginButton); // Butonun stilini ayarlıyoruz
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(patientLoginButton, gbc);

        // Doktor Girişi Butonu
        JButton doctorLoginButton = new JButton("Doktor Girişi");
        styleButton(doctorLoginButton);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(doctorLoginButton, gbc);

        // Yönetici Girişi Butonu
        JButton adminLoginButton = new JButton("Yönetici Girişi");
        styleButton(adminLoginButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(adminLoginButton, gbc);
        
        // Ana paneli frame'e ekliyoruz
        frame.add(mainPanel);

        // Hasta Girişi
        patientLoginButton.addActionListener(e -> PatientLoginGUI.open());

        // Doktor Girişi
        doctorLoginButton.addActionListener(e -> DoctorLoginGUI.open());

        // Yönetici Girişi
        adminLoginButton.addActionListener(e -> AdminLoginGUI.open());

        frame.setVisible(true);
    }

    // Butonları özelleştiren yardımcı metot
    private static void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(new Color(0, 123, 255)); // Mavi renk
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Hover efekti
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 102, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 123, 255));
            }
        });
    }
}


