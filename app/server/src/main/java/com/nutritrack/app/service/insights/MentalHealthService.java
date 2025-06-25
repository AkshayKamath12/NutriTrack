package com.nutritrack.app.service.insights;

import com.nutritrack.app.dto.MentalHealthInsightsDTO;
import com.nutritrack.app.dto.mentalHealth.MentalHealthNumeric;
import com.nutritrack.app.dto.mentalHealth.MentalHealthScores;
import com.nutritrack.app.dto.mentalHealth.MentalHealthText;
import com.nutritrack.app.entity.Meal;
import com.nutritrack.app.entity.NutriLabel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentalHealthService {
    public MentalHealthInsightsDTO getMentalHealthInsights(Meal meal) {
        MentalHealthInsightsDTO mentalHealthInsights = new MentalHealthInsightsDTO();
        List<NutriLabel> nutriLabelList = meal.getNutritionLabels();

        double totalFat = 0;
        double totalDietaryFibers = 0;
        double totalProtein = 0;
        double totalAddedSugars = 0;
        double totalCarbohydrates = 0;
        double totalSodium = 0;

        for(NutriLabel nutriLabel : nutriLabelList) {
            totalFat += nutriLabel.getTotalFat();
            totalDietaryFibers += nutriLabel.getDietaryFiber();
            totalProtein += nutriLabel.getProtein();
            totalAddedSugars += nutriLabel.getAddedSugars();
            totalCarbohydrates += nutriLabel.getTotalCarbohydrates();
            totalSodium += nutriLabel.getSodium();
        }

        MentalHealthNumeric mentalHealthNumeric = new MentalHealthNumeric();
        MentalHealthText mentalHealthText = new MentalHealthText();
        MentalHealthScores mentalHealthScores = new MentalHealthScores();

        //handle omega imbalance
        boolean omegaImbalance = totalFat > 15 && totalDietaryFibers < 3 && totalProtein < 10;
        mentalHealthNumeric.setOmegaImbalance(omegaImbalance);
        mentalHealthText.setOmegaImbalanceInsight(omegaImbalanceInsight(omegaImbalance, mentalHealthScores));

        //handle total sugar
        mentalHealthNumeric.setTotalAddedSugar(totalAddedSugars);
        mentalHealthText.setTotalAddedSugarInsight(totalAddedSugarsInsight(totalAddedSugars, mentalHealthScores));

        //handle total dietary fiber
        mentalHealthNumeric.setTotalDietaryFiber(totalDietaryFibers);
        mentalHealthText.setTotalDietaryFiberInsight(totalDietaryFibersInsight(totalDietaryFibers, mentalHealthScores));

        //handle tryptophan proxy
        boolean tryptophanProxy = totalProtein < 5 && totalCarbohydrates > 40;
        mentalHealthNumeric.setTryptophanProxy(tryptophanProxy);
        mentalHealthText.setTryptophanProxyInsight(tryptophanProxyInsight(tryptophanProxy, mentalHealthScores));

        //handle sodium hydration balance
        boolean sodiumHydrationBalance = totalSodium > 800 && totalDietaryFibers < 2;
        mentalHealthNumeric.setSodiumHydrationBalance(sodiumHydrationBalance);
        mentalHealthText.setSodiumHydrationBalanceInsight(sodiumHydrationBalanceInsight(sodiumHydrationBalance, mentalHealthScores));

        //handle magnesium proxy
        boolean magnesiumProxy = totalDietaryFibers < 3 && totalProtein < 10 && totalFat > 10 && totalAddedSugars > 10;
        mentalHealthNumeric.setMagnesiumProxy(magnesiumProxy);
        mentalHealthText.setMagnesiumProxyInsight(magnesiumProxyInsight(magnesiumProxy, mentalHealthScores));

        mentalHealthInsights.setMentalHealthNumeric(mentalHealthNumeric);
        mentalHealthInsights.setMentalHealthText(mentalHealthText);
        mentalHealthInsights.setMentalHealthScores(mentalHealthScores);

        return mentalHealthInsights;
    }

    private String omegaImbalanceInsight(boolean omegaImbalance, MentalHealthScores mentalHealthScores) {
        if(omegaImbalance) {
            mentalHealthScores.setOmegaImbalanceScore(1);
            return "This meal appears high in fat but low in fiber and protein, a pattern associated with an excessive omega-6 to omega-3 ratio. Diets with this imbalance are linked to greater neuroinflammation, which disrupts dopamine and serotonin signaling in the brain. This may increase the risk of mood instability and anxiety symptoms.";
        } else {
            mentalHealthScores.setOmegaImbalanceScore(10);
            return "This meal has a balanced fat profile, supported by fiber and/or protein, a positive sign for reducing neuroinflammation. Balanced fat-to-fiber/protein meals help maintain the optimal omega-6 to omega-3 ratio, which is important for cognitive resilience and emotional stability.";
        }
    }

    private String totalAddedSugarsInsight(double totalSugars, MentalHealthScores mentalHealthScores) {
        String result = "The total added sugars is " + totalSugars + ".\n";
        if(totalSugars < 5) {
            mentalHealthScores.setTotalAddedSugarScore(10);
            return result + "This meal is low in added sugar, which is optimal for mental health. Low-sugar diets are associated with lower rates of depression, improved emotional regulation, and more stable cognitive performance throughout the day.";
        } else if (totalSugars < 10) {
            mentalHealthScores.setTotalAddedSugarScore(7);
            return result + "This meal contains a moderate amount of added sugar. While not excessive on its own, regular consumption of sugar in this range may impair brain-derived neurotrophic factor (BDNF), a protein linked to learning and mood resilience.";
        } else if (totalSugars < 15) {
            mentalHealthScores.setTotalAddedSugarScore(3);
            return result + "This meal is relatively high in added sugar. Levels like this have been linked to decreased hippocampal function, higher inflammation, and increased risk of depressive episodes, especially when not balanced by fiber or protein.";
        } else {
            mentalHealthScores.setTotalAddedSugarScore(1);
            return result + "This meal is very high in added sugar. Meals with this sugar load are associated with significantly increased risk of mood swings, anxiety, and depression, particularly in individuals with already low serotonin turnover.";
        }
    }

    private String totalDietaryFibersInsight(double totalDietaryFibers, MentalHealthScores mentalHealthScores) {
        String result = "The total dietary fibers is " + totalDietaryFibers + ".\n";
        if(totalDietaryFibers < 2) {
            mentalHealthScores.setTotalDietaryFiberScore(1);
            return result + "This meal is very low in fiber. Diets lacking fiber reduce the production of short-chain fatty acids (SCFAs) like butyrate in the gut, which are critical for regulating mood via the gut-brain axis. Low fiber has been associated with increased risk of anxiety and poor stress tolerance.";
        } else if(totalDietaryFibers < 6) {
            mentalHealthScores.setTotalDietaryFiberScore(5);
            return result + "This meal provides a moderate amount of fiber. While better than low-fiber meals, higher levels may further support beneficial gut microbiota that produce mood-enhancing neurotransmitters.";
        } else {
            mentalHealthScores.setTotalDietaryFiberScore(10);
            return result + "This meal is rich in fiber, a strong protective factor for mental health. Fiber-rich diets support gut diversity, reduce systemic inflammation, and boost production of GABA and serotonin through microbial fermentation.";
        }
    }

    private String tryptophanProxyInsight(boolean tryptophanProxy, MentalHealthScores mentalHealthScores) {
        if(tryptophanProxy) {
            mentalHealthScores.setTryptophanProxyScore(1);
            return "This meal may disrupt brain tryptophan transport due to its low protein and high carbohydrate profile. Meals like this can increase blood levels of competing amino acids, reducing serotonin synthesis in the brain and worsening mood stability over time.";
        } else {
            mentalHealthScores.setTryptophanProxyScore(10);
            return "This meal is rich in protein, which likely supports tryptophan availability, a key precursor for serotonin. This profile is associated with improved mood regulation, better impulse control, and reduced depressive symptoms.";
        }
    }

    private String sodiumHydrationBalanceInsight(boolean hydrationBalance, MentalHealthScores mentalHealthScores) {
        if(hydrationBalance) {
            mentalHealthScores.setSodiumHydrationBalanceScore(1);
            return "This meal is high in sodium and low in fiber, a combination linked to higher baseline cortisol levels and impaired vagal tone, both of which worsen resilience to stress and anxiety. Over time, this pattern contributes to elevated blood pressure and irritability.";
        } else {
            mentalHealthScores.setSodiumHydrationBalanceScore(10);
            return "This meal avoids the high sodium–low fiber combo. This helps regulate blood pressure and cortisol sensitivity, contributing to better emotional regulation and reduced anxiety symptoms.";
        }
    }

    private String magnesiumProxyInsight(boolean magnesiumProxy, MentalHealthScores mentalHealthScores) {
        if(magnesiumProxy) {
            mentalHealthScores.setMagnesiumProxyScore(1);
            return "This meal profile may indicate low magnesium content, a mineral essential for calming the nervous system. Diets low in magnesium are linked to elevated risk of anxiety, insomnia, and even panic disorders due to poor GABA receptor sensitivity.";
        } else {
            mentalHealthScores.setMagnesiumProxyScore(10);
            return "This meal likely contains nutrients that support magnesium availability which plays a key role in regulating mood, stress response, and sleep quality";
        }
    }
}
