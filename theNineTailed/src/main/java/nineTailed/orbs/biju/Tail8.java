package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.watcher.WaveOfTheHandPower;


public class Tail8 extends BijuTail {
    public Tail8() {
        super("EightTail", "tail-8",1,1);
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new WaveOfTheHandPower(p, passiveAmount));
    }
}
