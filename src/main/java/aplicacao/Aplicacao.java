/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacao;

/**
 *
 * @author Usuário
 */

import java.util.Scanner;
import java.util.List;
import entidade.Funcionario;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Aplicacao {
    public static void main (String[] arg ) {
        
        //instanciando e iniciando leitura
        Scanner sc = new Scanner(System.in);
        
        //Variáveis
        int n = 0;
        boolean sair = true;
       
        
        //Instancia Lista
        List <Funcionario> lista = new ArrayList<>();
        
        
        
        while(sair == true){
        //Menu
        switch (menu()) {
            
            case 1 :// Iniciar cadastro
                lista = addFunc(lista);
                break;
            case 2 ://Aumentar salário
                if (lista.size()!= 0){
                lista = addSalario(lista);
                break;
                }
                System.out.println("Nenhum funcionario cadastrado!");
                break;
            case 3 ://Exibir lista
                if (lista.size()!= 0){
                System.out.println("");
                System.out.println("Id |  Nome  | Salario | Data");
                exibeLista(lista);
                break;}
                System.out.println("Nenhum funcionario cadastrado!");
                break;
                
            case 4 ://Remove funcionario
                if (lista.size()!= 0){
                removeFunc(lista);
                break;}
                System.out.println("Nenhum funcionario cadastrado!");
                break;
            case 9 ://Sair
                sair = false;
                break;
            default :
                System.out.println("Numero invalido!Tente outro novamente");
                break;
            }
        }
    
        sc.close();
}
    
    public static int menu (){
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter frm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime data = LocalDateTime.now();
        int op = 0;
        String menu="                                 "
                +data.format(frm)+"\n"
                +"1 - Cadastrar funcionarios\n"
                +"2 - Aumentar salario\n"
                +"3 - Exibir lista de funcionarios\n"
                +"4 - Remover funcionario\n"
                +"9 - Sair";
        System.out.println(menu);
        op = sc.nextInt();
        sc.nextLine();
        return op;
    };
        // case 1 adcionar funcionário
    public static List <Funcionario> addFunc(List<Funcionario> lista){
        Scanner sc = new Scanner(System.in);
        
        LocalDateTime data = LocalDateTime.now();
        int id = 0;
        int n = 0;
        String nome ="";
        double salario = 0;
        // Iniciar Entrada de Dados
        System.out.println("Qntos funcionarios serão cadastrados");
        n = sc.nextInt();
        sc.nextLine();

        // Laço de repetição que le os dados de entrados n vezes
        // Instancia Funcionario e o atribui à lista
        for (int i = 0; i < n ; i++) {
            //Entrada
            System.out.println("Funcionario #" + (i + 1) + " :");
            System.out.print("Id: ");
            id = sc.nextInt();
            sc.nextLine();
            while (temId(lista, id)) {
                System.out.println("Id repetido!Tente novamente.");
                System.out.print("Id: ");
                id = sc.nextInt();
                sc.nextLine();
            }
            System.out.print("Nome: ");
            nome = sc.nextLine();
            System.out.print("Salario: ");
            salario = sc.nextDouble();
            sc.nextLine();

            //Instancia Funcionario
            Funcionario func = new Funcionario(id, nome, salario,data);

            //Adciona o funcionario na lista
            lista.add(func);
        }
        
        
        return lista ;
    
    }
    
    //Case 2 aumenta salário
    public static List <Funcionario> addSalario(List<Funcionario> lista){
        Scanner sc = new Scanner(System.in);
        double porcent = 0;
        Integer posid = null;

        // case 2 - Aumentar salario
        // Enquanto o Id for inexistente volta a pedir o Id novamente
        while (posid == null) {

            System.out.println("");
            System.out.print("Insira o Id do funcionario que tera aumento no salario: ");
            posid = sc.nextInt();
            sc.nextLine();
            posid = posId(lista, posid);// Chama função e atribui retorno à posid
            if (posid == null) {
                System.out.println("Este Id nao existe");

            } else {
                System.out.print("Insira a porcentagem do aumento: ");
                porcent = sc.nextDouble();
                sc.nextLine();
            }
        }// Fim do laço
        lista.get(posid).porcentAumento(porcent); // chamando metodo
        return lista;
    
    }
    
    //Case 3 exibi lista de funcionários
    public static void exibeLista (List<Funcionario> lista){
        Scanner sc = new Scanner(System.in);
        
        String voltar = "Pressione Enter p/ voltar";
        for (Funcionario func : lista) {  //Para cada objeto 'func' do tipo Funcionario
            System.out.println(func); // Exibe os dados chamando o metodo toSting
        }
        System.out.println(voltar);
        voltar = sc.nextLine();
        
    
    }
    
    //case 4 remover funcionario
    public static List<Funcionario> removeFunc(List<Funcionario> lista){
        Scanner sc = new Scanner(System.in);
        int id = 0;
        boolean sair = true;
        while(sair){     
            System.out.println("Insira o Id do funcionario: ");
            id = sc.nextInt();
            sc.nextLine(); 
            if (temId(lista, id)) { 
                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getId() == id) {
                        lista.remove(i);
                        System.out.println("Id removido com sucesso! ");
                        
                        return lista;
                    }
                }
             }
            System.out.println("Id inexistente!Tente novamente. ");
        }
        return lista;
    }
    
    //Procura id na lista que recebera aumento
    public static Integer posId (List<Funcionario> lista, int id){
        int i;
        for (i = 0 ; i < lista.size();i++){
            if (lista.get(i).getId()== id){
                return i;
            }    
        }
        return null;
    }
   
    //Procura id na lista, compara se esta repetido
    public static boolean  temId(List<Funcionario> lista, int id){
        int i;
        for (i = 0; i < lista.size();i++){
           if (lista.get(i).getId()== id){
               return true; // true se repetido
           }
        }
        return false;// false se nao repetido
    }
}
