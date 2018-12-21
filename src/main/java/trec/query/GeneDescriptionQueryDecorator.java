package trec.query;

import trec.expansion.NCBIGeneInfo;
import trec.model.Topic;

import java.util.Optional;

public class GeneDescriptionQueryDecorator extends DynamicQueryDecorator {

    private static final NCBIGeneInfo NCBI_GENE_INFO = NCBIGeneInfo.getInstance();

    public GeneDescriptionQueryDecorator(Query decoratedQuery) {
        super(decoratedQuery);
    }

    @Override
    public Topic expandTopic(Topic topic) {
        String[] geneTokens = topic.getGeneTokens();

        for (String token : geneTokens) {
            Optional<String> description = NCBI_GENE_INFO.getDescription(token);
            description.ifPresent(d -> topic.withGeneDescription(d));
        }
        return topic;
    }

}
