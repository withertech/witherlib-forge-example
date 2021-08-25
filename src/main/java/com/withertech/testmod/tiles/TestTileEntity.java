/*
 * witherlib-forge
 * Copyright (C) 2021 WitherTech
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.withertech.testmod.tiles;

import com.withertech.testmod.TestMod;
import com.withertech.witherlib.WitherLib;
import com.withertech.witherlib.registration.TypedRegKey;
import com.withertech.witherlib.tile.BaseTileEntity;
import com.withertech.witherlib.util.SyncVariable;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TestTileEntity extends BaseTileEntity<TestTileEntity>
{
    @SyncVariable(name = "Items")
    public final LazyOptional<ItemStackHandler> itemsLazy = LazyOptional.of(this::createHandler);

    public TestTileEntity()
    {
        super(TestMod.INSTANCE.REGISTRY.getTile((TypedRegKey.tile("test_tile", TestTileEntity.class))).get());
    }

    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderFace(Direction p_184313_1_)
    {
        return Block.shouldRenderFace(this.getBlockState(), this.level, this.getBlockPos(), p_184313_1_);
    }

    @Nonnull
    private ItemStackHandler createHandler()
    {
        return new ItemStackHandler(54)
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                dataChanged();
            }

        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return itemsLazy.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    protected void invalidateCaps()
    {
        super.invalidateCaps();
        itemsLazy.invalidate();
    }
}
