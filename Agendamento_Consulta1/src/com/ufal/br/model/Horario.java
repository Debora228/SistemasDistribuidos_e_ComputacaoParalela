package com.ufal.br.model;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Horario extends Thread {
	ReentrantLock lock = new ReentrantLock();
	private Condition condVar = lock.newCondition();
	public int PacienteNaConsulta;
	
	public Horario(){
		PacienteNaConsulta = 0;
	}
	
	public void NoConsultorio(Consulta Paciente) {
		lock.lock();
		PacienteNaConsulta = Paciente.getId();
	}
	
	public void TerminarConsulta() {
		PacienteNaConsulta = 0;
		synchronized(condVar) {
		condVar.signalAll();
		}
		lock.unlock();
	}
	
	public void EsperarConsulta(Thread t) {
		try {
			synchronized(t) {
			t.wait();
			}
		}catch (InterruptedException e) { 
			System.out.println(e);
		}
		
	}
	
	public int getPacienteNaConsulta() {
		return PacienteNaConsulta;
	}
}
