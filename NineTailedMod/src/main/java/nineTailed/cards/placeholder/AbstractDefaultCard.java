package nineTailed.cards.placeholder;

import basemod.abstracts.CustomCard;

public abstract class AbstractDefaultCard extends CustomCard {
    
    public int magicNumber2;
    public int baseMagicNumber2;
    public boolean upgradedMagicNumber2;
    public boolean isMagic2Modified;
    
    public AbstractDefaultCard(final String id,
                               final String name,
                               final String img,
                               final int cost,
                               final String rawDescription,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {
        
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        isMagic2Modified = false;
    }
    
    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedMagicNumber2) {
            magicNumber2 = baseMagicNumber2;
            isMagic2Modified = true;
        }
    }
    
    public void upgradeMagic2(int amount) {
        baseMagicNumber2 += amount;
        magicNumber2 = baseMagicNumber2;
        upgradedMagicNumber2 = true;
    }
}