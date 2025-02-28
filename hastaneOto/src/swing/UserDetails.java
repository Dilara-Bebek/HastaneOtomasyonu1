package swing;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDetails implements Serializable {
    private String tcNumber;
    private String password;
    private String motherName;
    private String fatherName;
    private String idSerial;
    private String userType;
    private int age;
    private String specialty;
    private String phoneNumber;
    private String email;
    private String address;
    private ArrayList<String> diagnosisHistory;
    private ArrayList<String> appointments;
    private ArrayList<String> prescriptions;

    // Normal hasta kullanıcıları için
    public UserDetails(String tcNumber, String password, String motherName, String fatherName, String idSerial,
                       String userType, int age) {
        this.tcNumber = tcNumber;
        this.password = password;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.idSerial = idSerial;
        this.userType = userType;
        this.age = age;
        this.diagnosisHistory = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    // Doktorlar için
    public UserDetails(String tcNumber, String password, String motherName, String fatherName, String idSerial,
                       String userType, int age, String specialty, String phoneNumber, String email, String address) {
        this.tcNumber = tcNumber;
        this.password = password;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.idSerial = idSerial;
        this.userType = userType;
        this.age = age;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.diagnosisHistory = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    // Getter ve Setter metodları
    public String getTcNumber() {
        return tcNumber;
    }

    public void setTcNumber(String tcNumber) {
        this.tcNumber = tcNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getIdSerial() {
        return idSerial;
    }

    public void setIdSerial(String idSerial) {
        this.idSerial = idSerial;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getDiagnosisHistory() {
        return diagnosisHistory;
    }

    public void setDiagnosisHistory(ArrayList<String> diagnosisHistory) {
        this.diagnosisHistory = diagnosisHistory;
    }

    public ArrayList<String> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<String> appointments) {
        this.appointments = appointments;
    }

    public ArrayList<String> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(ArrayList<String> prescriptions) {
        this.prescriptions = prescriptions;
    }

    // Yeni bir teşhis ekleme
    public void addDiagnosis(String diagnosis) {
        this.diagnosisHistory.add(diagnosis);
    }

    // Yeni bir randevu ekleme
    public void addAppointment(String appointment) {
        this.appointments.add(appointment);
    }

    // Yeni bir reçete ekleme
    public void addPrescription(String prescription) {
        this.prescriptions.add(prescription);
    }

    // Tam adın alınması
    public String getFullName() {
        return this.motherName + " " + this.fatherName;
    }

    // Teşhislerin birleştirilmesi
    public String getDiagnoses() {
        return String.join(", ", this.diagnosisHistory);
    }

    // Reçetelerin birleştirilmesi
    public String getPrescriptions1() {
        return String.join(", ", this.prescriptions);
    }
}
