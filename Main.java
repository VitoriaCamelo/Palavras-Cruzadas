import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
  
class Main {
  public static ArrayList<String> abastecer (String endereco) throws Exception{
    ArrayList<String> seed = new ArrayList<String>();
    File doc = new File(endereco);
    Scanner obj = new Scanner(doc);
    while (obj.hasNextLine()){
      seed.add(obj.nextLine());      
    }
    return seed;
  }
  public static void main(String[] args) throws Exception{
    Jogo jogo1 = new Jogo(abastecer("seed1.txt"), abastecer("dicas1.txt"), 35);
    Jogo jogo2 = new Jogo(abastecer("seed2.txt"), abastecer("dicas2.txt"), 41);
    Jogo jogo3 = new Jogo(abastecer("seed3.txt"), abastecer("dicas3.txt"), 28); 
    while(jogo1.accessTerminados().size()!=3){
      if(jogo1.accessTerminados().size()==0){
      System.out.println("Bem vind@!");
      System.out.println("No momento, temos 3 palavras-cruzadas para você.");
      jogo1.jogar();
    }
    else if(jogo1.accessTerminados().size()<3){
      System.out.println("Você já tem: "+jogo1.accessNumCompletos()+" café/cafés");
   if(jogo1.accessTerminados().size()==1){
     System.out.println("Indo para o próximo jogo...");
       jogo2.jogar();
   }
      else{
        System.out.println("Indo para o último jogo...");
        jogo3.jogar();
      }
    }
    }
    System.out.println();
    System.out.println("----- FIM -----");
    System.out.print("Nossa! Esse código não pôde te segurar. Você completou tudo!");
    System.out.print("Aqui estão seus cafés de Java: \u2615 \u2615 \u2615");
  }
}
