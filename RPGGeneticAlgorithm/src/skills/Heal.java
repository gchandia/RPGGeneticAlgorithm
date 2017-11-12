package skills;

import characters.Hero;
import genetic.Battle;

public class Heal implements Skill {
  private String name = "Heal";
  private int mpCost;
  
  public Heal(int cost) {
    mpCost = cost;
  }
  
  public String getName() {
    return name;
  }
  
  public void useSkill(Hero hero, Battle battle) {
    if (hero.getMP() < mpCost) {return;}
    hero.increaseHP(3 * hero.getMAG());
    hero.decreaseMP(mpCost);
  }
}