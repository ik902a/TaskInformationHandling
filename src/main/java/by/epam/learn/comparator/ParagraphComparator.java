package by.epam.learn.comparator;

import java.util.Comparator;

import by.epam.learn.entity.ComponentText;

public class ParagraphComparator implements Comparator<ComponentText> {
	
	@Override
	public int compare(ComponentText o1, ComponentText o2) {
		int number1 = o1.getComponents().size();
		int number2 = o2.getComponents().size();
		return number1 - number2;
	}
}
