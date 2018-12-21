package trec.query;

import trec.model.Result;
import trec.model.Topic;

import java.util.List;

public interface Query {
	public List<Result> query(Topic topic);
	
	public void setJSONQuery(String jsonQuery);
	
	public String getJSONQuery();
	
	public String getName();
}
