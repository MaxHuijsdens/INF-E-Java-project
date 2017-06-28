/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek.service;

import nl.koekoek.AbstractSpringContextTest;
import nl.koekoek.service.ApplicationService.VersionInfo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationServiceTest extends AbstractSpringContextTest {
    
    @Autowired
    private ApplicationService applicationService;
    
    @Test
    public void testGetApplicationVersion() {
        VersionInfo versionInfo = applicationService.getVersionInfo();
        Assert.assertNotNull(versionInfo);
        Assert.assertNotNull(versionInfo.getApplicationVersion());
    }

}
