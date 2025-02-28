package Hastane;

public class Staff {
    private String name;
    private String id;
    private String role;
    private String phoneNumber; // Telefon numarası
    private String email; // E-posta adresi
    private String department; // Çalıştığı departman
    private String workHours; // Çalışma saatleri
    private String startDate; // Başlangıç tarihi

    public Staff(String name, String id, String role, String phoneNumber, String email, String department, String workHours, String startDate) {
        this.name = name;
        this.id = id;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.department = department;
        this.workHours = workHours;
        this.startDate = startDate;
    }

    // Getter ve Setter metotları
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    // Personel bilgilerini yazdırır
    public void displayStaffInfo() {
        System.out.println("Staff ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Role: " + role);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Department: " + department);
        System.out.println("Work Hours: " + workHours);
        System.out.println("Start Date: " + startDate);
    }
}
