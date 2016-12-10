package sel1.kan.core;

import java.util.Map;
import java.util.TreeMap;

/**
 * Context class: stores various information.
 */
public final class Context {
    /**
     * {@link Context} instance.
     */
    private static ThreadLocal<Context> context = new ThreadLocal<>();

    /**
     * Data storage.
     */
    private final Map<String, Object>   data    = new TreeMap<>();

    /**
     * Private constructor.
     */
    private Context() {
    }

    /**
     * Returns {@link Context} instance.
     *
     * @return {@link Context} instance
     */
    public static Context getContext() {
        Context result = Context.context.get();

        if (null == result) {
            result = new Context();
            Context.context.set(result);
        }

        return result;
    }

    /**
     * Returns value by provided key.
     * 
     * @param key
     *            key
     * @return value found or <code>null</code>
     */
    public Object get(final String key) {
        return this.data.get(key);
    }

    /**
     * Stores key-value pair.
     *
     * @param key
     *            key
     * @param value
     *            value
     */
    public void put(final String key, final Object value) {
        this.data.put(key, value);
    }
}
