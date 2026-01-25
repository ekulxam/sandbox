package survivalblock.atmosphere.sandbox.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import survivalblock.atmosphere.sandbox.common.init.SandboxItems;

import java.util.concurrent.CompletableFuture;

public class SandboxEnUsLangGenerator extends FabricLanguageProvider {
    public SandboxEnUsLangGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add(SandboxItems.COMPONENT_UPDATE_TEST, "Component Update Test Item");
    }
}
