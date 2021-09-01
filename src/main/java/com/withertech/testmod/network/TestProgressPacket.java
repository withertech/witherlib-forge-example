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

package com.withertech.testmod.network;

import com.withertech.testmod.tiles.TestProgressTile;
import com.withertech.witherlib.network.PacketContext;
import com.withertech.witherlib.network.TileEntityBasePacket;
import net.minecraft.util.math.BlockPos;

public class TestProgressPacket extends TileEntityBasePacket<TestProgressTile>
{
    public TestProgressPacket()
    {

    }

    public TestProgressPacket(BlockPos tilePos)
    {
        super(tilePos);
    }

    @Override
    protected void handle(TestProgressTile tile, PacketContext context)
    {
        tile.toggleRunning();
        tile.dataChanged();
    }
}
