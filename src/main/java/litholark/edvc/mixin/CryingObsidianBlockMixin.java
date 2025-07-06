package litholark.edvc.mixin;

import litholark.edvc.BrewingStandBEInterface;
import litholark.edvc.EmotionallyDistressedVolcanicGlass;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CryingObsidianBlock;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CryingObsidianBlock.class)
public class CryingObsidianBlockMixin extends Block {
    public CryingObsidianBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        EmotionallyDistressedVolcanicGlass.LOGGER.info("e");
        if (world.getBlockState(pos.add(new Vec3i(0, -1, 0))).isOf(Blocks.HOPPER)){
            if (world.getBlockState(pos.add(0, -2, 0)).isOf(Blocks.BREWING_STAND)){
                BrewingStandBlockEntity be = (BrewingStandBlockEntity) world.getBlockEntity(pos.add(0, -2, 0));

                boolean a = ((BrewingStandBEInterface) be).attemptInsertVolcanicTears();
                EmotionallyDistressedVolcanicGlass.LOGGER.info("" + a);
            }
        }
    }
}