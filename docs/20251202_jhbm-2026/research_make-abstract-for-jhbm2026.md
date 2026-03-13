# Overview Analysis Report — 2025-12-14

## Order

http://localhost:8080/summary-view を参照し、
Dataset、N、RC Age、Sex、Modality、Origin、Disease、Findingsの列の値を参照し、全体的な傾向を調査し、レポートを返してください。 ./doc/overview_analysis_report_20251214.md へ出力してください。

## Total files

Total Human JSON files analyzed: 123

## Dataset
Unique entries: 196; Top 15:
- 17x: UK Biobank
- 15x: Cam-CAN
- 10x: HCP Young Adult
- 9x: OASIS-3
- 8x: HCP Development
- 8x: PNC
- 8x: HCP
- 7x: EU-AIMS LEAP
- 7x: ABIDE II
- 6x: IXI
- 6x: HCP Aging
- 6x: TOP
- 5x: REST-meta-MDD
- 5x: HCP Early Psychosis
- 5x: NKI-Rockland

## N (sample size)
Records: 127; Parsed N values: 127
- min/25%/median/75%/max: 43/326/822/7728/101457
- mean: 8931.85

## RC Age
Records (lines): 137
- mean available: 91; sd available: 90; min available: 95; max available: 93
- mean (years) — min/25%/median/75%/max: 2.0/17.06/33.9/46.9/92.0 (mean 32.86)
- sd (years) — min/25%/median/75%/max: 0.62/5.63/9.375/18.5/24.55 (mean 11.62)
- min/max (years) — min: 0.0, median: 6.0, max: 100100.0

## Sex (female percentage)
Records (lines): 125; Parsed female %: 105
- female% — min/25%/median/75%/max: 0.0/45.3/51.0/54.0/63.1 (mean 46.81)

## Modality (category frequency)
- 96x: T1w MRI
- 35x: Others
- 29x: fMRI
- 9x: dMRI
- 7x: T2w MRI
- 5x: PET
- 5x: EEG
- 1x: MEG

## Origin (nm1_model_origin)
- 95x: New
- 25x: Pre-trained
- 2x: Yes

## Disease (CAA2; top 20)
- 36x: Schizophrenia (SCZ)
- 27x: Autism Spectrum Disorder (ASD)
- 19x: Major Depressive Disorder (MDD)
- 19x: Alzheimer's Disease (AD)
- 18x: Bipolar Disorder (BD)
- 13x: Attention-Deficit/Hyperactivity Disorder (ADHD)
- 13x: Mild Cognitive Impairment (MCI)
- 5x: First-Episode Psychosis (FEP)
- 5x: Obsessive-Compulsive Disorder (OCD)
- 3x: Early Psychosis (EP)
- 3x: Parkinson's Disease (PD)
- 3x: Parkinson's disease (PD)
- 3x: Autism spectrum disorder (ASD)
- 2x: Schizoaffective Disorder (SAD)
- 2x: Diffuse Intrinsic Pontine Glioma (DIPG)
- 2x: Multiple Sclerosis (MS)
- 2x: Clinical high risk for psychosis (CHR-P)
- 1x: Refractory Epilepsy ()
- 1x: 22q11.2 Deletion Syndrome (22qDel)
- 1x: Cannabis Use Disorder (CUD)

## Findings keywords (CAA8 brief; top 25)
- 84x: deviations
- 40x: normative
- 35x: deviation
- 33x: patterns
- 31x: show
- 29x: shows
- 26x: more
- 25x: showed
- 24x: asd
- 23x: cortical
- 19x: regions
- 18x: two
- 18x: patients
- 17x: heterogeneous
- 17x: differences
- 16x: subtypes
- 16x: gmv
- 16x: greater
- 15x: but
- 15x: regional
- 14x: distinct
- 14x: negative
- 13x: significant
- 12x: increased
- 12x: extreme

