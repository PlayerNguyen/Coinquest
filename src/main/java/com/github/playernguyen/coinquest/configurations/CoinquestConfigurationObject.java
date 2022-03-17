package com.github.playernguyen.coinquest.configurations;

import com.github.playernguyen.coinquest.storages.CoinquestStorageType;

public class CoinquestConfigurationObject {

    private final Object object;

    public CoinquestConfigurationObject(Object object) {
        this.object = object;
    }

    /**
     * Cast a current object as string.
     *
     * @return a string that cast from an object.
     */
    public String asString() throws ClassCastException {
        return (String) object;
    }

    /**
     * Cast a current object as integer.
     *
     * @return an integer object cast from object.
     */
    public int asInt() throws ClassCastException {
        return (int) object;
    }

    /**
     * @return a double object cast from object
     * @throws ClassCastException invalid cast object
     */
    public double asDouble() throws ClassCastException {
        return (double) object;
    }

    /**
     * Turn it into an object (references).
     *
     * @return an object
     * @throws ClassCastException invalid cast object exception.
     */
    public Object asObject() throws ClassCastException {
        return object;
    }

    /**
     * Turn it into a storage type, throws {@link IllegalStateException} if
     * not found a storage type.
     *
     * @return a storage type enumeration object.
     * @throws ClassCastException cannot cast into string before turn it into storage type enum.
     */
    public CoinquestStorageType asStorageType() throws ClassCastException {
        return CoinquestStorageType.parseStorageType(this.asString());
    }

}
