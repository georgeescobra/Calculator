package edu.csc413.calculator.evaluator;



import edu.csc413.calculator.operators.*;

import java.util.Stack;
import java.util.StringTokenizer;


public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/() ";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int eval(String expression) {
    String token;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);

    // initialize operator stack - necessary with operator priority schema
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators

    while (this.tokenizer.hasMoreTokens()) {
      // filter out spaces
      if (!(token = this.tokenizer.nextToken()).equals(" ")) {
        // check if token is an operand
        if (Operand.check(token)) {
          operandStack.push(new Operand(token));
        } else {

          //checks if it is a valid operator
          if (!Operator.check(token)) {
            System.out.println("*****invalid token******");
            throw new RuntimeException("*****invalid token******");
          }

          Operator newOperator = Operator.getOperator(token);
          if (operatorStack.isEmpty() || token.equals("(")) {
            operatorStack.push(newOperator);
          } else{
              if (token.equals(")")) {
                //this checks for the "("
                do {
                  //I should have just put this into a method, but did not realize I was going
                  //to use more than once
                  Operator oldOpr = operatorStack.pop();
                  Operand op2 = operandStack.pop();
                  Operand op1 = operandStack.pop();
                  operandStack.push(oldOpr.execute(op1, op2));
                } while (operatorStack.peek().priority() != 0 && operandStack.size() >1);
                operatorStack.pop();
                continue;
              }

              while (operatorStack.peek().priority() >= newOperator.priority()) {
                // note that when we eval the expression 1 - 2 we will
                // push the 1 then the 2 and then do the subtraction operation
                // This means that the first number to be popped is the
                // second operand, not the first operand - see the following code
                Operator oldOpr = operatorStack.pop();
                Operand op2 = operandStack.pop();
                Operand op1 = operandStack.pop();
                operandStack.push(oldOpr.execute(op1, op2));
                if(operatorStack.isEmpty()){
                 break;
                }
              }
              //new operator gets pushed if it is a higher priority
              //or if while loop gets exec
              operatorStack.push(newOperator);

        }
        }
      }
    }

      //runs after there are no more tokens to parse
      while (operandStack.size() > 1) {
        Operand op2 = operandStack.pop();
        Operand op1 = operandStack.pop();
        Operator op3 = operatorStack.pop();
        operandStack.push(op3.execute(op1, op2));
      }
      Operand done = operandStack.pop();
      return done.getValue();

      // Control gets here when we've picked up all of the tokens; you must add
      // code to complete the evaluation - consider how the code given here
      // will evaluate the expression 1+2*3
      // When we have no more tokens to scan, the operand stack will contain 1 2
      // and the operator stack will have + * with 2 and * on the top;
      // In order to complete the evaluation we must empty the stacks (except
      // the init operator on the operator stack); that is, we should keep
      // evaluating the operator stack until it only contains the init operator;
      // Suggestion: create a method that takes an operator as argument and
      // then executes the while loop

      // return 0;

    }
  }


    




