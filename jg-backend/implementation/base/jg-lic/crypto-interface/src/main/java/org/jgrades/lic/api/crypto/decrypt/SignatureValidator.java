/*
 * Copyright (C) 2016 the original author or authors.
 *
 * This file is part of jGrades Application Project.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jgrades.lic.api.crypto.decrypt;

import org.apache.commons.io.FileUtils;
import org.jgrades.lic.api.crypto.exception.LicenceCryptographyException;
import org.jgrades.logging.JgLogger;
import org.jgrades.logging.JgLoggerFactory;
import org.jgrades.security.utils.KeyStoreContentExtractor;

import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.cert.X509Certificate;

import static org.jgrades.security.utils.CryptoDataConstants.SIGNATURE_PROVIDER_INTERFACE;

class SignatureValidator {
    private static final JgLogger LOGGER = JgLoggerFactory.getLogger(SignatureValidator.class);

    private final KeyStoreContentExtractor keyExtractor;

    public SignatureValidator(KeyStoreContentExtractor keyExtractor) {
        this.keyExtractor = keyExtractor;
    }

    public boolean signatureValidated(File encryptedLicenceFile, File signatureFile) throws LicenceCryptographyException {
        try {
            X509Certificate certificate = keyExtractor.getCertificateForVerification();
            PublicKey publicKey = certificate.getPublicKey();

            Signature signature = Signature.getInstance(SIGNATURE_PROVIDER_INTERFACE);
            signature.initVerify(publicKey);

            signature.update(FileUtils.readFileToByteArray(encryptedLicenceFile));

            return signature.verify(FileUtils.readFileToByteArray(signatureFile));
        } catch (SignatureException e) {
            LOGGER.error("Signature {} validation failed", signatureFile.getAbsolutePath(), e);
            return false;
        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException e) {
            throw new LicenceCryptographyException(e);
        }
    }
}
