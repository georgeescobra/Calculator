package edu.csc413.calculator.evaluator;
import java.lang.Integer;
/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {

  private int val;
  /**
  * construct operand from string token.
  */

  public Operand( String token ) {

      if (!token.matches(".*[a-z].*"))
        val = Integer.parseInt(token);
      else
        System.out.println("Not A Valid Operand");

  }
  /**
   * construct operand from integer
   */
  public Operand( int value ) {
    val = value;
  }
  /**
   * return value of opernad
   */
  public int getValue() {
    return this.val;
  }
  /**
   * Check to see if given token is a valid
   * operand.
   */

  public static boolean check( String token ) {
    int temp = Integer.parseInt(token);
    if(temp >= 0)
      return true;
    else
    return false;
  }

}
