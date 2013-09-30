package base;

public class Pair<T> {
	private T __first, __second;
	public Pair(T first,T second) {
		__first = first;
		__second = second;
	}
	public T getFirst() {
		return __first;
	}
	public T getSecond() {
		return __second;
	}
	public void setSecond(T __second) {
		   this.__second = __second;
	}
	public void setFirst(T __second) {
		   this.__first = __second;
	}
	public String toString() {
		return "[" + __first + ", " + __second +"]";
	}
}