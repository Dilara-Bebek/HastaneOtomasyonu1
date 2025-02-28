package Hastane;

import java.util.HashMap;

public class Administrator {
	// Yönetici veritabanını temsil eden bir HashMap
    private static HashMap<String, String> adminDatabase = new HashMap<>();
    private static String adminUsername = "admin";  // Varsayılan yönetici kullanıcı adı
    private static String adminPassword = "admin123";   // Varsayılan yönetici şifresi

   

	// Admin doğrulama metodu
    public static boolean authenticateAdmin(String username, String password) {
        // Kullanıcı adı ve şifreyi kontrol et
        return adminUsername.equals(username) && adminPassword.equals(password);
    }

    // Yönetici şifresini oluşturma veya değiştirme
    public static boolean setAdminPassword(String newPassword) {
    	 // Yeni şifre null değilse ve en az 6 karakter uzunluğunda ise şifre güncellenir
        if (newPassword != null && newPassword.length() >= 6) {
            adminPassword = newPassword;
            return true;
        }
        return false;  // Şifre geçersizse false döner
    }

    // Şifremi unuttum kısmı
    public static boolean resetAdminPassword(String oldPassword, String newPassword) {
        if (adminPassword.equals(oldPassword) && newPassword != null && newPassword.length() >= 6) {
            adminPassword = newPassword;
            return true;
        }
        return false;  // Şifre güncellenemezse false döner
    }
 // Yönetici kullanıcı adını döndüren metod
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	// Yönetici şifresini döndüren metod
	public Object getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
}
