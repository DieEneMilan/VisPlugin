package com.foxdev.visplugin;

import java.util.Random;

public class FishingManager {

    private static double LARGE_FISH_MIN_WEIGHT = 3.0;
    private static int LARGE_FISH_CHANGE = 10;
    private static int CRATE_KEY_CHANGE = 50;
    private Random random;

    public FishingManager(){
        random = new Random();
    }

    public Fish generateFish(){
        double weight = (random.nextDouble() * 9.8) + 0.1;
        if(random.nextInt(LARGE_FISH_CHANGE) != 0){
            weight = Math.min(weight, LARGE_FISH_MIN_WEIGHT);
        }
        return new Fish(weight);
    }

    public boolean tryGenerateCrateKey(){
        return random.nextInt(CRATE_KEY_CHANGE) == 0;
    }
    public int calculateFishValue(Fish fish){
        return (int) (fish.getWeight() * 100);
    }
}
