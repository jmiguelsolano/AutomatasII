package Proyecto;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Bytecode extends JFrame {
		
	public Bytecode(Vector<Inst>inst) {
		setResizable(false);
		setTitle("Portable code");
		setVisible(true);
		setSize(300,500);
		this.setBackground(Color.BLACK);
		add(ta(inst));
		setLayout(null);
		setSize(300,500);
		
		//finaliza el programa cuando se da click en la X
		//setSize(300, 300);//configurando tamaño de la ventana
		setBounds(950,100 , 300,500);
		
		repaint();
		//t.setBounds(0, 0,500,200);
	}
	
	public JScrollPane ta(Vector<Inst>inst){
		String [] columnas={"Line","Instruction"};
		DefaultTableModel modelodefmure=new DefaultTableModel();
		JTable tablamure = new JTable(modelodefmure){// se crea la JTable con vector de vectores(informacion) y un vector(encabezado)
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false; // para que no se edite las celdas de JTable
		        }
			};;
			for(int i=0;i<columnas.length;i++){
				modelodefmure.addColumn(columnas[i]);
			}
			for (int j = 0; j < inst.size(); j++) {
				if(inst.elementAt(j).equals("")||inst.elementAt(j).equals(" ")){
			
				}
				modelodefmure.addRow(new Object[] {inst.elementAt(j).ind,"\t"+inst.elementAt(j).inst+"\t"+inst.elementAt(j).var});
				
			}
			

			tablamure.setPreferredScrollableViewportSize(new Dimension(1000,400));
			tablamure.getColumnModel().getColumn(0).setPreferredWidth(40);
			tablamure.getColumnModel().getColumn(1).setPreferredWidth(240);
			JScrollPane scrollpanemure = new JScrollPane(tablamure);
			scrollpanemure.setBounds(10,10, 280, 450);
			return scrollpanemure;
			
		}
}
