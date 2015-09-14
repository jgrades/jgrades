package org.jgrades.logging.utils;

import org.jgrades.logging.dao.LoggingConfigurationDao;
import org.jgrades.logging.model.LoggingConfiguration;
import org.joda.time.LocalDate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OldLogFileFilterTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Mock
    private LoggingConfigurationDao loggingConfigurationDao;

    @InjectMocks
    private OldLogFileFilter oldLogFileFilter;

    @Test
    public void shouldSelectFile_whenFileNameHasObsoleteDate() throws Exception {
        // given
        Integer maxDays = 7;
        LoggingConfiguration configuration = new LoggingConfiguration();
        configuration.setMaxDays(maxDays);
        when(loggingConfigurationDao.getCurrentConfiguration()).thenReturn(configuration);

        LocalDate actualDate = LocalDate.now();
        String okDate = actualDate.minusDays(maxDays - 1).toString("yyyy-MM-dd");
        String nokDate = actualDate.minusDays(maxDays).toString("yyyy-MM-dd");
        String nokDate2 = actualDate.minusDays(maxDays + 1).toString("yyyy-MM-dd");

        String fileName1 = "jg_monitor_" + okDate + "_0.log";
        String fileName2 = "jg_monitor_" + nokDate + "_0.log";
        String fileName3 = "jg_monitor_" + nokDate2 + "_0.log";


        // when
        boolean isTakenToRemoving1 = oldLogFileFilter.accept(temporaryFolder.newFolder(), fileName1);
        boolean isTakenToRemoving2 = oldLogFileFilter.accept(temporaryFolder.newFolder(), fileName2);
        boolean isTakenToRemoving3 = oldLogFileFilter.accept(temporaryFolder.newFolder(), fileName3);

        // then
        assertThat(isTakenToRemoving1).isFalse();
        assertThat(isTakenToRemoving2).isTrue();
        assertThat(isTakenToRemoving3).isTrue();
    }
}