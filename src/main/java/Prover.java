import javax.annotation.processing.SupportedSourceVersion;
import java.awt.*;
import java.util.*;

/**
 * Takes the input KB and an expression.
 * This class performs resolution to check if KB entails
 * the fed input expression.
 */

public class Prover {
    Set<Clause> KB;
    String sentence;
    Clause emptyClause;
    boolean resolved = false;
    public Prover(ArrayList<Clause> inputKB, String sentence ){
        KB = new HashSet<>();
        for(Clause clause: inputKB){
            KB.add(new Clause(clause));
        }
        emptyClause = new Clause();
        InfixToPostfix postfixExpression = new InfixToPostfix(sentence);
        CNFGenerator cnfGenerator = new CNFGenerator();
        TreeNode expressionTree = cnfGenerator.postOrder(postfixExpression.getExpressionTree());
        ConjunctiveNormalForm conjunctiveNormalForm = expressionTree.getCNF();
        KB.addAll(conjunctiveNormalForm.getCNF());

    }

    @Override
    public String toString() {
        String claueses = "";
        for(Clause clause : KB){
            claueses += clause;
            claueses += "\n";
        }
        return "Prover{" +
                "KB=\n" + claueses +
                '}';
    }

    /**
     * PL-Resolution Algorithm based on Artificial Intelligence A Modern Approach (3rd Edition)
     * @return boolean
     */
    public boolean plResolution(){
        resolved = false;
        Set<Clause> clauses = KB;
        Set<Clause> newClauseSet = new LinkedHashSet<>();
        do {
            resolved = false;
            ArrayList<Clause> clauseList = new ArrayList<>(clauses);
            for(int i = 0; i < clauseList.size() && !resolved; i++){
                Clause ci = clauseList.get(i);
                for(int j = i + 1; j < clauseList.size() && !resolved && !ci.getClause().isEmpty(); j++){
                    Clause cj = clauseList.get(j);
                    Clause resolvent = plResolve(ci, cj);
                    if(resolved) {
                        clauseList.remove(ci);
                        clauseList.remove(cj);
                        System.out.println(resolvent);
                        System.out.println();
                        clauseList.add(resolvent);
                    }
                }
            }


            clauses = new HashSet<>();
            for(Clause clause : clauseList){
                if(!clause.getClause().isEmpty())
                    clauses.add(clause);
            }

            if(clauses.isEmpty()){
                System.out.print("KB entails: ");
                return true;
            }

            if (!resolved){
                System.out.println("KB contains: ");
                System.out.println(clauses);
                System.out.println("No new clauses were added to KB");
                System.out.println("FAILURE!!");
                System.out.print("KB does not entail: ");
                return false;
            }
            clauses.addAll(newClauseSet);

        }while (true);
    }

    /**
     *
     * @param ci Clause 1
     * @param cj Clause 2
     * @return resolvent
     */
    Clause plResolve(Clause ci, Clause cj){
        Clause resolvent;
        boolean found = false;
        int indexCi = 0;
        int indexCj = 0;
        resolved = false;

        for(int i = 0; i < ci.getClause().size() && !found; i++){
            Literal li = ci.getClause().get(i);
            for(int j = 0; j < cj.getClause().size() && !found; j++){
                Literal lj = cj.getClause().get(j);
                if(li.isComplementary(lj)){
                    found = true;
                    resolved = true;
                    indexCi = i;
                    indexCj = j;
                }
            }
        }
        if(found){
            System.out.println("Resolving...");
            System.out.println(ci);
            System.out.println(cj);
            System.out.println("------------------------------------------");
            ci.getClause().remove(ci.getClause().get(indexCi));
            cj.getClause().remove(cj.getClause().get(indexCj));
        }

        if(ci.getClause().isEmpty() && cj.getClause().isEmpty()){
            resolvent = emptyClause;
            resolved = true;
        }
        else{
            LinkedList<Literal> newClause = new LinkedList<>();
            for(int i = 0; i < ci.getClause().size(); i++) {
                Literal li = new Literal(ci.getClause().get(i));
                newClause.add(li);
            }
            for(int j = 0; j < cj.getClause().size(); j++) {
                Literal lj = new Literal(cj.getClause().get(j));
                newClause.add(lj);
            }
            resolvent = new Clause(newClause);

        }
        return resolvent;
    }

}

