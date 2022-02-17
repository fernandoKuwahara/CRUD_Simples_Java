import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.awt.event.ItemEvent;

public class TelaDeExclusao {

	private JFrame frame;
	private JComboBox<String> txtCalcadosCadastrados1;
	MySQLAccess DB = new MySQLAccess();
	TelaDeMenu TelaDeMenu = new TelaDeMenu();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeExclusao window = new TelaDeExclusao();
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
	public TelaDeExclusao() {
		initialize();
	}
	
	//Abre o frame especifico e atualiza o JComboBox com o(s) nome(s) do(s) calçado(s) no banco de dados
	public void AbrirTelaDeExclusao()
	{
		frame.setVisible(true);
		List<String> nomes;
		try {
			nomes = DB.pegarNomeDosCalcados();
			for (String nome : nomes) 
			{
				txtCalcadosCadastrados1.addItem(nome);
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
		frame.setBounds(100, 100, 450, 235);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTelaDeExcluso = new JLabel("Tela de Exclus\u00E3o:");
		lblTelaDeExcluso.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblTelaDeExcluso.setBounds(10, 11, 149, 21);
		frame.getContentPane().add(lblTelaDeExcluso);
		
		JLabel lblNewLabel_1 = new JLabel("Selecione o Cal\u00E7ado Para Excluir:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setBounds(23, 55, 208, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//IF que verifica se o usuário selecionou um calçado antes de poder executar a ação de exclusão
				if (txtCalcadosCadastrados1.getSelectedIndex() == 0) 
					JOptionPane.showMessageDialog(null,"Selecione Um Calçado Para Poder Excluir!","ATENÇÃO - Informação Inválida!",JOptionPane.WARNING_MESSAGE);
				else
				{
					//try catch que faz uma requisição para o banco de dados e retorna uma resposta informando que o calçado foi excluido do banco de dados com sucesso
					try {
						String registroCalcado = DB.deletarCalcado(txtCalcadosCadastrados1.getSelectedItem().toString());
						JOptionPane.showMessageDialog(null,registroCalcado,"ATENÇÃO:",JOptionPane.WARNING_MESSAGE);
						TelaDeMenu.AbrirTelaDeMenu();
						frame.dispose();
					} catch (Exception e1) {
						//tratamento para eventuais erros 
						JOptionPane.showMessageDialog(null,"Ocorreu Um Erro Não Previsto: " + e1,"ERRO!",JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		});
		btnExcluir.setBounds(284, 74, 125, 37);
		frame.getContentPane().add(btnExcluir);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Abre a tela de menu, e fecha este frame atual
				TelaDeMenu.AbrirTelaDeMenu();
				frame.dispose();
				
			}
		});
		btnVoltar.setBounds(284, 132, 125, 37);
		frame.getContentPane().add(btnVoltar);
		
		txtCalcadosCadastrados1 = new JComboBox();
		txtCalcadosCadastrados1.setMaximumRowCount(5);
		txtCalcadosCadastrados1.setModel(new DefaultComboBoxModel(new String[] {"Selecione"}));
		txtCalcadosCadastrados1.setSelectedIndex(0);
		txtCalcadosCadastrados1.setBounds(23, 74, 225, 22);
		frame.getContentPane().add(txtCalcadosCadastrados1);
	}

}
