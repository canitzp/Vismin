package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.Game;
import de.canitzp.vismin.Main;
import de.canitzp.vismin.blocks.Block;
import de.canitzp.vismin.blocks.BlockRegistry;
import de.canitzp.vismin.util.Position;
import de.canitzp.vismin.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * @author canitzp
 */
public class GuiWorldEditor extends GuiScreen {

    private Block currentBlock, fieldBlock;
    private World currentWorld;
    private Position mousePosition, relativeMouseBlockOffset;
    private boolean render = true;
    private GuiTextField fieldBlockName = new GuiTextField(new Position(75, Main.HEIGHT/3*2+5), 100);

    public GuiWorldEditor(){
        int x = 10, y = 10;
        for(Block block : BlockRegistry.blockList){
            addButton(new BlockButton(block, x, y, 64, 64));
            y += 74;
            if(y >= Main.frame.getHeight()){
                x += 74;
                y = 10;
            }
        }
        fieldList.add(fieldBlockName);
    }

    @Override
    public void onOpen() {
        this.currentWorld = Game.worldAdativa;
        Main.frame.setSize(new Dimension(Main.WIDTH + Main.WIDTH / 3, Main.HEIGHT));
        Main.frame.setLocationRelativeTo(null);
    }

    @Override
    public void onClose() {
        Main.frame.setSize(new Dimension(Main.WIDTH , Main.HEIGHT));
        Main.frame.setLocationRelativeTo(null);
    }

    @Override
    public void render(Graphics graphics) {
        if(render){
            int i =  Main.WIDTH/3;
            graphics.setColor(Color.lightGray);
            graphics.fillRect(0, 0, Main.WIDTH/3, Main.HEIGHT);
            graphics.setColor(Color.BLACK);
            graphics.drawLine(0, Main.HEIGHT/3 * 2, Main.WIDTH/3, Main.HEIGHT/3*2);
            graphics.drawString("Block Name:", 5, Main.HEIGHT/3*2+15);

            this.currentWorld.renderWorld(new Position(i, 0), graphics);
            super.render(graphics);
            List<Block> blocks = this.currentWorld.blockList;
            for (Block block : blocks){
                if(block.layer == Block.RenderLayer.LAYER2)
                    block.renderBlock(graphics, block.position.add(i, 0));
            }
            for (Block block : blocks){
                if(block.layer == Block.RenderLayer.LAYER3)
                    block.renderBlock(graphics, block.position.add(i, 0));
            }
            for (Block block : blocks){
                if(block.layer == Block.RenderLayer.LAYER5)
                    block.renderBlock(graphics, block.position.add(i, 0));
            }
            for (Block block : blocks){
                if(block.layer == Block.RenderLayer.LAYER6)
                    block.renderBlock(graphics, block.position.add(i, 0));
            }
            if(this.currentBlock != null && mousePosition != null && relativeMouseBlockOffset != null){
                this.currentBlock.renderBlock(graphics, mousePosition.add(-relativeMouseBlockOffset.getX(), -relativeMouseBlockOffset.getY()));
            }
            if(this.fieldBlock != null && this.currentBlock == null){
                this.fieldBlock.renderLines(graphics, Color.green, new Position(i, 0));
            }
            if(Main.debug){
                this.currentWorld.renderCollisionBoxes(graphics, new Position(i, 0));
                this.currentWorld.renderBlockBoundingBoxes(graphics, new Position(i, 0));
            }
        }
    }

    @Override
    public boolean onMouseLeftClick(Position position) {
        for(GuiButton button : buttonList){
            if(button instanceof BlockButton){
                Block block = ((BlockButton)button).getBlockAtPosition(position);
                if(block != null){
                    this.currentBlock = block.cloneBlock();
                    this.relativeMouseBlockOffset = new Position(block.width/2, block.height/2);
                }
            }
        }
        return super.onMouseLeftClick(position);
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        Position position = new Position(e.getPoint().getX() - Main.WIDTH/3, e.getPoint().getY());
        Block block = this.currentWorld.getBlockAtPosition(position);
        if(e.getButton() == MouseEvent.BUTTON1){
            if(block != null){
                this.currentBlock = this.fieldBlock = block.cloneBlock();
                setBlockStats(this.fieldBlock);
                this.relativeMouseBlockOffset = position.add(-block.position.getX(), -block.position.getY());
                this.render = false;
                this.currentWorld.deleteBlockFromWorld(position);
                this.render = true;
            } else if(e.getPoint().getX() > Main.WIDTH/3){
                this.fieldBlock = null;
                this.fieldBlockName.text = "";
            }
        } else if(e.getButton() == MouseEvent.BUTTON3){
            if(block != null){
                this.currentBlock = this.fieldBlock = block.cloneBlock();
                this.relativeMouseBlockOffset = new Position(block.width/2, block.height/2);
            }
        }
    }

    private void setBlockStats(Block block){
        this.fieldBlockName.setText(block.registryName);
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        this.mousePosition = new Position(event.getPoint().getX(), event.getPoint().getY());
    }

    @Override
    public void onMouseMove(MouseEvent event) {
        this.mousePosition = new Position(event.getPoint().getX(), event.getPoint().getY());
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        if(this.currentBlock != null && relativeMouseBlockOffset!= null){
            if(event.getPoint().getX() >= Main.WIDTH/3){
                this.render = false;
                this.currentWorld.addBlockToWorld(new Position(event.getX() - Main.WIDTH/3 - relativeMouseBlockOffset.getX(), event.getY() - relativeMouseBlockOffset.getY()), this.currentBlock.cloneBlock());
                this.render = true;
                this.fieldBlock = currentWorld.getBlockAtPosition(new Position(event.getX() - Main.WIDTH/3 - relativeMouseBlockOffset.getX(), event.getY() - relativeMouseBlockOffset.getY()));
            }
            this.currentBlock = null;
        }
    }

    @Override
    public void onKeyboardPressed(KeyEvent event) {
        for(GuiTextField field : fieldList){
            if(field.focus && this.fieldBlock != null){
                this.render = false;
                if(event.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                    if(field.text.length() >= 1){
                        field.setText(field.text.substring(0,field.text.length()-1));
                    }
                    this.render = true;
                    return;
                } else if(event.getKeyChar() == KeyEvent.VK_ENTER){
                    currentWorld.deleteBlockFromWorld(fieldBlock.position);
                    currentWorld.addBlockToWorld(fieldBlock.position, fieldBlock.setRegistryName(field.text));
                    field.setFocus(false);
                    this.render = true;
                    return;
                } else if(event.getKeyChar() == KeyEvent.VK_SHIFT){
                    this.render = true;
                    return;
                } else {
                    field.setText(field.text += event.getKeyChar());
                }
                this.render = true;
            }
        }
    }

    public static class BlockButton extends GuiImageButton {
        private List<Block> blocks = new ArrayList<>();

        public BlockButton(Block block, int x, int y, int width, int height) {
            super(x, y, width, height, block.getLocation());
            blocks.add(block);
        }

        public Block getBlockAtPosition(Position position) {
            for (Block block : this.blocks) {
                if (position.getX() >= x && position.getX() <= x + width) {
                    if (position.getY() >= y && position.getY() <= y + height) {
                        return block;
                    }
                }
            }
            return null;
        }
    }
}