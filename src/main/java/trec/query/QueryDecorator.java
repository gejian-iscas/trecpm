package trec.query;

import trec.model.Result;
import trec.model.Topic;

import java.util.List;

public class QueryDecorator implements Query {

	protected Query decoratedQuery;

	public QueryDecorator(Query decoratedQuery) {
		this.decoratedQuery = decoratedQuery;
	}

	@Override
	public List<Result> query(Topic topic) {
		return decoratedQuery.query(topic);
	}

	@Override
	public void setJSONQuery(String jsonQuery) {
		decoratedQuery.setJSONQuery(jsonQuery);
	}

	@Override
	public String getJSONQuery() {
		return decoratedQuery.getJSONQuery();
	}

	@Override
	public String getName() {
		return getMyName() + "_" + decoratedQuery.getName();
	}
	
	protected String getSimpleClassName() {
		return this.getClass().getSimpleName().replace("QueryDecorator", "");
	}
	
	protected String getMyName() {
		return getSimpleClassName();
	}

}
