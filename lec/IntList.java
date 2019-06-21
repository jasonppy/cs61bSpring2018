/** constuct a linked list whose element are either null or int */
public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}
	
	public int size() {
		if (this.rest == null) {
			return 1;
		}
		return 1 + this.rest.size();
	}

	public int get(int i){
		if (i >= this.size()) {
			System.out.println("index out of length!");
			return 99999;
		} else if (i == 0) {
			return this.first;
		} else {
			return this.rest.get(i - 1);
		}
	}

	/** Returns an IntList identical to L, but with
	  * each element incremented by x. L is not changed */
	// public static IntList incrList(IntList L, int x) {
	// 	if (L == null) {
	// 		return null;
	// 	}
	// 	Inilist incrementedList = new IntList(L.first + x, null);
	// 	incrementedList.rest = incrList(L.rest, x);
	// 	return incrementedList;
	// }
	public static IntList incrList(IntList L, int x) {
		if (L == null) {
			return null;
		}
		IntList rest = incrList(L.rest, x);
		IntList incrementedList = new IntList(L.first + x, rest);
		return incrementedList;
	}


	/** Returns an IntList identical to L, but with
	  * each element incremented by x. Not allowed to use 
	  * the 'new' keyword */
	// public static IntList dincrList(IntList L, int x) {
	//  	if (L == null) {
	//  		return null;
	//  	} 
	//  	return new IntList(L.first + x, dincrList(L.rest, x));
	// }

	// public static IntList dincrList(IntList L, int x) {
	// 	if (L == null) {
	// 		return null;
	// 	}
	// 	L.first = L.first + x;
	// 	dincrList(L.rest, x);
	// 	return L;
	// }

	public static IntList dincrList(IntList L, int x) {
		if (L == null) {
			return L;
		} else {
			IntList B = L;
			B.first = B.first + x;
			dincrList(B.rest, x);
		}
		return L;
	}

	public static void main(String[] args) {
		IntList L = new IntList(5, new IntList(7, new IntList(9, null)));
		System.out.println(L.size());
		System.out.println(L.get(2));
		System.out.println(dincrList(L, 1).get(1));
		System.out.println(incrList(L, 1).get(1));
	}
}
