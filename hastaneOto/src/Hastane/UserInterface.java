package Hastane;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UserInterface {
    private Database database;// Veritabanı bağlantısı
    private Scanner scanner;// Kullanıcıdan girdi almak için Scanner nesnesi

    // Constructor
    public UserInterface(Database database) {
        this.database = database;
        this.scanner = new Scanner(System.in);// Scanner nesnesi başlatılır
    }

    // Başlangıç metodu, sistem başlatılır
    public void start() {
    	 boolean exit = false; // Çıkış durumu
         while (!exit) { // Çıkış yapılmadığı sürece döngü devam eder
             printMenu(); // Menü seçenekleri yazdırılır
             System.out.print("Choose an option: "); // Kullanıcıdan seçim istenir
             int choice = scanner.nextInt(); // Kullanıcı seçimi alınır
             scanner.nextLine(); // Buffer temizliği
             exit = handleChoice(choice); // Seçime göre işlemler yapılır
        }
    }

    // Menü seçeneği yazdırma metodu
    private void printMenu() {
    	 System.out.println("========== Hospital Management System ==========");
         System.out.println("1. Add Patient"); // Hasta ekleme
         System.out.println("2. Add Doctor"); // Doktor ekleme
         System.out.println("3. Schedule Appointment"); // Randevu oluşturma
         System.out.println("4. List Patients"); // Hastaları listeleme
         System.out.println("5. List Doctors"); // Doktorları listeleme
         System.out.println("6. List Appointments"); // Randevuları listeleme
         System.out.println("7. List Treatments"); // Tedavileri listeleme
         System.out.println("8. List Medications"); // İlaçları listeleme
         System.out.println("9. Assign Task to Staff"); // Personele görev atama
         System.out.println("10. Handle Emergency"); // Acil durum yönetimi
         System.out.println("11. Exit"); // Sistemden çıkış
    }

    // Menü seçeneklerini kontrol etme metodu
    private boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                addPatient();// Hasta ekleme işlemi
                break;
            case 2:
                addDoctor(); // Doktor ekleme işlemi
                break;
            case 3:
                scheduleAppointment();// Randevu oluşturma işlemi
                break;
            case 4:
                database.listPatients();// Hastaları listeleme işlemi
                break;
            case 5:
                database.listDoctors();// Doktorları listeleme işlemi
                break;
            case 6:
                database.listAppointments();// Randevuları listeleme işlemi
                break;
            case 7:
                database.listTreatments();// Tedavileri listeleme işlemi
                break;
            case 8:
                database.listMedications(); // İlaçları listeleme işlemi
                break;
            case 9:
                assignTaskToStaff();// Görev atama işlemi
                break;
            case 10:
                handleEmergency();// Acil durum yönetimi işlemi
                break;
            case 11:
                System.out.println("Exiting system...");// Sistemden çıkış
                return true;
            default:
                System.out.println("Invalid choice. Please try again.");// Hatalı giriş uyarısı
        }
        return false;
    }

    // Yeni hasta ekleme metodu
    private void addPatient() {
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();// Hasta ID'si alınır
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();// Hasta adı alınır
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();// Hasta yaşı alınır
        scanner.nextLine();  // Buffer temizliği
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Medical History: ");
        String medicalHistory = scanner.nextLine();// Hasta adresi alınır
        
        // Yeni hasta nesnesi oluşturulur ve veritabanına eklenir
        Patient patient = new Patient(id, name, age, medicalHistory, medicalHistory);
        database.addPatient(patient);// Veritabanına ekleme
        System.out.println("Patient added successfully.");
    }

    // Yeni doktor ekleme metodu
    private void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        String id = scanner.nextLine();// Doktor ID'si alınır
        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();// Doktor adı alınır
        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine(); // Uzmanlık alanı alınır
        System.out.print("Enter Username: ");
        String username = scanner.nextLine(); // Kullanıcı adı alınır
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();// Şifre alınır

     // Yeni doktor nesnesi oluşturulur ve veritabanına eklenir
        Doctor doctor = new Doctor(id, name, specialization,username, password);
        database.addDoctor(doctor); // Veritabanına ekleme
        System.out.println("Doctor added successfully.");
    }

    // Randevu planlama metodu
    private void scheduleAppointment() {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter Appointment Time (HH:MM): ");
        String time = scanner.nextLine();

        // Patient ve Doctor nesnelerini veritabanından alıyoruz
        Patient patient = database.getPatientById(patientId);
        Doctor doctor = database.getDoctorById(doctorId);

        if (patient != null && doctor != null) {
            // Tarih ve saat bilgisini LocalDateTime formatına dönüştür
            try {
                String[] dateParts = date.split("-"); // YYYY-MM-DD
                String[] timeParts = time.split(":"); // HH:MM

                // LocalDateTime nesnesi oluşturma (yyyy-MM-ddTHH:mm)
                LocalDateTime dateTime = LocalDateTime.of(
                    Integer.parseInt(dateParts[0]), // Yıl
                    Integer.parseInt(dateParts[1]), // Ay
                    Integer.parseInt(dateParts[2]), // Gün
                    Integer.parseInt(timeParts[0]), // Saat
                    Integer.parseInt(timeParts[1])  // Dakika
                );

                // Randevu oluştur
                String appointmentId = patientId + "_" + doctorId + "_" + dateTime.toString(); // ID oluşturuluyor
                Appointment appointment = new Appointment(appointmentId, patient, doctor, dateTime);

                // Veritabanına randevuyu ekle
                database.addAppointment(appointment);

                System.out.println("Appointment scheduled successfully.");
            } catch (Exception e) {
                System.out.println("Invalid date or time format. Please use YYYY-MM-DD for date and HH:MM for time.");
            }
        } else {
            System.out.println("Invalid Patient ID or Doctor ID.");
        }
    }



 // Personel görev atama metodu
    private void assignTaskToStaff() {
        System.out.print("Enter Staff ID: ");
        String staffId = scanner.nextLine();
        System.out.print("Enter Task: ");
        String task = scanner.nextLine();

        // Veritabanı üzerinden görevi atama
        boolean success = database.assignTaskToStaff(staffId, task);
        if (success) {
            System.out.println("Task assigned successfully.");
        } else {
            System.out.println("Staff member not found.");
        }
    }


    // Acil durum yönetimi metodu
    private void handleEmergency() {
        System.out.print("Enter Emergency Patient ID: ");
        String patientId = scanner.nextLine();
        Patient emergencyPatient = database.getPatientById(patientId);

        if (emergencyPatient != null) {
            database.handleEmergency(emergencyPatient);
            System.out.println("Emergency handled successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }
}
