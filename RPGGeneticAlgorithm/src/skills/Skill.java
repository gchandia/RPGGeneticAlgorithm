package skills;

import characters.Hero;
import genetic.Battle;

public interface Skill {
  public void useSkill(Hero hero, Battle battle);
}