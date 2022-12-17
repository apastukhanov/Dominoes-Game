import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Boneyard {
    private List<Dominoes> trayDominoes = new ArrayList<>();
    private static Boneyard instance = null;
    private Boneyard() {
        createDominoesTray();
    }

    public static Boneyard getInstance() {
        if (instance == null){
            instance = new Boneyard();
        }
        return instance;
    }

    public Dominoes getNextDominoes() {
        Random rnd = new Random();
        if (!this.isEmptyTray()) {
        int lenTray = this.trayDominoes.size();
        int nxtRnd = rnd.nextInt(lenTray);
        Dominoes d = this.trayDominoes.get(nxtRnd);
        this.trayDominoes.remove(d);
        return d;
        } else {
            System.out.println("Boneyard is empty!!!");
            return null;
        }
    }

    public boolean isEmptyTray() {
        return this.trayDominoes.isEmpty();
    }


    public List<Dominoes> getPlayerTrayDominoes(int countOfDominoes){
        Random rnd = new Random();
        List<Dominoes> outputTray = new ArrayList<>();
        for (int i = 0; i < countOfDominoes; i++) {
            int sizeTray = this.trayDominoes.size();
            int nxtInt = rnd.nextInt(sizeTray);
            Dominoes d = this.trayDominoes.get(nxtInt);
            this.trayDominoes.remove(d);
            outputTray.add(d);
        }
        return outputTray;
    }

    private void createDominoesTray() {
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                Dominoes d = new Dominoes(i, j);
                if (!this.trayDominoes.contains(d)){
                    this.trayDominoes.add(d);
                }
            }
        }
    }

    public String toString() {
        return "Boneyard contains " + this.trayDominoes.size() +
                " dominoes: " + this.trayDominoes;
    }
}
