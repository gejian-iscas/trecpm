package trec.query;

import trec.expansion.NCBIGeneInfo;
import trec.model.Topic;

import java.util.List;

public class GeneSynonymQueryDecorator extends DynamicQueryDecorator {

    private static final NCBIGeneInfo NCBI_GENE_INFO = NCBIGeneInfo.getInstance();

    public GeneSynonymQueryDecorator(Query decoratedQuery) {
        super(decoratedQuery);
    }

    @Override
    public Topic expandTopic(Topic topic) {
        String[] geneTokens = topic.getGeneTokens();

        for (String token : geneTokens) {
            List<String> synonyms = NCBI_GENE_INFO.getSynonyms(token);
            for (String synonym : synonyms) {
                // TODO reintroduce variant
                topic.withGeneSynonym(synonym);
            }
        }
        return topic;
    }

}
