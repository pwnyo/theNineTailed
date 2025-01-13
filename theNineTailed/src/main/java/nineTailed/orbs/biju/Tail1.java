package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.PlatedArmorPower;


public class Tail1 extends BijuTail {
    public Tail1() {
        super(2,"Tail1", "tail-1");
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new PlatedArmorPower(p, passiveAmount));
    }
}
