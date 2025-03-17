package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.watcher.ForesightPower;


public class Tail7 extends BijuTail {
    public Tail7() {
        super(1,"Tail7", "tail7");
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new ForesightPower(p, passiveAmount));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail7();
    }
}
