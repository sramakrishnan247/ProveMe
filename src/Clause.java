import java.util.LinkedList;
/**
 * A Clause is a disjunction of literals
 *
 */

public class Clause {

    private LinkedList<Literal> clause;

    public Clause(Literal literal){
        clause = new LinkedList<>();
        clause.add(literal);
    }
    public Clause(LinkedList<Literal> literals){
        clause = new LinkedList<>(literals);
    }

    public LinkedList<Literal> getClause() {
        return clause;
    }

    @Override
    public String toString() {
        String clauseString = "";
        for(Literal literal: clause){
            clauseString += literal.toString();
            clauseString += " v ";
        }
        return clauseString.substring(0,clauseString.length()-2);
    }
}
