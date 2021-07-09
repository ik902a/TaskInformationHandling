package test.epam.learn.action;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.epam.learn.action.TextAction;
import by.epam.learn.entity.ComponentText;
import by.epam.learn.exception.TextException;
import by.epam.learn.parser.TextParser;
import by.epam.learn.parser.impl.TextParserImpl;
import by.epam.learn.reader.TextFileReader;

public class TextActionTest {
	private TextFileReader reader;
	private TextParser parser;
	TextAction action;
	public static final String TEST_FILE = "data_text\\text_test.txt";
	
	@BeforeClass
	public void setUp() {
		reader = new TextFileReader();
		parser = new TextParserImpl();
		action = new TextAction();
	}
	
	@AfterClass
	public void tearDown() {
		reader = null;
		action = null;
		parser = null;
	}
	
	@Test 
	public void testSortParagraphs() throws TextException {
		String text = reader.readFromFile(TEST_FILE);
		ComponentText component = parser.parseText(text);
		ComponentText result = action.sortParagraphs(component);
		String actual = result.toString().strip();
		String expected = "It is a long. The point of using Ipsum is that. ToString(a?b:c). Like English?"
				+ " It has survived - not only. It was. It was. Traset sheets. ToString() containing Lorem english Ipsum passages, and.";
		assertEquals(actual, expected);
	}
	
	@Test 
	public void testFindSentenceWithLongestWord() throws TextException {
		String text = reader.readFromFile(TEST_FILE);
		ComponentText component = parser.parseText(text);
		ComponentText result = action.findSentenceWithLongestWord(component);
		String actual = result.toString().strip();
		String expected = "ToString() containing Lorem english Ipsum passages, and.";
		assertEquals(actual, expected);
	}
	
	@Test 
	public void testRemoveSentencesWithFewerWords() throws TextException {
		String text = reader.readFromFile(TEST_FILE);
		ComponentText component = parser.parseText(text);
		ComponentText result = action.removeSentencesWithFewerWords(component, 4);
		String actual = result.toString().strip();
		String expected = "It has survived - not only. ToString() containing Lorem english Ipsum passages, and."
				+ " It is a long. The point of using Ipsum is that.";
		assertEquals(actual, expected);
	}
	
	@Test 
	public void testFindIdenticalWordsAndNumber() throws TextException {
		String text = reader.readFromFile(TEST_FILE);
		ComponentText component = parser.parseText(text);
		Map<String, Integer> actual = action.findIdenticalWordsAndNumber(component);
		Map<String, Integer> expected = new HashMap<>();
		expected.put("it", 4);
		expected.put("was", 2);
		expected.put("is", 2);
		expected.put("english", 2);
		expected.put("ipsum", 2);
		assertEquals(actual, expected);
	}
}
