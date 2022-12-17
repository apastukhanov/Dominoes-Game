import java.util.HashMap;

public class Dominoes implements Comparable{
    private Integer leftVal;
    private Integer rightVal;

    private HashMap<Integer, Character> diceMap  = new HashMap<>();

    Dominoes (Integer aLeftVal, Integer aRightVal) {
        leftVal = aLeftVal;
        rightVal = aRightVal;
        createDiceMap();
    }

    private void createDiceMap() {
        this.diceMap.put(0, '\u25A1');
        this.diceMap.put(1, '\u2680');
        this.diceMap.put(2, '\u2681');
        this.diceMap.put(3, '\u2682');
        this.diceMap.put(4, '\u2683');
        this.diceMap.put(5, '\u2684');
        this.diceMap.put(6, '\u2685');
    }

    public Integer getLeftVal() {
        return leftVal;
    }

    public Integer getRightVal() {
        return rightVal;
    }

    public boolean isSameValue() {
        return leftVal.equals(rightVal);
    }


    @Override
    public int compareTo(Object o) {
        Dominoes other = (Dominoes)o;
        int compare = other.leftVal.compareTo(this.leftVal);
        if (compare == 0) {
            compare = other.rightVal.compareTo(this.rightVal);
        }
        return compare;
    }

    public String toString() {
        Character leftChar = this.diceMap.get(this.leftVal);
        Character rightChar = this.diceMap.get(this.rightVal);
        if (leftChar !=null)
            return "" + leftChar + rightChar + "|";
        return "[]";
    }
}
