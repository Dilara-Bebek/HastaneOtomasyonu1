package swing;

import java.util.List;

public class StaffViewer {
    private List<Staff> staffList; // Çalışanların listesi

    // Constructor
    public StaffViewer(List<Staff> staffList) {
        this.staffList = staffList;
    }

    // Çalışanları görüntüleme
    public void displayStaff() {
        if (staffList.isEmpty()) {
            System.out.println("No staff available.");
        } else {
            for (Staff staff : staffList) {
                System.out.println("Staff ID: " + staff.getId());
                System.out.println("Name: " + staff.getName());
                System.out.println("Role: " + staff.getRole());
                System.out.println("Phone: " + staff.getPhone());
                System.out.println("---------------------------------------------------");
            }
        }
    }

    // Yeni çalışan ekleme
    public void addStaff(Staff staff) {
        staffList.add(staff);
        System.out.println("Staff added successfully.");
    }

    // Çalışan bilgilerini düzenleme
    public void updateStaff(String staffId, String name, String role, String phone) {
        Staff staff = findStaffById(staffId);
        if (staff != null) {
            staff.setName(name);
            staff.setRole(role);
            staff.setPhone(phone);
            System.out.println("Staff updated successfully.");
        } else {
            System.out.println("Staff not found.");
        }
    }

    // Çalışan ID'sine göre arama
    private Staff findStaffById(String staffId) {
        for (Staff staff : staffList) {
            if (staff.getId().equals(staffId)) {
                return staff;
            }
        }
        return null;
    }

    // İç sınıf - Çalışan Modeli
    public static class Staff {
        private String id;
        private String name;
        private String role;
        private String phone;

        // Constructor
        public Staff(String id, String name, String role, String phone) {
            this.id = id;
            this.name = name;
            this.role = role;
            this.phone = phone;
        }

        // Getter ve Setter metodları
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

	public void open() {
		// TODO Auto-generated method stub
		
	}
}
