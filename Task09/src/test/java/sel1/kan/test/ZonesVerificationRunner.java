package sel1.kan.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Parameterized;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.junit.ExposeNotifierRunner;

/**
 * Sets up zones verification test.
 */
@RunWith(ExposeNotifierRunner.class)
public class ZonesVerificationRunner {

    /**
     * Starts zones verification test.
     */
    @Test
    public void startZonesVerificationTest() throws Throwable {
        String[] locators = (String[]) Context.getContext()
                .get(ContextKey.ZONES_LOCATORS);

        if ((null != locators) && (locators.length > 0)) {
            RunNotifier notifier = (RunNotifier) Context.getContext()
                    .get(ContextKey.NOTIFIER);
            new Parameterized(ZonesSortingVerifier.class).run(notifier);
        }
    }
}
