package com.uber.okbuck.core.manager;

import org.gradle.api.artifacts.ExcludeRule;
import org.gradle.api.artifacts.ExternalDependency;

import java.util.Set;
import java.util.TreeSet;

public class DependencyModel {

  String name;
  ExternalDependency externalDependency;

  Set<String> excludes;

  private DependencyModel(ExternalDependency externalDependency) {
    this.name = externalDependency.getName();
    this.externalDependency = externalDependency;
    this.excludes = new TreeSet<>();
    update(externalDependency);
  }

  public static DependencyModel get(ExternalDependency externalDependency) {
    return new DependencyModel(externalDependency);
  }

  public void update(ExternalDependency externalDependency) {
    if (externalDependency.getExcludeRules() != null) {
      for (ExcludeRule excludeRule : externalDependency.getExcludeRules()) {
        if (excludeRule.getModule() != null) {
          excludes.add(excludeRule.getModule());
        }
        if (excludeRule.getGroup() != null) {
          excludes.add(excludeRule.getGroup());
        }
      }
    }
  }

  public String getName() {
    return externalDependency.getName();
  }

}
