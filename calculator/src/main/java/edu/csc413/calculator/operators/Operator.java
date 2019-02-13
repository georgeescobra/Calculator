package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;
import java.util.HashMap;
import java.util.StringTokenizer;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.
    // ALL subclasses of operator MUST be in their own file.
    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );


    static private final HashMap<String, Object> calc = new HashMap<>();
        static{
            //priority starts from 3 down
            //reminder: 2 + 3 * 4 == 14
            calc.put("+", new AddOperator());
            calc.put("-", new SubtractOperator());
            calc.put("*", new MultiplyOperator());
            calc.put("/", new DivideOperator());
            calc.put("^", new PowerOperator());

        }

    public abstract int priority();
    public abstract Operand execute(Operand op1, Operand op2 );

    /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check( String token ) {
        //this will return any non-operator into stk and returns false if there is a token inside it
        StringTokenizer stk = new StringTokenizer(token, "+-/*^() ", false);
            if (stk.hasMoreTokens()){
                return false;
            }else
            return true;

    }

    public static Operator getOperator(String token){
        StringTokenizer stk = new StringTokenizer(token, "+-/*^", true);
        while (stk.hasMoreTokens()){
            if(calc.containsKey(stk)){
                if(stk.equals("+")) {
                    return new AddOperator();
                }else if(stk.equals("-")){
                    return new SubtractOperator();
                }else if(stk.equals("/")){
                    return new DivideOperator();
                }else if(stk.equals("*")){
                    return new MultiplyOperator();
                }else if(stk.equals("^")){
                    return new PowerOperator();
                }
            }

        }
        return null;

    }
}
