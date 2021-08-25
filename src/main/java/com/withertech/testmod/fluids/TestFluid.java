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

package com.withertech.testmod.fluids;

import com.withertech.testmod.TestMod;
import com.withertech.witherlib.WitherLib;
import com.withertech.witherlib.registration.TypedRegKey;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BucketItem;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public abstract class TestFluid extends ForgeFlowingFluid
{
    private static final Properties properties = new Properties(
            () -> TestMod.INSTANCE.REGISTRY.getFluid(TypedRegKey.fluid("test_fluid", Source.class)).get(),
            () -> TestMod.INSTANCE.REGISTRY.getFluid(TypedRegKey.fluid("test_fluid_flowing", Flowing.class)).get(),
            FluidAttributes.builder(
                    TestMod.INSTANCE.MOD.modLocation("block/test_fluid_still"),
                    TestMod.INSTANCE.MOD.modLocation("block/test_fluid_flow"))
                    .translationKey("block.testmod.test_fluid"))
            .bucket(() -> TestMod.INSTANCE.REGISTRY.getItem(TypedRegKey.item("test_fluid_bucket", BucketItem.class)).get())
            .block(() -> TestMod.INSTANCE.REGISTRY.getBlock(TypedRegKey.block("test_fluid", FlowingFluidBlock.class)).get())
            .canMultiply()
            .slopeFindDistance(4)
            .tickRate(5)
            .levelDecreasePerBlock(1)
            .explosionResistance(100F);


    protected TestFluid(Properties properties)
    {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(@Nonnull World world, @Nonnull BlockPos pos, FluidState state, @Nonnull Random random)
    {
        if (!state.isSource() && !state.getValue(FALLING))
        {
            if (random.nextInt(64) == 0)
            {
                world.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundCategory.BLOCKS, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);
            }
        }
        else if (random.nextInt(10) == 0)
        {
            world.addParticle(ParticleTypes.UNDERWATER, (double) pos.getX() + random.nextDouble(), (double) pos.getY() + random.nextDouble(), (double) pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
        }

    }

    @Override
    @Nullable
    @OnlyIn(Dist.CLIENT)
    public IParticleData getDripParticle()
    {
        return ParticleTypes.DRIPPING_WATER;
    }


    public static class Flowing extends ForgeFlowingFluid.Flowing
    {
        public Flowing()
        {
            super(properties);
        }
    }

    public static class Source extends ForgeFlowingFluid.Source
    {
        public Source()
        {
            super(properties);
        }
    }
}
