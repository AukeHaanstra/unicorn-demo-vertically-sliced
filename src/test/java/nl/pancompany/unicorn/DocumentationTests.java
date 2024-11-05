package nl.pancompany.unicorn;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.docs.Documenter.CanvasOptions;

class DocumentationTests {

    ApplicationModules modules = ApplicationModules.of(UnicornDemoApplication.class);

    @Test
    void writeC4ModuleComponentDiagramsAndAsciidocCanvasesAggregate() {
        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml()
                .writeModuleCanvases(CanvasOptions.defaults().revealInternals())
                .writeAggregatingDocument();
    }

}