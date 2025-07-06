package litholark.edvc.mixin;

import litholark.edvc.BrewingStandBEInterface;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

@Mixin(BrewingStandBlockEntity.class)
public abstract class BrewingStandBEMixin implements BrewingStandBEInterface {
    @Shadow
    int brewTime;

    @Shadow
    protected abstract DefaultedList<ItemStack> getHeldStacks();

    @Unique
    public boolean attemptInsertVolcanicTears() {
        if (brewTime <= 0) {
            List<ItemStack> inventory = getHeldStacks();
            ItemStack[] bottles = {inventory.get(0), inventory.get(1), inventory.get(2)};

            boolean flag = false;
            for (int i = 0; i < 3; i++) {
                ItemStack item = bottles[i];

                if (item.isOf(Items.GLASS_BOTTLE)) {
                    ItemStack newItem = new ItemStack(Items.POTION);
                    NbtCompound nbt = new NbtCompound();
                    nbt.putString("custom_item", "volcanic_tears");
                    newItem.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
                    inventory.set(i, newItem);
                    flag = true;
                }
            }
            return flag;
        }

        return false;
    }
}
