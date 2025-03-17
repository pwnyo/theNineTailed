package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.JuggernautPower;


public class Tail3 extends BijuTail {
    public Tail3() {
        super(2,"Tail3", "tail3");
    }

    @Override
    public void onStartOfTurn() { gainPower(p, new JuggernautPower(p, passiveAmount)); }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail3();
    }
}
