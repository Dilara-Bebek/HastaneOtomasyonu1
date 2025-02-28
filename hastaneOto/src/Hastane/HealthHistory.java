package Hastane;

import java.util.ArrayList;
import java.util.List;

public class HealthHistory {
    private List<String> diseases; // Geçmişteki hastalıklar
    private List<String> treatments; // Yapılan tedaviler
    private List<String> medications; // Kullanılan ilaçlar

    // Constructor
    public HealthHistory() {
        this.diseases = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.medications = new ArrayList<>();
    }

    // Getter ve Setter metotları
    public List<String> getDiseases() {
        return diseases;
    }

    public void addDisease(String disease) {
        diseases.add(disease);
    }

    public List<String> getTreatments() {
        return treatments;
    }

    public void addTreatment(String treatment) {
        treatments.add(treatment);
    }

    public List<String> getMedications() {
        return medications;
    }

    public void addMedication(String medication) {
        medications.add(medication);
    }

    // Sağlık geçmişini görüntüleme
    public void displayHealthHistory() {
        System.out.println("Health History:");
        if (diseases.isEmpty()) {
            System.out.println("No past diseases recorded.");
        } else {
            System.out.println("Diseases: " + String.join(", ", diseases));
        }
        if (treatments.isEmpty()) {
            System.out.println("No past treatments recorded.");
        } else {
            System.out.println("Treatments: " + String.join(", ", treatments));
        }
        if (medications.isEmpty()) {
            System.out.println("No past medications recorded.");
        } else {
            System.out.println("Medications: " + String.join(", ", medications));
        }
    }
}
