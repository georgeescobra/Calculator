package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class SubtractOperator extends Operator{
    @Override

    public int priority(){
        int  pri = 1;
        return pri;

    }

    @Override

    public Operand execute(Operand op1, Operand op2) {
    //holds the result
        Operand random = new Operand(op1.getValue() - op2.getValue());

        return random;
    }
}
