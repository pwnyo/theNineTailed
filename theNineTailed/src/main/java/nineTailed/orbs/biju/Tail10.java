package nineTailed.orbs.biju;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;


public class Tail10 extends BijuTail {
    public Tail10() {
        super(1,2,"Tail10", "tail10");
    }

    @Override
    public void onStartOfTurn() {
        super.onStartOfTurn();
        for (int i = 0; i < passiveAmount; i++) {
            AbstractDungeon.actionManager.addToBottom(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));
        }
    }

    @Override
    public void updateDescription() {
        applyFocus();
        if (passiveAmount == 1) {
            description = DESC[0];
        }
        else {
            description = DESC[1] + passiveAmount + DESC[2];
        }
    }

    @Override
    public void updateAnimation() {
        super.updateAnimation();
        angle += Gdx.graphics.getDeltaTime() * 180.0f;
    }

    @Override
    public void render(SpriteBatch sb) {
        this.shineColor.a = this.c.a / 2.0F;
        sb.setColor(this.shineColor);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + MathUtils.sin(this.angle / PI_4) * 0.05F + PI_DIV_16, this.scale * 1.1F, this.angle, 0, 0, 108, 108, false, false);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.1F, this.scale + MathUtils.sin(this.angle / PI_4) * 0.05F + PI_DIV_16, -this.angle, 0, 0, 108, 108, false, false);
        sb.setBlendFunction(770, 771);
        sb.setColor(this.c);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle / 12.0F, 0, 0, 108, 108, false, false);// 204
        renderText(sb);
        hb.render(sb);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail10();
    }
}
