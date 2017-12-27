/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J
 */
public class clasificador {
    private ArrayList<clasfuerte> clas;
    
    public clasificador(int n, int im,int nclas){
        clas=new ArrayList<clasfuerte>();
        for(int i=0;i<10;i++){
            clas.add(new clasfuerte(n,i,im,nclas));
        }
        
    }
    
    public clasificador(){
        clas=new ArrayList<clasfuerte>();
    }
    
    public int[] WNumber(ArrayList im){
        int[] res=new int[im.size()];
        for(int i=0;i<im.size();i++){
            double aux=-1000;
            for(int j=0;j<10;j++){
                double aux2=clas.get(j).isIt((Imagen) im.get(i));
                //System.out.println(i+" "+j+" "+aux2);
                if(aux2>aux){
                    res[i]=j;
                    aux=aux2;
                    
                }
            }
            
        }
        return res;
    }
    public void ToFile(File f){
        try{
            PrintWriter writer = new PrintWriter(f);
            for(int i=0;i<clas.size();i++){
                writer.println(i+":");
                for(int j=0;j<clas.get(i).clasif().size();j++){
                    writer.println(clas.get(i).clasif().get(j).getData());
                }
            }
            writer.close();
        }catch(Exception es){
            System.out.println("Error en al archivo, por favor use vuelva a intentarlo comprobando que el archivo sea valido");
        }
    }
    
    public void FromFile(File f){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
            String line;
            int actual=0;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                if(line.split(":").length>2){
                    
                    int p = Integer.parseInt(line.split(":")[1]);
                    int u = Integer.parseInt(line.split(":")[2]);
                    int d = Integer.parseInt(line.split(":")[3]);
                    double t = Double.parseDouble(line.split(":")[4]);
                    clas.get(actual).add(p,u,d,t);
                    
                }else{
                    actual = Integer.parseInt(line.split(":")[0]);
                    clas.add(new clasfuerte());
                }
            }
        }catch(Exception es){
            System.out.println(es.toString());
        }
    }
    
    public int IS(Imagen i){
        int res=0;
        double aux=-1000;
        for(int j=0;j<10;j++){
            double aux2=clas.get(j).isIt((Imagen) i);
            //System.out.println(i+" "+j+" "+aux2);
            if(aux2>aux){
                res=j;
                aux=aux2;                  
           }
        }
        return res;
    }
}
