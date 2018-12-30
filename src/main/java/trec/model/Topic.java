package trec.model;

import org.w3c.dom.Element;
import java.util.*;

public class Topic {

	private int number = 0;
	private String disease = "";
	private String gene = "";
	private String demographic = "";
	private String other = "";

    public String diseasePreferredTerm = "";
    public List<String> geneDescriptions = new ArrayList<>();
	public List<String> diseaseSynonyms = new ArrayList<>();
	public List<String> geneSynonyms = new ArrayList<>();
	public List<String> diseaseHypernyms = new ArrayList<>();
	public List<String> geneHypernyms = new ArrayList<>();

	public Topic() {

	}
	
	public static Topic fromElement(Element element) {
		int number = Integer.parseInt(getAttribute(element, "number"));
		String disease = getElement(element, "disease");

		String gene = "";
		if (hasElement(element, "variant")) {
			gene = getElement(element, "variant");
		} else if (hasElement(element, "gene")) {
			gene = getElement(element, "gene");
		}
		
		String demographic = getElement(element, "demographic");

		String other = "";
		if (hasElement(element, "other")){
			other = getElement(element, "other");
		}

		Topic topic = new Topic().withNumber(number).withDisease(disease).withGene(gene)
				.withDemographic(demographic).withOther(other);

		return topic;
	}

	public Topic withNumber(int number) {
		this.number = number;
		return this;
	}

	public Topic withDisease(String disease) {
		this.disease = disease;
		return this;
	}

	public Topic withGene(String gene) {
		this.gene = gene;
		return this;
	}

	public Topic withDemographic(String demographic) {
		this.demographic = demographic;
		return this;
	}

	public Topic withOther(String other) {
		this.other = other;
		return this;
	}

	public Topic withDiseasePreferredTerm(String term) {
        this.diseasePreferredTerm = term;
        return this;
    }

	private static boolean hasElement(Element element, String name) {
		return element.getElementsByTagName(name).getLength() > 0 ? true : false;
	}

	private static String getElement(Element element, String name) {
		return element.getElementsByTagName(name).item(0).getTextContent();
	}

	private static String getAttribute(Element element, String name) {
		return element.getAttribute(name);
	}

	public int getNumber() {
		return number;
	}

	public String getDisease() {
		return disease;
	}

	public String getGene() {
		return gene;
	}

	public String[] getGeneTokens() {
		return gene.split(" ");
	}

	public String getDemographic() {
		return demographic;
	}

	public int getAge() {
		try {
			return Integer.parseInt(demographic.replaceAll("[^0-9]+", ""));
		}
		catch (Exception e) {
			return -1;
		}
	}

	public String getSex() {
		if (demographic.toLowerCase().contains("female"))
			return "female";
		else
			return "male";
	}

	public String getOther() {
		return other;
	}
	
	public Map<String, String> getAttributes() {
		Map<String, String> ret = new HashMap<>();
		ret.put("number", String.valueOf(number));
		ret.put("disease", disease);
		ret.put("gene", gene);
		ret.put("variant", gene);
		ret.put("demographic", demographic);
		ret.put("other", other);
		ret.put("sex", getSex());
		ret.put("age", String.valueOf(getAge()));

		ret.put("diseasePreferredTerm", diseasePreferredTerm);

        for (int i = 0; i < geneDescriptions.size(); i++) {
            ret.put("geneDescriptions" + i, geneDescriptions.get(i));
        }

		for (int i = 0; i < diseaseSynonyms.size(); i++) {
			ret.put("diseaseSynonyms" + i, diseaseSynonyms.get(i));
		}

		for (int i = 0; i < geneSynonyms.size(); i++) {
			ret.put("geneSynonyms" + i, geneSynonyms.get(i));
		}

		for (int i = 0; i < diseaseHypernyms.size(); i++) {
			ret.put("diseaseHypernyms" + i, diseaseHypernyms.get(i));
		}

		for (int i = 0; i < geneHypernyms.size(); i++) {
			ret.put("geneHypernyms" + i, geneHypernyms.get(i));
		}
		
		return ret;
	}

	@Override
	public String toString() {
		return "Topic [number=" + number + ", disease=" + disease + ", gene=" + gene
				+ ", demographic=" + demographic + ", other=" + other + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		return Objects.equals(number, other.number);
	}

}
