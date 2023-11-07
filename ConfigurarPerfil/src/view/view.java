package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Position;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class view {

	private JFrame frame;
	private JTextField pesquisa;
	
	/**
	 * Pasta de rede.
	 */
	static String path = "\\\\rede\\diretorio\\local\\arquivo.csv"; // Adiocione o diretorio do arquivo csv(que contenha o nome e os diretorios das pastas).
	static String linha = "";
	static Object index;
	static BufferedReader br = null;
	static String diretorio = null;
	FileWriter arq;
	PrintWriter gravarArq;
	StringBuilder comando;
	protected static List<String> nome = new ArrayList();
	JList<?> lista;
	
	/**
	 * Impressoras.
	 */
	File arquivo = new File("\\\\rede\\diretorio\\local\\arquivo.txt"); // Diretorio de arquivo txt com as impressoras listadas em linhas.
	protected static String comandoInstala = "cmd /c start cmd.exe /c \" rundll32 printui.dll PrintUIEntry /in /n \\\\ServidorImpressora\\"; // Comando para instalacao da impressora de rede. IMPORTANTE ADICIONAR O ENDEREÇO DO SERVIDOR
	protected static String instalaImp1 = null; // Variavel de instalacao da impressora selecionada na 1ª caixa.
	boolean principal = false; // Boolean da check box para saber se quer a impressora como principal no sistema.
	protected static String comandoInstalaPrincipal = null; // comando gerado caso seja selecionado.
	protected static String instalaImp2 = null; // Variavel de instalacao da impressora selecionada na 2ª caixa.

	/**
	 * Inicio da aplicacao.
	 */
	public static void main(String[] args) {
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			  while((linha = br.readLine()) != null) {
				  String[] valores = linha.split(";");
				  nome.add(valores[0]);
				  //System.out.println("Nome pasta:" + valores[0] + " Diretorio:" + valores[1] +" Lista: " + nome); //mostragem de lista com os nomes das pastas de rede
			  }
		  } catch(FileNotFoundException e) {
			  e.printStackTrace();
		  } catch(IOException e) {
			  e.printStackTrace();
		  }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view window = new view();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void pesquisarLista(String texto){
	    int pos = lista.getNextMatch(texto, 0, Position.Bias.Forward);
	    lista.setSelectedIndex(pos);
	    lista.ensureIndexIsVisible(pos);
	  }

	/**
	 * Criando a aplicacao.
	 */
	public view() {
		initialize();
	}

	/**
	 * Inicializar as contents do frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	try {
					Runtime.getRuntime().exec(""+comandoInstalaPrincipal);
				} catch (IOException e4) {
				}
            	JOptionPane.showMessageDialog(null, "Perfil configurado!");
            	System.exit(0);
            }
		});
		frame.getContentPane().setLayout(null);
		
		/**
		 * Parte impressoras.
		 */
		
		JLabel lblNewLabel = new JLabel("Selecione a impressora que deseja");
		lblNewLabel.setBounds(10, 11, 211, 14);
		frame.getContentPane().add(lblNewLabel);
		
		final JComboBox comboBox = new JComboBox(); //Seletor da impressora que deseja instalar.
		try {  
            FileReader fr = new FileReader(arquivo);  
            BufferedReader br = new BufferedReader(fr);  
            String linha = br.readLine();
            
            while(linha != null){
                comboBox.addItem(linha);
                linha = br.readLine();
            }  
            fr.close();  
        } catch (IOException e) {
        	
        }
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instalaImp1 = (String) comboBox.getSelectedItem();
			}
		});
		comboBox.setBounds(20, 43, 161, 22);
		frame.getContentPane().add(comboBox);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Principal"); //Selecionar se quer que a impressora instalada seja a principal.
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal = chckbxNewCheckBox.isSelected();
			}
		});
		chckbxNewCheckBox.setBounds(191, 43, 97, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("Instalar");  //Botão de instalacao da impressora.
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String R1 = null;
				
				if(instalaImp1 != null) {
					if(principal == true) {
						//JOptionPane.showMessageDialog(null," " +Principal); //Teste para ver oque esta sendo Selecionado no JCheckBox.
						R1 = comandoInstala.concat(instalaImp1);
						try {
							Runtime.getRuntime().exec(""+R1 );
							R1 = R1.concat(" /y");
							comandoInstalaPrincipal = R1;
						}
						catch(IOException e1) {
						}
					} else {
						//JOptionPane.showMessageDialog(null," " +SEF1); //Teste para ver oque esta sendo Selecionado na JComboBox1.
						R1 = comandoInstala.concat(instalaImp1);
						try {
							Runtime.getRuntime().exec(""+R1);
						}
						
						catch(IOException e2) {
							
						}
					}
				}
			}
		});
		btnNewButton.setBounds(191, 88, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		/**
		 * Parte das pastas de rede.
		 */
		
		JLabel lblNewLabel_1 = new JLabel("Selecione a pasta de rede que deseja");
		lblNewLabel_1.setBounds(10, 124, 211, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lista = new JList<Object>(nome.toArray()); //Listando as pasta de rede de acordo com o arquivo csv inserido.
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				index = lista.getSelectedValue();
			}
	    });
		
		final JTextField pesquisa = new JTextField(); //JTextField para pesquisar o nome de uma pasta de rede da lista.
		pesquisa.getDocument().addDocumentListener(
			new DocumentListener(){
				public void insertUpdate(DocumentEvent e){
					
			        pesquisarLista(pesquisa.getText()); 
			    }
				
			    public void removeUpdate(DocumentEvent e){
			        pesquisarLista(pesquisa.getText());  
			    }
			 
			    public void changedUpdate(DocumentEvent e){}
			    }
			);
		pesquisa.setBounds(20, 149, 161, 20);
		frame.getContentPane().add(pesquisa);
		pesquisa.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane(lista); //Lista foi inserida num scroll panel para visualizar e navegar na procura do nome da pasta de rede.
		scrollPane.setBounds(20, 167, 161, 71);
		frame.getContentPane().add(scrollPane);
		
		JButton btnNewButton_1 = new JButton("Criar Atalho"); //Botao criar atalha ira criar criar um arquivo VBS e executar, para assim gerar o atalho na area de trabalho.
		btnNewButton_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {

	    		try {
	    			Runtime.getRuntime().exec("attrib -h \"" + System.getProperty("user.home") + "\\Documents\\AtalhoPasta.vbs\""); //O arquivo VBS sera gerado em Documents.
	    			JOptionPane.showMessageDialog(null,"Atalho pra pasta de rede " +index+" criada na �rea de trabalho"); //Mensagem de que foi gerado o atalha da pasta escolhida.
	    		} 
	    		catch(IOException e2) {
	    		}
	    			
	    		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
	    			
	    			arq = new FileWriter(System.getProperty("user.home")+"\\Documents\\AtalhoPasta.vbs"); //Inicio da criacao do script.
	    			gravarArq = new PrintWriter(arq);
	    			comando = new StringBuilder();
	    			
	    			  while((linha = br.readLine()) != null) {
	    				  String[] valores = linha.split(";");
	    				  if(valores[0].equals(""+index)) {
	    					  diretorio = valores[1];
	    					  comando.append("strAppPath = \"").append(diretorio).append("\"").append("\n") //Diretorio da pasta de rede que esta presente no arquivo csv.
	    					  .append("Set objShell = CreateObject(\"WScript.Shell\")").append("\n")
	    					  .append("objDesktop = objShell.SpecialFolders(\"Desktop\")").append("\n") //Destino onde sera gerado o atalho.
	    					  .append("Set objLink = objShell.CreateShortcut(objDesktop & \"\\"+valores[0]+".lnk\")").append("\n") //Nome da pasta de rede que sera exibido da area de trabalho.
	    					  .append("objLink.TargetPath = strAppPath").append("\n")
	    					  .append("objLink.WindowStyle = 3").append("\n")
	    					  .append("objLink.Save").append("\n")
	    					  .append("WScript.Quit"); 
	    				  }
	    					  
	    				  //System.out.println("Nome pasta:  " + valores[0] + "  Diretorio:  " + valores[1] +" Lista: " + nome); //teste
	    			  }
	    			  
	    			  String fileAsString = comando.toString();
	    	          gravarArq.printf(comando.toString());
	    	          System.out.println(fileAsString);
	    			  
	    		} catch(FileNotFoundException e1) {
	    			  e1.printStackTrace();
	    		} catch(IOException e1) {
	    			  e1.printStackTrace();
	    		}
	    		
	    		try {
					arq.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	    		
	    		try {
	    			Runtime.getRuntime().exec("wscript \"" + System.getProperty("user.home") + "\\Documents\\AtalhoPasta.vbs\""); //Rodando o script gerado para criacao do atalho.
	    			Runtime.getRuntime().exec("attrib +h \"" + System.getProperty("user.home") + "\\Documents\\AtalhoPasta.vbs\""); //depois de rodar o script o mesmo e ocultado.
	    		} 
	    		catch(IOException e2) {
	    		}
	    		
	    	}
	    });
		
		btnNewButton_1.setBounds(279, 215, 103, 23);
		frame.getContentPane().add(btnNewButton_1);
		
	}
}
