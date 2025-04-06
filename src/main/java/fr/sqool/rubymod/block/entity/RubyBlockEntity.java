package fr.sqool.rubymod.block.entity;

import fr.sqool.rubymod.RubyMod;
import fr.sqool.rubymod.item.RubyItem;
import fr.sqool.rubymod.screen.RubyChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProviderImpl;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RubyBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public RubyBlockEntity(BlockPos pos, BlockState state) {
        super(RubyBlockEntities.RUBY_CHEST.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> RubyBlockEntity.this.progress;
                    case 1 -> RubyBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> RubyBlockEntity.this.progress = value;
                    case 1 -> RubyBlockEntity.this.progress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Ruby Chest");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new RubyChestMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
    }

    public void drop() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RubyBlockEntity entity) {
        if (level.isClientSide()) {
            return;
        }

        if (hasRecipe(entity)) {
            entity.progress += 1;
            setChanged(level, pos, state);

            if (entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        }

        else {
            entity.resetProgress();
            setChanged(level, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(RubyBlockEntity entity) {
        if(hasRecipe(entity)) {
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.setStackInSlot(2, new ItemStack(RubyItem.RUBY_INGOT.get(), entity.itemHandler.getStackInSlot(2).getCount() + 1));
            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(RubyBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());

        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        boolean hasRaw = entity.itemHandler.getStackInSlot(1).getItem() == RubyItem.RUBY_INGOT.get();
        return  hasRaw && canInsertAmoutIntoOutputSlot(inventory) && canInsertIntoOutputSlot(inventory, new ItemStack(RubyItem.RUBY_INGOT.get(), 1));
    }

    private static boolean canInsertIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmoutIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }
}
