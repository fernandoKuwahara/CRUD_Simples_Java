import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaDeCadastro {

	private JFrame frame;
	private JTextField txtNomeCalcado;
	private JTextField txtCategoriaDoCalcado;
	private JTextField txtCorDoCalcado;
	private JTextField txtPrecoDoCalcado;
	private JTextField txtMarcaDoCalcado;
	private JTextField txtDataDeCadastro;
	private JTextField txtDescricaoDoCalcado;
	private JSpinner spnQuantidadeParaEstoque;
	private JSpinner spnTamanhoDoCalcado;
	private JButton btnCadastrarCalcado;
	private JButton btnReseta;
	private TelaDeMenu TelaDeMenu = new TelaDeMenu();
	private Validacoes validarDados = new Validacoes();

	MySQLAccess DB = new MySQLAccess();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeCadastro window = new TelaDeCadastro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaDeCadastro() {
		initialize();
	}
	
	//Método que abre o frame específico
	public void AbrirTelaDeCadastro()
	{
		frame.setVisible(true);
	}
	
	//Método que limpas os campos quando uma ação é finalizada pelo usuário
	public void limparCampos()
	{
		txtNomeCalcado.setText("");
		txtCategoriaDoCalcado.setText("");
		txtCorDoCalcado.setText("");
		txtMarcaDoCalcado.setText("");
		txtDataDeCadastro.setText("");
		txtDescricaoDoCalcado.setText("");
		txtPrecoDoCalcado.setText("");
		
		spnQuantidadeParaEstoque.setValue(1);
		spnTamanhoDoCalcado.setValue(1);
		
		txtCategoriaDoCalcado.setEnabled(false);
		txtCorDoCalcado.setEnabled(false);
		txtMarcaDoCalcado.setEnabled(false);
		txtDataDeCadastro.setEnabled(false);
		txtDescricaoDoCalcado.setEnabled(false);
		txtPrecoDoCalcado.setEnabled(false);
		
		spnQuantidadeParaEstoque.setEnabled(false);
		spnTamanhoDoCalcado.setEnabled(false);
		
		btnCadastrarCalcado.setEnabled(false);
		btnReseta.setEnabled(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tela de Cadastro:");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 149, 21);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome do Cal\u00E7ado:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 56, 130, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtNomeCalcado = new JTextField();
		txtNomeCalcado.setBounds(10, 76, 130, 20);
		frame.getContentPane().add(txtNomeCalcado);
		txtNomeCalcado.setColumns(10);
		
		spnTamanhoDoCalcado = new JSpinner();
		spnTamanhoDoCalcado.setEnabled(false);
		spnTamanhoDoCalcado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		spnTamanhoDoCalcado.setModel(new SpinnerNumberModel(1.0, 1.0, 60.0, 1.0));
		spnTamanhoDoCalcado.setBounds(10, 129, 130, 20);
		frame.getContentPane().add(spnTamanhoDoCalcado);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tamanho do Cal\u00E7ado:");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 109, 149, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Categoria do Cal\u00E7ado:");
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(162, 56, 141, 14);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		txtCategoriaDoCalcado = new JTextField();
		txtCategoriaDoCalcado.setEnabled(false);
		txtCategoriaDoCalcado.setColumns(10);
		txtCategoriaDoCalcado.setBounds(162, 76, 130, 20);
		frame.getContentPane().add(txtCategoriaDoCalcado);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Cor do Cal\u00E7ado:");
		lblNewLabel_1_2_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_2_1.setBounds(162, 109, 106, 14);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		txtCorDoCalcado = new JTextField();
		txtCorDoCalcado.setEnabled(false);
		txtCorDoCalcado.setColumns(10);
		txtCorDoCalcado.setBounds(162, 129, 130, 20);
		frame.getContentPane().add(txtCorDoCalcado);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Pre\u00E7o do Cal\u00E7ado:");
		lblNewLabel_1_2_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_2_1_1.setBounds(314, 56, 129, 14);
		frame.getContentPane().add(lblNewLabel_1_2_1_1);
		
		txtPrecoDoCalcado = new JTextField();
		txtPrecoDoCalcado.setEnabled(false);
		txtPrecoDoCalcado.setColumns(10);
		txtPrecoDoCalcado.setBounds(324, 76, 119, 20);
		frame.getContentPane().add(txtPrecoDoCalcado);
		
		JLabel lblNewLabel_2 = new JLabel("R$");
		lblNewLabel_2.setBounds(306, 79, 31, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Marca do Cal\u00E7ado:");
		lblNewLabel_1_2_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_2_1_1_1.setBounds(314, 110, 129, 14);
		frame.getContentPane().add(lblNewLabel_1_2_1_1_1);
		
		txtMarcaDoCalcado = new JTextField();
		txtMarcaDoCalcado.setEnabled(false);
		txtMarcaDoCalcado.setColumns(10);
		txtMarcaDoCalcado.setBounds(313, 130, 130, 20);
		frame.getContentPane().add(txtMarcaDoCalcado);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("Data de Cadastro do Cal\u00E7ado:");
		lblNewLabel_1_2_1_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_2_1_1_1_1.setBounds(10, 234, 188, 14);
		frame.getContentPane().add(lblNewLabel_1_2_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Quantidade Para Estoque:");
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(10, 284, 165, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		spnQuantidadeParaEstoque = new JSpinner();
		spnQuantidadeParaEstoque.setEnabled(false);
		spnQuantidadeParaEstoque.setModel(new SpinnerNumberModel(1.0, 1.0, 999.0, 1.0));
		spnQuantidadeParaEstoque.setFont(new Font("SansSerif", Font.PLAIN, 12));
		spnQuantidadeParaEstoque.setBounds(10, 304, 119, 20);
		frame.getContentPane().add(spnQuantidadeParaEstoque);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Descri\u00E7\u00E3o do Cal\u00E7ado:");
		lblNewLabel_1_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_1_1_1.setBounds(10, 185, 165, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		btnCadastrarCalcado = new JButton("CADASTRAR");
		btnCadastrarCalcado.setEnabled(false);
		btnCadastrarCalcado.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCadastrarCalcado.setBounds(280, 202, 149, 30);
		frame.getContentPane().add(btnCadastrarCalcado);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Abre a tela de menu, e fecha este frame atual
				TelaDeMenu.AbrirTelaDeMenu();
				frame.dispose();
				
			}
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnVoltar.setBounds(280, 304, 149, 30);
		frame.getContentPane().add(btnVoltar);
		
		btnReseta = new JButton("RESETAR");
		btnReseta.setEnabled(false);
		btnReseta.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnReseta.setBounds(280, 254, 149, 27);
		frame.getContentPane().add(btnReseta);
		
		txtDataDeCadastro = new JTextField();
		txtDataDeCadastro.setEnabled(false);
		txtDataDeCadastro.setBounds(10, 253, 119, 20);
		frame.getContentPane().add(txtDataDeCadastro);
		txtDataDeCadastro.setColumns(10);
		
		txtDescricaoDoCalcado = new JTextField();
		txtDescricaoDoCalcado.setEnabled(false);
		txtDescricaoDoCalcado.setBounds(10, 203, 221, 20);
		frame.getContentPane().add(txtDescricaoDoCalcado);
		txtDescricaoDoCalcado.setColumns(10);
		
		txtNomeCalcado.addKeyListener(new KeyAdapter() {
			@Override
			//Desbloqueia o acesso para os campos e botões do frame
			public void keyTyped(KeyEvent e) {
				
				String ConfirmarDesbloqueio = txtNomeCalcado.getText().toString();
				
				if (ConfirmarDesbloqueio.equals(""))
				{
					txtCategoriaDoCalcado.setEnabled(false);
					txtCorDoCalcado.setEnabled(false);
					txtMarcaDoCalcado.setEnabled(false);
					txtDataDeCadastro.setEnabled(false);
					txtDescricaoDoCalcado.setEnabled(false);
					txtPrecoDoCalcado.setEnabled(false);
					
					btnCadastrarCalcado.setEnabled(false);
					btnReseta.setEnabled(false);

					spnQuantidadeParaEstoque.setEnabled(false);
					spnTamanhoDoCalcado.setEnabled(false);
				}
				else
				{
					txtCategoriaDoCalcado.setEnabled(true);
					txtCorDoCalcado.setEnabled(true);
					txtMarcaDoCalcado.setEnabled(true);
					txtDataDeCadastro.setEnabled(true);
					txtDescricaoDoCalcado.setEnabled(true);
					txtPrecoDoCalcado.setEnabled(true);
					
					btnCadastrarCalcado.setEnabled(true);
					btnReseta.setEnabled(true);
					
					spnQuantidadeParaEstoque.setEnabled(true);
					spnTamanhoDoCalcado.setEnabled(true);
				}
				
			}
		});
		
		btnReseta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Invoca o método para limpar os campos
				limparCampos();
				
			}
		});
		
		btnCadastrarCalcado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String confirmarPreco = txtPrecoDoCalcado.getText().toString();
				confirmarPreco = confirmarPreco.replace(",", ".");
				
				//validarPreco é invocado de outra classe para poder validar o preco digitado pelo usuário
				boolean confirmarPrecoReal = validarDados.validarPreco(confirmarPreco);
				
				String confirmarData = txtDataDeCadastro.getText().toString();
				
				//validarPreco é invocado de outra classe para poder validar o preco digitado pelo usuário
				boolean confirmarDataReal = validarDados.validarData(confirmarData);
				
				//IF's que verificam a integridade dos dados antes de poder fazer a ação principal solicitada
				if (confirmarPreco.equals("")) 
					JOptionPane.showMessageDialog(null,"Informe O Preço Do Calçado!","ATENÇÃO - Informações Insuficientes!",JOptionPane.WARNING_MESSAGE);
				else if (confirmarData.equals(""))
					JOptionPane.showMessageDialog(null,"Informe A Data De Cadastro Para Este Calçado!","ATENÇÃO - Informações Insuficientes!",JOptionPane.WARNING_MESSAGE);
				else if (confirmarPrecoReal == false) 
					JOptionPane.showMessageDialog(null,"Informe Um Preço Válido!","ATENÇÃO - Informação Inválida!",JOptionPane.WARNING_MESSAGE);
				else if (confirmarDataReal == false)
					JOptionPane.showMessageDialog(null,"Informe Uma Data Válida!","ATENÇÃO - Informação Inválida!",JOptionPane.WARNING_MESSAGE);
				else if (txtNomeCalcado.getText().equals("") || txtCategoriaDoCalcado.getText().equals("") || txtCorDoCalcado.getText().equals("") || txtMarcaDoCalcado.getText().equals("") || txtDescricaoDoCalcado.getText().equals("") || txtDataDeCadastro.getText().equals(""))
					JOptionPane.showMessageDialog(null,"Existem Campos Que Ainda Não Foram Preenchidos!","ATENÇÃO - Informações Insuficientes!",JOptionPane.WARNING_MESSAGE);
				else
				{
					//try catch para tratar qualquer eventual erro de execução e por isto encerrar o processo de cadastro
					try {
					
						//Trata o preço informado pelo usuário, para que não possa ocorrer erros de registro
						String precoTratado = txtPrecoDoCalcado.getText().toString();
						precoTratado = precoTratado.trim();
						precoTratado = precoTratado.replace("R$", "");
						precoTratado = precoTratado.replace(" ", "");
						precoTratado = precoTratado.replace(",", ".");
					
						double precoOficial = Double.valueOf(precoTratado).doubleValue();
						double tamanhoCalcado = (double) spnTamanhoDoCalcado.getValue();
						double quantidadeEstoqueCalcado = (double) spnQuantidadeParaEstoque.getValue();
					
						String nomeCalcado = txtNomeCalcado.getText().toString();
						String categoriaCalcado = txtCategoriaDoCalcado.getText().toString();
						String corCalcado = txtCorDoCalcado.getText().toString();
						String marcaCalcado = txtMarcaDoCalcado.getText().toString();
						String descricaoCalcado = txtDescricaoDoCalcado.getText().toString();
						String dataCadastroCalcado = txtDataDeCadastro.getText().toString();
					
						//Retorna uma resposta do banco de dados, e mostra em uma mensagem caso o calçado tenha sido registrado com sucesso
						String registroCalcado = DB.cadastrarCalcado(nomeCalcado, tamanhoCalcado, categoriaCalcado, corCalcado, precoOficial, marcaCalcado, dataCadastroCalcado, quantidadeEstoqueCalcado, descricaoCalcado);
						JOptionPane.showMessageDialog(null,registroCalcado,"ATENÇÃO:",JOptionPane.WARNING_MESSAGE);
						
						//Invoca o método para limpar os campos logo após o registro do novo calçado
						limparCampos();
					} 
					catch (Exception e1) 
					{
						//tratamento para eventuais erros
						JOptionPane.showMessageDialog(null,"Ocorreu Um Erro Não Previsto: " + e1,"ERRO!",JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		});
	}
}
