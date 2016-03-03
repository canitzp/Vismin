package de.canitzp.vismin.blocks;

/**
 * @author canitzp
 */
public class BlockRegistry {

    public static Block tree1;

    public static void preInit(){
        tree1 = new Block(16, 32, Block.RenderLayer.LAYER5).setRegistryName("tree1");
    }

}
