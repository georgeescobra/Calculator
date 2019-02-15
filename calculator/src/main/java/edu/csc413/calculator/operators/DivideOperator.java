package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class DivideOperator extends Operator {
    @Override

    public int priority() {
        int  pri = 2;
        return pri;

    }

    @Override

    public Operand execute(Operand op1, Operand op2) {

        Operand result = new Operand(op1.getValue() / op2.getValue());

        return result;
    }

}
