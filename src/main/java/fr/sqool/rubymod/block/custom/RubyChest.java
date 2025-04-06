package fr.sqool.rubymod.block.custom;

import fr.sqool.rubymod.block.entity.RubyBlockEntities;
import fr.sqool.rubymod.block.entity.RubyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class RubyChest extends BaseEntityBlock {
    public RubyChest(Properties properties) {
        super(properties);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newstate, boolean isMoving) {
        if (state.getBlock() != newstate.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof RubyBlockEntity) {
                ((RubyBlockEntity) blockEntity).drop();
            }
        }
        super.onRemove(state, level, pos, newstate, isMoving);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);

            if (entity instanceof RubyBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer) player), (RubyBlockEntity)entity, pos);
            }

            else {
                throw new IllegalStateException("Our container provider is missing");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RubyBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, RubyBlockEntities.RUBY_CHEST.get(), RubyBlockEntity::tick);
    }
}
