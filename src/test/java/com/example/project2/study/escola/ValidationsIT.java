package com.example.project2.study.escola;

import com.example.project2.study.AbstractIntegrationIT;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class ValidationsIT extends AbstractIntegrationIT {

    @Test
    public void testEquals() {
        String nula = null;
        String vazia = "";
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(nula).isNull();
            s.assertThat(vazia.isBlank());
            s.assertThat(vazia.isEmpty());
            s.assertThat(vazia).isNotNull();
        });
    }
}
