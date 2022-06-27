
import java.util.Scanner;
import java.util.Calendar; 
// Exo 3 ------------------------------
/*public class Principal { 
	public static void main(String[] args) { 
		Calendar calendar = Calendar.getInstance(); 
		int year = calendar.get(Calendar.YEAR); 
		int month = calendar.get(Calendar.MONTH); 
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); 
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); 
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR); 
		int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH); 
		int hour = calendar.get(Calendar.HOUR); // 12 hour clock 
		int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour 
		int minute = calendar.get(Calendar.MINUTE); int
		second = calendar.get(Calendar.SECOND); int millisecond= 
		calendar.get(Calendar.MILLISECOND); 
		System.out.println(calendar.getTime()); 
		System.out.println("year \t\t: " + year); 
		System.out.println("month \t\t: " + month); 
		System.out.println("dayOfMonth \t: " + dayOfMonth); 
		System.out.println("dayOfWeek \t: " + dayOfWeek); 
		System.out.println("weekOfYear \t: " + weekOfYear); 
		 
		System.out.println("weekOfMonth \t: " + weekOfMonth); 
		System.out.println("hour \t\t: " + hour); 
		System.out.println("hourOfDay \t: " + hourOfDay); 
		System.out.println("minute \t\t: " + minute); 
		System.out.println("second \t\t: " + second); 
		System.out.println("millisecond \t: " + millisecond); 
	} 
}

//Exo 8  -------------------------------------------
public class Principal { 
	public static void main(String[] args){
		String mot, mot_inverse = "";
        Scanner in = new Scanner(System.in);
   
        System.out.println("Entrer un mot ");
        mot = in.nextLine();
   
        if (pal(mot))
           System.out.println("'"+mot + "' est un palindrome");
		else
		   System.out.println("'"+mot + "' n'est pas un palindrome");
	 }
	
	public static boolean pal(String mot){
		int i=0, longueur=mot.length()-1;
		boolean egale=true;
		while(i<longueur/2 && egale){
		   if(mot.charAt(i)==mot.charAt(longueur-i))
		      egale = true;
		   else
		      egale = false;
		   i++;
		}
		return egale;
	}
}*/

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Exo 1 -----------------------------------------
		/*   int nb;
        Scanner s = new Scanner(System.in);
        System.out.print("Entrez un nombre :");
        nb = s.nextInt();
        if(nb % 2 == 0)
        {
            System.out.println(nb + " est pair ");
        }
        else 
        {
            System.out.println(nb + " est impair ");
        }
        
        // Exo 2 -------------------------------------------
		float a, b ;
        Scanner s = new Scanner(System.in);
        System.out.print("Entrez le premier nombre réel :");
        a = s.nextFloat();
        System.out.print("Entrez le second nombre réel :");
        b = s.nextFloat();
        
        System.out.println("Somme : " + (a+b));
        
        // Exo 4  -------------------------------------------
		Calendar calendar = Calendar.getInstance(); 
		int age;
		Scanner s = new Scanner(System.in);
		System.out.print("Entrez votre âge :");
		age = s.nextInt();
		
		System.out.println("Voici votre année de naissance : "+ (calendar.get(Calendar.YEAR)-age));
        
     // Exo 5  -------------------------------------------
		float nb;
		Scanner s = new Scanner(System.in);
		System.out.print("Entrez un nombre :");
		nb = s.nextFloat();
		
		System.out.println("Table de multiplication : ");
		System.out.println(nb*1);
		System.out.println(nb*2);
		System.out.println(nb*3);
		System.out.println(nb*4);
		System.out.println(nb*5);
		System.out.println(nb*6);
		System.out.println(nb*7);
		System.out.println(nb*8);
		System.out.println(nb*9);
		System.out.println(nb*10);
		// optimiser avec for loop
		
		// Exo 6  -------------------------------------------
		String str;
		Scanner s = new Scanner(System.in);
		System.out.print("Entrez une phrase :");
		str = s.nextLine();
		s.close();
		
		System.out.println("Sans majuscules : " + str);
		
		int len = str.length();
		StringBuffer strBuffer = new StringBuffer(len);
		char c = 0;
		for (int i = 0; i < len; i++) {
			c = str.charAt(i);
			
			// if character is not a lower case letter and is the first letter of the sentence
			if (i==0){
				// lower case converted to upper case
				if (Character.isLowerCase(c)) {
					c = Character.toUpperCase(c);
				}
			}
			
			// if character is not a lower case letter 
			if (! Character.isLowerCase(c) && i!=0){
				strBuffer.append(c); 
				i++;
				c = str.charAt(i);
				// lower case converted to upper case
				if (Character.isLowerCase(c)) {
					c = Character.toUpperCase(c);
				}
			}
			
			strBuffer.append(c);
		}
		System.out.println("Avec majuscules : "+strBuffer.toString());*/
		
		// Exo 7  -------------------------------------------
		
		// Exo 9  -------------------------------------------
		
		// Exo 10  -------------------------------------------
		String str = "ingénieur";
		char c = 0;
		StringBuffer strBuffer = new StringBuffer(str.length());
		
		// 1

		// 2
	/*	for (int i=0; i < str.length() ; i++) {
			c = str.charAt(i);
			strBuffer.append(c);
			System.out.println(strBuffer.toString());
		}*/
	}

}	
