package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.RagePower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;


public class Tail4 extends BijuTail {
    public Tail4() {
        super(1,"Tail4", "tail4");
    }

    @Override
    public void onStartOfTurn() {
        super.onStartOfTurn();
        gainPower(p, new VigorPower(p, passiveAmount));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail4();
    }
}
