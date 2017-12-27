/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author EPS
 */
public class clasfuerte {
    private ArrayList<clasdebil> clas;
    
    clasfuerte(int number,int val, int im,int nclas){
        MNISTLoader ml = new MNISTLoader();
        ml.loadDBFromPath("./mnist_1000");
    	clas=new ArrayList<clasdebil>();
        ArrayList test=new ArrayList();
        int[] correct=new int[im];
        double[] w=new double[im];
        Random r=new Random();
        for(int j=0;j<im;j++){   
            int h=r.nextInt(10);
            int k=r.nextInt(ml.getImageDatabaseForDigit(h).size());
            //System.out.println(h+" "+k);      
            test.add(ml.getImageDatabaseForDigit(h).get(k));
            if(h==val){
                correct[j]=1;
            }else{
                correct[j]=-1;
            }
            w[j]=(double) 1/im;
        }

    	for(int m=0;m<number;m++){            
            int[] aux = new int[test.size()];
            clasdebil c=new clasdebil(train(test,nclas,w,correct));
            aux=c.aplicar(test);
            double err=c.getError(correct, w);
            double alfa=(double) Math.log((1-err)/err)*0.5;
            c.setTrust(alfa);
            double[] D=new double[im];
            double Z=0;
            for(int i=0;i<im;i++){
                D[i]=w[i]*Math.pow(Math.E,(-alfa*correct[i]*aux[i]));
                //D[i]=Math.pow(w[1],(-alfa*correct[i]*aux[i]));
                Z+=D[i];
            }
            for(int j=0;j<im;j++){
                w[j]=D[j]/Z;
            }
            clas.add(c);
        }	
    }
    
    clasfuerte(){
        clas=new ArrayList<clasdebil>();
    }
    
    public double isIt(Imagen i) {
        double res=0.0;
        for(int j=0;j<clas.size();j++) {			
            res+=clas.get(j).es(i)*clas.get(j).getTrust();
        }
        return res;
    }
    
    private clasdebil train(ArrayList<Imagen> a,int n, double[] w,int[] cr){
        ArrayList<clasdebil> ar=new ArrayList<clasdebil>();
        double[] perror=new double[n];
        for(int i=0;i<n;i++){
            clasdebil c=new clasdebil(a.get(i).getImageData().length,a.size());
            int[] error=c.aplicar(a);
            ar.add(c);
            perror[i]=ar.get(i).getError(cr, w);
        }
        return ar.get(minpeso(perror));
    }
    
    private int minpeso(double[] ps){
        double aux=ps[0];
        int pos=0;
        for(int i=0;i<ps.length;i++){
            if(ps[i]<aux){
                aux=ps[i];
                pos=i;
            }
        }
        return pos;
    }
    
    public ArrayList<clasdebil> clasif(){
        return clas;
    }
    
    public void add(int p,int u, int d,double t){
        clas.add(new clasdebil(p,d,u,t));
    }
}
