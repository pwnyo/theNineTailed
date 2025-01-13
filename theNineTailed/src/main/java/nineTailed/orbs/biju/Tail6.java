package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.NoxiousFumesPower;


public class Tail6 extends BijuTail {
    public Tail6() {
        super("Tail6", "tail-6");
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new NoxiousFumesPower(p, passiveAmount));
    }
}
