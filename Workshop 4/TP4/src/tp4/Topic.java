package tp4;

public class Topic {
	
	private int topicID;
	private String topic;
	
	public Topic(int topicID, String topic) {
		// TODO Auto-generated constructor stub
		this.topicID = topicID;
		this.topic = topic;
	}
	
	public int getTopicID() {
		return topicID;
	}
	
	public void setTopicID(int topicID) {
		this.topicID = topicID ;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic ;
	}
}
