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

package com.withertech.testmod.screens;

import com.withertech.testmod.TestMod;
import com.withertech.testmod.containers.TestProgressContainer;
import com.withertech.testmod.network.TestProgressPacket;
import com.withertech.testmod.tiles.TestProgressTile;
import com.withertech.witherlib.gui.TileEntityBaseContainerScreen;
import com.withertech.witherlib.gui.widget.ProgressBarWidget;
import com.withertech.witherlib.gui.widget.ToggleButtonWidget;
import com.withertech.witherlib.util.TextComponents;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TestProgressScreen extends TileEntityBaseContainerScreen<TestProgressTile, TestProgressContainer>
{
    public TestProgressScreen(TestProgressContainer testProgressContainer, @Nullable PlayerInventory playerInventory, ITextComponent textComponent)
    {
        super(testProgressContainer, playerInventory, textComponent);
    }

    @Override
    protected int sizeX(@Nonnull TestProgressTile tile)
    {
        return 176;
    }

    @Override
    protected int sizeY(@Nonnull TestProgressTile tile)
    {
        return 222;
    }

    @Override
    protected void addWidgets(@Nonnull TestProgressTile tile)
    {
        addWidget(new ProgressBarWidget(72, 64, tile::getProgress, tile::getMaxProgress));
        addWidget(new ToggleButtonWidget(16, 64, 48, 16, TextComponents.string("Start").get(), () -> TestMod.INSTANCE.REGISTRY.getNet("main").sendToServer(new TestProgressPacket(this.container.getTilePos())), tile::isRunning));
    }
}
