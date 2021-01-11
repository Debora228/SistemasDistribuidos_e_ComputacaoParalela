
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class Servidor_Validacao {

	public static boolean ValidarCpf(String cpf) {

		if (cpf.equals("00000000000") ||
				cpf.equals("11111111111") ||
				cpf.equals("22222222222") || cpf.equals("33333333333") ||
				cpf.equals("44444444444") || cpf.equals("55555555555") ||
				cpf.equals("66666666666") || cpf.equals("77777777777") ||
				cpf.equals("88888888888") || cpf.equals("99999999999") ||
	            (cpf.length() != 11))
	            return(false);
		
		int[] cpf2 = new int[11];
		
		for(int i=0; i<=10; i++) {
			String letra = "" + cpf.charAt(i);
			int num = Integer.parseInt(letra);
			cpf2[i]= num;			
		}
		
		int soma = 0; 
		for(int i=0; i<=8; i++) {			
			int num = 10 - i;
			soma = soma + cpf2[i]*num;
		}
		
		soma = soma*10;
		int resto = soma%11;
		
		if(resto == 10) {
			resto = 0;
		}
		
		if(resto != cpf2[9]) {
			return false;
		}else {
			int soma2 = 0; 
			
			for(int i=0; i<=9; i++) {			
				int num = 11 - i;
				soma2 = soma2 + cpf2[i]*num;
			}
			soma2 = soma2*10;
			
			int resto2 = soma2%11;
			
			if(resto2 == 10) {
				resto2 = 0;
			}
			
			if(resto2 != cpf2[10]) {
				return false;
			}else { 
				return true;
			}
		}
	}
	
	public static void enviarRegistro(String operacao) {
		try {
			Mensagem mensagem= new Mensagem(operacao);
			mensagem.setEndereco("localhost:1000");
			mensagem.setNome("validacpf");
			Socket cliente = new Socket("localhost", 1010);
			ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());
			output.writeObject(mensagem);
			output.flush();
			String msgReply = input.readUTF();
			System.out.println("Mensagem recebida: "+ msgReply);
			output.close();
			input.close();
			cliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket servidorCpf;
		
		enviarRegistro("registro");
		
		servidorCpf = new ServerSocket(1000);
		System.out.println("Servidor de Validacao de CPF pronto");
		while(true) {
			System.out.println("Aguardando clientes");
			Socket cliente = servidorCpf.accept();
			ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
	        ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());
	        
	        String CPF = input.readUTF();
	        
	        try{
	        	
	        	ValidarCpf(CPF);
	        	output.writeUTF("Cpf confirmado! - " + CPF);
	        	output.flush();
	        	System.out.println("Conexão encerrada");
		        output.close();
		        input.close();
		        cliente.close();
	       } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
