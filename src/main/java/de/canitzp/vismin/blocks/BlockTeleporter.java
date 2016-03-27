package de.canitzp.vismin.blocks;

import de.canitzp.vismin.util.WorldPosition;

/**
 * @author canitzp
 */
public class BlockTeleporter extends Block {

    private WorldPosition firstPosition, secondPosition;
    private boolean isReady;

    public BlockTeleporter(int width, int height, RenderLayer layer) {
        super(width, height, layer);
    }

    public BlockTeleporter(Block block) {
        super(block);
    }

    public BlockTeleporter setCoordinates(WorldPosition firstPosition, WorldPosition secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
        this.isReady = true;
        return this;
    }

    public WorldPosition getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(WorldPosition firstPosition) {
        this.firstPosition = firstPosition;
    }

    public WorldPosition getSecondPosition() {
        return secondPosition;
    }

    public void setSecondPosition(WorldPosition secondPosition) {
        this.secondPosition = secondPosition;
    }
}