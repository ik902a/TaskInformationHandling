package by.epam.learn.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.ComponentText;
import by.epam.learn.entity.CompositeText;
import by.epam.learn.entity.TypeComponent;
import by.epam.learn.exception.TextException;
import by.epam.learn.parser.TextParser;

public class TextParserImpl implements TextParser {
	public static Logger log = LogManager.getLogger();
	private TextParser parser = new ParagraphParserImpl();
	public final static String PARAGRAPH_SPLIT = "\t";
	
	@Override
	public ComponentText parseText(String text) throws TextException {
		if (text == null || text.isEmpty()) {
			throw new TextException("text is null or empty");
		}
		CompositeText textComponent = new CompositeText(TypeComponent.TEXT);
		text = text.strip();
		String[] paragraphs = text.split(PARAGRAPH_SPLIT);
		for (String paragraph : paragraphs) {
			ComponentText paragraphComponent = parser.parseText(paragraph);
			textComponent.addComponent(paragraphComponent);
		}
		log.info("parsing the component text");
		return textComponent;
	}
}
