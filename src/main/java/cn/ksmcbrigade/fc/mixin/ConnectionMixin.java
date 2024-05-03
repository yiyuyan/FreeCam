package cn.ksmcbrigade.fc.mixin;

import cn.ksmcbrigade.fc.FreeCam;
import net.minecraft.network.Connection;
import net.minecraft.network.PacketSendListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Connection.class)
public class ConnectionMixin {
    @Inject(method = "sendPacket",at = @At("HEAD"),cancellable = true)
    public void sendPacket(Packet<?> p_243260_, PacketSendListener p_243290_, boolean p_299937_, CallbackInfo ci){
        if(FreeCam.FC.enabled){
            if(p_243260_ instanceof ServerboundMovePlayerPacket){
                ci.cancel();
            }
        }
    }
}
