package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.NoxiousFumesPower;


public class Tail6 extends BijuTail {
    public Tail6() {
        super("SixTail", "tail-6",1,1);
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new NoxiousFumesPower(p, passiveAmount));
    }
}
