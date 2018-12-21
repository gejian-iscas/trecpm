package trec.query;

import lexigram.Lexigram;
import trec.model.Topic;

public class DiseasePreferredTermQueryDecorator extends DynamicQueryDecorator {

    public DiseasePreferredTermQueryDecorator(Query decoratedQuery) {
        super(decoratedQuery);
    }

    @Override
    public Topic expandTopic(Topic topic) {
        String disease = topic.getDisease();
        String preferredTerm = Lexigram.getPreferredTerm(disease);
        return topic.withDiseasePreferredTerm(preferredTerm);
    }

}
