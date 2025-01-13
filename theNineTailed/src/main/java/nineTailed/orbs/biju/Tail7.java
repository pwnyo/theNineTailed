package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.watcher.ForesightPower;


public class Tail7 extends BijuTail {
    public Tail7() {
        super("Tail7", "tail-7");
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new ForesightPower(p, passiveAmount));
    }
}
