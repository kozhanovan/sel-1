package sel1.kan.core;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Contains customer data to fill new account form.
 */
public class Customer {
    /**
     * Customer fields.
     */
    public enum Field {
        /**
         * First name.
         */
        FIRST_NAME,

        /**
         * Last name.
         */
        LAST_NAME,

        /**
         * Address line 1.
         */
        ADDRESS1,

        /**
         * Postal code.
         */
        POSTCODE,

        /**
         * City.
         */
        CITY,

        /**
         * Country.
         */
        COUNTRY,

        /**
         * State, if applicable.
         */
        STATE,

        /**
         * Email.
         */
        EMAIL,

        /**
         * Phone.
         */
        PHONE,

        /**
         * Subscribe flag.
         */
        SUBSCRIBE,

        /**
         * Password.
         */
        PASSWORD
    }

    /**
     * Customer data.
     */
    private final Map<Field, String> data = new TreeMap<>();

    /**
     * Initializes customer with random values.
     */
    public void generateRandom() {
        this.put(Field.FIRST_NAME, "John");
        this.put(Field.LAST_NAME, "Doe");
        this.put(Field.ADDRESS1, "Fake str, 123");
        this.put(Field.POSTCODE, "12345-6789");
        this.put(Field.CITY, "Seleniumtown");
        this.put(Field.COUNTRY, "United States");
        this.put(Field.STATE, "California");
        this.put(Field.EMAIL, "test+" + RandomStringUtils.randomAlphanumeric(6)
                + "@litecart.com");
        this.put(Field.PHONE, "+1 123 5551234");
        this.put(Field.SUBSCRIBE, "false");
        this.put(Field.PASSWORD, "Hello132");
    }

    /**
     * Returns value for provided key.
     *
     * @param key
     *            key
     * @return value or <code>null</code>
     */
    public String get(final Field key) {
        return this.data.get(key);
    }

    /**
     * Sets value for provided key.
     *
     * @param key
     *            key
     * @param value
     *            value
     * @return the previous value associated with key, or <code>null</code> if
     *         there was no mapping for key.
     */
    public String put(final Field key, final String value) {
        return this.data.put(key, value);
    }
}
