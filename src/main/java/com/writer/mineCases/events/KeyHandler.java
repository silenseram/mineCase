package com.writer.mineCases.events;


import com.writer.mineCases.CasesMain;
import com.writer.mineCases.gui.GuiMotd;
import com.writer.mineCases.network.Recieve;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;


public class KeyHandler {

    @SubscribeEvent
    public void KeyPress(InputEvent.KeyInputEvent event) {

        if (CasesMain.KeyTest.isPressed()) {
            //Minecraft.getMinecraft().displayGuiScreen(new GuiCaseWon(new ItemStack(Blocks.dirt, 9), 2));
            //Minecraft.getMinecraft().displayGuiScreen(new GuiCasesShop());
            //Minecraft.getMinecraft().displayGuiScreen(new GuiCaseView());
            //System.out.println(Recieve.CURRENT_CASE_ITEMS_LIST);
            Minecraft.getMinecraft().player.sendChatMessage("/mpcaseshop");
            Minecraft.getMinecraft().displayGuiScreen(new GuiMotd(Recieve.MOTD_IMG));
        }
    }
}