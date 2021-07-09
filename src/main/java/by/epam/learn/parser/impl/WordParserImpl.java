package by.epam.learn.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.ComponentText;
import by.epam.learn.entity.CompositeText;
import by.epam.learn.entity.Symbol;
import by.epam.learn.entity.TypeComponent;
import by.epam.learn.exception.TextException;
import by.epam.learn.parser.TextParser;

public class WordParserImpl implements TextParser {
	public static Logger log = LogManager.getLogger();

	@Override
	public ComponentText parseText(String word) throws TextException {
		CompositeText wordComponent = new CompositeText(TypeComponent.WORD);
		char[] symbols = word.toCharArray();
		Symbol symbolComponent;
		for (char symbol : symbols) {
			symbolComponent = new Symbol(TypeComponent.LETTER, symbol);
			wordComponent.addComponent(symbolComponent);
		}
		log.info("parsing the component word");
		return wordComponent;
	}
}
