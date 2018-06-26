package GUI;

import java.awt.BorderLayout;
import DAOs.DAOModalidades;
import Entidades.Modalidades;
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


public class GUI_Modalidades extends JDialog {

    private Container cp;
    private JPanel pnNorte = new JPanel(new FlowLayout());
    private JPanel pnCentro = new JPanel(new GridLayout(0, 2));
    private JPanel pnSul = new JPanel(new FlowLayout());

    private JLabel lbIdModalidades = new JLabel("IdModalidades:");
    private JTextField tfIdModalidades = new JTextField(10);

    private JButton btBuscar = new JButton("Buscar");
    private JButton btInserir = new JButton("Criar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btRemover = new JButton("Remover");
    private JButton btAtualizar = new JButton("Atualizar");
    private JButton btListIdModalidades = new JButton("Listar IdModalidades");
    private JButton btListNomeModalidades = new JButton("Listar NomeModalidades");
    private JButton btListarIdModalidades = new JButton("Listar(por IdModalidades)");
    private JButton btListarNomeModalidades = new JButton("Listar(por NomeModalidades)");
    private String list = "IdModalidades";
    
    private JLabel lbAviso = new JLabel("Aviso");
    DAOModalidades controle = new DAOModalidades();
    List<Modalidades> dados = new ArrayList<>();
    
    private boolean acao = true;
    Modalidades entidade = new Modalidades();

    private JLabel lbNomeModalidades = new JLabel("NomeModalidades");
    private JTextField tfNomeModalidades = new JTextField();

    private JDialog dialog = new JDialog();
    private JTextArea text = new JTextArea();
    private JScrollPane scroll = new JScrollPane(text);
    private Integer IdModalidades;
    public GUI_Modalidades() {

        setSize(1000, 200);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Modalidades");

        pnNorte.setBackground(Color.yellow);
        pnCentro.setBackground(Color.cyan);
        pnSul.setBackground(Color.green);

        dialog.add(scroll);
        text.setEditable(false);
        dialog.setSize(500,200);

        pnNorte.add(btListIdModalidades);
        pnNorte.add(btListNomeModalidades);
        pnNorte.add(lbIdModalidades);
        pnNorte.add(tfIdModalidades);
        pnNorte.add(btBuscar);
        pnNorte.add(btInserir);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.add(btRemover);
        pnNorte.add(btAtualizar);
        pnNorte.add(btListarIdModalidades);
        pnNorte.add(btListarNomeModalidades);
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


        tfNomeModalidades.setEnabled(false);
        pnCentro.add(lbNomeModalidades);
        pnCentro.add(tfNomeModalidades);


        btBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    entidade = new Modalidades();

                    IdModalidades = Integer.valueOf(tfIdModalidades.getText());

                    entidade.setIdModalidades(IdModalidades);

                    if (IdModalidades<= 0) {
                        int error = 3/0;
                    }
                    entidade = controle.obter(entidade.getIdModalidades());
                    lbAviso.setBackground(Color.green);
                    if (entidade != null) {

                        btInserir.setVisible(false);
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        lbAviso.setText("Achou na lista");

                        tfNomeModalidades.setText(entidade.getNomeModalidades());

                    } else {
                        lbAviso.setText("Não achou na lista");
                        btInserir.setVisible(true);

                        lbAviso.setBackground(Color.red);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);


                        tfNomeModalidades.setEnabled(false);

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
                        btListarIdModalidades.setVisible(false);
                        btListarNomeModalidades.setVisible(false);
                        btListIdModalidades.setVisible(false);
                        btListNomeModalidades.setVisible(false);

                        tfIdModalidades.setText(String.valueOf(IdModalidades));
                        tfIdModalidades.setEnabled(false);

                        tfNomeModalidades.setEnabled(true);
                        tfNomeModalidades.setText(String.valueOf(""));

                        tfNomeModalidades.requestFocus();


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

                            Modalidades entidade = new Modalidades();

                            entidade.setIdModalidades(IdModalidades);

                            entidade.setNomeModalidades(tfNomeModalidades.getText());

                            controle.inserir(entidade);

                            lbAviso.setText("Registro inserido");
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(true);
                            btListarIdModalidades.setVisible(true);
                            btListarNomeModalidades.setVisible(true);
                            btListIdModalidades.setVisible(true);
                            btListNomeModalidades.setVisible(true);
                            tfIdModalidades.setEnabled(true);
                            tfIdModalidades.requestFocus();
                            tfIdModalidades.selectAll();

                            tfNomeModalidades.setEnabled(false);

                            lbAviso.setBackground(Color.green);
                }catch (Exception btSalvar){
                    JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);}

                        } else {
                            try{
                            Modalidades entidadeOriginal = entidade;
                            Modalidades entidadeModificada = new Modalidades();

                            entidadeModificada.setIdModalidades(IdModalidades);

                            entidadeModificada.setNomeModalidades(tfNomeModalidades.getText());


                            controle.atualizar(entidadeModificada);

                            lbAviso.setText("Registro alterado");
                            tfIdModalidades.setEnabled(true);
                            tfIdModalidades.requestFocus();
                            tfIdModalidades.selectAll();
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(true);
                            btListarIdModalidades.setVisible(true);
                            btListarNomeModalidades.setVisible(true);
                            btListIdModalidades.setVisible(true);
                            btListNomeModalidades.setVisible(true);

                            tfNomeModalidades.setEnabled(false);

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
                        tfIdModalidades.setEnabled(true);

                        tfIdModalidades.requestFocus();
                        tfIdModalidades.selectAll();

                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListarIdModalidades.setVisible(true);
                        btListarNomeModalidades.setVisible(true);
                        btListIdModalidades.setVisible(true);
                        btListNomeModalidades.setVisible(true);
                        lbAviso.setBackground(Color.green);

                        tfNomeModalidades.setEnabled(false);
                        tfNomeModalidades.setText(String.valueOf(""));

                    }
                }
        );

        btAtualizar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        acao = false;

                        tfIdModalidades.setText(String.valueOf(IdModalidades));
                        tfIdModalidades.setEnabled(false);

                        tfNomeModalidades.setEnabled(true);

                        tfNomeModalidades.requestFocus();

                        btSalvar.setVisible(true);
                        btCancelar.setVisible(true);
                        btBuscar.setVisible(false);
                        btListarIdModalidades.setVisible(false);
                        btListarNomeModalidades.setVisible(false);
                        btListIdModalidades.setVisible(false);
                        btListNomeModalidades.setVisible(false);
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
                                "Confirma a exclusão do registro <ID = " + entidade.getIdModalidades() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {


                        tfNomeModalidades.setText(String.valueOf(""));

                        tfIdModalidades.requestFocus();
                        tfIdModalidades.setEnabled(true);
                        tfIdModalidades.setText(String.valueOf(""));
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

        btListIdModalidades.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                list = "byIdModalidades";
                btListarIdModalidades.doClick();
            }
        });

        btListNomeModalidades.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                list = "byNomeModalidades";
                btListarIdModalidades.doClick();
            }
        });

        btListarNomeModalidades.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                list = "NomeModalidades";
                btListarIdModalidades.doClick();
            }
        });

        btListarIdModalidades.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                String aux[];
                text.setText("");
                dados.clear();
                    switch (list) {
                        case "IdModalidades":
                            dados = controle.listInOrderId();
                            break;
                        case "NomeModalidades":
                            dados = controle.listInOrderNome();
                            break;
                        case "byIdModalidades":
                            dados = controle.listById(Integer.valueOf(tfIdModalidades.getText()));
                            break;
                        default:
                            dados = controle.listByNome(tfIdModalidades.getText());
                            break;
                    }
                try{
                if (dados.get(0) == null){}
                }catch(Exception lista){
                    int erro = 3/0;                }
                for (Modalidades linha : dados) {
                    aux = String.valueOf(linha).split(";");
                    text.append(
"IdModalidades: " + 
            Integer.valueOf(aux[0])
 + "\n" +
"NomeModalidades: " + 
            aux[1]
+ "\n-------------------------------------------------------------------------------------------\n"
);}
                dialog.setLocationRelativeTo(cp);
                dialog.setModal(true);
                dialog.setVisible(true);
                }catch(Exception Lista){
                    JOptionPane.showMessageDialog(null, "NOTHING TO SEE HERE! 8P", "OPS", JOptionPane.PLAIN_MESSAGE);
                    }finally{
                        list = "IdModalidades";
                    }
        }});
/*
       addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            System.exit(0);            }
        });
        */
        tfIdModalidades.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            btBuscar.doClick();}});


        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }
}

