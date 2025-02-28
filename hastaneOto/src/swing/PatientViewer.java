package swing;

import Hastane.Patient;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PatientViewer {

    public PatientViewer(List<Patient> patients) {
		// TODO Auto-generated constructor stub
	}

	public static void open(List<Patient> patients) {
        // Frame oluşturuluyor
        JFrame frame = new JFrame("Hasta Görüntüleyici");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // JTable için model oluşturuluyor
        PatientTableModel tableModel = new PatientTableModel(patients);
        JTable table = new JTable(tableModel);

        // Table'ı scroll pane içine ekliyoruz
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Pencereyi gösteriyoruz
        frame.setVisible(true);
    }
}
