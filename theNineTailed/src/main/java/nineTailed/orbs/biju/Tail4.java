package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.RagePower;


public class Tail4 extends BijuTail {
    public Tail4() {
        super(2,"Tail4", "tail-4");
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new RagePower(p, passiveAmount));
    }
}
