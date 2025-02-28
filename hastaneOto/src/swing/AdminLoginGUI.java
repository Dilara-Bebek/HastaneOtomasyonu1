package swing;

import java.awt.*;
import javax.swing.*;

import Hastane.Administrator;

class AdminLoginGUI {
	 // Yönetici girişi penceresini açan metot
    public static void open() {
    	 // JFrame oluşturuluyor ve pencere başlığı belirleniyor
        JFrame frame = new JFrame("Yönetici Girişi");
        frame.setSize(500, 400);// Pencere boyutu ayarlanıyor
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Pencere kapandığında programın çalışmaya devam etmesi sağlanıyor
        frame.setLocationRelativeTo(null); // Ekranda ortalamayı sağlar

        // Ana panel oluşturuluyor ve arka plan rengi ayarlanıyor
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(173, 216, 230));// Açık mavi renk
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);// Bileşenler arasındaki boşluklar ayarlanıyor

        // Kullanıcı Adı ve Şifre Alanları
        JLabel userLabel = new JLabel("Kullanıcı Adı:");
        JTextField userText = new JTextField(15);
        JLabel passLabel = new JLabel("Şifre:");
        JPasswordField passText = new JPasswordField(15);

        // Şifreyi Göster Checkbox
        JCheckBox showPassword = new JCheckBox("Şifreyi Göster");

        // Butonlar
        JButton loginButton = new JButton("Giriş Yap");
        JButton changePasswordButton = new JButton("Şifreyi Değiştir");
        JButton forgotPasswordButton = new JButton("Şifremi Unuttum");

        // GridBagLayout kullanarak bileşenlerin yerleştirilmesi
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(userText, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(passText, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(showPassword, gbc);

        gbc.gridy = 3;
        mainPanel.add(loginButton, gbc);

        gbc.gridy = 4;
        mainPanel.add(changePasswordButton, gbc);

        gbc.gridy = 5;
        mainPanel.add(forgotPasswordButton, gbc);

        frame.add(mainPanel, BorderLayout.CENTER);

        // Şifreyi Göster İşlevi
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passText.setEchoChar((char) 0); // Şifreyi göster
            } else {
                passText.setEchoChar('*'); // Şifreyi gizle
            }
        });

        // Giriş Yap Butonu
        loginButton.addActionListener(e -> {
            String username = userText.getText().trim();// Kullanıcı adı alınır
            String password = new String(passText.getPassword()).trim();// Şifre alınır

         // Kullanıcı adı veya şifre boş ise hata mesajı gösterilir
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Kullanıcı adı ve şifre boş olamaz!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

         // Yönetici doğrulaması yapılır
            if (Administrator.authenticateAdmin(username, password)) {
                JOptionPane.showMessageDialog(frame, "Giriş Başarılı!");
                frame.dispose(); // Login GUI'yi kapat
                AdminGUI.open(null, null, null, null); // Yönetici Kontrol Panelini Aç
            } else {
                JOptionPane.showMessageDialog(frame, "Hatalı Kullanıcı Adı veya Şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Şifre Değiştir Butonu
        changePasswordButton.addActionListener(e -> {
            String oldPassword = new String(passText.getPassword());
            String newPassword = JOptionPane.showInputDialog(frame, "Yeni Şifreyi Girin:");
            // Eski şifre veya yeni şifre boşsa hata mesajı gösterilir
            if (oldPassword.isEmpty() || newPassword == null || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Eski şifre ve yeni şifre boş olamaz!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }
         // Yeni şifre yeterince uzun değilse hata mesajı gösterilir
            if (newPassword.length() < 6) {
                JOptionPane.showMessageDialog(frame, "Yeni şifre en az 6 karakter olmalıdır!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }
         // Yeni şifreyi onaylamak için tekrar girilmesi sağlanır
            String confirmPassword = JOptionPane.showInputDialog(frame, "Yeni Şifreyi Tekrar Girin:");
            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Şifreler uyuşmuyor!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Şifre değişikliği işlemi yapılır
            if (Administrator.resetAdminPassword(oldPassword, newPassword)) {
                JOptionPane.showMessageDialog(frame, "Şifre Başarıyla Değiştirildi!");
            } else {
                JOptionPane.showMessageDialog(frame, "Eski şifre hatalı veya yeni şifre geçersiz!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Şifremi Unuttum Butonu
        forgotPasswordButton.addActionListener(e -> {
            String newPassword = JOptionPane.showInputDialog(frame, "Yeni Şifreyi Girin:");
            // Yeni şifre belirli bir uzunluktaysa şifre güncellenir
            if (newPassword != null && newPassword.length() >= 6) {
                Administrator.setAdminPassword(newPassword);
                JOptionPane.showMessageDialog(frame, "Şifre Başarıyla Yenilendi!");
            } else {
                JOptionPane.showMessageDialog(frame, "Şifre en az 6 karakter olmalıdır!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
     // Pencere görünür yapılır
        frame.setVisible(true);
    }
}
