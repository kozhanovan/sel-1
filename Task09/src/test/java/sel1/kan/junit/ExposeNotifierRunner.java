package sel1.kan.junit;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;

/**
 * Runner which exposes notifier to running tests.
 */
public class ExposeNotifierRunner extends BlockJUnit4ClassRunner {

    /**
     * Constructor.
     *
     * @param klass
     *            class to run
     * @throws InitializationError
     *             if some problems occur
     */
    public ExposeNotifierRunner(final Class<?> klass)
            throws InitializationError {
        super(klass);
    }

    @Override
    protected void runChild(final FrameworkMethod method,
            final RunNotifier notifier) {
        Context.getContext().put(ContextKey.NOTIFIER, notifier);
        super.runChild(method, notifier);
    }

}
