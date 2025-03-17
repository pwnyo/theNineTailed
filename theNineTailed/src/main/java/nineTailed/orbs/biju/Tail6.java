package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.NoxiousFumesPower;


public class Tail6 extends BijuTail {
    public Tail6() {
        super(1,"Tail6", "tail6");
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new NoxiousFumesPower(p, passiveAmount));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail6();
    }
}
