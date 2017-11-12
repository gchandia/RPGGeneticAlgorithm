package skills;

import characters.Hero;
import genetic.Battle;

public class Doom implements Skill {
  private String name = "Doom";
  private int mpCost;
  
  public Doom(int cost) {
    mpCost = cost;
  }
  
  public String getName() {
    return name;
  }
  
  public void useSkill(Hero hero, Battle battle) {
    if (hero.getMP() < mpCost) {return;}
    hero.increaseHP(3 * hero.getMAG());
    battle.castDoom();
    hero.decreaseMP(mpCost);
  }
}