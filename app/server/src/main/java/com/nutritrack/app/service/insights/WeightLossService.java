package com.nutritrack.app.service.insights;

import com.nutritrack.app.dto.WeightInsightsDTO;
import com.nutritrack.app.dto.weight.WeightNumeric;
import com.nutritrack.app.dto.weight.WeightScores;
import com.nutritrack.app.dto.weight.WeightText;
import com.nutritrack.app.entity.Meal;
import com.nutritrack.app.entity.NutriLabel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightLossService {
    public WeightInsightsDTO getWeightLossInsights(Meal meal) {
        WeightInsightsDTO weightInsightsDTO = new WeightInsightsDTO();
        List<NutriLabel> nutriLabelList = meal.getNutritionLabels();
        double totalCalories = 0;
        double totalAddedSugars = 0;
        double totalSodium = 0;
        double totalCarbohydrates = 0;
        double totalProtein = 0;
        double totalDietaryFiber = 0;
        double totalFat = 0;

        for(NutriLabel nutriLabel : nutriLabelList) {
            totalCalories += nutriLabel.getCalories();
            totalAddedSugars += nutriLabel.getAddedSugars();
            totalSodium += nutriLabel.getSodium();
            totalCarbohydrates += nutriLabel.getTotalCarbohydrates();
            totalProtein += nutriLabel.getProtein();
            totalDietaryFiber += nutriLabel.getDietaryFiber();
            totalFat += nutriLabel.getTotalFat();
        }

        WeightNumeric weightNumeric = new WeightNumeric();
        WeightText weightText = new WeightText();
        WeightScores weightScores = new WeightScores();

        //handle total calories
        weightNumeric.setTotalCalories(totalCalories);
        weightText.setTotalCaloriesInsight(totalCaloriesInsight(totalCalories, weightScores));

        //handle average calories
        double averageCalories = totalCalories / nutriLabelList.size();
        weightNumeric.setAverageCalories(averageCalories);
        weightText.setAverageCaloriesInsight(averageCaloriesInsight(averageCalories, weightScores));

        //handle total added sugars
        weightNumeric.setTotalAddedSugars(totalAddedSugars);
        weightText.setTotalAddedSugarsInsight(totalAddedSugarsInsight(totalAddedSugars, weightScores));

        //handle sodium carb limit
        boolean exceedSodiumCarbLimit = totalSodium > 800 && totalCarbohydrates > 50;
        weightNumeric.setExceedSodiumCarbLimit(exceedSodiumCarbLimit);
        weightText.setExceedSodiumCarbLimitInsight(sodiumCarbohydrateLimitInsight(exceedSodiumCarbLimit, weightScores));

        //handle satiety score
        double satietyScore = totalDietaryFiber * 2 + totalProtein * 1.5 - 0.5 * totalAddedSugars - 0.2 * totalFat;
        weightNumeric.setSatietyScore(satietyScore);
        weightText.setSatietyScoreInsight(satietyScoreInsight(satietyScore, weightScores));

        weightInsightsDTO.setWeightNumeric(weightNumeric);
        weightInsightsDTO.setWeightText(weightText);
        weightInsightsDTO.setWeightScores(weightScores);

        return weightInsightsDTO;
    }

    private String totalCaloriesInsight(double totalCalories, WeightScores weightScores) {
        String result = "The total calories for your meal is " + totalCalories + ".\n";
        if (totalCalories < 300) {
            weightScores.setTotalCaloriesScores(5);
            return result + "This meal is very low in energy. While low-calorie meals can aid weight loss, they may leave you hungry, which can lead to overeating later. Research shows that overly restrictive calorie intake is often linked to poorer long-term success due to compensatory snacking or binge-eating.";
        } else if (totalCalories < 550) {
            weightScores.setTotalCaloriesScores(10);
            return result + "This meal falls into the optimal range for sustainable weight loss. Studies show that meals in the 400–550 kcal range, especially when high in protein and fiber, promote fat loss while preserving lean mass and avoiding rebound hunger.";
        } else if (totalCalories < 750) {
            weightScores.setTotalCaloriesScores(5);
            return result + "This meal may be slightly high for weight loss. Infrequent larger meals can fit into a calorie-controlled plan, but repeated meals in this range tend to push people into maintenance or slow weight loss unless offset by activity.";
        } else {
            weightScores.setTotalCaloriesScores(1);
            return result + "This meal is high in calories relative to weight loss targets. Research shows that consistently consuming meals over 700 kcal correlates with poor weight loss outcomes unless total daily intake is tightly controlled. Late-day consumption of large meals is particularly associated with fat gain.";
        }
    }

    private String averageCaloriesInsight(double averageCalories, WeightScores weightScores) {
        String result = "The average calories for your meal is " + averageCalories + ".\n";
        if (averageCalories < 300) {
            weightScores.setAverageCaloriesScores(5);
            return result + "Your average meal is very low in calories. While this might seem good for weight loss, it may reflect under-eating, skipping meals, or relying on calorie-dense snacks between meals. Such patterns are linked to metabolic slowdown, muscle loss, and binge-eating.";
        } else if (averageCalories < 550) {
            weightScores.setAverageCaloriesScores(10);
            return result +  "Your average meal is well-balanced for weight loss. Studies show that meals in the 300–550 kcal range improve satiety and nutrient absorption while supporting a healthy calorie deficit. This range is also associated with lower post-meal insulin and glucose spikes.";
        } else if (averageCalories < 750) {
            weightScores.setAverageCaloriesScores(5);
            return result + "Your average meal is slightly higher than optimal for weight loss. This may work if you're active or eating fewer than three meals per day, but for most people, meals in this range make it harder to maintain a calorie deficit unless carefully planned.";
        } else {
            weightScores.setAverageCaloriesScores(1);
            return result + "Your average meal is high in calories for someone aiming to lose weight. Repeated meals above 700 kcal are strongly linked to higher daily intake, increased fat storage, and reduced dietary compliance. The effect is more pronounced in sedentary individuals or when eating late in the day.";
        }
    }

    private String totalAddedSugarsInsight(double totalAddedSugars, WeightScores weightScores) {
        String result = "The total added sugars for your meal is " + totalAddedSugars + ".\n";
        if (totalAddedSugars < 5) {
            weightScores.setTotalAddedSugarsScores(10);
            return result + "This meal is low in added sugar which is great for supporting weight loss, stable energy levels, and hormonal balance. Meals under 5g of added sugar are linked to reduced visceral fat, lower triglycerides, and better insulin sensitivity.";
        } else if (totalAddedSugars < 10) {
            weightScores.setTotalAddedSugarsScores(7);
            return result + "This meal has a moderate amount of added sugar. While this won’t derail progress alone, multiple meals in this range can quietly push you over daily limits and impair fat metabolism.";
        } else if (totalAddedSugars < 15) {
            weightScores.setTotalAddedSugarsScores(5);
            return result + "This meal exceeds the ideal per-meal added sugar target. Meals with this much added sugar are often linked to elevated blood sugar, insulin spikes, and increased cravings, making sustained weight loss harder.";
        } else {
            weightScores.setTotalAddedSugarsScores(1);
            return result + "This meal is high in added sugar. Consistently consuming meals like this increases fat storage via insulin, worsens appetite regulation (via ghrelin/leptin), and is linked to stubborn weight around the midsection.";
        }
    }

    private String sodiumCarbohydrateLimitInsight(boolean sodiumCarbohydrateLimit, WeightScores weightScores) {
        if(sodiumCarbohydrateLimit) {
            weightScores.setExceedSodiumCarbLimitScores(1);
            return "This meal is high in both sodium (≥800mg) and carbohydrates (≥50g). Meals with this profile are strongly linked to increased water retention, post-meal blood sugar spikes, and abdominal fat accumulation. This combo also increases dopamine-driven cravings, making it harder to maintain a calorie deficit.";
        } else {
            weightScores.setExceedSodiumCarbLimitScores(10);
            return "This meal avoids the high sodium + high carbohydrate combination, which is great for supporting metabolic balance and reducing bloating. Avoiding this combo helps regulate insulin, curb cravings, and maintain steady energy.";
        }
    }

    private String satietyScoreInsight(double satietyScore, WeightScores weightScores) {
        String result = "The satiety score for this meal is " + satietyScore + ".\n";
        if (satietyScore < 10) {
            weightScores.setSatietyScoreScores(5);
            return result + "This meal scores low on satiety. It’s likely to leave you hungry again within 1–2 hours. Meals with low fiber and protein but high sugar or fat tend to spike insulin and encourage rebound cravings, a key barrier to weight loss.";
        } else if (satietyScore < 20) {
            weightScores.setSatietyScoreScores(10);
            return result + "This meal provides some satiety but might not fully curb hunger. Meals in this range often lack enough fiber or protein to delay hunger signals for long.";
        } else {
            weightScores.setSatietyScoreScores(5);
            return result + "This meal scores high on satiety. It’s rich in fiber and/or protein, which slows digestion and supports fullness hormones like GLP-1 and PYY. Meals like this are linked to reduced calorie intake at the next meal and better adherence to weight loss plans.";
        }
    }
}

