package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.PlatedArmorPower;


public class Tail1 extends BijuTail {
    public Tail1() {
        super("OneTail", "tail-1", 1, 1);
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new PlatedArmorPower(p, passiveAmount));
    }
}
