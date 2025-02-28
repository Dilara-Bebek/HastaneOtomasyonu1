package Hastane;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Veritabanını oluşturuyoruz
        Database database = new Database();

        // Kullanıcı arayüzünü oluşturuyoruz
        UserInterface ui = new UserInterface(database);

        // Veritabanına bazı veriler ekliyoruz
        addSampleData(database);

        // Sistemi başlatıyoruz
        ui.start();
    }

    // Örnek veriler ekliyoruz (doktorlar ve hastalar gibi)
    public static void addSampleData(Database database) {
        // Hasta ve doktor oluşturuyoruz
        Patient patient = new Patient("P123", "John Doe", 0, null, null);
        Doctor doctor = new Doctor("D456", "Dr. Smith", "Cardiology", "drsmith", "password");
        LocalDateTime dateTime = LocalDateTime.of(2025, 12, 12, 0, 0);
        // Veritabanına ekliyoruz
        database.addPatient(patient);
        database.addDoctor(doctor);

        // Örnek randevu oluşturuyoruz
        Date appointmentDate = new Date();
        Appointment appointment = new Appointment("123",patient, doctor,dateTime);

        // Randevuyu veritabanına ekliyoruz
        database.addAppointment(appointment);
    }
}
