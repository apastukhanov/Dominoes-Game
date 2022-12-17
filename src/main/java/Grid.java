import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int LENGTH = 7;
    private int WIDTH = 29;

    private Dominoes[][] matrix;

    List<Cell> Options = new ArrayList<>();

    Grid() {
        this.matrix = new Dominoes[LENGTH][WIDTH];
    }

    public Cell.Address findAddressToPlace(Dominoes d){
        if (this.Options.isEmpty()) {
            int midL = this.LENGTH / 2;
            int midW = this.WIDTH / 2;
            return new Cell.Address(midL, midW);
        } else {
            for (Cell c : Options) {
                if (c.val == d.getLeftVal() ||
                        c.val == d.getRightVal()) {
                    return c.addr;
                }
            }
        }
        return new Cell.Address(-1,-1);
    }

    public void placeDominoesToGrid(Cell.Address addr, Dominoes d) {
        if (!this.Options.isEmpty()) {
            for (Cell c : this.Options) {
//                System.out.println(c);
                if ((c.val == d.getRightVal()
                        || c.val == d.getLeftVal())) {
                    if (c.isLeft==Boolean.TRUE) {
                        if (d.getRightVal() != c.val) {
                            d = new Dominoes(d.getRightVal(),
                                    d.getLeftVal());
                        }
                        this.matrix[addr.x][addr.y] = d;
                    } else {
                        if (d.getLeftVal() != c.val) {
                            d = new Dominoes(d.getRightVal(),
                                    d.getLeftVal());
                        }
                        this.matrix[addr.x][addr.y] = d;
                    }
                    if (c.addr.compareTo(addr) == 0) {
                        this.Options.remove(c);
                        break;
                    }
                }
            }
        } else {
            this.matrix[addr.x][addr.y] = d;
        }
        if (!d.isSameValue() || this.Options.isEmpty()) {
            if (addr.y - 1 >= 0) {
                if (this.matrix[addr.x][addr.y-1] == null) {
                    this.Options.add(new Cell(d.getLeftVal(), Boolean.TRUE,
                            new Cell.Address(addr.x, addr.y - 1)));
                }
            }
            if (addr.y + 1 < this.WIDTH) {
                if (this.matrix[addr.x][addr.y + 1] == null) {
                    this.Options.add(new Cell(d.getRightVal(), Boolean.FALSE,
                            new Cell.Address(addr.x, addr.y+1)));
                }
            }
        } else {
            if (addr.x - 1 >= 0) {
                if (this.matrix[addr.x - 1][addr.y] == null) {
                    this.Options.add(new Cell(d.getLeftVal(), Boolean.TRUE,
                            new Cell.Address(addr.x - 1, addr.y)));
                }
            }
            if (addr.x + 1 < this.LENGTH) {
                if (this.matrix[addr.x + 1][addr.y] == null) {
                    this.Options.add(new Cell(d.getRightVal(), Boolean.FALSE,
                            new Cell.Address(addr.x + 1, addr.y)));
                }
            }
        }
    }


    public List<Cell> getAllOptions() {
        return this.Options;
    }

    public void fillGridWithDominoes() {
        Boneyard b = Boneyard.getInstance();
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                Dominoes d = b.getNextDominoes();
                this.matrix[i][j] = d;
            }
        }
    }

    public String toString() {
        StringBuilder outputGrid = new StringBuilder();
        outputGrid.append("|");
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                if (this.matrix[i][j] == null){
                    outputGrid.append("  |");
                } else {
                    outputGrid.append(this.matrix[i][j].toString());
                }
            }
            if (i+1< this.matrix.length)
                outputGrid.append("\n|");
        }
        return outputGrid.toString();
    }

}
