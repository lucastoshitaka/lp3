/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GUI.GUI_Aluno;
import GUI.GUI_Disciplinas;
import GUI.GUI_Estado;
import GUI.GUI_Modalidades;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author lucastoshitaka
 */
public class GUI_Menu extends JDialog{
    
    private Container cp;
    private JPanel pnNorte = new JPanel(new GridLayout(1,5));
    private JLabel placa = new JLabel("Escolha: ");
    private JButton btAluno= new JButton("Aluno");
    private JButton btDisciplinas= new JButton("Disciplinas");
    private JButton btEstado= new JButton("Estado");
    private JButton btModalidades= new JButton("Modalidades");
    
    
    public GUI_Menu(){
    setSize(800, 200);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Menu");
        cp.add(pnNorte);
        pnNorte.add(placa);
        pnNorte.add(btAluno);
        pnNorte.add(btDisciplinas);
        pnNorte.add(btEstado);
        pnNorte.add(btModalidades);
        
        btAluno.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            try {
                GUI_Aluno guiAluno= new GUI_Aluno();
            } catch (ParseException ex) {
                Logger.getLogger(GUI_Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    });
         btDisciplinas.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GUI_Disciplinas guiDisciplinas= new GUI_Disciplinas();
        }
    });
         
          btEstado.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GUI_Estado guiEstado= new GUI_Estado();
        }
    });
          
          btModalidades.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GUI_Modalidades guiModalidades= new GUI_Modalidades();
        }
    });
          
          addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            System.exit(0);            }
        });
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
}
}