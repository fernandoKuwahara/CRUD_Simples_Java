import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaDeMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeMenu window = new TelaDeMenu();
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
	public TelaDeMenu() {
		initialize();
	}
	
	//Método que abre o frame específico
	public void AbrirTelaDeMenu()
	{
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 265);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MENU DO SISTEMA:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(26, 25, 147, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCadastrarCalcado = new JButton("CADASTRAR CAL\u00C7ADO");
		btnCadastrarCalcado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Abre a tela de cadastro para poder cadastrar um novo calcado
				TelaDeCadastro TelaDeCadastro = new TelaDeCadastro();
				TelaDeCadastro.AbrirTelaDeCadastro();
				frame.dispose();
				
			}
		});
		btnCadastrarCalcado.setBounds(42, 76, 186, 41);
		frame.getContentPane().add(btnCadastrarCalcado);
		
		JButton btnEditarInformacoes = new JButton("EDITAR INFORMA\u00C7\u00D5ES");
		btnEditarInformacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Abre a tela de edicao para poder editar/atualiza os dados de um calcado
				TelaDeEdicao TelaDeEdicao = new TelaDeEdicao();
				TelaDeEdicao.AbrirTelaDeEdicao();
				frame.dispose();
				
			}
		});
		btnEditarInformacoes.setBounds(280, 76, 178, 41);
		frame.getContentPane().add(btnEditarInformacoes);
		
		JButton btnExcluirCalCado = new JButton("EXCLUIR CAL\u00C7ADO");
		btnExcluirCalCado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Abre a tela de exclusao para poder excluir um calcado
				TelaDeExclusao TelaDeExclusao = new TelaDeExclusao();
				TelaDeExclusao.AbrirTelaDeExclusao();
				frame.dispose();
				
			}
		});
		btnExcluirCalCado.setBounds(42, 148, 186, 41);
		frame.getContentPane().add(btnExcluirCalCado);
		
		JButton btnConsultarEstoque = new JButton("CONSULTAR ESTOQUE");
		btnConsultarEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Abre a tela de consulta para poder consultar os calcados registrados no banco de dados
				TelaDeConsulta TelaDeConsulta = new TelaDeConsulta();
				TelaDeConsulta.AbrirTelaDeConsulta();
				frame.dispose();
				
			}
		});
		btnConsultarEstoque.setBounds(280, 148, 178, 41);
		frame.getContentPane().add(btnConsultarEstoque);
	}

}
