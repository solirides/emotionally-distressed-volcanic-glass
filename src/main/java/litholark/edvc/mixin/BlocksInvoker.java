package litholark.edvc.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Function;

@Mixin(Blocks.class)
public interface BlocksInvoker {
    @Invoker("register")
    static Block register(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings){
        throw new AssertionError();
    }
}
