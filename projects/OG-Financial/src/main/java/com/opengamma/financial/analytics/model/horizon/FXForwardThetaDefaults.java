/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.horizon;

import java.util.Collections;
import java.util.Set;

import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.financial.analytics.OpenGammaFunctionExclusions;
import com.opengamma.financial.property.DefaultPropertyFunction;
import com.opengamma.financial.security.FinancialSecurityTypes;
import com.opengamma.util.ArgumentChecker;

/**
 * Sets the default number of days to move forward when calculating theta for
 * FX forwards and non-deliverable FX forwards.
 */
public class FXForwardThetaDefaults extends DefaultPropertyFunction {
  /** The priority */
  private final PriorityClass _priority;
  /** The default number of days to move forward */
  private final String _defaultNumberOfDays;

  /**
   * @param priority The priority
   * @param defaultNumberOfDays The default number of days to move forward, not null
   */
  public FXForwardThetaDefaults(final String priority, final String defaultNumberOfDays) {
    super(FinancialSecurityTypes.FX_FORWARD_SECURITY.or(FinancialSecurityTypes.NON_DELIVERABLE_FX_FORWARD_SECURITY), true);
    ArgumentChecker.notNull(defaultNumberOfDays, "default number of days");
    _priority = PriorityClass.valueOf(priority);
    _defaultNumberOfDays = defaultNumberOfDays;
  }

  @Override
  protected void getDefaults(final PropertyDefaults defaults) {
    defaults.addValuePropertyName(ValueRequirementNames.VALUE_THETA, ThetaPropertyNamesAndValues.PROPERTY_DAYS_TO_MOVE_FORWARD);
  }

  @Override
  protected Set<String> getDefaultValue(final FunctionCompilationContext context, final ComputationTarget target, final ValueRequirement desiredValue, final String propertyName) {
    if (ThetaPropertyNamesAndValues.PROPERTY_DAYS_TO_MOVE_FORWARD.equals(propertyName)) {
      return Collections.singleton(_defaultNumberOfDays);
    }
    return null;
  }

  @Override
  public PriorityClass getPriority() {
    return _priority;
  }

  @Override
  public String getMutualExclusionGroup() {
    return OpenGammaFunctionExclusions.FX_FORWARD_THETA_DEFAULTS;
  }

}
