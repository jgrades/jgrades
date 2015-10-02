/*
 * Copyright (C) 2015 the original author or authors.
 *
 * This file is part of jGrades Application Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jgrades.admin.api.model;

import lombok.Data;
import org.jgrades.data.api.entities.ClassGroup;

@Data
public class StudentMassCreatingSettings {
    private boolean activeAfterCreation;

    private ClassGroup targetClassGroup;

    private LoginGenerationStrategy<StudentCsvEntry> loginGenerationStrategy;
}
