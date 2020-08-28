package org.cbioportal.service;

import org.cbioportal.model.*;
import org.cbioportal.service.exception.MolecularProfileNotFoundException;

import java.util.List;
import java.util.Map;

public interface AlterationEnrichmentService {

    List<AlterationEnrichment> getAlterationEnrichments(
        Map<String, List<MolecularProfileCaseIdentifier>> molecularProfileCaseSets,
        List<MutationEventType> mutationEventTypes,
        List<CopyNumberAlterationEventType> cnaEventTypes,
        EnrichmentScope enrichmentScope,
        boolean searchFusions,
        boolean exludeVUS,
        List<String> selectedTiers,
        boolean excludeGermline)
        throws MolecularProfileNotFoundException;
}
