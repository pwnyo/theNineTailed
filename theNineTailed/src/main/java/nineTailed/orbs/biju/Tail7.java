package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.ThornsPower;


public class Tail7 extends BijuTail {
    public Tail7() {
        super("SevenTail", "tail-7",1,1);
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new ThornsPower(p, passiveAmount));
    }
}
