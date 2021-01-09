package com.ufal.br.model;

public class Threadex extends Thread{
	Consulta consulta;
	Horario h1;
	Horario h2;
	Horario h3;		
	public Threadex(Horario h1, Horario h2, Horario h3, Consulta consulta) {
		this.consulta = consulta;
		this.h1 = h1;
		this.h2 = h2;
		this.h3 = h3;
	}
	@Override
	public void run() {
		try {
			while (true) {
				System.out.println(consulta.getDescricao()+" "+  consulta.getNomePaciente()+" esta aguardando...");
				if(h1.getPacienteNaConsulta() == 0) {
					h1.NoConsultorio(consulta);
					System.out.println(consulta.getDescricao() +" "+  consulta.getNomePaciente()+ " está na consulta H1");
					Thread.sleep(3000);					
					h1.TerminarConsulta();
					System.out.println(consulta.getDescricao() +  " "+ consulta.getNomePaciente()+ " largou a consulta H1");
					return;
				}else if(h2.getPacienteNaConsulta() == 0) {
					h2.NoConsultorio(consulta);
					System.out.println(consulta.getDescricao() +" "+  consulta.getNomePaciente()+ " está a consulta H2");
					Thread.sleep(3000);
					h2.TerminarConsulta();
					System.out.println(consulta.getDescricao() +" "+  consulta.getNomePaciente()+ " largou a consulta H2");
					return;
				}else if(h3.getPacienteNaConsulta() == 0){
					h3.NoConsultorio(consulta);
					System.out.println(consulta.getDescricao() + " "+ consulta.getNomePaciente()+ " está na consulta H3");
					Thread.sleep(3000);
					h3.TerminarConsulta();
					System.out.println(consulta.getDescricao() +" "+  consulta.getNomePaciente()+ " largou a consulta H3");
					return; 
				}else {
					
					//devia botar ela para esperar porém nao sei como
					//Thread.onSpinWait();// nao sei se isso será disperto pelo SignalAll()
					//Thread.await();//nao funciona
					h1.EsperarConsulta(Thread.currentThread());// tá diferente na foto que te mandei
				}
			}
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
/*		
		
	
	*/
	

}
}