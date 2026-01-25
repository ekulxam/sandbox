package survivalblock.atmosphere.sandbox.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

// https://discord.com/channels/507304429255393322/721100785936760876/1464892272679190548
public final class ComponentUpdateTestItem extends PickaxeItem {

    public ComponentUpdateTestItem(Item.Properties properties) {
        super(Tiers.NETHERITE,  properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int i) {
        if (!(livingEntity instanceof Player player)) {
            return;
        }

        Component customName = Component.literal("Pickaxe").withStyle(ChatFormatting.GOLD);
        Component currentName = itemStack.get(DataComponents.ITEM_NAME);

        if (currentName != null && currentName.getString().equals(customName.getString())) {
            itemStack.remove(DataComponents.ITEM_NAME);
            player.displayClientMessage(Component.literal("Mode: Normal"), true);
        } else {
            itemStack.set(DataComponents.ITEM_NAME, customName);
            player.displayClientMessage(Component.literal("Mode: Custom"), true);
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.UI_BUTTON_CLICK, SoundSource.PLAYERS, 0.5F, 1.2F);
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 72000;
    }

    @Override
    public boolean allowComponentsUpdateAnimation(
            Player player,
            InteractionHand hand,
            ItemStack oldStack,
            ItemStack newStack
    ) {
        return false;
    }
}

