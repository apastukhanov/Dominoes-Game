public class Cell {

    public Integer val;
    public Address addr;
    public Boolean isLeft;

    public Cell(Integer aVal, Boolean isLeft, Address address) {
        this.val = aVal;
        this.isLeft = isLeft;
        this.addr = address;
    }

    public static class Address implements Comparable{
        public Integer x;
        public Integer y;
        public Address(Integer aX, Integer aY) {
            this.x = aX;
            this.y = aY;
        }

        public String toString() {
            return "[" + this.x +
                    ", " + this.y + "]";
        }

        @Override
        public int compareTo(Object o) {
            Address other = (Address) o;
            int compare = other.x.compareTo(this.x);
            if (compare == 0) {
                compare = other.y.compareTo(this.y);
            }
            return compare;
        }
    }

    public String toString() {
        return "Cell{val=" + this.val + ", " +
                "Address=" + this.addr + "}";
    }

}
