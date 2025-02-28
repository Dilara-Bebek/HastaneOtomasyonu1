package Hastane;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient {
    private String id; // Hasta kimlik numarası
    private String name; // Hasta adı
    private List<MedicalHistoryRecord> medicalHistory; // Sağlık geçmişi
    private List<TreatmentMethod> treatmentMethods; // Tedavi yöntemleri
    private List<Prescription> prescriptions; // Reçeteler
    private int age; // Hasta yaşı
    private String phone; // Hasta telefonu
    private String address; // Hasta adresi

    // Constructor
    public Patient(String id, String name, int age, String phone, String address) {
        this.id = id;
        this.name = name;
        this.medicalHistory = new ArrayList<>();
        this.treatmentMethods = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.age = age;
        this.phone = phone;
        this.address = address;
    }

    // Getter ve Setter metotları
    
    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
    
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

    public List<MedicalHistoryRecord> getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(List<MedicalHistoryRecord> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public List<TreatmentMethod> getTreatmentMethods() {
        return treatmentMethods;
    }

    public void setTreatmentMethods(List<TreatmentMethod> treatmentMethods) {
        this.treatmentMethods = treatmentMethods;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    // Sağlık geçmişi ekleme
    public void addMedicalHistory(String description, Date date) {
        medicalHistory.add(new MedicalHistoryRecord(description, date));
    }

    // Tedavi yöntemi ekleme
    public void addTreatmentMethod(String method, Date startDate, Date endDate) {
        treatmentMethods.add(new TreatmentMethod(method, startDate, endDate));
    }

    // Reçete ekleme
    public void addPrescription(String medicationName, String dosage, String usageInstructions) {
        prescriptions.add(new Prescription(medicationName, dosage, usageInstructions));
    }

    // Sağlık geçmişini görüntüleme
    public void displayMedicalHistory() {
        System.out.println("Medical History for " + name + ":");
        if (medicalHistory.isEmpty()) {
            System.out.println("No medical history available.");
        } else {
            for (MedicalHistoryRecord record : medicalHistory) {
                System.out.println("Description: " + record.getDescription() + ", Date: " + record.getDate());
            }
        }
    }

    // Tedavi yöntemlerini görüntüleme
    public void displayTreatmentMethods() {
        System.out.println("Treatment Methods for " + name + ":");
        if (treatmentMethods.isEmpty()) {
            System.out.println("No treatment methods available.");
        } else {
            for (TreatmentMethod method : treatmentMethods) {
                System.out.println("Method: " + method.getMethod() + ", Start Date: " + method.getStartDate() + ", End Date: " + method.getEndDate());
            }
        }
    }

    // Reçeteleri görüntüleme
    public void displayPrescriptions() {
        System.out.println("Prescriptions for " + name + ":");
        if (prescriptions.isEmpty()) {
            System.out.println("No prescriptions available.");
        } else {
            for (Prescription prescription : prescriptions) {
                System.out.println("Medication: " + prescription.getMedicationName() + ", Dosage: " + prescription.getDosage() + ", Instructions: " + prescription.getUsageInstructions());
            }
        }
    }

    // İç sınıflar
    private class MedicalHistoryRecord {
        private String description;
        private Date date;  // Sağlık geçmişinin tarihi

        public MedicalHistoryRecord(String description, Date date) {
            this.description = description;
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public Date getDate() {
            return date;
        }
    }

    private class TreatmentMethod {
        private String method;
        private Date startDate;
        private Date endDate;

        public TreatmentMethod(String method, Date startDate, Date endDate) {
            this.method = method;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public String getMethod() {
            return method;
        }

        public Date getStartDate() {
            return startDate;
        }

        public Date getEndDate() {
            return endDate;
        }
    }

    private class Prescription {
        private String medicationName;
        private String dosage;
        private String usageInstructions;

        public Prescription(String medicationName, String dosage, String usageInstructions) {
            this.medicationName = medicationName;
            this.dosage = dosage;
            this.usageInstructions = usageInstructions;
        }

        public String getMedicationName() {
            return medicationName;
        }

        public String getDosage() {
            return dosage;
        }

        public String getUsageInstructions() {
            return usageInstructions;
        }
    }

	public void displayPatientInfo() {
		// TODO Auto-generated method stub
		
	}
}
