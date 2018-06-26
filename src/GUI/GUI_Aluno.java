package GUI;

import java.awt.BorderLayout;
import DAOs.DAOAluno;
import Entidades.Aluno;
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

import java.text.SimpleDateFormat;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GUI_Aluno extends JDialog {

    private Container cp;
    private JPanel pnNorte = new JPanel(new FlowLayout());
    private JPanel pnCentro = new JPanel(new GridLayout(0, 2));
    private JPanel pnSul = new JPanel(new FlowLayout());

    private JLabel lbCpf = new JLabel("Cpf:");
    private JTextField tfCpf = new JTextField(10);

    private JButton btBuscar = new JButton("Buscar");
    private JButton btInserir = new JButton("Criar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btRemover = new JButton("Remover");
    private JButton btAtualizar = new JButton("Atualizar");
    private JButton btListCpf = new JButton("Listar Cpf");
    private JButton btListNome = new JButton("Listar Nome");
    private JButton btListarCpf = new JButton("Listar(por Cpf)");
    private JButton btListarNome = new JButton("Listar(por Nome)");
    private String list = "Cpf";
    
    private JLabel lbAviso = new JLabel("Aviso");
    DAOAluno controle = new DAOAluno();
    List<Aluno> dados = new ArrayList<>();
    
    private boolean acao = true;
    Aluno entidade = new Aluno();

    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome = new JTextField();

    private JLabel lbSenha = new JLabel("Senha");
    private JTextField tfSenha = new JTextField();

    Imagem imagem = new Imagem();
    ImageIcon iFoto = new ImageIcon();
    private JPanel pnLeste = new JPanel();
    private JLabel lbFoto = new JLabel();
    File flFoto = null;

    private SimpleDateFormat DateDataInscricao = new SimpleDateFormat("");
    private JLabel lbDataInscricao = new JLabel("DataInscricao");
    private JTextField tfDataInscricao = new JTextField(20);

    private JDialog dialog = new JDialog();
    private JTextArea text = new JTextArea();
    private JScrollPane scroll = new JScrollPane(text);
    private String Cpf;
    public GUI_Aluno() throws ParseException{

        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Aluno");

        pnNorte.setBackground(Color.yellow);
        pnCentro.setBackground(Color.cyan);
        pnSul.setBackground(Color.green);

        dialog.add(scroll);
        text.setEditable(false);
        dialog.setSize(500,200);

        pnNorte.add(btListCpf);
        pnNorte.add(btListNome);
        pnNorte.add(lbCpf);
        pnNorte.add(tfCpf);
        pnNorte.add(btBuscar);
        pnNorte.add(btInserir);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.add(btRemover);
        pnNorte.add(btAtualizar);
        pnNorte.add(btListarCpf);
        pnNorte.add(btListarNome);
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


        tfNome.setEnabled(false);
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);

        tfSenha.setEnabled(false);
        pnCentro.add(lbSenha);
        pnCentro.add(tfSenha);

        iFoto = new ImageIcon(imagem.GetImg("src/Imagens/add.png", 100, 100));
        lbFoto.setIcon(iFoto);
        pnLeste.add(lbFoto);
        cp.add(pnLeste, BorderLayout.EAST);
        lbFoto.setEnabled(false);

        tfDataInscricao.setEnabled(false);
        pnCentro.add(lbDataInscricao);
        pnCentro.add(tfDataInscricao);


        btBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    entidade = new Aluno();

                    Cpf = tfCpf.getText();

                    entidade.setCpf(Cpf);

                    if (Cpf.isEmpty()) {
                        int error = 3/0;
                    }
                    entidade = controle.obter(entidade.getCpf());
                    lbAviso.setBackground(Color.green);
                    if (entidade != null) {

                        btInserir.setVisible(false);
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        lbAviso.setText("Achou na lista");

                        tfNome.setText(entidade.getNome());

                        tfSenha.setText(entidade.getSenha());

                        try{
                            iFoto = new ImageIcon(imagem.GetImg(entidade.getFoto(), 100, 100));
                            lbFoto.setIcon(iFoto);
                            if(!entidade.getFoto().contains("src")){
                                int Error = 3/0;
                            }
                        }catch(Exception EX){
                            iFoto = new ImageIcon(imagem.GetImg("src/icones/list.png", 100, 100));
                            lbFoto.setIcon(iFoto);
                        }

                        tfDataInscricao.setText(DateDataInscricao.format(entidade.getDataInscricao()));

                    } else {
                        lbAviso.setText("Não achou na lista");
                        btInserir.setVisible(true);

                        lbAviso.setBackground(Color.red);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);


                        tfNome.setEnabled(false);

                        tfSenha.setEnabled(false);

                        lbFoto.setEnabled(false);

                        tfDataInscricao.setEnabled(false);

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
                        btListarCpf.setVisible(false);
                        btListarNome.setVisible(false);
                        btListCpf.setVisible(false);
                        btListNome.setVisible(false);

                        tfCpf.setText(Cpf);
                        tfCpf.setEnabled(false);

                        tfNome.setEnabled(true);
                        tfNome.setText(String.valueOf(""));

                        tfSenha.setEnabled(true);
                        tfSenha.setText(String.valueOf(""));

                        iFoto = new ImageIcon(imagem.GetImg("src/Imagens/add.png", 100, 100));
                        lbFoto.setIcon(iFoto);
                        lbFoto.setEnabled(true);

                        tfDataInscricao.setEnabled(true);
                        tfDataInscricao.setText(String.valueOf(""));

                        tfDataInscricao.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                            if (tfDataInscricao.getText().equals("")){
                                tfDataInscricao.setText("");}}
                        public void focusLost(FocusEvent e) {
                            if (tfDataInscricao.getText().equals("")){
                                tfDataInscricao.setText("");}}});
                        tfDataInscricao.requestFocus();


                        tfNome.requestFocus();


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

                            Aluno entidade = new Aluno();

                            entidade.setCpf(Cpf);

                            entidade.setNome(tfNome.getText());

                            entidade.setSenha(tfSenha.getText());

                            if(flFoto != null){
                                entidade.setFoto(imagem.MoverImagem(String.valueOf(flFoto), "src/Imagens/", String.valueOf(entidade.getCpf())));
                                flFoto = null;
                            }else{
                                iFoto = new ImageIcon(imagem.GetImg("src/Imagens/add.png", 100, 100));
                                lbFoto.setIcon(iFoto);
                            }

                            DateDataInscricao.setLenient(false);
                            entidade.setDataInscricao(DateDataInscricao.parse(tfDataInscricao.getText()));

                            controle.inserir(entidade);

                            lbAviso.setText("Registro inserido");
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(true);
                            btListarCpf.setVisible(true);
                            btListarNome.setVisible(true);
                            btListCpf.setVisible(true);
                            btListNome.setVisible(true);
                            tfCpf.setEnabled(true);
                            tfCpf.requestFocus();
                            tfCpf.selectAll();

                            tfNome.setEnabled(false);

                            tfSenha.setEnabled(false);

                            lbFoto.setEnabled(false);

                            tfDataInscricao.setEnabled(false);

                            lbAviso.setBackground(Color.green);
                }catch (Exception btSalvar){
                    JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);}

                        } else {
                            try{
                            Aluno entidadeOriginal = entidade;
                            Aluno entidadeModificada = new Aluno();

                            entidadeModificada.setCpf(Cpf);

                            entidadeModificada.setNome(tfNome.getText());

                            entidadeModificada.setSenha(tfSenha.getText());

                            if(flFoto != null){
                                if(entidade.getFoto() != null){
                                    imagem.DeletarImagem(entidade.getFoto());}
                                entidadeModificada.setFoto(imagem.MoverImagem(String.valueOf(flFoto), "src/Imagens/", String.valueOf(entidade.getCpf())));
                                flFoto = null;
                            }else{
                                entidadeModificada.setFoto(entidade.getFoto());
                            }

                            DateDataInscricao.setLenient(false);
                            entidadeModificada.setDataInscricao(DateDataInscricao.parse(tfDataInscricao.getText()));


                            controle.atualizar(entidadeModificada);

                            lbAviso.setText("Registro alterado");
                            tfCpf.setEnabled(true);
                            tfCpf.requestFocus();
                            tfCpf.selectAll();
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(true);
                            btListarCpf.setVisible(true);
                            btListarNome.setVisible(true);
                            btListCpf.setVisible(true);
                            btListNome.setVisible(true);

                            tfNome.setEnabled(false);

                            tfSenha.setEnabled(false);

                            lbFoto.setEnabled(false);

                            tfDataInscricao.setEnabled(false);

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
                        tfCpf.setEnabled(true);

                        tfCpf.requestFocus();
                        tfCpf.selectAll();

                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListarCpf.setVisible(true);
                        btListarNome.setVisible(true);
                        btListCpf.setVisible(true);
                        btListNome.setVisible(true);
                        lbAviso.setBackground(Color.green);

                        tfNome.setEnabled(false);
                        tfNome.setText(String.valueOf(""));

                        tfSenha.setEnabled(false);
                        tfSenha.setText(String.valueOf(""));

                        iFoto = new ImageIcon(imagem.GetImg("src/Imagens/add.png", 100, 100));
                        lbFoto.setIcon(iFoto);
                        lbFoto.setEnabled(false);

                        tfDataInscricao.setEnabled(false);
                        tfDataInscricao.setText(String.valueOf(""));

                    }
                }
        );

        btAtualizar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        acao = false;

                        tfCpf.setText(Cpf);
                        tfCpf.setEnabled(false);

                        tfNome.setEnabled(true);

                        tfSenha.setEnabled(true);

                        lbFoto.setEnabled(true);

                        tfDataInscricao.setEnabled(true);

                        tfNome.requestFocus();

                        btSalvar.setVisible(true);
                        btCancelar.setVisible(true);
                        btBuscar.setVisible(false);
                        btListarCpf.setVisible(false);
                        btListarNome.setVisible(false);
                        btListCpf.setVisible(false);
                        btListNome.setVisible(false);
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
                                "Confirma a exclusão do registro <ID = " + entidade.getCpf() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {


                        tfNome.setText(String.valueOf(""));

                        tfSenha.setText(String.valueOf(""));

                        if(entidade.getFoto() != null){
                            imagem.DeletarImagem(entidade.getFoto());
                        }
                        iFoto = new ImageIcon(imagem.GetImg("src/Imagens/add.png", 100, 100));
                        lbFoto.setIcon(iFoto);

                        tfDataInscricao.setText(String.valueOf(""));

                        tfCpf.requestFocus();
                        tfCpf.setEnabled(true);
                        tfCpf.setText(String.valueOf(""));
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

        btListCpf.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                list = "byCpf";
                btListarCpf.doClick();
            }
        });

        btListNome.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                list = "byNome";
                btListarCpf.doClick();
            }
        });

        btListarNome.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                list = "Nome";
                btListarCpf.doClick();
            }
        });

        btListarCpf.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                String aux[];
                text.setText("");
                dados.clear();
                    switch (list) {
                        case "Cpf":
                            dados = controle.listInOrderId();
                            break;
                        case "Nome":
                            dados = controle.listInOrderNome();
                            break;
                        case "byCpf":
                            dados = controle.listById(Integer.valueOf(tfCpf.getText()));
                            break;
                        default:
                            dados = controle.listByNome(tfCpf.getText());
                            break;
                    }
                try{
                if (dados.get(0) == null){}
                }catch(Exception lista){
                    int erro = 3/0;                }
                for (Aluno linha : dados) {
                    aux = String.valueOf(linha).split(";");
                    text.append(
"Cpf: " + 
            aux[0]
 + "\n" +
"Nome: " + 
            aux[1]
 + "\n" +
"Senha: " + 
            aux[2]
 + "\n" +
"Foto: " + 
            aux[3]
 + "\n" +
"DataInscricao: " + 
            DateDataInscricao.format(DateDataInscricao.parse(aux[4]))
+ "\n-------------------------------------------------------------------------------------------\n"
);}
                dialog.setLocationRelativeTo(cp);
                dialog.setModal(true);
                dialog.setVisible(true);
                }catch(Exception Lista){
                    JOptionPane.showMessageDialog(null, "NOTHING TO SEE HERE! 8P", "OPS", JOptionPane.PLAIN_MESSAGE);
                    }finally{
                        list = "Cpf";
                    }
        }});
/*
       addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            System.exit(0);            }
        });
     */   
        tfCpf.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            btBuscar.doClick();}});

        lbFoto.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (lbFoto.isEnabled()) {
                    FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
                    JFileChooser file = new JFileChooser();
                    file.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    file.setFileFilter(imageFilter);
                    int i = file.showSaveDialog(null);
                    if (i == 1) {
                        iFoto = new ImageIcon(imagem.GetImg("src/Imagens/add.png", 100, 100));
                        lbFoto.setIcon(iFoto);
                        flFoto = null;
                    } else {
                        try{
                            flFoto = file.getSelectedFile();
                            iFoto = new ImageIcon(imagem.GetImg(String.valueOf(flFoto), 100, 100));
                            lbFoto.setIcon(iFoto);
                        }catch(Exception img){
                            System.out.println("JFileChooser Listerner Error: " + img);}
                    }
                }}});


        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }
}

