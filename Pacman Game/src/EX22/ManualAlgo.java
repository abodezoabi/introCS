package EX22;


import exe.ex3.game.PacManAlgo;
import exe.ex3.game.PacmanGame;

public class ManualAlgo implements PacManAlgo{
    public ManualAlgo() {;}
    @Override
    public String getInfo() {
        return "This is a manual algorithm for manual controlling the PacMan using w,a,s,d (up,left,down,right).";
    }

    @Override
    public int move(PacmanGame game) {
        int ans = PacmanGame.ERR;
        Character cmd = Ex3Main.getCMD();
            if (cmd != null) {
                if (cmd == 'w') {ans = PacmanGame.UP;}
                if (cmd == 's') {ans = PacmanGame.DOWN;}//changed it to s 
                if (cmd == 'a') {ans = PacmanGame.LEFT;}
                if (cmd == 'd') {ans = PacmanGame.RIGHT;}
            }
            return  ans;
    }
}
