package swing;

import java.util.List;

import Hastane.Patient;

public class AppointmentViewer {
    private List<Appointment> appointments; // Randevuların listesi

    // Constructor: AppointmentViewer sınıfının bir örneği oluşturulduğunda, randevular listesi alınır
    public AppointmentViewer(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    // Randevuları görüntüleme
    public void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments available."); // Eğer randevu yoksa, bu mesaj yazdırılır
        } else {
        	// Randevular liste halinde yazdırılır
            for (Appointment appointment : appointments) {
                System.out.println("Appointment ID: " + appointment.getId());
                System.out.println("Patient: " + appointment.getPatient().getName());
                System.out.println("Doctor: " + appointment.getDoctor().getName());
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Status: " + appointment.getStatus());
                System.out.println("---------------------------------------------------");
            }
        }
    }

    // Randevu ekleme
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        System.out.println("Appointment added successfully.");
    }

    // Randevu iptal etme
    public void cancelAppointment(String appointmentId) {
        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment != null) {
            appointments.remove(appointment);
            System.out.println("Appointment cancelled successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    // Randevu ID'sine göre arama
    private Appointment findAppointmentById(String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getId().equals(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }

    // İç Sınıf - Randevu Modeli
    public static class Appointment {
        private String id;
        private Patient patient;
        private Doctor doctor;
        private String date;
        private String status;

        public Appointment(String id, Patient patient, Doctor doctor, String date, String status) {
            this.id = id;
            this.patient = patient;
            this.doctor = doctor;
            this.date = date;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public Patient getPatient() {
            return patient;
        }

        public Doctor getDoctor() {
            return doctor;
        }

        public String getDate() {
            return date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    // İç Sınıf - Doktor Modeli
    public static class Doctor {
        private String name;

        public Doctor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

	public void open() {
		// TODO Auto-generated method stub
		
	}
}
