package org.jgrades.lic.app.service.crypto.encrypt;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class LicenceSaverTest {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private byte[] licenceBytes;
    private byte[] signatureBytes;

    @Before
    public void setUp() throws Exception {
        Resource licResource = new ClassPathResource("encrypted.lic");
        licenceBytes = FileUtils.readFileToByteArray(licResource.getFile());

        Resource signResource = new ClassPathResource("encrypted.lic.sign");
        signatureBytes = FileUtils.readFileToByteArray(signResource.getFile());
    }

    @Test
    public void shouldSaveEncryptedLicenceAndSignatureWithProperExtension() throws Exception {
        // given
        File newFile = tempFolder.newFile("lic.dat");
        File newSignatureFile = tempFolder.newFile("lic.dat.sign");
        LicenceSaver licenceSaver = new LicenceSaver(newFile.getPath());

        // when
        licenceSaver.saveLicence(licenceBytes);

        // then
        assertThat(newFile).exists();
        assertThat(FileUtils.readFileToByteArray(newFile)).isEqualTo(licenceBytes);

        // when
        licenceSaver.saveSignature(signatureBytes);

        // then
        assertThat(newSignatureFile).exists();
        assertThat(FileUtils.readFileToByteArray(newSignatureFile)).isEqualTo(signatureBytes);
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailed_whenNullPassed() throws Exception {
        // when
        LicenceSaver licenceSaver = new LicenceSaver(null);

        // then
        // should throw NullPointerException

    }
}
