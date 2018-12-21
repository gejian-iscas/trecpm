package trec.query;

import lexigram.Lexigram;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trec.model.Result;
import trec.model.Topic;

import java.util.List;

public class DiseaseExpanderQueryDecorator extends QueryDecorator {

	private static final Logger LOG = LogManager.getLogger();

	public DiseaseExpanderQueryDecorator(Query decoratedQuery) {
		super(decoratedQuery);
	}

	@Override
	public List<Result> query(Topic topic) {
		expandDisease(topic);
		return decoratedQuery.query(topic);
	}

	private void expandDisease(Topic topic) {
		String disease = topic.getDisease();

		List<String> synonyms = Lexigram.addSynonymsFromBestConceptMatch(disease);
		
		String expandedDisease = String.join(" ", synonyms);
		topic.withDisease(expandedDisease);
		
		LOG.debug(disease + " changed to " + expandedDisease);
	}

}
