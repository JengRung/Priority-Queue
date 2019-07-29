import java.util.ArrayList;

/**
 * 
 * TODO PriorityQueue
 *
 * @author Jeng-Rung Tu.
 *         Created Sep 29, 2018.
 * @param <T>
 */

public class PriorityQueue<T extends Comparable<? super T>> extends ArrayList<T> {

	public boolean add(T e) {
		if (e == null) {
			throw new NullPointerException();
		}
		super.add(e);
		addRotate(super.size() - 1);
		return true;
	}

	private void addRotate(int i) {
		
		while (super.get(i).compareTo(super.get((i-1) / 2)) < 0) {
			int det = (i-1) / 2;
			T cur = super.get(i);
			super.set(i, super.get(det));
			super.set(det, cur);
			i = det;
		}
	}

	public boolean contains(Object o) {
		return super.contains(o);
	}

	public boolean offer(T e) {
		return this.add(e);
	}

	public T peek() {
		if (super.size() == 0) {
			return null;
		}
		return super.get(0);
	}

	public boolean remove(T e) {

		if (super.isEmpty()) {
			throw new NullPointerException();
		}

		if (!super.contains(e)) {
			return false;
		}

		if (super.size() == 1) {
			super.clear();
			return true;
		}

		int ind = super.indexOf(e);
		super.set(ind, super.get(super.size() - 1));
		super.remove(super.size() - 1);
		this.removeRotate(ind);

		return true;
	}

	private void removeRotate(int i) {

		if (i * 2 + 1 > super.size() - 1) { 
			return;
		}

		if (i * 2 + 1 == super.size() - 1) {
			if (super.get(i).compareTo(super.get(i * 2 + 1)) > 0) {
				T cur = super.get(i);
				super.set(i, super.get(i * 2 + 1));
				super.set(i * 2 + 1, cur);
				i = i * 2 + 1;
			}
			return;
		}

		while (super.get(i).compareTo(super.get(i * 2 + 1)) > 0 || super.get(i).compareTo(super.get(i * 2 + 2)) > 0) {
			if (super.get(i * 2 + 2).compareTo(super.get(i * 2 + 1)) < 0) {
				T cur = super.get(i);
				super.set(i, super.get(i * 2 + 2));
				super.set(i * 2 + 2, cur);
				i = i * 2 + 2;
			} else {
				T cur = super.get(i);
				super.set(i, super.get(i * 2 + 1));
				super.set(i * 2 + 1, cur);
				i = i * 2 + 1;
			}
			if (i * 2 + 1 >= this.size() - 1) {
				break;
			}
		}
	}

	public T poll() {
		if (super.size() == 0) {
			return null;
		}
		T first = super.get(0);
		this.remove(first);
		return first;
	}

	public Object[] toArray() {
		return super.toArray();
	}
}
