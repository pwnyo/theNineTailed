package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.RagePower;


public class Tail3 extends BijuTail {
    public Tail3() {
        super(1,"Tail3", "tail3");
    }

    @Override
    public void onStartOfTurn() {
        super.onStartOfTurn();
        gainPower(p, new RagePower(p, passiveAmount));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail3();
    }
}
