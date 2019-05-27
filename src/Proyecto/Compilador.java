package Proyecto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Compilador extends JFrame implements ActionListener{
	//Declaración de variables
	File abre;
	ArbolRecur arbol;
	private static final long serialVersionUID = 1L;
	JLabel label=new JLabel("Load a file or type code");
	JTextArea txtaCodigo;
	JScrollPane scroll;
	JButton btnCompilar;
	JButton btnCargarArchivo;
	JButton btnSalir;
	Dimension dmBotones = new Dimension(140,30);

	public Compilador() {
		super("Compilador");
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//finaliza el programa cuando se da click en la X
		setLocationRelativeTo(null);
		componentes();
		setVisible(true);
	}
	
	protected void componentes() {
		JPanel pPrincipal	= new JPanel(new BorderLayout());
		JPanel pNorte		= new JPanel(new FlowLayout());
		JPanel pSur			= new JPanel(new FlowLayout());
		JPanel pCentro		= new JPanel(new GridLayout(1,1));
		JPanel pEste		= new JPanel();
		JPanel pOeste		= new JPanel();
		 
		label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
		label.setForeground(Color.blue);
		
		txtaCodigo = new JTextArea();
		
		scroll = new JScrollPane(txtaCodigo);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//t.setLineWrap(true);
		
		btnCargarArchivo = new JButton("Cargar Archivo");
		btnCargarArchivo.setPreferredSize(dmBotones);
		btnCargarArchivo.addActionListener(this);
		
		btnCompilar = new JButton("Compilar");
		btnCompilar.setPreferredSize(dmBotones);
		btnCompilar.addActionListener(this);
		
		btnSalir = new JButton("Salir");
		btnSalir.setPreferredSize(dmBotones);
		btnSalir.addActionListener(this);
		
		pNorte.add(label);
		pCentro.add(scroll);
	    pSur.add(btnCargarArchivo);
	    pSur.add(btnCompilar);
	    pSur.add(btnSalir);
		
		pPrincipal.add(pNorte,BorderLayout.NORTH);
		pPrincipal.add(pSur,BorderLayout.SOUTH);
		pPrincipal.add(pCentro,BorderLayout.CENTER);
		pPrincipal.add(pEste,BorderLayout.EAST);
		pPrincipal.add(pOeste,BorderLayout.WEST);
		
		add(pPrincipal);
		
	
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		//Si "GUARDAR" es el command que le pasamos es porque se habrá pulsado el botón nuevo_bt 
		if (actionEvent.getSource() == btnCompilar){//				label.setBounds(new Rectangle(200,50,400,21));
			System.out.println("compilado\n||-----\n"+txtaCodigo.getText()+"-----||");				//-------------------------Modificar
			if(txtaCodigo.getText().equals("")){
				setTitle("Empty text box");
				JOptionPane.showMessageDialog(null, "You got no Strings on the text field!!!","Are you crazy mf? this shit empty",JOptionPane.ERROR_MESSAGE);
			}else{
				try {
					System.out.println("aqqui");
					//Parser p=new Parser(t.getText());
					Pars p=new Pars(txtaCodigo.getText());
					if (p.daval()==true) {
						JOptionPane.showMessageDialog(null, "\tsuccessfully compiled \n"
								+ "\tthe results are in the next file\nC:/Users/BadMf/Desktop/Resultados.java","Done",JOptionPane.INFORMATION_MESSAGE);
		                @SuppressWarnings("resource")
						PrintStream DDescritor = new PrintStream("C:/Users/BadMf/Desktop/Resultados.java");
		                DDescritor.println(txtaCodigo.getText());
		                setTitle("compiled file "+abre.getName());
					}else {
						if(p.mensaje.equals("Expected boolean expression")){
							JOptionPane.showMessageDialog(null, "Critical error on token: "+p.error+"\nType: " +p.getMensaje(),"Failure",JOptionPane.ERROR_MESSAGE);
						}else if(p.mensaje.equals("Not the same type of variable")){
							JOptionPane.showMessageDialog(null, "Critical error on tokens: "+p.auxiliar+", " +p.auxiliar2+"\nType: " +p.getMensaje(),"Failure",JOptionPane.ERROR_MESSAGE);
							}else{
						JOptionPane.showMessageDialog(null, "Critical error on token: "+p.getTok()+"\nType: " +p.getMensaje(),"Failure",JOptionPane.ERROR_MESSAGE);
						}
						setTitle("I can't proccess this file "+abre.getName());
					}
				} catch (Exception e2) {			 
				}			
			}
		} 
		if (actionEvent.getSource() == btnCargarArchivo){
			abrirArchivo();
		} 
		if (actionEvent.getSource() == btnSalir){
			System.exit(0);
		}
		
	}
	
	private String abrirArchivo() {
		String aux="";   
		String texto="";
		try {
			/**llamamos el metodo que permite cargar la ventana*/
			JFileChooser file=new JFileChooser();
			file.showOpenDialog(this);
			/**abrimos el archivo seleccionado*/
			abre=file.getSelectedFile();
			setTitle("Trying to compile "+abre.getName());
			/**recorremos el archivo, lo leemos para plasmarlo
			*en el area de texto*/
			
			if(abre!=null){  
				FileReader archivos=new FileReader(abre);
				BufferedReader lee=new BufferedReader(archivos);
				while((aux=lee.readLine())!=null){
					texto+= aux+ "\n";
			        txtaCodigo.setText(texto);
				}
			    lee.close();
			}  
		} catch(Exception ex){
			JOptionPane.showMessageDialog(null,"MF there's no fucking file you dumbass",
					"Warning!!!",JOptionPane.WARNING_MESSAGE);
		}
		return texto;//El texto se almacena en el JTextArea
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,e.toString());
		} catch (InstantiationException e) {
			JOptionPane.showMessageDialog(null,e.toString());
		} catch (IllegalAccessException e) {
			JOptionPane.showMessageDialog(null,e.toString());
		} catch (UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null,e.toString());
		}
			new Compilador();
	}
}
