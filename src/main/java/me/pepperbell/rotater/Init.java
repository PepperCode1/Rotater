package me.pepperbell.rotater;

import me.pepperbell.rotater.block.RotaterBlock;
import me.pepperbell.rotater.block.RotaterBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Init {
	public static final Material ROTATER_MATERIAL = new FabricMaterialBuilder(MaterialColor.BROWN).lightPassesThrough().destroyedByPiston().notSolid().build();
	public static final Block ROTATER_BLOCK = Registry.register(Registry.BLOCK, new Identifier(RotaterCommon.MOD_ID, "rotater"), new RotaterBlock(AbstractBlock.Settings.of(ROTATER_MATERIAL).nonOpaque().breakInstantly().sounds(BlockSoundGroup.NETHER_WART)));
	public static final Item ROTATER_ITEM = Registry.register(Registry.ITEM, new Identifier(RotaterCommon.MOD_ID, "rotater"), new BlockItem(ROTATER_BLOCK, new Item.Settings()));
	public static final BlockEntityType<RotaterBlockEntity> ROTATER_BE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(RotaterCommon.MOD_ID, "rotater"), BlockEntityType.Builder.create(RotaterBlockEntity::create, ROTATER_BLOCK).build(null));

	public static void init() {
	}
}
