package skills;

import characters.Hero;
import genetic.Battle;

public class Berserk implements Skill {
  private String name = "Berserk";
  private int mpCost;
  
  public Berserk(int cost) {
    mpCost = cost;
  }
  
  public String getName() {
    return name;
  }
  
  public void useSkill(Hero hero, Battle battle) {
    if (hero.getMP() < mpCost) {return;}
    hero.setBerserk();
    battle.attack();
    hero.decreaseMP(mpCost);
  }
}