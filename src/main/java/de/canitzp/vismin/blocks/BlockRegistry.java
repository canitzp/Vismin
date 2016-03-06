package de.canitzp.vismin.blocks;

import de.canitzp.vismin.renderer.Images;

import java.util.ArrayList;
import java.util.List;

/**
 * @author canitzp
 */
public class BlockRegistry {

    public static List<Block> blockList = new ArrayList<>();

    public static Block tree1;
    public static Block telepad;

    public static void preInit(){
        tree1 = registerBlock(16, 32, Block.RenderLayer.LAYER5).setRegistryName("tree1");
        telepad = registerBlock(16, 16, Block.RenderLayer.LAYER2).setRegistryName("telepad").setLocation(Images.ImageEnum.TELEPAD).setNoCollision();
    }

    private static Block registerBlock(int width, int height, Block.RenderLayer layer){
        Block block = new Block(width, height, layer);
        blockList.add(block);
        return block;
    }

}
