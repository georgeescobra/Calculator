package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator {
    public int priority(){
        int priority = calc.get("^");
        return priority;

    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}
