package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * A simple implementation of arbitrary-precision Fractions.
 *
 * @author Samuel A. Rebelsky
 * @author Yash Malik
 */
public class BigFraction {
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+

  /*
   * (1) Denominators are always positive. Therefore, negative fractions
   * are represented with a negative numerator. Similarly, if a fraction
   * has a negative numerator, it is negative.
   *
   * (2) Fractions are not necessarily stored in simplified form. To
   * obtain a fraction in simplified form, one must call the `simplify`
   * method.
   */

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+


  /** The zero constant BigFraction. */
  public static final BigFraction ZERO = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  private BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  private BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    if (denominator.compareTo(BigInteger.ZERO) == 0) {
      System.err.println("Denominator cannot be zero.");
      return;
    } // Check for zero denominator.
    if (denominator.compareTo(BigInteger.ZERO) < 0) {
      this.num = numerator.negate();
      this.denom = denominator.negate();
    } else {
      this.num = numerator;
      this.denom = denominator;
    } // Check for negative numerator.
  } // BigFraction(BigInteger, BigInteger)

/**
 * Simplifies the fraction.
 *
 * @return
 *      the simplified fraction.
 */
  public BigFraction simplify() {
    BigInteger gcd = num.gcd(denom);
    this.num = num.divide(gcd); // Modify the current numerator
    this.denom = denom.divide(gcd); // Modify the current denominator
    return this; // Return the simplified instance
  } // end of simplify.
  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   * Implemented.
   *
   * @param str
   *   The fraction in string form
   */
  public BigFraction(String str) {
    // Check if the input contains a '/'
    if (str.contains("/")) {
      String[] parts = str.split("/");
      if (parts.length == 2) {
        if (isNumeric(parts[0]) && isNumeric(parts[1])) {
          BigInteger numerator = new BigInteger(parts[0]);
          BigInteger denominator = new BigInteger(parts[1]);
          if (denominator.compareTo(BigInteger.ZERO) == 0) {
            this.num = BigInteger.ZERO;
            this.denom = BigInteger.ONE;
          } else {
            this.num = numerator;
            this.denom = denominator.abs();
          } // Ensures a positive denominator
        } else {
          this.num = BigInteger.ZERO;
          this.denom = BigInteger.ONE;
        } // Validate numerator and denominator.
      } else {
        this.num = BigInteger.ZERO;
        this.denom = BigInteger.ONE;
      } // If the format is invalid, default to zero.
    } else {
      if (isNumeric(str)) {
        BigInteger wholeNumber = new BigInteger(str);
        this.num = wholeNumber;
        this.denom = BigInteger.ONE;
      } else {
        this.num = BigInteger.ZERO;
        this.denom = BigInteger.ONE;
      } // If parsing fails, default to zero
    } // Handle whole number input
    simplify(); // Ensure the fraction is simplified on creation
  } // end of BigFraction.

  private boolean isNumeric(String str) {
    if (str == null || str.isEmpty()) {
      return false;
    } // not null.
    for (char c : str.toCharArray()) {
      if (!Character.isDigit(c) && c != '-' && c != '+') {
        return false;
      } // check to determine if a string is numeric.
    } // loop for str.
    return true;
  } // end of isNumeric.


  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Express this fraction as a double.
   *
   * @return the fraction approxiamted as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add another fraction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(addend.denom);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator).simplify();
  } // add(BigFraction)

  /**
   * subtract another fraction from this fraction.
   *
   * @param subtractend
   *   The fraction to subtract.
   *
   * @return the result of the subtraction.
   */
  public BigFraction subtract(BigFraction subtractend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and subtractend's denominator
    resultDenominator = this.denom.multiply(subtractend.denom);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(subtractend.denom)).subtract(subtractend.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator).simplify();
  } // subtract(BigFraction)

  /**
   * multiply another fraction to this fraction.
   *
   * @param multiplend
   *   The fraction to multiply.
   *
   * @return the result of the multiplication.
   */
  public BigFraction multiply(BigFraction multiplend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // Multiply the numerators and denominators correctly
    resultNumerator = this.num.multiply(multiplend.num);
    resultDenominator = this.denom.multiply(multiplend.denom);

    // Return a new BigFraction with the resulting numerator and denominator
    return new BigFraction(resultNumerator, resultDenominator).simplify();
  } // end of multiply.

  /**
   * divide fraction by another fraction.
   *
   * @param divisor
   *   The fraction to divide by.
   *
   * @return the result of the division.
   */
  public BigFraction divide(BigFraction divisor) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // Multiply the numerator by the denominator of the divisor and vice versa
    resultNumerator = this.num.multiply(divisor.denom);
    resultDenominator = this.denom.multiply(divisor.num);

    // Return a new BigFraction with the resulting numerator and denominator
    return new BigFraction(resultNumerator, resultDenominator).simplify();
  } // end of divide.


  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   */
  public String toString() {
    // First, simplify the fraction
    BigFraction simplified = this.simplify();

    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero

    if (this.denom.equals(BigInteger.ONE)) {
      return this.num.toString();
    } // return a whole number if denom is one.
    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return simplified.num + "/" + simplified.denom;
  } // toString()

  /**
   *
   * @return the additive inverse of the BigFraction.
   */
  public BigFraction negate() {
    // Negate the numerator; denominator remains unchanged
    return new BigFraction(this.num.negate(), this.denom);
  } // end of negate.
} // end of BigFraction.
