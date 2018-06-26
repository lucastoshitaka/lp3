package GUI;

import java.awt.BorderLayout;
import DAOs.DAOEstado;
import Entidades.Estado;
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


public class GUI_Estado extends JDialog {

    private Container cp;
    private JPanel pnNorte = new JPanel(new FlowLayout());
    private JPanel pnCentro = new JPanel(new GridLayout(0, 2));
    private JPanel pnSul = new JPanel(new FlowLayout());

    private JLabel lbSiglaEstado = new JLabel("SiglaEstado:");
    private JTextField tfSiglaEstado = new JTextField(10);

    private JButton btBuscar = new JButton("Buscar");
    private JButton btInserir = new JButton("Criar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btRemover = new JButton("Remover");
    private JButton btAtualizar = new JButton("Atualizar");
    private JButton btListSiglaEstado = new JButton("Listar SiglaEstado");
    private JButton btListNomeEstado = new JButton("Listar NomeEstado");
    private JButton btListarSiglaEstado = new JButton("Listar(por SiglaEstado)");
    private JButton btListarNomeEstado = new JButton("Listar(por NomeEstado)");
    private String list = "SiglaEstado";
    
    private JLabel lbAviso = new JLabel("Aviso");
    DAOEstado controle = new DAOEstado();
    List<Estado> dados = new ArrayList<>();
    
    private boolean acao = true;
    Estado entidade = new Estado();

    private JLabel lbNomeEstado = new JLabel("NomeEstado");
    private JTextField tfNomeEstado = new JTextField();

    private JDialog dialog = new JDialog();
    private JTextArea text = new JTextArea();
    private JScrollPane scroll = new JScrollPane(text);
    private String SiglaEstado;
    public GUI_Estado() {

        setSize(1000, 200);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Estado");

        pnNorte.setBackground(Color.yellow);
        pnCentro.setBackground(Color.cyan);
        pnSul.setBackground(Color.green);

        dialog.add(scroll);
        text.setEditable(false);
        dialog.setSize(500,200);

        pnNorte.add(btListSiglaEstado);
        pnNorte.add(btListNomeEstado);
        pnNorte.add(lbSiglaEstado);
        pnNorte.add(tfSiglaEstado);
        pnNorte.add(btBuscar);
        pnNorte.add(btInserir);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.add(btRemover);
        pnNorte.add(btAtualizar);
        pnNorte.add(btListarSiglaEstado);
        pnNorte.add(btListarNomeEstado);
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


        tfNomeEstado.setEnabled(false);
        pnCentro.add(lbNomeEstado);
        pnCentro.add(tfNomeEstado);


        btBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    entidade = new Estado();

                    SiglaEstado = tfSiglaEstado.getText();

                    entidade.setSiglaEstado(SiglaEstado);

                    if (SiglaEstado.isEmpty()) {
                        int error = 3/0;
                    }
                    entidade = controle.obter(entidade.getSiglaEstado());
                    lbAviso.setBackground(Color.green);
                    if (entidade != null) {

                        btInserir.setVisible(false);
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        lbAviso.setText("Achou na lista");

                        tfNomeEstado.setText(entidade.getNomeEstado());

                    } else {
                        lbAviso.setText("Não achou na lista");
                        btInserir.setVisible(true);

                        lbAviso.setBackground(Color.red);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);


                        tfNomeEstado.setEnabled(false);

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
                        btListarSiglaEstado.setVisible(false);
                        btListarNomeEstado.setVisible(false);
                        btListSiglaEstado.setVisible(false);
                        btListNomeEstado.setVisible(false);

                        tfSiglaEstado.setText(SiglaEstado);
                        tfSiglaEstado.setEnabled(false);

                        tfNomeEstado.setEnabled(true);
                        tfNomeEstado.setText(String.valueOf(""));

                        tfNomeEstado.requestFocus();


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

                            Estado entidade = new Estado();

                            entidade.setSiglaEstado(SiglaEstado);

                            entidade.setNomeEstado(tfNomeEstado.getText());

                            controle.inserir(entidade);

                            lbAviso.setText("Registro inserido");
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(true);
                            btListarSiglaEstado.setVisible(true);
                            btListarNomeEstado.setVisible(true);
                            btListSiglaEstado.setVisible(true);
                            btListNomeEstado.setVisible(true);
                            tfSiglaEstado.setEnabled(true);
                            tfSiglaEstado.requestFocus();
                            tfSiglaEstado.selectAll();

                            tfNomeEstado.setEnabled(false);

                            lbAviso.setBackground(Color.green);
                }catch (Exception btSalvar){
                    JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);}

                        } else {
                            try{
                            Estado entidadeOriginal = entidade;
                            Estado entidadeModificada = new Estado();

                            entidadeModificada.setSiglaEstado(SiglaEstado);

                            entidadeModificada.setNomeEstado(tfNomeEstado.getText());


                            controle.atualizar(entidadeModificada);

                            lbAviso.setText("Registro alterado");
                            tfSiglaEstado.setEnabled(true);
                            tfSiglaEstado.requestFocus();
                            tfSiglaEstado.selectAll();
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(true);
                            btListarSiglaEstado.setVisible(true);
                            btListarNomeEstado.setVisible(true);
                            btListSiglaEstado.setVisible(true);
                            btListNomeEstado.setVisible(true);

                            tfNomeEstado.setEnabled(false);

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
                        tfSiglaEstado.setEnabled(true);

                        tfSiglaEstado.requestFocus();
                        tfSiglaEstado.selectAll();

                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListarSiglaEstado.setVisible(true);
                        btListarNomeEstado.setVisible(true);
                        btListSiglaEstado.setVisible(true);
                        btListNomeEstado.setVisible(true);
                        lbAviso.setBackground(Color.green);

                        tfNomeEstado.setEnabled(false);
                        tfNomeEstado.setText(String.valueOf(""));

                    }
                }
        );

        btAtualizar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        acao = false;

                        tfSiglaEstado.setText(SiglaEstado);
                        tfSiglaEstado.setEnabled(false);

                        tfNomeEstado.setEnabled(true);

                        tfNomeEstado.requestFocus();

                        btSalvar.setVisible(true);
                        btCancelar.setVisible(true);
                        btBuscar.setVisible(false);
                        btListarSiglaEstado.setVisible(false);
                        btListarNomeEstado.setVisible(false);
                        btListSiglaEstado.setVisible(false);
                        btListNomeEstado.setVisible(false);
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
                                "Confirma a exclusão do registro <ID = " + entidade.getSiglaEstado() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {


                        tfNomeEstado.setText(String.valueOf(""));

                        tfSiglaEstado.requestFocus();
                        tfSiglaEstado.setEnabled(true);
                        tfSiglaEstado.setText(String.valueOf(""));
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

        btListSiglaEstado.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                list = "bySiglaEstado";
                btListarSiglaEstado.doClick();
            }
        });

        btListNomeEstado.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                list = "byNomeEstado";
                btListarSiglaEstado.doClick();
            }
        });

        btListarNomeEstado.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                list = "NomeEstado";
                btListarSiglaEstado.doClick();
            }
        });

        btListarSiglaEstado.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                String aux[];
                text.setText("");
                dados.clear();
                    switch (list) {
                        case "SiglaEstado":
                            dados = controle.listInOrderId();
                            break;
                        case "NomeEstado":
                            dados = controle.listInOrderNome();
                            break;
                        case "bySiglaEstado":
                            dados = controle.listById(Integer.valueOf(tfSiglaEstado.getText()));
                            break;
                        default:
                            dados = controle.listByNome(tfSiglaEstado.getText());
                            break;
                    }
                try{
                if (dados.get(0) == null){}
                }catch(Exception lista){
                    int erro = 3/0;                }
                for (Estado linha : dados) {
                    aux = String.valueOf(linha).split(";");
                    text.append(
"SiglaEstado: " + 
            aux[0]
 + "\n" +
"NomeEstado: " + 
            aux[1]
+ "\n-------------------------------------------------------------------------------------------\n"
);}
                dialog.setLocationRelativeTo(cp);
                dialog.setModal(true);
                dialog.setVisible(true);
                }catch(Exception Lista){
                    JOptionPane.showMessageDialog(null, "NOTHING TO SEE HERE! 8P", "OPS", JOptionPane.PLAIN_MESSAGE);
                    }finally{
                        list = "SiglaEstado";
                    }
        }});
/*
       addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            System.exit(0);            }
        });
   */     
        tfSiglaEstado.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            btBuscar.doClick();}});


        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }
}

