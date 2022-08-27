package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import nineTailed.orbs.Tail;

public class TailWhipAction extends AbstractGameAction {
    private int damage, bonus;

    public TailWhipAction(int damage, int bonus) {
        this.damage = damage;
        this.bonus = bonus;
    }

    public void update() {
        int count = 0;
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof Tail) {
                count++;
            }
        }
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, damage + bonus * count,
                DamageInfo.DamageType.NORMAL, AttackEffect.BLUNT_LIGHT));

        this.isDone = true;
    }
}
