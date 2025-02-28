package Hastane;

import java.time.LocalDateTime;

public class Appointment {
    private String id; // Randevu ID'si
    private Patient patient; // Randevuya ait hasta bilgisi
    private Doctor doctor;// Randevuya ait doktor bilgisi
    private LocalDateTime dateTime; // Randevu tarihi ve saati
    private boolean isCancelled; // Randevu iptal durumu

    // Constructor: Randevu nesnesini oluşturur ve başlangıçta iptal durumunu "false" olarak ayarlar
    public Appointment(String id, Patient patient, Doctor doctor, LocalDateTime dateTime) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.isCancelled = false; // Başlangıçta iptal edilmemiş
    }

 // Getter ve Setter metotları: Randevu nesnesinin özelliklerine erişmek ve güncellemek için kullanılır
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    // Randevu bilgilerini görüntüleme
    public void displayAppointmentInfo() {
        System.out.println("Appointment ID: " + id);
        System.out.println("Patient: " + patient.getName());
        System.out.println("Doctor: " + doctor.getName());
        System.out.println("Date: " + dateTime);
        System.out.println("Cancelled: " + (isCancelled ? "Yes" : "No"));
    }

    // Randevu iptalini kontrol etme
    private boolean canCancel() {
        return !isCancelled;
    }

    // Randevu iptal etme
    public boolean cancelAppointment(Object user) {
        if (user instanceof Doctor && doctor.equals(user)) {
            // Doktorun randevusunu iptal etme
            return cancelForDoctor();
        } else if (user instanceof Patient && patient.equals(user)) {
            // Hastanın randevusunu iptal etme
            return cancelForPatient();
        } else {
            System.out.println("User is not authorized to cancel this appointment.");
            return false;
        }
    }

    // Doktor için randevu iptali
    private boolean cancelForDoctor() {
        if (canCancel()) {  // Eğer randevu iptal edilebilir durumdaysa, randevuyu iptal eder ve başarılı olduğunu döner
            isCancelled = true;
            System.out.println("Appointment with Dr. " + doctor.getName() + " has been cancelled.");
            return true;
        } else {// Eğer randevu zaten iptal edilmişse, hata mesajı verir
            System.out.println("This appointment has already been cancelled.");
            return false;
        }
    }

    // Hasta için randevu iptali
    private boolean cancelForPatient() {
        // Hastaların yalnızca doktor randevularını iptal etmesine izin verilmiyor.
        System.out.println("Patient cannot cancel the appointment.");
        return false;
    }
}
