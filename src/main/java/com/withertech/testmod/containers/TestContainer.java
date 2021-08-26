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

package com.withertech.testmod.containers;

import com.withertech.testmod.TestMod;
import com.withertech.testmod.tiles.TestTileEntity;
import com.withertech.witherlib.gui.TileEntityBaseContainer;
import com.withertech.witherlib.registration.TypedRegKey;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class TestContainer extends TileEntityBaseContainer<TestContainer, TestTileEntity>
{
    private static final int SIZE = 54;

    public TestContainer(int id, PlayerEntity player, BlockPos pos)
    {
        super(TestMod.INSTANCE.REGISTRY.getContainer(TypedRegKey.container("test_container", TestContainer.class)).get(), id, player, pos);
        addSlots();
    }

    @Override
    protected void addSlots(PlayerEntity player, @Nonnull TestTileEntity object)
    {
        object.items.ifPresent(itemStackHandler ->
        {
            for (int i = 0; i < 6; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    addSlot(new SlotItemHandler(itemStackHandler, j + i * 9, 8 + j * 18, 24 + i * 18));
                }
            }
        });
        addPlayerSlots(8, 140);
    }


    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem())
        {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < SIZE)
            {
                if (!this.moveItemStackTo(itemstack1, SIZE, this.slots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(itemstack1, 0, SIZE, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }
            else
            {
                slot.setChanged();
            }
        }

        return itemstack;
    }
}
