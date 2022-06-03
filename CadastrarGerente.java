package sistema_bancario.main;

import sistema_bancario.classes.Gerente;
import sistema_bancario.persistencia.Persistencia_Gerente;

public class CadastrarGerente {
	public static void main(String[] args) 
	{
		Persistencia_Gerente pg = new Persistencia_Gerente();
		Gerente gerente = new Gerente("Gustavo", "357", "2021", "g@bancogh.com", "987");
		try {
			pg.adicionarGerente(gerente);
			pg.salvarDadosAposFimPrograma();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
