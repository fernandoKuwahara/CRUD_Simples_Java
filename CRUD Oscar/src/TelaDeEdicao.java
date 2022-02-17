import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TelaDeEdicao {

	private JFrame frame;
	private JTextField txtCategoriaDoCalcado;
	private JTextField txtPrecoDoCalcado;
	private JTextField txtCorDoCalcado;
	private JTextField txtMarcaDoCalcado;
	private JComboBox<String> txtCalcadosCadastrados;
	private JTextField txtDescricaoDoCalcado;
	
	MySQLAccess DB = new MySQLAccess();
	TelaDeMenu TelaDeMenu = new TelaDeMenu();
	List<String> nomeCalcados = null;
	ResultSet informacoesDoCalcado = null;
	private int controleDeCalcadosCadastrados;
	private String editarDadosDoCalcado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeEdicao window = new TelaDeEdicao();
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
	public TelaDeEdicao() {
		initialize();
	}
	
	//Abre o frame especifico e atualiza o JComboBox com o(s) nome(s) do(s) calçado(s) no banco de dados
	public void AbrirTelaDeEdicao()
	{
		frame.setVisible(true);
		List<String> nomes;
		try {
			nomes = DB.pegarNomeDosCalcados();
			for (String nome : nomes) 
			{
				txtCalcadosCadastrados.addItem(nome);
			}
		} catch (Exception e) {
			//tratamento para eventuais erros
			JOptionPane.showMessageDialog(null,"Ocorreu Um Erro Não Previsto: " + e,"ERRO!",JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTelaDeEdio = new JLabel("Tela de Edi\u00E7\u00E3o:");
		lblTelaDeEdio.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblTelaDeEdio.setBounds(10, 11, 149, 21);
		frame.getContentPane().add(lblTelaDeEdio);
		
		JLabel lblNewLabel_1 = new JLabel("Nome do Cal\u00E7ado:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 56, 130, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Categoria do Cal\u00E7ado:");
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(162, 56, 141, 14);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Pre\u00E7o do Cal\u00E7ado:");
		lblNewLabel_1_2_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_2_1_1.setBounds(314, 56, 129, 14);
		frame.getContentPane().add(lblNewLabel_1_2_1_1);
		
		txtCategoriaDoCalcado = new JTextField();
		txtCategoriaDoCalcado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCategoriaDoCalcado.setEnabled(false);
		txtCategoriaDoCalcado.setColumns(10);
		txtCategoriaDoCalcado.setBounds(162, 76, 130, 20);
		frame.getContentPane().add(txtCategoriaDoCalcado);
		
		txtPrecoDoCalcado = new JTextField();
		txtPrecoDoCalcado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPrecoDoCalcado.setEnabled(false);
		txtPrecoDoCalcado.setColumns(10);
		txtPrecoDoCalcado.setBounds(324, 76, 119, 20);
		frame.getContentPane().add(txtPrecoDoCalcado);
		
		JLabel lblNewLabel_2 = new JLabel("R$");
		lblNewLabel_2.setBounds(306, 79, 31, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JSpinner spnTamanhoDoCalcado = new JSpinner();
		spnTamanhoDoCalcado.setModel(new SpinnerNumberModel(1.0, 1.0, 60.0, 1.0));
		spnTamanhoDoCalcado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		spnTamanhoDoCalcado.setEnabled(false);
		spnTamanhoDoCalcado.setBounds(10, 129, 130, 20);
		frame.getContentPane().add(spnTamanhoDoCalcado);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tamanho do Cal\u00E7ado:");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 109, 149, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Cor do Cal\u00E7ado:");
		lblNewLabel_1_2_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_2_1.setBounds(162, 109, 106, 14);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		txtCorDoCalcado = new JTextField();
		txtCorDoCalcado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCorDoCalcado.setEnabled(false);
		txtCorDoCalcado.setColumns(10);
		txtCorDoCalcado.setBounds(162, 129, 130, 20);
		frame.getContentPane().add(txtCorDoCalcado);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Marca do Cal\u00E7ado:");
		lblNewLabel_1_2_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_2_1_1_1.setBounds(314, 109, 129, 14);
		frame.getContentPane().add(lblNewLabel_1_2_1_1_1);
		
		txtMarcaDoCalcado = new JTextField();
		txtMarcaDoCalcado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMarcaDoCalcado.setEnabled(false);
		txtMarcaDoCalcado.setColumns(10);
		txtMarcaDoCalcado.setBounds(313, 130, 130, 20);
		frame.getContentPane().add(txtMarcaDoCalcado);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Descri\u00E7\u00E3o do Cal\u00E7ado:");
		lblNewLabel_1_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_1_1_1.setBounds(10, 175, 165, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Quantidade Para Estoque:");
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(10, 227, 165, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JSpinner spnQuantidadeParaEstoque = new JSpinner();
		spnQuantidadeParaEstoque.setModel(new SpinnerNumberModel(1.0, 1.0, 999.0, 1.0));
		spnQuantidadeParaEstoque.setFont(new Font("SansSerif", Font.PLAIN, 12));
		spnQuantidadeParaEstoque.setEnabled(false);
		spnQuantidadeParaEstoque.setBounds(10, 247, 130, 20);
		frame.getContentPane().add(spnQuantidadeParaEstoque);
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnEditar.setEnabled(false);
		btnEditar.setBounds(258, 185, 161, 30);
		frame.getContentPane().add(btnEditar);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Abre a tela de menu, e fecha este frame atual
				TelaDeMenu.AbrirTelaDeMenu();
				frame.dispose();
			}
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnVoltar.setBounds(258, 237, 161, 30);
		frame.getContentPane().add(btnVoltar);
		
		txtCalcadosCadastrados = new JComboBox();
		txtCalcadosCadastrados.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				//Desbloqueia o acesso para os campos e botões do frame
				controleDeCalcadosCadastrados = txtCalcadosCadastrados.getSelectedIndex();
				
				if (controleDeCalcadosCadastrados == 0)
				{
					txtCategoriaDoCalcado.setEnabled(false);
					txtCorDoCalcado.setEnabled(false);
					txtMarcaDoCalcado.setEnabled(false);
					txtPrecoDoCalcado.setEnabled(false);
					txtDescricaoDoCalcado.setEnabled(false);

					spnQuantidadeParaEstoque.setEnabled(false);
					spnTamanhoDoCalcado.setEnabled(false);
					
					btnEditar.setEnabled(false);
					
					txtCategoriaDoCalcado.setText("");
					txtCorDoCalcado.setText("");
					txtMarcaDoCalcado.setText("");
					txtDescricaoDoCalcado.setText("");
					txtPrecoDoCalcado.setText("");
					
					spnQuantidadeParaEstoque.setValue(1);
					spnTamanhoDoCalcado.setValue(1);
				}
				else
				{
					txtCategoriaDoCalcado.setEnabled(true);
					txtCorDoCalcado.setEnabled(true);
					txtMarcaDoCalcado.setEnabled(true);
					txtPrecoDoCalcado.setEnabled(true);
					txtDescricaoDoCalcado.setEnabled(true);

					spnQuantidadeParaEstoque.setEnabled(true);
					spnTamanhoDoCalcado.setEnabled(true);
					
					btnEditar.setEnabled(true);
					
					//try catch que faz uma requisição para o banco de dados e retorna com as informações do calçado escolhido, e preenche os campos dos mesmos
					try {
						editarDadosDoCalcado = txtCalcadosCadastrados.getSelectedItem().toString();
						informacoesDoCalcado = DB.pegarInformacoesDoCalcado(editarDadosDoCalcado);
						
						informacoesDoCalcado.next();
						
						txtCategoriaDoCalcado.setText(informacoesDoCalcado.getString("categoriaCalcado"));
						txtCorDoCalcado.setText(informacoesDoCalcado.getString("corCalcado"));
						txtMarcaDoCalcado.setText(informacoesDoCalcado.getString("marcaCalcado"));
						txtDescricaoDoCalcado.setText(informacoesDoCalcado.getString("descricaoCalcado"));
						
						String confirmarPreco = informacoesDoCalcado.getString("precoCalcado");
						confirmarPreco = confirmarPreco.replace(".", ",");
						txtPrecoDoCalcado.setText(confirmarPreco);
						
						spnQuantidadeParaEstoque.setValue(Double.parseDouble(informacoesDoCalcado.getString("quantidadeEstoqueCalcado")));
						spnTamanhoDoCalcado.setValue(Double.parseDouble(informacoesDoCalcado.getString("tamanhoCalcado")));
						
					} catch (Exception e1) {
						//tratamento para eventuais erros 
						JOptionPane.showMessageDialog(null,"Ocorreu Um Erro Não Previsto: " + e1,"ERRO!",JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		});
		
		txtCalcadosCadastrados.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtCalcadosCadastrados.setModel(new DefaultComboBoxModel(new String[] {"Selecione"}));
		txtCalcadosCadastrados.setSelectedIndex(0);
		txtCalcadosCadastrados.setBounds(10, 75, 130, 22);
		frame.getContentPane().add(txtCalcadosCadastrados);
		
		txtDescricaoDoCalcado = new JTextField();
		txtDescricaoDoCalcado.setEnabled(false);
		txtDescricaoDoCalcado.setBounds(10, 196, 201, 20);
		frame.getContentPane().add(txtDescricaoDoCalcado);
		txtDescricaoDoCalcado.setColumns(10);
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				String confirmarPreco = txtPrecoDoCalcado.getText().toString();
				confirmarPreco = confirmarPreco.replace(",", ".");
				
				//validarPreco é invocado de outra classe para poder validar o preco digitado pelo usuário
				boolean confirmarPrecoReal = new Validacoes().validarPreco(confirmarPreco);
				
				//IF's que verificam a integridade dos dados antes de poder fazer a ação principal solicitada
				if (confirmarPreco.equals("")) 
					JOptionPane.showMessageDialog(null,"Informe O Preço Do Calçado!","ATENÇÃO - Informações Insuficientes!",JOptionPane.WARNING_MESSAGE);
				else if (confirmarPrecoReal == false) 
					JOptionPane.showMessageDialog(null,"Informe Um Preço Válido!","ATENÇÃO - Informação Inválida!",JOptionPane.WARNING_MESSAGE);
				else if (txtCategoriaDoCalcado.getText().equals("") || txtCorDoCalcado.getText().equals("") || txtMarcaDoCalcado.getText().equals("") || txtDescricaoDoCalcado.getText().equals(""))
					JOptionPane.showMessageDialog(null,"Existem Campos Que Ainda Não Foram Preenchidos!","ATENÇÃO - Informações Insuficientes!",JOptionPane.WARNING_MESSAGE);
				else
				{
					//try catch para tratar qualquer eventual erro de execução e por isto encerrar o processo de edição
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
						
						String categoriaCalcado = txtCategoriaDoCalcado.getText().toString();
						String corCalcado = txtCorDoCalcado.getText().toString();
						String marcaCalcado = txtMarcaDoCalcado.getText().toString();
						String descricaoCalcado = txtDescricaoDoCalcado.getText().toString();
						
						//Retorna uma resposta do banco de dados, e mostra em uma mensagem caso os dados tenham sidos alterados com sucesso
						String registroCalcado = DB.alterarDadoDoCalcado(editarDadosDoCalcado, tamanhoCalcado, categoriaCalcado, corCalcado, precoOficial, marcaCalcado, quantidadeEstoqueCalcado, descricaoCalcado);
						JOptionPane.showMessageDialog(null,registroCalcado,"ATENÇÃO:",JOptionPane.WARNING_MESSAGE);
						txtCalcadosCadastrados.setSelectedIndex(0);
					} catch (Exception e1) {
						//tratamento para eventuais erros
						JOptionPane.showMessageDialog(null,"Ocorreu Um Erro Não Previsto: " + e1,"ERRO!",JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		});
	}

}
