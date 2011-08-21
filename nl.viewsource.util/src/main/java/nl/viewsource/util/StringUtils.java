package nl.viewsource.util;

/**
 * Utility class with some convenience methods on java.lang.String.
 * 
 * @author Marcel Akkerman
 */
public final class StringUtils {

	// Suppress default constructor to prevent instantiation
	private StringUtils() {
		// This constructor will never be invoked
	}

	public static final String EMPTY = "";

	/**
	 * Checks if the parameter is empty. A parameter is considered empty if
	 * <ul>
	 * <li>the variable is null or,
	 * <li>the length of the string is 0 or,
	 * <li>the string contains only whitespace.
	 * 
	 * @param s
	 * @return true if empty, false otherwise
	 */
	public static boolean isEmpty(final String s) {
		return EMPTY.equals(notNull(s).trim());
	}

	/**
	 * Returns the empty String if parameter is null.
	 * 
	 * @param s the string to make null-save
	 * @return empty string if s is null, s otherwise
	 */
	public static String notNull(final String s) {
		return s == null ? EMPTY : s;
	}

	private static final String intPattern = "-?\\d+";		

	/**
	 * Checks if the provided parameter contains only digits, possibly starting
	 * with a minus (-) sign.<br>
	 * When this method returns <code>true</code> parsing the parameter to 
	 * an Integer should not yield a <code>NumberFormatException</code> 
	 * (unless the integer is out of range)
	 * 
	 * @param s parameter possibly holding an integer
	 * @return <code>true</code> if parameter is an integer, <code>false</code> otherwise
	 */
	public static boolean isInteger(final String s) {
		return !isEmpty(s) && s.matches(intPattern);
	}

	private static String ZEROES = "0000000000";

	/**
	 * Returns a left zero padded string.
	 * 
	 * @param s string to pad
	 * @param len total length of the desired string
	 * @return left zero padded string
	 */
	public static String zeroPadd(final String s, final int len) {
		String ns = notNull(s);

		if (ns.length() < len) { // pad on left with zeros
			StringBuffer padded = new StringBuffer(ZEROES);
			for (int i = 0, max = len / 10; i < max; i++) {
				padded.append(ZEROES);
			}
			padded.append(ns);

			ns = padded.substring(padded.length() - len);
		} else {
			assert ns.equals(s);
		}
		return ns;
	}

	/**
	 * Converts the the first character of the given string to upper case.
	 * 
	 * @param s string to convert
	 * @return string with first character to Upper case (or <code>s</code>,
	 *         if <code>s</code> is empty)
	 */
	public static String firstToUpper(String s) {
		if (isEmpty(s)) {
			return s;
		}

		if (s.length() == 1) {
			return s.toUpperCase();
		}

		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

}