package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import control.Kontrol;
import klasa.Racunar;

public class JTableRacunar extends AbstractTableModel {
	private ArrayList<Racunar> lista;

	public JTableRacunar(ArrayList<Racunar> lista) {
		super();
		this.lista = lista;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int r, int c) {
		Racunar comp = lista.get(r);
		switch (c) {
//		case 0 : return comp.getId();
		case 0:
			return comp.getVrsta();
		case 1:
			return comp.getCena();
		case 2:
			return comp.isNov();
		default:
			return "GRESKA";
		}
	}

	@Override
	public String getColumnName(int c) {
		switch (c) {
//		case 0 : return "Id";
		case 0:
			return "Vrsta";
		case 1:
			return "Cena";
		case 2:
			return "Nov";
		default:
			return "GRESKA!";
		}
	}

	@Override
	public boolean isCellEditable(int r, int c) {
		switch (c) {
		case 0:
			return false;
		case 1:
			return true;
		case 2:
			return true;
//		case 3 : return true;
		default:
			return false;
		}
	}

	@Override
	public void setValueAt(Object aValue, int r, int c) {

		try {
			Racunar pom = lista.get(r);
			switch (c) {
			case 0:
				pom.setVrsta(aValue.toString());
				break;
			case 1:
				pom.setCena(Double.parseDouble(aValue.toString()));
				break;
			case 2:
				pom.setNov(Boolean.parseBoolean(aValue.toString()));
				break;
			}
			
			RadSaBazom.getInstanceOf().updateRacunar(pom);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Greska " + e.getMessage());
		}

		fireTableRowsUpdated(r, c);
	}

	public ArrayList<Racunar> getLista() {
		return lista;
	}
}
