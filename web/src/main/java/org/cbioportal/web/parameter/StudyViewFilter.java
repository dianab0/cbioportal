package org.cbioportal.web.parameter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.cbioportal.model.AlterationFilter;
import org.cbioportal.model.GeneFilter;
import org.cbioportal.web.parameter.filter.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class StudyViewFilter implements Serializable {

    @Size(min = 1)
    private List<SampleIdentifier> sampleIdentifiers;
    @Size(min = 1)
    private List<String> studyIds;
    private List<ClinicalDataFilter> clinicalDataFilters;
    private List<GeneFilter> geneFilters;
    private AndedSampleTreatmentFilters sampleTreatmentFilters;
    private AndedSampleTreatmentFilters sampleTreatmentGroupFilters;
    private AndedPatientTreatmentFilters patientTreatmentFilters;
    private AndedPatientTreatmentFilters patientTreatmentGroupFilters;
	private List<List<String>> genomicProfiles;
    private List<GenomicDataFilter> genomicDataFilters;
    private List<GenericAssayDataFilter> genericAssayDataFilters;
    private List<List<String>> caseLists;
    private List<ClinicalDataFilter> customDataFilters;
    private AlterationFilter alterationFilter;
    
    @AssertTrue
    private boolean isEitherSampleIdentifiersOrStudyIdsPresent() {
        return sampleIdentifiers != null ^ studyIds != null;
    }

    @AssertTrue
    private boolean isEitherValueOrRangePresentInClinicalDataIntervalFilters() {
        return validateDataFilters(clinicalDataFilters);
    }

    @AssertTrue
    private boolean isEitherValueOrRangePresentInGenomicDataIntervalFilters() {
        return validateDataFilters(genomicDataFilters);
    }

    @AssertTrue
    private boolean isEitherValueOrRangePresentInGenericAssayDataIntervalFilters() {
        return validateDataFilters(genericAssayDataFilters);
    }

    @AssertTrue
    private boolean isEitherValueOrRangePresentInCustomDataFilters() {
        return validateDataFilters(customDataFilters);
    }

    private <T extends DataFilter> boolean validateDataFilters(List<T> dataFilters) {
        long invalidCount = 0;

        if (dataFilters != null) {
            invalidCount = dataFilters.stream()
                    .flatMap(f -> f.getValues().stream())
                    .filter(Objects::nonNull)
                    .filter(v -> v.getValue() != null == (v.getStart() != null || v.getEnd() != null))
                    .count();
        }

        return invalidCount == 0;
    }

    public List<SampleIdentifier> getSampleIdentifiers() {
        return sampleIdentifiers;
    }

    public void setSampleIdentifiers(List<SampleIdentifier> sampleIdentifiers) {
        this.sampleIdentifiers = sampleIdentifiers;
    }

    public List<String> getStudyIds() {
        return studyIds;
    }

    public void setStudyIds(List<String> studyIds) {
        this.studyIds = studyIds;
    }

    public List<ClinicalDataFilter> getClinicalDataFilters() {
        return clinicalDataFilters;
    }

    public void setClinicalDataFilters(List<ClinicalDataFilter> clinicalDataFilters) {
        this.clinicalDataFilters = clinicalDataFilters;
    }

    public List<GeneFilter> getGeneFilters() {
        return geneFilters;
    }

    public void setGeneFilters(List<GeneFilter> geneFilters) {
        this.geneFilters = geneFilters;
    }

    public List<List<String>> getGenomicProfiles() {
        return genomicProfiles;
    }

    public void setGenomicProfiles(List<List<String>> genomicProfiles) {
        this.genomicProfiles = genomicProfiles;
    }

    public List<GenomicDataFilter> getGenomicDataFilters() {
        return genomicDataFilters;
    }

    public void setGenomicDataFilters(List<GenomicDataFilter> genomicDataFilters) {
        this.genomicDataFilters = genomicDataFilters;
    }

    public AndedSampleTreatmentFilters getSampleTreatmentFilters() {
        return sampleTreatmentFilters;
    }

    public void setSampleTreatmentFilters(AndedSampleTreatmentFilters sampleTreatmentFilters) {
        this.sampleTreatmentFilters = sampleTreatmentFilters;
    }

    public AndedPatientTreatmentFilters getPatientTreatmentFilters() {
        return patientTreatmentFilters;
    }

    public void setPatientTreatmentFilters(AndedPatientTreatmentFilters patientTreatmentFilters) {
        this.patientTreatmentFilters = patientTreatmentFilters;
    }

    public List<List<String>> getCaseLists() {
        return caseLists;
    }

    public void setCaseLists(List<List<String>> caseLists) {
        this.caseLists = caseLists;
    }

	public List<GenericAssayDataFilter> getGenericAssayDataFilters() {
		return genericAssayDataFilters;
	}

	public void setGenericAssayDataFilters(List<GenericAssayDataFilter> genericAssayDataFilters) {
		this.genericAssayDataFilters = genericAssayDataFilters;
	}

    public List<ClinicalDataFilter> getCustomDataFilters() {
        return customDataFilters;
    }

    public void setCustomDataFilters(List<ClinicalDataFilter> customDataFilters) {
        this.customDataFilters = customDataFilters;
    }

    public AlterationFilter getAlterationFilter() {
        return alterationFilter;
    }

    public void setAlterationFilter(AlterationFilter alterationFilter) {
        this.alterationFilter = alterationFilter;
    }

    public AndedSampleTreatmentFilters getSampleTreatmentGroupFilters() {
        return sampleTreatmentGroupFilters;
    }

    public void setSampleTreatmentGroupFilters(AndedSampleTreatmentFilters sampleTreatmentGroupFilters) {
        this.sampleTreatmentGroupFilters = sampleTreatmentGroupFilters;
    }

    public AndedPatientTreatmentFilters getPatientTreatmentGroupFilters() {
        return patientTreatmentGroupFilters;
    }

    public void setPatientTreatmentGroupFilters(AndedPatientTreatmentFilters patientTreatmentGroupFilters) {
        this.patientTreatmentGroupFilters = patientTreatmentGroupFilters;
    }
}
