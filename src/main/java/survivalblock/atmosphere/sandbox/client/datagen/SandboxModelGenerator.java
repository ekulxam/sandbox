package survivalblock.atmosphere.sandbox.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import survivalblock.atmosphere.sandbox.common.init.SandboxItems;

public class SandboxModelGenerator extends FabricModelProvider {
    public SandboxModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(SandboxItems.COMPONENT_UPDATE_TEST, ModelTemplates.FLAT_HANDHELD_ITEM);
    }
}
