import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;

public class Servidor_de_Nomes {

	public static void main(String[] args) throws ClassNotFoundException {
		ServerSocket servidor;
		Mensagem mensagem;
		
		Map<String,String> tabela = new HashMap<String,String>();
		
		try {
			servidor = new ServerSocket(1010);
			System.out.println("Servidor de Nomes pronto!");
			
			while (true){
				Socket cliente = servidor.accept();
				ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
		        ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());
		        
		        mensagem = (Mensagem) input.readObject();
		        
		        switch(mensagem.getOperacao()) {
		        case "lookup": {
		        	System.out.println("Receba os dados solicitados do serviço: " + mensagem.getNome());
		        	output.writeUTF(tabela.get(mensagem.getNome()));
		        	output.flush();
			        output.close();
			        input.close();
			        cliente.close();
			        break;
		        } case "registro": {
		        	 tabela.put(mensagem.getNome(), mensagem.getEndereco());
		        	 System.out.println("Cadastro realizado");
				     output.writeUTF("Cadastro Realizado");
				     output.flush();
				     output.close();
				     input.close();
				     cliente.close();
				     break;
		        }
		        }
		        
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
