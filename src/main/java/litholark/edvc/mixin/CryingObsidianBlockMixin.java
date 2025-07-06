package litholark.edvc.mixin;

import litholark.edvc.BrewingStandBEInterface;
import litholark.edvc.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CryingObsidianBlock;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CryingObsidianBlock.class)
public class CryingObsidianBlockMixin extends Block {
    public CryingObsidianBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockState bottomBlock = world.getBlockState(pos.add(new Vec3i(0, -1, 0)));
        if (bottomBlock.isOf(Blocks.HOPPER) || bottomBlock.isOf(Blocks.POINTED_DRIPSTONE)){
            if (world.getBlockState(pos.add(0, -2, 0)).isOf(Blocks.BREWING_STAND)){
                if (world.getRandom().nextDouble() < Constants.volcanicTearsConversionChance){
                    BrewingStandBlockEntity be = (BrewingStandBlockEntity) world.getBlockEntity(pos.add(0, -2, 0));

                    boolean a = ((BrewingStandBEInterface) be).attemptInsertVolcanicTears();

                    if (a) {
                        world.playSound(null, pos, SoundEvents.BLOCK_POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON, SoundCategory.BLOCKS, 2.0f, 0.5f);
                        if (Constants.doVolcanicRocksGetTherapy) {
                            world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
                            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(Blocks.OBSIDIAN.getDefaultState()));
                        }
                    } else {
                        world.playSound(null, pos, SoundEvents.BLOCK_POINTED_DRIPSTONE_DRIP_WATER, SoundCategory.BLOCKS, 1.0f, 0.8f);
                    }
                }
            }
        }
    }
}