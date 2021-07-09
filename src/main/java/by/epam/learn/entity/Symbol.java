package by.epam.learn.entity;

import java.util.List;

public class Symbol implements ComponentText {
	public static final int LENGHT_SYMBOL = 1;
	private TypeComponent type;
	private char value;
	
	public Symbol() {
	}

	public Symbol(TypeComponent type, char value){
		this.type = type;
		this.value = value;
	}

	public void setType(TypeComponent type) {
		this.type = type;
	}

	@Override
	public TypeComponent getType() {
		return type;
	}
	
	public void setValue(char value)  {
		this.value = value;
	}
	
	public char getValue() {
		return value;
	}

	@Override
	public int countSymbol() {
		return LENGHT_SYMBOL;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Symbol other = (Symbol) obj;
		if (type != other.type)
			return false;
		if (value != other.value)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return Character.toString(value);
	}
	
	@Override
	public List<ComponentText> getComponents() {
		throw new UnsupportedOperationException("Unsupported operation for class Symbol");
	}
}
