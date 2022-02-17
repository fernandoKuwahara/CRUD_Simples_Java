import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ItemEvent;
import javax.swing.JScrollBar;

public class TelaDeConsulta {

	private JFrame frame;
	private JTextField txtFiltroDosCalcados;
	MySQLAccess DB = new MySQLAccess();
	private JTable tblCalcadosFiltrados;
	ResultSet informacoesDoCalcado = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeConsulta window = new TelaDeConsulta();
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
	public TelaDeConsulta() {
		initialize();
	}
	
	//Abre o frame especifico
	public void AbrirTelaDeConsulta()
	{
		frame.setVisible(true);
	}
	
	//Método usado para preencher os dados vindos do banco de dados na JTable
	public void PreencherTabelaDeFiltros()
	{
		DefaultTableModel tabelaDeCalcados = (DefaultTableModel) tblCalcadosFiltrados.getModel();
		tabelaDeCalcados.setNumRows(0);
		
		tblCalcadosFiltrados.getColumnModel().getColumn(0);
		tblCalcadosFiltrados.getColumnModel().getColumn(1);
		tblCalcadosFiltrados.getColumnModel().getColumn(2);
		tblCalcadosFiltrados.getColumnModel().getColumn(3);
		tblCalcadosFiltrados.getColumnModel().getColumn(4);
		tblCalcadosFiltrados.getColumnModel().getColumn(5);
		tblCalcadosFiltrados.getColumnModel().getColumn(6);
		tblCalcadosFiltrados.getColumnModel().getColumn(7);
		tblCalcadosFiltrados.getColumnModel().getColumn(8);

		try {
			while (informacoesDoCalcado.next())
			{
				tabelaDeCalcados.addRow(new Object[]{
						informacoesDoCalcado.getString("nomeCalcado"),
						informacoesDoCalcado.getString("tamanhoCalcado"),
						informacoesDoCalcado.getString("categoriaCalcado"),
						informacoesDoCalcado.getString("corCalcado"),
						informacoesDoCalcado.getString("precoCalcado"),
						informacoesDoCalcado.getString("marcaCalcado"),
						informacoesDoCalcado.getString("dataCadastroCalcado"),
						informacoesDoCalcado.getString("quantidadeEstoqueCalcado"),
						informacoesDoCalcado.getString("descricaoCalcado")
				});
			}
		} catch (SQLException e) {
			//tratamento para eventuais erros
			JOptionPane.showMessageDialog(null,"Ocorreu Um Erro Não Previsto: " + e,"ERRO!",JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 925, 365);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTelaDeConsulta = new JLabel("Tela de Consulta:");
		lblTelaDeConsulta.setBounds(10, 11, 149, 21);
		lblTelaDeConsulta.setFont(new Font("SansSerif", Font.BOLD, 16));
		frame.getContentPane().add(lblTelaDeConsulta);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Abre a tela de menu, e fecha este frame atual
				TelaDeMenu TelaDeMenu = new TelaDeMenu();
				TelaDeMenu.AbrirTelaDeMenu();
				frame.dispose();
				
			}
		});
		btnVoltar.setBounds(747, 279, 152, 36);
		frame.getContentPane().add(btnVoltar);
		
		JComboBox txtSelecioneUmFiltro = new JComboBox();
		txtSelecioneUmFiltro.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				int opcaoDeFiltro = txtSelecioneUmFiltro.getSelectedIndex();
				txtFiltroDosCalcados.setText("");
				
				//Limpa o campo que é preenchido com algum filtro mais especifico digitado pelo usuário
				if (opcaoDeFiltro == 0 || opcaoDeFiltro == 1)
					txtFiltroDosCalcados.setEnabled(false);
				else
					txtFiltroDosCalcados.setEnabled(true);
				
			}
		});
		txtSelecioneUmFiltro.setMaximumRowCount(5);
		txtSelecioneUmFiltro.setModel(new DefaultComboBoxModel(new String[] {"Selecione", "Todos", "Tamanho", "Categoria", "Cor", "Menor Pre\u00E7o", "Marca"}));
		txtSelecioneUmFiltro.setSelectedIndex(0);
		txtSelecioneUmFiltro.setBounds(747, 59, 152, 22);
		frame.getContentPane().add(txtSelecioneUmFiltro);
		
		JButton btnFiltrar = new JButton("ADICIONAR FILTRO");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Pega o indice do JComboBox, para usar no controle dos IF's abaixo
				int opcaoDeFiltro = txtSelecioneUmFiltro.getSelectedIndex();
				
				//IF's que controlam o fluxo de ações de escolha de filtros pelo usuário
				//IF verifica se a opção selecionada é a "Selecione" e mostra uma mensagem informando o usuário para escolher um filtro
				if (opcaoDeFiltro == 0)
				{
					DefaultTableModel tabelaDeCalcados = (DefaultTableModel) tblCalcadosFiltrados.getModel();
					tabelaDeCalcados.setNumRows(0);
					JOptionPane.showMessageDialog(null,"Selecione Um Filtro Primeiro!","ATENÇÃO - Informação Inválida!",JOptionPane.WARNING_MESSAGE);
				}
				//IF verifica se a opção selecionada é a "Todos" e faz a requisição para o banco de dados com todos os calçados registrados no banco de dados
				else if (opcaoDeFiltro == 1)
				{
					try {
						//Limpa o ResultSet
						informacoesDoCalcado = null;
						
						informacoesDoCalcado = DB.filtrarTodosCalcados();
						
						PreencherTabelaDeFiltros();
						
					} catch (Exception e1) {
						//tratamento para eventuais erros 
						JOptionPane.showMessageDialog(null,"Ocorreu Um Erro Não Previsto: " + e1,"ERRO!",JOptionPane.WARNING_MESSAGE);
					}
				}
				//IF verifica se o usuário digitou algum filtro especifico 
				else if (txtFiltroDosCalcados.getText().equals(""))
					JOptionPane.showMessageDialog(null,"Indique Um Filtro Primeiro!","ATENÇÃO - Informação Faltando!",JOptionPane.WARNING_MESSAGE);
				//IF verifica se a opção selecionada é a "Tamanho" e faz a requisição para o banco de dados com o tamanho especificado por calçados registrados no banco de dados
				else if (opcaoDeFiltro == 2)
				{
					try {
						//Limpa o ResultSet
						informacoesDoCalcado = null;
						
						informacoesDoCalcado = DB.filtrarCalcadosComFiltroSelecionadoPorDadosNumericos("tamanhoCalcado", Double.valueOf(txtFiltroDosCalcados.getText().toString()));
						
						PreencherTabelaDeFiltros();
						
					} catch (Exception e1) {
						//tratamento para eventuais erros 
						JOptionPane.showMessageDialog(null,"Ocorreu Um Erro Não Previsto: " + e1,"ERRO!",JOptionPane.WARNING_MESSAGE);
					}
				}
				//IF verifica se a opção selecionada é a "Categoria" e faz a requisição para o banco de dados com a categoria especificada por calçados registrados no banco de dados
				else if (opcaoDeFiltro == 3)
				{
					try {
						//Limpa o ResultSet
						informacoesDoCalcado = null;
						
						informacoesDoCalcado = DB.filtrarCalcadosComFiltroSelecionado("categoriaCalcado", txtFiltroDosCalcados.getText().toString());
						
						PreencherTabelaDeFiltros();
						
					} catch (Exception e1) {
						//tratamento para eventuais erros 
						JOptionPane.showMessageDialog(null,"Ocorreu Um Erro Não Previsto: " + e1,"ERRO!",JOptionPane.WARNING_MESSAGE);
					}
				}
				//IF verifica se a opção selecionada é a "Cor" e faz a requisição para o banco de dados com a cor especificada por calçados registrados no banco de dados
				else if (opcaoDeFiltro == 4)
				{
					try {
						//Limpa o ResultSet
						informacoesDoCalcado = null;
						
						informacoesDoCalcado = DB.filtrarCalcadosComFiltroSelecionado("corCalcado", txtFiltroDosCalcados.getText().toString());
						
						PreencherTabelaDeFiltros();
						
					} catch (Exception e1) {
						//tratamento para eventuais erros 
						JOptionPane.showMessageDialog(null,"Ocorreu Um Erro Não Previsto: " + e1,"ERRO!",JOptionPane.WARNING_MESSAGE);
					}
				}
				//IF verifica se a opção selecionada é a "Menor Preco" e faz a requisição para o banco de dados com o menor preco especificado por calçados registrados no banco de dados
				else if (opcaoDeFiltro == 5)
				{
					try {
						//Limpa o ResultSet
						informacoesDoCalcado = null;
						
						//Trata o preço antes de ser usado na requisição para o banco de dados
						String precoTratado = txtFiltroDosCalcados.getText().toString();
						precoTratado = precoTratado.trim();
						precoTratado = precoTratado.replace("R$", "");
						precoTratado = precoTratado.replace(" ", "");
						precoTratado = precoTratado.replace(",", ".");
						
						informacoesDoCalcado = DB.filtrarCalcadosComFiltroSelecionadoPorDadosNumericos("precoCalcado", Double.valueOf(precoTratado));
						
						PreencherTabelaDeFiltros();
						
					} catch (Exception e1) {
						//tratamento para eventuais erros 
						JOptionPane.showMessageDialog(null,"Ocorreu Um Erro Não Previsto: " + e1,"ERRO!",JOptionPane.WARNING_MESSAGE);
					}
				}
				//IF verifica se a opção selecionada é a "Marca" e faz a requisição para o banco de dados com a marca especificada por calçados registrados no banco de dados
				else
				{
					try {
						//Limpa o ResultSet
						informacoesDoCalcado = null;
						
						informacoesDoCalcado = DB.filtrarCalcadosComFiltroSelecionado("marcaCalcado", txtFiltroDosCalcados.getText().toString());
						
						PreencherTabelaDeFiltros();
						
					} catch (Exception e1) {
						//tratamento para eventuais erros 
						JOptionPane.showMessageDialog(null,"Ocorreu Um Erro Não Previsto: " + e1,"ERRO!",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			
		});
		btnFiltrar.setBounds(747, 232, 152, 36);
		frame.getContentPane().add(btnFiltrar);
		
		JLabel lblNewLabel_1 = new JLabel("Digite o Que Deseja Filtrar:");
		lblNewLabel_1.setBounds(747, 112, 152, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtFiltroDosCalcados = new JTextField();
		txtFiltroDosCalcados.setEnabled(false);
		txtFiltroDosCalcados.setBounds(747, 128, 152, 20);
		frame.getContentPane().add(txtFiltroDosCalcados);
		txtFiltroDosCalcados.setColumns(10);
		
		tblCalcadosFiltrados = new JTable();
		tblCalcadosFiltrados.setCellSelectionEnabled(true);
		tblCalcadosFiltrados.setColumnSelectionAllowed(true);
		tblCalcadosFiltrados.setEnabled(false);
		tblCalcadosFiltrados.setSurrendersFocusOnKeystroke(true);
		tblCalcadosFiltrados.setRowSelectionAllowed(false);
		tblCalcadosFiltrados.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		tblCalcadosFiltrados.getColumnModel().getColumn(0).setPreferredWidth(105);
		tblCalcadosFiltrados.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblCalcadosFiltrados.getColumnModel().getColumn(2).setPreferredWidth(85);
		tblCalcadosFiltrados.getColumnModel().getColumn(3).setPreferredWidth(85);
		tblCalcadosFiltrados.getColumnModel().getColumn(4).setPreferredWidth(85);
		tblCalcadosFiltrados.getColumnModel().getColumn(5).setPreferredWidth(95);
		tblCalcadosFiltrados.getColumnModel().getColumn(6).setPreferredWidth(100);
		tblCalcadosFiltrados.getColumnModel().getColumn(7).setPreferredWidth(50);
		tblCalcadosFiltrados.getColumnModel().getColumn(8).setPreferredWidth(400);
		tblCalcadosFiltrados.setBounds(10, 43, 727, 272);
		frame.getContentPane().add(tblCalcadosFiltrados);
		
		JLabel lblNewLabel_1_1 = new JLabel("Filtro:");
		lblNewLabel_1_1.setBounds(747, 43, 142, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
	}
}
