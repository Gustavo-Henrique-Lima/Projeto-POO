package sistema_bancario.main;
import java.util.Scanner;
import sistema_bancario.classes.Cliente;
import sistema_bancario.classes.Conta;
import sistema_bancario.classes.Gerente;
import sistema_bancario.persistencia.Persistencia_Cliente;
import sistema_bancario.persistencia.Persistencia_Gerente;
/*Clientes
//35 123 613241 
123 123 517044*/
public class Programa {
	public static void main(String[] args) {
		Scanner entrada= new Scanner(System.in);
		Scanner ler=new Scanner(System.in);
		byte escolha;
		Persistencia_Cliente pc= new Persistencia_Cliente();
		Persistencia_Gerente pg = new Persistencia_Gerente();
		System.out.println("Bem vindo ao Banco GH\nFique a vontade para usufruir nossos serviços :)");
		System.out.println("[1] Para abrir uma conta     [2] Efetuar login");
		System.out.println("[3] Gerência   [4] Encerrar aplicação");
		System.out.println("Digite o número de escolha e aperte a tecla ENTER");
		escolha=entrada.nextByte();
		while(escolha!=4) 
		{
			switch (escolha) 
			{
				case 1:
					String nome;
					String cpf;
					String telefone;
					String senha;
					String email;
					String endereco;
					System.out.println();
					System.out.println("Obrigado por escolher nosso banco, ficamos muito felizes com isso!");
					System.out.println("Para começarmos, informe o seu nome: ");
					nome=ler.nextLine();
					System.out.println("Olá, "+nome+" sou seu assistente virtual, Max, para continuarmos me informe o seu cpf: ");
					cpf=entrada.next();
					System.out.println("Agora, insira sua senha, ela será usada para você acessar o app, como também para validar movimentações");
					senha=entrada.next();
					System.out.println("Estamos quase lá, me diga o número do seu aparelho celular: ");
					telefone=entrada.next();
					System.out.println("Endereco");
					endereco=ler.nextLine();
					System.out.println("Para finalizarmos me diga seu email para contato: ");
					email=entrada.next();
					Cliente cliente=new Cliente(nome,cpf,senha,telefone,email,endereco);
					Conta conta=new Conta();
					try
					{
						cliente.inserirConta(conta);
					}
					catch (Exception e) 
					{
						System.out.println(e.getMessage());
					}
					try 
					{
						pc.adicionarCliente(cliente);
						System.out.println(nome+", agora você é oficialmente membro do banco GH");
						System.out.println("Esses são os dados da sua conta, salve eles, pois será a forma de você ter acesso ao app :) .");
						System.out.println(cliente);
						System.out.println("Por questões de segurança você será redirecionado para o menu principal, para ter acesso a sua conta que já se encontra ativa, basta\nefetuar o login");
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				break;
				case 2:
					Cliente usuario;
					Conta conta_usuario;
					String numero;
					System.out.println("Olá eu sou o Max o assistente virtual do banco GH, seja em vindo! Para efetuar login me informe seu cpf: ");
					cpf=entrada.next();
					try
					{
						usuario=pc.localizarClienteCPF(cpf);
						System.out.println("Insira a sua senha");
						boolean verificador=false;
						senha=entrada.next();
						verificador=usuario.consultarSenha(senha);
						if(verificador)
						{
							System.out.println("Agora só preciso do número da sua conta para que você consiga acessar o app");
							numero=entrada.next();
							conta_usuario=usuario.localizarConta(numero);
							if(conta_usuario!=null)
							{
								byte opcao=-1;
								while(opcao!=0)
								{
									System.out.println("Bem vindo "+usuario.getNome());
									System.out.println("Voce dispõe dos seguintes serviços");
									System.out.println("[1] Consultar saldo   [2] Realizar depósito");
									System.out.println("[3] Realizar saque   [4] Desativar conta");
									System.out.println("[5] Abrir nova conta   [6] Excluir meu cadastro");
									System.out.println("[7] Consultar contas no meu nome   [8] Solicitar cartão de crédito");
									System.out.println("[9] Acessar meu dados  [10] Atualizar telefone para contato");
									System.out.println("[0] Encerrar sessão");
									System.out.println("Digite o número de escolha e aperte a tecla ENTER");
									opcao=entrada.nextByte();
									switch (opcao) 
									{
									case 0:
										opcao=0;
										System.out.println("Você saiu da sua conta com sucesso");
										break;
									case 1:
										System.out.printf(usuario.getNome()+", o saldo atual da sua conta é: %.2f%n R$",conta_usuario.getSaldo());
										break;
									case 2:
										System.out.println("Informe o valor para depósito: ");
										float valor=entrada.nextFloat();
										try
										{
											conta_usuario.depositarValor(valor);
										}
										catch (Exception e) 
										{
											System.out.println(e.getMessage());
										}
										pc.salvarDadosAposFimPrograma();
										System.out.println();
										break;
									case 3:
										System.out.println("Informe a sua senha: ");
										String password=entrada.next();
										boolean autenticador=usuario.consultarSenha(password);
										if(autenticador)
										{
											System.out.println("Informe o valor a ser sacado:");
											float quantia=entrada.nextFloat();
											try
											{
												conta_usuario.sacarValor(quantia);
												pc.salvarDadosAposFimPrograma();
											}
											catch (Exception e) 
											{
												System.out.println(e.getMessage());
											}
										}
										else
										{
											System.out.println("Senha inválida");
										}
										break;
									case 4:
										System.out.println("Para desativar sua conta, informe sua senha: ");
										String Password=entrada.next();
										boolean Autenticador=usuario.consultarSenha(Password);
										if(Autenticador)
										{
											try
											{
												conta_usuario.desativarConta(conta_usuario);
												usuario.removerConta(conta_usuario);
												pc.salvarDadosAposFimPrograma();
												System.out.println("Conta desativada com sucesso");
												opcao=0;
											}
											catch(Exception e)
											{
												System.out.println(e.getMessage());
											}
										}
										else
										{
											System.out.println("Senha inválida");
										}
										break;
									case 5:
										Conta nova_conta=new Conta();
										usuario.inserirConta(nova_conta);
										pc.salvarDadosAposFimPrograma();
										System.out.println("O número da sua nova conta é:"+nova_conta.getNumeroConta()+", vale lembrar que a sua senha de acesso permanece a mesma :)");
										break;
									case 6:
										System.out.println("Informe sua senha: ");
										String Senha=entrada.next();
										boolean validacao=usuario.consultarSenha(Senha);
										if(validacao)
										{
											pc.removerCliente(usuario);
											pc.salvarDadosAposFimPrograma();
											System.out.println("Cadastrado excluído com sucesso");
											opcao=0;
										}
										else
										{
											System.out.println("Senha inválida");
										}
										break;
									case 7:
										System.out.println("Essa são todas as contas vinculadas ao cliente, "+usuario.getNome()+", portador do CPF: "+usuario.getCpf());
										for(Conta contas: usuario.getContas())
										{
											System.out.println(contas);
										}
										break;
									case 8:
										try
										{
											usuario.contratarCartao();
											System.out.println("O limite do seu cartão é:"+conta_usuario.getSaldo()*2.5+"R$");
											pc.salvarDadosAposFimPrograma();
										}
										catch (Exception e) 
										{
											System.out.println(e.getMessage());
										}
										break;
									case 9:
										System.out.println(usuario);
										break;
									case 10:
										System.out.println("[1] Para remover algum número   [2] Para inserir novo número");
										System.out.println("Digite o número de escolha e aperte a tecla ENTER");
										byte opc=entrada.nextByte();
										if(opc==1)
										{
											System.out.println("Insira o número que deseja remover");
											String numero_telefone=entrada.next();
											try
											{
												usuario.removerTelefone(numero_telefone);
												System.out.println("Telefone removido com sucesso");
												pc.salvarDadosAposFimPrograma();
											}
											catch (Exception e) 
											{
												System.out.println(e.getMessage());
											}
											
										}
										else if(opc==2)
										{
											System.out.println("Insira o número que deseja inserir");
											String numero_telefone=entrada.next();
											try
											{
												usuario.inserirTelefone(numero_telefone);
												System.out.println("Telefone cadastrado com sucesso");
												pc.salvarDadosAposFimPrograma();
											}
											catch (Exception e) 
											{
												System.out.println(e.getMessage());
											}
										}
										else
										{
											System.out.println("Opção inválida");
										}
										break;
									default:
										System.out.println("Opção inválida");
										break;
									}
								}
							}
							else
							{
								System.out.println("Há algo de errado com sua conta, tente novamente.");
							}
						}
						else
						{
							System.out.println("Senha inválida");
						}
						
					}
					catch(Exception exc)
					{
						System.out.println(exc.getMessage());
					}
					
					break;
				case 3:
					Gerente gerente;
					System.out.println("Informe sua matrícula: ");
					String matricula=entrada.next();
					try
					{
						gerente=pg.localizarGerenteMatricula(matricula);
						System.out.println("Insira sua senha: ");
						String senhaa=entrada.next();
						boolean validador=false;
						validador=gerente.consultarSenha(senhaa);
						if(validador)
						{
							byte opcao=0;
							while(opcao!=8)
							{
								System.out.println("[1] Listar todos os clientes   [2] Localizar cliente por cpf");
								System.out.println("[3] Desativar conta   [4] Redefinir senha do cliente");
								System.out.println("[5] Abrir conta   [6] Excluir cadastro do cliente");
								System.out.println("[7] Abrir conta para cliente já cadastrado   [8] Encerrar sessão");
								opcao=entrada.nextByte();
								switch (opcao) 
								{
									case 1:
										System.out.println("Esses são todos os clientes do banco GH");
										for(Cliente clientes: pc.getClientes())
										{
											System.out.println(clientes);
										}
									break;
									case 2:
										System.out.println("Digite o cpf do cliente");
										String cpf_cliente=entrada.next();
										try 
										{
											Cliente cliente_cpf=pc.localizarClienteCPF(cpf_cliente);
											System.out.println(cliente_cpf);
										} 
										catch (Exception e) 
										{
											e.printStackTrace();
										}	
									break;
									case 3:
										System.out.println("Insira o cpf do cliente: ");
										String CPF=entrada.next();
										try
										{
											Cliente clientee=pc.localizarClienteCPF(CPF);
											System.out.println("Insira o número da conta que você deseja desativar: ");
											String numer=entrada.next();
											Conta cont_cliente=clientee.localizarConta(numer);
											cont_cliente.desativarConta(cont_cliente);
											clientee.removerConta(cont_cliente);
											pc.salvarDadosAposFimPrograma();
										}
										catch (Exception e) 
										{
											System.out.println(e.getMessage());
										}
									break;
									case 4:
										System.out.println("Insira o cpf do cliente: ");
										String Cp=entrada.next();
										try
										{
											Cliente clientee=pc.localizarClienteCPF(Cp);
											System.out.println("Insira a nova senha do cliente: ");
											String nova_senha=entrada.next();
											clientee.mudarSenha(nova_senha);
											pc.salvarDadosAposFimPrograma();
											System.out.println("Senha alterada com sucesso");
										}
										catch (Exception e) 
										{
											System.out.println(e.getMessage());
										}
									break;
									case 5:
										String nome_cliente;
										String cpf_novo_cliente;
										String telefone_cliente;
										String senha_cliente;
										String endereco_cliente;
										String email_cliente;
										System.out.println();
										System.out.println("Para começarmos, informe o nome do cliente: ");
										nome_cliente=ler.nextLine();
										System.out.println("Para continuarmos me informe o seu cpf do cliente: ");
										cpf_novo_cliente=entrada.next();
										System.out.println("Cadastro de senha do usuário, vale lembrar que ela será usada para acessar o app, como também para validar movimentações");
										senha_cliente=entrada.next();
										System.out.println("Estamos quase lá, me diga o número do aparelho celular do cliente: ");
										telefone_cliente=entrada.next();
										System.out.println("Endereco");
										endereco_cliente=ler.nextLine();
										System.out.println("Para finalizarmos me diga o email para contato: ");
										email_cliente=entrada.next();
										Cliente novo_cliente=new Cliente(nome_cliente,cpf_novo_cliente,senha_cliente,telefone_cliente,email_cliente,endereco_cliente);
										Conta nova_conta=new Conta();
										try
										{
											novo_cliente.inserirConta(nova_conta);
										}
										catch (Exception e) 
										{
											System.out.println(e.getMessage());
										}
										try 
										{
											pc.adicionarCliente(novo_cliente);
											System.out.println("Conta aberta com sucesso");
											System.out.println("Informe ao cliente os seguintes dados, senha: "+senha_cliente+", número da conta: "+nova_conta.getNumeroConta());
										}
										catch(Exception e)
										{
											System.out.println(e.getMessage());
										}
										break;
									case 6:
										System.out.println("Insira o cpf do cliente: ");
										String cp=entrada.next();
										Cliente c1;
										try 
										{
											c1 = pc.localizarClienteCPF(cp);
											try
											{
												pc.removerCliente(c1);
												pc.salvarDadosAposFimPrograma();
												System.out.println("Cliente excluído com sucesso");
											}
											catch (Exception e) 
											{
												
											}
										} 
										catch (Exception e1) 
										{
											e1.printStackTrace();
										}
										break;
									case 7:
										System.out.println("Informe o cpf do cliente: ");
										String cpff=entrada.next();
										try
										{
											Cliente cl=pc.localizarClienteCPF(cpff);
											Conta new_conta= new Conta();
											cl.inserirConta(new_conta);
											pc.salvarDadosAposFimPrograma();
											System.out.println("Conta aberta com sucesso!");
											System.out.println("O número da nova conta do cliente é: "+new_conta.getNumeroConta());
										}
										catch (Exception e) 
										{
											System.out.println(e.getMessage());
										}
										
										break;
									case 8:
										opcao=8;
										System.out.println("Sessão encerrada com sucesso!");
										break;
									default:
										System.out.println("Opção inválida");
									break;
							}
						}
						}
						else
						{
							System.out.println("Senha inválida");
						}
					}
					catch (Exception e) 
					{
						System.out.println(e.getMessage());
					}
				default:
					System.out.println("Opção inválida, tente novamente");
				break;
			}
			System.out.println();
			System.out.println("Bem vindo ao Banco GH\nFique a vontade para usufruir nossos serviços :)");
			System.out.println("[1] Para abrir uma conta     [2] Efetuar login");
			System.out.println("[3] Gerência   [4] Encerrar aplicação");
			System.out.println("Digite o número de escolha e aperte a tecla ENTER");
			escolha=entrada.nextByte();
	    }
		entrada.close();
		ler.close();
	}
}
