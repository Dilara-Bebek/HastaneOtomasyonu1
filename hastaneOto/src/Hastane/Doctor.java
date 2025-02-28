package Hastane;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String id; // Doktor kimlik numarası
    private String name; // Doktorun adı
    private String specialization; // Uzmanlık alanı
    private String username; // Kullanıcı adı (giriş için)
    private String password; // Şifre (giriş için)
    private List<Appointment> appointments; // Doktorun randevuları

    // Constructor
    public Doctor(String id, String name, String specialization, String username, String password) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.username = username;
        this.password = password;
        this.appointments = new ArrayList<>();
    }

    // Getter ve Setter metotları
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    // Randevu eklemek
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // Doktor bilgilerini görüntülemek
    public void displayDoctorInfo() {
        System.out.println("Doctor ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Specialization: " + specialization);
        System.out.println("Username: " + username);
    }

    // Doktorun randevularını listelemek
    public void displayAppointments() {
        System.out.println("Appointments for Dr. " + name + ":");
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment appointment : appointments) {
                appointment.displayAppointmentInfo(); // Appointment sınıfındaki metodu kullanarak detaylı bilgi veriyoruz
            }
        }
    }

    // Giriş yapma işlemi
    public boolean checkCredentials(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Doktorun randevusunu iptal etme
    public boolean cancelAppointment(Appointment appointment) {
        if (appointments.contains(appointment)) {
            return appointment.cancelAppointment(this); // Doktor yalnızca kendi randevusunu iptal edebilir
        }
        System.out.println("Appointment not found.");
        return false;
    }

    // Hastanın sağlık geçmişini görüntüleme
    public void displayPatientMedicalHistory(Patient patient) {
        patient.displayMedicalHistory();
    }

    // Hastanın tedavi yöntemlerini görüntüleme
    public void displayPatientTreatmentMethods(Patient patient) {
        patient.displayTreatmentMethods();
    }

    // Hastanın reçetelerini görüntüleme
    public void displayPatientPrescriptions(Patient patient) {
        patient.displayPrescriptions();
    }

	public Object getPhone() {
		// TODO Auto-generated method stub
		return null;
	}
}
