package GUI;

import java.awt.BorderLayout;
import DAOs.DAODisciplinas;
import Entidades.Disciplinas;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class GUI_Disciplinas extends JDialog {

    private Container cp;
    private JPanel pnNorte = new JPanel(new FlowLayout());
    private JPanel pnCentro = new JPanel(new GridLayout(0, 2));
    private JPanel pnSul = new JPanel(new FlowLayout());

    private JLabel lbIdDisciplinas = new JLabel("IdDisciplinas:");
    private JTextField tfIdDisciplinas = new JTextField(10);

    private JButton btBuscar = new JButton("Buscar");
    private JButton btInserir = new JButton("Criar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btRemover = new JButton("Remover");
    private JButton btAtualizar = new JButton("Atualizar");
    private JButton btListIdDisciplinas = new JButton("Listar IdDisciplinas");
    private JButton btListNomeDisciplinas = new JButton("Listar NomeDisciplinas");
    private JButton btListarIdDisciplinas = new JButton("Listar(por IdDisciplinas)");
    private JButton btListarNomeDisciplinas = new JButton("Listar(por NomeDisciplinas)");
    private String list = "IdDisciplinas";
    
    private JLabel lbAviso = new JLabel("Aviso");
    DAODisciplinas controle = new DAODisciplinas();
    List<Disciplinas> dados = new ArrayList<>();
    
    private boolean acao = true;
    Disciplinas entidade = new Disciplinas();

    private JLabel lbNomeDisciplinas = new JLabel("NomeDisciplinas");
    private JTextField tfNomeDisciplinas = new JTextField();

    private JDialog dialog = new JDialog();
    private JTextArea text = new JTextArea();
    private JScrollPane scroll = new JScrollPane(text);
    private Integer IdDisciplinas;
    public GUI_Disciplinas() {

        setSize(1000, 200);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Disciplinas");

        pnNorte.setBackground(Color.yellow);
        pnCentro.setBackground(Color.cyan);
        pnSul.setBackground(Color.green);

        dialog.add(scroll);
        text.setEditable(false);
        dialog.setSize(500,200);

        pnNorte.add(btListIdDisciplinas);
        pnNorte.add(btListNomeDisciplinas);
        pnNorte.add(lbIdDisciplinas);
        pnNorte.add(tfIdDisciplinas);
        pnNorte.add(btBuscar);
        pnNorte.add(btInserir);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.add(btRemover);
        pnNorte.add(btAtualizar);
        pnNorte.add(btListarIdDisciplinas);
        pnNorte.add(btListarNomeDisciplinas);
        btInserir.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btRemover.setVisible(false);
        btAtualizar.setVisible(false);

        pnSul.add(lbAviso);
        lbAviso.setOpaque(true);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);


        tfNomeDisciplinas.setEnabled(false);
        pnCentro.add(lbNomeDisciplinas);
        pnCentro.add(tfNomeDisciplinas);


        btBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    entidade = new Disciplinas();

                    IdDisciplinas = Integer.valueOf(tfIdDisciplinas.getText());

                    entidade.setIdDisciplinas(IdDisciplinas);

                    if (IdDisciplinas<= 0) {
                        int error = 3/0;
                    }
                    entidade = controle.obter(entidade.getIdDisciplinas());
                    lbAviso.setBackground(Color.green);
                    if (entidade != null) {

                        btInserir.setVisible(false);
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        lbAviso.setText("Achou na lista");

                        tfNomeDisciplinas.setText(entidade.getNomeDisciplinas());

                    } else {
                        lbAviso.setText("Não achou na lista");
                        btInserir.setVisible(true);

                        lbAviso.setBackground(Color.red);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);


                        tfNomeDisciplinas.setEnabled(false);

                    }

                } catch (Exception err) {
                    lbAviso.setText("Erro nos dados");
                    lbAviso.setBackground(Color.red);
                }

            }
        }
        );

        btInserir.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        acao = true;
                        btBuscar.setVisible(false);
                        btListarIdDisciplinas.setVisible(false);
                        btListarNomeDisciplinas.setVisible(false);
                        btListIdDisciplinas.setVisible(false);
                        btListNomeDisciplinas.setVisible(false);

                        tfIdDisciplinas.setText(String.valueOf(IdDisciplinas));
                        tfIdDisciplinas.setEnabled(false);

                        tfNomeDisciplinas.setEnabled(true);
                        tfNomeDisciplinas.setText(String.valueOf(""));

                        tfNomeDisciplinas.requestFocus();


                        btInserir.setVisible(false);
                        btSalvar.setVisible(true);
                        btCancelar.setVisible(true);
                        btRemover.setVisible(false);
                        btAtualizar.setVisible(false);
                    }
                }
        );

        btSalvar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        if (acao) {
                            try{

                            Disciplinas entidade = new Disciplinas();

                            entidade.setIdDisciplinas(IdDisciplinas);

                            entidade.setNomeDisciplinas(tfNomeDisciplinas.getText());

                            controle.inserir(entidade);

                            lbAviso.setText("Registro inserido");
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(true);
                            btListarIdDisciplinas.setVisible(true);
                            btListarNomeDisciplinas.setVisible(true);
                            btListIdDisciplinas.setVisible(true);
                            btListNomeDisciplinas.setVisible(true);
                            tfIdDisciplinas.setEnabled(true);
                            tfIdDisciplinas.requestFocus();
                            tfIdDisciplinas.selectAll();

                            tfNomeDisciplinas.setEnabled(false);

                            lbAviso.setBackground(Color.green);
                }catch (Exception btSalvar){
                    JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);}

                        } else {
                            try{
                            Disciplinas entidadeOriginal = entidade;
                            Disciplinas entidadeModificada = new Disciplinas();

                            entidadeModificada.setIdDisciplinas(IdDisciplinas);

                            entidadeModificada.setNomeDisciplinas(tfNomeDisciplinas.getText());


                            controle.atualizar(entidadeModificada);

                            lbAviso.setText("Registro alterado");
                            tfIdDisciplinas.setEnabled(true);
                            tfIdDisciplinas.requestFocus();
                            tfIdDisciplinas.selectAll();
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(true);
                            btListarIdDisciplinas.setVisible(true);
                            btListarNomeDisciplinas.setVisible(true);
                            btListIdDisciplinas.setVisible(true);
                            btListNomeDisciplinas.setVisible(true);

                            tfNomeDisciplinas.setEnabled(false);

                            lbAviso.setBackground(Color.green);
                            }catch (Exception btSalvarAtt){
                                JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);}


                        }
                    }
                }
        );

        btCancelar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        lbAviso.setText("Cancelado");
                        tfIdDisciplinas.setEnabled(true);

                        tfIdDisciplinas.requestFocus();
                        tfIdDisciplinas.selectAll();

                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListarIdDisciplinas.setVisible(true);
                        btListarNomeDisciplinas.setVisible(true);
                        btListIdDisciplinas.setVisible(true);
                        btListNomeDisciplinas.setVisible(true);
                        lbAviso.setBackground(Color.green);

                        tfNomeDisciplinas.setEnabled(false);
                        tfNomeDisciplinas.setText(String.valueOf(""));

                    }
                }
        );

        btAtualizar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        acao = false;

                        tfIdDisciplinas.setText(String.valueOf(IdDisciplinas));
                        tfIdDisciplinas.setEnabled(false);

                        tfNomeDisciplinas.setEnabled(true);

                        tfNomeDisciplinas.requestFocus();

                        btSalvar.setVisible(true);
                        btCancelar.setVisible(true);
                        btBuscar.setVisible(false);
                        btListarIdDisciplinas.setVisible(false);
                        btListarNomeDisciplinas.setVisible(false);
                        btListIdDisciplinas.setVisible(false);
                        btListNomeDisciplinas.setVisible(false);
                        btRemover.setVisible(false);
                        btAtualizar.setVisible(false);

                    }
                }
        );

        btRemover.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        btRemover.setVisible(false);
                        btAtualizar.setVisible(false);
                        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                                "Confirma a exclusão do registro <ID = " + entidade.getIdDisciplinas() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {


                        tfNomeDisciplinas.setText(String.valueOf(""));

                        tfIdDisciplinas.requestFocus();
                        tfIdDisciplinas.setEnabled(true);
                        tfIdDisciplinas.setText(String.valueOf(""));
                        controle.remover(entidade);
                        lbAviso.setText("Removeu");


                        } else {
                            lbAviso.setText("Cancelada a remoção");
                            btRemover.setVisible(true);
                            btAtualizar.setVisible(true);
                        }

                    }
                }
        );

        btListIdDisciplinas.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                list = "byIdDisciplinas";
                btListarIdDisciplinas.doClick();
            }
        });

        btListNomeDisciplinas.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                list = "byNomeDisciplinas";
                btListarIdDisciplinas.doClick();
            }
        });

        btListarNomeDisciplinas.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                list = "NomeDisciplinas";
                btListarIdDisciplinas.doClick();
            }
        });

        btListarIdDisciplinas.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                String aux[];
                text.setText("");
                dados.clear();
                    switch (list) {
                        case "IdDisciplinas":
                            dados = controle.listInOrderId();
                            break;
                        case "NomeDisciplinas":
                            dados = controle.listInOrderNome();
                            break;
                        case "byIdDisciplinas":
                            dados = controle.listById(Integer.valueOf(tfIdDisciplinas.getText()));
                            break;
                        default:
                            dados = controle.listByNome(tfIdDisciplinas.getText());
                            break;
                    }
                try{
                if (dados.get(0) == null){}
                }catch(Exception lista){
                    int erro = 3/0;                }
                for (Disciplinas linha : dados) {
                    aux = String.valueOf(linha).split(";");
                    text.append(
"IdDisciplinas: " + 
            Integer.valueOf(aux[0])
 + "\n" +
"NomeDisciplinas: " + 
            aux[1]
+ "\n-------------------------------------------------------------------------------------------\n"
);}
                dialog.setLocationRelativeTo(cp);
                dialog.setModal(true);
                dialog.setVisible(true);
                }catch(Exception Lista){
                    JOptionPane.showMessageDialog(null, "NOTHING TO SEE HERE! 8P", "OPS", JOptionPane.PLAIN_MESSAGE);
                    }finally{
                        list = "IdDisciplinas";
                    }
        }});
/*
       addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            System.exit(0);            }
        });
  */      
        tfIdDisciplinas.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            btBuscar.doClick();}});


        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }
}

