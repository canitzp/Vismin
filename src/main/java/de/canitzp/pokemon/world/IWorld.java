package de.canitzp.pokemon.world;

import de.canitzp.pokemon.entity.EntityPlayer;

import java.awt.*;

/**
 * @author canitzp
 */
public interface IWorld {

    void onWorldTick();

    void renderWorld(Graphics graphics);

    void renderLayer2(Graphics graphics);

    void renderLayer3(Graphics graphics);

    void renderPlayer(Graphics graphics, EntityPlayer player);

    void renderLayer5(Graphics graphics);

    void renderLayer6(Graphics graphics);

}
