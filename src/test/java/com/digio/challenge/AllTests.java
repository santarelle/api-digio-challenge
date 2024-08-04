package com.digio.challenge;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        IntegrationTests.class,
        AcceptanceCucumberTests.class
})
@SuiteDisplayName("Suite that brings integration and acceptance tests")
@SuppressWarnings("squid:S2187")
public class AllTests {
}
