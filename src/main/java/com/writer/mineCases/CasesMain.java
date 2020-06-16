package com.writer.mineCases;

import com.writer.mineCases.events.KeyHandler;
import com.writer.mineCases.network.CasesListPacket;
import com.writer.mineCases.network.CasesMainPacket;
import com.writer.mineCases.network.CasesViewPacket;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;


@Mod(
        modid = Refs.MOD_ID,
        version = Refs.MOD_VERSION,
        name = Refs.MOD_NAME
)

public class CasesMain {

    public static KeyBinding KeyTest;

    @Mod.Instance
    public static CasesMain instance;

    public static CasesMain getInstance() {
        return instance;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.newSimpleChannel("CasesShopChanel").registerMessage(CasesMainPacket.Handler.class, CasesMainPacket.class, 0, Side.CLIENT);
        NetworkRegistry.INSTANCE.newSimpleChannel("CasesListChanel").registerMessage(CasesListPacket.Handler.class, CasesListPacket.class, 0, Side.CLIENT);
        NetworkRegistry.INSTANCE.newSimpleChannel("CasesCurChanel").registerMessage(CasesViewPacket.Handler.class, CasesViewPacket.class, 0, Side.CLIENT);
        FMLCommonHandler.instance().bus().register(new KeyHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        KeyTest = new KeyBinding("key.friendsgui", Keyboard.KEY_Y, "key.categories.oshop");
        ClientRegistry.registerKeyBinding(KeyTest);
    }

}
