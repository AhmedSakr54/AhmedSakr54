
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Youssef
 */
public class Dictionary {

    RedBlackTree  tree = new RedBlackTree ();
    //RBTree  tree = new RBTree ();


    public void LoadDic(){
        
        File file = new File("dictionary.txt");
        
        
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while(bufferedReader.ready()){
                
                String line = bufferedReader.readLine();
                tree.insert(line);
            }
            
            
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }
    
    public void printsize(){
        
           System.out.println("Dictionary size = " + tree.getSize());
        
    }
    
    public void insertword(Comparable data){
        if(tree.search(tree.getRoot(), data))
            System.out.println("Word already in the dictionary !");
        else
            tree.insert(data);

    }
    
    public void lookup(Comparable data){
        
        if(tree.search(tree.getRoot(), data))
            System.out.println("Yes");
        else
            System.out.println("No");
        
    }

    public void printHeight() {
        System.out.println("Tree Height = " + tree.calculateHeight());
    }

    
    
}


