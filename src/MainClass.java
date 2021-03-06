import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by anshup on 24/08/15.
 */
public class MainClass {
    public static void main(String args[])throws IOException{
        String eachLine="";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Basket> baskets = new ArrayList<Basket>();
        System.out.println("What all things you bought today. Finish it by saying That is it");
        Basket basket = new Basket();
        while (!(eachLine=br.readLine()).equals("That is it")){
            if (eachLine.contains("Input")){
                baskets.add(basket);
                basket.clear(); // There is some problem here. Everytime the reference is getting cleared.
                continue;
            }
            else if (!eachLine.equals("")){
               Goods good = processInputLine(eachLine);
                basket.addGood(good);
            }
        }
        //give output
        int i = 1;
        System.out.println("OUTPUT");
        for (Basket eachBasket : baskets){
            System.out.println("OUTPUT "+i++);
            eachBasket.display();
        }
    }
    public static Goods processInputLine(String inputLine){
        String[] parts = inputLine.split("at ");
        float price = Float.parseFloat(parts[1]);
        boolean isImported=parts[0].contains("imported");
        int qty = Integer.parseInt(parts[0].substring(0,parts[0].indexOf(' ')));
        String nameOfGood = parts[0].substring(parts[0].indexOf(' ')+1);
        boolean isTaxable = !(nameOfGood.contains("chocolate") || nameOfGood.contains("book") || nameOfGood.contains("pills"));
        //System.out.println(nameOfGood+" "+ price+" "+ qty+" "+isImported+" "+isTaxable);
        //For now considering chocolate, book or pills to be the only words
        Goods good = new Goods(nameOfGood,price,qty,isImported,isTaxable);
        return good;
    }
}
