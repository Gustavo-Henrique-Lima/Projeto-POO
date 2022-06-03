package sistema_bancario.persistencia;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import sistema_bancario.classes.Gerente;
public class Persistencia_Gerente {
	List<Gerente> gerentes= new ArrayList<>();
	public Persistencia_Gerente()
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
	public void adicionarGerente(Gerente gerente) throws Exception
	{
		if(gerentes.contains(gerente))
		{
			throw new Exception("Gerente já cadastrado");
		}
		else
		{
			gerentes.add(gerente);
			salvarDados();
		}
	}
	public void removerGerente(Gerente gerente) throws Exception
	{
		if(gerentes.contains(gerente))
		{
			gerentes.remove(gerente);
		}
		else
		{
			throw new Exception("Gerente já removido ou inexistente");
		}
	}
	public Gerente localizarGerenteMatricula(String matricula) throws Exception
	{
		Gerente temp = new Gerente(matricula);
		if(gerentes.contains(temp)) {
			int index = gerentes.indexOf(temp);
			temp = gerentes.get(index);
			return temp;
		}
		else
			throw new Exception("Usuário não cadastrado");
	}
	private void salvarDados() throws Exception
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream("gerentes.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(gerentes);
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
			FileInputStream fis = new FileInputStream("gerentes.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			gerentes = (ArrayList<Gerente>) ois.readObject();
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
	public List<Gerente> getGerentes() 
	{
		return gerentes;
	}
}
