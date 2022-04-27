package com.backend.coding.challenge.sevice.text;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class StringSimilarityService {
    private final LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

    public double scoreSimilarity(String left, String right) {

        double maxLength = Double.max(left.length(), right.length());

        if (maxLength > 0) {
            Integer distance = levenshteinDistance.apply(left.toLowerCase(), right.toLowerCase());
            double score = (maxLength - distance) / maxLength;
            return BigDecimal.valueOf(score)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }

        return 1.0;
    }

}
