package main.java;

/**
 * A literal will have a symbol.
 * A literal can be either positive or negative
 *
 *
 */


public class Literal {

    String symbol;
    boolean positive;

    public Literal(String symbol){
        this.symbol = symbol;
    }

    public Literal(String symbol, boolean positive){
        this.symbol = symbol;
        this.positive = positive;
    }

    public boolean isPositive() {
        return positive;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        if(isPositive()){
            return symbol;
        }
        else{
            return "~" + symbol;
        }
    }

    public boolean isComplementary(Literal other){
        if(symbol.equals(other.getSymbol()) && isPositive() != other.isPositive())
            return true;
        return false;
    }
}
