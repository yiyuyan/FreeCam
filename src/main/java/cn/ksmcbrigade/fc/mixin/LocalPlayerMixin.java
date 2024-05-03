package cn.ksmcbrigade.fc.mixin;

import cn.ksmcbrigade.fc.FreeCam;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class LocalPlayerMixin extends Player {

    public LocalPlayerMixin(Level p_250508_, BlockPos p_250289_, float p_251702_, GameProfile p_252153_) {
        super(p_250508_, p_250289_, p_251702_, p_252153_);
    }

    @Inject(method = "isSpectator",at = @At("RETURN"), cancellable = true)
    public void spectator(CallbackInfoReturnable<Boolean> cir){
        try {
            if (Minecraft.getInstance().player != null && FreeCam.FC.enabled && (this.getId() == Minecraft.getInstance().player.getId())) {
                cir.setReturnValue(true);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
