package swing;

import javax.swing.*;
import java.awt.*;

public class DoctorLoginGUI {
    public static void main(String[] args) {
    	// Program çalıştığında login ekranını başlatmak için open metodunu çağırıyoruz
        SwingUtilities.invokeLater(DoctorLoginGUI::open);
    }

    public static void open() {
    	  // Doktor giriş penceresi oluşturuluyor
        JFrame frame = new JFrame("Doktor Girişi");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Pencereyi ekranın ortasında aç

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(173, 216, 230));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Kullanıcı adı ve şifre giriş alanları
        JLabel userLabel = new JLabel("T.C. Kimlik No:");
        JTextField userText = new JTextField(15);

        JLabel passLabel = new JLabel("Şifre:");
        JPasswordField passText = new JPasswordField(15);

        // Butonlar
        JButton loginButton = new JButton("Giriş Yap");
        JButton registerButton = new JButton("Kayıt Ol");
        JButton forgotPasswordButton = new JButton("Şifremi Unuttum");

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(userText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(passText, gbc);


        // Giriş butonunu ekliyoruz
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(loginButton, gbc);

        // Kayıt ol butonunu ekliyoruz
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(registerButton, gbc);

        // Şifremi unuttum butonunu ekliyoruz
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(forgotPasswordButton, gbc);

        // Ana panele butonları ekliyoruz
        frame.add(mainPanel);

        // Giriş Yap İşlevi
        loginButton.addActionListener(e -> {
            String tcNumber = userText.getText();
            String password = new String(passText.getPassword());

            if (UserDatabase.authenticateUserByTcAndPassword(tcNumber, password) &&
                    "Doctor".equalsIgnoreCase(UserDatabase.getUserTypeByTc(tcNumber))) {
                JOptionPane.showMessageDialog(frame, "Doktor Girişi Başarılı!");
                frame.dispose();
                // Doktorun özel GUI'sini başlat
                DoctorGUI.openDoctorWindow(tcNumber, null, "09:00-17:00");
            } else {
                JOptionPane.showMessageDialog(frame, "Hatalı T.C. Kimlik No veya Şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Kayıt Ol İşlevi
        registerButton.addActionListener(e -> openRegisterWindow());

        // Şifremi Unuttum İşlevi
        forgotPasswordButton.addActionListener(e -> openForgotPasswordWindow());

        frame.setVisible(true);
    }

    // Kayıt olma penceresini açan metod
    private static void openRegisterWindow() {
    	 // Yeni bir kayıt penceresi oluşturuluyor
        JFrame frame = new JFrame("Kayıt Ol - Doktor");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        // Kayıt için gerekli alanlar
        JTextField tcText = new JTextField(15);
        JPasswordField passText = new JPasswordField(15);
        JTextField motherNameText = new JTextField(15);
        JTextField fatherNameText = new JTextField(15);
        JTextField idSerialText = new JTextField(15);
        JComboBox<String> specialtyBox = new JComboBox<>(new String[]{"Kardiyoloji", "Dahiliye", "Nöroloji"});

        JButton registerButton = new JButton("Kayıt Ol");

     // Alanları ekliyoruz
        addField(panel, gbc, "T.C. Kimlik No:", tcText, 0);
        addField(panel, gbc, "Şifre:", passText, 1);
        addField(panel, gbc, "Anne Adı:", motherNameText, 2);
        addField(panel, gbc, "Baba Adı:", fatherNameText, 3);
        addField(panel, gbc, "Kimlik Seri No:", idSerialText, 4);
        addField(panel, gbc, "Uzmanlık:", specialtyBox, 5);

        // Kayıt ol butonunu ekliyoruz
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(registerButton, gbc);

     // Kayıt butonuna tıklandığında kullanıcı veritabanına kaydediliyor
        registerButton.addActionListener(e -> {
            String tcNumber = tcText.getText();
            String password = new String(passText.getPassword());
            String motherName = motherNameText.getText();
            String fatherName = fatherNameText.getText();
            String idSerial = idSerialText.getText();
            String specialty = (String) specialtyBox.getSelectedItem();

            // Eğer alanlar boş değilse, kullanıcı veritabanına kaydedilir
            if (!tcNumber.isEmpty() && !password.isEmpty() && !motherName.isEmpty() && !fatherName.isEmpty() && !idSerial.isEmpty()) {
                UserDatabase.addUser(tcNumber, password, motherName, fatherName, idSerial, "Doctor", 0, specialty, null, null, null);
                JOptionPane.showMessageDialog(frame, "Kayıt başarılı!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }


    // Şifremi Unuttum penceresini açan metod
    private static void openForgotPasswordWindow() {
    	   // Şifremi unuttum penceresi oluşturuluyor
        JFrame frame = new JFrame("Şifremi Unuttum");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

     // Kimlik doğrulama ve yeni şifre alanları
        JTextField tcText = new JTextField(15);
        JTextField motherNameText = new JTextField(15);
        JTextField fatherNameText = new JTextField(15);
        JPasswordField newPasswordText = new JPasswordField(15);

        JButton resetButton = new JButton("Şifreyi Sıfırla");

     // Alanları ekliyoruz
        addField(panel, gbc, "T.C. Kimlik No:", tcText, 0);
        addField(panel, gbc, "Anne Adı:", motherNameText, 1);
        addField(panel, gbc, "Baba Adı:", fatherNameText, 2);
        addField(panel, gbc, "Yeni Şifre:", newPasswordText, 3);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(resetButton, gbc);

     // Şifre sıfırlama butonuna tıklandığında kimlik doğrulaması yapılır ve şifre sıfırlanır
        resetButton.addActionListener(e -> {
            String tcNumber = tcText.getText();
            String motherName = motherNameText.getText();
            String fatherName = fatherNameText.getText();
            String newPassword = new String(newPasswordText.getPassword());

            if (UserDatabase.verifyIdentity(tcNumber, motherName, fatherName)) {
                UserDatabase.updatePassword(tcNumber, newPassword);
                JOptionPane.showMessageDialog(frame, "Şifre başarıyla sıfırlandı!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Kimlik doğrulama başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void addField(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }
}
