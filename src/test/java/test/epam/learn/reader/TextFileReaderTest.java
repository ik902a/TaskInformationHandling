package test.epam.learn.reader;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.epam.learn.exception.TextException;
import by.epam.learn.reader.TextFileReader;

public class TextFileReaderTest {
	TextFileReader reader;
	public static final String TEST_FILE = "data_text\\text_test.txt";
	
	@BeforeClass
	public void setUp() {
		reader = new TextFileReader();
	}
	
	@AfterClass
	public void tearDown() {
		reader = null;
	}
	
	@Test 
	public void testReadFromFile() throws TextException {
		String expected = "\tIt has survived - not only. It was. It was. Traset sheets. ToString() containing Lorem english Ipsum passages, and."
				+ "\tIt is a long. The point of using Ipsum is that. ToString(a?b:c). Like English?";
		String actual = reader.readFromFile(TEST_FILE);
		assertEquals(actual, expected);
	}
}
