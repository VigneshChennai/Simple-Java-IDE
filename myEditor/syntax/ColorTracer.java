package myEditor.syntax;

import java.util.ArrayList;
import base.Pair;

class InvalidSyntaxException extends Exception {
	private static final long serialVersionUID = -468647261150403257L;
}

public class ColorTracer {
	private static int hotPointer = -1;
	private static int currentState = 0;
	private static int makeTransition[][] = {
	/* Transition Table */
	/* {other,\n, \, /, *, ', "} */
	/* q0 */{ 0, 0, 0, 1, 0, 10, 8 },
	/* q1 */{ 0, 0, -1, 2, 3, 0, 8 },
	/* q2 */{ 2, 0, 2, 2, 2, 2, 2 },
	/* q3 */{ 6, 6, 6, 6, 4, 6, 6 },
	/* q4 */{ 5, 5, 5, 0, 7, 5, 5 },
	/* q5 */{ 5, 5, 5, 5, 7, 5, 5 },
	/* q6 */{ 6, 6, 6, 6, 7, 6, 6 },
	/* q7 */{ 7, 6, 6, 0, 7, 6, 6 },
	/* q8 */{ 8, -1, 9, 8, 8, 8, 0 },
	/* q9 */{ 8, 8, 8, 8, 8, 8, 8 },
	/* q10 */{ 10, -1, 11, 10, 10, 0, -1 },
	/* q11 */{ 10, -1, 10, -1, -1, 10, 10 } };

	private static ArrayList<Pair<Integer>> comments = new ArrayList<Pair<Integer>>();
	private static ArrayList<Pair<Integer>> documentation = new ArrayList<Pair<Integer>>();
	private static ArrayList<Pair<Integer>> singleQuotes = new ArrayList<Pair<Integer>>();
	private static ArrayList<Pair<Integer>> doubleQuotes = new ArrayList<Pair<Integer>>();

	public static ArrayList<Pair<Integer>>  getCommentsLocs() {
		return comments;
	}

	public static ArrayList<Pair<Integer>> getDocuCommentsLocs() {
		return documentation;
	}

	public static ArrayList<Pair<Integer>> getSingleQuotesLocs() {
		return singleQuotes;
	}

	public static ArrayList<Pair<Integer>> getDoubleQuotesLocs() {
		return doubleQuotes;
	}

	private static void assignPointerValuesInPairs(int index) {
		if (hotPointer == -1 || currentState == 0) {
			switch (currentState) {
			case 2: /* q2 */
				hotPointer = 0; // It is a comment
				comments.add(new Pair<Integer>(index - 1, 0));
				//System.out.printf("\nComment noted at position %d", index - 1);
				break;
			case 6: /* q3 */
				hotPointer = 0; /* It is a comment */// Since Both // and /* */
														// will be same color
														// both hot pointers are
														// zero.
				comments.add(new Pair<Integer>(index - 2, 0));
				//System.out.printf("\nComment noted at position %d\n", index - 2);
				break;
			case 5: /* q5 */
				hotPointer = 1; // It is a /*** Documentation */
				documentation.add(new Pair<Integer>(index - 3, 0));
				//System.out.printf("\nDocumentation noted at position %d\n",index - 3);
				//System.out.println(documentation.size());
				break;
			case 8: /* q8 */
				hotPointer = 2; // "It is a String"
				doubleQuotes.add(new Pair<Integer>(index, 0));
				//System.out.printf("\nString noted at position %d\n", index);
				break;
			case 10: /* q10 */
				hotPointer = 3; // 'I't is a 'c'har
				singleQuotes.add(new Pair<Integer>(index, 0));
				//System.out.printf("\nChar noted at position %d\n", index);
				break;
			case 0: /* On initial state q0 */
				switch (hotPointer) {
				case 0: // If I am in end of comment.
					(comments.get(comments.size() - 1)).setSecond(index);
					break;
				case 1: // If I am in end of Documentation.
					(documentation.get(documentation.size() - 1))
							.setSecond(index);
					break;
				case 2: // If I am in end of String.
					(doubleQuotes.get(doubleQuotes.size() - 1))
							.setSecond(index);
					break;
				case 3: // If I am in end of char.
					(singleQuotes.get(singleQuotes.size() - 1))
							.setSecond(index);
				case -1: /* Already in q1 only */
					/* Do nothing */
				}
				// Resetting the hotPointer.
				hotPointer = -1;
			}
		}
	}

	private static int symbolToInteger(char c) {
		switch (c) {
		case '\n':
			return 1;
		case '\\':
			return 2;
		case '/':
			return 3;
		case '*':
			return 4;
		case '\'':
			return 5;
		case '\"':
			return 6;
		default: /* on others */
			return 0;

		}
	}

	public synchronized static void traceOut(String s) {

		int index = 0;
		comments.clear();
		documentation.clear();
		singleQuotes.clear();
		doubleQuotes.clear();

		// Now read the buffered stream.
		while (index < s.length()) {
			char c = s.charAt(index);
			index++;
			//if(currentState >= 0)
				currentState = makeTransition[currentState][symbolToInteger(c)];
			try {
				if (currentState == -1)
					throw new InvalidSyntaxException();
			}

			catch (InvalidSyntaxException e) {
				System.out.println("InvalidSyntax at index" + index);
				return;
			}
			assignPointerValuesInPairs(index);
		}

	}

}