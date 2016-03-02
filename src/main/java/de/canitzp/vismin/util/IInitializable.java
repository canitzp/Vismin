package de.canitzp.vismin.util;

/**
 * @author canitzp
 */
public interface IInitializable {

    void preInit();

    void init();

    void postInit();

    void onGameStarts();

}
