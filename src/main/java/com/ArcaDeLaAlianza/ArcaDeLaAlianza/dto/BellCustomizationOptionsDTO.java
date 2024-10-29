package com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellAlloy;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellFinish;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellWeightSize;
import lombok.Getter;


import java.util.List;


@Getter
public class BellCustomizationOptionsDTO {
    private final List<BellAlloy> alloys;
    private final List<BellWeightSize> WeightSizes;
    private final List<BellFinish> finishes;

    public BellCustomizationOptionsDTO(List<BellFinish> finishes, List<BellAlloy> alloys, List<BellWeightSize> weightSizes) {
        this.alloys = alloys;
        this.WeightSizes = weightSizes;
        this.finishes = finishes;
    }

}
