import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
//    private String nickName;
    private List<Dominoes> trayDominoes = new ArrayList<>();

    public Player(int id) {
        this.id = id;
//        this.nickName = nickName;
    }

    public int getID() {
        return this.id;
    }

    public Dominoes play(List<Cell> Options) {
        if (Options.isEmpty()) {
            Dominoes d = this.getHighestDominoes();
            this.trayDominoes.remove(d);
            return d;
        }
        for (Cell c : Options) {
            for (Dominoes d : this.trayDominoes) {
                Integer leftV = d.getLeftVal();
                Integer rightV = d.getRightVal();
                if (leftV == c.val) {
                    this.trayDominoes.remove(d);
                    return d;
                }
                if (rightV == c.val) {
                    this.trayDominoes.remove(d);
                    return d;
                }
            }
        }
        return new Dominoes(-1,-1);
    }

    public void addToTray(Dominoes d) {
        this.trayDominoes.add(d);
    }

    public boolean isEmptyTray() {
        return this.trayDominoes.isEmpty();
    }


    public void setTrayDominoes(List<Dominoes> trayDominoes) {
        this.trayDominoes = trayDominoes;
    }

    public Dominoes getHighestDominoes() {
        Dominoes maxDominoes = new Dominoes(-1,-1);
        for (Dominoes d : this.trayDominoes) {
            if (maxDominoes.compareTo(d) > 0) {
                maxDominoes = new Dominoes(d.getLeftVal(), d.getRightVal());
            }
        }
        return maxDominoes;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' + ", " +
                "Tray: " + this.trayDominoes + "}";
    }
}
