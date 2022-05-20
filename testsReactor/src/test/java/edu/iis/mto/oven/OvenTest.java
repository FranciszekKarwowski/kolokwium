package edu.iis.mto.oven;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OvenTest {
    @Mock
    private HeatingModule heatingModule;

    @Mock
    private Fan fan;

    private Oven oven;

    private ProgramStage programStage;
    private List<ProgramStage> stages;
    private BakingProgram bakingProgram;

    @BeforeEach
    void setUp() throws Exception {
        oven = new Oven(heatingModule, fan);
        programStage = ProgramStage.builder().withTargetTemp(200).withStageTime(10).withHeat(HeatType.THERMO_CIRCULATION).build();
        stages = new LinkedList<>();
        stages.add(programStage);
        bakingProgram = BakingProgram.builder().withInitialTemp(20).withStages(stages).withCoolAtFinish(true).build();
    }
    
    @Test
    void itCompiles() {
        assertThat(true, equalTo(true));
    }

    @Test
    void checkIfFanIsOnWhileCooling() {
        oven.runProgram(bakingProgram);
    }

}
