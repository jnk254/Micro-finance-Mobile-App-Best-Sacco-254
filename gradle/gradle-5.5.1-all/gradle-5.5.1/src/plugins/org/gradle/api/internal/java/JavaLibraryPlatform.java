/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.internal.java;

import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.model.ObjectFactory;

import javax.inject.Inject;

/**
 * Represents an attempt at providing a Java platform definition.
 *
 * @deprecated Replaced by the {@code java-platform} plugin
 */
@Deprecated
public class JavaLibraryPlatform extends JavaLibrary {
    @Inject
    public JavaLibraryPlatform(ObjectFactory objectFactory, ConfigurationContainer configurations, ImmutableAttributesFactory attributesFactory) {
        super(objectFactory, configurations, attributesFactory, null);
    }

    @Override
    public String getName() {
        return "javaLibraryPlatform";
    }
}
