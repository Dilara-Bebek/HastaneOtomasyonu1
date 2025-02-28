package swing;

import Hastane.Doctor;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DoctorTableModel extends AbstractTableModel {

    private List<Doctor> doctors;
    private String[] columnNames = {"Doktor Adı", "Uzmanlık", "Telefon"};

    // Constructor
    public DoctorTableModel(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    // Satır sayısını döndürür
    @Override
    public int getRowCount() {
        return doctors.size();
    }

    // Sütun sayısını döndürür
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // Belirli bir hücrenin değerini döndürür
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Doctor doctor = doctors.get(rowIndex);
        switch (columnIndex) {
            case 0: return doctor.getName();
            case 1: return doctor.getSpecialization();
            case 2: return doctor.getPhone();
            default: return null;
        }
    }

    // Sütun adı
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}
