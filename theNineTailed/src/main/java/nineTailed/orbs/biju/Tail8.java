package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.watcher.WaveOfTheHandPower;


public class Tail8 extends BijuTail {
    public Tail8() {
        super("Tail8", "tail-8");
    }

    @Override
    public void onStartOfTurn() {
    }

    @Override
    public void onEndOfTurn() {
        gainPower(p, new DrawCardNextTurnPower(p, passiveAmount));
    }
}
