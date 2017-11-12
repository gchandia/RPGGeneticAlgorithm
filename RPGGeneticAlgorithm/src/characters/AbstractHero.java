package characters;

import java.util.Random;
import skills.Skill;

public abstract class AbstractHero implements Hero {
  private int HP, MP, ATK, DEF, MAG, SPD, guardDEF, berATK, healHP;
  private int bHP, bMP, bATK, bDEF, bMAG, bSPD;
  private int fitness;
  private double normalizedFitness;
  private boolean guarding = false, berserking = false;
  private Skill skill;
  
  public AbstractHero(int hp, int mp, int atk, int def, int mag, int spd) {
    HP = hp > 9999? 9999 : hp; MP = mp > 999? 999 : mp; 
    ATK = atk > 500? 500 : atk; DEF = def > 500? 500 : def; 
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
    int oldHP = HP, newHP = HP + hp;
    HP = newHP > bHP? bHP : newHP ;
    healHP += newHP - oldHP;
  }
  
  public void decreaseHP(int hp) {
    HP -= hp;
  }
  
  public void decreaseMP(int mp) {
    MP -= mp;
  }
  
  public void attack(Hero enemy) {
    Random random = new Random();
    if (random.nextInt(101) <= this.SPD) {  //speed determines accuracy
      enemy.getAttacked(ATK);
    }
  }
  
  public void spell(Hero enemy) {
    Random random = new Random();
    if (random.nextInt(101) <= this.SPD) {
      enemy.getAttacked(MAG);
    }
  }
  
  public void getAttacked(int atk) {
    int formula = 4 * atk - 2 * DEF;
    decreaseHP(formula <= 0? 1 : formula);
  }
  
  public boolean isGuarding() {
    return guarding;
  }
  
  public void setGuard() {
    if (guarding) {return;}
    guarding = true;
    guardDEF = DEF;
    DEF += Math.round(0.7 * DEF);
  }
  
  public void unsetGuard() {
    guarding = false;
    DEF = guardDEF;
  }
  
  public void setSkill(Skill skill) {
    this.skill = skill;
  }
  
  public Skill getSkill() {
    return skill;
  }
  
  public boolean isBerserking() {
    return berserking;
  }
  
  public void setBerserk() {
    if (berserking) {return;}
    berserking = true;
    berATK = ATK;
    ATK *= 4;
    int spdFormula = (int) (SPD - Math.round(SPD * 0.1));
    int defFormula = (int) (DEF - Math.round(DEF * 0.1));
    SPD = spdFormula < 10? 10 : spdFormula;
    DEF = defFormula < 10? 10 : defFormula;
  }
  
  public void unsetBerserk() {
    berserking = false;
    ATK = berATK;
  }
  
  public int getDamage() {
    return bHP - HP;
  }
  
  public int getHealedHP() {
    return healHP;
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