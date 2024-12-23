package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.JuggernautPower;


public class Tail3 extends BijuTail {
    public Tail3() {
        super("ThreeTail", "tail-2",3,1);
    }

    @Override
    public void onStartOfTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        gainPower(p, new JuggernautPower(p, passiveAmount));
    }
}
