package cn.ksmcbrigade.fc.mixin;

import cn.ksmcbrigade.fc.FreeCam;
import cn.ksmcbrigade.fc.XRay;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "shouldRenderFace",at = @At("RETURN"), cancellable = true)
    private static void render(BlockState p_152445_, BlockGetter p_152446_, BlockPos p_152447_, Direction p_152448_, BlockPos p_152449_, CallbackInfoReturnable<Boolean> cir){
        if(FreeCam.XR.enabled && XRay.get().contains(p_152445_.getBlock())){
            cir.setReturnValue(true);
        }
        else if(FreeCam.XR.enabled){
            cir.setReturnValue(false);
        }
    }
}
