package by.epam.learn.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.comparator.ParagraphComparator;
import by.epam.learn.entity.ComponentText;
import by.epam.learn.entity.CompositeText;
import by.epam.learn.entity.TypeComponent;
import by.epam.learn.exception.TextException;

public class TextAction {
	public static Logger log = LogManager.getLogger();
	public static final int DEFAULT_NUMBER_WORDS = 10;
	
	public ComponentText sortParagraphs(ComponentText text) throws TextException {
		if (text == null || text.getType()!=TypeComponent.TEXT) {
			log.error("component is invalid");
			throw new TextException("component is invalid");
		}
		List<ComponentText> resultText = new ArrayList<>(text.getComponents());
		Collections.sort(resultText, new ParagraphComparator());
		((CompositeText) text).setComponents(resultText);
		log.info("text after sorting by the number of sentences in paragraph: " + text.toString());
		return text;
	}

	public ComponentText findSentenceWithLongestWord(ComponentText text) throws TextException {
		if (text == null || text.getType()!=TypeComponent.TEXT) {
			log.error("component is invalid");
			throw new TextException("component is invalid");
		}
		ComponentText sentenceWithLongestWord = text;
		ComponentText longestWord = text;
		int maxWordLenght = 0;
		for (ComponentText paragraphComponent : text.getComponents()) {
			for (ComponentText sentenceComponent : paragraphComponent.getComponents()) {
				for (ComponentText lexemeComponent : sentenceComponent.getComponents()) {
					for (ComponentText elementLexeme : lexemeComponent.getComponents()) {
						if (elementLexeme.getType() == TypeComponent.WORD
								&& elementLexeme.countSymbol() > maxWordLenght) {
							maxWordLenght = elementLexeme.countSymbol();
							longestWord = elementLexeme;
							sentenceWithLongestWord = sentenceComponent;
						}
					}
				}
			}
		}
		log.info("the longest word is " 
				+ longestWord.toString() 
				+ ", in the sentence: " 
				+ sentenceWithLongestWord.toString());
		return sentenceWithLongestWord;
	}
	
	public ComponentText removeSentencesWithFewerWords(ComponentText text, int numberWords) throws TextException {
		if (text == null || text.getType()!=TypeComponent.TEXT) {
			log.error("component is invalid");
			throw new TextException("component is invalid");
		}
		if (numberWords <= 0) {
			numberWords = DEFAULT_NUMBER_WORDS;
			log.info("word count argument is incorrect, a value of 10 will be accepted by default");
		}
		ComponentText resultText = new CompositeText(TypeComponent.TEXT);
		for (ComponentText paragraphComponent : text.getComponents()) {
			ComponentText resultParagraph = new CompositeText(TypeComponent.PARAGRAPH);
			for (ComponentText sentenceComponent : paragraphComponent.getComponents()) {
				int counterWords = countWords(sentenceComponent);
				if (counterWords >= numberWords) {
					((CompositeText) resultParagraph).addComponent(sentenceComponent);
				}
			}
			((CompositeText) resultText).addComponent(resultParagraph);
		}
		log.info("text after removing sentences with fewer words " + numberWords + ": " + resultText.toString());
		return resultText;	 
	}
	
	public Map<String, Integer> findIdenticalWordsAndNumber(ComponentText text) throws TextException {
		if (text == null || text.getType()!=TypeComponent.TEXT) {
			log.error("component is invalid");
			throw new TextException("component is invalid");
		}
		Map<String, Integer> identicalWords = new HashMap<>();
		for (ComponentText paragraphComponent : text.getComponents()) {
			for (ComponentText sentenceComponent : paragraphComponent.getComponents()) {
				for (ComponentText lexemeComponent : sentenceComponent.getComponents()) {
					for (ComponentText elementLexeme : lexemeComponent.getComponents()) {
						if (elementLexeme.getType() == TypeComponent.WORD) {
							String word = elementLexeme.toString().toLowerCase();
							Integer value = identicalWords.put(word, 1);
							if (value != null) {
								identicalWords.put(word, ++value);
							}
						}
					}
				}
			}
		}
		Iterator<Map.Entry<String, Integer>> iterator = identicalWords.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> wordAndNumber = iterator.next();
			Integer number = wordAndNumber.getValue();
			if (number == 1) {
				iterator.remove();
			}
		}
		log.info("identical words in the text and their number: " + identicalWords.toString());
		return identicalWords;
	}
	
	private int  countWords(ComponentText sentence) {
		int counter = 0;
		for (ComponentText component : sentence.getComponents()) {
			if (component.getType() == TypeComponent.LEXEME) {
				counter++;
			}
		}
		return counter;
	}	
}
