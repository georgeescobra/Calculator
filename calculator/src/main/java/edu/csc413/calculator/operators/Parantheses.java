package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class Parantheses extends Operator{
    @Override
    //I set the priority to 0 so that when this is compared to with another
    //operator then the newOperator will always be pushed
    public int priority(){
        int  pri = 0;
        return pri;

    }

    @Override
    //this operator has no execute
    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}
