import java.util.ArrayList;
import java.util.List;

public class Game {

    private int numberOfPlayers;
    private GameState gameState;
    private Grid grid;
    private Boneyard boneyard;
    private List<Player> players;
    private Player winner = null;

    public Game(int aNumberOfPlayers) {
        this.numberOfPlayers = aNumberOfPlayers;
        this.gameState = GameState.INITIALISING;
        this.boneyard = Boneyard.getInstance();
        this.grid = new Grid();
        this.players = new ArrayList<>();

        initGame();
    }

    private void initGame() {
        if (this.numberOfPlayers > 4 || this.numberOfPlayers < 2) {
            System.out.println("You cannot play the Game with more than 4 players and less then 2!!!");
        } else {
            System.out.println("Game is starting with the following players: ");
            for (int i = 0; i < this.numberOfPlayers; i++) {
                Player pl1 = new Player(i);
                List<Dominoes> l1 = this.boneyard.getPlayerTrayDominoes(7);
                pl1.setTrayDominoes(l1);
                this.players.add(pl1);
                System.out.println(pl1);
            }
            this.gameState = GameState.PLAYING;
        }
    }

    public void run() {

        int gameRound = 1;

        while (this.gameState == GameState.PLAYING) {
            Player pl1 = this.players.get(0);
            System.out.println("************");
            System.out.printf("* %2d round *\n", gameRound);
            System.out.println("************");
            System.out.println(pl1 + " is playing...");
            Dominoes d1 = pl1.play(grid.getAllOptions());
            while (d1.compareTo(new Dominoes(-1,-1))==0){
                System.out.println("Taking from boneyard");
                Dominoes nxt = this.boneyard.getNextDominoes();
                if (nxt == null){
                    this.gameState = GameState.ENDOFGAME;
                    break;
                }
                pl1.addToTray(nxt);
                System.out.println(pl1 + " is playing...");
                d1 = pl1.play(grid.getAllOptions());
            }
            if (this.gameState == GameState.ENDOFGAME) {
                break;
            }
            Cell.Address addr = grid.findAddressToPlace(d1);
            grid.placeDominoesToGrid(addr, d1);
            System.out.println(grid);
            gameRound++;
            if (pl1.isEmptyTray()) {
                this.gameState = GameState.ENDOFGAME;
                this.winner = pl1;
                break;
            }
            this.players.remove(0);
            this.players.add(pl1);
        }

        if (this.gameState == GameState.ENDOFGAME) {
            if (this.winner==null) {
                this.winner = players.get(players.size()-1);
            }
            System.out.println("********************************************");
            System.out.println("The Final Grid");
            System.out.println(this.grid);
            System.out.println();
            System.out.println("The END of the GAME");
            System.out.println("The winner is Player #" + this.winner.getID());
            System.out.println("Players Dominoes:");
            for (Player pl : this.players) {
                System.out.println(pl);
            }
            System.out.println("********************************************");
        }
    }

}
