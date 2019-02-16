package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class Parantheses extends Operator{
    @Override

    public int priority(){
        int  pri = 0;
        return pri;

    }

    @Override

    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}
