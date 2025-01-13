package nineTailed.cards.basic;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.orbs.Clone;
import nineTailed.patches.CustomTags;
import nineTailed.patches.IOrbListener;

@AutoAdd.Ignore
public abstract class AbstractRasengan extends AbstractDynamicCard implements IOrbListener {

    protected boolean isDiscounted = false;
    protected int discount = 1;
    protected int requirement = 1;
    public AbstractRasengan(String id, String img, int cst, CardType typ, CardColor col, CardRarity rar, CardTarget tgt) {
        super(id, img, cst, typ, col, rar, tgt);
        countOrbs();
        tags.add(CustomTags.RASEN);
    }

    protected void countOrbs() {
        if (CardCrawlGame.dungeon == null || AbstractDungeon.currMapNode == null ||
                AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            return;
        }
        int count = 0;
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof Clone) {
                count++;
            }
        }
        if (count >= requirement && !isDiscounted) {
            isDiscounted = true;
            setCostForTurn(cost - discount);
        }
        else if (count < requirement && isDiscounted) {
            isDiscounted = false;
            setCostForTurn(cost);
        }
    }
    @Override
    public void onChannel(AbstractOrb o) {
        countOrbs();
    }

    @Override
    public void onEvoke(AbstractOrb o) {
        countOrbs();
    }

    @Override
    public void onLoseOrbSlot() {
        countOrbs();
    }

    @Override
    public void onGainOrbSlot() { }

    @Override
    public void onRemoveOrb() { countOrbs(); }

    public void triggerWhenAddedToHand() {
        isDiscounted = false;
        countOrbs();
    }
}