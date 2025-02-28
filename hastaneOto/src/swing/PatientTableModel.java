package swing;

import Hastane.Patient;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PatientTableModel extends AbstractTableModel {

    private List<Patient> patients;
    private String[] columnNames = {"Hasta Adı", "Yaş", "Telefon", "Adres"};

    // Constructor
    public PatientTableModel(List<Patient> patients) {
        this.patients = patients;
    }

    // Satır sayısını döndürür
    @Override
    public int getRowCount() {
        return patients.size();
    }

    // Sütun sayısını döndürür
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // Belirli bir hücrenin değerini döndürür
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Patient patient = patients.get(rowIndex);
        switch (columnIndex) {
            case 0: return patient.getName();
            case 1: return patient.getAge();
            case 2: return patient.getPhone();
            case 3: return patient.getAddress();
            default: return null;
        }
    }

    // Sütun adı
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}
