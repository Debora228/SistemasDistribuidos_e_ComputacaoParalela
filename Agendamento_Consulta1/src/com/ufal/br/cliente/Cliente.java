package com.ufal.br.cliente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ufal.br.model.Consulta;

public class Cliente {
	public Consulta agendarConsulta(String nomePaciente, String descricao, int id) {
		
		Consulta consulta = new Consulta(id,nomePaciente,descricao);
		
		return consulta;
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		try{			
			System.out.println("Digite o código do cliente: ");			
			Scanner ler = new Scanner(System.in);
			String nomeCliente = ler.next();
			for(int i = 0; i<=10; i++) {
				Socket socket = new Socket("localhost", 5555);
				Cliente cliente = new Cliente();
				
				//instânciando o recebimento
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				//instânciando o envio
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				
				Consulta consulta = new Consulta(i+1, "Paciente " + i, nomeCliente);
				Thread.sleep(1000);
				output.writeObject(consulta);
				output.flush();
				
				input.close();
				output.close();
			}
			
			
		}catch(IOException e) {
			//tratando o erro
			System.out.println("Erro no cliente: " + e);
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}

}
