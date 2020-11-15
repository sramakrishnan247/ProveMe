import java.util.LinkedList;
/**
 * A Clause is a disjunction of literals
 * This is stored using a Linked List.
 */

public class Clause {

    private LinkedList<Literal> clause;

    public Clause(){
        clause = new LinkedList<>();
    }

    public Clause(Literal literal){
        clause = new LinkedList<>();
        clause.add(literal);
    }

    /**
     * Copy Constructor
     */
    public Clause(Clause copyClause){
        clause = new LinkedList<>();
        for(Literal literal : copyClause.getClause())
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
        if(clauseString.length()>=3)
            return clauseString.substring(0,clauseString.length()-2);
        return clauseString;
    }
}
