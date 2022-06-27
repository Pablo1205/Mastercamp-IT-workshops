package tp4;

import java.util.Date;


public class Document {
	
	protected int documentID;
	protected String documentName;
	protected Date documentDate;
	protected int storageAddress;
	
	public Document(int documentID, String documentName, Date documentDate,int storageAddress) {
		// TODO Auto-generated constructor stub
		this.documentID = documentID;
		this.documentName = documentName;
		this.documentDate = documentDate;
		this.storageAddress = storageAddress;
	}
	

	public int getDocumentID() {
		return documentID;
	}
	
	public void setDocumentID(int documentID) {
		this.documentID = documentID ;
	}
	
	public String getDocumentName() {
		return documentName;
	}
	
	public void setDocumentName(String document) {
		this.documentName = documentName ;
	}
	
	public Date getDocumentDate() {
		return documentDate;
	}
	
	public void setDocumentDate(java.sql.Date documentDate) {
		this.documentDate = documentDate ;
	}
	
	public int getStorageAddress() {
		return storageAddress;
	}
	
	public void setStorageAddress(int storageAddress) {
		this.storageAddress = storageAddress ;
	}

}
