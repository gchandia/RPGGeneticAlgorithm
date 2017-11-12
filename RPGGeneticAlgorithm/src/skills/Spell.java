package skills;

import characters.Hero;
import genetic.Battle;

public class Spell implements Skill {
  private String name = "Spell";
  private int mpCost;
  
  public Spell(int cost) {
    mpCost = cost;
  }
  
  public String getName() {
    return name;
  }
  
  public void useSkill(Hero hero, Battle battle) {
    if (hero.getMP() < mpCost) {return;}
    battle.spell();
    hero.decreaseMP(mpCost);
  }
}