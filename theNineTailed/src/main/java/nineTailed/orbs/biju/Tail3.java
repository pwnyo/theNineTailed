package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.JuggernautPower;


public class Tail3 extends BijuTail {
    public Tail3() {
        super(2,"Tail3", "tail-2");
    }

    @Override
    public void onStartOfTurn() { gainPower(p, new JuggernautPower(p, passiveAmount)); }
}
