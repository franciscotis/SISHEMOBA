package br.uefs.ecomp.hemoba.main.view;
//Importações
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import br.uefs.ecomp.hemoba.main.controller.Controller;
import br.uefs.ecomp.hemoba.main.model.Doacao;
import br.uefs.ecomp.hemoba.main.model.Doador;
import br.uefs.ecomp.hemoba.main.model.Posto;
import br.uefs.ecomp.hemoba.main.util.Console;
import br.uefs.ecomp.hemoba.main.util.Iterador;

/**
 * 
 * @author Francisco Pereira (16111203)
 */

public class Principal { 
    private static int matricula= 0; //Atributo que vai servir para gerar a matricula do doador
    private static int matriculaposto=0; //Atributo que vai servir para gerar o numero do posto
    private static Controller controller = new Controller(); //Construtor

    /**
     *
     * @throws IOException
     * @throws ParseException
     */
    

    public static void Cadastro() throws IOException, ParseException { //Menu de cadastro
        int a;
        System.out.println("O que você gostaria:\n"
                + "[1] - Cadastrar Doador \n"
                + "[2] - Cadastrar Posto\n"
                + "[3] - Cadastrar Doação\n"
                + "[4] - Realizar Doação\n");
        a = Console.readInt();
        switch (a) {
            case 1: //Caso o usuario queira cadastrar o doador
                matricula++; //Acrescendo 1 ao numero de matricula 
                String datarecebida="";
                DateFormat df;
                //Perguntas
                System.out.println("Digite o seu nome");
                String nome = Console.readString();
                System.out.println("Digite o seu endereço");
                String endereco = Console.readString();
                System.out.println("Digite o seu telefone");
                String telefone = Console.readString();
                Date datanasc = new Date(); //Crio um novo objeto do tipo Date
                System.out.println("Digite a sua data de nascimento");
                datarecebida = Console.readString(); //Crio uma String que irá armazenar a data 
                 df = new SimpleDateFormat("dd/MM/yyyy"); //Digo como vai ser o formato da data
                datanasc = df.parse(datarecebida); //Converto a String para  Date 
                String responsavelDoador; 
                int idade = calculaIdade(datarecebida); //idade vai receber o retorno do método calculaIdade
                if (idade >= 18) { //caso seja maior ou igual a 18
                    responsavelDoador = nome; //o responsável é o doador
                } else { //caso contrário
                    System.out.println("Digite o nome do responsável pelo doador");
                    responsavelDoador = Console.readString();
                }
                System.out.println("Digite o seu peso"); 
                int peso = Console.readInt();
                controller.cadastrarDoador(nome, endereco, telefone, datanasc, peso, responsavelDoador,matricula);
               //Cadastro o doador
                System.out.println("A sua matrícula é : " + matricula + " Não esqueça!");
                break;
            case 2: //Caso o usuário queira cadastrar o posto
                matriculaposto++; //Acrescento 1 ao número de posto
                //Perguntas
                System.out.println("Digite o endereço do posto");
                String enderecoposto = Console.readString();
                System.out.println("Digite o telefone do posto");
                String telefoneposto = Console.readString();
                System.out.println("Digite o nome do responsável pelo posto");
                String responsavelposto = Console.readString();
                controller.cadastrarPosto(enderecoposto, telefoneposto, responsavelposto,matriculaposto);
                //Cadastro o posto
                System.out.println("O número do posto é " + matriculaposto + " Não esqueça!");
                break;
            case 3: //Caso o usuário deseja fazer uma doação
                 System.out.println("Digite o número do posto");
                int num = Console.readInt();
                Posto pt = controller.obterPosto(num); //pt recebe o posto com o numero de posto informado
                do{
                if(pt==null){ //Se o posto não existir
                    System.out.println("Não existe posto com esse número,digite outro numero");
                    num = Console.readInt();
                    pt = controller.obterPosto(num);
                }
                }while(pt==null);
                System.out.println("Digite a matrícula do doador");
                int mat = Console.readInt();
                Doador mt = controller.obterDoador(mat);
                do {
                    if (mt == null) { //Se o doador não existir
                        System.out.println("Não tem doador cadastrado com essa matrícula, por favor, tente novamente!");
                        mat = Console.readInt();
                        mt = controller.obterDoador(mat);
                    }
                } while (mt == null);
                if(controller.obterDoador(mat).getPeso()<50){ //Caso o doador tiver menos de 50kg ele não pode doar
                    System.out.println("Você tem menos de 50kg, logo não pode doar");
                    break;
                }
                System.out.println("Digite a data da doação");
                Date datadoacao = new Date(); //Crio um novo objeto do tipo Date
                String datar = Console.readString(); //Crio uma String que recebe a data
                DateFormat dfr = new SimpleDateFormat("dd/MM/yyyy"); //Digo o formato da data
                datadoacao = dfr.parse(datar); //Transformo a String em Date
                System.out.println("Digite a hora da doação");
                int hora = Console.readInt();
                do { //Irá fazer isso enquanto a hora informada for diferente da cadastrada no dia e no posto
                    if (controller.horaEDataDoacao(hora,datadoacao,pt)) {
                        System.out.println("A hora já foi cadastrada para esse dia e posto,"
                                + " digite outra hora");
                        hora = Console.readInt();
                    }
                } while (controller.horaEDataDoacao(hora,datadoacao,pt));
               
                controller.cadastrarDoacao(datadoacao, hora, pt, mt, false);
                //Cadastra a doação
                break;
            case 4: //Caso o usuario queira fazer a doação
                System.out.println("Digite  sua matricula");
                int matriculadoacao = Console.readInt();
                Doador pd = controller.obterDoador(matriculadoacao);
                if (controller.obterDoacao(pd)!= null) { //Se existir a doação cadastrada
                    Doacao temp = controller.obterDoacao(pd);
                    temp.setStatus(true); //muda o status para true
                    System.out.println("Doação realizada com sucesso!");
                } else { //caso contrário ele não muda o status
                    System.out.println("Não existe doador com esse número de matrícula");
                }
                break;

            default: //Caso o usuario não coloca a opção correta
                System.out.println("Digite uma opção correta!");

        }
    }

    public static int calculaIdade(String data) throws ParseException { //metodo que calcula a idade do usuario a partir da classe date
        int idade = 0; //inicializo o atributo idade
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); //É dito como vai ser o formato da idade
        Date data1 = df.parse(data); //transformo a String em data
        Date data2 = new Date(); //pego a data do sistema
        Calendar cal1 = Calendar.getInstance(); //Atributo do tipo calendar
        Calendar cal2 = Calendar.getInstance(); //Atributo do tipo calendar
        //Faço as comparações
        cal1.setTime(data1); 
        cal2.setTime(data2);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        idade = year2 - year1;
        if ((month2 > month1) || ((month2 == month1) && (day2 > day1))) {
            idade -= 1;
        }
        return idade; //Retorno a idade desejada

    }

    private static void consultarDoador() throws IOException { //Consulta todos os dados do doador
        System.out.println("Digite a matrícula do doador");
        int matricularecebida = Console.readInt();
        if (controller.obterDoador(matricularecebida) != null) { //enquanto tiver objetos na lista
            Doador temp = controller.obterDoador(matricularecebida); 
            //imprime seus dados
            System.out.println("---------------------------------");
            System.out.println("O nome do doador é : " + temp.getNome());
            System.out.println("O telefone do doador é : " + temp.getTelefone());
            System.out.println("A data de nascimento do doador é : " + temp.getDatanasc());
            System.out.println("O peso do doador é : " + temp.getPeso());
            System.out.println("O responsável do doador é :\n " + temp.getResponsavel());
            System.out.println("---------------------------------");
        } else { //Caso contrário:
            System.out.println("Não existe doador com esse número de matrícula");
        }
    }

    private static void consultarPosto() throws IOException { //Consulta todos os postos
        System.out.println("Digite o número do posto");
        int numeroposto = Console.readInt();
        if (controller.obterDoador(numeroposto) != null) { //enquanto tiver objetos na lista com o mesmo numero de posto informado
            Posto post = controller.obterPosto(numeroposto);
            //imprime seus dados
            System.out.println("---------------------------------");
            System.out.println("O endereço do posto é  : " + post.getEndereco());
            System.out.println("O telefone do posto é : " + post.getTelefone());
            System.out.println("O responsável pelo posto é  : " + post.getResponsavel());
            System.out.println("---------------------------------");
        } else { //Caso contrário:
            System.out.println("Não existe doador com esse número de matrícula");
        }
    }

    private static void listarDoadores() throws IOException { //Lista os doadores
        Iterador id = controller.listarDoadores();
        while (id.temProximo()) { //enquanto tiver objetos na lista
            Doador doad = (Doador) id.obterProximo();
            System.out.println("-------------------------");
            System.out.println("O nome do doador é : " + doad.getNome());
            System.out.println("O telefone do doador é : " + doad.getTelefone());
            System.out.println("A data de nascimento do doador é : " + doad.getDatanasc());
            System.out.println("O peso do doador é : " + doad.getPeso());
            System.out.println("O responsável pelo doador é :" + doad.getResponsavel());
            System.out.println("A matrícula do doador é : " + doad.getMatricula());
            System.out.println("-------------------------");
           
        }
    }
    
     private static void listarDoacoesRealizadas() throws IOException { //Lista todas as doações realizadas
        Iterador id = controller.listarDoacoesRealizadas();
        while (id.temProximo()) {
            Doacao doad = (Doacao) id.obterProximo();
            if(doad.isStatus()==true){ //imprime se o status for igual a true
                System.out.println("-------------------------");
                System.out.println("A data da doação foi " + doad.getData());
                System.out.println("A hora da doação foi " + doad.getHora());
                System.out.println("A doação foi feita no posto de  número " + doad.getNumeroposto().getNumeroposto());
                System.out.println("A matricula do doador é " + doad.getMatriculadodoador().getMatricula());
            }
        }
    }
     private static void listarDoacoesNaoRealizadas() throws IOException { //Lista todas a doações não realizadas
        Iterador id = controller.listarDoacoesNaoRealizadas();
        while (id.temProximo()) {
            Doacao doad = (Doacao) id.obterProximo();
            if(doad.isStatus()==false){ //imprime se o status for igual a false
                System.out.println("-------------------------");
                System.out.println("A data da doação é " + doad.getData());
                System.out.println("A hora da doação é " + doad.getHora());
                System.out.println("A doação era para ser feita no posto de número " + doad.getNumeroposto().getNumeroposto());
                System.out.println("A matricula do doador é " + doad.getMatriculadodoador().getMatricula());
            }
        }
    }
    private static void listarPostos() throws IOException { //lista todos os postos
        Iterador id = controller.listarPostos();
        while (id.temProximo()) { //enquanto tiver proximo na lista, irá listar seus dados
            Posto post = (Posto) id.obterProximo();
            System.out.println("-------------------------");
            System.out.println("O número do posto é : " + post.getNumeroposto());
            System.out.println("O endereço do posto é : " + post.getEndereco());
            System.out.println("O telefone do posto é : " + post.getTelefone());
            System.out.println("O responsável pelo posto é  : " + post.getResponsavel());
            System.out.println("-------------------------");
           
        }
    }
    
    private static void excluirDoacao() throws IOException{ //exclui a doação da lista
        Iterador id = controller.listarDoadores();
        System.out.println("Digite o número da sua matrícula");
        int mat = Console.readInt();
        controller.excluirDoacao(mat);
        System.out.println("Removido com sucesso!");
    }
    private static void listarDoacaoPorDia() throws IOException, ParseException { //lista doações por dia 
        Doacao dd[] = new Doacao[24]; //Crio um vetor com 24 posições do tipo da
        Doacao da;  //crio um atributo do tipo Doação
        Iterador id = controller.listarDoacoesDia(); //crio um iterador que aponta para o primeiro nó da lista
        System.out.println("Digite o dia que você deseja saber");
        Date dia = new Date();
        String datarecebida = Console.readString();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        dia = df.parse(datarecebida);
        System.out.println("Digite o número do posto que você deseja ver a lista de doações");
        int matricula = Console.readInt();
        while(id.temProximo()){ //enquanto tiver objetos na lista
           Doacao dod = (Doacao) id.obterProximo(); 
           if(dod.getNumeroposto().getNumeroposto()==matricula){ //Se o numero do posto for igual ao numero informado
           if(dia.equals(dod.getData())){ //Se o dia informado da doação for igual ao dia informado pelo usuário
               dd[dod.getHora()] = dod;  //Passa o conteudo da lista para o vetor
           }
        }}
        
       
        for(int i = 0 ; i<24 ; i++){
            da = dd[i]; //Digo que da pega o conteudo presente em dd[i]
            if(da == null){} //Caso da não tiver nenhum elemento não faz nada
            else{ //Caso contrário imprime a lista
            	System.out.println("A hora da doação é às " + da.getHora());
                System.out.println("O posto é de número " + da.getNumeroposto().getNumeroposto());
                System.out.println("O número de matricula do doador é  " + da.getMatriculadodoador().getMatricula());
                System.out.println("O status da doação é " + da.isStatus());
            }
        	
        }
    }

    public static void main(String[] args) throws IOException, ParseException { //Metodo principal
        Calendar c1 = Calendar.getInstance();
        int a;
        //Pega a hora do sistema e a depender da hora diz "bom dia", "boa tarde" ou "boa noite"
        int hora = c1.get(Calendar.HOUR_OF_DAY);
        if (hora >= 6 && hora <= 12) {
            System.out.println("Bom Dia");
        } else if (hora > 12 && hora < 18) {
            System.out.println("Boa Tarde");
        } else {
            System.out.println("Boa Noite");
        }
        do { //menu
            System.out.println("Digite o que você deseja fazer");
            System.out.println("[1] - Página de cadastro\n" //ok
                    + "[2] - Consultar dados do doador\n" //ok
                    + "[3] - Consultar Posto\n" //ok 
                    + "[4] - Excluir doações\n"  //ok
                    + "[5] - Listar Doadores\n" //ok
                    + "[6] - Listar Postos de Doação\n" //ok
                    + "[7] - Listar Doações do Dia\n" 
                    + "[8] - Listar Doações Realizadas\n" //ok
                    + "[9] - Listar Doações não realizadas\n" //ok
                    + "[10] - Sair\n"); //ok
            a = Console.readInt();

            switch (a) {
                case 1:
                    Principal.Cadastro();
                    break;
                case 2:
                    Principal.consultarDoador();
                    break;
                case 3:
                    Principal.consultarPosto();
                    break;
                case 4:
                    Principal.excluirDoacao();
                    break;
                case 5:
                    Principal.listarDoadores();
                    break;
                case 6:
                    Principal.listarPostos();
                    break;
                case 7:
                    Principal.listarDoacaoPorDia();
                    break;
                case 8:
                    Principal.listarDoacoesRealizadas();
                    break;
                case 9:
                    Principal.listarDoacoesNaoRealizadas();
                    break;
                case 10:
                    break;
                default:
            }

        } while (a != 10);

    }

}
