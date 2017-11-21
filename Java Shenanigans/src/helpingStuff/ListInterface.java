package helpingStuff;

public interface ListInterface<T> {
	boolean isEmpty();

	int size();

	void add(int index, T item) throws ListIndexOutOfBoundsException;

	T get(int index) throws ListIndexOutOfBoundsException;

	T remove(int index) throws ListIndexOutOfBoundsException;

	void removeAll();
}
