package main.java;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class CNFGenerator {

    public CNFGenerator(){
    }



    public TreeNode postOrder(TreeNode root){
        if(root != null){
            if(root.isUniqueChild()){
                postOrder(root.getUniqueChild());
            }
            if(root.isLeftChild()){
                postOrder(root.getLeft());
            }
            if(root.isRightChild()){
                postOrder(root.getRight());
            }

            //do stuff on the node

            //left node -- operand
            if(root.isOperand()){
                String symbol = root.getStringVal();
                Literal literal = new Literal(symbol,true);
                Clause clauses = new Clause(literal);
                root.setCNF(new ConjunctiveNormalForm(clauses));
            }
            else{
                String symbol = root.getStringVal();
                ConjunctiveNormalForm CNF;
                if(symbol.equals("~")){
                    CNF = getNegationCNF(root.getUniqueChild().getCNF());
                }
                else if(symbol.equals("||")){
                    ConjunctiveNormalForm leftCNF = root.getLeft().getCNF();
                    ConjunctiveNormalForm rightCNF = root.getRight().getCNF();
                    CNF = getDisjunctionCNF(leftCNF,rightCNF);
                }
                else if(symbol.equals("&&")){
                    ConjunctiveNormalForm leftCNF = root.getLeft().getCNF();
                    ConjunctiveNormalForm rightCNF = root.getRight().getCNF();
                    CNF = getConjunctionCNF(leftCNF,rightCNF);
                }
                else if(symbol.equals("=>")){
                    ConjunctiveNormalForm leftCNF = root.getLeft().getCNF();
                    ConjunctiveNormalForm rightCNF = root.getRight().getCNF();
                    CNF = getImplicationCNF(leftCNF,rightCNF);
                }
                else{
                    ConjunctiveNormalForm leftCNF = root.getLeft().getCNF();
                    ConjunctiveNormalForm rightCNF = root.getRight().getCNF();
                    CNF = getBiconditionalCNF(leftCNF,rightCNF);

                }
                root.setCNF(CNF);
            }
        }
        return root;
    }

    private ConjunctiveNormalForm getBiconditionalCNF(ConjunctiveNormalForm leftCNF, ConjunctiveNormalForm rightCNF) {
//        System.out.println("Biconditional");
//        System.out.println(leftCNF);
//        System.out.println(rightCNF);
        ConjunctiveNormalForm leftImplication = getImplicationCNF(leftCNF,rightCNF);
        ConjunctiveNormalForm rightImplication = getImplicationCNF(rightCNF,leftCNF);
//        System.out.println(leftImplication);
//        System.out.println(rightCNF);
        return getConjunctionCNF(leftImplication,rightImplication);
    }

    private ConjunctiveNormalForm getImplicationCNF(ConjunctiveNormalForm leftCNF, ConjunctiveNormalForm rightCNF) {
//        System.out.println("Implication");
        ConjunctiveNormalForm leftTerm = getNegationCNF(leftCNF);
//        System.out.println(leftTerm);
//        System.out.println(rightCNF);
        return getDisjunctionCNF(leftTerm,rightCNF);
    }

    private ConjunctiveNormalForm getConjunctionCNF(ConjunctiveNormalForm leftCNF, ConjunctiveNormalForm rightCNF) {
        ConjunctiveNormalForm conjunctiveNormalForm = leftCNF;
        conjunctiveNormalForm.getCNF().addAll(rightCNF.getCNF());
//        System.out.println(conjunctiveNormalForm.toString());
        return conjunctiveNormalForm;
    }

    private ConjunctiveNormalForm getDisjunctionCNF(ConjunctiveNormalForm leftCNF, ConjunctiveNormalForm rightCNF ) {
//        System.out.println("Disjunction");
        LinkedList<Clause> clauses = new LinkedList<>();
//        System.out.println(leftCNF);
//        System.out.println(rightCNF);
        for(Clause leftClause : leftCNF.getCNF()){
            for(Clause rightClause: rightCNF.getCNF()){
//                System.out.print(leftClause+","+rightClause);
                //Create a new linked-list of literals for each pair of clauses
                LinkedList<Literal> literals = new LinkedList<>();
                literals.addAll(leftClause.getClause());
                literals.addAll(rightClause.getClause());

                //Create a new clause and store.
                Clause clause = new Clause(literals);
                clauses.add(clause);
//                System.out.println(","+clause);
            }
        }

        return new ConjunctiveNormalForm(clauses);
    }

    private ConjunctiveNormalForm getNegationCNFSingleClause(Clause clause){
        LinkedList<Clause> clauses = new LinkedList<>();
        for(Literal literal: clause.getClause()){
            Clause newClause = new Clause(new Literal(literal.getSymbol(),!literal.isPositive()));
            clauses.add(newClause);
        }
        return new ConjunctiveNormalForm(clauses);
    }
    private ConjunctiveNormalForm getNegationCNF(ConjunctiveNormalForm originalChild) {
        ConjunctiveNormalForm child = new ConjunctiveNormalForm(originalChild);
        ConjunctiveNormalForm result;

        if(child.getCNF().size() == 1){
//            System.out.println("Single item");
            result =  getNegationCNFSingleClause(child.getCNF().getFirst());
        }
        else{
            //More than one clause present.
            //Compute negation of first two and store disjunction
            Clause first = child.getCNF().removeFirst();
            Clause second = child.getCNF().removeFirst();
            ConjunctiveNormalForm firstCNF = getNegationCNFSingleClause(first);
            ConjunctiveNormalForm secondCNF = getNegationCNFSingleClause(second);
            result = getDisjunctionCNF(firstCNF,secondCNF);

            //For each of the remaining clauses:
            //1.Compute negated CNF
            //2.Perform disjunction with previous results.
            while (!child.getCNF().isEmpty()){
                Clause clause = child.getCNF().removeFirst();
                ConjunctiveNormalForm conjunctiveNormalForm = getNegationCNFSingleClause(clause);
                result = getDisjunctionCNF(result,conjunctiveNormalForm);
            }
        }
//        System.out.println(result.toString());
        return result;
    }

}
