package control;

import java.sql.SQLException;
import java.util.ArrayList;
import klasa.Racunar;
import model.RadSaBazom;

public class Kontrol {
	private static Kontrol kont;
	
	
	private Kontrol() {	}
	
	
	public static Kontrol getInstanceOf() {
		if(kont == null) {
			kont = new Kontrol();
		}
		return kont;
	}
	
	
	public ArrayList<Racunar> vratiListuRacunara(){
		try {
			return RadSaBazom.getInstanceOf().vratiListuRacunara();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void unosRacunara(Racunar r) {
		try {
			RadSaBazom.getInstanceOf().unosRacunaraUBazu(r);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Racunar> listaOpadajuca(){
		try {
			return RadSaBazom.getInstanceOf().vratiListuRacunaraOpadajuce();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Racunar> listaRastuca(){
		try {
			return RadSaBazom.getInstanceOf().vratiListuRacunaraRastuce();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Racunar> listaRacunaraKojiSuSkupljiOd(int iznos){
		try {
			return RadSaBazom.getInstanceOf().vratiListuRacunaraKojiSuSkupljiOd(iznos);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Racunar> listaNovihRacunara(){
		try {
			return RadSaBazom.getInstanceOf().vratiListuNovihRacunara();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Racunar> listaPolovnihRacunara(){
		try {
			return RadSaBazom.getInstanceOf().vratiListuPolovnihRacunara();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void obrisiRacunar(int id) {
		try {
			RadSaBazom.getInstanceOf().brisanjeRacunaraIzBaze(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
