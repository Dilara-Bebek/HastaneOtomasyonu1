package Hastane;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;
    private List<Treatment> treatments;
    private List<MedicationManagement> medications;
    private List<Staff> staff;
    private List<Administrator> administrators; // Yöneticiler için liste
	private Administrator admin;

    // Constructor
    public Database() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
        treatments = new ArrayList<>();
        medications = new ArrayList<>();
        staff = new ArrayList<>();
        administrators = new ArrayList<>();
        this.admin = new Administrator();
    }

    //  Hasta ekleme
    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient added: " + patient.getName());
    }

    //  Doktor ekleme
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Doctor added: " + doctor.getName());
    }

    //  Randevu oluşturma
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        System.out.println("Appointment scheduled for " + appointment.getPatient().getName());
    }

    //  Tedavi ekleme
    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
        System.out.println("Treatment added: " + treatment.getTreatmentName());
    }

    //  İlaç ekleme
    public void addMedication(MedicationManagement medication) {
        medications.add(medication);
        System.out.println("Medication added: " + medication.getMedicationName());
    }

    //  Personel ekleme
    public void addStaff(Staff staffMember) {
        staff.add(staffMember);
        System.out.println("Staff added: " + staffMember.getName());
    }

    //  Yönetici ekleme
    public void addAdministrator(Administrator administrator) {
        administrators.add(administrator);
        System.out.println("Administrator added: " + administrator.getUsername());
    }

    //  Hastaları listeleme
    public void listPatients() {
        System.out.println("Listing all patients:");
        for (Patient patient : patients) {
            patient.displayPatientInfo();
            System.out.println("--------------------");
        }
    }

    //  Doktorları listeleme
    public void listDoctors() {
        System.out.println("Listing all doctors:");
        for (Doctor doctor : doctors) {
            doctor.displayDoctorInfo();
            System.out.println("--------------------");
        }
    }

    //  Randevuları listeleme
    public void listAppointments() {
        System.out.println("Listing all appointments:");
        for (Appointment appointment : appointments) {
            appointment.displayAppointmentInfo();
            System.out.println("--------------------");
        }
    }

    //  Tedavileri listeleme
    public void listTreatments() {
        System.out.println("Listing all treatments:");
        for (Treatment treatment : treatments) {
            treatment.displayTreatmentInfo();
            System.out.println("--------------------");
        }
    }

    //  İlaçları listeleme
    public void listMedications() {
        System.out.println("Listing all medications:");
        for (MedicationManagement medication : medications) {
            medication.displayMedicationInfo();
            System.out.println("--------------------");
        }
    }

    //  Personeli listeleme
    public void listStaff() {
        System.out.println("Listing all staff members:");
        for (Staff staffMember : staff) {
            staffMember.displayStaffInfo();
            System.out.println("--------------------");
        }
    }

 // Görev atama metodu
    public boolean assignTaskToStaff(String staffId, String task) {
        for (Staff staffMember : staff) {
            if (staffMember.getId().equals(staffId)) {
                // Görev atama işlemi yapılır
                System.out.println("Task assigned to " + staffMember.getName() + ": " + task);
                return true; // Başarıyla görevi atama
            }
        }
        System.out.println("Staff member not found.");
        return false; // Görev atama başarısız
    }


    // Acil durum yönetimi metodu
    public void handleEmergency(Patient emergencyPatient) {
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization().equalsIgnoreCase("Emergency")) {
                System.out.println("Emergency patient " + emergencyPatient.getName() +
                                   " assigned to Dr. " + doctor.getName());
                return;
            }
        }
        System.out.println("No available emergency doctor found.");
    }

    //  Veriyi yedekleme
    public void backupData(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Data backup completed successfully!");
        } catch (IOException e) {
            System.out.println("Backup failed: " + e.getMessage());
        }
    }

    //  Veriyi geri yükleme
    public static Database restoreData(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Database) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Restore failed: " + e.getMessage());
            return new Database();
        }
    }

    //  Get metotları (Arama için)
    public Patient getPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) return patient;
        }
        return null;
    }

    public Doctor getDoctorById(String id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) return doctor;
        }
        return null;
    }

    // Yönetici ile giriş yapma
    public Administrator loginAsAdministrator(String username, String password) {
        for (Administrator admin : administrators) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return admin; // Giriş başarılı
            }
        }
        return null; // Yanlış giriş
    }

    // Veritabanındaki yöneticiyi alma (username ile)
    public Administrator getAdministratorByUsername(String username) {
        for (Administrator admin : administrators) {
            if (admin.getUsername().equals(username)) {
                return admin;  // Yönetici bulundu
            }
        }
        return null; // Yönetici bulunamazsa null döner
    }
}
