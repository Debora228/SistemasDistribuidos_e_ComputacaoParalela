import java.io.Serializable;


public class Mensagem implements Serializable{
	
	private String operacao;
	private String endereco;
	private String nome;
	
	public Mensagem (String operacao) {
		this.setOperacao(operacao);
	}

	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
