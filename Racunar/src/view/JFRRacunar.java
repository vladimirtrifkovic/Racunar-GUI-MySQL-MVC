package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import control.Kontrol;
import klasa.Racunar;
import model.JTableRacunar;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class JFRRacunar extends JFrame {

	private JPanel contentPane;
	private JTextField txtCena;
	private JTextField txtUnosVrijednostiZaSortiranje;
	private JTable table;
	private JRadioButton rdbtnNov = new JRadioButton("Nov");
	private JRadioButton rdbtnPolovan = new JRadioButton("Polovan");
	private ButtonGroup grupa;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFRRacunar frame = new JFRRacunar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFRRacunar() {
		grupa = new ButtonGroup();
		grupa.add(rdbtnNov);
		grupa.add(rdbtnPolovan);
		rdbtnNov.setSelected(true);

		setTitle("Unos i ispis racunara");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 961, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBorder(new TitledBorder(null, "Racunari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 289, 227);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblVrsta = new JLabel("Vrsta:");
		lblVrsta.setBounds(22, 30, 45, 13);
		panel.add(lblVrsta);

		JComboBox comboBoxVrsta = new JComboBox();
		comboBoxVrsta.setModel(new DefaultComboBoxModel(new String[] { "Desktop", "Laptop" }));
		comboBoxVrsta.setBounds(84, 26, 165, 21);
		panel.add(comboBoxVrsta);

		JLabel lblCena = new JLabel("Cena:");
		lblCena.setBounds(22, 70, 45, 13);
		panel.add(lblCena);

		txtCena = new JTextField();
		txtCena.setBounds(84, 67, 165, 19);
		panel.add(txtCena);
		txtCena.setColumns(10);

		rdbtnNov.setBounds(22, 117, 103, 21);
		panel.add(rdbtnNov);

		rdbtnPolovan.setBounds(22, 150, 103, 21);
		panel.add(rdbtnPolovan);

		JButton btnUnos = new JButton("Unos");
		btnUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Racunar> lista = new ArrayList<Racunar>();
					String vrsta = comboBoxVrsta.getSelectedItem().toString();
					String cenaS = txtCena.getText().trim();
					boolean nov = rdbtnNov.isSelected();
					double cena = Double.parseDouble(cenaS);

					Racunar r = new Racunar(0, vrsta, cena, nov);
//					lista.add(r);

					Kontrol.getInstanceOf().unosRacunara(r);
					JTableRacunar model = new JTableRacunar(Kontrol.getInstanceOf().listaRastuca());
					table.setModel(model);
					JOptionPane.showMessageDialog(getContentPane(), "Uspesno ste unjeli racunar");

					txtCena.setText("");

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(getContentPane(), "GRESKA " + e2.getMessage());
				}
			}
		});

		btnUnos.setBounds(147, 180, 85, 21);
		panel.add(btnUnos);

		JComboBox comboxSortirajOpadajuceRastuce = new JComboBox();
		comboxSortirajOpadajuceRastuce.setModel(new DefaultComboBoxModel(new String[] { "Opadajuce", "Rastuce" }));
		comboxSortirajOpadajuceRastuce.setBounds(22, 303, 127, 21);
		contentPane.add(comboxSortirajOpadajuceRastuce);

		JButton btnPrikaziSortirano = new JButton("Prikazi sortirano");
		btnPrikaziSortirano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTableRacunar model;
				try {
					String sortiranje = comboxSortirajOpadajuceRastuce.getSelectedItem().toString();
					if (sortiranje.equalsIgnoreCase("opadajuce")) {
						model = new JTableRacunar(Kontrol.getInstanceOf().listaOpadajuca());
						table.setModel(model);
					} else {
						model = new JTableRacunar(Kontrol.getInstanceOf().listaRastuca());
						table.setModel(model);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(getContentPane(), "GRESKA " + e2.getMessage());
				}
			}
		});

		btnPrikaziSortirano.setBounds(176, 303, 149, 21);
		contentPane.add(btnPrikaziSortirano);

		txtUnosVrijednostiZaSortiranje = new JTextField();
		txtUnosVrijednostiZaSortiranje.setBounds(22, 355, 127, 19);
		contentPane.add(txtUnosVrijednostiZaSortiranje);
		txtUnosVrijednostiZaSortiranje.setColumns(10);

		JButton btnPrikaziSkuplje = new JButton("Prikazi skuplje");
		btnPrikaziSkuplje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String iznosS = txtUnosVrijednostiZaSortiranje.getText().trim();
					int iznos = Integer.parseInt(iznosS);
					JTableRacunar model = new JTableRacunar(
					Kontrol.getInstanceOf().listaRacunaraKojiSuSkupljiOd(iznos));
					table.setModel(model);
					txtUnosVrijednostiZaSortiranje.setText("");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(getContentPane(), "GRESKA " + e2.getMessage());
				}
			}
		});

		btnPrikaziSkuplje.setBounds(176, 354, 149, 21);
		contentPane.add(btnPrikaziSkuplje);

		JList listaPolovnoNovo = new JList();
		listaPolovnoNovo.setModel(new AbstractListModel() {
			String[] values = new String[] { "Polovno", "Novo" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listaPolovnoNovo.setBounds(22, 417, 127, 38);
		contentPane.add(listaPolovnoNovo);

		JButton btnPrikaziPolovanNov = new JButton("Prikazi");
		btnPrikaziPolovanNov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTableRacunar model;
				try {
					int index = listaPolovnoNovo.getSelectedIndex();
//					System.out.println("Index selected = " + index);
					String s = (String) listaPolovnoNovo.getSelectedValue();

					if (s.equalsIgnoreCase("polovno")) {
						model = new JTableRacunar(Kontrol.getInstanceOf().listaPolovnihRacunara());
						table.setModel(model);
					}

					if (s.equalsIgnoreCase("novo")) {
						model = new JTableRacunar(Kontrol.getInstanceOf().listaNovihRacunara());
						table.setModel(model);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(getContentPane(),
							"MORATE SELEKTOVATI POLOVNO/NOVO " + e2.getMessage());
				}
			}
		});

		btnPrikaziPolovanNov.setBounds(176, 414, 85, 21);
		contentPane.add(btnPrikaziPolovanNov);

		JButton btnNewButton = new JButton("Obrisi selektovano");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTableRacunar model = (JTableRacunar) table.getModel();
				int selekotvaniRed = table.getSelectedRow();
//				System.out.println("Selektovani red " + selekotvaniRed);
				
				if (selekotvaniRed != -1) {
					int id = model.getLista().get(selekotvaniRed).getId();
					Kontrol.getInstanceOf().obrisiRacunar(id);

					model.getLista().remove(selekotvaniRed);
					JTableRacunar model1 = new JTableRacunar(Kontrol.getInstanceOf().vratiListuRacunara());
					table.setModel(model1);
				} else {
					JOptionPane.showMessageDialog(null, "Morate selektovati red");
				}
			}
		});

		btnNewButton.setBounds(82, 478, 179, 21);
		contentPane.add(btnNewButton);

		JButton btnResetuj = new JButton("Resetuj");
		btnResetuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTableRacunar model = new JTableRacunar(Kontrol.getInstanceOf().vratiListuRacunara());
				table.setModel(model);
			}
		});
		
		btnResetuj.setBounds(123, 523, 119, 21);
		contentPane.add(btnResetuj);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(348, 23, 549, 527);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id ", "Vrsta", "Cena", "Nov" }));
		scrollPane.setViewportView(table);
		JTableRacunar model = new JTableRacunar(Kontrol.getInstanceOf().listaRastuca());
		table.setModel(model);
	}
}
