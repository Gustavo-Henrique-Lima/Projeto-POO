package sistema_bancario.classes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	private String Nome;
	private String Cpf;
	private String Senha;
	private String Email;
	private String Endereco;
	private boolean cartao_credito;
	private Set <String> Telefones = new HashSet<>();
	private List <Conta> Contas= new ArrayList<>();
	public Cliente()
	{
		
	}
	public Cliente(String cpf)
	{
		this.Cpf=cpf;
	}
	public Cliente(String Nome, String Cpf, String Senha,String telefones,String Email,String Endereco)
	{
		this.Nome=Nome;
		this.Cpf=Cpf;
		this.Senha=Senha;
		Telefones.add(telefones);
		this.Email=Email;
		this.Endereco=Endereco;
		this.cartao_credito=false;
	}
	
	public String getNome() 
	{
		return Nome;
	}
	public String getEmail() 
	{
		return Email;
	}
	public String getCpf()
	{
		return Cpf;
	}
	public Set<String> getTelefones() 
	{
		return Telefones;
	}
	public List<Conta> getContas() 
	{
		return Contas;
	}
	public String getEndereco() 
	{
		return Endereco;
	}
	public String getTemCartao()
	{
		if(this.cartao_credito)
		{
			return "Cliente possui cartão de crédito";
		}
		else
		{
			return "Cliente não possui cartão de crédito";
		}
	}
	private void setCartao() throws Exception
	{
		if(this.cartao_credito)
		{
			throw new Exception("Cliente já possui cartão de crédito");
		}
		else
		{
			this.cartao_credito=true;
			System.out.print("Cartão de crédito contratado com sucesso");
		}
	}
	public void contratarCartao()
	{
		try
		{
			setCartao();
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
	private void setSenha(String senha)
	{
		this.Senha=senha;
	}
    public void mudarSenha(String senha)
    {
    	setSenha(senha);
    }
	
	@Override
	public String toString() 
	{
		return "Cliente: " + getNome() + "\nCpf:" + getCpf() + "\nTelefones: " + getTelefones() + "\nContas: "
				+ getContas() + "\nEmail:"+ getEmail()+ "\nEndereço:"+getEndereco()+ "\nCartão de crédito: "+getTemCartao();
	}
	@Override
	public int hashCode() 
	{
		return Objects.hash(Cpf);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(Cpf, other.Cpf);
	}
	public void inserirConta(Conta conta) throws Exception
	{
		if(Contas.contains(conta))
		{
			throw new Exception("Conta já cadastrado");
		}
		else
		{
			Contas.add(conta);
		}
	}
	public void removerConta(Conta conta) throws Exception
	{
		if(Contas.contains(conta))
		{
			Contas.remove(conta);
		}
		else
		{
			throw new Exception("Conta já removida");
		}
	}
	public void inserirTelefone(String tele) throws Exception
	{
		if(Telefones.contains(tele))
		{
			throw new Exception("Telefone já cadastrado");
		}
		else
		{
			Telefones.add(tele);
		}
	}
	public void removerTelefone(String tele) throws Exception
	{
		if(Telefones.contains(tele))
		{
			Telefones.remove(tele);
		}
		else
		{
			throw new Exception("Telefone já removido");
		}
	}
	public Conta localizarConta(String numero)
	{
		Conta temp = new Conta(numero);
		if(Contas.contains(temp))
		{
			int index = Contas.indexOf(temp);
			temp = Contas.get(index);
			return temp;
		}
		else
			return null;
	}
	public boolean consultarSenha(String senha) 
	{
		if(this.Senha.equals(senha))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
