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

package com.withertech.testmod.gui;

import com.withertech.testmod.TestMod;
import com.withertech.testmod.blocks.TestProgressBlock;
import com.withertech.testmod.containers.TestProgressContainer;
import com.withertech.testmod.screens.TestProgressScreen;
import com.withertech.testmod.tiles.TestProgressTile;
import com.withertech.witherlib.gui.TileGui;
import com.withertech.witherlib.registration.TypedRegKey;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Function;

public class TestProgressGui extends TileGui<TestProgressBlock, TestProgressTile, TestProgressContainer, TestProgressScreen, TileEntityRenderer<TestProgressTile>>
{
    @Override
    protected RegistryObject<TestProgressBlock> registerBlock()
    {
        return TestMod.INSTANCE.REGISTRY.getBlock(TypedRegKey.block("test_progress_block", TestProgressBlock.class));
    }

    @Override
    protected RegistryObject<TileEntityType<TestProgressTile>> registerTile()
    {
        return TestMod.INSTANCE.REGISTRY.getTile(TypedRegKey.tile("test_progress_tile", TestProgressTile.class));
    }

    @Override
    protected RegistryObject<ContainerType<TestProgressContainer>> registerContainer()
    {
        return TestMod.INSTANCE.REGISTRY.getContainer(TypedRegKey.container("test_progress_container", TestProgressContainer.class));
    }

    @Override
    protected ScreenManager.IScreenFactory<TestProgressContainer, TestProgressScreen> registerScreen()
    {
        return TestProgressScreen::new;
    }

    @Nullable
    @Override
    protected Function<? super TileEntityRendererDispatcher, TileEntityRenderer<TestProgressTile>> registerTer()
    {
        return null;
    }
}
