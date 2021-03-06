/*
 * Copyright (C) 2016 the original author or authors.
 *
 * This file is part of jGrades Application Project.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jgrades.backup.restore;

import org.jgrades.backup.api.entities.Backup;
import org.jgrades.backup.api.model.RestoreSettings;
import org.springframework.stereotype.Component;

@Component
public class RestoringPerformer {
    public void restore(Backup backup, RestoreSettings restoreSettings) {
        throw new UnsupportedOperationException();
    }
}
