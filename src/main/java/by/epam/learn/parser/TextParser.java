package by.epam.learn.parser;

import by.epam.learn.entity.ComponentText;
import by.epam.learn.exception.TextException;

public interface TextParser {
	ComponentText parseText(String text) throws TextException;
}
