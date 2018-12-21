package trec.query;

import trec.model.Result;
import trec.model.Topic;

import java.util.List;
import java.util.Map;

public class StaticMapQueryDecorator extends MapQueryDecorator {
	
	private Map<String, String> keymap;

	public StaticMapQueryDecorator(Map<String, String> keymap, Query decoratedQuery) {
		super(decoratedQuery);
		this.keymap = keymap;
	}
	
	@Override
	public List<Result> query(Topic topic) {
		map(keymap);
		return decoratedQuery.query(topic);
	}
	
	@Override
	protected String getMyName() {
		return getSimpleClassName() + "(" + keymap.values() + ")";
	}
}
