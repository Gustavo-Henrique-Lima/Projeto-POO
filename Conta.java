package sistema_bancario.classes;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
public class Conta implements Serializable{
	private static final long serialVersionUID = 1L;
	private String NumeroConta;
	private String Agencia;
	private float Saldo;
	private boolean Status;
	private Date DataDeAbertura;
	public Conta(String NumeroConta)
	{
		this.NumeroConta=NumeroConta;
	}
	public Conta()
	{
		this.NumeroConta=gerarNumeroConta();
		this.Agencia="3";
		this.Saldo=0f;
		this.Status=true;
		this.DataDeAbertura=new Date();
	}
	
	public String getNumeroConta() 
	{
		return NumeroConta;
	}
	public String getAgencia() 
	{
		return Agencia;
	}
	public float getSaldo() 
	{
		return Saldo;
	}
	public boolean isStatus() 
	{
		return Status;
	}
	public Date getDataDeAbertura() 
	{
		return DataDeAbertura;
	}
	private void setSaldo(float saldo) 
	{
		Saldo = saldo;
	}
	private void setStatus(boolean status) 
	{
		Status = status;
	}
	public String status()
	{
		if(isStatus())
		{
			return "Conta ativa";
		}
		else
		{
			return "Conta desativada";
		}
	}
	@Override
	public int hashCode() 
	{
		return Objects.hash(NumeroConta);
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
		Conta other = (Conta) obj;
		return Objects.equals(NumeroConta, other.NumeroConta);
	}
	@Override
	public String toString() 
	{
		return "\nConta:" + getNumeroConta() + "\nAgência: " + getAgencia() + "\nSaldo: " + getSaldo() + "\nStatus: " + status()
				+ "\nData de abertura: " + getDataDeAbertura();
	}
	private String gerarNumeroConta()
	{
		String NumeroConta="";
		for(int i=0;i<=5;i++)
		{
			int numero=((int)(10*Math.random()));
			NumeroConta+=numero;
		}
		return NumeroConta;
	}
	public void sacarValor(float quantia) throws Exception
	{
		if(quantia<=this.Saldo && this.Status)
		{
			setSaldo(getSaldo()-quantia);
		}
		else if(quantia>this.Saldo)
		{
			throw new Exception("Valor indisponível para saque");
		}
		else if(isStatus()==false)
		{
			throw new Exception("A conta se encontra desativada");
		}
	}
	public void depositarValor(float quantia) throws Exception
	{
		if(quantia>0 && isStatus())
		{
			setSaldo(getSaldo()+quantia);
		}
		else if(quantia<=0)
		{
			throw new Exception("A quantia de deposito deve ser maior que 0");
		}
		else if(Status==false)
		{
			throw new Exception("A conta se encontra desativada");
		}
	}
	public void desativarConta(Conta conta) throws Exception
	{
		if(conta.Status && this.Saldo==0)
		{
			setStatus(false);
		}
		else if(this.Saldo>0)
		{
			throw new Exception("Antes de encerrar sua conta você deve zerar o seu saldo");
		}
		else
		{
			throw new Exception("Conta já se encontra desativada");
		}
	}
}
