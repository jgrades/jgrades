/*
 * Copyright (C) 2015 the original author or authors.
 *
 * This file is part of jGrades Application Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jgrades.admin.api.accounts;

import org.jgrades.admin.api.common.CrudPagingService;
import org.jgrades.admin.api.common.PagingSpecificationSelector;
import org.jgrades.data.api.entities.User;

public interface UserMgntService<U extends User> extends CrudPagingService<U, Long>, PagingSpecificationSelector<U, Long> {
}
