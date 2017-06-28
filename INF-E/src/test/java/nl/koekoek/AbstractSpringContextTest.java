/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@ActiveProfiles({ "test", "in-memory-db" })
@Transactional
public abstract class AbstractSpringContextTest {
    
}
