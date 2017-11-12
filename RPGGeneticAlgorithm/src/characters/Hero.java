package characters;

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
  public void attack(Hero enemy);
  public void getAttacked(int atk);
  public int getDamage();
  public void restore();
  public int getFitness();
  public void setFitness(int fitness);
  public double getNormalizedFitness();
  public void setNormalizedFitness(double fitness);
}