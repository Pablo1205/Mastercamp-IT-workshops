package TP3;

public class Cycliste {

	private String name;
	private int number;
	private int rank;
	private boolean finished;
	private boolean abandon;
	private boolean disqualified;
	private int chrono;
	
	public Cycliste(String name, int number, int rank, boolean finished, boolean abandon, boolean disqualified, int chrono) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.number = number;
		this.rank = rank;
		this.setFinished(finished);
		this.abandon = abandon;
		this.disqualified = disqualified;
		this.chrono = chrono;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getRank() {
		return rank;
	}
	
	public boolean getFinished() {
		return isFinished();
	}
	
	public boolean getAbandon() {
		return abandon;
	}
	
	public boolean getDisqualified() {
		return disqualified;
	}
	
	public int getChrono() {
		return chrono;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isAbandon() {
		return abandon;
	}

	public void setAbandon(boolean abandon) {
		this.abandon = abandon;
	}
	
	public boolean isDisqualified() {
		return disqualified;
	}

	public void setDisqualified(boolean disqualified) {
		this.disqualified = disqualified;
	}
	
	public int isTime() {
		return chrono;
	}

	public void setTime(int chrono) {
		this.chrono = chrono ;
	}
	
	public int isRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank ;
	}
	
}
