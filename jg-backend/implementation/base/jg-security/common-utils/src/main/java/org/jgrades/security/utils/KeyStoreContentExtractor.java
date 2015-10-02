/*
 * Copyright (C) 2015 the original author or authors.
 *
 * This file is part of jGrades Application Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jgrades.security.utils;

import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import static org.jgrades.security.utils.CryptoDataConstants.*;


public class KeyStoreContentExtractor {
    private final File keystore;
    private final SecDatFileContentExtractor secExtractor;

    public KeyStoreContentExtractor(File keystore, File secDatFile) throws IOException {
        this.keystore = keystore;
        this.secExtractor = new SecDatFileContentExtractor(secDatFile);
    }

    public SecretKeySpec getPrivateKeyForEncryptionAndDecryption() {
        return (SecretKeySpec) getPrivateKey(ENCRYPTION_KEY_ALIAS, secExtractor.getEncryptionDat());
    }

    public PrivateKey getPrivateKeyForSigning() {
        return (PrivateKey) getPrivateKey(SIGNATURE_KEY_ALIAS, secExtractor.getSignatureDat());
    }

    public X509Certificate getCertificateForVerification() {
        return getCertificate(SIGNATURE_KEY_ALIAS);
    }

    private Object getPrivateKey(String alias, char[] secDat) {
        try {
            KeyStore ks = KeyStore.getInstance(KEYSTORE_TYPE);
            ks.load(new FileInputStream(keystore), secExtractor.getKeystoreDat());
            return ks.getKey(alias, secDat);
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException
                | IOException | UnrecoverableKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private X509Certificate getCertificate(String signatureKeyAlias) {
        try {
            KeyStore ks = KeyStore.getInstance(KEYSTORE_TYPE);
            ks.load(new FileInputStream(keystore), secExtractor.getKeystoreDat());
            return (X509Certificate) ks.getCertificate(signatureKeyAlias);
        } catch (KeyStoreException | CertificateException |
                NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
