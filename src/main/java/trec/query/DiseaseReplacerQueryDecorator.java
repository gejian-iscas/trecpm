package trec.query;

import lexigram.Lexigram;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trec.model.Result;
import trec.model.Topic;

import java.util.List;

public class DiseaseReplacerQueryDecorator extends QueryDecorator {

	private static final Logger LOG = LogManager.getLogger();

	public DiseaseReplacerQueryDecorator(Query decoratedQuery) {
		super(decoratedQuery);
	}

	@Override
	public List<Result> query(Topic topic) {
		expandDisease(topic);
		return decoratedQuery.query(topic);
	}

	private void expandDisease(Topic topic) {
		String disease = topic.getDisease();

		String prefferedTerm = Lexigram.getPreferredTerm(disease);

		topic.withDisease(prefferedTerm);
		
		LOG.debug(disease + " changed to " + prefferedTerm);
	}

}
