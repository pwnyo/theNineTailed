package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.RagePower;


public class Tail4 extends BijuTail {
    public Tail4() {
        super("FourTail", "tail-4",4,1);
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new RagePower(p, passiveAmount));
    }
}
