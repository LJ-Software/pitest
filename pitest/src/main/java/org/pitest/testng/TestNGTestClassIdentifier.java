/*
 * Copyright 2011 Henry Coles
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package org.pitest.testng;

import org.pitest.classinfo.ClassInfo;
import org.pitest.classinfo.ClassName;
import org.pitest.functional.Option;
import org.pitest.testapi.TestClassIdentifier;

public class TestNGTestClassIdentifier implements TestClassIdentifier {
  private final static ClassName annotationName = new ClassName(
                                                    "org.testng.annotations.Test");

  public boolean isATestClass(final ClassInfo a) {
    return a.hasAnnotation(annotationName) || isATestClass(a.getSuperClass());
  }

  private boolean isATestClass(final Option<ClassInfo> clazz) {
    return clazz.hasSome() && isATestClass(clazz.value());
  }

  public boolean isIncluded(ClassInfo a) {
    return true;
  }

  public boolean isExcluded(ClassInfo a) {
    return false;
  }
}
