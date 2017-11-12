package characters;

import java.util.Random;

public abstract class AbstractHero implements Hero {
  private int HP, MP, ATK, DEF, MAG, SPD;
  private int bHP, bMP, bATK, bDEF, bMAG, bSPD;
  private int fitness;
  private double normalizedFitness;
  
  public AbstractHero(int hp, int mp, int atk, int def, int mag, int spd) {
    HP = hp > 9999? 9999 : hp; MP = mp > 999? 999 : mp; 
    ATK = atk > 500? 500 : atk; DEF = def > 500? 500 : atk; 
    MAG = mag > 500? 500 : mag; SPD = spd > 100? 100 : spd;
    bHP = HP; bMP = MP; bATK = ATK; bDEF = DEF; bMAG = MAG; bSPD = SPD;
  }
  
  public int getHP() {
    return HP;
  }
  
  public int getMP() {
    return MP;
  }
  
  public int getATK() {
    return ATK;
  }
  
  public int getDEF() {
    return DEF;
  }
  
  public int getMAG() {
    return MAG;
  }
  
  public int getSPD() {
    return SPD;
  }
  
  public int[] getBaseStats() {
    int[] base = {bHP, bMP, bATK, bDEF, bMAG, bSPD};
    return base;
  }
  
  public void increaseHP(int hp) {
    HP += hp;
  }
  
  public void decreaseHP(int hp) {
    HP -= hp;
  }
  
  public void attack(Hero enemy) {
    Random random = new Random();
    if (random.nextInt(101) <= this.SPD) {  //speed determines accuracy
      enemy.getAttacked(ATK);
    }
  }
  
  public void getAttacked(int atk) {
    int formula = 4 * atk - 2 * DEF;
    decreaseHP(formula <= 0? 1 : formula);
  }
  
  public int getDamage() {
    return bHP - HP;
  }
  
  public void restore() {
    HP = bHP; MP = bMP; ATK = bATK; DEF = bDEF; MAG = bMAG; SPD = bSPD;
  }
  
  public int getFitness() {
    return fitness;
  }
  
  public void setFitness(int fitness) {
    this.fitness = fitness;
  }
  
  public double getNormalizedFitness() {
    return normalizedFitness;
  }
  
  public void setNormalizedFitness(double fitness) {
    this.normalizedFitness = fitness;
  }
}