/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

import java.io.File;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author fidel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	MNISTLoader ml = new MNISTLoader();
        ml.loadDBFromPath("./mnist_1000");
        clasificador t=new clasificador(); 
        t.FromFile(new File("./src/data.txt"));
        ArrayList am=new ArrayList();
        Random r=new Random();
        int[] data=new int[300];
        int[] aux=new int[300];
        for(int i=0;i<300;i++){
            int m=r.nextInt(10);
            data[i]=m;
            am.add(ml.getImageDatabaseForDigit(m).get(r.nextInt(ml.getImageDatabaseForDigit(m).size())));
        }
        aux=t.WNumber(am);
        int res=0;
        for(int j=0;j<300;j++){
            if(aux[j]==data[j]){
                res+=1;
            }
        }
        
        
        System.out.println("Acierto: "+(double)res/300);
        
    }
    
}
