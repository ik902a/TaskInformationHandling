package by.epam.learn.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.ComponentText;
import by.epam.learn.entity.CompositeText;
import by.epam.learn.entity.TypeComponent;
import by.epam.learn.exception.TextException;
import by.epam.learn.parser.TextParser;

public class SentenceParserImpl implements TextParser {
	public static Logger log = LogManager.getLogger();
	private TextParser parser = new LexemeParserImpl();
	public final static String LEXEME_SPLIT = "\s";

	@Override
	public ComponentText parseText(String sentence) throws TextException {
		if (sentence == null || sentence.isEmpty()) {
			throw new TextException("sentence is null or empty");
		}
		CompositeText sentenceComponent = new CompositeText(TypeComponent.SENTENCE);
		String[] lexemes = sentence.split(LEXEME_SPLIT);
		for (String lexeme : lexemes) {
			ComponentText lexemeComponent = parser.parseText(lexeme);
			sentenceComponent.addComponent(lexemeComponent);
		}
		log.info("parsing the component sentence");
		return sentenceComponent;
	}
}
