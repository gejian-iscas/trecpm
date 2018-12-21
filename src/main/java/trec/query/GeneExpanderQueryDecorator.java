package trec.query;

import trec.expansion.NCBIGeneInfo;
import trec.model.Gene;
import trec.model.Result;
import trec.model.Topic;

import java.util.List;

public class GeneExpanderQueryDecorator extends QueryDecorator {

	private static final String TOKEN_SEPARATOR = " ";

	private static final NCBIGeneInfo NCBI_GENE_INFO = NCBIGeneInfo.getInstance();

	private Gene.Field[] expandTo;

	public GeneExpanderQueryDecorator(Gene.Field[] expandTo, Query decoratedQuery) {
		super(decoratedQuery);
		this.expandTo = expandTo;
	}

	@Override
	public List<Result> query(Topic topic) {
		expandGenes(topic);
		return decoratedQuery.query(topic);
	}

	private void expandGenes(Topic topic) {
		String[] geneTokens = topic.getGeneTokens();

		StringBuilder expandedGenes = new StringBuilder();
		for (String token : geneTokens) {
			if (NCBI_GENE_INFO.containsKey(token)) {
				Gene gene = NCBI_GENE_INFO.get(token);
				String expansion = gene.getExpandedField(TOKEN_SEPARATOR, expandTo);
				expandedGenes.append(expansion);
			} else {
				expandedGenes.append(token);
			}
			expandedGenes.append(TOKEN_SEPARATOR);
		}

		topic.withGene(expandedGenes.toString().trim());
	}
}
