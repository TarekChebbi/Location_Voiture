package louerv;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class Voiture extends JFrame {
	Connectage cn=new Connectage();
	Statement st;
	ResultSet rst;
	JLabel lbtitre,lbmatri,lbnom,lbprix;
	JTextField tfmatri,tfnom,tfprix;
	JButton bajt,bsupp,blocation;
	JTable tb1;
	JScrollPane scrl1;
	public Voiture(){
		this.setTitle("chcode_appli");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		
		JPanel pn=new JPanel();
		pn.setLayout(null);
		pn.setBackground(new Color(150,185,240));
		add(pn);
		
		lbtitre=new JLabel("Enregistrement des voitures");
		lbtitre.setBounds(110,20,600,30);
		lbtitre.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre);
		
		lbmatri=new JLabel("Matricule voiture");
		lbmatri.setBounds(80,80,130,25);
		lbmatri.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbmatri);
		
		tfmatri=new JTextField();
		tfmatri.setBounds(220,80,150,25);
		pn.add(tfmatri);
		
		lbnom=new JLabel("Nom voiture");
		lbnom.setBounds(110,120,150,25);
		lbnom.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbnom);
		
		tfnom=new JTextField();
		tfnom.setBounds(220,120,150,25);
		pn.add(tfnom);
		
		
		
		lbprix=new JLabel("Prix location par jour");
		lbprix.setBounds(27,160,200,25);
		lbprix.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbprix);
		
		tfprix=new JTextField();
		tfprix.setBounds(220,160,150,25);
		pn.add(tfprix);
		//boutons
		//bouton enregistrement locations voitures
				blocation=new JButton("Location");
				blocation.setBounds(530,20,100,25);
				blocation.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dispose();
						Location lt=new Location();
						lt.setVisible(true);
						
					}
				});
				pn.add(blocation);
		//ajout
		bajt=new JButton("AJOUT");
		bajt.setBounds(130,230,100,25);
		bajt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		   String a=tfmatri.getText(),b=tfnom.getText(),c=tfprix.getText();
				String qq="insert into tbvoiture(matricule,nom,prix_locat,disponible)"
						+ "values('"+a+"','"+b+"','"+c+"','oui')";
				try{
					st=cn.laconnexion().createStatement();
				st.executeUpdate(qq);
				JOptionPane.showMessageDialog(null,"Enregistrement reussi!");
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Impossible d'enregistrer!",null,JOptionPane.ERROR_MESSAGE);
				}
				dispose();
				Voiture vr=new Voiture();
				vr.setVisible(true);
				
			}
		});
		pn.add(bajt);
		//suppression
		bsupp=new JButton("SUPPRESSION");
		bsupp.setBounds(270,230,130,25);
		bsupp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a=tfmatri.getText();
		
				String qq="delete from tbvoiture where matricule='"+a+"'";
				try{
					st=cn.laconnexion().createStatement();
				if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){	
				st.executeUpdate(qq);
				JOptionPane.showMessageDialog(null,"Suppression effectuee!");
				}
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Impossible de supprimer!",null,JOptionPane.ERROR_MESSAGE);
				}
				dispose();
				Voiture eg=new Voiture();
				eg.setVisible(true);
				
			}
		});
		pn.add(bsupp);
		////
		DefaultTableModel df=new DefaultTableModel();
		init();
		df.addColumn("Numero matricule ");
		df.addColumn("Nom voiture");
		df.addColumn("Prix location par jour");
		
		tb1.setModel(df);
		pn.add(scrl1);
		
		String sql="select * from tbvoiture";
		
	 cn=new Connectage();
		try{
			st=cn.laconnexion().createStatement();
			rst=st.executeQuery(sql);
			while(rst.next()){
		df.addRow(new Object[]{
				rst.getString("matricule"),
				rst.getString("nom"),
				rst.getString("prix_locat")
		});
			}
		}
		catch(SQLException ex){
			
		}
		///
	}
	private void init(){
		tb1=new JTable();
		scrl1=new JScrollPane();
		scrl1.setViewportView(tb1);
		//scrl1.setBounds(20,290,540,160);
		scrl1.setBounds(20,300,740,250);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Voiture vt=new Voiture();
		vt.setVisible(true);

	}

}
