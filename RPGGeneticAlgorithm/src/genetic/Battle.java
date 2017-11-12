package genetic;

import characters.Hero;

public class Battle {
  private Hero hero, boss, current, opponent;
  
  public Battle (Hero hero, Hero boss) {
    this.hero = hero;
    this.boss = boss;
    
    if (this.hero.getSPD() > this.boss.getSPD()) {
      current = hero; opponent = boss;
    } else {
      current = boss; opponent = hero;
    }
  }
  
  public void turn (int move) {
    if (move == 0) {attack();}
    else if (move == 1) {guard();}
    else {skill();}
    switchTurn();
  }
  
  public void switchTurn () {
    Hero temp = current;
    current = opponent;
    opponent = temp;
  }
  
  public boolean notOver() {
    return hero.getHP() > 0 && boss.getHP() > 0;
  }
  
  public boolean bossDefeated() {
    return boss.getHP() <= 0;
  }
  
  public void attack() {
    if (current.isGuarding()) {current.unsetGuard();}
    current.attack(opponent);
    if (current.isBerserking()) {current.unsetBerserk();}
  }
  
  public void spell() {
    if (current.isGuarding()) {current.unsetGuard();}
  }
  
  public void guard() {
    current.setGuard();
  }
  
  public void skill() {
    current.getSkill().useSkill(current, this);
  }
}