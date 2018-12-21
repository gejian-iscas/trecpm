package trec.query;

import lexigram.Lexigram;
import trec.model.Topic;

import java.util.List;

public class DiseaseHypernymQueryDecorator extends DynamicQueryDecorator {

    public DiseaseHypernymQueryDecorator(Query decoratedQuery) {
        super(decoratedQuery);
    }

    @Override
    public Topic expandTopic(Topic topic) {
        String disease = topic.getDisease();
        List<String> ancestors = Lexigram.getAncestorsFromBestConceptMatch(disease);
        for (String hypernym : ancestors) {
            topic.withDiseaseHypernym(hypernym);
        }
        return topic;
    }

}
