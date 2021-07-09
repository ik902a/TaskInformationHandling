package by.epam.learn.entity;

import java.util.List;

public interface ComponentText {
	TypeComponent getType();
	
	List<ComponentText> getComponents();
	
	int countSymbol();
}
