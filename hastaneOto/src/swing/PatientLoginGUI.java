package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientLoginGUI {


    // Hasta giriş penceresini açan metod
    public static void open() {
        JFrame frame = new JFrame("Hasta Girişi");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(173, 216, 230));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel tcLabel = new JLabel("T.C. Kimlik No:");
        JTextField tcText = new JTextField(15);

        JLabel passLabel = new JLabel("Şifre:");
        JPasswordField passText = new JPasswordField(15);

        JButton loginButton = new JButton("Giriş Yap");
        JButton registerButton = new JButton("Kayıt Ol");
        JButton forgotPasswordButton = new JButton("Şifremi Unuttum");

        // T.C. Kimlik No ve Şifre giriş alanları
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(tcLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(tcText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(passText, gbc);

        // Butonlar
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(registerButton, gbc);

        // Şifreyi unuttum butonu, pencerenin üst kısmına yerleştiriliyor
        JPanel forgotPasswordPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        forgotPasswordPanel.setBackground(new Color(173, 216, 230));
        forgotPasswordPanel.add(forgotPasswordButton);

        // Ana frame'e butonlar ekleniyor
        frame.add(forgotPasswordPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Giriş butonuna tıklanıldığında yapılacak işlem
        loginButton.addActionListener(e -> {
            String tcNumber = tcText.getText();
            String password = new String(passText.getPassword());
            // Kullanıcı bilgileri doğrulanıyor
            if (UserDatabase.authenticateUserByTcAndPassword(tcNumber, password)) {
            	  // Kullanıcı hasta mı kontrol ediliyor
                if ("Patient".equalsIgnoreCase(UserDatabase.getUserTypeByTc(tcNumber))) {
                    int age = UserDatabase.getPatientAge(tcNumber);
                    JOptionPane.showMessageDialog(frame, "Hasta Girişi Başarılı!");
                    frame.dispose();
                    PatientGUI.openPatientWindow(tcNumber, age);
                } else {
                    JOptionPane.showMessageDialog(frame, "Yetkisiz giriş!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Hatalı T.C. Kimlik Numarası veya Şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Kayıt ol butonuna tıklanıldığında kayıt penceresi açılıyor
        registerButton.addActionListener(e -> openRegisterWindow());

     // Şifremi unuttum butonuna tıklanıldığında şifre sıfırlama penceresi açılıyor
        forgotPasswordButton.addActionListener(e -> openForgotPasswordWindow());

        frame.setVisible(true);// Pencere görünür hale getiriliyor
    }
 // Kayıt ol penceresini açan metod
    private static void openRegisterWindow() {
        JFrame frame = new JFrame("Kayıt Ol - Hasta");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        // Kayıt formu alanları ekleniyor
        JLabel passLabel = new JLabel("Şifre:");
        JPasswordField passText = new JPasswordField(15);

        JLabel passConfirmLabel = new JLabel("Şifreyi Tekrar Girin:");
        JPasswordField passConfirmText = new JPasswordField(15);

        JLabel tcLabel = new JLabel("T.C. Kimlik No:");
        JTextField tcText = new JTextField(15);

        JLabel ageLabel = new JLabel("Yaş:");
        JTextField ageText = new JTextField(5);

        JLabel motherLabel = new JLabel("Anne Adı:");
        JTextField motherText = new JTextField(15);

        JLabel fatherLabel = new JLabel("Baba Adı:");
        JTextField fatherText = new JTextField(15);

        JLabel idSerialLabel = new JLabel("Kimlik Seri No:");
        JTextField idSerialText = new JTextField(15);

        JButton registerButton = new JButton("Kayıt Ol");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        panel.add(passText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passConfirmLabel, gbc);
        gbc.gridx = 1;
        panel.add(passConfirmText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(tcLabel, gbc);
        gbc.gridx = 1;
        panel.add(tcText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(ageLabel, gbc);
        gbc.gridx = 1;
        panel.add(ageText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(motherLabel, gbc);
        gbc.gridx = 1;
        panel.add(motherText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(fatherLabel, gbc);
        gbc.gridx = 1;
        panel.add(fatherText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(idSerialLabel, gbc);
        gbc.gridx = 1;
        panel.add(idSerialText, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(registerButton, gbc);

        frame.add(panel);


        // Kayıt butonuna tıklanıldığında yapılacak işlem
        registerButton.addActionListener(e -> {
            String password = new String(passText.getPassword());
            String passwordConfirm = new String(passConfirmText.getPassword());
            String tcNumber = tcText.getText();
            String ageString = ageText.getText();
            String motherName = motherText.getText();
            String fatherName = fatherText.getText();
            String idSerial = idSerialText.getText();


            // Tüm alanlar kontrol ediliyor
            if (!password.isEmpty() && !passwordConfirm.isEmpty() && !tcNumber.isEmpty() && !ageString.isEmpty() &&
                !motherName.isEmpty() && !fatherName.isEmpty() && !idSerial.isEmpty()) {

                if (!password.equals(passwordConfirm)) {
                    JOptionPane.showMessageDialog(frame, "Şifreler uyuşmuyor!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!tcNumber.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(frame, "Geçersiz T.C. Kimlik Numarası!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    boolean isRegistered = UserDatabase.registerPatient(tcNumber, password, Integer.parseInt(ageString),
                            motherName, fatherName, idSerial);

                    if (isRegistered) {
                        JOptionPane.showMessageDialog(frame, "Kayıt Başarılı!");
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Kayıt sırasında hata oluştu! Lütfen verilerinizi kontrol edin.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Kayıt işlemi sırasında bir hata meydana geldi: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Lütfen tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
    // Şifreyi unuttum penceresini açan metod
    private static void openForgotPasswordWindow() {
        JFrame frame = new JFrame("Şifreyi Unuttum - Hasta");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

     // Şifre sıfırlama için gerekli alanlar
        JLabel tcLabel = new JLabel("T.C. Kimlik No:");
        JTextField tcText = new JTextField(15);

        JLabel motherLabel = new JLabel("Anne Adı:");
        JTextField motherText = new JTextField(15);

        JLabel fatherLabel = new JLabel("Baba Adı:");
        JTextField fatherText = new JTextField(15);

        JButton resetButton = new JButton("Şifreyi Sıfırla");

        // Kullanıcı bilgileri alınarak, sıfırlama işlemi yapılacak
        panel.add(tcLabel);
        panel.add(tcText);
        panel.add(motherLabel);
        panel.add(motherText);
        panel.add(fatherLabel);
        panel.add(fatherText);
        panel.add(resetButton);

        // Şifre sıfırlama butonuna tıklanıldığında yapılacak işlem
        resetButton.addActionListener(e -> {
            String tcNumber = tcText.getText();
            String motherName = motherText.getText();
            String fatherName = fatherText.getText();

            // Kimlik doğrulaması yapılır
            if (UserDatabase.verifyIdentity(tcNumber, motherName, fatherName)) {
                String newPassword = JOptionPane.showInputDialog(frame, "Yeni Şifrenizi Girin:");
                if (newPassword != null && !newPassword.isEmpty()) {
                    UserDatabase.updatePassword(tcNumber, newPassword);
                    JOptionPane.showMessageDialog(frame, "Şifreniz başarıyla sıfırlandı.");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Yeni şifre boş olamaz!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Kimlik bilgileri hatalı!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
