![Image](./Leiberg2023_artifacts/image_000000_e8931b0ca04a0fb407e342a12e8eeba11bf4526ce69b46b73c250dd4614cea10.png)

## Research Report

## Effects of anterior temporal lobe resection on cortical morphology

Karoline Leiberg a, * , Jane de Tisi b , John S. Duncan b,c , Bethany Little a,d , Peter N. Taylor a,d,e , Sjoerd B. Vos e,f,g,h , Gavin P. Winston b,i,j , Bruno Mota k and Yujiang Wang a,d,e

a CNNP Lab (www.cnnp-lab.com), Interdisciplinary Computing and Complex BioSystems Group, School of Computing, Newcastle University, Newcastle Upon Tyne, UK

b Department of Clinical &amp; Experimental Epilepsy, UCL Queen Square Institute of Neurology, London, UK

c Chalfont Centre for Epilepsy, Chalfont St Peter, UK

d Faculty of Medical Sciences, Newcastle University, Newcastle Upon Tyne, United Kingdom

e Queen Square Institute of Neurology, University College London, Queen Square, London, UK

f Neuroradiological Academic Unit, Department of Brain Repair and Rehabilitation, UCL, UK

g Centre for Medical Image Computing, University College London, London, UK

h Centre for Microscopy, Characterisation, And Analysis, The University of Western Australia, Nedlands, Australia

i MRI Unit, Epilepsy Society, Buckinghamshire, UK

j Division of Neurology, Department of Medicine, Queen's University, Kingston, Ontario, Canada

k MetaBIO Lab, Instituto de Fı ´sica, Universidade Federal Do Rio de Janeiro (UFRJ), Rio de Janeiro, Brazil

## a r t i c l e i n f o

Article history: Received 19 January 2023 Reviewed: 13 March 2023 Revised 11 April 2023 Accepted 16 April 2023 Action editor Gus Buchtel Published online 8 June 2023

Keywords: Temporal lobe epilepsy Cortical morphology Structural MRI Epilepsy surgery

## a b s t r a c t

Neuroimaging can capture brain restructuring after anterior temporal lobe resection (ATLR), a surgical procedure to treat drug-resistant temporal lobe epilepsy (TLE). Here, we examine the effects of this surgery on brain morphology measured in recently-proposed independent variables.

We studied 101 individuals with TLE (55 left, 46 right onset) who underwent ATLR. For each individual we considered one pre-surgical MRI and one follow-up MRI 2 e 13 months after surgery. We used a surface-based method to locally compute traditional morphological variables, and the independent measures K , I , and S , where K measures white matter tension, I captures isometric scaling, and S contains the remaining information about cortical shape. A normative model trained on data from 924 healthy controls was used to debias the data and account for healthy ageing effects occurring during scans. A SurfStat random field theory clustering approach assessed changes across the cortex caused by ATLR.

Compared to preoperative data, surgery had marked effects on all morphological measures. Ipsilateral effects were located in the orbitofrontal and inferior frontal gyri, the pre- and postcentral gyri and supramarginal gyrus, and the lateral occipital gyrus and lingual cortex. Contralateral effects were in the lateral occipital gyrus, and inferior frontal gyrus and frontal pole.

* Corresponding author . School of Computing, Newcastle University, 1 Science Square, NE4 5TG, Newcastle upon Tyne, United Kingdom. E-mail addresses: k.leiberg2@newcastle.ac.uk (K. Leiberg), yujiang.wang@newcastle.ac.uk (Y. Wang). https://doi.org/10.1016/j.cortex.2023.04.018

Available online at www.sciencedirect.com

## ScienceDirect

Journal homepage: www.elsevier.com/locate/cortex

![Image](./Leiberg2023_artifacts/image_000001_f0f49b0b6b7d1bc56e1fe1e8feb8e7c944c23834db6b04d7e1d704050d8b167e.png)

![Image](./Leiberg2023_artifacts/image_000002_964463f689a126101f49683a65d487c6cae2c2b8ca0a30e2df53fe96975b22ad.png)

## 1. Introduction

Anterior temporal lobe resection (ATLR) is a common surgical procedure to treat drug-resistant temporal lobe epilepsy (TLE). It removes the anterior (3 e 3.5 cm) of the middle and inferior temporal gyri and uncus, amygdala, anterior hippocampus, and parahippocampal gyrus (Foldvary-Schaefer &amp; Wyllie, 2007).

The effect of surgery on the cortical morphology of the remaining brain could inform how neighbouring, or connected regions affect each other's morphology, and the processes of postoperative plasticity. Previous studies found widespread, bilateral changes to cortical thickness (Elias et al., 2021; Galovic et al., 2020; Li et al., 2022; Zhao et al., 2021) or volume (Pajkert et al., 2020). However, these studies had relatively small sample sizes (between 12 and 56 subjects), and there is little overlap in regions identified as affected by morphological changes between the studies. In fact, some of these studies found conflicting effects, such as increased (Elias et al., 2021) or decreased (Galovic et al., 2020) cortical thickness in the contralateral anterior and middle cingulate cortex. ATLR has also been associated with altered white matter tract properties (Concha et al., 2007; da Silva et al., 2020; Faber et al., 2013; McDonald et al., 2010; Pustina et al., 2014; Winston et al., 2013), particularly the ipsilateral uncinate fasciculus, inferior longitudinal, inferior fronto-occipital fasciculi, optic radiation, cingulum, fornix, and the corpus callosum; these alterations may also differ in individuals rendered seizure free and those experiencing seizures after surgery (da Silva et al., 2020). Other studies have found widespread reductions in functional connectivity after surgery compared to healthy controls in connections that were not different to controls before surgery (Morgan et al., 2020), as well as dissimilar functional reorganisation for individuals rendered seizure free and individuals with recurrent seizures (Liao et al., 2016). Finally, 25 e 50% of individuals with TLE experience significant declines in memory and language functions after ATLR (Davies et al., 1998; Martin et al., 1998), and postoperative changes in white matter are reported to be associated with recovery of language function (Winston et al., 2013; Yogarajah et al., 2010). However, in terms of brain morphology, the spatial characteristics of postoperative changes and their relationship with seizure and cognitive outcomes are currently not clear.

Traditional morphological analyses generally rely on measures such as cortical thickness, volume, and surface areas. However, these measures can yield conflicting results (Alhusaini et al., 2012) and a single measure, such as volume, could be driven by multiple independent biological processes

The restructuring following ATLR is reflected in widespread morphological changes, mainly in regions near the resection, but also remotely in regions that are structurally connected to the anterior temporal lobe. The causes could include mechanical effects, Wallerian degeneration, or compensatory plasticity. The study of independent measures revealed additional effects compared to traditional measures.

© 2023 The Author(s). Published by Elsevier Ltd. This is an open access article under the CC BY license (http://creativecommons.org/licenses/by/4.0/).

(Panizzon et al., 2009). Further, the measures of average cortical thickness ( T ), total surface area ( At ), and exposed surface area ( Ae ) covary tightly across species, individuals, and regions (Mota &amp; Herculano-Houzel, 2015; Wang et al., 2016, 2019) according to a scaling law of cortical folding. More recently developed morphological measures were presented by Wang et al., (2021). That study showed that traditional morphological measures can miss substantial information about cortical shape, and suggested alternative morphological measures of tension ( K ), isometric size ( I ), and shape ( S ). These morphological measures are theoretically and statistically independent, capture all morphological information in T , At and Ae , and can detect morphological changes that might otherwise not be detected due to the covariance of the traditional variables.

In this study we examined 101 individuals with TLE who underwent ATLR. We performed a localised, surface-based analysis of cortical morphology to derive maps of cortical changes after ATLR using both traditional and recently developed morphology measures. Quantifying these changes after ATLR on a regional cortical basis could lead to a better understanding of the consequences and processes of reorganisation following ATLR, and the inter-relationships between connected regions.

## 2. Methods

## 2.1. Independent morphological measures

We performed a morphological analysis in the recently developed framework (Wang et al., 2021) to study cortical morphology based on the universal scaling law of cortical folding. This law states that across mammalian species (Mota &amp; Herculano-Houzel, 2015), individual human brains (Wang et al., 2016), their lobes (Wang et al., 2019), and small local areas (Leiberg et al., 2021), the average cortical thickness T , the total surface area At , and the exposed surface area Ae covary according to the equation

<!-- formula-not-decoded -->

Here, k is a constant that is relatively preserved across species compared to the range of variation in the traditional morphological measures T , At , and Ae , but shows consistent changes across age groups and between cohorts of healthy and diseased subjects (Mota &amp; Herculano-Houzel, 2015; Wang et al., 2016, 2019). This interdependence of the traditional morphological measures means that their interaction must be considered when studying effects in this framework. We use

independent morphological measures that are derived from said scaling law as combinations of the traditional measures, and capture all morphological information they contain. Fig. 1 shows a schematic example of how the interdependence of the traditional measures can hide information, which is revealed in the independent measures.

Importantly, the scaling law also directly challenges the direct use of these traditional morphological measures of surface and thickness, without consideration for their covariance. Instead an independent set of morphological measures is proposed, derived from the scaling law (Wang et al., 2021). Solving equation (1) for log k gives the first morphometric measure K in this framework of describing cortical morphology. It is a dimensionless measure that we interpreted as the tension acting on the cortex (Leiberg et al., 2021; Wang et al., 2016, 2019, 2021). The second measure is I , a term of isometric size in all three traditional variables T 2 , At , and Ae . I is orthogonal to K . The final measure S is the cross product of K and I , so that all three are orthogonal to each other. S contains all remaining information about cortical

Fig. 1 e Schematic illustration with example data on how traditional measures can hide information due to their covariance, and how a change of coordinate system can reveal this information. The plot shows a 2-dimensional projection of data points measured in thickness T , total surface area At , and exposed surface area Ae . Their agreement with the scaling law can be seen as the alignment with a slope of 1.25 (thin grey line). The two processes of changing from the initial black point to either blue or red appear similar on the original axes, both being increases in the same directions. It becomes apparent that they are in fact opposing processes when looking at changes along the new axis K (thick black line).

![Image](./Leiberg2023_artifacts/image_000003_c35f16c45930030c942f3a91910bbccb25f955f1a2e3669f847a5416c637922f.png)

shape that is not captured in K or I , and is a measure of complexity in cortical folding. The three new measures span orthogonal vectors in the three-dimensional morphological space of T 2 , At , and Ae , and hence using K , I , and S is simply a change of coordinate systems:

<!-- formula-not-decoded -->

<!-- formula-not-decoded -->

<!-- formula-not-decoded -->

## 2.2. Data &amp; preprocessing

We report how we determined our sample size, all data exclusions, all inclusion/exclusion criteria, whether inclusion/ exclusion criteria were established prior to data analysis, all manipulations, and all measures in the study.

We used 3T T1-weighted structural magnetic resonance images (MRIs) of 101 individuals with TLE who underwent ATLRattheNational Hospital for Neurology and Neurosurgery (NHNN). The analysis was carried out under approval by the Newcastle University Ethics Committee (8841/2020). No part of the study procedures or analyses were pre-registered prior to the research being conducted. We included individuals with TLE in our study that received a preoperative and postoperative T1-weighted MRI scan between 2010 and 2018 at NHNN or Chalfont Centre for Epilepsy. For each subject we analysed an image acquired up to three years before surgery and one follow-up scan 2 e 13 months (median ¼ 3.6 months) after surgery. If patients had more than one follow-up scan, we used the one closest to surgery. MRI scans were taken under two different scanning protocols: 52 subjects were scanned with a resolution of .94 /C2 0.94 /C2 1.1 mm (scanning protocol 1; for detailed acquisition parameters see Nowell et al. (2016)) and the remaining 49 with 1 /C2 1x1mm (scanning protocol 2; for detailed acquisition parameters see Vos et al. (2020)). Surgery outcomes were recorded 12 months after surgery according to the ILAE classification of seizure outcome (Wieser et al., 2001). We also used MR images of crosssectional cohorts of 924 healthy controls, 91 of which were taken together with patients. Specifically, 22 were taken with scanning protocol 1 and 69 with scanning protocol 2 at the NHNN. The remaining 833 controls are subjects from the Nathan Kline Institute (NKI) data set (Nooner et al., 2012). 96 other subjects of the NKI data set were rejected prior to the analysis due to inadequate image quality and motion artifacts. Table 1 shows the demographics of the data used.

MRI scans were preprocessed with the FreeSurfer (Fischl, 2012) 6.0 recon-all pipeline, which produces a mesh representation of the grey matter surface, along with the grey matter thickness for each point on the grey matter surface. We manually corrected the grey- and white matter segmentation by using control points and corrected grey matter boundaries where necessary. The local gyrification index processing stream (https://surfer.nmr.mgh.harvard.edu/ fswiki/LGI) was then used to acquire the smooth pial

Table 1 e Demographics of individuals with TLE and controls. * ILAE scale for classification of outcome of epilepsy surgery.

|                                                                       | Left TLE   | Right TLE    | Controls   |
|-----------------------------------------------------------------------|------------|--------------|------------|
| Subjects ( n )                                                        | 55         | 46           | 924        |
| Sex (M/F)                                                             | 24/31      | 16/30        | 362/562    |
| Median postoperative interval in months (IQR)                         | 3.6 (1.52) | 3.6 (1.05)   | e          |
| Median TLE duration in years (IQR)                                    | 19 (27.99) | 21.5 (21.23) | e          |
| Seizure outcome group * 3 þ (recurrent seizures) after one year ( n ) | 12         | 11           | e          |

surface. This surface, which is obtained by closing sulci of the pial surface with a 15 mm diameter sphere, is an outer surface wrapped tightly around the pial surface (Schaer et al., 2008).

We visually ensured that there were no distortions to the Desikan-Killiany atlas surface ROI labels around the resected area by comparing pre- and postoperative scans.

## 2.3. Computation of localised morphological measures

To analyse the effects of epilepsy surgery, we used a surfacebased approach rather than a parcellation-based approach for increased sensitivity. We obtained surface-based morphological measures of the local cortical thickness, local surface area and local exposed area.

We employed our previously published pipeline (Fig. 2) (Leiberg et al., 2021). In summary: we first downsampled the FreeSurfer pial surface to 5% of its original density. We then defined a contiguous surface patch of 3 cm radius surrounding each point in the pial. We closed potential holes in the patch which can arise at the top of gyri or the bottom of sulci. We computed the pointwise average cortical thickness ð p T Þ and total surface area ð p At Þ from this patch around each point.

Next, we found a corresponding surface patch on the smooth pial surface by selecting all vertices on it for which their nearest vertex on the pial surface is part of the pial surface patch. From this, we calculated the exposed surface area ð p Ae Þ of the patch. We then fitted a convex hull over the patch on the smooth pial surface and calculated its integrated Gaussian curvature ð p IG Þ as the sum of Gaussian curvatures of all points on the convex hull that are not lying on its edge. Since the integrated Gaussian curvature is conserved in closed surfaces, we used the proportion of curvature of a patch over the full pial surface to correct both surface areas p At and p Ae according to the reasoning laid out in previous publications (Leiberg et al., 2021; Wang et al., 2019): p A 0 t ¼ p At /C2 4 p p IG and p A 0 e ¼

<!-- formula-not-decoded -->

The method of correcting surface areas does not perform well for regions that are particularly flat or located in deep sulci, so some vertices were missing data. Where possible, we imputed data on vertices that were missing values as the mean over their direct neighbouring vertices, but had to exclude the insula and the regions around the corpus callosum from our analysis, since these regions had entirely missing values. We then converted back to the full pial surface by using the value of the vertex on the downsampled pial surface that was closest to the pial surface.

To avoid the morphology of the resection affecting measures of nearby vertices, we excluded the entire ipsilateral temporal lobe from the pial surface before applying our method. We also applied this exclusion to the preoperative surfaces and processed all controls twice, once with the temporal lobes and once without, to keep the analysis consistent and comparable.

## 2.4. Scaling law fit in data of individuals with TLE

Weassessed the fit of the scaling law to the cortical folding of individuals with TLE before surgery by regressing between the quantities log 10 ð p A 0 e Þ and log 10 ð p A 0 t ffiffiffiffiffi p T p Þ across each hemisphere, and comparing the slope of this regression to the predicted slope of 1.25.

## 2.5. Age, sex and scanning protocol correction

After registering all subjects to the FreeSurfer fsaverage surface and performing a log transform of the three traditional morphological measures p T , p A 0 t , and p A 0 e , we performed a correction of these measures based on a normative model of the controls using generalised additive mixed models (GAMM) for each patch on the cortical surface. This correction accounts for differences due to age, sex, and scanning protocol. Wedid this with the R package mgcv (https://CRAN.R-project. org/package ¼ mgcv). We trained a model with the formula

<!-- formula-not-decoded -->

where b is a model coefficient, s is a smooth spline, ε is the error term, and y is one of the measures p T , p A 0 t , or p A 0 e , using

Fig. 2 e Method to extract local morphological measures. a) Definition of neighbourhood of connected vertices within 3 cm around each point. b) Computation of average thickness p T and total area p At of patch. c) Computation of exposed area p Ae of the patch.

![Image](./Leiberg2023_artifacts/image_000004_c7f0468e953985227240da0e2018d731f61e2a153ccbc724fa1648aeed599fd9.png)

restricted maximum likelihood as the method for smoothing parameter estimation. We used the controls to provide a normative model of age and sex with scanning protocol as a random effect. Even though the large NKI data set did not match the patients in scanning protocol, we used them for training to ensure we had robust models for the entire age range, given that the site-specific control data sets were relatively small (Rutherford et al., 2023). The site-specific controls matching the patients then informed effects for the specific scanning protocols. We used the control data processed without/with the temporal lobes for correcting the ipsilateral/ contralateral sides respectively by predicting the values of individuals with TLE both in pre- and postoperative data and using those predictions for correcting the individuals ' data. Note how this automatically accounts for changes to the brain's morphology that occur due to healthy ageing between the time points of the pre- and postoperative scans.

## 2.6. Converting to independent morphological measures

The traditional measures p T , p A 0 t , and p A 0 e were then converted to a set of independent morphological measures (section 2.1) in each patch, to account for the covariance in the traditional measures. We transformed the data, which had been corrected for the covariates age, sex, and scanning protocol, to the independent morphological measures K , I , and S according to their formulae

<!-- formula-not-decoded -->

<!-- formula-not-decoded -->

<!-- formula-not-decoded -->

Here, K and S are dimensionless measures, whilst the isometric term I is measured in multiples of 6 log( mm ). We thus obtained six maps of cortical morphology for each subject's preand postoperative brain, measured in independent morphological measures ð p K ; p I ; p S Þ and traditional variables ( p T , p A 0 t , and p A 0 e ) in a region for each point on the cortex.

For simplicity of notations, and as we will focus our analysis on patch-based measures, we will drop the indicator p for the patch, and in the following K , I , S are the independent morphological measures of patches, and T , At , Ae are the traditional measures of patches.

## 2.7. Surface effects

Weproceededwithanipsilateral and contralateral analysis by combining individuals with left and right onset (see Supplementary Figs. 1-4 for separate results for each onset side).

Toallow for a pairwise analysis of the changes between the pre- and postoperative scans, we centred the data subjectwise at each vertex.

We used the Matlab toolbox SurfStat (Worsley et al., 2009) for a surface based statistical analysis that corrects for multiple comparison and accounts for spatial correlation with random field theory. We employed a design matrix with effects for the

Fig. 3 e Distribution of observed scaling law slopes in individuals with TLE preoperatively. Each subject's hemisphere is a single measurement of a slope and the distribution is formed across subjects. The mean of slopes is marked by the black line.

![Image](./Leiberg2023_artifacts/image_000005_d529864419463d4a91945a684a8f822ef491238311c264b38c437242d37cd439.png)

group of preoperative surfaces and the group of postoperative surfaces and used a contrast to check for effects between the two categories. We repeated this for all six variables (traditional and independent), ending up with a surface map of the pairwise effect between the two scans for each. We applied a threshold for effects having a cluster-wise model significance of p /C20 .05. We reported effects measured in Cohen's f 2 .

## 2.8. Covariate effects

We tested for effects of the covariates: age at surgery, duration since first seizure, resection volume, side of resection, time between surgery and postoperative scan, and seizure outcome (ILAE 1 &amp; 2 vs 3 þ ). We performed this by taking subject-wise differences between pre- and postoperative at each point and using a design matrix for each covariate separately to test for significance of the covariate on the effects of surgery.

## 3. Results

## 3.1. Scaling law of cortical folding in individuals with TLE

We first verified that the local folding in individuals with TLE follows the universal scaling law. As shown in Fig. 3, the slopes of each subject's hemispheres are distributed around a mean of 1.246, indicating that the scaling law also applies to our cohort of individuals with TLE.

## 3.2. Morphological effects

We found morphological effects in 5 main areas.

- /C15 ipsilateral orbitofrontal and inferior frontal gyri

- /C15 ipsilateral preand postcentral gyri and supramarginal gyrus
- /C15 ipsilateral lateral occipital gyrus and lingual cortex
- /C15
- /C15 contralateral lateral occipital gyrus
- contralateral inferior frontal gyrus and frontal pole.

In the following, we will go through changes in these areas by hemisphere and morphometric measure.

## 3.2.1. Traditional morphological measures

We first assessed effects of ATLR on the traditional measures of average cortical thickness, total surface area and exposed surface area. On the side ipsilateral to seizure onset, cortical thickness increased in the precentral gyrus and decreased in the lingual cortex and lateral occipital gyrus (Fig. 4a). The total surface area increased in the orbitofrontal and inferior frontal gyri (Fig. 4b). The exposed area also increased in the orbitofrontal and inferior frontal gyri, and reduced in the lingual cortex (Fig. 4c).

In the contralateral hemisphere, T increased in the lateral occipital gyrus (Fig. 4a), whilst At increased in the lateral occipital cortex and in the inferior frontal gyrus (Fig. 4b).

Average effect sizes for relevant regions can be found in supplementary section 1. See supplementary section 2.1 for effects by onset side.

## 3.2.2. Independent morphological measures

In the independent measures, on the ipsilateral side the pressure term K decreased in the orbitofrontal and inferior frontal gyri, and increased in the pre- and postcentral and supramarginal gyri, as well as the lateral occipital cortex (Fig. 5a). The isometric term I increased in the orbitofrontal and inferior frontal gyri, and decreased in the lateral occipital cortex (Fig. 5b). The shape term S increased in the orbitofrontal and inferior frontal gyri (Fig. 5c).

In the contralateral hemisphere, K increased in the lateral occipital cortex and in the frontal pole (Fig. 5a). I increased in the lateral occipital cortex and in the inferior frontal gyrus (Fig. 5b).

Average effect sizes for relevant regions can be found in supplementary section 1. See supplementary section 2.2 for effects by onset side.

Some effects, for example in the supramarginal gyrus, were only visible in the independent variables, specifically in K . Some effects to cortical thickness, for example in the precentral gyrus, were not reflected in the independent morphological measures K , I , and S .

## 3.3. Covariate effects

We did not find any effects of age at surgery, duration of TLE, resection volume, side of resection, time between surgery and postoperative scan, or seizure outcome on the morphological changes following ATLR at a cluster significance of .05.

## 4. Discussion

## 4.1. Morphological findings

We found significant changes in cortical morphology after ATLR in all measures we studied. The regions affected ipsilateral to the resection were the orbitofrontal and inferior frontal gyri, where we found changes to total surface area, exposed surface area, tension term, isometric term, and shape term, the lateral occipital gyrus, and lingual cortex, which saw changes in cortical thickness, exposed surface area, tension term, and isometric term. We also found thickness changes in parts of the ipsilateral pre- and postcentral gyri, as well as the supramarginal gyrus. Contralaterally, we found morphological changes in the occipital cortex in thickness, total surface area, tension term, and isometric term, and in the frontal cortex in total area, tension term, and isometric term. These findings indicate widespread structural cerebral changes after ATLR.

## 4.2. Comparison to previous work

Previous work has predominantly focused on changes to cortical thickness following ATLR. Our findings of changes in cortical thickness largely agree with those from previous studies; this includes decreases in thickness in the ipsilateral lateral occipital gyrus (Elias et al., 2021) and in the ipsilateral lingual cortex (Li et al., 2022), and increased thickness in the

Fig. 4 e Changes following ATLR in the morphological variables average cortical thickness T (a), total surface Area At (b), and exposed surface area Ae (c). Effect clusters with statistical significance of p ≤ .05 are shown and effect sizes are given in Cohen's f 2 metric. Increases are in blue, decreases are in red. Regions excluded from the analysis, such as the temporal lobe that was operated upon, are in dark grey.

![Image](./Leiberg2023_artifacts/image_000006_9bc58c3316eb8907cce21a2ce6d669c00c2d43625fbf1a3b6b2d90ffe11af44e.png)

Fig. 5 e Changes following ATLR in the independent morphological measures K (a), I (b), and S (c). Effect clusters with statistical significance of p ≤ .05 are shown and effect sizes are given in Cohen's f 2 metric. Increases are in blue, decreases are in red. Regions excluded from the analysis, such as the temporal lobe that was operated upon, are in dark grey.

![Image](./Leiberg2023_artifacts/image_000007_7b793447dbd0ba5ef2922209b64ea2649dc5b6569127f5031c3a0ab98602e75e.png)

ipsilateral precentral gyrus (Zhao et al., 2021). We additionally found cortical thickness increased in the contralateral lateral occipital gyrus. Changes to thickness can be offset by the surface area measures, explaining why studies might fail to replicate findings or even be in disagreement, showing how cortical thickness is not sufficient as a sole measure of cortical morphology. It may also explain why some changes in thickness were not reflected in the independent measures in our study.

## 4.3. Interpretations

Strong effects were seen in the orbitofrontal and inferior frontal gyri in almost all morphometric measures. Specifically, in the traditional morphological measures we saw increased total and exposed surface area, and in the independent measures an increase in the isometric term I, an increase in morphological complexity S, and a decrease in the tension term K. After surgery, the inferior and orbitofrontal cortex above the resection site often drops slightly into the resection cavity. Our observed effects are most likely explained by this ' sagging ' of the frontal lobe into the resection cavity; although, we cannot exclude that these effects are due to additional functional or structural mechanisms. We checked that these strong effects were not caused by distortion to the FreeSurfer region surface labels by visually inspecting the Desikan-Killiany atlas labelling in the post-surgery images. The thickness remaining constant in these gyri whilst the surface areas increase indicates that the cortical tissue is arranged differently in space, which is confirmed by changes to K and S. The decrease in K supports the hypothesis that K captures pressure on the cortex, which is reduced in the orbitofrontal and inferior frontal gyri because of the cavity.

In our study, the main regions with the greatest morphological changes after ATLR were structurally connected to the resected tissue. Previous studies on white matter tract alterations after temporal lobe surgery found reductions in quantitative anisotropy (QA) (da Silva et al., 2020) and fractional anisotropy (FA) (Concha et al., 2007; Faber et al., 2013; McDonald et al., 2010; Pustina et al., 2014; Winston et al., 2013) in the ipsilateral uncinate fasciculus, which connects the resected tissue to the orbitofrontal cortex, the region in which our analysis found the greatest morphological alterations. We also saw structural changes in the ipsilateral lateral occipital gyrus. This region is connected to the resected temporal lobe by the inferior longitudinal fasciculus, which has been shown to have reduced FA following surgery (Concha et al., 2007; Faber et al., 2013; McDonald et al., 2010; Pustina et al., 2014; Winston et al., 2013). There is evidence of reduced QA in the ipsilateral inferior fronto-occipital fasciculus (da Silva et al., 2020) and reduced FA in the ipsilateral inferior frontooccipital fasciculus and the optic radiation (Concha et al., 2007; Faber et al., 2013; McDonald et al., 2010; Pustina et al., 2014; Winston et al., 2013), both passing through the temporal lobe to the occipital lobe. One study found increased FA in the contralateral uncinate fasciculus and superior longitudinal fasciculus (Pustina et al., 2014), evidence of white matter changes which could be underlying the morphological changes we found contralateral to the resection in the inferior frontal gyrus, frontal pole, and lateral occipital cortex. Previous studies also found alterations to the cingulum (Concha et al., 2007; Faber et al., 2013; McDonald et al., 2010; Winston et al., 2013). Although we did not find significant changes to the cingulate cortex in the traditional morphometrics, we were unable to investigate this region with independent morphometrics due to limitations of the method for their computation. Overall, our study suggests that the regions whose white matter connections are affected by surgery undergo structural changes. This could be due to Wallerian degeneration of the white matter tracts, a process in which an axon is cut or injured, causing parts of the axon distant from the damage to degenerate. This could in turn lead to atrophy in the connected grey matter, which is reflected in morphological changes. Future work on longitudinal structural, diffusion, and functional MRI will be necessary to confirm if this is in fact the underlying process.

None of the covariates we tested had significant effects on the morphological changes we found. In particular, there was noeffect of the time since the first seizure occurred, indicating that the brains of those who experienced seizures for longer do not restructure differently than those with a short duration. Similarly, the age of the subject made no significant

difference, although as subjects in our study were in an age range of 19 e 60 years, there might be age-dependent effects for individuals outside this range. Further, we did not see significant correlation with seizure outcome, despite evidence of larger white matter tract alterations in those who were seizure-free after surgery (da Silva et al., 2020). This suggests that effects sizes of white matter and grey matter restructuring may not be correlated. Due to lack of data, we did not test for differences in morphological changes when grouping subjects by language lateralisation, but since language network differences are known in left and right TLE (Neudorf et al., 2020), in future such studies of structural changes after ATLR would be interesting. We did however test for the effect of onset side in our model, and although there are some differences in morphological effects between left and right onset (see Supplementary Figs. 1-4), the covariate had no significant effect in our model. Future work may also explore additional outcome measures and covariates, such as language, visual, memory and cognitive function. Given that structural morphology relates to cognitive functioning in epilepsy (Garcia-Ramos et al., 2022), investigating structural changes after surgery using independent components could help understand the mechanisms of, and treatment for, cognitive impairment.

We postulate that the structural changes we found are truly due to the surgery, rather than the progression of the disorder itself, for three reasons: First, although previous literature suggests that the progression of TLE affects cortical morphology, the changes described are not in regions such as the bilateral lateral occipital cortex or frontal poles (Galovic et al., 2020; Whelan et al., 2018), where we found changes between the pre- and postoperative scans. Additionally, these changes occur at longer time scales, with effects of up to .02 mm reduction in cortical thickness per year (Galovic et al., 2020; Whelan et al., 2018). Thus, we would not expect strong effects over a relatively short period of two years. Second, we found no correlation between the duration of TLE and the morphological changes after ATLR. Third, the regions that we found to be affected by morphological changes are also connected to the resected temporal lobe by white matter tracts, suggesting a spatially specific non-random effect.

We cannot decisively determine if morphological change is a sign of atrophy or reorganisation, or potentially even increased function to compensate for the loss of tissue. A recent study on brain age changes after surgery found that an increased brain age in those with mesial TLE is reversed following surgery (de B /C19 ezenac et al., 2021). Similarly, progressive cortical thinning in unilateral TLE ceases after successful surgery to the rate of healthy ageing (Galovic et al., 2020; Zhao et al., 2021). These findings suggest that structural changes following surgery are linked to a restorative effect on brain health, which future work using independent morphometrics with longitudinal data may be able to verify. Of course, the processes of restructuring do not require spatial uniformity across the cortex, and there are likely region-specific effects with different causes. Further studies linking structural changes to postsurgical deficits such as visual field impairment will be necessary to deduce the nature of the processes causing the changes to morphology after ATLR. Furthermore, a focus on differences in morphological changes between individuals with persistent seizures after surgery and those rendered seizure free would also be valuable. One may hypothesise that abnormalities in morphological measures could be useful for localisation during pre-surgical evaluation with the biggest changes pertaining to seizure-freedom located within the ipsilateral anterior temporal lobe, which was excluded from our analyses. Future studies could investigate the location of these abnormalities in such focal areas.

## 4.4. Novel morphological measures

We identified changes to cortical regions, such as the ipsilateral postcentral gyrus or the contralateral frontal pole, that the traditional measures did not find. This is in line with previous work (Wang et al., 2021), which showed that using the variables K , I , and S can reveal morphological information otherwise concealed in the covariance between the measures thickness, exposed surface area, and total surface area. We found that the tension term K detected even subtle morphological changes, likely due to its near-invariant value in controls, highlighting the benefits of independent measures for quantifying cortical shape.

## 5. Conclusions

We found widespread morphological changes following anterior temporal lobectomy, mainly in regions near the resections, but also remotely in regions that are structurally connected to the anterior temporal lobe, even contralaterally. This could be evidence of a reorganisation of the cortex after surgery, or atrophy caused by Wallerian degeneration of connected white matter structures. Future work correlating these structural changes with functional change can be useful to inform surgery strategy. We found that the morphological effects were more pronounced and, in some cases, only detectable in a set of new, independent morphological measures of cortical morphology, rather than in traditional morphometric measures.

## Data and code availability

The NKI data set is available at http://fcon\_1000.projects.nitrc. org/indi/enhanced/. Patient data will be shared as part of a larger public data repository planned for 2023/2024. To discuss data access ahead of the release, please contact one of the corresponding authors. De-identified imaging data for each individual summarised as their morphological information can be found on zenodo (https://doi.org/10.5281/zenodo. 7957831).

The analysis code can be found on github (https://github. com/cnnp-lab/2023Leiberg\_ATLRmorphology).

## Declaration of competing interest

The authors declare no competing interests.

## Acknowledgements

We thank members of the Computational Neurology, Neuroscience &amp; Psychiatry Lab (www.cnnp-lab.com) for discussions on the analysis and manuscript. The authors acknowledge the facilities and scientific and technical assistance of the National Imaging Facility, a National Collaborative Research Infrastructure Strategy (NCRIS) capability, at the Centre for Microscopy, Characterisation, and Analysis, the University of Western Australia. K.L. was supported by the EPSRC Centre for Doctoral Training in Cloud Computing for Big Data (EP/ L015358/1). G.P.W. was supported by the MRC (G0802012, MR/ M00841X/1). J.S.D., J.d.T., and S.B.V. are funded by UCL/UCLH and supported by the National Institute for Health and Care Research University College London Hospitals Biomedical Research Centre. P.N.T. and Y.W. are both supported by UKRI Future Leaders Fellowships (MR/T04294X/1, MR/V026569/1). B.M. is supported by Fundac ¸ ~ ao Serrapilheira Institute (grant Serra-1709-16981) and CNPq (PQ 2017 312837/2017-8).

## Supplementary data

Supplementary data to this article can be found online at https://doi.org/10.1016/j.cortex.2023.04.018.

## r e f e r e n c e s

- Alhusaini, S., Doherty, C. P., Palaniyappan, L., Scanlon, C., Maguire, S., Brennan, P., Delanty, N., Fitzsimons, M., &amp; Cavalleri, G. L. (2012). Asymmetric cortical surface area and morphology changes in mesial temporal lobe epilepsy with hippocampal sclerosis. Epilepsia, 53 (6), 995 e 1003. https:// doi.org/10.1111/j.1528-1167.2012.03457.x
- Concha, L., Beaulieu, C., Wheatley, B. M., &amp; Gross, D. W. (2007). Bilateral white matter diffusion changes persist after epilepsy surgery. may Epilepsia, 48 (5), 931 e 940. https://doi.org/10.1111/ J.1528-1167.2007.01006.X.

da Silva, N. M., Forsyth, R., McEvoy, A., Miserocchi, A., de Tisi, J., Vos, S. B., Winston, G. P., Duncan, J., Wang, Y., &amp; Taylor, P. N. (2020). Network reorganisation following anterior temporal lobe resection and relation with post-surgery seizure relapse: A longitudinal study. NeuroImage: Clinical, 27 , 102320. https:// doi.org/10.1016/J.NICL.2020.102320

Davies, K. G., Bell, B. D., Bush, A. J., Hermann, B. P., Dohan, F. C., &amp; Jaap, A. S. (1998). Naming decline after left anterior temporal lobectomy correlates with pathological status of resected hippocampus. Epilepsia, 39 (4), 407 e 419. https://doi.org/ 10.1111/j.1528-1157.1998.tb01393.x de B /C19 ezenac, C. E., Adan, G., Weber, B., &amp; Keller, S. S. (2021). Association of epilepsy surgery with changes in imagingdefined brain age. aug Neurology, 97 (6), e554 e e563. https:// doi.org/10.1212/WNL.0000000000012289.

- Elias, G. J. B., Germann, J., Neudorfer, C., Namasivayam, A. A., Loh, A., Gramer, R. M., Ibrahim, G. M., Valiante, T., Tomaszczyk, J. C., McAndrews, M. P., Kucharczyk, W., Boutet, A., &amp; Lozano, A. M. (2021). Impact of mesial temporal lobe resection on brain structure in medically refractory epilepsy. World Neurosurgery, 152 , e652 e e665. https://doi.org/ 10.1016/j.wneu.2021.06.039

Faber, J., Schoene-Bake, J. C., Trautner, P., Von Lehe, M., Elger, C. E., &amp; Weber, B. (2013). Progressive fiber tract affections

- after temporal lobe surgery. apr Epilepsia, 54 (4), e53 e e57. https://doi.org/10.1111/EPI.12077.
- Fischl, B. (2012). FreeSurfer. NeuroImage, 62 (2), 774 e 781. https:// doi.org/10.1016/j.neuroimage.2012.01.021

Foldvary-Schaefer, N., &amp; Wyllie, E. (2007). In C. G. Goetz (Ed.) (3rd ed., Textbook of clinical neurologyChapter 52 - epilepsy (pp. 1213 e 1244). W.B. Saunders. https://doi.org/10.1016/B978141603618-0.10052-9.

Galovic, M., de Tisi, J., McEvoy, A. W., Miserocchi, A., Vos, S. B., Borzi, G., Rosillo, J. C., Vuong, K. A., Nachev, P., Duncan, J. S., &amp; Koepp, M. J. (2020). Resective surgery prevents progressive cortical thinning in temporal lobe epilepsy. Brain: a Journal of Neurology, 143 (11), 3262 e 3272. https://doi.org/10.1093/BRAIN/ AWAA284

Garcia-Ramos, C., Nair, V., Maganti, R., Mathis, J., Conant, L. L., Prabhakaran, V., &amp; Struck, A. F. (2022). Network phenotypes and their clinical significance in temporal lobe epilepsy using machine learning applications to morphological and functional graph theory metrics. aug Scientific Reports, 12 (1), 1 e 12. https://doi.org/10.1038/s41598-022-18495-z.

- Leiberg, K., Papasavvas, C., &amp; Wang, Y. (2021). Local morphological measures confirm that folding within small partitions of the human cortex follows universal scaling law. sep. In Medical image computing and computer assisted Intervention e MICCAI 2021, 12907 LNCS (pp. 691 e 700). https:// doi.org/10.1007/978-3-030-87234-2\_65.
- Li, W., Jiang, Y., Qin, Y., Li, X., Lei, D., Zhang, H., Luo, C., Gong, Q., Zhou, D., &amp; An, D. (2022). Cortical remodeling before and after successful temporal lobe epilepsy surgery. Acta Neurologica Scandinavica, 146 (2), 144 e 151. https://doi.org/10.1111/ ane.13631
- Liao, W., Ji, G.-J., Xu, Q., Wei, W., Wang, J., Wang, Z., &amp; Lu, G. (2016). Functional connectome before and following temporal lobectomy in mesial temporal lobe epilepsy. Scientific Reports, 6 (1), 23153, 23153.
- Martin, R. C., Sawrie, S. M., Roth, D. L., Gilliam, F. G., Faught, E., Morawetz, R. B., &amp; Kuzniecky, R. (1998). Individual memory change after anterior temporal lobectomy: A base rate analysis using regression-based outcome methodology. Epilepsia, 39 (10), 1075 e 1082. https://doi.org/10.1111/J.15281157.1998.TB01293.X
- McDonald, C. R., Hagler, D. J., Girard, H. M., Pung, C., Ahmadi, M. E., Holland, D., &amp; Dale, A. M. (2010). Changes in fiber tract integrity and visual fields after anterior temporal lobectomy. Neurology, 75 (18), 1631 e 1638. https://doi.org/ 10.1212/WNL.0B013E3181FB44DB
- Morgan, V. L., Rogers, B. P., Gonz /C19 alez, H. F., Goodale, S. E., &amp; Englot, D. J. (2020). Characterization of postsurgical functional connectivity changes in temporal lobe epilepsy. Journal of neurosurgery, 133 (2), 392 e 402.
- Mota, B., &amp; Herculano-Houzel, S. (2015). Cortical folding scales universally with surface area and thickness, not number of neurons. Science, 349 (6243), 74 e 77. https://doi.org/10.1126/ science.aaa9101

Neudorf, J., Kress, S., Gould, L., Gibb, K., Mickleborough, M., &amp; Borowsky, R. (2020). Language lateralization differences between left and right temporal lobe epilepsy as measured by overt word reading fMRI activation and DTI structural connectivity. Epilepsy &amp; Behavior, 112 , Article 107467. https:// doi.org/10.1016/J.YEBEH.2020.107467

- Nooner, K. B., Colcombe, S. J., Tobe, R. H., Mennes, M., Benedict, M. M., Moreno, A. L., … Milham, M. P. (2012). The NKI-rockland sample: A model for accelerating the pace of discovery science in psychiatry. The Florida Nurse, 6 . https:// doi.org/10.3389/fnins.2012.00152
- Nowell, M., Vos, S. B., Sidhu, M., Wilcoxen, K., Sargsyan, N., Ourselin, S., &amp; Duncan, J. S. (2016). Meyer ' s loop asymmetry and language lateralisation in epilepsy. Journal of Neurology,

- Neurosurgery, and Psychiatry, 87 (8), 836 e 842. https://doi.org/ 10.1136/JNNP-2015-311161
- Pajkert, A., Ploner, C. J., Lehmann, T. N., Witte, V. A., Oltmanns, F., Sommer, W., Holtkamp, M., Heekeren, H. R., &amp; Finke, C. (2020). Early volumetric changes of hippocampus and medial prefrontal cortex following medial temporal lobe resection. European Journal of Neuroscience, 52 (10), 4375 e 4384. https:// doi.org/10.1111/ejn.14784
- Panizzon, M. S., Fennema-Notestine, C., Eyler, L. T., Jernigan, T. L., Prom-Wormley, E., Neale, M., Jacobson, K., Lyons, M. J., Grant, M. D., Franz, C. E., Xian, H., Tsuang, M., Fischl, B., Seidman, L., Dale, A., &amp; Kremen, W. S. (2009). Distinct genetic influences on cortical surface area and cortical thickness. Cerebral cortex (New York, N.Y. : 1991), 19 (11), 2728 e 2735. https://doi.org/10.1093/CERCOR/BHP026
- Pustina, D., Doucet, G., Evans, J., Sharan, A., Sperling, M., Skidmore, C., &amp; Tracy, J. (2014). Distinct types of white matter changes are observed after anterior temporal lobectomy in epilepsy. Plos One, 9 (8), Article e104211. https://doi.org/10.1371/ JOURNAL.PONE.0104211
- Rutherford, S., Barkema, P., Tso, I. F., Sripada, C., Beckmann, C., Ruhe, H. G., &amp; Marquand, A. F. (2023). Evidence for embracing normative modeling. eLife, 12 , 1 e 24. https://doi.org/10.7554/ eLife.85082
- Schaer, M., Bach Cuadra, M., Tamarit, L., Lazeyras, F., Eliez, S., &amp; Thiran, J. P. (2008). A Surface-based approach to quantify local cortical gyrification. IEEE Transactions on Medical Imaging, 27 (2), 161 e 170. https://doi.org/10.1109/TMI.2007.903576
- Vos, S. B., Winston, G. P., Goodkin, O., Pemberton, H. G., Barkhof, F., Prados, F., Galovic, M., Koepp, M., Ourselin, S., Cardoso, M. J., &amp; Duncan, J. S. (2020). Hippocampal profiling: Localized magnetic resonance imaging volumetry and T2 relaxometry for hippocampal sclerosis. Epilepsia, 61 (2), 297 e 309. https://doi.org/10.1111/EPI.16416
- Wang, Y., Leiberg, K., Ludwig, T., Little, B., Necus, J. H., Winston, G., Vos, S. B., de Tisi, J., Duncan, J. S., Taylor, P. N., &amp; Mota, B. (2021). Independent components of human brain morphology. NeuroImage, 226 , 117546. https://doi.org/10.1016/ j.neuroimage.2020.117546
- Wang, Y., Necus, J., Kaiser, M., &amp; Mota, B. (2016). Universality in human cortical folding in health and disease. nov Proceedings
- of the National Academy of Sciences of the United States of America, 113 (45), 12820 e 12825. https://doi.org/10.1073/pnas.161017 5113.
- Wang, Y., Necus, J., Rodriguez, L. P., Taylor, P. N., &amp; Mota, B. (2019). Humancortical folding across regions within individual brains follows universal scaling law. Communications Biology, 2 (1), 1 e 8. https://doi.org/10.1038/s42003-019-0421-7
- Whelan, C. D., Altmann, A., Botı ´a, J. A., Jahanshad, N., Hibar, D. P., Absil, J., Alhusaini, S., Alvim, M. K. M., Auvinen, P., Bartolini, E., Bergo, F. P. G., Bernardes, T., Blackmon, K., Braga, B., Caligiuri, M. E., Calvo, A., Carr, S. J., Chen, J., &amp; Chen, S. (2018). Structural brain abnormalities in the common epilepsies assessed in a worldwide ENIGMA study. Brain: a Journal of Neurology, 141 (2), 391 e 408. https://doi.org/10.1093/ brain/awx341
- Wieser, H. G., Blume, W. T., Fish, D., Goldensohn, E., Hufnagel, A., King, D., Sperling, M. R., &amp; Lu ¨ ders, H. (2001). Proposal for a new classification of outcome with respect to epileptic seizures following epilepsy surgery. Epilepsia, 42 (2), 282 e 286. https:// doi.org/10.1046/J.1528-1157.2001.35100.X
- Winston, G. P., Stretton, J., Sidhu, M. K., Symms, M. R., &amp; Duncan, J. S. (2013). Progressive white matter changes following anterior temporal lobe resection for epilepsy. NeuroImage. Clinical, 4 , 190 e 200. https://doi.org/10.1016/ J.NICL.2013.12.004
- Worsley, K., Taylor, J., Carbonell, F., Chung, M., Duerden, E., Bernhardt, B., &amp; Evans, A. (2009). SurfStat: A Matlab toolbox for the statistical analysis of univariate and multivariate surface and volumetric data using linear mixed effects models and random field theory. NeuroImage, 47 , S102. https://doi.org/ 10.1016/S1053-8119(09)70882-1
- Yogarajah, M., Focke, N. K., Bonelli, S. B., Thompson, P., Vollmar, C., McEvoy, A. W., &amp; Duncan, J. S. (2010). The structural plasticity of white matter networks following anterior temporal lobe resection. Brain: a Journal of Neurology, 133 (8), 2348 e 2364. https://doi.org/10.1093/BRAIN/AWQ175
