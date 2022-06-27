package tp4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.* ;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int docID=getAvailableDocID();
		int storageAddress=getAvailableStorageAddress();
		int prog=-1;
		
		while (prog!=1) {
			
			Scanner s1 = new Scanner(System.in);
			System.out.println("\n1 - Quitter le programmme"
				+ "\n2 - Enregistrer un nouveau document" 
				+ "\n3 - Afficher les documents par catégorie, sujet, tag"
				+ "\n4 - Afficher le sujet le plus fréquent"
				+ "\n5 - Afficher le nb d'occurences par tag"
				+ "\n6 - Enregistrer un nouveau document sans date" 
				+ "\n7 - Enregistrer nouvelle date sur un document" 
				+ "\n8 - Enregistrer nouveau tag sur un document"
				);	
			prog = s1.nextInt();
			
			
			switch(prog){
				case 1: 
					System.out.println("Au revoir !");
					break;
   
				case 2:
					newDoc(docID,storageAddress);
					docID++;
					storageAddress++;
					break;
					
				case 3:
					showDocByFactor();
					break;
					
				case 4:
					showMostFreqTopic();
					break;
					
				case 5:
					showOccPerTag();
					break;
				
				case 6:
					newDocWithoutDate(docID,storageAddress);
					break;
					
				case 7:
					modifyDate();
					showDocs();
					break;
					
				case 8:
					addTag();
					showDocs();
					break;
    
				default:
					System.out.println("Choix incorrect");
						break;
			}
		}
		
	}
	
	static void newDoc(int docID, int storageAddress) {
		//nom doc
		String documentName;
		Scanner s2 = new Scanner(System.in);
		System.out.println("Entrez le nom du document : ");	
		documentName = s2.nextLine();
		//date doc
		Date documentDate = new Date();
		
		//new doc item
		Document Doc = new Document(docID,documentName,documentDate,storageAddress);
		
		//topic doc
		int topic;
		Scanner s3 = new Scanner(System.in);
		showTopics();
		System.out.println("Quel est le thème (donnez le numéro) : ");	
		topic = s3.nextInt();
		
		//tag doc
		int tag;
		Scanner s4 = new Scanner(System.in);
		showTags();
		System.out.println("Quelle balise voulez vous attribuer (donnez le numéro) : ");	
		tag = s4.nextInt();
		
		//category doc
		int category;
		Scanner s5 = new Scanner(System.in);
		showCategories();
		System.out.println("Dans quelle catégorie souhaitez vous ranger le fichier (donnez le numéro) : ");	
		category = s5.nextInt();
		
		// register new doc
		registerDoc(Doc,topic,category);
		getTopic(Doc,tag);
		
		// show all docs
		showDocs();
		
	}

	static void registerDoc(Document Doc, int topic, int category) {
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            String query = " insert into tp4.document (DocumentID, DocumentName, DocumentDate, StorageAddress, TopicID, CategoryID)"
                    + " values (?, ?, ?, ?, ?, ?)";
            
            //change date into sql compatible format
            java.sql.Date sqlDate = convert(Doc.getDocumentDate());
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, Doc.getDocumentID());
            preparedStmt.setString (2, Doc.getDocumentName());
            preparedStmt.setDate (3, sqlDate);
            preparedStmt.setInt (4, Doc.getStorageAddress());
            preparedStmt.setInt    (5, topic);
            preparedStmt.setInt    (6, category);

            // execute the preparedstatement
            preparedStmt.execute();
            
            con.close();
            
            System.out.println("Query executed.");  
            
          
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}
	
	static void getTopic(Document Doc, int tag) {
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            
            String query2 = " insert into tp4.porte (DocumentID, TagID)"
                    + " values (?, ?)";
                   
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query2);
            preparedStmt.setInt (1, Doc.getDocumentID());
            preparedStmt.setInt (2, tag);

            // execute the preparedstatement
            preparedStmt.execute();
            
            con.close();
            
            System.out.println("Query executed.");  
            
          
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}
	
	static void showDocs() {
		try
        {
			
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo"); 
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * from tp4.document Join tp4.porte on tp4.document.DocumentID=tp4.porte.DocumentID;");
            
            while (rs.next())
            {
              int DocumentID = rs.getInt("DocumentID");
              String DocumentName = rs.getString("DocumentName");
        	  Date DocumentDate = rs.getDate("DocumentDate");
              int StorageAddress = rs.getInt("StorageAddress");
              int TagID = rs.getInt("TagID");
              int TopicID = rs.getInt("TopicID");
              int CategoryID = rs.getInt("CategoryID");
              // print the results
        	  System.out.println(DocumentID 
            		  + " " + DocumentName 
            		  + " " + DocumentDate 
            		  + " " + StorageAddress 
            		  + " " + TopicID
            		  + " " + CategoryID
            		  + " " + TagID
            		  );
            }
            
            stmt.close();
            
            System.out.println("Query executed.");
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}

	static void showTopics() {
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT * from tp4.topic;");
            System.out.println("Query executed.");  
            
            while (rs.next())
            {
              int TopicID = rs.getInt("TopicID");
              String Topic = rs.getString("Topic");
              // print the results
              System.out.println(TopicID 
            		  + " " + Topic 
            		  );
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}

	static void showTags() {
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT * from tp4.tag;");
            System.out.println("Query executed.");  
            
            while (rs.next())
            {
              int TagID = rs.getInt("TagID");
              String Tag = rs.getString("Tag");
              // print the results
              System.out.println(TagID 
            		  + " " + Tag 
            		  );
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}

	static void showCategories() {
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT * from tp4.category;");
            System.out.println("Query executed.");  
            
            while (rs.next())
            {
              int CategoryID = rs.getInt("CategoryID");
              String Name = rs.getString("Name");
              // print the results
              System.out.println(CategoryID 
            		  + " " + Name 
            		  );
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}

	static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

	static int getAvailableDocID() {
		int DocumentID=1;
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT MAX(tp4.document.DocumentID) as max_docs from tp4.document;");
        //    System.out.println("Query executed.");  
            
            while (rs.next())
            {
              DocumentID = rs.getInt("max_docs");
              // print the results
            //  System.out.println(DocumentID);
            }
            stmt.close();
        }
        catch(Exception e)
        {
       //     System.out.println(e);
         //   System.out.println("Error connecting to tp4 database.");
        }
		
		return DocumentID+1;
	}
	
	static int getAvailableStorageAddress() {
		int StorageAddress=1;
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT MAX(tp4.document.StorageAddress) as max_stor_add from tp4.document;");
        //    System.out.println("Query executed.");  
            
            while (rs.next())
            {
				StorageAddress = rs.getInt("max_stor_add");
				// print the results
				//System.out.println(StorageAddress);
            }
            stmt.close();
        }
        catch(Exception e)
        {
       //     System.out.println(e);
         //   System.out.println("Error connecting to tp4 database.");
        }
		
		return StorageAddress+1;
	}

	static void showDocByFactor() {
		int a=0;
		Scanner s6 = new Scanner(System.in);
		System.out.println("\n1 - Par thème"
			+ "\n2 - Par balise" 
			+ "\n3 - Par catégorie"
			);	
		a = s6.nextInt();
		
		
		switch(a){
			case 1: 
				showTopics();
				int choice=0;
				Scanner s7 = new Scanner(System.in);
				System.out.println("\nChoisisez un thème : ");	
				choice = s7.nextInt();
				showDocByTopic(choice);
				break;
	
			case 2:
				showTags();
				int choice2=0;
				Scanner s8 = new Scanner(System.in);
				System.out.println("\nChoisisez une balise : ");	
				choice2 = s8.nextInt();
				showDocByTag(choice2);
				break;
				
			case 3:
				showCategories();
				int choice3=0;
				Scanner s9 = new Scanner(System.in);
				System.out.println("\nChoisisez une catégorie : ");	
				choice3 = s9.nextInt();
				showDocByCategory(choice3);
				break;
				
			default:
				System.out.println("Choix incorrect");
					break;
		}
		
	}
	
	static void showDocByTopic(int choice) {
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT \r\n"
            		+ "	tp4.document.DocumentName,\r\n"
            		+ "    tp4.topic.Topic\r\n"
            		+ "FROM tp4.document\r\n"
            		+ "JOIN tp4.topic ON tp4.topic.TopicID=tp4.document.TopicID\r\n"
            		+ "WHERE tp4.document.TopicID="+ choice +";");
            System.out.println("Query executed.");  
            
            while (rs.next())
            {
              String DocumentName = rs.getString("DocumentName");
              String Topic = rs.getString("Topic");
              // print the results
              System.out.println("Nom : " + DocumentName 
            		  + " / Thème : " + Topic 
            		  );
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}

	static void showDocByTag(int choice) {
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT \r\n"
            		+ "	tp4.document.DocumentName,\r\n"
            		+ "    tp4.tag.Tag\r\n"
            		+ "FROM tp4.document\r\n"
            		+ "Join tp4.porte on tp4.porte.DocumentID=tp4.document.DocumentID\r\n"
            		+ "Join tp4.tag on tp4.porte.TagID=tp4.tag.TagID\r\n"
            		+ "WHERE tp4.document.CategoryID="+ choice +";");
            System.out.println("Query executed.");  
            
            while (rs.next())
            {
              String DocumentName = rs.getString("DocumentName");
              String Tag = rs.getString("Tag");
              // print the results
              System.out.println("Nom : " + DocumentName 
            		  + " / Balise : " + Tag 
            		  );
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}
	
	static void showDocByCategory(int choice) {
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            Statement stmt=con.createStatement();  
            
            ResultSet rs=stmt.executeQuery("SELECT \r\n"
            		+ "	tp4.document.DocumentName,\r\n"
            		+ "    tp4.category.Name\r\n"
            		+ "FROM tp4.document\r\n"
            		+ "JOIN tp4.category ON tp4.category.CategoryID=tp4.document.CategoryID\r\n"
            		+ "WHERE tp4.document.CategoryID="+ choice +";");
                        
            System.out.println("Query executed.");  
            
            while (rs.next())
            {
              String DocumentName = rs.getString("DocumentName");
              String Category = rs.getString("Name");
              // print the results
              System.out.println("Nom : " + DocumentName 
            		  + " / Catégorie : " + Category
            		  );
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}

	static void showMostFreqTopic() {
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT \r\n"
            		+ "	tp4.document.DocumentName,\r\n"
            		+ "    tp4.topic.Topic,\r\n"
            		+ "    Count(Topic) as nb_of_occurence\r\n"
            		+ "FROM tp4.document\r\n"
            		+ "JOIN tp4.topic ON tp4.topic.TopicID=tp4.document.TopicID\r\n"
            		+ "Group by tp4.topic.Topic\r\n"
            		+ "ORDER BY nb_of_occurence DESC\r\n"
            		+ "Limit 1;\r\n"
            		+ "");
            System.out.println("Query executed.");  
            
            while (rs.next())
            {
              String Topic = rs.getString("Topic");
              // print the results
              System.out.println("Thème le plus fréquent : " + Topic);
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}

	static void showOccPerTag() {
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT \r\n"
            		+ "    tp4.tag.Tag,\r\n"
            		+ "	Count(Tag) as nb_of_occurence\r\n"
            		+ "FROM tp4.document\r\n"
            		+ "Join tp4.porte on tp4.porte.DocumentID=tp4.document.DocumentID\r\n"
            		+ "Join tp4.tag on tp4.tag.TagID=tp4.porte.TagID\r\n"
            		+ "Group by tp4.tag.Tag");
            System.out.println("Query executed.");  
            
            while (rs.next())
            {
              String Tag = rs.getString("Tag");
              int nb_of_occurence = rs.getInt("nb_of_occurence");
              // print the results
              System.out.println("Balise : " + Tag + " / Nombre d'apparitions : " + nb_of_occurence );
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}
	
	static void newDocWithoutDate(int docID, int storageAddress) {
		//nom doc
		String documentName;
		Scanner s10 = new Scanner(System.in);
		System.out.println("Entrez le nom du document : ");	
		documentName = s10.nextLine();
		
		//new doc item
		Document Doc = new Document(docID,documentName,null,storageAddress);
		
		//topic doc
		int topic;
		Scanner s11 = new Scanner(System.in);
		showTopics();
		System.out.println("Quel est le thème (donnez le numéro) : ");	
		topic = s11.nextInt();
		
		//tag doc
		int tag;
		Scanner s12 = new Scanner(System.in);
		showTags();
		System.out.println("Quelle balise voulez vous attribuer (donnez le numéro) : ");	
		tag = s12.nextInt();
		
		//category doc
		int category;
		Scanner s13 = new Scanner(System.in);
		showCategories();
		System.out.println("Dans quelle catégorie souhaitez vous ranger le fichier (donnez le numéro) : ");	
		category = s13.nextInt();
		
		// register new doc
		registerDocWithoutDate(Doc,topic,category);
		getTopic(Doc,tag);
		
		// show all docs
		showDocs();
		
	}
	
	static void registerDocWithoutDate(Document Doc, int topic, int category) {
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            String query = " insert into tp4.document (DocumentID, DocumentName, DocumentDate, StorageAddress, TopicID, CategoryID)"
                    + " values (?, ?, ? , ?, ?, ?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, Doc.getDocumentID());
            preparedStmt.setString (2, Doc.getDocumentName());
            preparedStmt.setDate (3, null);
            preparedStmt.setInt (4, Doc.getStorageAddress());
            preparedStmt.setInt    (5, topic);
            preparedStmt.setInt    (6, category);
            // execute the preparedstatement
            preparedStmt.execute();
            
            con.close();
            
            System.out.println("Query executed.");  
            
          
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}
	
	static void modifyDate() {
		showDocs();
		int docID;
		Scanner s14 = new Scanner(System.in);
		System.out.println("Quel document souhaitez-vous modifier ? (donnez le numéro) : ");	
		docID = s14.nextInt();

		Date documentDate = new Date();
		Scanner s15 = new Scanner(System.in);
		System.out.println("Entrez une nouvelle date (format jj/mm/aaaa) : ");	
		String sDate1=s15.nextLine();  
	    try {
			documentDate=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  

		java.sql.Date sqlDate = convert(documentDate);
		
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo"); 
            
            String query = "update tp4.document set tp4.document.DocumentDate = ? where tp4.document.DocumentID = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setDate (1, sqlDate);
            preparedStmt.setInt(2, docID);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            
            con.close();
    
            System.out.println("Query executed.");  
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}

	static void addTag() {
		showDocs();
		int docID;
		Scanner s16 = new Scanner(System.in);
		System.out.println("Quel document souhaitez-vous modifier ? (donnez le numéro) : ");	
		docID = s16.nextInt();

		showTags();
		int tagID;
		Scanner s17 = new Scanner(System.in);
		System.out.println("Quelle balise voulez-vous ajouter ? (donnez le numéro) : ");	
		tagID = s17.nextInt();

		
		try
        {
			
			Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp4","root","pablo");
            String query = " insert into tp4.porte (DocumentID,TagID)"
                    + " values (?, ?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, docID);
            preparedStmt.setInt (2, tagID);
            // execute the preparedstatement
            preparedStmt.execute();
            
            con.close();
            
            System.out.println("Query executed.");  
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Error connecting to tp4 database.");
        }
	}
	
}
