package org.jgrades.lic.app.cli;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleApplicationTest {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
    private ConsoleApplication consoleApplication;

    @Before
    public void setUp() throws Exception {
        consoleApplication = new ConsoleApplication();
        assertThat(consoleApplication).isNotNull();
    }

    @Test
    public void shouldGetNewLine() throws Exception {
        // given
        Scanner scanner = new Scanner("qwerty\n");
        ReflectionTestUtils.setField(consoleApplication, "scanner", scanner);

        // when
        String line = consoleApplication.getLine("desc");

        // when
        assertThat(line).isEqualTo("qwerty");
    }

    @Test
    public void shouldExit_whenThirdActionChosen() throws Exception {
        exit.expectSystemExitWithStatus(0);

        // given
        Scanner scanner = new Scanner("3\n");
        ReflectionTestUtils.setField(consoleApplication, "scanner", scanner);

        // when
        consoleApplication.runApp();
    }

    @Test
    public void shouldRetry_whenUnsupportedActionInvoked() throws Exception {
        exit.expectSystemExitWithStatus(0);

        // given
        Scanner scanner = new Scanner("4\n\n3\n\n");
        ReflectionTestUtils.setField(consoleApplication, "scanner", scanner);

        // when
        consoleApplication.runApp();
    }
}
