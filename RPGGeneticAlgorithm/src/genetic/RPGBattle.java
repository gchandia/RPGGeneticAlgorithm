package genetic;

import java.util.Arrays;
import java.util.Random;
import characters.Boss;
import characters.Healer;
import characters.Hero;
import characters.Mage;
import characters.Paladin;
import characters.Warrior;
import skills.Berserk;
import skills.Doom;
import skills.Heal;
import skills.Spell;
import skills.TripleHit;

public class RPGBattle {
  static Random random = new Random();
  static int[] winnerStats, bossStats;
  static String winnerSkill, bossSkill;
  private int totalGenerations = 1;
  
  private int getFitnessSum(Hero[] population) {
    int sum = 0;
    for (Hero hero : population) {
      sum += hero.getFitness();
    }
    return sum;
  }
  
  private Hero[] sortByFitness(Hero[] population) {
    Hero[] sorted = population.clone();
    Hero fittest, swap;
    int index;
    
    for (int i = 0; i < sorted.length - 1; i++) {
      fittest = sorted[i];
      index = i;
      for (int j = i + 1; j < population.length; j++) {
        if (sorted[j].getNormalizedFitness() > fittest.getNormalizedFitness()) {
          fittest = sorted[j];
          index = j;
        }
      }
      swap = sorted[i];
      sorted[i] = fittest;
      sorted[index] = swap;
    }
    
    return sorted;
  }
  
  private Hero crossOver(Hero dad, Hero mom, int mutationRate) {
    Hero bestParent, worstParent, son;
    int[] bestStats, upStats;
    
    if (dad.getFitness() >= mom.getFitness()) {
      bestParent = dad; worstParent = mom;
    } else {
      bestParent = mom; worstParent = dad;
    }
    
    bestStats = bestParent.getBaseStats();
    upStats = worstParent.getBaseStats();
    
    son = new Warrior(bestStats[0] + upStats[0] / 10,
                      bestStats[1] + upStats[1] / 10,
                      bestStats[2] + upStats[2] / 10,
                      bestStats[3] + upStats[3] / 10,
                      bestStats[4] + upStats[4] / 10,
                      bestStats[5] + 1); //only increase speed by 1 each generation
    son.setSkill(bestParent.getSkill());
    if (random.nextInt(101) <= mutationRate) {son.setSkill(worstParent.getSkill());}
    return son;
  }
  
  public Hero[] initiatePopulation(int size) {
    Hero[] population = new Hero[4 * size];
    int index = 0;
    for (int i = 0; i < size; i++) {
      population[index] = new Warrior(500, 50, 50, 50, 50, 55);
      population[index++].setSkill(new TripleHit(10));
      
      population[index] = new Paladin(1500, 20, 100, 55, 40, 50);
      population[index++].setSkill(new Berserk(20));
      
      population[index] = new Mage(450, 150, 30, 20, 70, 70);
      population[index++].setSkill(new Spell(5));
      
      population[index] = new Healer(400, 100, 10, 80, 90, 45);
      population[index++].setSkill(new Heal(30));
    }
    return population;
  }
  
  public boolean evaluateFitness(Hero[] population, Hero boss) {
    for (Hero hero : population) {
      Battle battle = new Battle(hero, boss);
      while (battle.notOver()) {
        battle.turn(random.nextInt(3)); //0 = attack, 1 = guard, 2 = skill
      }
      if (battle.bossDefeated()) {
        winnerStats = hero.getBaseStats();
        bossStats = boss.getBaseStats();
        winnerSkill = hero.getSkill().getName();
        bossSkill = boss.getSkill().getName();
        System.out.println(hero.getHP() + " " + hero.getMP() +  " " + 
        hero.getATK() + " " + hero.getDEF() + " " + hero.getMAG() + " " + hero.getSPD());
        return true;
      } else {
        hero.setFitness(boss.getDamage() + hero.getHealedHP());
      }
      boss.restore();
    }
    return false;
  }
  
  public Hero[] selection(Hero[] population) {
    Hero[] nextGen = new Hero[population.length];
    int fitnessSum = getFitnessSum(population);
    
    for (Hero hero : population) {
      hero.setNormalizedFitness(1.0 * hero.getFitness() / fitnessSum);
    }
    
    nextGen = sortByFitness(population);
    
    for (int j = 1; j < nextGen.length; j++) {
      nextGen[j].setNormalizedFitness(nextGen[j - 1].getNormalizedFitness() + nextGen[j].getNormalizedFitness());
    }
    
    double R = Math.random();
    int cutIndex = 0;
    
    for (int k = 0; k < nextGen.length; k++) {
      if (nextGen[k].getNormalizedFitness() <= R) cutIndex = k;
    }
    
    return Arrays.copyOfRange(nextGen, 0, cutIndex + 1);
  }
  
  public Hero[] reproduction(Hero[] fittest, int size) {
    Hero[] breed = new Hero[size];
    int filled = 0;
    
    while (filled < size) {
      Hero dad = fittest[random.nextInt(fittest.length)];
      Hero mom = fittest[random.nextInt(fittest.length)];
      breed[filled++] = crossOver(dad, mom, 5);
    }
    
    return breed;
  }
  
  public void geneticAlgorithm(int size, Hero boss, Hero[] initialPopulation) {
    Hero[] population;
    if (initialPopulation == null) {population = initiatePopulation(size);}
    else {population = initialPopulation;}
    if (evaluateFitness(population, boss)) {
      return;
    } else {
      Hero[] fittest = selection(population);
      Hero[] breed = reproduction(fittest, size);
      ++this.totalGenerations;
      geneticAlgorithm(size, boss, breed);
    }
  }
  
  public int getTotalGenerations() {
    return totalGenerations;
  }
  
  public static void main(String[] args) {
    RPGBattle gene = new RPGBattle();
    
    /*
    Hero slime = new Boss(random.nextInt((1500 - 1000) + 1) + 1000,
                          random.nextInt((150 - 100) + 1) + 100,
                          random.nextInt((100 - 50) + 1) + 50,
                          random.nextInt((100 - 50) + 1) + 50,
                          random.nextInt((100 - 50) + 1) + 50,
                          30);
    
    Hero dragon = new Boss(random.nextInt((5000 - 3000) + 1) + 3000,
                           random.nextInt((500 - 300) + 1) + 300,
                           random.nextInt((250 - 100) + 1) + 100,
                           random.nextInt((250 - 100) + 1) + 100,
                           random.nextInt((250 - 100) + 1) + 100,
                           55);
    */
    
    Hero chaos = new Boss(random.nextInt((9999 - 7500) + 1) + 7500,
                          random.nextInt((999 - 500) + 1) + 500,
                          random.nextInt((500 - 300) + 1) + 300,
                          random.nextInt((500 - 300) + 1) + 300,
                          random.nextInt((500 - 300) + 1) + 300,
                          75);
    chaos.setSkill(new Doom(40));
    
    long startTime = System.currentTimeMillis();
    gene.geneticAlgorithm(25, chaos, null);
    long stopTime = System.currentTimeMillis();
    
    System.out.println("Elapsed time is: " + (stopTime - startTime));
    System.out.println("Number of generations was: " + gene.getTotalGenerations());
    System.out.println("Our savior's stats were:");
    System.out.println("HP: " + winnerStats[0] + "\t" + "MP: " + winnerStats[1]);
    System.out.println("ATK: " + winnerStats[2] + "\t" + "DEF: " + winnerStats[3]);
    System.out.println("MAG: " + winnerStats[4] + "\t" + "SPD: " + winnerStats[5]);
    System.out.println("Their skill was: " + winnerSkill);
    System.out.println("The villain's stats were:");
    System.out.println("HP: " + bossStats[0] + "\t" + "MP: " + bossStats[1]);
    System.out.println("ATK: " + bossStats[2] + "\t" + "DEF: " + bossStats[3]);
    System.out.println("MAG: " + bossStats[4] + "\t" + "SPD: " + bossStats[5]);
    System.out.println("Their skill was: " + bossSkill);
  }
}