
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
          
        Dictionary dictionary = new Dictionary();
        dictionary.LoadDic();
        
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("English Dictionary:");
        int choice = 5;
        while(true){
        System.out.println("Please select ur choice :");
        System.out.println("1.Lookup a Word \n2.Insert a word  \n3.Print size \n0.exit");
        
        choice = scanner.nextInt();
        
        if(choice == 1){
            System.out.println("Please Enter a word :");
            String temp = scanner2.nextLine();
            dictionary.lookup(temp);
            
        }
        else if(choice == 2){
            System.out.println("Please Enter a word :");
            String temp = scanner2.nextLine();
            dictionary.insertword(temp);
            dictionary.printsize();
            dictionary.printHeight();
        }
        else if(choice == 3)
            dictionary.printsize();
        else if(choice == 0)
            break;
        else
            System.out.println("Wrong Input");
        }
//        System.out.println(dictionary.tree.getRoot().data);
//        System.out.println(" Word     ------    isRed?");
//        dictionary.tree.inOrder(dictionary.tree.getRoot());

    }
    
}
