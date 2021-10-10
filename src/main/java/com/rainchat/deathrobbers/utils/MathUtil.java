package com.rainchat.deathrobbers.utils;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class MathUtil {

    private static final Random random = new Random();

    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean calculateChance(double chance) {
        int maxBound = 100000;
        chance *= 100000;
        return random.nextInt(maxBound) < chance;
    }

    public static int getRandomNumber(String number) {

        String[] string = number.trim().split(",");
        if (string.length == 1) {
            return Integer.parseInt(number);
        }
        return getRandomNumber(Integer.parseInt(string[0]), Integer.parseInt(string[1]));
    }

    public static double getRandomDouble(String number) {
        String[] string = number.trim().split(",");
        if (string.length == 1) {
            return Double.parseDouble(number);
        }
        double rangeMin = Double.parseDouble(string[0]);
        double rangeMax = Double.parseDouble(string[1]);

        return rangeMin + (rangeMax - rangeMin) * random.nextDouble();
    }

    public static long getRandomLong(String number) {
        String[] string = number.trim().split(",");
        if (string.length == 1) {
            return Long.parseLong(number);
        }
        long rangeMin = Long.parseLong(string[0]);
        long rangeMax = Long.parseLong(string[1]);

        return rangeMin + (rangeMax - rangeMin) * random.nextLong();
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static Player pickRandomPlayer(List<Player> playerList) {
        return playerList.get(random.nextInt(playerList.size()));
    }

    public static Long roundAvoid(long value, int places) {
        long scale = (long) Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

}
