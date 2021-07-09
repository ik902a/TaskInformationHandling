package test.epam.learn.parser;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.epam.learn.entity.ComponentText;
import by.epam.learn.exception.TextException;
import by.epam.learn.parser.TextParser;
import by.epam.learn.parser.impl.TextParserImpl;
import by.epam.learn.reader.TextFileReader;

public class TextParserTest {
	private TextFileReader reader;
	private TextParser parser;
	public static final String TEST_FILE = "data_text\\text_test.txt";
	
	@BeforeClass
	public void setUp() {
		reader = new TextFileReader();
		parser = new TextParserImpl();
	}
	
	@AfterClass
	public void tearDown() {
		reader = null;
		parser = null;
	}
	
	@Test
	public void testParseText() throws TextException {
		String text = reader.readFromFile(TEST_FILE);
		ComponentText component = parser.parseText(text);
		String actual = component.toString().strip();
		String expected = "It has survived - not only. It was. It was. Traset sheets. ToString() containing Lorem english Ipsum passages, and."
				+ " It is a long. The point of using Ipsum is that. ToString(a?b:c). Like English?";
		assertEquals(actual, expected);
	}
}
