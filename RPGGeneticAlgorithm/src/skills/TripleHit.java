package skills;

import characters.Hero;
import genetic.Battle;

public class TripleHit implements Skill {
  private String name = "Triple hit";
  private int mpCost;
  
  public TripleHit(int cost) {
    mpCost = cost;
  }
  
  public String getName() {
    return name;
  }
  
  public void useSkill(Hero hero, Battle battle) {
    if (hero.getMP() < mpCost) {return;}
    battle.attack();
    battle.attack();
    battle.attack();
    hero.decreaseMP(mpCost);
  }
}