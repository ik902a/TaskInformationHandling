package by.epam.learn.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.ComponentText;
import by.epam.learn.entity.CompositeText;
import by.epam.learn.entity.Symbol;
import by.epam.learn.entity.TypeComponent;
import by.epam.learn.exception.TextException;
import by.epam.learn.parser.TextParser;

public class LexemeParserImpl implements TextParser {
	public static Logger log = LogManager.getLogger();
	private TextParser parser = new WordParserImpl();
	public final static String WORD_CODE = "\\w+\\.\\w+\\(.*\\)";
	public final static String PUNCTUATION_MARK = "(\\p{Punct})";
	public final static String MARK_WORD_MARK = "(\\p{Punct}.+\\p{Punct})";
	public final static String MARK_WORD = "(\\p{Punct}.+)";
	public final static String CODE_MARK = "\\w+\\.\\w+\\(.*\\)\\p{Punct}";
	public final static String WORD_MARK = ".+\\p{Punct}";
	public final static String WORD_MARK_SPLIT = "(?=[,.!?)])";

	@Override
	public ComponentText parseText(String lexeme) throws TextException {
		if (lexeme == null || lexeme.isEmpty()) {
			throw new TextException("lexeme is null or empty");
		}
		CompositeText lexemeComponent = new CompositeText(TypeComponent.LEXEME);
		String word;
		Symbol symbol;
		if (lexeme.matches(WORD_CODE)) {
			ComponentText wordComponent = parser.parseText(lexeme);
			lexemeComponent.addComponent(wordComponent);
		} else if (lexeme.matches(PUNCTUATION_MARK)) {
			symbol = new Symbol(TypeComponent.PUNCTUATION_MARK, lexeme.charAt(0));
			lexemeComponent.addComponent(symbol);
		} else if (lexeme.matches(MARK_WORD_MARK)) {
			Character firstSymbol = lexeme.charAt(0);
			symbol = new Symbol(TypeComponent.PUNCTUATION_MARK, firstSymbol);
			lexemeComponent.addComponent(symbol);
			word = lexeme.substring(1, lexeme.length() - 1);
			ComponentText wordComponent = parser.parseText(word);
			lexemeComponent.addComponent(wordComponent);
			Character lastSymbol = lexeme.charAt(lexeme.length() - 1);
			symbol = new Symbol(TypeComponent.PUNCTUATION_MARK, lastSymbol);
			lexemeComponent.addComponent(symbol);
		} else if (lexeme.matches(MARK_WORD)) {
			Character firstSymbol = lexeme.charAt(0);
			symbol = new Symbol(TypeComponent.PUNCTUATION_MARK, firstSymbol);
			lexemeComponent.addComponent(symbol);
			word = lexeme.substring(1, lexeme.length());
			ComponentText wordComponent = parser.parseText(word);
			lexemeComponent.addComponent(wordComponent);
		} else if (lexeme.matches(CODE_MARK)) {
			word = lexeme.substring(0, lexeme.length() - 1);
			ComponentText wordComponent = parser.parseText(word);
			lexemeComponent.addComponent(wordComponent);
			Character lastSymbol = lexeme.charAt(lexeme.length() - 1);
			symbol = new Symbol(TypeComponent.PUNCTUATION_MARK, lastSymbol);
			lexemeComponent.addComponent(symbol);
		} else if (lexeme.matches(WORD_MARK)) {
			String[] lexemeEelements = lexeme.split(WORD_MARK_SPLIT);
			for (String element : lexemeEelements) {
				if (element.matches(PUNCTUATION_MARK)) {
					Character markSymbol = element.charAt(0);
					symbol = new Symbol(TypeComponent.PUNCTUATION_MARK, markSymbol);
					lexemeComponent.addComponent(symbol);
				} else {
					ComponentText wordComponent = parser.parseText(element);
					lexemeComponent.addComponent(wordComponent);
				}
			}
		} else {
			ComponentText wordComponent = parser.parseText(lexeme);
			lexemeComponent.addComponent(wordComponent);
		}
		log.info("parsing the component lexeme");
		return lexemeComponent;
	}
}
