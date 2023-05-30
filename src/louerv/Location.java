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


public class Location extends JFrame {
	Connectage cn=new Connectage();
	Statement st;
	ResultSet rst;
	JLabel lbtitre,lbtitre2,lbtitre3,lbmatri,lbnom,lbcontact,lbduree,lbnumchf;
	JTextField tfmatri,tfnom,tfcontact,tfduree,tfnumchf;
	JButton bajt,bsupp,bvoiture,bmodif,bterm;
	JTable tb1,tb2;
	JScrollPane scrl1,scrl2;
	public Location(){
		this.setTitle("application_location_voiture");
		this.setSize(1100,600);
		this.setLocationRelativeTo(null);
		
		JPanel pn=new JPanel();
		pn.setLayout(null);
		pn.setBackground(new Color(150,185,240));
		add(pn);
		
		lbtitre=new JLabel("Liste des voitures disponibles pour location ");
		lbtitre.setBounds(630,20,600,30);
		lbtitre.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre);
		//bouton
		//bouton enregistrement voitures
		bvoiture=new JButton("Voiture");
		bvoiture.setBounds(450,20,100,25);
		bvoiture.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Voiture vf=new Voiture();
				vf.setVisible(true);
				
			}
		});
		pn.add(bvoiture);
		//titre2
		lbtitre2=new JLabel("Enregistrement des locations de voitures ");
		lbtitre2.setBounds(40,20,600,30);
		lbtitre2.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre2);
		//titre3
		lbtitre3=new JLabel("Liste des locations en cours ");
		lbtitre3.setBounds(40,270,600,30);
		lbtitre3.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre3);
		
		lbmatri=new JLabel("Matricule voiture");
		lbmatri.setBounds(80,60,130,25);
		lbmatri.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbmatri);
		
		tfmatri=new JTextField();
		tfmatri.setBounds(220,60,150,25);
		pn.add(tfmatri);
		
		lbcontact=new JLabel("Contact client ");
		lbcontact.setBounds(80,90,130,25);
		lbcontact.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbcontact);
		
		tfcontact=new JTextField();
		tfcontact.setBounds(220,90,150,25);
		pn.add(tfcontact);
		
		lbnom=new JLabel("Nom client ");
		lbnom.setBounds(100,120,130,25);
		lbnom.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbnom);
		
		tfnom=new JTextField();
		tfnom.setBounds(220,120,150,25);
		pn.add(tfnom);
		
		lbduree=new JLabel("Nombre de jours");
		lbduree.setBounds(100,150,130,25);
		lbduree.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbduree);
		
		tfduree=new JTextField();
		tfduree.setBounds(220,150,150,25);
		pn.add(tfduree);
		
		lbnumchf=new JLabel("Contact chauffeur");
		lbnumchf.setBounds(65,180,150,25);
		lbnumchf.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbnumchf);
		
		tfnumchf=new JTextField();
		tfnumchf.setBounds(220,180,150,25);
		pn.add(tfnumchf);
		//boutons
		//ajout
				bajt=new JButton("Enregistrer");
				bajt.setBounds(30,230,100,25);
				bajt.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
				   String matri=tfmatri.getText(),contact=tfcontact.getText(),numchf=tfnumchf.getText()
		,nomcl=tfnom.getText(),duree=tfduree.getText();
				   
	String qq2="update tbvoiture set disponible='non' where matricule='"+matri+"'";
	String qq="insert into tblocation(matriculev,contact_locat,nom_locat,date_locat,duree_prev,reservation,contact_chauff) "
					+ "values('"+matri+"','"+contact+"','"+nomcl+"',now(),'"+duree+"','non','"+numchf+"')";
						try{
							st=cn.laconnexion().createStatement();
						st.executeUpdate(qq);
						st.executeUpdate(qq2);
						JOptionPane.showMessageDialog(null,"Enregistrement r�ussi!");
						}
						catch(SQLException ex){
							JOptionPane.showMessageDialog(null,"Impossible d'enregistrer!",null,JOptionPane.ERROR_MESSAGE);
						}
						dispose();
						Location vr=new Location();
						vr.setVisible(true);
						
					}
				});
				pn.add(bajt);
		//modifier
				bmodif=new JButton("Modifier");
				bmodif.setBounds(150,230,100,25);
				bmodif.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
				   String matri=tfmatri.getText(),contact=tfcontact.getText(),numchf=tfnumchf.getText()
		,nomcl=tfnom.getText(),duree=tfduree.getText();
				
						/*String qq="insert intotblocation(matriculev,contact_locat,nom_locat,date_locat,duree_prev,reservation,contact_chauff) "
								+ "values('"+matri+"','"+contact+"','"+nomcl+"',now(),'"+duree+"','non','"+numchf+"')";*/
	String qq="update tblocation set contact_locat='"+contact+"',nom_locat='"+nomcl+"',"
			+ "date_locat=now(),duree_prev='"+duree+"',reservation='non',contact_chauff='"+numchf+"' where matriculev='"+matri+"'";	
				   try{
							st=cn.laconnexion().createStatement();
						st.executeUpdate(qq);
						JOptionPane.showMessageDialog(null,"Modification effectuee!");
						}
						catch(SQLException ex){
							JOptionPane.showMessageDialog(null,"Impossible de modifier!",null,JOptionPane.ERROR_MESSAGE);
						}
						dispose();
						Location vr=new Location();
						vr.setVisible(true);
						
					}
				});
				pn.add(bmodif);
		//Annuler/Terminer
				//modifier
				bterm=new JButton("Annuler/Terminer");
				bterm.setBounds(270,230,150,25);
				bterm.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
				   String matri=tfmatri.getText(),contact=tfcontact.getText(),numchf=tfnumchf.getText()
		,nomcl=tfnom.getText(),duree=tfduree.getText();
				   //delete from tblocation  where matriculev='$matricule
				   // mysqli_query($conn,"update tbvoiture set disponible='oui' where matricule='$matricule' ");
	String qq="delete from tblocation where matriculev='"+matri+"'";
	String qq2="update tbvoiture set disponible='oui' where matricule='"+matri+"'";
						try{
							st=cn.laconnexion().createStatement();
						st.executeUpdate(qq);
						st.executeUpdate(qq2);
						JOptionPane.showMessageDialog(null,"Annulation effectuee!");
						}
						catch(SQLException ex){
							JOptionPane.showMessageDialog(null,"Impossible d'annuler!",null,JOptionPane.ERROR_MESSAGE);
						}
						dispose();
						Location vr=new Location();
						vr.setVisible(true);
						
					}
				});
				pn.add(bterm);
		////
				DefaultTableModel df=new DefaultTableModel();
				init();
				df.addColumn("Numero matricule ");
				df.addColumn("Nom voiture");
				df.addColumn("Prix location par jour");
				
				tb1.setModel(df);
				pn.add(scrl1);
				//select * from tbvoiture where disponible='oui'
				String sql="select * from tbvoiture  where disponible='oui'";
				
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
				////
				DefaultTableModel df2=new DefaultTableModel();
				init2();
				df2.addColumn("Matricule vehicule");
				df2.addColumn("Contact client (e)");
				df2.addColumn("Nom client (e)");
				df2.addColumn("Nombre jours");
				df2.addColumn("Date debut");
				df2.addColumn("Contact chauffeur");
				df2.addColumn("Montant total");
				df2.addColumn("Etat location");
				
				tb2.setModel(df2);
				pn.add(scrl2);
		
				String sql2="select matriculev,contact_locat,nom_locat,duree_prev,date_locat,contact_chauff,duree_prev*prix_locat as montant,case when datediff(now(),date_locat)>=duree_prev then 'Termin�e' else 'En cours' end as etat from tblocation inner join tbvoiture on tblocation.matriculev=tbvoiture.matricule  where reservation='non'";
				
			 cn=new Connectage();
				try{
					st=cn.laconnexion().createStatement();
					rst=st.executeQuery(sql2);
					while(rst.next()){
				df2.addRow(new Object[]{
						rst.getString("matriculev"),
						rst.getString("contact_locat"),
						rst.getString("nom_locat"),
						rst.getString("duree_prev"),
						rst.getString("date_locat"),
						rst.getString("contact_chauff"),
						rst.getString("montant"),
						rst.getString("etat")
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
		scrl1.setBounds(630,60,440,190);
	}
	
	private void init2(){
		tb2=new JTable();
		scrl2=new JScrollPane();
		scrl2.setViewportView(tb2);
		scrl2.setBounds(20,310,1040,240);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  Location lt=new Location();
  lt.setVisible(true);
	}

}
