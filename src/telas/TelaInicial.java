package telas;

import Classes.Cliente;
import Classes.Funcionario;
import Classes.Gerente;
import Classes.Item;
import Classes.Mesa;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;
import javax.swing.JOptionPane;

public class TelaInicial extends javax.swing.JFrame {
    
    String tipo = "";
    private Gerente gerente;

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }
    
    public TelaInicial() {
        initComponents();
        // Ler do arquivo
        String caminhoArquivo = "C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\gerente.txt";
        String conteudoLido = lerArquivo(caminhoArquivo);
        if (conteudoLido.equals("")){
            System.out.println("não há nada escrito");
            txtCPF.setEnabled(false);
            txtSenha.setEnabled(false);
            txtSenhaV.setEnabled(false);
            btnGerente.setEnabled(false);
            btnFuncionario.setEnabled(false);
            btnEntrar.setEnabled(false);
            jCheckBox1.setEnabled(false);
        }else{
            txtCPF.setEnabled(true);
            txtSenha.setEnabled(true);
            txtSenhaV.setEnabled(false);
            btnGerente.setEnabled(true);
            btnFuncionario.setEnabled(true);
            btnEntrar.setEnabled(true);
            jCheckBox1.setEnabled(true);
            //Construtor do gerente
            String texto = lerArquivo("C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\gerente.txt");
            // Usando o método split para separar os elementos
            String[] elementos = texto.split("\\*");

            //Criando variáveis para armazenar os elementos
            String rgGerente = elementos[0];
            String senhaGerente = elementos[1];
            String nomeGerente = elementos[2];
            String cpfGerente = elementos[3];
            // Criar um StringBuilder a partir da string original
            StringBuilder sb = new StringBuilder(cpfGerente);

            // Remover o caractere na posição desejada
            sb.deleteCharAt(cpfGerente.length()-1);

            // Imprimir o StringBuilder convertido de volta para uma string
            
            Gerente gerente2 = new Gerente(rgGerente,senhaGerente,nomeGerente,sb.toString());
            this.gerente = gerente2;
            //Funcionários
            String texto1 = lerArquivo("C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\funcionarios.txt");
            // Dividir a string em linhas
            String[] linhas = texto1.split("\n");
                // Iterar sobre cada linha
                for (String linha : linhas) {
                    String[] elementos1 = linha.split("\\*");
                    String nomeFuncionario = elementos1[0];
                    String cpfFuncionario = elementos1[1];
                    String dataFuncionario = elementos1[2];
                    String senhaFuncionario = elementos1[3];
                    gerente.adicionarFuncionario(nomeFuncionario, cpfFuncionario, dataFuncionario, senhaFuncionario);
                }
            //Cardápio
            String texto2 = lerArquivo("C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\cardapio.txt");
            String[] linhas2 = texto2.split("\n");
                // Iterar sobre cada linha
                for (String linha2 : linhas2) {
                    String[] elementos2 = linha2.split("\\*");
                    String nomeItem = elementos2[0];
                    String preco = elementos2[1];
                    gerente.adicionarItem(nomeItem, Float.parseFloat(preco));
                }
            //Dados Mesa
            String texto3 = lerArquivo("C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\mesas.txt");
            String[] linhas3 = texto3.split("\n");
                // Iterar sobre cada linha
                for (String linha3 : linhas3) {
                    String[] elementos3 = linha3.split("\\*");
                    String numMesa = elementos3[0];
                    String capacidade = elementos3[1];
                    Mesa mesa = new Mesa(Integer.parseInt(numMesa),Integer.parseInt(capacidade));
                    gerente.adicionarMesas(mesa);
                }
            //Dados Mesas Funcionários
            String texto4 = lerArquivo("C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\mesasFunc.txt");
            String[] linhas4 = texto4.split("\n");
                // Iterar sobre cada linha
                for (String linha4 : linhas4) {
                    String[] elementos4 = linha4.split("\\*");
                    String cpfFuncionario2 = elementos4[0];
                    String numMesa2 = elementos4[1];
                    for (Funcionario x: gerente.getFuncionarios()){
                        if (x.getCpf().equals(cpfFuncionario2)){
                            for (Mesa y: gerente.getMesas()){
                                if (y.getNumMesa()==Integer.parseInt(numMesa2)){
                                    x.addMesa(y);
                                }
                            }
                        }
                    }
                }
            
            
            //Dados Mesa Clientes
            String texto5 = lerArquivo("C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\clientesMesa.txt");
            String[] linhas5 = texto5.split("\n");
                // Iterar sobre cada linha
                for (String linha5 : linhas5) {
                    String[] elementos5 = linha5.split("\\*");
                    String numMesa3 = elementos5[0];
                    String nomeCliente = elementos5[1];
                    String cpfCliente = elementos5[2];
                    for (Mesa y: gerente.getMesas()){
                        if (y.getNumMesa()==Integer.parseInt(numMesa3)){
                            Cliente cliente = new Cliente(cpfCliente,nomeCliente);
                            y.addClientes(cliente);
                        }
                    }
                }
            //Dados Mesa Pedidos
            String texto6 = lerArquivo("C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\mesaPedidos.txt");
            String[] linhas6 = texto6.split("\n");
                // Iterar sobre cada linha
                for (String linha6 : linhas6) {
                    String[] elementos6 = linha6.split("\\*");
                    String numMesa4 = elementos6[0];
                    String produto = elementos6[1];
                    String quantidade = elementos6[2];
                    for (Mesa y: gerente.getMesas()){
                        if (y.getNumMesa()==Integer.parseInt(numMesa4)){
                            for (Item item : gerente.getCardapio().getItens()){
                                if (item.getNome().equals(produto)){
                                    y.addPedido(item, Integer.parseInt(quantidade));
                                }
                            }
                        }
                    }
                }
            }
    }
    public TelaInicial(Gerente gerente) {
        this.gerente = gerente;
        initComponents();
        txtCPF.setEnabled(false);
        txtSenha.setEnabled(false);
        txtSenhaV.setEnabled(false);
        btnPrimeiroCadastro.setEnabled(false);
        btnGerente.setEnabled(true);
        btnFuncionario.setEnabled(true);
        btnEntrar.setEnabled(false);
        jCheckBox1.setEnabled(false);
    }
    
    public static void escreverArquivo(String caminho, String conteudo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho))) {
            escritor.write(conteudo);
            System.out.println("Conteúdo escrito no arquivo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String lerArquivo(String caminho) {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conteudo.toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnFuncionario = new javax.swing.JRadioButton();
        btnGerente = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnEntrar = new javax.swing.JButton();
        btnPrimeiroCadastro = new javax.swing.JButton();
        txtSenhaV = new javax.swing.JTextField();
        btnSair = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Al Mossar Pizza");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/pizza.png")).getImage());
        setIconImages(null);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/LogoTelaInicial.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        jPanel8.setBackground(new java.awt.Color(255, 204, 0));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));

        buttonGroup1.add(btnFuncionario);
        btnFuncionario.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnFuncionario.setText("Funcionário");
        btnFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncionarioActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnGerente);
        btnGerente.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnGerente.setText("Gerente");
        btnGerente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenteActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(255, 204, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 0));
        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel1.setText("CPF:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
        );

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPFActionPerformed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(255, 204, 0));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setText("Senha:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(14, 14, 14))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jCheckBox1.setText("Mostrar senha");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        btnEntrar.setBackground(new java.awt.Color(255, 204, 0));
        btnEntrar.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnEntrar.setText("Entrar");
        btnEntrar.setBorder(null);
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        btnPrimeiroCadastro.setBackground(new java.awt.Color(255, 204, 0));
        btnPrimeiroCadastro.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnPrimeiroCadastro.setText("PrimeiroCadastro");
        btnPrimeiroCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeiroCadastroActionPerformed(evt);
            }
        });

        btnSair.setBackground(new java.awt.Color(255, 204, 0));
        btnSair.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnSair.setText("Sair");
        btnSair.setBorder(null);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCPF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSenhaV)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jCheckBox1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 2, Short.MAX_VALUE)
                                .addComponent(btnPrimeiroCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnGerente)
                                .addGap(18, 18, 18)
                                .addComponent(btnFuncionario)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFuncionario)
                    .addComponent(btnGerente))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCPF)
                        .addComponent(btnPrimeiroCadastro))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSenha)
                        .addComponent(txtSenhaV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));

        jPanel12.setBackground(new java.awt.Color(255, 204, 0));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 223, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)))
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        //
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void btnPrimeiroCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeiroCadastroActionPerformed
        new TelaCadastroGerentes().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnPrimeiroCadastroActionPerformed

    private void btnGerenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenteActionPerformed
        tipo = "gerente";
        txtCPF.setEnabled(true);
        txtSenha.setEnabled(true);
        btnEntrar.setEnabled(true);
        jCheckBox1.setEnabled(true);
        txtCPF.requestFocus();
    }//GEN-LAST:event_btnGerenteActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        int index = 0;
        if (tipo.equals("")){
            JOptionPane.showMessageDialog(null, "Selecione o tipo do login", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        else if (txtCPF.getText().equals("") || txtSenha.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Preencha todos os espaços", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        else if (tipo.equals("gerente")){
            if (txtCPF.getText().equals(gerente.getCpf()) && txtSenha.getText().equals(gerente.getSenha())){
                TelaGerente telaGerente = new TelaGerente(gerente);
                telaGerente.setVisible(true);
                this.setVisible(false);
            }
            else{
               JOptionPane.showMessageDialog(null, "Informações incorretas,\n tente novamente.", "Aviso", JOptionPane.ERROR_MESSAGE); 
            }
        }
        else if (tipo.equals("funcionario")){
            boolean acheiF = false;
            if (gerente.getFuncionarios().isEmpty()){
                JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
            else{
                for (Funcionario funcionario: gerente.getFuncionarios()){
                    if (txtSenha.getText().equals(funcionario.getSenha()) && txtCPF.getText().equals(funcionario.getCpf())){
                        acheiF = true;
                        TelaFuncionarioMesas telaFuncionarioMesas = new TelaFuncionarioMesas(gerente,index);
                        telaFuncionarioMesas.setVisible(true);
                        this.setVisible(false);
                    }
                    index += 1;
                }
                if (!acheiF){
                    JOptionPane.showMessageDialog(null, "Informações incorretas,\n tente novamente.", "Aviso", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()){
            txtSenhaV.setText(txtSenha.getText());
            txtSenha.setEnabled(false);
        }    
        else{
          txtSenhaV.setText("");
          txtSenha.setEnabled(true);  
        }    

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncionarioActionPerformed
        tipo = "funcionario";
        txtCPF.setEnabled(true);
        txtSenha.setEnabled(true);
        btnEntrar.setEnabled(true);
        jCheckBox1.setEnabled(true);
        txtCPF.requestFocus();
    }//GEN-LAST:event_btnFuncionarioActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        //Dados do Gerente
        String dadosGerente = "C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\gerente.txt";
        String conteudoParaEscrever = gerente.getRg() + "*" + gerente.getSenha() + "*" + gerente.getNome() + "*" + gerente.getCpf();
        escreverArquivo(dadosGerente, conteudoParaEscrever);
        
        //Dados dos Funcionários
        String dadosFuncionarios = "C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\funcionarios.txt";
        StringJoiner resultado = new StringJoiner("\n");
        for (Funcionario func : gerente.getFuncionarios()){
            resultado.add(func.getNome() + "*" + func.getCpf() + "*" + func.getData() + "*"  + func.getSenha());
        }
        escreverArquivo(dadosFuncionarios, resultado.toString());
        
        //Dados do Cardápio
        String dadosCardapio = "C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\cardapio.txt";
        StringJoiner resultado1 = new StringJoiner("\n");
        for (Item item : gerente.getCardapio().getItens()){
            resultado1.add(item.getNome() + "*" + item.getPreco());
        }
        escreverArquivo(dadosCardapio, resultado1.toString());
        
        //Dados das Mesas
        String dadosMesas = "C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\mesas.txt";
        StringJoiner resultado2 = new StringJoiner("\n");
        for (Mesa mesa : gerente.getMesas()){
            resultado2.add(mesa.getNumMesa()+ "*" + String.valueOf(mesa.getCapacidade()));
        }
        escreverArquivo(dadosMesas, resultado2.toString());
        
        //Dados Mesas Funcionários
        String dadosMesasFunc = "C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\mesasFunc.txt";
        StringJoiner resultado3 = new StringJoiner("\n");
        for (Funcionario func : gerente.getFuncionarios()){
            for(Mesa mesa: func.getMesas()){
                resultado3.add(func.getCpf()+ "*"+mesa.getNumMesa());
            }
        }
        escreverArquivo(dadosMesasFunc, resultado3.toString());
        
        //Dados Mesa Clientes
        String dadosClientesMesa = "C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\clientesMesa.txt";
        StringJoiner resultado4 = new StringJoiner("\n");
        for (Funcionario func : gerente.getFuncionarios()){
            for(Mesa mesa: func.getMesas()){
                for (Cliente clie: mesa.getClientes()){
                    resultado4.add(mesa.getNumMesa() + "*" + clie.getNome() + "*" + clie.getCpf());
                }
            }
        }
        escreverArquivo(dadosClientesMesa, resultado4.toString());
        
        //Dados Mesa Pedidos
        String dadosMesaPedidos = "C:\\Users\\Erick\\Desktop\\GustavoRestaurante\\Restaurante1\\TELAS\\build\\classes\\arquivos\\mesaPedidos.txt";
        StringJoiner resultado5 = new StringJoiner("\n");
        for (Funcionario func : gerente.getFuncionarios()){
            for(Mesa mesa: func.getMesas()){
                int cont = 0;
                for (Item item: mesa.getPedido().getItens()){
                    resultado5.add(mesa.getNumMesa() + "*" +  item.getNome() + "*" + mesa.getPedido().getQuantidades().get(cont));
                    cont += 1;
                }
            }
        }
        escreverArquivo(dadosMesaPedidos, resultado5.toString());
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JRadioButton btnFuncionario;
    private javax.swing.JRadioButton btnGerente;
    private javax.swing.JButton btnPrimeiroCadastro;
    private javax.swing.JButton btnSair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtSenhaV;
    // End of variables declaration//GEN-END:variables
}
