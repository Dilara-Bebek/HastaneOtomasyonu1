package Hastane;

import java.io.Serializable;
import java.util.List;

public class Treatment implements Serializable {
    private String treatmentName;
    private String description;
    private String startDate;
    private String endDate;
    private Doctor responsibleDoctor;
    private Patient patient; // Tedavi ile ilişkilendirilen hasta
    private String treatmentStatus; // Tedavi durumu (Devam ediyor, Tamamlandı, vb.)
    private List<String> testResults; // Tedavi ile ilgili test sonuçları
    private List<String> medications; // Tedaviye ait ilaçlar

    public Treatment(String treatmentName, String description, String startDate, String endDate, Doctor responsibleDoctor, Patient patient) {
        this.treatmentName = treatmentName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.responsibleDoctor = responsibleDoctor;
        this.patient = patient;
        this.treatmentStatus = "Devam Ediyor"; // Başlangıçta tedavi devam ediyor
        this.testResults = null; // Başlangıçta test sonuçları boş
        this.medications = null; // Başlangıçta ilaçlar boş
    }

    public String getTreatmentName() { return treatmentName; }
    public void setTreatmentName(String treatmentName) { this.treatmentName = treatmentName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public Doctor getResponsibleDoctor() { return responsibleDoctor; }
    public void setResponsibleDoctor(Doctor responsibleDoctor) { this.responsibleDoctor = responsibleDoctor; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public String getTreatmentStatus() { return treatmentStatus; }
    public void setTreatmentStatus(String treatmentStatus) { this.treatmentStatus = treatmentStatus; }

    public List<String> getTestResults() { return testResults; }
    public void setTestResults(List<String> testResults) { this.testResults = testResults; }

    public List<String> getMedications() { return medications; }
    public void setMedications(List<String> medications) { this.medications = medications; }

    // Tedavi bilgilerini görüntüleme
    public void displayTreatmentInfo() {
        System.out.println("Treatment Name: " + treatmentName);
        System.out.println("Description: " + description);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Responsible Doctor: " + responsibleDoctor.getName());
        System.out.println("Patient: " + patient.getName());
        System.out.println("Treatment Status: " + treatmentStatus);
        
        if (testResults != null && !testResults.isEmpty()) {
            System.out.println("Test Results:");
            for (String result : testResults) {
                System.out.println(result);
            }
        } else {
            System.out.println("No test results available.");
        }

        if (medications != null && !medications.isEmpty()) {
            System.out.println("Medications:");
            for (String medication : medications) {
                System.out.println(medication);
            }
        } else {
            System.out.println("No medications prescribed.");
        }
    }

    // Tedavi bilgilerini daha anlaşılır şekilde döndürme
    @Override
    public String toString() {
        return "Treatment [Name: " + treatmentName + ", Start Date: " + startDate + ", End Date: " + endDate + 
               ", Doctor: " + responsibleDoctor.getName() + ", Patient: " + patient.getName() + "]";
    }
}
