package trec.query;

import lexigram.Lexigram;
import trec.model.Topic;

import java.util.List;

public class DiseaseSynonymQueryDecorator extends DynamicQueryDecorator {

    public DiseaseSynonymQueryDecorator(Query decoratedQuery) {
        super(decoratedQuery);
    }

    @Override
    public Topic expandTopic(Topic topic) {
        String disease = topic.getDisease();
        List<String> synonyms = Lexigram.getSynonymsFromBestConceptMatch(disease);
        for (String synonym : synonyms) {
            topic.withDiseaseSynonym(synonym);
        }
        return topic;
    }

}
