package characters;

import skills.Skill;

public interface Hero {
  public int getHP();
  public int getMP();
  public int getATK();
  public int getDEF();
  public int getMAG();
  public int getSPD();
  public int[] getBaseStats();
  public void increaseHP(int hp);
  public void decreaseHP(int hp);
  public void decreaseMP(int mp);
  public void attack(Hero enemy);
  public void spell(Hero enemy);
  public void doom(Hero enemy);
  public void getAttacked(int atk);
  public boolean isGuarding();
  public void setGuard();
  public void unsetGuard();
  public void setSkill(Skill skill);
  public Skill getSkill();
  public boolean isBerserking();
  public void setBerserk();
  public void unsetBerserk();
  public int getDamage();
  public int getHealedHP();
  public void restore();
  public int getFitness();
  public void setFitness(int fitness);
  public double getNormalizedFitness();
  public void setNormalizedFitness(double fitness);
}