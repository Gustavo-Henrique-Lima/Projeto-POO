package sistema_bancario.classes;
import java.io.Serializable;
import java.util.Objects;
public class Gerente implements Serializable{
	private static final long serialVersionUID = 1L;
	private String Nome;
	private String Cpf;
	private String Matricula;
	private String Email;
	private String Senha;
	public Gerente()
	{
		
	}
	public Gerente(String matricula)
	{
		this.Matricula=matricula;
	}
	public Gerente(String nome, String cpf, String matricula, String email, String senha) 
	{
		this.Nome = nome;
		this.Cpf = cpf;
		this.Matricula = matricula;
		this.Email = email;
		this.Senha = senha;
	}
	@Override
	public int hashCode() 
	{
		return Objects.hash(Matricula);
	}
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gerente other = (Gerente) obj;
		return Objects.equals(Matricula, other.Matricula);
	}
	
	public String getNome() 
	{
		return Nome;
	}
	public String getCpf() 
	{
		return Cpf;
	}
	public String getMatricula() 
	{
		return Matricula;
	}
	public String getEmail() 
	{
		return Email;
	}
	public void setNome(String nome) 
	{
		Nome = nome;
	}
	public void setCpf(String cpf) 
	{
		Cpf = cpf;
	}
	public void setMatricula(String matricula) 
	{
		Matricula = matricula;
	}
	public void setEmail(String email) 
	{
		Email = email;
	}
	@Override
	public String toString() 
	{
		return "Gerente: "+ getNome() + "\nCpf: " + getCpf() + "\nMatricula: " + getMatricula() + "\nEmail: " + getEmail();
	}
	public boolean consultarSenha(String senha) throws Exception
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
