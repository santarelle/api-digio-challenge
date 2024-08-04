package com.digio.challenge;

import com.digio.challenge.adapter.in.web.impl.PurchaseControllerImplTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        PurchaseControllerImplTest.class
})
@SuiteDisplayName("Suite that brings all integration tests")
@SuppressWarnings("squid:S2187")
public class IntegrationTests {
}
