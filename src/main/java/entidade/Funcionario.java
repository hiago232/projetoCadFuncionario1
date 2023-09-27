/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Usuário
 */
public class Funcionario {
    //Declaração de variáveis

    private int id;
    private String nome;
    private double salario;
    private DateTimeFormatter frm = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
    private LocalDateTime data ;

    
    //Construtores
    public Funcionario (){};// Construtor Padrão
    public Funcionario (int id,String nome, double salario,LocalDateTime data){ // 3 parâmetros   
        this.id = id;
        this.nome = nome;
        this.salario = salario;
        this.data = data;
        
                
        
    }
    
    // Getters & Setters
    public void setId (int id){
        this.id = id;
    }
    
    public int getId (){
        return id;
    }
    
    public void setNome (String nome){
        this.nome = nome;
    }
    
    public String getNome (){
        return nome;
    }
    
    public double getSalario (){
        return salario;
    }
    
    //Metodos 
    public void porcentAumento(double porcent){
        salario = salario + porcent/100.0* salario;
    }
    
    public String toString (){
        return id + ",  "+nome+" , "
                +String.format("%.2f",salario)
                +"  "+data.format(frm);
    
    }
}
