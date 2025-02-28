package swing;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDatabase {
    private static final String DATA_FILE = "user_data.ser";
    private static HashMap<String, UserDetails> userDatabase = new HashMap<>();

    // Kullanıcı verilerini dosyadan yükler
    public static void loadUserData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            setUserDatabase((HashMap<String, UserDetails>) ois.readObject());
            System.out.println("Kullanıcı verileri başarıyla yüklendi.");
        } catch (FileNotFoundException e) {
            System.out.println("Veritabanı dosyası bulunamadı. Yeni bir dosya oluşturulacak.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Kullanıcı verilerini dosyaya kaydeder
    public static void saveUserData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(getUserDatabase());
            System.out.println("Kullanıcı verileri başarıyla kaydedildi.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Kullanıcı ekleme (Hasta, Doktor veya Yönetici)
    public static void addUser(String tcNumber, String password, String motherName, String fatherName, String idSerial,
                                String userType, int age) {
        getUserDatabase().put(tcNumber, new UserDetails(tcNumber, password, motherName, fatherName, idSerial, userType, age));
        saveUserData();
    }

    // Kullanıcı ekleme (Doktor için uzmanlık, telefon, e-posta, adres eklenir)
    public static void addUser(String tcNumber, String password, String motherName, String fatherName, String idSerial,
                                String userType, int age, String specialty, String phoneNumber, String email, String address) {
        getUserDatabase().put(tcNumber, new UserDetails(tcNumber, password, motherName, fatherName, idSerial, userType, age, specialty, phoneNumber, email, address));
        saveUserData();
    }

    // Kullanıcı doğrulama (TC kimlik numarası ve şifre ile)
    public static boolean authenticateUserByTcAndPassword(String tcNumber, String password) {
        return getUserDatabase().containsKey(tcNumber) && getUserDatabase().get(tcNumber).getPassword().equals(password);
    }

    // Kullanıcı tipi getirir
    public static String getUserTypeByTc(String tcNumber) {
        return getUserDatabase().containsKey(tcNumber) ? getUserDatabase().get(tcNumber).getUserType() : null;
    }

    // Kullanıcı yaşını getirir
    public static int getPatientAge(String tcNumber) {
        return getUserDatabase().containsKey(tcNumber) ? getUserDatabase().get(tcNumber).getAge() : -1; // Hata durumunda
    }

    // Kullanıcı TC kimlik numarasını getirir
    public static String getPatientTc(String tcNumber) {
        return getUserDatabase().containsKey(tcNumber) ? getUserDatabase().get(tcNumber).getTcNumber() : null;
    }

    // Hastaya teşhis ekleme
    public static void addDiagnosisToPatient(String patientTcNumber, String diagnosis) {
        if (getUserDatabase().containsKey(patientTcNumber)) {
            getUserDatabase().get(patientTcNumber).addDiagnosis(diagnosis);
            saveUserData();
        }
    }

    // Hastanın geçmişini getirir
    public static ArrayList<String> getPatientHistory(String patientTcNumber) {
        return getUserDatabase().containsKey(patientTcNumber) ? getUserDatabase().get(patientTcNumber).getDiagnosisHistory() : new ArrayList<>();
    }

    // Doktorun randevularını ekler
    public static void addAppointmentToDoctor(String doctorTcNumber, String appointment) {
        if (getUserDatabase().containsKey(doctorTcNumber)) {
            getUserDatabase().get(doctorTcNumber).addAppointment(appointment);
            saveUserData();
        }
    }

    // Tüm hasta kullanıcılarını getirir
    public static ArrayList<String> getAllPatients() {
        return getAllUsersByType("Patient");
    }

    // Tüm doktor kullanıcılarını getirir
    public static ArrayList<String> getAllDoctors() {
        return getAllUsersByType("Doctor");
    }

    // Kullanıcı tipine göre kullanıcıları getirir
    private static ArrayList<String> getAllUsersByType(String userType) {
        ArrayList<String> userList = new ArrayList<>();
        for (UserDetails user : getUserDatabase().values()) {
            if (userType.equalsIgnoreCase(user.getUserType())) {
                userList.add(user.getTcNumber());
            }
        }
        return userList;
    }

    // Kullanıcının randevularını getirir
    public static ArrayList<String> getAppointments(String tcNumber) {
        if (getUserDatabase().containsKey(tcNumber)) {
            ArrayList<String> appointments = getUserDatabase().get(tcNumber).getAppointments();
            return appointments != null ? appointments : new ArrayList<>();
        }
        return new ArrayList<>();
    }

    // Randevu kaydeder
    public static boolean bookAppointment(String tcNumber, String branch, String doctor) {
        if (getUserDatabase().containsKey(tcNumber)) {
            String appointment = "Branch: " + branch + ", Doctor: " + doctor;
            getUserDatabase().get(tcNumber).addAppointment(appointment);
            saveUserData();
            return true;
        }
        return false;
    }

    // Hastanın reçetelerini getirir
    public static ArrayList<String> getPrescriptions(String tcNumber) {
        return getUserDatabase().containsKey(tcNumber) ? getUserDatabase().get(tcNumber).getPrescriptions() : new ArrayList<>();
    }

    public static HashMap<String, UserDetails> getUserDatabase() {
        return userDatabase;
    }

    public static void setUserDatabase(HashMap<String, UserDetails> userDatabase) {
        UserDatabase.userDatabase = userDatabase;
    }

	public static boolean registerPatient(String tcNumber, String password, int int1, String motherName,
			String fatherName, String idSerial) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean verifyIdentity(String tcNumber, String motherName, String fatherName) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void updatePassword(String tcNumber, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	public static String getPatientDiagnoses(String patientName) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getPatientPrescriptions(String patientName) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void addPrescriptionToPatient(String patientName, String prescription) {
		// TODO Auto-generated method stub
		
	}
}
