package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.MayhemPower;


public class Tail10 extends BijuTail {
    public Tail10() {
        super("TenTail", "tail-10",1,1);
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new MayhemPower(p, passiveAmount));
    }
}
