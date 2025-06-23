package com.nutritrack.app.service.insights;

import com.nutritrack.app.dto.SleepInsightsDTO;
import com.nutritrack.app.dto.sleep.SleepNumeric;
import com.nutritrack.app.dto.sleep.SleepText;
import com.nutritrack.app.entity.NutriLabel;
import com.nutritrack.app.service.NutriLabelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SleepInsightsService {
    private NutriLabelService nutriLabelService;

    public SleepInsightsService(NutriLabelService nutriLabelService) {
        this.nutriLabelService = nutriLabelService;
    }

    public SleepInsightsDTO getSleepInsights(String username) {
        SleepInsightsDTO sleepInsights = new SleepInsightsDTO();
        List<NutriLabel> nutriLabels = nutriLabelService.getAllNutriLabels(username);
        double totalAddedSugars = 0;
        double totalSodium = 0;
        double totalDietaryFiber = 0;
        double totalProtein = 0;

        for (NutriLabel nutriLabel : nutriLabels) {
            totalAddedSugars += nutriLabel.getAddedSugars();
            totalSodium += nutriLabel.getSodium();
            totalDietaryFiber += nutriLabel.getDietaryFiber();
            totalProtein += nutriLabel.getProtein();
        }

        double nutriLabelSize = nutriLabels.size();
        double averageAddedSugars = totalAddedSugars / nutriLabelSize;
        double averageSodium = totalSodium / nutriLabelSize;
        double averageDietaryFiber = totalDietaryFiber / nutriLabelSize;
        double averageProtein = totalProtein / nutriLabelSize;

        SleepText textInsights = new SleepText();
        textInsights.setSugarIntake(sugarInsights(averageAddedSugars));
        textInsights.setSodiumIntake(sodiumInsights(averageSodium));
        textInsights.setFiberIntake(dietaryFiberInsights(averageDietaryFiber));
        textInsights.setProteinIntake(proteinInsights(averageProtein));

        SleepNumeric numericInsights = new SleepNumeric();
        numericInsights.setAverageAddedSugars(averageAddedSugars);
        numericInsights.setAverageSodium(averageSodium);
        numericInsights.setAverageDietaryFiber(averageDietaryFiber);
        numericInsights.setAverageProtein(averageProtein);

        sleepInsights.setSleepText(textInsights);
        sleepInsights.setSleepNumeric(numericInsights);
        return sleepInsights;
    }

    private String sugarInsights(double averageAddedSugars) {
        String result = "The average added sugars is " + averageAddedSugars + " grams.\n";
        if(averageAddedSugars > 15) {
            return result + "This is higher than you would want and research links high sugar consumption to a higher frequency of waking up at night.";
        } else if(averageAddedSugars > 5) {
            return result + "This is about average for a given meal. Consider slightly reducing added sugar later in the day to help with sleep.";
        } else {
            return result + "You're doing a great job at limiting products with high added sugars. Make sure to keep doing so.";
        }
    }

    private String sodiumInsights(double averageAddedSodium) {
        String result = "The average sodium is " + averageAddedSodium + " grams.\n";
        if(averageAddedSodium > 800) {
            return result + "Your sodium intake is high. High sodium is linked to increased nighttime urination and reduced deep sleep due to elevated blood pressure. Consider lighter, low-salt evening meals to minimize sleep disruptions.";
        } else if(averageAddedSodium > 500) {
            return result + "Your sodium levels are moderate. While within a healthy range, avoiding sodium at dinner time helps reduce overnight water retention and supports steady cardiovascular rhythms during sleep.";
        } else {
            return result + "Your sodium intake per meal is low. This supports the body’s natural nighttime drop in blood pressure, which is essential for deep, uninterrupted sleep.";
        }
    }

    private String dietaryFiberInsights(double averageDietaryFiber) {
        String result = "The average dietary fiber is " + averageDietaryFiber + " grams.\n";
        if(averageDietaryFiber < 5) {
            return result + "Your meals are low in dietary fiber. Low fiber is linked to reduced time in slow-wave sleep, which is the stage your brain uses for memory consolidation and physical restoration.";
        } else if(averageDietaryFiber < 8) {
            return result + "You’re getting a fair amount of fiber. Research shows moderate fiber intake helps prevent blood sugar crashes at night, supporting more continuous sleep.";
        } else {
            return result + "Excellent, your fiber intake is high. Higher fiber diets are linked to more time spent in deep sleep and shorter time to fall asleep, helping your body and brain recover overnight.";
        }
    }

    private String proteinInsights(double averageProtein) {
        String result = "The average protein is " + averageProtein + " grams.\n";
        if(averageProtein < 10) {
            return result + "Your protein intake is on the lower side. Low protein can limit the production of serotonin and melatonin, which may make it harder to fall asleep or stay asleep through the night.";
        } else if(averageProtein < 25) {
            return result + "Your protein intake per meal is in a healthy range. Moderate protein supports stable blood sugar and promotes melatonin production, which can help you fall asleep more easily and sleep more deeply.";
        } else {
            return result + "This meal is high in protein. While protein is essential, very high amounts, especially close to bedtime, can increase alertness and reduce REM sleep";
        }

    }
}
