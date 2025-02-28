package Hastane;

import java.util.Date;

public class MedicationManagement {
    private String medicationName;
    private int stockQuantity;
    private Date expiryDate; // Son kullanma tarihi
    private String manufacturer; // İlaç üretici firma
    private String usageInstructions; // Kullanım talimatları

    public MedicationManagement(String medicationName, int stockQuantity, Date expiryDate, String manufacturer, String usageInstructions) {
        this.medicationName = medicationName;
        this.stockQuantity = stockQuantity;
        this.expiryDate = expiryDate;
        this.manufacturer = manufacturer;
        this.usageInstructions = usageInstructions;
    }

    // Getter ve Setter metotları
    public String getMedicationName() { return medicationName; }
    public void setMedicationName(String medicationName) { this.medicationName = medicationName; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getUsageInstructions() { return usageInstructions; }
    public void setUsageInstructions(String usageInstructions) { this.usageInstructions = usageInstructions; }

    // İlaçları dağıtma
    public void dispenseMedication(int quantity) {
        if (stockQuantity >= quantity) {
            stockQuantity -= quantity;
            System.out.println("Dispensed " + quantity + " units of " + medicationName);
        } else {
            System.out.println("Insufficient stock for " + medicationName);
        }
    }

    // İlaç stoklarını güncelleme
    public void restockMedication(int quantity) {
        stockQuantity += quantity;
        System.out.println("Restocked " + quantity + " units of " + medicationName);
    }

    // İlaç bilgilerini görüntüleme
    public void displayMedicationInfo() {
        System.out.println("Medication Name: " + medicationName);
        System.out.println("Stock Quantity: " + stockQuantity);
        System.out.println("Expiry Date: " + expiryDate);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Usage Instructions: " + usageInstructions);
    }

    // Son kullanma tarihi kontrolü
    public boolean isExpired() {
        Date currentDate = new Date();
        return currentDate.after(expiryDate);
    }

    // İlaç stokları kontrolü
    public boolean isStockAvailable(int requiredQuantity) {
        return stockQuantity >= requiredQuantity;
    }
}
