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
    if (move == 0) {current.attack(opponent);}
    else if (move == 1) {}
    else {}
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
}