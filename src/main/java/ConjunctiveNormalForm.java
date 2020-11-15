package main.java;

import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * ConjunctiveNormalForm is a conjunction of clauses.
 *
 */

public class ConjunctiveNormalForm {

    LinkedList<Clause> CNF;
    public ConjunctiveNormalForm(){
        CNF = new LinkedList<>();
    }


    public ConjunctiveNormalForm(Clause clause){
        CNF = new LinkedList<>();
        CNF.add(clause);
    }

    public ConjunctiveNormalForm(LinkedList<Clause> clauses){
        CNF = new LinkedList<>(clauses);
    }

    //Copy constructor
    public ConjunctiveNormalForm(ConjunctiveNormalForm conjunctiveNormalForm){
        CNF = new LinkedList<>();
        CNF.addAll(conjunctiveNormalForm.getCNF());
    }
    public LinkedList<Clause> getCNF() {
        return CNF;
    }

    @Override
    public String toString() {

        if(CNF == null){
            return "empty";
        }

        String CNFString = "";
        for(Clause clause : CNF){
            CNFString += clause.toString();
            CNFString += "\n";

        }

        return CNFString;

    }

}
