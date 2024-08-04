package com.digio.challenge;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
})
@SuiteDisplayName("Suite that brings all integration tests")
@SuppressWarnings("squid:S2187")
public class IntegrationTests {
}
