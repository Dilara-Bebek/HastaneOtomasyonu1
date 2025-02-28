package Hastane;

import java.util.Date;

public abstract class Person {
    private String id; // Kimlik numarası
    private String name; // İsim
    private Date birthDate; // Doğum tarihi (isteğe bağlı)
    private String address; // Adres (isteğe bağlı)
    private String email; // E-posta (isteğe bağlı)
    private String phoneNumber; // Telefon numarası (isteğe bağlı)
    private String gender; // Cinsiyet (isteğe bağlı)

    // Constructor (Yapıcı metot)
    public Person(String id, String name, Date birthDate, String address, String email, String phoneNumber, String gender) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Kişi bilgilerini döndürür
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Birth Date: " + birthDate + ", Address: " + address + 
               ", Email: " + email + ", Phone Number: " + phoneNumber + ", Gender: " + gender;
    }
}
