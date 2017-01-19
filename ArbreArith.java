import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArbreArith {
	private String ar;
	private ArbreArith fils_gauche, fils_droit;
	
	 public ArbreArith(){	 }
	 public ArbreArith(String val){
		 this.ar = val;
	 }
	 
	  
	    public String getValeur() {
	        return(ar);
	    }
	 
	 public ArbreArith getArbre_Gauche() {
	        return(fils_gauche);
	    }   

	    public ArbreArith getArbre_Droit() {
	        return(fils_droit);
	    }
	 
	 public void setfilsgauche(ArbreArith f){
		 this.fils_gauche = f;
	 }
	 
	 public void setfilsdroit(ArbreArith f){
		 this.fils_droit = f;
	 }
	 
	//------->>>> AFFICHER
	    public String toString() {
	        return toString("\t");
	    }

	    public String toString(String st) {
		if (fils_gauche!=null) {
		if (fils_droit!=null) 
		    return(st+"_("+ar+")___\n"+fils_gauche.toString(st+"\t")+fils_droit.toString(st+"\t"));
		else
		    return(st+ar+"\n"+fils_gauche.toString(st+"\t")+"\n");
	        }
	        else 
		if (fils_droit!=null) 
		    return(st+ar+"\n\n"+fils_droit.toString(st+"\t"));
		else
		    return(st+ar+" \n");
	    }

	private static Scanner scanner;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArbreArith aux,courant, racine;
		
		 scanner = new Scanner(System.in);
		  System.out.println("+==================Veuillez Entrer l'Expression Postfixée==================+");
		  ArrayList<String> arraylist ; 
		  String in = scanner.nextLine();
		  
		  arraylist = new ArrayList<>(Arrays.asList(in.split(" ")));
		  
		  ArrayDeque<ArbreArith> pil = new ArrayDeque<ArbreArith>();
		  for (String st : arraylist){
			  courant = new ArbreArith(st);
			  if (is_Operator(st)){
				 aux =  pil.pop();
				 courant.setfilsgauche(pil.pop());
				 courant.setfilsdroit(aux);
			  }System.out.println("===========================+++++++++++++++++++========================");
			  pil.push(courant);
			  
		  }
		  racine = pil.pop();
                  System.out.println("===========================+++++++++++++++++++========================");
		  System.out.println("L'arbre est :---> " +racine);
		  System.out.println("===========================+++++++++++++++++++========================");
		  
		  //------------->>>>>>Prefix
		  String st1,st2,xor;
		  
		  ArrayDeque<String> operst = new ArrayDeque<String>();
		  for (String n : arraylist){
			  if (!is_Operator(n))
				  operst.push(n);
			  else{
				st2  = operst.pop();
			  	st1  = operst.pop();
			  	xor = n +" "+ st1 +" "+ st2;
			            operst.push(xor);
			  }
			            			  
		  }
                  System.out.println("===========================+++++++++++++++++++========================");
		  System.out.println("L'Expression Prefixee est : --->: " +operst.pop());
                  System.out.println("===========================+++++++++++++++++++========================");
		  
                   		  
		  
		  //----->>>>>EVALUATION
		  Double D, D1,som;
		  ArrayDeque<Double> abc = new ArrayDeque<Double>();
		  for (String n : arraylist){
			  if (!is_Operator(n))
				  abc.push(Double.valueOf(n));			
			  else{
				  D1 = abc.pop();			  	
				  D = abc.pop();
			  	som = operate(D,D1,n);			           
			  		abc.push(som);
			  }
			            			  
		  }
                  System.out.println("===========================+++++++++++++++++++========================");
		  System.out.println("l'Evaluation de l'Expression Postfixée est : --->: " +abc.pop());
                  System.out.println("===========================+++++++++++++++++++========================");
		
		
	}
	
	public void Afficher(ArbreArith ar){
		System.out.println("\t\t\t\t"+ar.getValeur());
		try{
		ArbreArith b = ar.getArbre_Gauche();
		ArbreArith c = ar.getArbre_Droit();
		System.out.println("\t\t\t"+b.getValeur()+" \t\t "+c.getValeur());
		ArbreArith d = b.getArbre_Gauche();	
                ArbreArith f,g;
		ArbreArith e = b.getArbre_Droit();
		f= c.getArbre_Gauche(); 
                g = c.getArbre_Droit();
		System.out.println("\t\t"+d.getValeur()+" \t"+e.getValeur()+"\t \t "+f.getValeur()+"\t"+g.getValeur());}
		finally{}
	}
	
	 private static double operate(Double ar, Double b, String op){
	       
	        switch (op){
	            case "+": return Double.valueOf(ar) + Double.valueOf(b);
	            case "-": return Double.valueOf(ar) - Double.valueOf(b);
	            case "*": return Double.valueOf(ar) * Double.valueOf(b);
	            case "/": try{
	                return Double.valueOf(ar) / Double.valueOf(b);
	            }catch (Exception e){
	                 e.getMessage();
	            }
	            default: return -1;
	        }
	    }
	 
	    private static boolean is_Operator(String op){
	        switch (op){
	            case "+":
	            case "-":
	            case "*":
	            case "/":return true;
	            default: return false;
	        }
	    }
}
