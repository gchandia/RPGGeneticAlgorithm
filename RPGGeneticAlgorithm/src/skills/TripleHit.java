package skills;

import characters.Hero;
import genetic.Battle;

public class TripleHit implements Skill {
  private int mpCost;
  
  public TripleHit(int cost) {
    mpCost = cost;
  }
  
  public void useSkill(Hero hero, Battle battle) {
    if (hero.getMP() < mpCost) {return;}
    battle.attack();
    battle.attack();
    battle.attack();
    hero.decreaseMP(mpCost);
  }
}