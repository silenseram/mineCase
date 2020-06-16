package com.writer.mineCases.gui;

import com.google.common.collect.Maps;
import com.writer.mineCases.CasesMain;
import com.writer.mineCases.Refs;
import com.writer.mineCases.model.ModelItemCase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.IResource;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.sql.Ref;
import java.util.Map;


public class MPGui extends GuiScreen {

    ModelItemCase modelItemCase = new ModelItemCase();
    float delta = 0;
    final double tc = 60D;
    long lastTime = System.nanoTime();
    private int MX = 0;
    private int MY = 0;
    // TODO
    ResourceLocation location = new ResourceLocation("mineCase", "rolling");
    // SoundEvent event = new SoundEvent("/src");

    protected void drawScaledString(String text, float x, float y, float scale, TextPosition textPosition) {

        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0.0F);
        GL11.glScalef(scale, scale, 0.0F);

        if (textPosition == TextPosition.CENTER) {
            drawCenteredString(mc.fontRenderer, text, 0, 0, 0xFFFFFF);
        } else if (textPosition == TextPosition.LEFT) {
            drawString(mc.fontRenderer, text, 0, 0, 0xFFFFFF);
        } else if (textPosition == TextPosition.RIGHT) {
            //drawString(mc.fontRenderer, text, -fontRendererObj.getStringWidth(text), 0, 0xFFFFFF); БЫЛО
            drawString(mc.fontRenderer, text, -this.fontRenderer.getStringWidth(text), 0, 0xFFFFFF); // сделал рекер в 2020
        }
        GL11.glPopMatrix();
    }

    enum TextPosition {
        LEFT, CENTER, RIGHT
    }

    /**
     * Метод для отрисовки изображения с ЛЮБЫМ размером текстуры;
     * posX, posY - позиция width, height - размер
     */
    protected void drawCompleteImage(int posX, int posY, int width, int height) {
        GL11.glPushMatrix();

        GL11.glTranslatef(posX, posY, 0.0F);
        GL11.glBegin(7);

        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex3f(0.0F, 0.0F, 0.0F);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex3f(0.0F, height, 0.0F);
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex3f(width, height, 0.0F);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex3f(width, 0.0F, 0.0F);
        GL11.glEnd();

        GL11.glPopMatrix();
    }

    public boolean isHover(int xx, int yy, int xx1, int yy1) {
        int mouseX = MX;
        int mouseY = MY;
        if (mouseX >= xx && mouseX < xx1+xx && mouseY >= yy && mouseY < yy1+yy) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isClicked(int xx, int yy, int xx1, int yy1) {
        int mouseX = MX;
        int mouseY = MY;
        if (mouseX >= xx && mouseX < xx1+xx && mouseY >= yy && mouseY < yy1+yy && isClicked) {
            return true;
        } else {
            return false;
        }
    }

    protected void draw3DCase(int x, int y, String texture, float rotation) {
        GL11.glPushMatrix();
        bindTexture(texture);
        GL11.glTranslatef(x + 27, y - 12,44);
        GL11.glScalef(20,20,20);
        GL11.glRotatef(-8, 1, 0, 0);
        //160
        GL11.glRotatef(rotation, 0, 1, 0);
        GL11.glDisable(GL11.GL_CULL_FACE);
        modelItemCase.renderModel(0.0625F);
        GL11.glPopMatrix();
    }

    protected boolean isClicked = false;
    class Timing extends Thread {

        private int timer;

        public Timing(int timer) {
            this.timer = timer;
        }

        public void run() {
            try {
                Thread.sleep(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isClicked = false;
            stop();
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int b) throws IOException {
        super.mouseClicked(x, y, b);

        isClicked = true;
        Timing timing = new Timing(100); timing.start();
    }

    @Override
    public void drawScreen(int x, int y, float ticks) {
        MX = x;
        MY = y;
        double ns = 1000000000 / tc;
        long now = System.nanoTime();
        delta = (float) ((now - lastTime) / ns);
        lastTime = now;
    }

    protected void draw3DGuiItem(ItemStack itemStack, float x, float y, float scale) {
        ItemStack is = itemStack.copy();
        is.setCount(1); // TODO

        itemStack.setItemDamage(itemStack.getItemDamage());
        //EntityItem entityItem = new EntityItem(mc.theWorld, 0D, 0D, 0D, is); БЫЛО
        EntityItem entityItem = new EntityItem(mc.world, 0D, 0D, 0D, is); // сделал рекер в 2020

        entityItem.hoverStart = 0;
        GL11.glPushMatrix();


        RenderHelper.enableGUIStandardItemLighting();
        GL11.glTranslatef(x,y,4);
        GL11.glRotatef(-11, 1, 0, 0);
        GL11.glRotatef(160, 0, 1, 0);
        GL11.glRotatef(180, 1, 0, 0);
        GL11.glScalef(scale,scale,scale);


        Minecraft.getMinecraft().getRenderManager().renderEntity(entityItem, 0, 0, 0, 0.2F, 0.2F, false); //TODO

        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
    }


    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    protected void playSound(String name) {
    	//mc.thePlayer.playSound(Refs.MOD_ID + ":" + name, 1.0F, 1.0F); БЫЛО
        ;
    	mc.player.playSound(new SoundEvent(location), 1.0F, 1.0F); //TODO
    }

    private static Map<String, DynamicTexture> images = Maps.newHashMap();

    public static void bindTexture(String name){
        if (images.get(name) != null)
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, images.get(name).getGlTextureId());
    }

    public void addTex(String name, String image){
        try {
            System.out.println("TEXTURE=" + image);

            images.put(name, new DynamicTexture(ImageIO.read(new URL(image))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
