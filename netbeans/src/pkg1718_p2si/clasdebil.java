/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;
import java.util.Random;
import java.util.ArrayList;
 /*
 * @author EPS
 */
public class clasdebil {
    private int pixel;
    private int dir;
    private int umbral;
    private int[] res;
    private double trust;
    
    clasdebil(int data, int size){
        Random r=new Random();
        pixel = r.nextInt(data);
        dir=r.nextInt(2);
        if(dir==0){
            dir=-1;
        }
        umbral=r.nextInt(256)-128;
        res=new int[size];
    }
    
    clasdebil(clasdebil d){
        Random r=new Random();
        pixel = d.pixel;
        dir=d.dir;
        umbral=d.umbral;
        res=d.res;
        trust=0;
    }
    
    clasdebil(int p, int d, int u, double t){
        pixel=p;
        dir=d;
        umbral=u;
        trust=t;
    }
    
    public int[] aplicar(ArrayList im){
        for(int i=0;i<im.size();i++){
            Imagen img = (Imagen) im.get(i);
            byte imageData[] = img.getImageData();
            if(imageData[pixel]>umbral){
                res[i]=dir;
            }else{
                res[i]=-dir;
            }
        }
        return res;
    } 
    
    public int es(Imagen im){
        byte imageData[] = im.getImageData();
        if(imageData[pixel]>umbral){
            return dir;
        }else{
            return -dir;
        }
    } 
    
    
    
    public void setTrust(double a){
        trust=a;
    }
    
    public double getError(int[] correct, double[] weight){
        double error=0.0;
        for(int i=0;i<correct.length;i++){
            if(correct[i]*res[i]<0){
                error+=weight[i];
            }
        }
        //return 0.5-(error/2);
        return error;
    }
    
    public String getData(){
        return ":"+pixel + ":" + umbral + ":" + dir+":"+trust;
    }
    
    public double getTrust(){
        return trust;
    }
}
