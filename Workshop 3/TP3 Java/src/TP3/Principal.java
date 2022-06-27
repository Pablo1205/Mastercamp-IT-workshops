package TP3;
import java.util.Scanner;
import java.util.Random;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cycliste[] allRunners;
		Cycliste[] runnersDone;
		
		int a;
		Scanner s = new Scanner(System.in);
		System.out.print("Combien de cyclistes participent à cette course :");
		a = s.nextInt();
		
		
		
		// nb de cyclistes 
		System.out.println("Il y a " + a + " participants.");
		allRunners = new Cycliste[a];
		runnersDone = new Cycliste[a];
		

		// enregistrer cyclistes
		for (int i=0 ; i < a ; i++) {
			String str;
			// get name of the runner 
			Scanner s1 = new Scanner(System.in);
			System.out.print("Donnez le nom du coureur suivant :");
			str = s1.nextLine();
			allRunners[i] = new Cycliste(str,i,(i+1),false,false,false,0);
		}

		//s.close();
		
		// launch program 
		int prog=-1;
		while (prog!=1) {
			Scanner s2 = new Scanner(System.in);
			System.out.println("\n1 - Quitter le programmme"
			+ "\n2 - Afficher les coureurs" 
			+ "\n3 - Afficher le classement"
			+ "\n4 - Enregistrer une arrivée" 
			+ "\n5 - Enregistrer une abandon"
			+ "\n6 - Enregistrer une disqualification" 
			+ "\n7 - Enregistrer le temps d'arrivée d'un coureur"
			+ "\n8 - Obtenir le temps d'un coureur"
			+ "\n9 - Obtenir l'écart des temps entre deux coureurs donnés"
					);	
			prog = s2.nextInt();
		
		   switch(prog){
		       case 1: 
		           System.out.println("Au revoir !");
		           break;
		   
		       case 2:
		    	   displayCoureur(allRunners ,a);
		           break;
		   
		       case 3:
		    	   displayRanking(runnersDone,allRunners,a);
		       break;
		       
			   case 4:
				   	int nb=-1;
					Scanner s10 = new Scanner(System.in);
					System.out.print("Quel cycliste est arrivé ? ");
					nb = s10.nextInt();
					allRunners=registerArrival(allRunners,a,nb);
					runnersDone=updateArrivalList(runnersDone,allRunners,a,nb);
					allRunners=updateCoureurList(runnersDone, allRunners, a);
					a=updateA(a); 
					break;
			       
			   case 5:
				   int nb1=-1;
					Scanner s4 = new Scanner(System.in);
					System.out.print("Quel cycliste a abandonné ? ");
					nb1 = s4.nextInt();
				   allRunners=registerAbandon(allRunners,a,nb1);
				   runnersDone=updateAbandonList(runnersDone,allRunners,a,nb1);
				   allRunners=updateCoureurList(runnersDone, allRunners, a);
					a=updateA(a);
			       break;
			       
			   case 6:
				   registerDisqualified(allRunners, a);
			       break;
			       
			   case 7:
				   registerTime(allRunners, a);
			       break;
			       
			   case 8:
				   getTime(allRunners,a);
			       break;    
			       
			   case 9:
				   getTimeGap(allRunners,a);
			       break;    
			       
			   default:
			       System.out.println("Choix incorrect");
			           break;
		   }
		}

	}
	
	
	
	static void displayCoureur(Cycliste[] allRunners, int a){
		// displays cyclistes 
		
		for (int i=0; i < a ; i++) {
			if (allRunners[i] != null) {
				System.out.println("Nom : " + allRunners[i].getName() 
						+ " / Numéro : " + allRunners[i].getNumber() 
						+ " / Classement : " + allRunners[i].getRank() 
						+ " / A fini : " + allRunners[i].getFinished() 
						+ " / Abandon : " + allRunners[i].getAbandon() 
						+ " / Est disqualifié : " + allRunners[i].getDisqualified() 
						+ " / Temps : " + allRunners[i].getChrono() 
						);	
			}
			
		}
		
	}

	static void displayRanking(Cycliste[] runnersDone, Cycliste[] allRunners, int a){
		System.out.println("Ont terminé : \n");
		for (int i=0; i < a ; i++) {
			if (runnersDone[i] != null) {
				System.out.println("Nom : " + runnersDone[i].getName() 
						+ " / Numéro : " + runnersDone[i].getNumber() 
						+ " / Classement : " + runnersDone[i].getRank() 
						+ " / A fini : " + runnersDone[i].getFinished() 
						+ " / Abandon : " + runnersDone[i].getAbandon() 
						+ " / Est disqualifié : " + runnersDone[i].getDisqualified() 
						+ " / Temps : " + runnersDone[i].getChrono() 
						);	
			}
		}
		System.out.println("\nSont toujours en course : \n");
		/*for (int i=0; i < a ; i++) {
			for (int j=0; j < a ; j++) {
				if (allRunners[i].getRank()>allRunners[j].getRank()) {
					Cycliste c = new Cycliste(" ",0,0,false,false,false,0);
					c = allRunners[j];
					allRunners[j]=allRunners[i];
					allRunners[i]=c;
				}
			}
		}*/
		displayCoureur(allRunners,a);
	}
	
	static Cycliste[] registerArrival(Cycliste[] allRunners, int a,int nb){
		//make sure the user selects an existing number 
		int c=0;
		while (c==0){
			for (int i=0; i < a ; i++) {
				if (nb==allRunners[i].getNumber()) {
					c=1;
					allRunners[nb].setFinished(true);
				}
			}
		}
		return allRunners;
	}
	
	static Cycliste[] registerAbandon(Cycliste[] allRunners, int a, int nb){
		
		//make sure the user selects an existing number 
		int c=0;
		while (c==0){
			for (int i=0; i < a ; i++) {
				if (nb==allRunners[i].getNumber()) {
					c=1;
					allRunners[nb].setAbandon(true);
				}
			}
		}
		return allRunners;
	}

	static Cycliste[] registerDisqualified(Cycliste[] allRunners, int a){
		
		//make sure the user selects an existing number 
		int c=0;
		while (c==0){
			int nb=-1;
			Scanner s5 = new Scanner(System.in);
			System.out.print("Quel cycliste est disqualifié ? ");
			nb = s5.nextInt();
			for (int i=0; i < a ; i++) {
				if (nb==allRunners[i].getNumber()) {
					c=1;
					allRunners[nb].setDisqualified(true);
				}
			}
		}
		return allRunners;
	}

	static Cycliste[] registerTime(Cycliste[] allRunners, int a){
		
		//make sure the user selects an existing number 
		int c=0;
		while (c==0){
			int nb=-1;
			Scanner s6 = new Scanner(System.in);
			System.out.print("A quel cycliste souhaitez-vous attribuer un temps ? ");
			nb = s6.nextInt();
			for (int i=0; i < a ; i++) {
				if (nb==allRunners[i].getNumber()) {
					c=1;
					int newTime=0;
					Scanner s7= new Scanner(System.in);
					System.out.print("En combien de temps est-il arrivé ? ");
					newTime = s7.nextInt();
					allRunners[nb].setTime(newTime) ;
				}
			}
		}
		return allRunners;
	}

	static void getTime(Cycliste[] allRunners, int a){
		
		//make sure the user selects an existing number 
		int c=0;
		while (c==0){
			int nb=-1;
			Scanner s7 = new Scanner(System.in);
			System.out.print("Vous souhaitez le temps de quel cycliste ? ");
			nb = s7.nextInt();
			for (int i=0; i < a ; i++) {
				if (nb==allRunners[i].getNumber()) {
					c=1;
					System.out.print(allRunners[i].getName() + " est arrivé en " + allRunners[i].getChrono() +  " minutes.");
				}
			}
		}
	}

	static void getTimeGap(Cycliste[] allRunners, int a){
		
		//make sure the user selects an existing numbers 
		int c=0;
		int val1 = 0; 
		int val2 = 0;
		while (c==0){
			int nb1=-1;
			Scanner s8 = new Scanner(System.in);
			System.out.print("Sélectionnez le premier coureur : ");
			nb1 = s8.nextInt();
			for (int i=0; i < a ; i++) {
				if (nb1==allRunners[i].getNumber()) {
					c=1;
					val1=nb1;
				}
			}
		}
		c=0;
		while (c==0){
			int nb2=-1;
			Scanner s9 = new Scanner(System.in);
			System.out.print("Sélectionnez le second coureur : ");
			nb2 = s9.nextInt();
			for (int i=0; i < a ; i++) {
				if (nb2==allRunners[i].getNumber() && val1!=nb2) {
					c=1;
					val2=nb2;
				}
			}
		}
		
		if (allRunners[val1].getChrono() > allRunners[val2].getChrono()) {
			System.out.print("La différence est de : " + (allRunners[val1].getChrono()-allRunners[val2].getChrono()) + " minutes.");
		}
		else {
			System.out.print("La différence est de : " + (allRunners[val2].getChrono()-allRunners[val1].getChrono()) + " minutes.");
		}
	}

	static Cycliste[] updateArrivalList(Cycliste[] runnersDone, Cycliste[] allRunners, int a, int nb) {
		//make sure the user selects an existing number 
				int c=0;
				while (c==0){
					for (int i=0; i < a ; i++) {
						if (nb==allRunners[i].getNumber()) {
							c=1;
							int j=1;
							while (runnersDone[j] != null){
								j++;
							}
							runnersDone[j] = allRunners[nb];
							runnersDone[j].setRank(j);							
						}
					}
				}
				
		return runnersDone;
	}

	static Cycliste[] updateCoureurList(Cycliste[] runnersDone, Cycliste[] allRunners, int a) {
		//make sure the user selects an existing number 
		for (int i=0; i < a ; i++) {
			for (int j=0; j < a ; j++) {
				if (runnersDone[j]!=null && allRunners[i]!=null) {
					if (allRunners[i].getName()==runnersDone[j].getName()) {
						allRunners[i]=null;
					}
				}
			}
			//get max rank in runnersDone
			int j=1;
			while (runnersDone[j]!=null) {
				j++;
			}
			if (allRunners[i]!=null && allRunners[i].getRank()<=j) {
				allRunners[i].setRank(allRunners[i].getRank()+1);
			}
			
		}
		return allRunners;	
	}
	
	static int updateA(int a) {
		return a--;
	}

	static Cycliste[] updateAbandonList(Cycliste[] runnersDone, Cycliste[] allRunners, int a, int nb) {
		//make sure the user selects an existing number 
				int c=0;
				while (c==0){
					for (int i=0; i < a ; i++) {
						if (nb==allRunners[i].getNumber()) {
							c=1;
							int j=1;
							//while (runnersDone[j] == null){
							//	j++;
							//}
							runnersDone[a-1] = allRunners[nb];
							runnersDone[a-1].setRank(a-1);							
						}
					}
				}
		return runnersDone;
	}

}
