package swing;

import java.io.Serializable;

public class KullaniciBilgi implements Serializable {
    private String password;
    private String tcNumber;
    private String motherName;
    private String fatherName;
    private String idSerial;
    private String userType;
    private String specialty;  // Doktorun Uzmanlık Alanı
    private int age;

    public KullaniciBilgi(String password, String tcNumber, String motherName, String fatherName,
                          String idSerial, String userType, String specialty, int age) {
        this.password = password;
        this.tcNumber = tcNumber;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.idSerial = idSerial;
        this.userType = userType;
        this.specialty = specialty;
        this.age = age;
    }

    // Getter ve Setter Metotları
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTcNumber() { return tcNumber; }
    public void setTcNumber(String tcNumber) { this.tcNumber = tcNumber; }

    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public String getIdSerial() { return idSerial; }
    public void setIdSerial(String idSerial) { this.idSerial = idSerial; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Kullanıcı Türü: " + userType +
                "\nT.C. Kimlik No: " + tcNumber +
                "\nAnne Adı: " + motherName +
                "\nBaba Adı: " + fatherName +
                "\nKimlik Seri No: " + idSerial +
                (userType != null && userType.equalsIgnoreCase("Doctor") ? "\nUzmanlık Alanı: " + specialty : "") +
                "\nYaş: " + age;
    }

    // Eğer kullanıcı adı saklanıyorsa, bu metot tanımlanabilir
    public String getName() {
        // Kullanıcı adı eklenirse, ilgili alan buradan döndürülür
        return "Kullanıcı adı henüz tanımlı değil.";
    }
}
