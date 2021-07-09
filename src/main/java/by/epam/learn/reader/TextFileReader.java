package by.epam.learn.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.exception.TextException;

public class TextFileReader {
	public static Logger log = LogManager.getLogger();
	
	public String readFromFile (String filename) throws TextException {
		Path path = Paths.get(filename);
		if (!Files.exists(path) && Files.isDirectory(path) && !Files.isReadable(path)) {
			log.error("file " + filename + " not read");
			throw new TextException("file " + filename + " not read");
		}
		String result;
		try (Stream<String> streamLines = Files.lines(path)) {
			result = streamLines.collect(Collectors.joining());
		} catch (IOException e) {
			log.error("file " + filename + " read error");
			throw new TextException("file " + filename + " read error", e);
		}
		log.info("read data from file: " + filename);
		return result;
	}
}
