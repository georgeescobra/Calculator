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

  public Operand(String token) {
    val = Integer.parseInt(token);
  }

  /**
   * construct operand from integer
   */
  public Operand(int value) {
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

  public static boolean check(String token) {
      try {
        int temp = Integer.parseInt(token);
        if (temp >= 0) {
          return true;
        } else
          return false;
      } catch (NumberFormatException ep) {
        return false;
      }
    }
}