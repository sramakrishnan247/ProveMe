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

    //PL-Resolution Algorithm based on Artificial Intelligence A Modern Approach (3rd Edition)
    public boolean plResolution(){

        Set<Clause> clauses = KB;
        Set<Clause> newClauseSet = new LinkedHashSet<>();
        do {
            resolved = false;
            ArrayList<Clause> clauseList = new ArrayList<>(clauses);
            for(int i = 0; i < clauseList.size() && !resolved; i++){
                Clause ci = clauseList.get(i);
                for(int j = i + 1; j < clauseList.size() && !resolved && !ci.getClause().isEmpty(); j++){
//                    System.out.println(this.toString());
                    Clause cj = clauseList.get(j);
                    Set<Clause> beforePlResolve = new HashSet<>();
                    Clause oldCi = new Clause(ci);
                    Clause oldCj = new Clause(cj);
                    beforePlResolve.add(ci);
                    beforePlResolve.add(cj);
                    resolved = false;
                    Set<Clause> resolvent = plResolve(ci, cj, beforePlResolve);
                    if(resolved){
                        System.out.println();
                        System.out.println(oldCi.getClause());
                        System.out.println(oldCj.getClause());
                        System.out.println("---------------------------------------");
                        if(resolvent.contains(emptyClause)){
                            System.out.println("empty!");
                        }
                        else{
                            for(Clause c:resolvent){
                                if(!c.getClause().isEmpty())
                                    System.out.print(c.getClause());
                            }
                        }
                        System.out.println();

                    }
                    newClauseSet.addAll(resolvent);
                }
            }
            clauses = new HashSet<>();
            for(Clause clause : clauseList){
                if(!clause.getClause().isEmpty())
                    clauses.add(clause);
            }

            if(clauses.isEmpty() && newClauseSet.contains(emptyClause)){
                System.out.print("KB entails: ");
                return true;
            }

            if (!resolved){
                System.out.println("KB contains: ");
                System.out.println(clauses);
                System.out.print("KB does not entail: ");
                return false;
            }
            clauses.addAll(newClauseSet);

        }while (true);
    }
    private Set<Clause> plResolve(Clause ci, Clause cj, Set<Clause> beforePlResolve){
//        System.out.println(ci+","+cj);
        Set<Clause> resolvent = new LinkedHashSet<>();
        boolean found = false;
        int indexCi = 0;
        int indexCj = 0;

        if(ci.getClause().isEmpty()){
            return beforePlResolve;
        }

        if(cj.getClause().isEmpty()){
            return beforePlResolve;
        }

        for(int i = 0; i < ci.getClause().size() && !found; i++){
            Literal li = ci.getClause().get(i);
            for(int j = 0; j < cj.getClause().size() && !found; j++){
                Literal lj = cj.getClause().get(j);
                if(li.isComplementary(lj)){
                    found = true;
                    this.resolved = true;
                    indexCi = i;
                    indexCj = j;
                }
            }
        }
        if(found){
            ci.getClause().remove(ci.getClause().get(indexCi));
            cj.getClause().remove(cj.getClause().get(indexCj));
        }

        if(ci.getClause().isEmpty() && cj.getClause().isEmpty()){
            resolvent.add(emptyClause);
        }
        else{
            resolvent.add(ci);
            resolvent.add(cj);
        }
        return resolvent;
    }




}

