package com.github.playernguyen.coinquest.configurations;

public interface CoinquestConfigurationPattern {

    /**
     * Represents an address of the configuration section.
     *
     * <br/> For example: <br/>
     * <ul>
     *     <li>preferences.debug</li>
     *     <li>option.options1.options2</li>
     * </ul>
     *
     * @return an address as string of the configuration.
     */
    String getNode();

    /**
     * Default value after initialised the configuration file.
     *
     * @return a default value as object, the object should be serializable
     *  with yaml object.
     */
    Object getDefaultValue();

    /**
     * A comment block to describe the functionality of the node.
     *  The comments will be put above the configuration section
     *
     * @return a comment
     */
    String getComment();
}
