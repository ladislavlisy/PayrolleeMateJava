package Core;

/**
 * Created by ladislavlisy on 28.10.15.
 */
public class SymbolName implements Comparable<SymbolName>, Cloneable {
    private int code;

    private String name;

    public SymbolName(int code, String name)
    {
        this.code = code;

        this.name = name;
    }

    public SymbolName(SymbolName element)
    {
        this.code = element.code;

        this.name = element.name;
    }

    public int getCode() { return code; }

    public String getName() { return name; }

    public String description()
    {
        StringBuilder descriptionBuilder = new StringBuilder(this.name);
        descriptionBuilder.append("::").append(Integer.toString(this.code));
        return descriptionBuilder.toString();
    }

    @Override
    public int compareTo(SymbolName other) {
        return (this.code - other.code);
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof SymbolName) {
            SymbolName otherSymbol = (SymbolName) other;
            return this.isEqualToSymbol(otherSymbol);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();

        result += prime * result + this.code;

        return result;
    }

    public boolean isEqualToSymbol(SymbolName other)
    {
        return (this.code == other.code);
    }

    @Override
    public String toString()
    {
        return Integer.toString(this.code);
    }

    @Override
    public Object clone()
    {
        SymbolName otherSymbol;
        try
        {
            otherSymbol = (SymbolName)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new Error();
        }

        // Deep clone member fields here
        otherSymbol.code = this.code;
        otherSymbol.name = this.name;
        return otherSymbol;
    }

}
