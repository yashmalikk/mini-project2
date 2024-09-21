/**
 * @author: Yash Malik.
 * Made for CSC207 2024Fa.
*/
package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * This class contains all the methods incoporated between fractions
 *  in a calculator.
 */
public class BFCalculator {
  /** Initialize lastValue to 0. */
  private BigFraction lastValue = new BigFraction(BigInteger.ZERO, BigInteger.ONE);

  /**
   *
   * @return Returns the last computed value, which is 0 if nothing has been computed yet
   */
  public BigFraction get() {
    return lastValue;
  } //get method.

  /**
   *
   * @param val to be added.
   */
  public void add(BigFraction val) {
    lastValue = lastValue.add(val);
  } // Adds val to the last computed value.

  /**
   *
   * @param val to be subtracted.
   */
  public void subtract(BigFraction val) {
    lastValue = lastValue.subtract(val);
  } // Subtracts val from the last computed value.

  /**
   *
   * @param val to be multiplied.
   */
  public void multiply(BigFraction val) {
    lastValue = lastValue.multiply(val);
  } // Multiplies the last computed value by val.

  /**
   *
   * @param val to be dived by.
   */
  public void divide(BigFraction val) {
    if (val.equals(BigFraction.ZERO)) {
      System.err.println("Cannot divide by zero.");
      return;
    } // Error handling for division by zero
    lastValue = lastValue.divide(val);
  } //  Divides the last computed value by val.


  /**
   * sets the lastValue to 0.
   */
  public void clear() {
    lastValue = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
  } // sets the lastValue to 0.
} // end of BFCalculator.
