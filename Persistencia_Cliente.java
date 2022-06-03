package sistema_bancario.persistencia;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import sistema_bancario.classes.Cliente;

public class Persistencia_Cliente {
	List<Cliente> clientes= new ArrayList<>();
	public Persistencia_Cliente()
	{
		try
		{
			carregarDados();
		}
		catch (Exception ex)
		{
			System.out.println(ex.getStackTrace());
		}
	}
	public void adicionarCliente(Cliente cliente) throws Exception
	{
		if(clientes.contains(cliente))
		{
			throw new Exception("Cliente já cadastrado");
		}
		else
		{
			clientes.add(cliente);
			salvarDados();
		}
	}
	public void removerCliente(Cliente cliente) throws Exception
	{
		if(clientes.contains(cliente))
		{
			clientes.remove(cliente);
		}
		else
		{
			throw new Exception("Cliente já removido ou inexistente");
		}
	}
	public Cliente localizarClienteCPF(String cpf) throws Exception
	{
		Cliente temp = new Cliente(cpf);
		
		if(clientes.contains(temp)) {
			int index = clientes.indexOf(temp);
			temp = clientes.get(index);
			return temp;
		}
		else
			throw new Exception("Usuário não cadastrado");
	}
	public List<Cliente> getClientes() 
	{
		return clientes;
	}
	private void salvarDados() throws Exception
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream("clientes.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(clientes);
			oos.close();
		} 
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	private void carregarDados() throws Exception
	{
		try 
		{
			FileInputStream fis = new FileInputStream("clientes.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			clientes = (ArrayList<Cliente>) ois.readObject();
			ois.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	public void salvarDadosAposFimPrograma()
	{
		try 
		{	
			salvarDados();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getStackTrace());
		}
	}
	public void setClientes(List<Cliente> clientes) 
	{
		this.clientes = clientes;
	}
}
