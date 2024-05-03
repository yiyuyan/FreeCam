package cn.ksmcbrigade.fc;

import cn.ksmcbrigade.vmr.command.Command;
import cn.ksmcbrigade.vmr.uitls.CommandUtils;
import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod("fc")
public class FreeCam {

    public static FCM FC = new FCM();

    public static XRay XR = new XRay();

    public FreeCam() {
        MinecraftForge.EVENT_BUS.register(this);
        ModuleUtils.add(FC);
        ModuleUtils.add(XR);
        CommandUtils.add(new Command("tp",3){
            @Override
            public void onCommand(Minecraft MC, Player player, String[] args) {
                try {
                    Vec3 pos = new Vec3(Double.parseDouble(args[0]),Double.parseDouble(args[1]),Double.parseDouble(args[2]));
                    player.setPos(pos);
                    Objects.requireNonNull(MC.getConnection()).getConnection().send(new ServerboundMovePlayerPacket.Pos(pos.x,pos.y,pos.z,player.onGround()));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
