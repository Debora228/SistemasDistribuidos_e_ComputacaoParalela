import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static String lookup (String valor){
		String endereco = "";
		Mensagem mensagem = new Mensagem(valor);
		mensagem.setNome("validacpf");
		
		try {
		Socket cliente = new Socket("localhost",1010);
		ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());
		output.writeObject(mensagem);
		output.flush();
		
		String msg = input.readUTF();
		endereco = msg;
		System.out.println("Endereço recebido: "+ endereco);
		output.close();
		input.close();
		cliente.close();
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return endereco;
	}
	public static void main(String[] args){
		// TODO Auto-generated method stub
		String endereco = lookup("lookup");
		String[] strings = endereco.split("\\:");
		String enderecoValidacao = strings[0];
		int portValidacao = Integer.parseInt(strings[1]);
		
		try {
			Socket clienteValidacao = new Socket(enderecoValidacao,portValidacao);
			ObjectOutputStream output = new ObjectOutputStream(clienteValidacao.getOutputStream());
	        ObjectInputStream input = new ObjectInputStream(clienteValidacao.getInputStream());
			String cpf = "10472814494";
			output.writeUTF(cpf);
			output.flush();
			
			String mensagemRecebida = input.readUTF();
			System.out.println("Mensagem Recebida: "+ mensagemRecebida);
			
			output.close();
			input.close();
			clienteValidacao.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
