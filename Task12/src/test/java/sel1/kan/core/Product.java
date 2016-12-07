package sel1.kan.core;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Stores product information.
 */
public class Product {
    /**
     * Product fields.
     */
    public enum Field {
        /**
         * Product name.
         */
        NAME,

        /**
         * Product code.
         */
        CODE,

        /**
         * Product quantity.
         */
        QUANTITY,

        /**
         * Keywords.
         */
        KEYWORDS,

        /**
         * Short description.
         */
        SHORT_DESCRIPTION,

        /**
         * Description.
         */
        DESCRIPTION,

        /**
         * Head title.
         */
        HEAD_TITLE,

        /**
         * Meta description.
         */
        META,

        /**
         * Purchase price.
         */
        PURCHASE_PRICE,

        /**
         * Price.
         */
        PRICE,
    }

    /**
     * Product data.
     */
    private final Map<Field, String> data = new TreeMap<>();

    /**
     * Fills product with new random values.
     */
    public void generate() {
        String randomPart = RandomStringUtils.randomAlphanumeric(6);
        this.put(Field.NAME, "Product-" + randomPart);
        this.put(Field.CODE, "C-" + randomPart);
        this.put(Field.QUANTITY, "10");
        this.put(Field.KEYWORDS, randomPart);
        this.put(Field.SHORT_DESCRIPTION, "Short for " + randomPart);
        this.put(Field.DESCRIPTION,
                "This is a description for product " + randomPart);
        this.put(Field.HEAD_TITLE, "Title " + randomPart);
        this.put(Field.META, "Meta " + randomPart);
        this.put(Field.PURCHASE_PRICE, "10");
        this.put(Field.PRICE, "15");
    }

    /**
     * Returns value for provided key.
     *
     * @param key
     *            key
     * @return value
     */
    public String get(final Field key) {
        return this.data.get(key);
    }

    /**
     * Sets new value for provided key.
     *
     * @param key
     *            key
     * @param value
     *            value
     * @return previously associated value or <code>null</code>
     */
    public String put(final Field key, final String value) {
        return this.data.put(key, value);
    }
}
