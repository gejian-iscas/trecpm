package trec.search;

import org.json.JSONObject;
import trec.model.Result;

import java.util.List;

public interface SearchEngine {
	public List<Result> query(JSONObject jsonQuery);

}
