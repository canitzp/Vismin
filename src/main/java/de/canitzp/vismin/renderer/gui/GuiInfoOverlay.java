package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.Main;
import de.canitzp.vismin.blocks.Block;
import de.canitzp.vismin.util.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author canitzp
 */
public class GuiInfoOverlay extends Gui {

    public Position position;
    public int width, height;
    public Block currentBlock;
    private List<GuiTextButton> buttons = new ArrayList<>();

    public GuiInfoOverlay(Block block, int width, int height){
        if(block != null){
            this.position = block.position;
            this.currentBlock = block;
            this.width = width;
            this.height = height;
            buttons.add(new GuiTextButton((int)position.getX() + 2, (int)position.getY() + 22, 10, 10, "+", "Block Width:" + block.width){
                @Override
                public boolean onClicked(Position position) {
                    currentBlock.width++;
                    return true;
                }
            }.setFont(null));
        }
    }

    public void render(Graphics graphics) {
        if(currentBlock != null){
            this.showBlockInfo(this.currentBlock, graphics);
            for(GuiTextButton button : buttons){
                button.render(graphics, new Position(Main.WIDTH/3 + currentBlock.width, 0));
            }
        }
    }

    private void showBlockInfo(Block currentBlock, Graphics graphics) {
        int x = (int) (currentBlock.position.getX() + Main.WIDTH/3 + currentBlock.width);
        int y = (int) currentBlock.position.getY();
        graphics.setColor(Color.lightGray);
        graphics.fillRect(x, y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawString(currentBlock.registryName, x + 2, y + 12);
        //graphics.drawString("Block Width:" + currentBlock.width, x + 2, y + 22);
    }

    public boolean onMousePress(Position position) {
        for (GuiButton button : this.buttons) {
            System.out.println(position.getX() + " " + button.x);
            if (position.getX() >= button.x && position.getX() <= button.x + button.width) {
                if (position.getY() >= button.y && position.getY() <= button.y + button.height) {
                    button.onClicked(position);
                }
            }
        }
        return true;
    }

    public void tick() {
        //checkMouseInput();
    }
}