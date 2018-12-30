package trec.query;

import org.apache.commons.io.FileUtils;
import trec.model.Result;
import trec.model.Topic;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class TemplateQueryDecorator extends MapQueryDecorator {
	
	protected File template;

	private static final Pattern DOUBLE_COMMA = Pattern.compile("(\\p{javaWhitespace}*,){2,}");

	public TemplateQueryDecorator(File template, Query decoratedQuery) {
		super(decoratedQuery);
		this.template = template;
	}

	@Override
	public List<Result> query(Topic topic) {
		loadTemplate(topic);
		map(topic.getAttributes());
		setJSONQuery(cleanup(getJSONQuery()));
		return decoratedQuery.query(topic);
	}

	protected static String readTemplate(File template) {
		String ret = "";
		try {
			ret = FileUtils.readFileToString(template, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	protected void loadTemplate(Topic topic) {
		setJSONQuery(readTemplate(template));
	}

	private static String cleanup(String template) {
		return DOUBLE_COMMA.matcher(template).replaceAll(",");
	}
	
	@Override
	protected String getMyName() {
		return getSimpleClassName() + "(" + template.getName() + ")";
	}

}
