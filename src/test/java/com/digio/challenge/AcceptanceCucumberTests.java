package com.digio.challenge;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@SuiteDisplayName("Suite that brings all acceptance tests")
@SuppressWarnings("squid:S2187")
public class AcceptanceCucumberTests {

}