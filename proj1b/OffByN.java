public class OffByN implements CharacterComparator {
    public int N;
    public OffByN(int N) {
        this.N = N;
    }

    public boolean equalChars(char x, char y) {
        if (x - y == N || x - y == -N) {
            return true;
        }
        return false;
    }
}