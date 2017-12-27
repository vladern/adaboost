/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author EPS
 */
public class Adaboost {

    
    public static void main(String[] args) {
        if(args.length>2 || args.length<2){
            System.out.println("Error de sintaxis:");
            System.out.println("Argumentos validos:");
            System.out.println("–t <fichero_almacenamiento_clasificador_fuerte> ");
            System.out.println("<fichero_origen_clasificador_fuerte> <imagen_prueba>");
            System.out.println("Usa 'número de imagen:posición en el array' para elegir la imagen");
            System.exit(0);
        }else{
            if(!args[0].equals("-t")){
                File f=new File(args[0]);
                if(f.isFile()){
                }else{
                    System.out.println("Archivo no valido");
                    System.exit(0);
                }
                clasificador c=new clasificador();
                c.FromFile(f);
                MNISTLoader ml = new MNISTLoader();
                ml.loadDBFromPath("./mnist_1000");
                try{
                    Scanner scanner = new Scanner(System.in);
                    int a=Integer.parseInt(args[1].split(":")[0]);
                    int b=Integer.parseInt(args[1].split(":")[1]);
                    Imagen i=null;
                    if(a>9 || a<0){
                       System.out.println("Solo se admiten imagenes del 0 al 9");
                    }
                    ArrayList ar=ml.getImageDatabaseForDigit(a);
                    
                    if(b<1 || b >ar.size()){
                        System.out.println("La posicion elegida se sale de los vlaores validos (0-"+ar.size()+") ¿Desea usar una al azar?(y/cualquiera)");
                        String input = scanner.next();
                        if(input.equals("y")){
                            Random r=new Random();
                            i=(Imagen)ar.get(r.nextInt(ar.size()));
                        }
                    }else{
                        i=(Imagen)ar.get(b);
                    }
                    System.out.println(c.IS(i));
                    String input="";
                    do{
                        System.out.println("¿Desea hacer otra prueba (y/cualquiera)?");
                        input = scanner.next();
                        if(input.equals("y")){
                            System.out.println("Introduzca un valor entre el 0 y el 9");
                            String aux = scanner.next();
                            try{
                                int n=Integer.parseInt(aux);
                                System.out.println("Introduzca un valor entre el 1 y el "+ml.getImageDatabaseForDigit(n).size());
                                aux = scanner.next();
                                int p=Integer.parseInt(aux);
                                System.out.println("Es "+c.IS((Imagen) ml.getImageDatabaseForDigit(n).get(p-1)));
                            }catch(Exception e){
                                System.out.println("Error: Compruebe que todos los datos sean validos");
                            }
                        }
                    }while(input.equals("y"));
                }catch(Exception e){
                    System.out.println(e.toString());
                }
                
            }else{
                Scanner scanner = new Scanner(System.in);
                File f=new File(args[1]);
                if(f.isFile()){
                }else{
                    System.out.println("Archivo no valido");
                    System.exit(0);
                }
                int images=0,clas=0,pru=0;
                boolean is=false;
                while(is!=true){
                    System.out.print("Introduce cantidad de imagenes para entrenamiento:");
                    try{
                        String input = scanner.next();
                        images=Integer.parseInt(input);
                        is=true;
                    }catch(Exception e){
                        System.out.print("Por favor introduzca un numero.");
                    }
                }
                is=false;
                while(is!=true){
                    System.out.print("Introduce cantidad de clasificadores para clasificador fuerte:");
                    try{
                        String input = scanner.next();
                        clas=Integer.parseInt(input);
                        is=true;
                    }catch(Exception e){
                        System.out.print("Por favor introduzca un numero.");
                    }
                }
                is=false;
                while(is!=true){
                    System.out.print("Introduce cantidad clasificadores debiles a entrenar:");
                    try{
                        String input =  scanner.next();
                        pru=Integer.parseInt(input);
                        is=true;
                    }catch(Exception e){
                        System.out.print("Por favor introduzca un numero.");
                    }
                }
                clasificador c=new clasificador(clas, images, pru); 
                c.ToFile(f);
                
            }
        }
    }
}
