

public class ArrayDeque<Item> {
    public Item[] items;
    public int size;
    public int nextFirst;
    public int nextLast;
    public double usageFactor = 0.25;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int size() {
        return size;
    }

    public void resize(int cap) {
        Item[] a = (Item[]) new Object[cap];
        int current = plusOne(nextFirst);
        for (int i = 0; i < size - 1; i++) {
            a[i] = items[current];
            current = plusOne(current);
        }
        items = a;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int plusOne(int index) {
        index++;
        if(index == items.length) {
            index = 0;
        }
        return index;
    }

    public int minusOne(int index) {
        index--;
        if(index < 0) {
            index = items.length - 1;
        }
        return index;
    }

    public void addFirst(Item x) {
        if (size == items.length - 1) {
            resize(size * 2);
        }

        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
    }

    public void addLast(Item x) {
        if (size == items.length - 1) {
            resize(size * 2);
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
    }

    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        int first = plusOne(nextFirst);
        Item item = items[first];
        items[first] = null;
        nextFirst = first;
        if (items.length >= 16 && size/items.length < usageFactor) {
            resize(Math.toIntExact(Math.round(size * usageFactor)));
        }
        return item;
    }

    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        int last = minusOne(nextLast);
        Item item = items[last];
        items[last] = null;
        if (items.length >= 16 && size/items.length < usageFactor) {
            resize(Math.toIntExact(Math.round(size * usageFactor)));
        }
        return item;
    }

    public Item get(int index) {
        if (index > size - 1) {
            return null;
        }
        int current = plusOne(nextFirst);
        while(index > 0) {
            current = plusOne(current);
            index --;
        }
        return items[current];
    }

}