import java.util.ArrayList;
import java.util.Scanner;

public class Jogo{
  private int codigo;
  private static int num_jogos=0;
  private static int num_completos=0;
  private static ArrayList<Integer> num_terminados = new ArrayList<Integer>();
  private ArrayList<String> jogo = new ArrayList<String>();
  private ArrayList<String> dicas = new ArrayList<String>();
  private ArrayList<ArrayList<String>> posicoes = new ArrayList<ArrayList<String>>();
  private int tamanho;
  private int numDicas;
  private int numLetras;

  public Jogo(ArrayList<String> seed, ArrayList<String> seedDicas, int numletras){
    numLetras = numletras;
    for(int i=0; i<seed.size(); i++){
      jogo.add(seed.get(i));
      tamanho+=1;}
    for(int j=0; j<seedDicas.size(); j++){
      dicas.add(seedDicas.get(j));
      numDicas+=1;
    }
    codigo=num_jogos;
    num_jogos+=1;
  }

  public int accessCodigo(){
    return codigo;
  }
  public ArrayList<Integer> accessTerminados(){
    return num_terminados;
  }
  public int accessNumCompletos(){
    return num_completos;
  }
  public int accessNum_Jogos(){
    return num_jogos;
    }
  public int accessTamanho(){
    return tamanho;
  }
  public int numDicas(){
    return numDicas;
  }
  public ArrayList<String> accessJogo(){
    return jogo;
  }
  public ArrayList<String> accessDicas(){
    return dicas;
  }
  public int accessNumLetras(){
    return numLetras;
  }
  public ArrayList<ArrayList<String>> accessPosicoes(){
    return posicoes;
  }
  
  public void mostrarJogo(){
    //indicar coluna da posição
    String linha0 = this.accessJogo().get(0); 
    System.out.print("   ");
    for(int k=0; k<linha0.length();k++){
      System.out.print(k+1+" ");
    }
    //espaço para ajudar visualização
    System.out.println(" ");
    System.out.println(" ");
    
    for(int i=0; i<this.accessTamanho(); i++){
      //mostrar linha da posição
      if(i<9){
        System.out.print((i+1) + "  ");}
      else{
        System.out.print((i+1) + " ");
      }
      //acessa cada coluna da matriz
      String linha = this.accessJogo().get(i);
      for (int j=0; j<this.accessJogo().get(i).length();j++){
        //acessa cada letra da linha
        char letrinha = linha.charAt(j);
        //esses numeros indicam onde começa palavra
        if(letrinha=='1' || letrinha=='2' || letrinha=='3' || letrinha=='4'|| letrinha=='5' || letrinha=='6'){
          System.out.print(letrinha+" ");
         }
        else{
          if(letrinha=='0'){
            //print para espaços sem número ou letra
            if (j<10){
            System.out.print("  ");}
            else{
              System.out.print("   ");
            }
          }
          else{
            //isso indica cada letra da palavra
           if(this.verificaAbertura()!=0){
             int contador=0;
             for (int num=0; num<this.verificaAbertura();num++){
               ArrayList<String> posicao = this.accessPosicoes().get(num);
               String linha_posicao = posicao.get(0);
               String coluna_posicao = posicao.get(1);
               Integer m = Integer.valueOf(linha_posicao);
               Integer n = Integer.valueOf(coluna_posicao);
               if(i==(m-1) & j==(n-1)){
                 contador+=1;
                if(j<9){
           System.out.print(letrinha+" ");}
                 else{
             System.out.print(letrinha+"_"+" ");
               }}
           }
            if(contador==0){
              if(j<9){
           System.out.print("_"+" ");}
           else{
             System.out.print("__"+" ");
           }
            }
           }
          // impressão sem histórico
           else{
           if(j<9){
           System.out.print("_"+" ");}
           else{
             System.out.print("__"+" ");
           }}}}}
      //print para ir para próxima linha
      System.out.println(" ");}
    System.out.println(" ");}
  public void mostrarDicas(){
    //esse trecho mostra dicas + número correspondente
    System.out.println("* Essas são as dicas:");
    for(int i=0; i<this.numDicas(); i++){
      System.out.println((i+1)+" "+this.accessDicas().get(i));
    }
  }
  public void addPosicoes(ArrayList<String> posicao, char letra){
    String linha_s = posicao.get(0);
    String coluna_s = posicao.get(1);
    Integer linha = Integer.valueOf(linha_s);
    Integer coluna = Integer.valueOf(coluna_s);
    for(int m=0; m<this.accessTamanho();m++){
      if(m==linha-1){
        for (int n=0; n<this.accessJogo().get(m).length();n++){
          if(n==coluna-1){
            char letraJogo = this.accessJogo().get(m).charAt(n);
            if(letraJogo==letra){
              posicoes.add(posicao);
            }
            else{
              System.out.println("Ops! Essa não é a letra correta, rsrs");
            }
          }
        }
    }}
  }
  public int verificaAbertura(){
    ArrayList<ArrayList<String>> lista_posicoes = this.accessPosicoes();
    int num_posicoes = lista_posicoes.size();
    return num_posicoes;
  }

  public void jogar(){
    //primeiro, mostrar jogo e dicas
    System.out.println(" ");
    this.mostrarDicas();
    System.out.println(" ");
    System.out.println("Esse é o jogo: ");
    this.mostrarJogo();
    //depois, verificar se numero de posicoes tá igual num_letras
    int objetivo = this.accessNumLetras();
    //int agora = this.verificaIgualdade();
    int agora = this.verificaAbertura();
    Scanner objeto1 = new Scanner(System.in);
    Scanner objeto2 = new Scanner(System.in);
    while(objetivo!=agora){
      System.out.println(" ");
      System.out.println("Digite a posição que deseja alterar (linha x coluna, ex.: 2 2): ");
      String linha = objeto1.next();
      String coluna_ = objeto1.nextLine();
      String coluna = coluna_.replace(" ", "");
      ArrayList<String> posicao = new ArrayList<String>();
      posicao.add(linha);
      posicao.add(coluna);
      System.out.println("Digite a letra desejada: ");
      char letra = objeto2.next().charAt(0);
      this.addPosicoes(posicao, letra);
      System.out.println(" ");
      this.mostrarDicas();
      System.out.println(" ");
      System.out.println("Como ficou:");
      this.mostrarJogo();
    }
    num_terminados.add(this.accessCodigo());
    num_completos++;
    System.out.println("Parabéns, você terminou o jogo!");
      System.out.println("Esse é o seu presente: "+"\u2615");
  }
}
