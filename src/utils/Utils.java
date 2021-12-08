package utils;

import common.Constants;
import entities.Child;
import entities.Gift;
import entities.Simulation;
import enums.Category;
import enums.Cities;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Utils {
    private Utils() {

    }

    /**
     * Converts a string to a Category object
     * @param categoryObject string to be converted
     * @return Category object
     */
    public static Category stringToCategory(final String categoryObject) {
        return switch (categoryObject) {
            case "Board Games" -> Category.BOARD_GAMES;
            case "Books" -> Category.BOOKS;
            case "Clothes" -> Category.CLOTHES;
            case "Sweets" -> Category.SWEETS;
            case "Technology" -> Category.TECHNOLOGY;
            case "Toys" -> Category.TOYS;
            default -> null;
        };
    }

    /**
     * Transform a JSONArray in a category string array
     * @param array JSONArray to be parsed
     * @return the list of categories
     */
    public static List<Category> arrayToCategory(final JSONArray array) {
        List<Category> categories = new ArrayList<>();

        if (array != null) {
            for (Object object : array) {
                categories.add(stringToCategory((String) object));
            }
            return categories;
        } else {
            return null;
        }
    }

    /**
     * Converts a string to a City object
     * @param city String to be converted
     * @return City enum object
     */
    public static Cities stringToCities(final String city) {
        return switch (city) {
            case "Bucuresti" -> Cities.BUCURESTI;
            case "Constanta" -> Cities.CONSTANTA;
            case "Buzau" -> Cities.BUZAU;
            case "Timisoara" -> Cities.TIMISOARA;
            case "Cluj-Napoca" -> Cities.CLUJ;
            case "Iasi" -> Cities.IASI;
            case "Craiova" -> Cities.CRAIOVA;
            case "Brasov" -> Cities.BRASOV;
            case "Braila" -> Cities.BRAILA;
            case "Oradea" -> Cities.ORADEA;
            default -> null;
        };
    }

    /**
     * Computes the average score for a child
     * @param child The child which we need to compute the average for
     * @return average score
     */
    public static Double calculateAverage(final Child child) {
        if (child.getAge() < Constants.FIVE) {
            return Constants.TEN;
        } else if (child.getAge() < Constants.TWELVE) {
            Double sum = 0.0;
            Double cnt = 0.0;
            for (Double d : child.getNiceScore()) {
                sum += d;
                cnt++;
            }
            return sum / cnt;
        } else {
            Double weight = 0.0;
            Double sum = 0.0;
            for (int i = 0; i < child.getNiceScore().size(); i++) {
                weight += (i + 1);
                sum += (i + 1) * child.getNiceScore().get(i);
            }
            return sum / weight;
        }
    }

    /**
     * Simulates a round based on the data stored in simulation
     * @param simulation current round data data
     * @return list of children information to be written in the JSON output
     */
    public static List<Map<String, Object>> simulateRound(final Simulation simulation) {
        List<Map<String, Object>> currentRound = new ArrayList<>();
        Double averageSum = 0.0;
        for (Child child : simulation.getChildren()) {
            Double averageScore = Utils.calculateAverage(child);
            averageSum += averageScore;
        }
        Double budgetUnit = simulation.getBudget() / averageSum;
        for (Child child : simulation.getChildren()) {
            Double averageScore = Utils.calculateAverage(child);
            Double childBudget = budgetUnit * averageScore;
            List<Gift> receivedGifts = new ArrayList<>();
            Double prices = 0.0;
            for (Category preference : child.getGiftPreferences()) {
                Double minPrice = Double.MAX_VALUE;
                Gift crtGift = null;
                for (Gift gift : simulation.getGifts()) {
                    if (prices >= childBudget) {
                        break;
                    }
                    if (gift.getCategory().equals(preference)) {
                        if (gift.getPrice() < minPrice
                                && gift.getPrice() + prices <= childBudget) {
                            minPrice = gift.getPrice();
                            crtGift = gift;
                        }
                    }
                }
                if (crtGift != null) {
                    receivedGifts.add(crtGift);
                    prices += crtGift.getPrice();
                }
            }
            Map<String, Object> currentChild = new LinkedHashMap<>();
            currentChild.put(Constants.ID, child.getId());
            currentChild.put(Constants.LAST_NAME, child.getLastName());
            currentChild.put(Constants.FIRST_NAME, child.getFirstName());
            currentChild.put(Constants.CITY, child.getCity());
            currentChild.put(Constants.AGE, child.getAge());
            List<Category> copyGiftPreference = new ArrayList<>(child.getGiftPreferences());
            currentChild.put(Constants.GIFTS_PREFERENCES, copyGiftPreference);
            currentChild.put(Constants.AVERAGE_SCORE, averageScore);
            List<Double> copyNiceScore = new ArrayList<>(child.getNiceScore());
            currentChild.put(Constants.NICE_SCORE_HISTORY, copyNiceScore);
            currentChild.put(Constants.ASSIGNED_BUDGET, childBudget);
            currentChild.put(Constants.RECEIVED_GIFTS, receivedGifts);
            currentRound.add(currentChild);
        }
        return currentRound;
    }
}
