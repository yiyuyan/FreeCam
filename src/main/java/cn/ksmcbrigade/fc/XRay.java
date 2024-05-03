package cn.ksmcbrigade.fc;

import cn.ksmcbrigade.vmr.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class XRay extends Module {

    public static ArrayList<Block> blocks = new ArrayList<>();

    public XRay() {
        super("hack.name.xr",false, KeyEvent.VK_X);
    }

    public static ArrayList<Block> get(){
        if(blocks.size()==0){
            blocks.addAll(ForgeRegistries.BLOCKS.getValues().stream()
                    .filter(f -> f.getName().getString().toLowerCase().contains("ore"))
                    .toList());
        }
        return blocks;
    }

    @Override
    public void enabled(Minecraft MC) {
        MC.reloadResourcePacks();
    }

    @Override
    public void disabled(Minecraft MC) {
        MC.reloadResourcePacks();
    }
}
