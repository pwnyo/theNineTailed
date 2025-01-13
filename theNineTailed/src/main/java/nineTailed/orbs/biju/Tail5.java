package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.watcher.VigorPower;


public class Tail5 extends BijuTail {
    public Tail5() {
        super(2,"Tail5", "tail-5");
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new VigorPower(p, passiveAmount));
    }
}
