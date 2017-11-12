package skills;

import characters.Hero;
import genetic.Battle;

public interface Skill {
  public String getName();
  public void useSkill(Hero hero, Battle battle);
}