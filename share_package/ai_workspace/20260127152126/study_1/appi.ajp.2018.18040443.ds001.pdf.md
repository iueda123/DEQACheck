Data Supplement for Jalbrzikowski et al., Age-Associated Deviations of Amygdala Functional Connectivity in Youths With Psychosis Spectrum Disorders: Relevance to Psychotic Symptoms. Am J Psychiatry (doi: 10.1176/appi.ajp.2018.18040443)

## Supplemental Methods

## Participants

Luna 1 and Luna 2

For Luna 1 and Luna 2, participants and their first-degree relatives did not have a psychiatric disorder determined by phone screen and a clinical questionnaire (1). Exclusion criteria for all participants included: medical illness affecting the central nervous system function, IQ (determined using the Reynolds Intellectual Assessment Scale [2]) lower than 80, a firstdegree relative with a major psychiatric disorder, or any MRI contraindications.

## PNC

Data for the Philadelphia Neurodevelopmental Cohort (PNC) was obtained through the Database of Genotypes and Phenotypes platform (Beatriz Luna, #43787-2). The PNC is a population sample consisting of 9498 youth (ages 9-22 years) who participated in neurocognitive and genetic assessment after providing writing informed consent or assent with parental consent (youth under 18 years old [3]). A subset of these youth (N=997) also underwent neuroimaging measures (4). Psychopathology was assessed using a computerized, structured interview (GOASSESS [3, 5]), which is based on the Kiddie Schedule for Affective Disorders and Schizophrenia for School-Age Children Present and Lifetime (KSADS-PL [6]). Categorical and dimensional measures of psychosis were created from clinical symptom responses to GOASSESS, the Structured Interview for Prodromal Syndromes (SIPS [7]), and a 12-item PRIME Screen-Revised questionnaire (PS-R [8]). Categorical psychosis spectrum group was defined as 1) a score that is two standard deviations or greater than age-matched

peers on the SIPS or PS-R, 2) definite or possible hallucinations or delusions reported on the responses to psychosis items in GOASSESS, or 3) a minimum of 1 PS-R item rated 6 (definitely agree) or at least 3 items rated 5 (somewhat agree); this definition is consistent with previous PNC publications (5, 9, 10).

To test specificity of psychosis abnormalities, another group of participants who met DSM-IV criteria for non-psychotic psychopathology was created. We used responses to questions on the GOASSES to determine DSM-IV diagnosis ranking. Similar to previous PNC publications (3), psychopathology was considered to be significant if symptoms endorsed were consisted with frequency and duration of a DSM-IV psychiatric disorder, while correspondingly accompanied by significant distress or impairment (a rating of &gt;5 on a scale of 0-10). Pitt

The Pitt sample was recruited from an ongoing Conte Center study examining neurobiological mechanisms of working memory deficits in first episode psychosis (FEP). Exclusion criteria for all participants included: medical illness affecting the central nervous system function, IQ (determined using the Wechsler Abbreviated Scale of Intelligence [11], lower than 75, or any MRI contraindications. Inclusion criteria for FEP were as follows: experiencing one's first psychotic episode and seeking help for his/her psychotic symptoms for the first time and antipsychotic naive or prescribed antipsychotic treatment for less than two months. Diagnoses were determined using all available clinical information and data gathered from a Structured Clinical Interview for DSM-IV (SCID [12]) conducted with a trained clinician. Experienced diagnostician/clinical researchers confirmed diagnoses at consensus meetings. None of the patients met criteria for a DSM-IV substance abuse disorder currently or within the previous 6 months. The inclusion criteria for controls in the Pitt sample were no lifetime history of a major psychiatric disorder or antipsychotic treatment, no first-degree family member with a history of a psychotic disorder, and no significant neurological disorder or head injury or mental retardation as defined by the DSM-IV.

## rsfMRI Processing

The first 4 TRs from all scans were removed to allow for BOLD signal normalization. Functional images were warped into MNI standard space using a series of affine and nonlinear transforms. Normalization based on global mode was then calculated on the functional images. Next all functional images were spatially smoothed using a 5-mm full width at half maximum Gaussian kernel. Removal of non-stationary events in the fMRI time series was conducted using Wavelet Despiking (13). To better control nuisance-related variability (14) we then conducted simultaneous multiple regression of nuisance variables and bandpass filtering at 0.009 Hz  &lt; f &lt; 0.08. Nuisance regressors included were non-brain tissue (NBT), average white matter signal, average ventricular signal, six head realignment parameters obtained by rigid body head motion correction, and the derivatives of these measures. NBT, average white matter, and average ventricular signal nuisance regressors were created using Freesurfer's automated segmentation program (15) and extracted from each participant's MPRAGE scan. ICA-Aroma was implemented to remove motion artifacts (16, 17). We then removed any remaining high motion volumes via scrubbing procedure. For all subjects, we calculated two quality control measures with respect to head motion: volume-to-volume frame displacement, (FD) and the RMS derivative of fMRI time series (DVARS). We censored and removed volumes that had an FD &gt; 0.3 mm and/or DVARS &gt; 20 (computed after wavelet despiking). By implementing wavelet despiking prior to scrubbing, we were able to use most of the time series data to provide a more reliable estimate of the true correlation. However, because motion is such a critical issue in developmental studies and there were some remaining DVARS values over the identified threshold, after wavelet despiking, these volumes were censored as extra validation to ensure that motion was not contaminating our signal. Subjects were dropped from rsfMRI analyses if more than 20% of their volumes were removed.

## Regions of Interest

Centromedial (CM) and basolateral (BL) regions of interest (ROIs) are available in FSL's Juelich histological atlas (18) and have been used in previous studies examining amygdala rsfMRI connectivity (19-21). Because the FSL atlas has a slight bias for its MNI template (22), we first used FNIRT to warp the Jeulich atlas to the standard MNI template space. Voxels with at least a 50% probability of belonging in one of these subregions were included in each ROI and each voxel was only assigned to one subregion.

## I mplementation of AFNI's 3dClustSim

Analysis was masked to only include voxels with a 50% or greater probability of being grey matter in the MNI-152 template. Results were corrected for multiple comparisons using a combination of cluster size and voxel probability, with parameters determined through a Monte Carlo simulation using AFNI's 3dClustSim program on randomly generated data within the grey matter mask with the same smoothness as the group mean smoothness estimated from first-level residuals for each subregion. This analysis specified that a cluster of 30 contiguous voxels with a single voxel threshold of p &lt;.001 are required to achieve a clusterwise corrected p &lt;.05.

## Supplemental Figures

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000000_9db4fbd70bbc564013002f703179c7816e68682713fe9094583f34a9d2d2a71a.png)

FIGURE S2. Nineteen clusters exhibited developmental decreases in connectivity with the centromedial amygdala. One cluster that exhibited developmental decreases in connectivity with the basolateral amygdala.

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000001_30cbf231dcc2d31980d1b661da112d27c2a367078c0750ae3781e971af35c07e.png)

FIGURE S3. For each amygdala subregion connectivity measure that exhibited a significant developmental change in typically developing youth, we plotted the ageassociated line of best fit in each protocol (Luna 1, Luna 2, PNC, and Pitt). The pattern of age-associated change is remarkably consistent across different samples.

site

Buna

Luna

2

Pat nc

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000002_ff0d1d235c628ed4a92af48217a0ce07dce246c176d1df70e4a94acacad0e104.png)

.

‘Age

(years)

‘Age

(years)

FIGURE S4. After regressing out protocol as a covariate, we plotted the residuals in each protocol separately. In all 20 regions that exhibited age associated changes, the residuals cluster around a mean of zero and do not significantly differ from each other in each protocol.  This suggests that we adequately accounted for site in our analyses.

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000003_f86aab3fce9622a05ce69fc6952ee149db3c998f79400de3c4f13bd389bf9294.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000004_c382499bedd337368f0a20baca7cf0cb6f107dfb2ce0f1932002dd324c3f4194.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000005_6a8132793a21f7c698d0d9b5b134b0c354483974be5e18bb4216fa3cd228c789.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000006_96860ca9627e567e8f8745d29a51cb02b44582c45cd10a15334070857638ab1d.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000007_c692d0db73f77d52cef9e610ac14fa27d3952c76bed822518d2f8ee3bffbfcc0.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000008_a71e45adec4b9972a4a473e3a38045fb3ccd824b63e0244a2a4fea049af19611.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000009_53a9b750bb58a80cde53456c9b299d7d4e8c7be4bd7a39cbf6df49246c318721.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000010_baabf50a11b78a933c25a0d046861fb9f28759510da6bc2ec8343b27cef10a24.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000011_5fbb4abf7c1fc4de2c35b55848193562cd53bd5ec85ed986f84ef062a1879061.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000012_ae062e44cbf78330e0a1af67bac045a18dd38fc00be71026063f8ab6880dd962.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000013_37fdd784238bc89f79c4ec6926ccd824e3a01f5127c676f3390ea3ffe6277a9c.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000014_be6ab825828351bb499ea1ef82c0a1f8e376b4ca659e8fa5575104d4e85c7908.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000015_b8b8be690154a0e2309e302582e87470f31ba0657ba35a9f1d61896826cd276e.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000016_1e18a6a4cf0da4e2a66d6adc8fd25baf998dd5c767b02d242a928b4bf0c08b7e.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000017_26355cc8272d0324f085bc75c089e1d727178174253f27a6abf22860feb89622.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000018_66023a2836fd5410e46dbc5229e64eb49640ff7fb856aef02de89c1319d013db.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000019_ef4ef4dbc637027eaa32e0fd52a28a8df810d641966301fea5484523210c2057.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000020_6bf9069748edc296b415eadd9b9f307750726c533ae74103a5d1a720c21213c3.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000021_28df3275518e0c77fc4d386e140ba7acc4cddd54218bafee6834c77a1598daff.png)

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000022_8b272ca045d0db0415cf85d101db4bd9f452795972a6e8e6c8e43f4f25729d00.png)

FIGURE S5. Like controls (blue), youth with other psychopathology (grey) showed significant age-related decreases with increasing age in connectivity between the following regions: CM amygdala-dorsolateral prefrontal cortex, CM amygdala-putamen, CM amygdala-caudate, and CM amygdala-occipital cortex. Like psychosis spectrum youth (red), the other psychopathology group failed to show age-associated changes in CM amygdala-ventrolateral prefrontal cortex connectivity, and CM amygdala-thalamus connectivity.

![Image](./appi.ajp.2018.18040443.ds001_artifacts/image_000023_f60dfb4fcef5c1bec1433a05882ec8b22ac7262d82d342d7340ff963abaac1d9.png)

## Supplemental Tables

TABLE S1. Responses to the following SIPS/PRIME Screen-Revised questionnaire were summed as a dimensional measure of A) positive and B) negative symptoms. For positive symptoms, responses were rated on a Likert scale (0=definitely disagree, 1=somewhat disagree, 2=slightly disagree, 3=not sure, 4=slightly agree, 5=somewhat agree, 6=definitely agree).

| A. Positive Symptoms   | A. Positive Symptoms                                                                                                                         |
|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| SIP003                 | I think that I have felt that there are odd or unusual things going on that I can't  explain.                                                |
| SIP004                 | I think that I might be able to predict the future.                                                                                          |
| SIP005                 | I may have felt that there could possibly be something interrupting or controlling  my thoughts, feelings, or actions.                       |
| SIP006                 | I have had the experience of doing something differently because of my  superstitions.                                                       |
| SIP007                 | I think I may get confused at times whether something I experience or perceive  may be real or may be just part of my imagination or dreams. |
| SIP008                 | I have thought that it might be possible that other people can read my mind, or that  I can read others' minds                               |
| SIP009                 | I wonder if people may be planning to hurt me or even may be about to hurt me.                                                               |
| SIP010                 | I believe that I have special natural or supernatural gifts beyond my talents and  natural strengths.                                        |
| SIP011                 | I think I might feel like my mind is "playing tricks" on me.                                                                                 |
| SIP012                 | I have had the experience of hearing faint or clear sounds of people or a person  mumbling or talking when there is no one near me.          |
| SIP013                 | I think that I may hear my own thoughts being said out loud.                                                                                 |
| SIP014                 | I have been concerned that I might be "going crazy."                                                                                         |
| B. Negative Symptoms   | B. Negative Symptoms                                                                                                                         |
| SIP001                 | Trouble with focus and attention severity                                                                                                    |
| SIP035                 | Changes in perception of self, others, or the world in general severity                                                                      |
| SIP037                 | Expression of emotion severity                                                                                                               |
| SIP041                 | Occupational functioning severity                                                                                                            |
| SIP043                 | Avolition severity                                                                                                                           |

TABLE S2. Resting state and structural scan sequences for each cohort.

|                            | LUNA 1                            | LUNA 2                                                | PNC                                                   | Pitt                                                 |
|----------------------------|-----------------------------------|-------------------------------------------------------|-------------------------------------------------------|------------------------------------------------------|
| head coil (# of  channels) | 12                                | 32                                                    | 32                                                    | 32                                                   |
| rsfMRI parameters          | rsfMRI parameters                 | rsfMRI parameters                                     | rsfMRI parameters                                     | rsfMRI parameters                                    |
| Instructions               | Eyes closed,  stay awake &  still | Eyes open, stay  awake & still,  fixate on  crosshair | Eyes open, stay  awake & still,  fixate on  crosshair | Eyes open, stay  awake & still,  fixate on crosshair |
| Acquisition time (s)       | 300 s                             | 360s                                                  | 378 s                                                 | 360s                                                 |
| TR/TE (ms)                 | 1500/29 ms                        | 1000/30 ms                                            | 3000/32 ms                                            | 1000/30                                              |
| Flip angle (°)             | 70 °                              | 50°                                                   | 90 °                                                  | 55°                                                  |
| Voxel size (mm)            | 3 mm                              | 2.3 mm                                                | 3 mm                                                  | 2.3 mm                                               |
| gradient echo field  map   | no                                | yes                                                   | no                                                    | yes                                                  |
| MPRAGE parameters          | MPRAGE parameters                 | MPRAGE parameters                                     | MPRAGE parameters                                     | MPRAGE parameters                                    |
| Acquisition time (s)       | 435                               | 424                                                   | 208                                                   | 362                                                  |
| TI (ms)                    | 800                               | 1000                                                  | 1100                                                  | 1260                                                 |
| TR/TE (ms)                 | 1570/3.4                          | 2200/3.5                                              | 1810/3.5                                              | 2530/1.7/3.6/5.46/ 7.3                               |
| Flip angle (°)             | 8                                 | 9                                                     | 9                                                     | 7                                                    |
| Voxel size (mm)            | 1 mm                              | 1 mm                                                  | 1 mm                                                  | 1 mm                                                 |

TABLE S3. All significant age effects remained with inclusion average framewise displacement as an additional covariate.

| cluster                                   | predictor                          | χ2                                 | df                                 | p                                  |
|-------------------------------------------|------------------------------------|------------------------------------|------------------------------------|------------------------------------|
| Centromedial amygdala connectivity        | Centromedial amygdala connectivity | Centromedial amygdala connectivity | Centromedial amygdala connectivity | Centromedial amygdala connectivity |
| L posterior  cingulate/precuneus          | inverse age                        | 25.7                               | 1                                  | 3.90E-07                           |
| L posterior  cingulate/precuneus          | site                               | 1.7                                | 3                                  | 0.64                               |
| L posterior  cingulate/precuneus          | sex                                | 0.4                                | 1                                  | 0.51                               |
| L posterior  cingulate/precuneus          | average framewise displacement     | 5.0                                | 1                                  | 0.03                               |
| R posterior  cingulate/precuneus          | inverse age                        | 22.4                               | 1                                  | 2.16E-06                           |
| R posterior  cingulate/precuneus          | site                               | 5.4                                | 3                                  | 0.14                               |
| R posterior  cingulate/precuneus          | sex                                | 1.4                                | 1                                  | 0.23                               |
| R posterior  cingulate/precuneus          | average framewise displacement     | 4.4                                | 1                                  | 0.04                               |
| L FEF/BA 6 &  Precentral gyrus            | inverse age                        | 23.3                               | 1                                  | 1.37E-06                           |
| L FEF/BA 6 &  Precentral gyrus            | site                               | 19.2                               | 3                                  | 2.49E-04                           |
| L FEF/BA 6 &  Precentral gyrus            | sex                                | 0.3                                | 1                                  | 0.58                               |
| L FEF/BA 6 &  Precentral gyrus            | average framewise displacement     | 0.9                                | 1                                  | 0.34                               |
| R FEF/BA Precentral  gyrus                | inverse age                        | 24.9                               | 1                                  | 6.06E-07                           |
| R FEF/BA Precentral  gyrus                | site                               | 15.3                               | 3                                  | 1.56E-03                           |
| R FEF/BA Precentral  gyrus                | sex                                | 0.0                                | 1                                  | 0.83                               |
| R FEF/BA Precentral  gyrus                | average framewise displacement     | 1.7                                | 1                                  | 0.19                               |
| R insula/claustrum                        | inverse age                        | 26.5                               | 1                                  | 2.66E-07                           |
| R insula/claustrum                        | site                               | 62.5                               | 3                                  | 1.72E-13                           |
| R insula/claustrum                        | sex                                | 1.3                                | 1                                  | 0.25                               |
| R insula/claustrum                        | average framewise displacement     | 23.1                               | 1                                  | 1.55E-06                           |
| L insula/claustrum                        | inverse age                        | 31.2                               | 1                                  | 2.36E-08                           |
| L insula/claustrum                        | site                               | 98.9                               | 3                                  | 2.68E-21                           |
| L insula/claustrum                        | sex                                | 1.3                                | 1                                  | 0.26                               |
| L insula/claustrum                        | average framewise displacement     | 13.6                               | 1                                  | 2.31E-04                           |
| L parietal  cortex/middle  temporal gyrus | inverse age                        | 21.6                               | 1                                  | 3.29E-06                           |
| L parietal  cortex/middle  temporal gyrus | site                               | 6.0                                | 3                                  | 0.11                               |
| L parietal  cortex/middle  temporal gyrus | sex                                | 0.0                                | 1                                  | 0.94                               |
| L parietal  cortex/middle  temporal gyrus | average framewise displacement     | 0.9                                | 1                                  | 0.33                               |
| R parahippocampal  gyrus                  | inverse age                        | 34.6                               | 1                                  | 4.06E-09                           |
| R parahippocampal  gyrus                  | site                               | 9.0                                | 3                                  | 0.03                               |
| R parahippocampal  gyrus                  | sex                                | 0.3                                | 1                                  | 0.60                               |
| R parahippocampal  gyrus                  | average framewise displacement     | 12.4                               | 1                                  | 4.36E-04                           |

(Continued)

|                                        | inverse age                     |   25.8 |   1 |   3.76E-07 |
|----------------------------------------|---------------------------------|--------|-----|------------|
|                                        | site                            |    1.7 |   3 |   0.63     |
|                                        | sex                             |    2   |   1 |   0.16     |
|                                        | average framewise displacement  |   11.1 |   1 |   0.000869 |
| precentral/postcentral                 | inverse age                     |   16.1 |   1 |   5.97e-05 |
| precentral/postcentral                 | site9                           |    6.7 |   3 |   0.08     |
| precentral/postcentral                 | sex9                            |    0.8 |   1 |   0.36     |
| precentral/postcentral                 | average framewise displacement9 |    1.6 |   1 |   0.2      |
| L ventrolateral  prefrontal cortex     | inverse age                     |   24.6 |   1 |   6.95e-07 |
| L ventrolateral  prefrontal cortex     | site0                           |    2.9 |   3 |   0.41     |
| L ventrolateral  prefrontal cortex     | sex0                            |    0.3 |   1 |   0.61     |
|                                        | average framewise displacement0 |    8.9 |   1 |   0.00292  |
| L putamen                              | inverse age                     |   23.1 |   1 |   1.56e-06 |
| L putamen                              | site                            |  160.8 |   3 |   1.24e-34 |
| L putamen                              | sex                             |    0.3 |   1 |   0.57     |
| L putamen                              | average framewise displacement  |    9.5 |   1 |   0.0021   |
| L BA 10/superior  frontal gyrus        | inverse age                     |   23.8 |   1 |   1.09e-06 |
| L BA 10/superior  frontal gyrus        | site                            |   33.9 |   3 |   2.03e-07 |
| L BA 10/superior  frontal gyrus        | sex                             |    0.7 |   1 |   0.39     |
| L BA 10/superior  frontal gyrus        | average framewise displacement  |    9.3 |   1 |   0.00231  |
| R thalamus                             | inverse age                     |   29.7 |   1 |   4.96e-08 |
| R thalamus                             | site                            |    4   |   3 |   0.27     |
| R thalamus                             | sex                             |    0.8 |   1 |   0.36     |
| R thalamus                             | average framewise displacement  |    0.9 |   1 |   0.35     |
|                                        | inverse age                     |   20.6 |   1 |   5.62e-06 |
|                                        | site                            |   16.2 |   3 |   0.00103  |
|                                        | sex                             |    0.4 |   1 |   0.52     |
|                                        | average framewise displacement  |   13.6 |   1 |   0.000231 |
| L caudate                              | inverse age                     |   20.7 |   1 |   5.46e-06 |
| L caudate                              | site                            |   44.6 |   3 |   1.13e-09 |
| L caudate                              | sex                             |    5.1 |   1 |   0.02     |
| L caudate                              | average framewise displacement  |    9.8 |   1 |   0.00175  |
| L dorsolateral  prefrontal cortex/BA 9 | inverse age                     |   24   |   1 |   9.82e-07 |
| L dorsolateral  prefrontal cortex/BA 9 | site                            |    8.4 |   3 |   0.04     |
| L dorsolateral  prefrontal cortex/BA 9 | sex                             |    0   |   1 |   0.92     |
|                                        | average framewise displacement  |    3.8 |   1 |   0.05     |

(Continued)

|                                   | inverse age                       | 18.9                              | 1                                 | 1.38E-05                          |
|-----------------------------------|-----------------------------------|-----------------------------------|-----------------------------------|-----------------------------------|
|                                   | site                              | 12.5                              | 3                                 | 0.01                              |
|                                   | sex                               | 2.5                               | 1                                 | 0.12                              |
|                                   | average framewise displacement    | 7.3                               | 1                                 | 0.01                              |
| R middle occipital                | inverse age                       | 13.0                              | 1                                 | 3.15E-04                          |
| R middle occipital                | site                              | 1.6                               | 3                                 | 0.66                              |
| R middle occipital                | sex                               | 1.8                               | 1                                 | 0.19                              |
| R middle occipital                | average framewise displacement    | 4.9                               | 1                                 | 0.03                              |
| Basolateral amygdala connectivity | Basolateral amygdala connectivity | Basolateral amygdala connectivity | Basolateral amygdala connectivity | Basolateral amygdala connectivity |
|                                   | inverse age                       | 22.4                              | 1                                 | 2.50E-06                          |
|                                   | site                              | 18.7                              | 3                                 | 3.20E-04                          |
|                                   | sex                               | 2.3                               | 1                                 | 0.13                              |
|                                   | average framewise displacement    | 5.1                               | 1                                 | 0.02                              |

TABLE S4. All significant age effects remained when the highest motion subjects (top 25%, &gt;0.17) were removed from the analysis.

| cluster                            | predictor                          | χ2                                 | df                                 | p                                  |
|------------------------------------|------------------------------------|------------------------------------|------------------------------------|------------------------------------|
| Centromedial amygdala connectivity | Centromedial amygdala connectivity | Centromedial amygdala connectivity | Centromedial amygdala connectivity | Centromedial amygdala connectivity |
| L posterior  cingulate/precuneus   | inverse age                        | 16.2                               | 1                                  | 5.84E-05                           |
| L posterior  cingulate/precuneus   | site                               | 2.4                                | 3                                  | 0.49                               |
| L posterior  cingulate/precuneus   | sex                                | 0.3                                | 1                                  | 0.60                               |
| L posterior  cingulate/precuneus   | average framewise displacement     | 1.8                                | 1                                  | 0.18                               |
| R posterior  cingulate/precuneus   | inverse age                        | 16.1                               | 1                                  | 6.11E-05                           |
| R posterior  cingulate/precuneus   | site                               | 5.8                                | 3                                  | 0.12                               |
| R posterior  cingulate/precuneus   | sex                                | 1.3                                | 1                                  | 0.26                               |
| R posterior  cingulate/precuneus   | average framewise displacement     | 2.1                                | 1                                  | 0.15                               |
| L FEF/BA 6 &  Precentral gyrus     | inverse age                        | 14.3                               | 1                                  | 1.56E-04                           |
| L FEF/BA 6 &  Precentral gyrus     | site                               | 17.9                               | 3                                  | 4.63E-04                           |
| L FEF/BA 6 &  Precentral gyrus     | sex                                | 1.0                                | 1                                  | 0.32                               |
| L FEF/BA 6 &  Precentral gyrus     | average framewise displacement     | 0.9                                | 1                                  | 0.34                               |
| R FEF/BA Precentral  gyrus         | inverse age                        | 17.6                               | 1                                  | 2.66E-05                           |
| R FEF/BA Precentral  gyrus         | site                               | 13.0                               | 3                                  | 4.73E-03                           |
| R FEF/BA Precentral  gyrus         | sex                                | 0.9                                | 1                                  | 0.33                               |
| R FEF/BA Precentral  gyrus         | average framewise displacement     | 1.3                                | 1                                  | 0.26                               |

(Continued)

|                                    | inverse age                     |   20.7 |   1 |   5.25E-06 |
|------------------------------------|---------------------------------|--------|-----|------------|
|                                    | site                            |   48   |   3 |   2.12e-10 |
|                                    | sex                             |    2   |   1 |   0.16     |
|                                    | average framewise displacement  |    4.2 |   1 |   0.04     |
| L insula/claustrum                 | inverse age                     |   19.8 |   1 |   8.6e-06  |
| L insula/claustrum                 | site                            |   69.8 |   3 |   4.69e-15 |
| L insula/claustrum                 | sex                             |    1.5 |   1 |   0.23     |
| L insula/claustrum                 | average framewise displacement  |    7.7 |   1 |   0.01     |
| cortex/middle  temporal gyrus      | inverse age                     |   14.7 |   1 |   0.000124 |
| cortex/middle  temporal gyrus      | site                            |    2.4 |   3 |   0.49     |
| cortex/middle  temporal gyrus      | sex                             |    0.5 |   1 |   0.5      |
|                                    | average framewise displacement  |    7.2 |   1 |   0.01     |
| R parahippocampal                  | inverse age                     |   26   |   1 |   3.48e-07 |
| R parahippocampal                  | site                            |   10.6 |   3 |   0.01     |
| R parahippocampal                  | sex                             |    0   |   1 |   0.98     |
| R parahippocampal                  | average framewise displacement  |    7.5 |   1 |   0.01     |
| L parahippocampal                  | inverse age                     |   14.2 |   1 |   0.000167 |
| L parahippocampal                  | site                            |    5.3 |   3 |   0.15     |
| L parahippocampal                  | sex                             |    0   |   1 |   0.96     |
| L parahippocampal                  | average framewise displacement  |    8   |   1 |   0.00475  |
| precentral/postcentral  gyrus      | inverse age                     |   12.2 |   1 |   0.000479 |
| precentral/postcentral  gyrus      | site9                           |    7.1 |   3 |   0.07     |
| precentral/postcentral  gyrus      | sex9                            |    0.5 |   1 |   0.47     |
| precentral/postcentral  gyrus      | average framewise displacement9 |    2.8 |   1 |   0.1      |
| L ventrolateral  prefrontal cortex | inverse age                     |   15.9 |   1 |   6.51e-05 |
| L ventrolateral  prefrontal cortex | site0                           |    5.9 |   3 |   0.11     |
| L ventrolateral  prefrontal cortex | sex0                            |    0.3 |   1 |   0.61     |
| L ventrolateral  prefrontal cortex | average framewise displacement0 |    4   |   1 |   0.05     |
| L putamen                          | inverse age                     |   16.9 |   1 |   3.97e-05 |
| L putamen                          | site                            |  127.4 |   3 |   1.98e-27 |
| L putamen                          | sex                             |    1   |   1 |   0.32     |
| L putamen                          | average framewise displacement  |   10.6 |   1 |   0.00115  |
| L BA 10/superior  frontal gyrus    | inverse age                     |   21   |   1 |   4.6e-06  |
| L BA 10/superior  frontal gyrus    | site                            |   36.5 |   3 |   5.95e-08 |
| L BA 10/superior  frontal gyrus    | sex                             |    0.7 |   1 |   0.39     |
|                                    | average framewise displacement  |    7.6 |   1 |   0.01     |

(Continued)

|                                        | inverse age                       | 18.2                              | 1                                 | 2.03E-05                          |
|----------------------------------------|-----------------------------------|-----------------------------------|-----------------------------------|-----------------------------------|
|                                        | site                              | 1.2                               | 3                                 | 0.75                              |
|                                        | sex                               | 0.3                               | 1                                 | 0.59                              |
|                                        | average framewise displacement    | 1.8                               | 1                                 | 0.18                              |
|                                        | inverse age                       | 16.7                              | 1                                 | 4.36E-05                          |
|                                        | site                              | 14.1                              | 3                                 | 2.79E-03                          |
|                                        | sex                               | 0.2                               | 1                                 | 0.66                              |
|                                        | average framewise displacement    | 7.0                               | 1                                 | 0.01                              |
|                                        | inverse age                       | 18.6                              | 1                                 | 1.58E-05                          |
|                                        | site                              | 36.9                              | 3                                 | 4.77E-08                          |
|                                        | sex                               | 3.7                               | 1                                 | 0.06                              |
|                                        | average framewise displacement    | 21.2                              | 1                                 | 4.16E-06                          |
| L dorsolateral  prefrontal cortex/BA 9 | inverse age                       | 10.4                              | 1                                 | 1.26E-03                          |
| L dorsolateral  prefrontal cortex/BA 9 | site                              | 5.9                               | 3                                 | 0.11                              |
| L dorsolateral  prefrontal cortex/BA 9 | sex                               | 0.1                               | 1                                 | 0.80                              |
| L dorsolateral  prefrontal cortex/BA 9 | average framewise displacement    | 3.8                               | 1                                 | 0.05                              |
| L parahippocampal                      | inverse age                       | 12.1                              | 1                                 | 4.92E-04                          |
| L parahippocampal                      | site                              | 5.8                               | 3                                 | 0.12                              |
| L parahippocampal                      | sex                               | 3.3                               | 1                                 | 0.07                              |
| L parahippocampal                      | average framewise displacement    | 6.8                               | 1                                 | 0.01                              |
| R middle occipital                     | inverse age                       | 6.6                               | 1                                 | 0.01                              |
| R middle occipital                     | site                              | 3.5                               | 3                                 | 0.32                              |
| R middle occipital                     | sex                               | 0.9                               | 1                                 | 0.34                              |
| R middle occipital                     | average framewise displacement    | 5.0                               | 1                                 | 0.03                              |
| Basolateral amygdala connectivity      | Basolateral amygdala connectivity | Basolateral amygdala connectivity | Basolateral amygdala connectivity | Basolateral amygdala connectivity |
| L uncus                                | inverse age                       | 18.6                              | 1                                 | 1.70E-05                          |
| L uncus                                | site                              | 21.8                              | 3                                 | 6.90E-05                          |
| L uncus                                | sex                               | 2.3                               | 1                                 | 0.13                              |
| L uncus                                | average framewise displacement    | 4.3                               | 1                                 | 0.04                              |

TABLE S5. Youth with psychosis spectrum exhibited lower connectivity in comparison to controls during late childhood for the following clusters: CM amygdala-ventrolateral prefrontal cortex, CM amygdala-putamen, CM amygdala-thalamus, CM amygdalacaudate, CM amygdala-occipital cortex. Youth with psychosis spectrum exhibited lower connectivity in comparison to other psychopathology during late childhood for the following clusters: CM amygdala-putamen and CM amygdala-occipital cortex. Youth with psychosis spectrum exhibited increased connectivity in comparison to controls during adulthood in the following clusters: CM amygdala-ventrolateral prefrontal cortex, CM amygdala-putamen, CM amygdala-caudate, and CM amygdala-occipital cortex.

| Amygdala connectivity  measure   | Psychosis spectrum vs.  Typically Developing   | Psychosis spectrum vs.  Typically Developing   | Psychosis spectrum vs.  Other Psychopathology   | Psychosis spectrum vs.  Other Psychopathology   | Other Psychopathology vs.  Typically Developing   | Other Psychopathology vs.  Typically Developing   |
|----------------------------------|------------------------------------------------|------------------------------------------------|-------------------------------------------------|-------------------------------------------------|---------------------------------------------------|---------------------------------------------------|
| Amygdala connectivity  measure   | ↓ connectivity  in psychosis  spectrum         | ↑ connectivity in  psychosis  spectrum         | ↓ connectivity  in psychosis  spectrum          | ↑ connectivity  in psychosis  spectrum          | ↓ connectivity  in other  psycho-  pathology      | ↑ connectivity in  other psycho-  pathology       |
| Ventrolateral  Prefrontal Cortex | 10-12 yrs                                      | 17.9-25.9 yrs                                  | --                                              | --                                              | --                                                | --                                                |
| Dorsolateral Prefrontal  Cortex  | --                                             | 10-14 yrs                                      | --                                              | --                                              | --                                                | --                                                |
| Putamen                          | 10-14 yrs                                      | 24-25.9 yrs                                    | 10-14 yrs                                       | --                                              | --                                                | --                                                |
| Thalmaus                         | 10-15 yrs                                      | --                                             | --                                              | --                                              | --                                                | --                                                |
| Caudate                          | 10-13 yrs                                      | 20-25.9 yrs                                    | --                                              | --                                              | --                                                | --                                                |
| Occipital Cortex                 | 10 yrs                                         | 17-25.9 yrs                                    | 10-12 yrs                                       | --                                              | --                                                | --                                                |

TABLE S6. When the other psychopathology group was added to models in which there were inverse age x group associations observed between psychosis spectrum youth and controls, the interaction term remained significant.

|         |                                             | Typically  developing*psychosis*other  psychopathology   | Typically  developing*psychosis*other  psychopathology   | Typically  developing*psychosis*other  psychopathology   |
|---------|---------------------------------------------|----------------------------------------------------------|----------------------------------------------------------|----------------------------------------------------------|
| Cluster | rsfMRI connectivity measure                 | χ 2                                                      | p                                                        | Q                                                        |
| 11      | CM amygdala-ventrolateral prefrontal cortex | 8.2                                                      | 0.01                                                     | 0.03                                                     |
| 12      | CM amygdala-putamen                         | 6.3                                                      | 0.04                                                     | 0.04                                                     |
| 14      | CM amygdala-thalmaus                        | 7.5                                                      | 0.02                                                     | 0.03                                                     |
| 16      | CM amygdala-caudate                         | 7.7                                                      | 0.02                                                     | 0.03                                                     |
| 19      | CM amygdala-occipital cortex                | 6.3                                                      | 0.04                                                     | 0.04                                                     |

TABLE S7. For all significant inverse age*group interactions, controls exhibited ageassociated decreases in CM amygdala connectivity. Psychosis spectrum youth failed to show significant age-associated changes in all CM amygdala connectivity clusters. The other psychopathology group exhibited age-associated decreases in CM amygdalaputamen connectivity, CM amygdala-caudate connectivity, and CM amygdala-occipital connectivity.

| rsfMRI Connectivity  Measure                  | Group                                                           | Inverse  age beta   | Z-ratio                    | p                    |
|-----------------------------------------------|-----------------------------------------------------------------|---------------------|----------------------------|----------------------|
| CM amygdala- ventrolateral  prefrontal cortex | psychosis spectrum  typically developing  other psychopathology | -0.4  2.1  0.9      | -0.4  5.0  1.4             | 0.66  5.4E-07  0.16  |
| CM amygdala- putamen                          | psychosis spectrum  typically developing  other psychopathology | -0.4  2.5  2.1      | -0.3  5.0  2.8             | 0.73  6.6E-07  0.005 |
| CM amygdala- thalamus                         | psychosis spectrum  typically developing  other psychopathology | 0.0  2.0  0.8       | 0.0  5.4  1.5              | 0.98  5.8E-08  0.13  |
| CM amygdala- caudate                          | psychosis spectrum  typically developing  other psychopathology | -0.9  2.4  1.4      | -0.9  5.0  2.1             | 0.37  5.1E-07  0.04  |
| CM amygdala- occipital cortex                 | psychosis spectrum  typically developing  other psychopathology | -1.3  2.1  1.6      | -1.1  0.28  3.8  2.0  0.05 | 1.2E-04              |

## References

1. Achenbach TM, Rescorla LA: The Manual for the ASEBA School-Age Forms &amp; Profiles. Burlington, VT, Research Center for Children, Youth, and Families., 2001
2. Reynolds CR, Kmaphaus RW: Reynolds Intellectual Assessment Scales2003;
3. Calkins ME, Merikangas KR, Moore TM, et al.: The Philadelphia Neurodevelopmental Cohort: constructing a deep phenotyping collaborative. J Child Psychol Psychiatry 2015; 56:1356-1369
4. Satterthwaite TD, Connolly JJ, Ruparel K, et al.: The Philadelphia Neurodevelopmental Cohort: A publicly available resource for the study of normal and abnormal brain development in youth. Neuroimage 2015; 124:1115-1119
5. Calkins ME, Moore TM, Merikangas KR, et al.: The psychosis spectrum in a young U.S. community sample: findings from the Philadelphia Neurodevelopmental Cohort. World Psychiatry 2014; 13:296-305
6. Kaufman J, Birmaher B, Brent D, et al.: Schedule for Affective Disorders and Schizophrenia for School-Age Children-Present and Lifetime Version (K-SADS-PL): initial reliability and validity data. J Am Acad Child Adolesc Psychiatry 1997; 36:980-988
7. Miller TJ, McGlashan TH, Rosen JL, et al.: Prodromal assessment with the structured interview for prodromal syndromes and the scale of prodromal symptoms: predictive validity, interrater reliability, and training to reliability. Schizophr Bull 2003; 29:703-715
8. Kobayashi H, Nemoto T, Koshikawa H, et al.: A self-reported instrument for prodromal symptoms of psychosis: testing the clinical validity of the PRIME Screen-Revised (PS-R) in a Japanese population. Schizophr Res 2008; 106:356-362
9. Wolf DH, Satterthwaite TD, Calkins ME, et al.: Functional neuroimaging abnormalities in youth with psychosis spectrum symptoms. JAMA Psychiatry 2015; 72:456-465
10.  Satterthwaite TD, Wolf DH, Calkins ME, et al.: Structural Brain Abnormalities in Youth With Psychosis Spectrum Symptoms. JAMA Psychiatry 2016; 73:515-524
11.  Wechsler D: Wechsler adult intelligence scale--Fourth Edition (WAIS--IV) [Internet]. San Antonio, Texas: Psychological Corporation 2014; Available from: https://pdfs.semanticscholar.org/2858/f906a462c4424192f80361f689bdec24c16d.pdf
12.  First MB, Spitzer RL, Gibbon M, et al.: Structured clinical interview for DSM-IV-TR axis I disorders, research version, patient edition. SCID-I/P, 2002
13.  Patel AX, Bullmore ET: A wavelet-based estimator of the degrees of freedom in denoised fMRI time series for probabilistic testing of functional connectivity and brain graphs [Internet]. Neuroimage 2015; Available from: http://eutils.ncbi.nlm.nih.gov/entrez/eutils/elink.fcgi?dbfrom=pubmed&amp;id=25944610&amp;retmod e=ref&amp;cmd=prlinks
14.  Hallquist MN, Hwang K, Luna B: The nuisance of nuisance regression: spectral misspecification in a common approach to resting-state fMRI preprocessing reintroduces noise and obscures functional connectivity. Neuroimage 2013; 82:208-225

15.  Fischl B, Salat DH, Busa E, et al.: Whole brain segmentation: automated labeling of neuroanatomical structures in the human brain. Neuron 2002; 33:341-355
16.  Pruim RHR, Mennes M, van Rooij D, et al.: ICA-AROMA: A robust ICA-based strategy for removing motion artifacts from fMRI data. Neuroimage 2015; 112:267-277
17.  Pruim RHR, Mennes M, Buitelaar JK, et al.: Evaluation of ICA-AROMA and alternative strategies for motion artifact removal in resting state fMRI. Neuroimage 2015; 112:278-287
18.  Eickhoff SB, Paus T, Caspers S, et al.: Assignment of functional activations to probabilistic cytoarchitectonic areas revisited. Neuroimage 2007; 36:511-521
19.  Roy AK, Shehzad Z, Margulies DS, et al.: Functional connectivity of the human amygdala using resting state fMRI. Neuroimage 2009; 45:614-626
20.  Gabard-Durnam LJ, Flannery J, Goff B, et al.: The development of human amygdala functional connectivity at rest from 4 to 23 years: a cross-sectional study. Neuroimage 2014; 95:193-207
21.  Jalbrzikowski M, Larsen B, Hallquist MN, et al.: Development of White Matter Microstructure and Intrinsic Functional Connectivity Between the Amygdala and Ventromedial Prefrontal Cortex: Associations With Anxiety and Depression. Biol Psychiatry 2017; 82:511-521
