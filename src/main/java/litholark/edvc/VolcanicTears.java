package litholark.edvc;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class VolcanicTears {
    private static final Supplier<List<@NotNull StatusEffectInstance>> BOTTLE_EFFECTS_SUPPLIER = () -> List.of(
            new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 20 * 100),
            new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 10, 2));

    public static ItemStack createBottle(){
        ItemStack stack = new ItemStack(Items.POTION);

        NbtCompound nbt = new NbtCompound();
        nbt.putString("custom_item", "volcanic_tears");
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
        stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(
                Optional.empty(),
                Optional.of(0xFF6400C8),
                BOTTLE_EFFECTS_SUPPLIER.get(),
                Optional.empty()));
        stack.set(DataComponentTypes.CUSTOM_NAME, Text.literal("Volcanic Tears").formatted(Formatting.YELLOW));
        return stack;
    }
}
